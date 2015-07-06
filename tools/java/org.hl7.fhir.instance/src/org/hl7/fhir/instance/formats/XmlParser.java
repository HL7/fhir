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

// Generated on Mon, Jul 6, 2015 16:44+1000 for FHIR v0.5.0

import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.UnsignedIntType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.Base64BinaryType;
import org.hl7.fhir.instance.model.TimeType;
import org.hl7.fhir.instance.model.OidType;
import org.hl7.fhir.instance.model.PositiveIntType;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.UuidType;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.*;
import org.xmlpull.v1.*;
import org.hl7.fhir.utilities.Utilities;

public class XmlParser extends XmlParserBase {

  public XmlParser() {
    super();
  }

  public XmlParser(boolean allowUnknownContent) {
    super();
    setAllowUnknownContent(allowUnknownContent);
  }


  protected boolean parseElementContent(int eventType, XmlPullParser xpp, Element res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) 
      res.getExtension().add(parseExtension(xpp));
    else
      return false;
      
    return true;
  }

  protected boolean parseBackboneContent(int eventType, XmlPullParser xpp, BackboneElement res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifierExtension")) 
      res.getModifierExtension().add(parseExtension(xpp));
    else
      return parseElementContent(eventType, xpp, res);
      
    return true;
  }

  @SuppressWarnings("unchecked")
  protected <E extends Enum<E>> Enumeration<E> parseEnumeration(XmlPullParser xpp, E item, EnumFactory e) throws Exception {
    Enumeration<E> res = new Enumeration<E>(e);
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

  protected IntegerType parseInteger(XmlPullParser xpp) throws Exception {
    IntegerType res = new IntegerType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected DateTimeType parseDateTime(XmlPullParser xpp) throws Exception {
    DateTimeType res = new DateTimeType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected UnsignedIntType parseUnsignedInt(XmlPullParser xpp) throws Exception {
    UnsignedIntType res = new UnsignedIntType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected CodeType parseCode(XmlPullParser xpp) throws Exception {
    CodeType res = new CodeType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected DateType parseDate(XmlPullParser xpp) throws Exception {
    DateType res = new DateType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected DecimalType parseDecimal(XmlPullParser xpp) throws Exception {
    DecimalType res = new DecimalType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected UriType parseUri(XmlPullParser xpp) throws Exception {
    UriType res = new UriType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected IdType parseId(XmlPullParser xpp) throws Exception {
    IdType res = new IdType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected Base64BinaryType parseBase64Binary(XmlPullParser xpp) throws Exception {
    Base64BinaryType res = new Base64BinaryType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected TimeType parseTime(XmlPullParser xpp) throws Exception {
    TimeType res = new TimeType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected OidType parseOid(XmlPullParser xpp) throws Exception {
    OidType res = new OidType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected PositiveIntType parsePositiveInt(XmlPullParser xpp) throws Exception {
    PositiveIntType res = new PositiveIntType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected StringType parseString(XmlPullParser xpp) throws Exception {
    StringType res = new StringType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected BooleanType parseBoolean(XmlPullParser xpp) throws Exception {
    BooleanType res = new BooleanType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected UuidType parseUuid(XmlPullParser xpp) throws Exception {
    UuidType res = new UuidType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected InstantType parseInstant(XmlPullParser xpp) throws Exception {
    InstantType res = new InstantType(xpp.getAttributeValue(null, "value"));
    parseElementAttributes(xpp, res);
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

  protected Extension parseExtension(XmlPullParser xpp) throws Exception {
    Extension res = new Extension();
    parseElementAttributes(xpp, res);
    if (xpp.getAttributeValue(null, "url") != null)
        res.setUrl(xpp.getAttributeValue(null, "url"));
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

  protected Narrative parseNarrative(XmlPullParser xpp) throws Exception {
    Narrative res = new Narrative();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Narrative.NarrativeStatus.NULL, new Narrative.NarrativeStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("div")) {
        res.setDiv(parseXhtml(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Period parsePeriod(XmlPullParser xpp) throws Exception {
    Period res = new Period();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStartElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEndElement(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Coding parseCoding(XmlPullParser xpp) throws Exception {
    Coding res = new Coding();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("userSelected")) {
        res.setUserSelectedElement(parseBoolean(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Range parseRange(XmlPullParser xpp) throws Exception {
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

  protected Quantity parseQuantity(XmlPullParser xpp) throws Exception {
    Quantity res = new Quantity();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparatorElement(parseEnumeration(xpp, Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnitsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Attachment parseAttachment(XmlPullParser xpp) throws Exception {
    Attachment res = new Attachment();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contentType")) {
        res.setContentTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguageElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("data")) {
        res.setDataElement(parseBase64Binary(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("size")) {
        res.setSizeElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("hash")) {
        res.setHashElement(parseBase64Binary(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("creation")) {
        res.setCreationElement(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Ratio parseRatio(XmlPullParser xpp) throws Exception {
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

  protected Annotation parseAnnotation(XmlPullParser xpp) throws Exception {
    Annotation res = new Annotation();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "author")) {
        res.setAuthor(parseType("author", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("time")) {
        res.setTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected SampledData parseSampledData(XmlPullParser xpp) throws Exception {
    SampledData res = new SampledData();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("origin")) {
        res.setOrigin(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriodElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactorElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lowerLimit")) {
        res.setLowerLimitElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("upperLimit")) {
        res.setUpperLimitElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dimensions")) {
        res.setDimensionsElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("data")) {
        res.setDataElement(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Reference parseReference(XmlPullParser xpp) throws Exception {
    Reference res = new Reference();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReferenceElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CodeableConcept parseCodeableConcept(XmlPullParser xpp) throws Exception {
    CodeableConcept res = new CodeableConcept();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coding")) {
        res.getCoding().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Identifier parseIdentifier(XmlPullParser xpp) throws Exception {
    Identifier res = new Identifier();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUseElement(parseEnumeration(xpp, Identifier.IdentifierUse.NULL, new Identifier.IdentifierUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assigner")) {
        res.setAssigner(parseReference(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Signature parseSignature(XmlPullParser xpp) throws Exception {
    Signature res = new Signature();
    parseTypeAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhenElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "who")) {
        res.setWho(parseType("who", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("blob")) {
        res.setBlobElement(parseBase64Binary(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Age parseAge(XmlPullParser xpp) throws Exception {
    Age res = new Age();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparatorElement(parseEnumeration(xpp, Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnitsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Count parseCount(XmlPullParser xpp) throws Exception {
    Count res = new Count();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparatorElement(parseEnumeration(xpp, Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnitsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Money parseMoney(XmlPullParser xpp) throws Exception {
    Money res = new Money();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparatorElement(parseEnumeration(xpp, Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnitsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Distance parseDistance(XmlPullParser xpp) throws Exception {
    Distance res = new Distance();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparatorElement(parseEnumeration(xpp, Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnitsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Duration parseDuration(XmlPullParser xpp) throws Exception {
    Duration res = new Duration();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparatorElement(parseEnumeration(xpp, Quantity.QuantityComparator.NULL, new Quantity.QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnitsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ElementDefinition parseElementDefinition(XmlPullParser xpp) throws Exception {
    ElementDefinition res = new ElementDefinition();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("path")) {
        res.setPathElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("representation")) {
        res.getRepresentation().add(parseEnumeration(xpp, ElementDefinition.PropertyRepresentation.NULL, new ElementDefinition.PropertyRepresentationEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("label")) {
        res.setLabelElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("slicing")) {
        res.setSlicing(parseElementDefinitionElementDefinitionSlicingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("short")) {
        res.setShortElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinitionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setCommentsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("alias")) {
        res.getAlias().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("min")) {
        res.setMinElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("max")) {
        res.setMaxElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseElementDefinitionTypeRefComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("nameReference")) {
        res.setNameReferenceElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "defaultValue")) {
        res.setDefaultValue(parseType("defaultValue", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("meaningWhenMissing")) {
        res.setMeaningWhenMissingElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "fixed")) {
        res.setFixed(parseType("fixed", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "pattern")) {
        res.setPattern(parseType("pattern", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "example")) {
        res.setExample(parseType("example", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxLength")) {
        res.setMaxLengthElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.getCondition().add(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("constraint")) {
        res.getConstraint().add(parseElementDefinitionElementDefinitionConstraintComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mustSupport")) {
        res.setMustSupportElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isModifier")) {
        res.setIsModifierElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isSummary")) {
        res.setIsSummaryElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("binding")) {
        res.setBinding(parseElementDefinitionElementDefinitionBindingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mapping")) {
        res.getMapping().add(parseElementDefinitionElementDefinitionMappingComponent(xpp, res));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ElementDefinition.ElementDefinitionSlicingComponent parseElementDefinitionElementDefinitionSlicingComponent(XmlPullParser xpp, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionSlicingComponent res = new ElementDefinition.ElementDefinitionSlicingComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("discriminator")) {
        res.getDiscriminator().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ordered")) {
        res.setOrderedElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rules")) {
        res.setRulesElement(parseEnumeration(xpp, ElementDefinition.SlicingRules.NULL, new ElementDefinition.SlicingRulesEnumFactory()));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ElementDefinition.TypeRefComponent parseElementDefinitionTypeRefComponent(XmlPullParser xpp, ElementDefinition owner) throws Exception {
    ElementDefinition.TypeRefComponent res = new ElementDefinition.TypeRefComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.getProfile().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("aggregation")) {
        res.getAggregation().add(parseEnumeration(xpp, ElementDefinition.AggregationMode.NULL, new ElementDefinition.AggregationModeEnumFactory()));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ElementDefinition.ElementDefinitionConstraintComponent parseElementDefinitionElementDefinitionConstraintComponent(XmlPullParser xpp, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionConstraintComponent res = new ElementDefinition.ElementDefinitionConstraintComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("key")) {
        res.setKeyElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverityElement(parseEnumeration(xpp, ElementDefinition.ConstraintSeverity.NULL, new ElementDefinition.ConstraintSeverityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("human")) {
        res.setHumanElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("xpath")) {
        res.setXpathElement(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ElementDefinition.ElementDefinitionBindingComponent parseElementDefinitionElementDefinitionBindingComponent(XmlPullParser xpp, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionBindingComponent res = new ElementDefinition.ElementDefinitionBindingComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("strength")) {
        res.setStrengthElement(parseEnumeration(xpp, Enumerations.BindingStrength.NULL, new Enumerations.BindingStrengthEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "valueSet")) {
        res.setValueSet(parseType("valueSet", xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ElementDefinition.ElementDefinitionMappingComponent parseElementDefinitionElementDefinitionMappingComponent(XmlPullParser xpp, ElementDefinition owner) throws Exception {
    ElementDefinition.ElementDefinitionMappingComponent res = new ElementDefinition.ElementDefinitionMappingComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identity")) {
        res.setIdentityElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguageElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("map")) {
        res.setMapElement(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Timing parseTiming(XmlPullParser xpp) throws Exception {
    Timing res = new Timing();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeat")) {
        res.setRepeat(parseTimingTimingRepeatComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Timing.TimingRepeatComponent parseTimingTimingRepeatComponent(XmlPullParser xpp, Timing owner) throws Exception {
    Timing.TimingRepeatComponent res = new Timing.TimingRepeatComponent();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "bounds")) {
        res.setBounds(parseType("bounds", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("count")) {
        res.setCountElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("duration")) {
        res.setDurationElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("durationMax")) {
        res.setDurationMaxElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("durationUnits")) {
        res.setDurationUnitsElement(parseEnumeration(xpp, Timing.UnitsOfTime.NULL, new Timing.UnitsOfTimeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frequency")) {
        res.setFrequencyElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frequencyMax")) {
        res.setFrequencyMaxElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriodElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("periodMax")) {
        res.setPeriodMaxElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("periodUnits")) {
        res.setPeriodUnitsElement(parseEnumeration(xpp, Timing.UnitsOfTime.NULL, new Timing.UnitsOfTimeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhenElement(parseEnumeration(xpp, Timing.EventTiming.NULL, new Timing.EventTimingEnumFactory()));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Address parseAddress(XmlPullParser xpp) throws Exception {
    Address res = new Address();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUseElement(parseEnumeration(xpp, Address.AddressUse.NULL, new Address.AddressUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Address.AddressType.NULL, new Address.AddressTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("line")) {
        res.getLine().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("city")) {
        res.setCityElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("state")) {
        res.setStateElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("postalCode")) {
        res.setPostalCodeElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("country")) {
        res.setCountryElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected HumanName parseHumanName(XmlPullParser xpp) throws Exception {
    HumanName res = new HumanName();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUseElement(parseEnumeration(xpp, HumanName.NameUse.NULL, new HumanName.NameUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
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

  protected Meta parseMeta(XmlPullParser xpp) throws Exception {
    Meta res = new Meta();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("versionId")) {
        res.setVersionIdElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastUpdated")) {
        res.setLastUpdatedElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.getProfile().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("security")) {
        res.getSecurity().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("tag")) {
        res.getTag().add(parseCoding(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ContactPoint parseContactPoint(XmlPullParser xpp) throws Exception {
    ContactPoint res = new ContactPoint();
    parseElementAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseEnumeration(xpp, ContactPoint.ContactPointSystem.NULL, new ContactPoint.ContactPointSystemEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUseElement(parseEnumeration(xpp, ContactPoint.ContactPointUse.NULL, new ContactPoint.ContactPointUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Parameters parseParameters(XmlPullParser xpp) throws Exception {
    Parameters res = new Parameters();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseParametersParametersParameterComponent(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Parameters.ParametersParameterComponent parseParametersParametersParameterComponent(XmlPullParser xpp, Parameters owner) throws Exception {
    Parameters.ParametersParameterComponent res = new Parameters.ParametersParameterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("resource")) {
        res.setResource(parseResourceContained(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("part")) {
        res.getPart().add(parseParametersParametersParameterComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected void parseResourceAttributes(XmlPullParser xpp, Resource res) throws Exception {
  }

  protected boolean parseResourceContent(int eventType, XmlPullParser xpp, Resource res) throws Exception {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("id")) {
        res.setIdElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("meta")) {
        res.setMeta(parseMeta(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("implicitRules")) {
        res.setImplicitRulesElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguageElement(parseCode(xpp));
    } else
        return false;
    return true;
  }

  protected void parseDomainResourceAttributes(XmlPullParser xpp, DomainResource res) throws Exception {
    parseResourceAttributes(xpp, res);
  }

  protected boolean parseDomainResourceContent(int eventType, XmlPullParser xpp, DomainResource res) throws Exception {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseNarrative(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contained")) {
        res.getContained().add(parseResourceContained(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) {
        res.getExtension().add(parseExtension(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifierExtension")) {
        res.getModifierExtension().add(parseExtension(xpp));
    } else
    return parseResourceContent(eventType, xpp, res);
    return true;
  }

  protected AllergyIntolerance parseAllergyIntolerance(XmlPullParser xpp) throws Exception {
    AllergyIntolerance res = new AllergyIntolerance();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recordedDate")) {
        res.setRecordedDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recorder")) {
        res.setRecorder(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reporter")) {
        res.setReporter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substance")) {
        res.setSubstance(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, AllergyIntolerance.AllergyIntoleranceStatus.NULL, new AllergyIntolerance.AllergyIntoleranceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("criticality")) {
        res.setCriticalityElement(parseEnumeration(xpp, AllergyIntolerance.AllergyIntoleranceCriticality.NULL, new AllergyIntolerance.AllergyIntoleranceCriticalityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, AllergyIntolerance.AllergyIntoleranceType.NULL, new AllergyIntolerance.AllergyIntoleranceTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategoryElement(parseEnumeration(xpp, AllergyIntolerance.AllergyIntoleranceCategory.NULL, new AllergyIntolerance.AllergyIntoleranceCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastOccurence")) {
        res.setLastOccurenceElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseAllergyIntoleranceAllergyIntoleranceEventComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AllergyIntolerance.AllergyIntoleranceEventComponent parseAllergyIntoleranceAllergyIntoleranceEventComponent(XmlPullParser xpp, AllergyIntolerance owner) throws Exception {
    AllergyIntolerance.AllergyIntoleranceEventComponent res = new AllergyIntolerance.AllergyIntoleranceEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substance")) {
        res.setSubstance(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("certainty")) {
        res.setCertaintyElement(parseEnumeration(xpp, AllergyIntolerance.AllergyIntoleranceCertainty.NULL, new AllergyIntolerance.AllergyIntoleranceCertaintyEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manifestation")) {
        res.getManifestation().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("onset")) {
        res.setOnsetElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("duration")) {
        res.setDuration(parseDuration(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverityElement(parseEnumeration(xpp, AllergyIntolerance.AllergyIntoleranceSeverity.NULL, new AllergyIntolerance.AllergyIntoleranceSeverityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("exposureRoute")) {
        res.setExposureRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Appointment parseAppointment(XmlPullParser xpp) throws Exception {
    Appointment res = new Appointment();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Appointment.AppointmentStatus.NULL, new Appointment.AppointmentStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriorityElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStartElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEndElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("slot")) {
        res.getSlot().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("order")) {
        res.setOrder(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseAppointmentAppointmentParticipantComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Appointment.AppointmentParticipantComponent parseAppointmentAppointmentParticipantComponent(XmlPullParser xpp, Appointment owner) throws Exception {
    Appointment.AppointmentParticipantComponent res = new Appointment.AppointmentParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("required")) {
        res.setRequiredElement(parseEnumeration(xpp, Appointment.ParticipantRequired.NULL, new Appointment.ParticipantRequiredEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Appointment.ParticipationStatus.NULL, new Appointment.ParticipationStatusEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AppointmentResponse parseAppointmentResponse(XmlPullParser xpp) throws Exception {
    AppointmentResponse res = new AppointmentResponse();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("appointment")) {
        res.setAppointment(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participantType")) {
        res.getParticipantType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participantStatus")) {
        res.setParticipantStatusElement(parseEnumeration(xpp, AppointmentResponse.ParticipantStatus.NULL, new AppointmentResponse.ParticipantStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStartElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEndElement(parseInstant(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent parseAuditEvent(XmlPullParser xpp) throws Exception {
    AuditEvent res = new AuditEvent();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.setEvent(parseAuditEventAuditEventEventComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseAuditEventAuditEventParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseAuditEventAuditEventSourceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("object")) {
        res.getObject().add(parseAuditEventAuditEventObjectComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent.AuditEventEventComponent parseAuditEventAuditEventEventComponent(XmlPullParser xpp, AuditEvent owner) throws Exception {
    AuditEvent.AuditEventEventComponent res = new AuditEvent.AuditEventEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subtype")) {
        res.getSubtype().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.setActionElement(parseEnumeration(xpp, AuditEvent.AuditEventAction.NULL, new AuditEvent.AuditEventActionEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTimeElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcomeElement(parseEnumeration(xpp, AuditEvent.AuditEventOutcome.NULL, new AuditEvent.AuditEventOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcomeDesc")) {
        res.setOutcomeDescElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("purposeOfEvent")) {
        res.getPurposeOfEvent().add(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent.AuditEventParticipantComponent parseAuditEventAuditEventParticipantComponent(XmlPullParser xpp, AuditEvent owner) throws Exception {
    AuditEvent.AuditEventParticipantComponent res = new AuditEvent.AuditEventParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("userId")) {
        res.setUserIdElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("altId")) {
        res.setAltIdElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestor")) {
        res.setRequestorElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("policy")) {
        res.getPolicy().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("media")) {
        res.setMedia(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("network")) {
        res.setNetwork(parseAuditEventAuditEventParticipantNetworkComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("purposeOfUse")) {
        res.getPurposeOfUse().add(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent.AuditEventParticipantNetworkComponent parseAuditEventAuditEventParticipantNetworkComponent(XmlPullParser xpp, AuditEvent owner) throws Exception {
    AuditEvent.AuditEventParticipantNetworkComponent res = new AuditEvent.AuditEventParticipantNetworkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifierElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, AuditEvent.AuditEventParticipantNetworkType.NULL, new AuditEvent.AuditEventParticipantNetworkTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent.AuditEventSourceComponent parseAuditEventAuditEventSourceComponent(XmlPullParser xpp, AuditEvent owner) throws Exception {
    AuditEvent.AuditEventSourceComponent res = new AuditEvent.AuditEventSourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSiteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifierElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent.AuditEventObjectComponent parseAuditEventAuditEventObjectComponent(XmlPullParser xpp, AuditEvent owner) throws Exception {
    AuditEvent.AuditEventObjectComponent res = new AuditEvent.AuditEventObjectComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, AuditEvent.AuditEventObjectType.NULL, new AuditEvent.AuditEventObjectTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRoleElement(parseEnumeration(xpp, AuditEvent.AuditEventObjectRole.NULL, new AuditEvent.AuditEventObjectRoleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lifecycle")) {
        res.setLifecycleElement(parseEnumeration(xpp, AuditEvent.AuditEventObjectLifecycle.NULL, new AuditEvent.AuditEventObjectLifecycleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sensitivity")) {
        res.setSensitivity(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("query")) {
        res.setQueryElement(parseBase64Binary(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseAuditEventAuditEventObjectDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected AuditEvent.AuditEventObjectDetailComponent parseAuditEventAuditEventObjectDetailComponent(XmlPullParser xpp, AuditEvent owner) throws Exception {
    AuditEvent.AuditEventObjectDetailComponent res = new AuditEvent.AuditEventObjectDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseBase64Binary(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Basic parseBasic(XmlPullParser xpp) throws Exception {
    Basic res = new Basic();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDate(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Binary parseBinary(XmlPullParser xpp) throws Exception {
    Binary res = new Binary();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contentType")) {
        res.setContentTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContentElement(parseBase64Binary(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected BodySite parseBodySite(XmlPullParser xpp) throws Exception {
    BodySite res = new BodySite();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifier")) {
        res.getModifier().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("image")) {
        res.getImage().add(parseAttachment(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Bundle parseBundle(XmlPullParser xpp) throws Exception {
    Bundle res = new Bundle();
    parseResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Bundle.BundleType.NULL, new Bundle.BundleTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBaseElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("total")) {
        res.setTotalElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parseBundleBundleLinkComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entry")) {
        res.getEntry().add(parseBundleBundleEntryComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("signature")) {
        res.setSignatureElement(parseBase64Binary(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Bundle.BundleLinkComponent parseBundleBundleLinkComponent(XmlPullParser xpp, Bundle owner) throws Exception {
    Bundle.BundleLinkComponent res = new Bundle.BundleLinkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relation")) {
        res.setRelationElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Bundle.BundleEntryComponent parseBundleBundleEntryComponent(XmlPullParser xpp, Bundle owner) throws Exception {
    Bundle.BundleEntryComponent res = new Bundle.BundleEntryComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBaseElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parseBundleBundleLinkComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("resource")) {
        res.setResource(parseResourceContained(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("search")) {
        res.setSearch(parseBundleBundleEntrySearchComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("transaction")) {
        res.setTransaction(parseBundleBundleEntryTransactionComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("transactionResponse")) {
        res.setTransactionResponse(parseBundleBundleEntryTransactionResponseComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Bundle.BundleEntrySearchComponent parseBundleBundleEntrySearchComponent(XmlPullParser xpp, Bundle owner) throws Exception {
    Bundle.BundleEntrySearchComponent res = new Bundle.BundleEntrySearchComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setModeElement(parseEnumeration(xpp, Bundle.SearchEntryMode.NULL, new Bundle.SearchEntryModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("score")) {
        res.setScoreElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Bundle.BundleEntryTransactionComponent parseBundleBundleEntryTransactionComponent(XmlPullParser xpp, Bundle owner) throws Exception {
    Bundle.BundleEntryTransactionComponent res = new Bundle.BundleEntryTransactionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethodElement(parseEnumeration(xpp, Bundle.HTTPVerb.NULL, new Bundle.HTTPVerbEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ifNoneMatch")) {
        res.setIfNoneMatchElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ifMatch")) {
        res.setIfMatchElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ifModifiedSince")) {
        res.setIfModifiedSinceElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ifNoneExist")) {
        res.setIfNoneExistElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Bundle.BundleEntryTransactionResponseComponent parseBundleBundleEntryTransactionResponseComponent(XmlPullParser xpp, Bundle owner) throws Exception {
    Bundle.BundleEntryTransactionResponseComponent res = new Bundle.BundleEntryTransactionResponseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocationElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("etag")) {
        res.setEtagElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastModified")) {
        res.setLastModifiedElement(parseInstant(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CarePlan parseCarePlan(XmlPullParser xpp) throws Exception {
    CarePlan res = new CarePlan();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, CarePlan.CarePlanStatus.NULL, new CarePlan.CarePlanStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modified")) {
        res.setModifiedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.getCategory().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concern")) {
        res.getConcern().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("support")) {
        res.getSupport().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseCarePlanCarePlanParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("goal")) {
        res.getGoal().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("activity")) {
        res.getActivity().add(parseCarePlanCarePlanActivityComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CarePlan.CarePlanParticipantComponent parseCarePlanCarePlanParticipantComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanParticipantComponent res = new CarePlan.CarePlanParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("member")) {
        res.setMember(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CarePlan.CarePlanActivityComponent parseCarePlanCarePlanActivityComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivityComponent res = new CarePlan.CarePlanActivityComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actionResulting")) {
        res.getActionResulting().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReference(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetail(parseCarePlanCarePlanActivityDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CarePlan.CarePlanActivityDetailComponent parseCarePlanCarePlanActivityDetailComponent(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.CarePlanActivityDetailComponent res = new CarePlan.CarePlanActivityDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategoryElement(parseEnumeration(xpp, CarePlan.CarePlanActivityCategory.NULL, new CarePlan.CarePlanActivityCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("goal")) {
        res.getGoal().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, CarePlan.CarePlanActivityStatus.NULL, new CarePlan.CarePlanActivityStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("statusReason")) {
        res.setStatusReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prohibited")) {
        res.setProhibitedElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "scheduled")) {
        res.setScheduled(parseType("scheduled", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.setProduct(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dailyAmount")) {
        res.setDailyAmount(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim parseClaim(XmlPullParser xpp) throws Exception {
    Claim res = new Claim();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Claim.ClaimType.NULL, new Claim.ClaimTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUseElement(parseEnumeration(xpp, Claim.Use.NULL, new Claim.UseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fundsReserve")) {
        res.setFundsReserve(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("enterer")) {
        res.setEnterer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("facility")) {
        res.setFacility(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prescription")) {
        res.setPrescription(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalPrescription")) {
        res.setOriginalPrescription(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payee")) {
        res.setPayee(parseClaimPayeeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referral")) {
        res.setReferral(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("diagnosis")) {
        res.getDiagnosis().add(parseClaimDiagnosisComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.getCondition().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coverage")) {
        res.getCoverage().add(parseClaimCoverageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("exception")) {
        res.getException().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("school")) {
        res.setSchoolElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accident")) {
        res.setAccidentElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accidentType")) {
        res.setAccidentType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interventionException")) {
        res.getInterventionException().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseClaimItemsComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additionalMaterials")) {
        res.getAdditionalMaterials().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("missingTeeth")) {
        res.getMissingTeeth().add(parseClaimMissingTeethComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.PayeeComponent parseClaimPayeeComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.PayeeComponent res = new Claim.PayeeComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("person")) {
        res.setPerson(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.DiagnosisComponent parseClaimDiagnosisComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.DiagnosisComponent res = new Claim.DiagnosisComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("diagnosis")) {
        res.setDiagnosis(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.CoverageComponent parseClaimCoverageComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.CoverageComponent res = new Claim.CoverageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("focal")) {
        res.setFocalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coverage")) {
        res.setCoverage(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("businessArrangement")) {
        res.setBusinessArrangementElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preAuthRef")) {
        res.getPreAuthRef().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("claimResponse")) {
        res.setClaimResponse(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.ItemsComponent parseClaimItemsComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.ItemsComponent res = new Claim.ItemsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("diagnosisLinkId")) {
        res.getDiagnosisLinkId().add(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.setService(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceDate")) {
        res.setServiceDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unitPrice")) {
        res.setUnitPrice(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactorElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("points")) {
        res.setPointsElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("net")) {
        res.setNet(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("udi")) {
        res.setUdi(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.setBodySite(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subSite")) {
        res.getSubSite().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifier")) {
        res.getModifier().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseClaimDetailComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prosthesis")) {
        res.setProsthesis(parseClaimProsthesisComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.DetailComponent parseClaimDetailComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.DetailComponent res = new Claim.DetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.setService(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unitPrice")) {
        res.setUnitPrice(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactorElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("points")) {
        res.setPointsElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("net")) {
        res.setNet(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("udi")) {
        res.setUdi(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subDetail")) {
        res.getSubDetail().add(parseClaimSubDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.SubDetailComponent parseClaimSubDetailComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.SubDetailComponent res = new Claim.SubDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.setService(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unitPrice")) {
        res.setUnitPrice(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactorElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("points")) {
        res.setPointsElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("net")) {
        res.setNet(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("udi")) {
        res.setUdi(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.ProsthesisComponent parseClaimProsthesisComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.ProsthesisComponent res = new Claim.ProsthesisComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("initial")) {
        res.setInitialElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priorDate")) {
        res.setPriorDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priorMaterial")) {
        res.setPriorMaterial(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Claim.MissingTeethComponent parseClaimMissingTeethComponent(XmlPullParser xpp, Claim owner) throws Exception {
    Claim.MissingTeethComponent res = new Claim.MissingTeethComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("tooth")) {
        res.setTooth(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extractionDate")) {
        res.setExtractionDateElement(parseDate(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse parseClaimResponse(XmlPullParser xpp) throws Exception {
    ClaimResponse res = new ClaimResponse();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestProvider")) {
        res.setRequestProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestOrganization")) {
        res.setRequestOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcomeElement(parseEnumeration(xpp, Enumerations.RemittanceOutcome.NULL, new Enumerations.RemittanceOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("disposition")) {
        res.setDispositionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payeeType")) {
        res.setPayeeType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseClaimResponseItemsComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("addItem")) {
        res.getAddItem().add(parseClaimResponseAddedItemComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("error")) {
        res.getError().add(parseClaimResponseErrorsComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("totalCost")) {
        res.setTotalCost(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unallocDeductable")) {
        res.setUnallocDeductable(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("totalBenefit")) {
        res.setTotalBenefit(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("paymentAdjustment")) {
        res.setPaymentAdjustment(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("paymentAdjustmentReason")) {
        res.setPaymentAdjustmentReason(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("paymentDate")) {
        res.setPaymentDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("paymentAmount")) {
        res.setPaymentAmount(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("paymentRef")) {
        res.setPaymentRef(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reserved")) {
        res.setReserved(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("form")) {
        res.setForm(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.getNote().add(parseClaimResponseNotesComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coverage")) {
        res.getCoverage().add(parseClaimResponseCoverageComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.ItemsComponent parseClaimResponseItemsComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemsComponent res = new ClaimResponse.ItemsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequenceLinkId")) {
        res.setSequenceLinkIdElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("noteNumber")) {
        res.getNoteNumber().add(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("adjudication")) {
        res.getAdjudication().add(parseClaimResponseItemAdjudicationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseClaimResponseItemDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.ItemAdjudicationComponent parseClaimResponseItemAdjudicationComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemAdjudicationComponent res = new ClaimResponse.ItemAdjudicationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.ItemDetailComponent parseClaimResponseItemDetailComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.ItemDetailComponent res = new ClaimResponse.ItemDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequenceLinkId")) {
        res.setSequenceLinkIdElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("adjudication")) {
        res.getAdjudication().add(parseClaimResponseDetailAdjudicationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subDetail")) {
        res.getSubDetail().add(parseClaimResponseSubDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.DetailAdjudicationComponent parseClaimResponseDetailAdjudicationComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.DetailAdjudicationComponent res = new ClaimResponse.DetailAdjudicationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.SubDetailComponent parseClaimResponseSubDetailComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.SubDetailComponent res = new ClaimResponse.SubDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequenceLinkId")) {
        res.setSequenceLinkIdElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("adjudication")) {
        res.getAdjudication().add(parseClaimResponseSubdetailAdjudicationComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.SubdetailAdjudicationComponent parseClaimResponseSubdetailAdjudicationComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.SubdetailAdjudicationComponent res = new ClaimResponse.SubdetailAdjudicationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.AddedItemComponent parseClaimResponseAddedItemComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemComponent res = new ClaimResponse.AddedItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequenceLinkId")) {
        res.getSequenceLinkId().add(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.setService(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fee")) {
        res.setFee(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("noteNumberLinkId")) {
        res.getNoteNumberLinkId().add(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("adjudication")) {
        res.getAdjudication().add(parseClaimResponseAddedItemAdjudicationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseClaimResponseAddedItemsDetailComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.AddedItemAdjudicationComponent parseClaimResponseAddedItemAdjudicationComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemAdjudicationComponent res = new ClaimResponse.AddedItemAdjudicationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.AddedItemsDetailComponent parseClaimResponseAddedItemsDetailComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemsDetailComponent res = new ClaimResponse.AddedItemsDetailComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.setService(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fee")) {
        res.setFee(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("adjudication")) {
        res.getAdjudication().add(parseClaimResponseAddedItemDetailAdjudicationComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.AddedItemDetailAdjudicationComponent parseClaimResponseAddedItemDetailAdjudicationComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.AddedItemDetailAdjudicationComponent res = new ClaimResponse.AddedItemDetailAdjudicationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.ErrorsComponent parseClaimResponseErrorsComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.ErrorsComponent res = new ClaimResponse.ErrorsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequenceLinkId")) {
        res.setSequenceLinkIdElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detailSequenceLinkId")) {
        res.setDetailSequenceLinkIdElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subdetailSequenceLinkId")) {
        res.setSubdetailSequenceLinkIdElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.NotesComponent parseClaimResponseNotesComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.NotesComponent res = new ClaimResponse.NotesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("number")) {
        res.setNumberElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClaimResponse.CoverageComponent parseClaimResponseCoverageComponent(XmlPullParser xpp, ClaimResponse owner) throws Exception {
    ClaimResponse.CoverageComponent res = new ClaimResponse.CoverageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("focal")) {
        res.setFocalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coverage")) {
        res.setCoverage(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("businessArrangement")) {
        res.setBusinessArrangementElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preAuthRef")) {
        res.getPreAuthRef().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("claimResponse")) {
        res.setClaimResponse(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClinicalImpression parseClinicalImpression(XmlPullParser xpp) throws Exception {
    ClinicalImpression res = new ClinicalImpression();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assessor")) {
        res.setAssessor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, ClinicalImpression.ClinicalImpressionStatus.NULL, new ClinicalImpression.ClinicalImpressionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("previous")) {
        res.setPrevious(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("problem")) {
        res.getProblem().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "trigger")) {
        res.setTrigger(parseType("trigger", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("investigations")) {
        res.getInvestigations().add(parseClinicalImpressionClinicalImpressionInvestigationsComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("protocol")) {
        res.setProtocolElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("summary")) {
        res.setSummaryElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("finding")) {
        res.getFinding().add(parseClinicalImpressionClinicalImpressionFindingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("resolved")) {
        res.getResolved().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruledOut")) {
        res.getRuledOut().add(parseClinicalImpressionClinicalImpressionRuledOutComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prognosis")) {
        res.setPrognosisElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("plan")) {
        res.getPlan().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.getAction().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClinicalImpression.ClinicalImpressionInvestigationsComponent parseClinicalImpressionClinicalImpressionInvestigationsComponent(XmlPullParser xpp, ClinicalImpression owner) throws Exception {
    ClinicalImpression.ClinicalImpressionInvestigationsComponent res = new ClinicalImpression.ClinicalImpressionInvestigationsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClinicalImpression.ClinicalImpressionFindingComponent parseClinicalImpressionClinicalImpressionFindingComponent(XmlPullParser xpp, ClinicalImpression owner) throws Exception {
    ClinicalImpression.ClinicalImpressionFindingComponent res = new ClinicalImpression.ClinicalImpressionFindingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("cause")) {
        res.setCauseElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ClinicalImpression.ClinicalImpressionRuledOutComponent parseClinicalImpressionClinicalImpressionRuledOutComponent(XmlPullParser xpp, ClinicalImpression owner) throws Exception {
    ClinicalImpression.ClinicalImpressionRuledOutComponent res = new ClinicalImpression.ClinicalImpressionRuledOutComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReasonElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Communication parseCommunication(XmlPullParser xpp) throws Exception {
    Communication res = new Communication();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sender")) {
        res.setSender(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recipient")) {
        res.getRecipient().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payload")) {
        res.getPayload().add(parseCommunicationCommunicationPayloadComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medium")) {
        res.getMedium().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Communication.CommunicationStatus.NULL, new Communication.CommunicationStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sent")) {
        res.setSentElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("received")) {
        res.setReceivedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.getReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Communication.CommunicationPayloadComponent parseCommunicationCommunicationPayloadComponent(XmlPullParser xpp, Communication owner) throws Exception {
    Communication.CommunicationPayloadComponent res = new Communication.CommunicationPayloadComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "content")) {
        res.setContent(parseType("content", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CommunicationRequest parseCommunicationRequest(XmlPullParser xpp) throws Exception {
    CommunicationRequest res = new CommunicationRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sender")) {
        res.setSender(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recipient")) {
        res.getRecipient().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payload")) {
        res.getPayload().add(parseCommunicationRequestCommunicationRequestPayloadComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("medium")) {
        res.getMedium().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requester")) {
        res.setRequester(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, CommunicationRequest.CommunicationRequestStatus.NULL, new CommunicationRequest.CommunicationRequestStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("scheduledTime")) {
        res.setScheduledTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.getReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedOn")) {
        res.setOrderedOnElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCodeableConcept(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected CommunicationRequest.CommunicationRequestPayloadComponent parseCommunicationRequestCommunicationRequestPayloadComponent(XmlPullParser xpp, CommunicationRequest owner) throws Exception {
    CommunicationRequest.CommunicationRequestPayloadComponent res = new CommunicationRequest.CommunicationRequestPayloadComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "content")) {
        res.setContent(parseType("content", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Composition parseComposition(XmlPullParser xpp) throws Exception {
    Composition res = new Composition();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("class")) {
        res.setClass_(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Composition.CompositionStatus.NULL, new Composition.CompositionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("confidentiality")) {
        res.setConfidentialityElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("attester")) {
        res.getAttester().add(parseCompositionCompositionAttesterComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("custodian")) {
        res.setCustodian(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseCompositionCompositionEventComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("section")) {
        res.getSection().add(parseCompositionSectionComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Composition.CompositionAttesterComponent parseCompositionCompositionAttesterComponent(XmlPullParser xpp, Composition owner) throws Exception {
    Composition.CompositionAttesterComponent res = new Composition.CompositionAttesterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.getMode().add(parseEnumeration(xpp, Composition.CompositionAttestationMode.NULL, new Composition.CompositionAttestationModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("time")) {
        res.setTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("party")) {
        res.setParty(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Composition.CompositionEventComponent parseCompositionCompositionEventComponent(XmlPullParser xpp, Composition owner) throws Exception {
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
        res.getDetail().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Composition.SectionComponent parseCompositionSectionComponent(XmlPullParser xpp, Composition owner) throws Exception {
    Composition.SectionComponent res = new Composition.SectionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContent(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("section")) {
        res.getSection().add(parseCompositionSectionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ConceptMap parseConceptMap(XmlPullParser xpp) throws Exception {
    ConceptMap res = new ConceptMap();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("useContext")) {
        res.getUseContext().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseConceptMapConceptMapContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyrightElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "source")) {
        res.setSource(parseType("source", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "target")) {
        res.setTarget(parseType("target", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseConceptMapSourceElementComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ConceptMap.ConceptMapContactComponent parseConceptMapConceptMapContactComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.ConceptMapContactComponent res = new ConceptMap.ConceptMapContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ConceptMap.SourceElementComponent parseConceptMapSourceElementComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.SourceElementComponent res = new ConceptMap.SourceElementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codeSystem")) {
        res.setCodeSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseConceptMapTargetElementComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ConceptMap.TargetElementComponent parseConceptMapTargetElementComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.TargetElementComponent res = new ConceptMap.TargetElementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codeSystem")) {
        res.setCodeSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("equivalence")) {
        res.setEquivalenceElement(parseEnumeration(xpp, Enumerations.ConceptMapEquivalence.NULL, new Enumerations.ConceptMapEquivalenceEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setCommentsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dependsOn")) {
        res.getDependsOn().add(parseConceptMapOtherElementComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.getProduct().add(parseConceptMapOtherElementComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ConceptMap.OtherElementComponent parseConceptMapOtherElementComponent(XmlPullParser xpp, ConceptMap owner) throws Exception {
    ConceptMap.OtherElementComponent res = new ConceptMap.OtherElementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.setElementElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codeSystem")) {
        res.setCodeSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Condition parseCondition(XmlPullParser xpp) throws Exception {
    Condition res = new Condition();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("asserter")) {
        res.setAsserter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateAsserted")) {
        res.setDateAssertedElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("clinicalStatus")) {
        res.setClinicalStatusElement(parseEnumeration(xpp, Condition.ConditionClinicalStatus.NULL, new Condition.ConditionClinicalStatusEnumFactory()));
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
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dueTo")) {
        res.getDueTo().add(parseConditionConditionDueToComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("occurredFollowing")) {
        res.getOccurredFollowing().add(parseConditionConditionOccurredFollowingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Condition.ConditionStageComponent parseConditionConditionStageComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionStageComponent res = new Condition.ConditionStageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("summary")) {
        res.setSummary(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assessment")) {
        res.getAssessment().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Condition.ConditionEvidenceComponent parseConditionConditionEvidenceComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionEvidenceComponent res = new Condition.ConditionEvidenceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Condition.ConditionLocationComponent parseConditionConditionLocationComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionLocationComponent res = new Condition.ConditionLocationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "site")) {
        res.setSite(parseType("site", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Condition.ConditionDueToComponent parseConditionConditionDueToComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionDueToComponent res = new Condition.ConditionDueToComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Condition.ConditionOccurredFollowingComponent parseConditionConditionOccurredFollowingComponent(XmlPullParser xpp, Condition owner) throws Exception {
    Condition.ConditionOccurredFollowingComponent res = new Condition.ConditionOccurredFollowingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance parseConformance(XmlPullParser xpp) throws Exception {
    Conformance res = new Conformance();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseConformanceConformanceContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyrightElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("software")) {
        res.setSoftware(parseConformanceConformanceSoftwareComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("implementation")) {
        res.setImplementation(parseConformanceConformanceImplementationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fhirVersion")) {
        res.setFhirVersionElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("acceptUnknown")) {
        res.setAcceptUnknownElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("format")) {
        res.getFormat().add(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.getProfile().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rest")) {
        res.getRest().add(parseConformanceConformanceRestComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("messaging")) {
        res.getMessaging().add(parseConformanceConformanceMessagingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("document")) {
        res.getDocument().add(parseConformanceConformanceDocumentComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceContactComponent parseConformanceConformanceContactComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceContactComponent res = new Conformance.ConformanceContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceSoftwareComponent parseConformanceConformanceSoftwareComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceSoftwareComponent res = new Conformance.ConformanceSoftwareComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("releaseDate")) {
        res.setReleaseDateElement(parseDateTime(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceImplementationComponent parseConformanceConformanceImplementationComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceImplementationComponent res = new Conformance.ConformanceImplementationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceRestComponent parseConformanceConformanceRestComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestComponent res = new Conformance.ConformanceRestComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setModeElement(parseEnumeration(xpp, Conformance.RestfulConformanceMode.NULL, new Conformance.RestfulConformanceModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
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
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("compartment")) {
        res.getCompartment().add(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceRestSecurityComponent parseConformanceConformanceRestSecurityComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityComponent res = new Conformance.ConformanceRestSecurityComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("cors")) {
        res.setCorsElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("service")) {
        res.getService().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("certificate")) {
        res.getCertificate().add(parseConformanceConformanceRestSecurityCertificateComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceRestSecurityCertificateComponent parseConformanceConformanceRestSecurityCertificateComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestSecurityCertificateComponent res = new Conformance.ConformanceRestSecurityCertificateComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("blob")) {
        res.setBlobElement(parseBase64Binary(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceRestResourceComponent parseConformanceConformanceRestResourceComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interaction")) {
        res.getInteraction().add(parseConformanceResourceInteractionComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("versioning")) {
        res.setVersioningElement(parseEnumeration(xpp, Conformance.ResourceVersionPolicy.NULL, new Conformance.ResourceVersionPolicyEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("readHistory")) {
        res.setReadHistoryElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("updateCreate")) {
        res.setUpdateCreateElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conditionalCreate")) {
        res.setConditionalCreateElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conditionalUpdate")) {
        res.setConditionalUpdateElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conditionalDelete")) {
        res.setConditionalDeleteElement(parseBoolean(xpp));
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

  protected Conformance.ResourceInteractionComponent parseConformanceResourceInteractionComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ResourceInteractionComponent res = new Conformance.ResourceInteractionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseEnumeration(xpp, Conformance.TypeRestfulInteraction.NULL, new Conformance.TypeRestfulInteractionEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceRestResourceSearchParamComponent parseConformanceConformanceRestResourceSearchParamComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestResourceSearchParamComponent res = new Conformance.ConformanceRestResourceSearchParamComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinitionElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Enumerations.SearchParamType.NULL, new Enumerations.SearchParamTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
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

  protected Conformance.SystemInteractionComponent parseConformanceSystemInteractionComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.SystemInteractionComponent res = new Conformance.SystemInteractionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseEnumeration(xpp, Conformance.SystemRestfulInteraction.NULL, new Conformance.SystemRestfulInteractionEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceRestOperationComponent parseConformanceConformanceRestOperationComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceRestOperationComponent res = new Conformance.ConformanceRestOperationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinition(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceMessagingComponent parseConformanceConformanceMessagingComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingComponent res = new Conformance.ConformanceMessagingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpointElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reliableCache")) {
        res.setReliableCacheElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseConformanceConformanceMessagingEventComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceMessagingEventComponent parseConformanceConformanceMessagingEventComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceMessagingEventComponent res = new Conformance.ConformanceMessagingEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategoryElement(parseEnumeration(xpp, Conformance.MessageSignificanceCategory.NULL, new Conformance.MessageSignificanceCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setModeElement(parseEnumeration(xpp, Conformance.ConformanceEventMode.NULL, new Conformance.ConformanceEventModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("protocol")) {
        res.getProtocol().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("focus")) {
        res.setFocusElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Conformance.ConformanceDocumentComponent parseConformanceConformanceDocumentComponent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.ConformanceDocumentComponent res = new Conformance.ConformanceDocumentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setModeElement(parseEnumeration(xpp, Conformance.DocumentMode.NULL, new Conformance.DocumentModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract parseContract(XmlPullParser xpp) throws Exception {
    Contract res = new Contract();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issued")) {
        res.setIssuedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("applies")) {
        res.setApplies(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.getSubject().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authority")) {
        res.getAuthority().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("domain")) {
        res.getDomain().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subType")) {
        res.getSubType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.getAction().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actionReason")) {
        res.getActionReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.getActor().add(parseContractActorComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("valuedItem")) {
        res.getValuedItem().add(parseContractValuedItemComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("signer")) {
        res.getSigner().add(parseContractSignatoryComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("term")) {
        res.getTerm().add(parseContractTermComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "binding")) {
        res.setBinding(parseType("binding", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("friendly")) {
        res.getFriendly().add(parseContractFriendlyLanguageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("legal")) {
        res.getLegal().add(parseContractLegalLanguageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rule")) {
        res.getRule().add(parseContractComputableLanguageComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.ActorComponent parseContractActorComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.ActorComponent res = new Contract.ActorComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entity")) {
        res.setEntity(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.ValuedItemComponent parseContractValuedItemComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.ValuedItemComponent res = new Contract.ValuedItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "entity")) {
        res.setEntity(parseType("entity", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("effectiveTime")) {
        res.setEffectiveTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unitPrice")) {
        res.setUnitPrice(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactorElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("points")) {
        res.setPointsElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("net")) {
        res.setNet(parseMoney(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.SignatoryComponent parseContractSignatoryComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.SignatoryComponent res = new Contract.SignatoryComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("party")) {
        res.setParty(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("signature")) {
        res.setSignatureElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.TermComponent parseContractTermComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.TermComponent res = new Contract.TermComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issued")) {
        res.setIssuedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("applies")) {
        res.setApplies(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subType")) {
        res.setSubType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.getAction().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actionReason")) {
        res.getActionReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.getActor().add(parseContractTermActorComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("valuedItem")) {
        res.getValuedItem().add(parseContractTermValuedItemComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.getGroup().add(parseContractTermComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.TermActorComponent parseContractTermActorComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.TermActorComponent res = new Contract.TermActorComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entity")) {
        res.setEntity(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.TermValuedItemComponent parseContractTermValuedItemComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.TermValuedItemComponent res = new Contract.TermValuedItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "entity")) {
        res.setEntity(parseType("entity", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("effectiveTime")) {
        res.setEffectiveTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unitPrice")) {
        res.setUnitPrice(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("factor")) {
        res.setFactorElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("points")) {
        res.setPointsElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("net")) {
        res.setNet(parseMoney(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.FriendlyLanguageComponent parseContractFriendlyLanguageComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.FriendlyLanguageComponent res = new Contract.FriendlyLanguageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "content")) {
        res.setContent(parseType("content", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.LegalLanguageComponent parseContractLegalLanguageComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.LegalLanguageComponent res = new Contract.LegalLanguageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "content")) {
        res.setContent(parseType("content", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contract.ComputableLanguageComponent parseContractComputableLanguageComponent(XmlPullParser xpp, Contract owner) throws Exception {
    Contract.ComputableLanguageComponent res = new Contract.ComputableLanguageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "content")) {
        res.setContent(parseType("content", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contraindication parseContraindication(XmlPullParser xpp) throws Exception {
    Contraindication res = new Contraindication();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverityElement(parseEnumeration(xpp, Contraindication.ContraindicationSeverity.NULL, new Contraindication.ContraindicationSeverityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("implicated")) {
        res.getImplicated().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetailElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReferenceElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mitigation")) {
        res.getMitigation().add(parseContraindicationContraindicationMitigationComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Contraindication.ContraindicationMitigationComponent parseContraindicationContraindicationMitigationComponent(XmlPullParser xpp, Contraindication owner) throws Exception {
    Contraindication.ContraindicationMitigationComponent res = new Contraindication.ContraindicationMitigationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.setAction(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Coverage parseCoverage(XmlPullParser xpp) throws Exception {
    Coverage res = new Coverage();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issuer")) {
        res.setIssuer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bin")) {
        res.setBin(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subscriberId")) {
        res.setSubscriberId(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.setGroupElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("plan")) {
        res.setPlanElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subPlan")) {
        res.setSubPlanElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dependent")) {
        res.setDependentElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequence")) {
        res.setSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subscriber")) {
        res.setSubscriber(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("network")) {
        res.setNetwork(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contract")) {
        res.getContract().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DataElement parseDataElement(XmlPullParser xpp) throws Exception {
    DataElement res = new DataElement();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("useContext")) {
        res.getUseContext().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyrightElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseDataElementDataElementContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specificity")) {
        res.setSpecificityElement(parseEnumeration(xpp, DataElement.DataElementSpecificity.NULL, new DataElement.DataElementSpecificityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mapping")) {
        res.getMapping().add(parseDataElementDataElementMappingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseElementDefinition(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DataElement.DataElementContactComponent parseDataElementDataElementContactComponent(XmlPullParser xpp, DataElement owner) throws Exception {
    DataElement.DataElementContactComponent res = new DataElement.DataElementContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DataElement.DataElementMappingComponent parseDataElementDataElementMappingComponent(XmlPullParser xpp, DataElement owner) throws Exception {
    DataElement.DataElementMappingComponent res = new DataElement.DataElementMappingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identity")) {
        res.setIdentityElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri")) {
        res.setUriElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setCommentsElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Device parseDevice(XmlPullParser xpp) throws Exception {
    Device res = new Device();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.getNote().add(parseAnnotation(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Device.DeviceStatus.NULL, new Device.DeviceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturerElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("model")) {
        res.setModelElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufactureDate")) {
        res.setManufactureDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expiry")) {
        res.setExpiryElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("udi")) {
        res.setUdiElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lotNumber")) {
        res.setLotNumberElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("owner")) {
        res.setOwner(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DeviceComponent parseDeviceComponent(XmlPullParser xpp) throws Exception {
    DeviceComponent res = new DeviceComponent();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lastSystemChange")) {
        res.setLastSystemChangeElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parent")) {
        res.setParent(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operationalStatus")) {
        res.getOperationalStatus().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameterGroup")) {
        res.setParameterGroup(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("measurementPrinciple")) {
        res.setMeasurementPrincipleElement(parseEnumeration(xpp, DeviceComponent.MeasmntPrinciple.NULL, new DeviceComponent.MeasmntPrincipleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("productionSpecification")) {
        res.getProductionSpecification().add(parseDeviceComponentDeviceComponentProductionSpecificationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("languageCode")) {
        res.setLanguageCode(parseCodeableConcept(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DeviceComponent.DeviceComponentProductionSpecificationComponent parseDeviceComponentDeviceComponentProductionSpecificationComponent(XmlPullParser xpp, DeviceComponent owner) throws Exception {
    DeviceComponent.DeviceComponentProductionSpecificationComponent res = new DeviceComponent.DeviceComponentProductionSpecificationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specType")) {
        res.setSpecType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("componentId")) {
        res.setComponentId(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("productionSpec")) {
        res.setProductionSpecElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DeviceMetric parseDeviceMetric(XmlPullParser xpp) throws Exception {
    DeviceMetric res = new DeviceMetric();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("unit")) {
        res.setUnit(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parent")) {
        res.setParent(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operationalStatus")) {
        res.setOperationalStatusElement(parseEnumeration(xpp, DeviceMetric.DeviceMetricOperationalStatus.NULL, new DeviceMetric.DeviceMetricOperationalStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("color")) {
        res.setColorElement(parseEnumeration(xpp, DeviceMetric.DeviceMetricColor.NULL, new DeviceMetric.DeviceMetricColorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategoryElement(parseEnumeration(xpp, DeviceMetric.DeviceMetricCategory.NULL, new DeviceMetric.DeviceMetricCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("measurementPeriod")) {
        res.setMeasurementPeriod(parseTiming(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("calibration")) {
        res.getCalibration().add(parseDeviceMetricDeviceMetricCalibrationComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DeviceMetric.DeviceMetricCalibrationComponent parseDeviceMetricDeviceMetricCalibrationComponent(XmlPullParser xpp, DeviceMetric owner) throws Exception {
    DeviceMetric.DeviceMetricCalibrationComponent res = new DeviceMetric.DeviceMetricCalibrationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, DeviceMetric.DeviceMetricCalibrationType.NULL, new DeviceMetric.DeviceMetricCalibrationTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("state")) {
        res.setStateElement(parseEnumeration(xpp, DeviceMetric.DeviceMetricCalibrationState.NULL, new DeviceMetric.DeviceMetricCalibrationStateEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("time")) {
        res.setTimeElement(parseInstant(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DeviceUseRequest parseDeviceUseRequest(XmlPullParser xpp) throws Exception {
    DeviceUseRequest res = new DeviceUseRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "bodySite")) {
        res.setBodySite(parseType("bodySite", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, DeviceUseRequest.DeviceUseRequestStatus.NULL, new DeviceUseRequest.DeviceUseRequestStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.setDevice(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.getIndication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.getNotes().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prnReason")) {
        res.getPrnReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedOn")) {
        res.setOrderedOnElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recordedOn")) {
        res.setRecordedOnElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriorityElement(parseEnumeration(xpp, DeviceUseRequest.DeviceUseRequestPriority.NULL, new DeviceUseRequest.DeviceUseRequestPriorityEnumFactory()));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DeviceUseStatement parseDeviceUseStatement(XmlPullParser xpp) throws Exception {
    DeviceUseStatement res = new DeviceUseStatement();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "bodySite")) {
        res.setBodySite(parseType("bodySite", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenUsed")) {
        res.setWhenUsed(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.setDevice(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.getIndication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.getNotes().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recordedOn")) {
        res.setRecordedOnElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DiagnosticOrder parseDiagnosticOrder(XmlPullParser xpp) throws Exception {
    DiagnosticOrder res = new DiagnosticOrder();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderer")) {
        res.setOrderer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("clinicalNotes")) {
        res.setClinicalNotesElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingInformation")) {
        res.getSupportingInformation().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.getSpecimen().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderStatus.NULL, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriorityElement(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderPriority.NULL, new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseDiagnosticOrderDiagnosticOrderItemComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DiagnosticOrder.DiagnosticOrderEventComponent parseDiagnosticOrderDiagnosticOrderEventComponent(XmlPullParser xpp, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderEventComponent res = new DiagnosticOrder.DiagnosticOrderEventComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderStatus.NULL, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DiagnosticOrder.DiagnosticOrderItemComponent parseDiagnosticOrderDiagnosticOrderItemComponent(XmlPullParser xpp, DiagnosticOrder owner) throws Exception {
    DiagnosticOrder.DiagnosticOrderItemComponent res = new DiagnosticOrder.DiagnosticOrderItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.getSpecimen().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "bodySite")) {
        res.setBodySite(parseType("bodySite", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, DiagnosticOrder.DiagnosticOrderStatus.NULL, new DiagnosticOrder.DiagnosticOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseDiagnosticOrderDiagnosticOrderEventComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DiagnosticReport parseDiagnosticReport(XmlPullParser xpp) throws Exception {
    DiagnosticReport res = new DiagnosticReport();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, DiagnosticReport.DiagnosticReportStatus.NULL, new DiagnosticReport.DiagnosticReportStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issued")) {
        res.setIssuedElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestDetail")) {
        res.getRequestDetail().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceCategory")) {
        res.setServiceCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "effective")) {
        res.setEffective(parseType("effective", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.getSpecimen().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("result")) {
        res.getResult().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("imagingStudy")) {
        res.getImagingStudy().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("image")) {
        res.getImage().add(parseDiagnosticReportDiagnosticReportImageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("conclusion")) {
        res.setConclusionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("codedDiagnosis")) {
        res.getCodedDiagnosis().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("presentedForm")) {
        res.getPresentedForm().add(parseAttachment(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DiagnosticReport.DiagnosticReportImageComponent parseDiagnosticReportDiagnosticReportImageComponent(XmlPullParser xpp, DiagnosticReport owner) throws Exception {
    DiagnosticReport.DiagnosticReportImageComponent res = new DiagnosticReport.DiagnosticReportImageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.setLink(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentManifest parseDocumentManifest(XmlPullParser xpp) throws Exception {
    DocumentManifest res = new DocumentManifest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("masterIdentifier")) {
        res.setMasterIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recipient")) {
        res.getRecipient().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSourceElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.DocumentReferenceStatus.NULL, new Enumerations.DocumentReferenceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.getContent().add(parseDocumentManifestDocumentManifestContentComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("related")) {
        res.getRelated().add(parseDocumentManifestDocumentManifestRelatedComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentManifest.DocumentManifestContentComponent parseDocumentManifestDocumentManifestContentComponent(XmlPullParser xpp, DocumentManifest owner) throws Exception {
    DocumentManifest.DocumentManifestContentComponent res = new DocumentManifest.DocumentManifestContentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "p")) {
        res.setP(parseType("p", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentManifest.DocumentManifestRelatedComponent parseDocumentManifestDocumentManifestRelatedComponent(XmlPullParser xpp, DocumentManifest owner) throws Exception {
    DocumentManifest.DocumentManifestRelatedComponent res = new DocumentManifest.DocumentManifestRelatedComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ref")) {
        res.setRef(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentReference parseDocumentReference(XmlPullParser xpp) throws Exception {
    DocumentReference res = new DocumentReference();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("masterIdentifier")) {
        res.setMasterIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("class")) {
        res.setClass_(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("format")) {
        res.getFormat().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.getAuthor().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("custodian")) {
        res.setCustodian(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authenticator")) {
        res.setAuthenticator(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indexed")) {
        res.setIndexedElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.DocumentReferenceStatus.NULL, new Enumerations.DocumentReferenceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("docStatus")) {
        res.setDocStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relatesTo")) {
        res.getRelatesTo().add(parseDocumentReferenceDocumentReferenceRelatesToComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("confidentiality")) {
        res.getConfidentiality().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.getContent().add(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("context")) {
        res.setContext(parseDocumentReferenceDocumentReferenceContextComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentReference.DocumentReferenceRelatesToComponent parseDocumentReferenceDocumentReferenceRelatesToComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceRelatesToComponent res = new DocumentReference.DocumentReferenceRelatesToComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseEnumeration(xpp, DocumentReference.DocumentRelationshipType.NULL, new DocumentReference.DocumentRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentReference.DocumentReferenceContextComponent parseDocumentReferenceDocumentReferenceContextComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
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
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("practiceSetting")) {
        res.setPracticeSetting(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sourcePatientInfo")) {
        res.setSourcePatientInfo(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("related")) {
        res.getRelated().add(parseDocumentReferenceDocumentReferenceContextRelatedComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected DocumentReference.DocumentReferenceContextRelatedComponent parseDocumentReferenceDocumentReferenceContextRelatedComponent(XmlPullParser xpp, DocumentReference owner) throws Exception {
    DocumentReference.DocumentReferenceContextRelatedComponent res = new DocumentReference.DocumentReferenceContextRelatedComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ref")) {
        res.setRef(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EligibilityRequest parseEligibilityRequest(XmlPullParser xpp) throws Exception {
    EligibilityRequest res = new EligibilityRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EligibilityResponse parseEligibilityResponse(XmlPullParser xpp) throws Exception {
    EligibilityResponse res = new EligibilityResponse();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcomeElement(parseEnumeration(xpp, Enumerations.RemittanceOutcome.NULL, new Enumerations.RemittanceOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("disposition")) {
        res.setDispositionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestProvider")) {
        res.setRequestProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestOrganization")) {
        res.setRequestOrganization(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Encounter parseEncounter(XmlPullParser xpp) throws Exception {
    Encounter res = new Encounter();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Encounter.EncounterState.NULL, new Encounter.EncounterStateEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("statusHistory")) {
        res.getStatusHistory().add(parseEncounterEncounterStatusHistoryComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("class")) {
        res.setClass_Element(parseEnumeration(xpp, Encounter.EncounterClass.NULL, new Encounter.EncounterClassEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("episodeOfCare")) {
        res.setEpisodeOfCare(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("incomingReferralRequest")) {
        res.getIncomingReferralRequest().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseEncounterEncounterParticipantComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fulfills")) {
        res.setFulfills(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("length")) {
        res.setLength(parseDuration(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.getReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.getIndication().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("hospitalization")) {
        res.setHospitalization(parseEncounterEncounterHospitalizationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseEncounterEncounterLocationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceProvider")) {
        res.setServiceProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("partOf")) {
        res.setPartOf(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Encounter.EncounterStatusHistoryComponent parseEncounterEncounterStatusHistoryComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterStatusHistoryComponent res = new Encounter.EncounterStatusHistoryComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Encounter.EncounterState.NULL, new Encounter.EncounterStateEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Encounter.EncounterParticipantComponent parseEncounterEncounterParticipantComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterParticipantComponent res = new Encounter.EncounterParticipantComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("individual")) {
        res.setIndividual(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Encounter.EncounterHospitalizationComponent parseEncounterEncounterHospitalizationComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterHospitalizationComponent res = new Encounter.EncounterHospitalizationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preAdmissionIdentifier")) {
        res.setPreAdmissionIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("origin")) {
        res.setOrigin(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("admitSource")) {
        res.setAdmitSource(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dietPreference")) {
        res.setDietPreference(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialCourtesy")) {
        res.getSpecialCourtesy().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialArrangement")) {
        res.getSpecialArrangement().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dischargeDisposition")) {
        res.setDischargeDisposition(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dischargeDiagnosis")) {
        res.setDischargeDiagnosis(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reAdmission")) {
        res.setReAdmissionElement(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Encounter.EncounterLocationComponent parseEncounterEncounterLocationComponent(XmlPullParser xpp, Encounter owner) throws Exception {
    Encounter.EncounterLocationComponent res = new Encounter.EncounterLocationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Encounter.EncounterLocationStatus.NULL, new Encounter.EncounterLocationStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EnrollmentRequest parseEnrollmentRequest(XmlPullParser xpp) throws Exception {
    EnrollmentRequest res = new EnrollmentRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coverage")) {
        res.setCoverage(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCoding(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EnrollmentResponse parseEnrollmentResponse(XmlPullParser xpp) throws Exception {
    EnrollmentResponse res = new EnrollmentResponse();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcomeElement(parseEnumeration(xpp, Enumerations.RemittanceOutcome.NULL, new Enumerations.RemittanceOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("disposition")) {
        res.setDispositionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestProvider")) {
        res.setRequestProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestOrganization")) {
        res.setRequestOrganization(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EpisodeOfCare parseEpisodeOfCare(XmlPullParser xpp) throws Exception {
    EpisodeOfCare res = new EpisodeOfCare();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, EpisodeOfCare.EpisodeOfCareStatus.NULL, new EpisodeOfCare.EpisodeOfCareStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("statusHistory")) {
        res.getStatusHistory().add(parseEpisodeOfCareEpisodeOfCareStatusHistoryComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.getCondition().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referralRequest")) {
        res.getReferralRequest().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("careManager")) {
        res.setCareManager(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("careTeam")) {
        res.getCareTeam().add(parseEpisodeOfCareEpisodeOfCareCareTeamComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EpisodeOfCare.EpisodeOfCareStatusHistoryComponent parseEpisodeOfCareEpisodeOfCareStatusHistoryComponent(XmlPullParser xpp, EpisodeOfCare owner) throws Exception {
    EpisodeOfCare.EpisodeOfCareStatusHistoryComponent res = new EpisodeOfCare.EpisodeOfCareStatusHistoryComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, EpisodeOfCare.EpisodeOfCareStatus.NULL, new EpisodeOfCare.EpisodeOfCareStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected EpisodeOfCare.EpisodeOfCareCareTeamComponent parseEpisodeOfCareEpisodeOfCareCareTeamComponent(XmlPullParser xpp, EpisodeOfCare owner) throws Exception {
    EpisodeOfCare.EpisodeOfCareCareTeamComponent res = new EpisodeOfCare.EpisodeOfCareCareTeamComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("member")) {
        res.setMember(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ExplanationOfBenefit parseExplanationOfBenefit(XmlPullParser xpp) throws Exception {
    ExplanationOfBenefit res = new ExplanationOfBenefit();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcomeElement(parseEnumeration(xpp, Enumerations.RemittanceOutcome.NULL, new Enumerations.RemittanceOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("disposition")) {
        res.setDispositionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestProvider")) {
        res.setRequestProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestOrganization")) {
        res.setRequestOrganization(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected FamilyMemberHistory parseFamilyMemberHistory(XmlPullParser xpp) throws Exception {
    FamilyMemberHistory res = new FamilyMemberHistory();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGenderElement(parseEnumeration(xpp, Enumerations.AdministrativeGender.NULL, new Enumerations.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "born")) {
        res.setBorn(parseType("born", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "age")) {
        res.setAge(parseType("age", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "deceased")) {
        res.setDeceased(parseType("deceased", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.getCondition().add(parseFamilyMemberHistoryFamilyMemberHistoryConditionComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected FamilyMemberHistory.FamilyMemberHistoryConditionComponent parseFamilyMemberHistoryFamilyMemberHistoryConditionComponent(XmlPullParser xpp, FamilyMemberHistory owner) throws Exception {
    FamilyMemberHistory.FamilyMemberHistoryConditionComponent res = new FamilyMemberHistory.FamilyMemberHistoryConditionComponent();
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
        res.setNoteElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Flag parseFlag(XmlPullParser xpp) throws Exception {
    Flag res = new Flag();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Flag.FlagStatus.NULL, new Flag.FlagStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Goal parseGoal(XmlPullParser xpp) throws Exception {
    Goal res = new Goal();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("targetDate")) {
        res.setTargetDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Goal.GoalStatus.NULL, new Goal.GoalStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("statusDate")) {
        res.setStatusDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concern")) {
        res.getConcern().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.getOutcome().add(parseGoalGoalOutcomeComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Goal.GoalOutcomeComponent parseGoalGoalOutcomeComponent(XmlPullParser xpp, Goal owner) throws Exception {
    Goal.GoalOutcomeComponent res = new Goal.GoalOutcomeComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "result")) {
        res.setResult(parseType("result", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Group parseGroup(XmlPullParser xpp) throws Exception {
    Group res = new Group();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Group.GroupType.NULL, new Group.GroupTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actual")) {
        res.setActualElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantityElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("characteristic")) {
        res.getCharacteristic().add(parseGroupGroupCharacteristicComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("member")) {
        res.getMember().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Group.GroupCharacteristicComponent parseGroupGroupCharacteristicComponent(XmlPullParser xpp, Group owner) throws Exception {
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
        res.setExcludeElement(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected HealthcareService parseHealthcareService(XmlPullParser xpp) throws Exception {
    HealthcareService res = new HealthcareService();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("providedBy")) {
        res.setProvidedBy(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceCategory")) {
        res.setServiceCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceType")) {
        res.getServiceType().add(parseHealthcareServiceServiceTypeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceName")) {
        res.setServiceNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extraDetails")) {
        res.setExtraDetailsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.setPhoto(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coverageArea")) {
        res.getCoverageArea().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceProvisionCode")) {
        res.getServiceProvisionCode().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("eligibility")) {
        res.setEligibility(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("eligibilityNote")) {
        res.setEligibilityNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("programName")) {
        res.getProgramName().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("characteristic")) {
        res.getCharacteristic().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referralMethod")) {
        res.getReferralMethod().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publicKey")) {
        res.setPublicKeyElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("appointmentRequired")) {
        res.setAppointmentRequiredElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availableTime")) {
        res.getAvailableTime().add(parseHealthcareServiceHealthcareServiceAvailableTimeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notAvailable")) {
        res.getNotAvailable().add(parseHealthcareServiceHealthcareServiceNotAvailableComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availabilityExceptions")) {
        res.setAvailabilityExceptionsElement(parseString(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected HealthcareService.ServiceTypeComponent parseHealthcareServiceServiceTypeComponent(XmlPullParser xpp, HealthcareService owner) throws Exception {
    HealthcareService.ServiceTypeComponent res = new HealthcareService.ServiceTypeComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialty")) {
        res.getSpecialty().add(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected HealthcareService.HealthcareServiceAvailableTimeComponent parseHealthcareServiceHealthcareServiceAvailableTimeComponent(XmlPullParser xpp, HealthcareService owner) throws Exception {
    HealthcareService.HealthcareServiceAvailableTimeComponent res = new HealthcareService.HealthcareServiceAvailableTimeComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("daysOfWeek")) {
        res.getDaysOfWeek().add(parseEnumeration(xpp, HealthcareService.DaysOfWeek.NULL, new HealthcareService.DaysOfWeekEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("allDay")) {
        res.setAllDayElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availableStartTime")) {
        res.setAvailableStartTimeElement(parseTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availableEndTime")) {
        res.setAvailableEndTimeElement(parseTime(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected HealthcareService.HealthcareServiceNotAvailableComponent parseHealthcareServiceHealthcareServiceNotAvailableComponent(XmlPullParser xpp, HealthcareService owner) throws Exception {
    HealthcareService.HealthcareServiceNotAvailableComponent res = new HealthcareService.HealthcareServiceNotAvailableComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("during")) {
        res.setDuring(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingObjectSelection parseImagingObjectSelection(XmlPullParser xpp) throws Exception {
    ImagingObjectSelection res = new ImagingObjectSelection();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authoringTime")) {
        res.setAuthoringTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("study")) {
        res.getStudy().add(parseImagingObjectSelectionStudyComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingObjectSelection.StudyComponent parseImagingObjectSelectionStudyComponent(XmlPullParser xpp, ImagingObjectSelection owner) throws Exception {
    ImagingObjectSelection.StudyComponent res = new ImagingObjectSelection.StudyComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.getSeries().add(parseImagingObjectSelectionSeriesComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingObjectSelection.SeriesComponent parseImagingObjectSelectionSeriesComponent(XmlPullParser xpp, ImagingObjectSelection owner) throws Exception {
    ImagingObjectSelection.SeriesComponent res = new ImagingObjectSelection.SeriesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.getInstance().add(parseImagingObjectSelectionInstanceComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingObjectSelection.InstanceComponent parseImagingObjectSelectionInstanceComponent(XmlPullParser xpp, ImagingObjectSelection owner) throws Exception {
    ImagingObjectSelection.InstanceComponent res = new ImagingObjectSelection.InstanceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sopClass")) {
        res.setSopClassElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frames")) {
        res.getFrames().add(parseImagingObjectSelectionFramesComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingObjectSelection.FramesComponent parseImagingObjectSelectionFramesComponent(XmlPullParser xpp, ImagingObjectSelection owner) throws Exception {
    ImagingObjectSelection.FramesComponent res = new ImagingObjectSelection.FramesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frameNumbers")) {
        res.getFrameNumbers().add(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingStudy parseImagingStudy(XmlPullParser xpp) throws Exception {
    ImagingStudy res = new ImagingStudy();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("started")) {
        res.setStartedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accession")) {
        res.setAccession(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("order")) {
        res.getOrder().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modalityList")) {
        res.getModalityList().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referrer")) {
        res.setReferrer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availability")) {
        res.setAvailabilityElement(parseEnumeration(xpp, ImagingStudy.InstanceAvailability.NULL, new ImagingStudy.InstanceAvailabilityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfSeries")) {
        res.setNumberOfSeriesElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfInstances")) {
        res.setNumberOfInstancesElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("clinicalInformation")) {
        res.setClinicalInformationElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("procedure")) {
        res.getProcedure().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interpreter")) {
        res.setInterpreter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.getSeries().add(parseImagingStudyImagingStudySeriesComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingStudy.ImagingStudySeriesComponent parseImagingStudyImagingStudySeriesComponent(XmlPullParser xpp, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesComponent res = new ImagingStudy.ImagingStudySeriesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("number")) {
        res.setNumberElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modality")) {
        res.setModalityElement(parseEnumeration(xpp, ImagingStudy.Modality.NULL, new ImagingStudy.ModalityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfInstances")) {
        res.setNumberOfInstancesElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("availability")) {
        res.setAvailabilityElement(parseEnumeration(xpp, ImagingStudy.InstanceAvailability.NULL, new ImagingStudy.InstanceAvailabilityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.setBodySite(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("laterality")) {
        res.setLaterality(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.getInstance().add(parseImagingStudyImagingStudySeriesInstanceComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImagingStudy.ImagingStudySeriesInstanceComponent parseImagingStudyImagingStudySeriesInstanceComponent(XmlPullParser xpp, ImagingStudy owner) throws Exception {
    ImagingStudy.ImagingStudySeriesInstanceComponent res = new ImagingStudy.ImagingStudySeriesInstanceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("number")) {
        res.setNumberElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uid")) {
        res.setUidElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sopclass")) {
        res.setSopclassElement(parseOid(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.getContent().add(parseAttachment(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Immunization parseImmunization(XmlPullParser xpp) throws Exception {
    Immunization res = new Immunization();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("vaccineType")) {
        res.setVaccineType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("wasNotGiven")) {
        res.setWasNotGivenElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reported")) {
        res.setReportedElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requester")) {
        res.setRequester(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lotNumber")) {
        res.setLotNumberElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expirationDate")) {
        res.setExpirationDateElement(parseDate(xpp));
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
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Immunization.ImmunizationExplanationComponent parseImmunizationImmunizationExplanationComponent(XmlPullParser xpp, Immunization owner) throws Exception {
    Immunization.ImmunizationExplanationComponent res = new Immunization.ImmunizationExplanationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.getReason().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reasonNotGiven")) {
        res.getReasonNotGiven().add(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Immunization.ImmunizationReactionComponent parseImmunizationImmunizationReactionComponent(XmlPullParser xpp, Immunization owner) throws Exception {
    Immunization.ImmunizationReactionComponent res = new Immunization.ImmunizationReactionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.setDetail(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reported")) {
        res.setReportedElement(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Immunization.ImmunizationVaccinationProtocolComponent parseImmunizationImmunizationVaccinationProtocolComponent(XmlPullParser xpp, Immunization owner) throws Exception {
    Immunization.ImmunizationVaccinationProtocolComponent res = new Immunization.ImmunizationVaccinationProtocolComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseSequence")) {
        res.setDoseSequenceElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authority")) {
        res.setAuthority(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.setSeriesElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("seriesDoses")) {
        res.setSeriesDosesElement(parsePositiveInt(xpp));
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

  protected ImmunizationRecommendation parseImmunizationRecommendation(XmlPullParser xpp) throws Exception {
    ImmunizationRecommendation res = new ImmunizationRecommendation();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recommendation")) {
        res.getRecommendation().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationComponent(XmlPullParser xpp, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("vaccineType")) {
        res.setVaccineType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseNumber")) {
        res.setDoseNumberElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("forecastStatus")) {
        res.setForecastStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateCriterion")) {
        res.getDateCriterion().add(parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("protocol")) {
        res.setProtocol(parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingImmunization")) {
        res.getSupportingImmunization().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingPatientInformation")) {
        res.getSupportingPatientInformation().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(XmlPullParser xpp, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseDateTime(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent parseImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(XmlPullParser xpp, ImmunizationRecommendation owner) throws Exception {
    ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent res = new ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("doseSequence")) {
        res.setDoseSequenceElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authority")) {
        res.setAuthority(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("series")) {
        res.setSeriesElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide parseImplementationGuide(XmlPullParser xpp) throws Exception {
    ImplementationGuide res = new ImplementationGuide();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("useContext")) {
        res.getUseContext().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseImplementationGuideImplementationGuideContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyrightElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fhirVersion")) {
        res.setFhirVersionElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("page")) {
        res.getPage().add(parseImplementationGuideImplementationGuidePageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("package")) {
        res.getPackage().add(parseImplementationGuideImplementationGuidePackageComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("default")) {
        res.getDefault().add(parseImplementationGuideImplementationGuideDefaultComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide.ImplementationGuideContactComponent parseImplementationGuideImplementationGuideContactComponent(XmlPullParser xpp, ImplementationGuide owner) throws Exception {
    ImplementationGuide.ImplementationGuideContactComponent res = new ImplementationGuide.ImplementationGuideContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide.ImplementationGuidePageComponent parseImplementationGuideImplementationGuidePageComponent(XmlPullParser xpp, ImplementationGuide owner) throws Exception {
    ImplementationGuide.ImplementationGuidePageComponent res = new ImplementationGuide.ImplementationGuidePageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSourceElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("purpose")) {
        res.setPurposeElement(parseEnumeration(xpp, ImplementationGuide.PagePurpose.NULL, new ImplementationGuide.PagePurposeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide.ImplementationGuidePackageComponent parseImplementationGuideImplementationGuidePackageComponent(XmlPullParser xpp, ImplementationGuide owner) throws Exception {
    ImplementationGuide.ImplementationGuidePackageComponent res = new ImplementationGuide.ImplementationGuidePackageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseImplementationGuideImplementationGuidePackageItemComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("page")) {
        res.getPage().add(parseImplementationGuideImplementationGuidePageComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide.ImplementationGuidePackageItemComponent parseImplementationGuideImplementationGuidePackageItemComponent(XmlPullParser xpp, ImplementationGuide owner) throws Exception {
    ImplementationGuide.ImplementationGuidePackageItemComponent res = new ImplementationGuide.ImplementationGuidePackageItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContent(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("example")) {
        res.getExample().add(parseImplementationGuideImplementationGuidePackageItemExampleComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide.ImplementationGuidePackageItemExampleComponent parseImplementationGuideImplementationGuidePackageItemExampleComponent(XmlPullParser xpp, ImplementationGuide owner) throws Exception {
    ImplementationGuide.ImplementationGuidePackageItemExampleComponent res = new ImplementationGuide.ImplementationGuidePackageItemExampleComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ImplementationGuide.ImplementationGuideDefaultComponent parseImplementationGuideImplementationGuideDefaultComponent(XmlPullParser xpp, ImplementationGuide owner) throws Exception {
    ImplementationGuide.ImplementationGuideDefaultComponent res = new ImplementationGuide.ImplementationGuideDefaultComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected List_ parseList_(XmlPullParser xpp) throws Exception {
    List_ res = new List_();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, List_.ListStatus.NULL, new List_.ListStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedBy")) {
        res.setOrderedBy(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setModeElement(parseEnumeration(xpp, List_.ListMode.NULL, new List_.ListModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entry")) {
        res.getEntry().add(parseList_ListEntryComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("emptyReason")) {
        res.setEmptyReason(parseCodeableConcept(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected List_.ListEntryComponent parseList_ListEntryComponent(XmlPullParser xpp, List_ owner) throws Exception {
    List_.ListEntryComponent res = new List_.ListEntryComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("flag")) {
        res.getFlag().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("deleted")) {
        res.setDeletedElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Location parseLocation(XmlPullParser xpp) throws Exception {
    Location res = new Location();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setModeElement(parseEnumeration(xpp, Location.LocationMode.NULL, new Location.LocationModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("physicalType")) {
        res.setPhysicalType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("position")) {
        res.setPosition(parseLocationLocationPositionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("partOf")) {
        res.setPartOf(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Location.LocationStatus.NULL, new Location.LocationStatusEnumFactory()));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Location.LocationPositionComponent parseLocationLocationPositionComponent(XmlPullParser xpp, Location owner) throws Exception {
    Location.LocationPositionComponent res = new Location.LocationPositionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("longitude")) {
        res.setLongitudeElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("latitude")) {
        res.setLatitudeElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("altitude")) {
        res.setAltitudeElement(parseDecimal(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Media parseMedia(XmlPullParser xpp) throws Exception {
    Media res = new Media();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Media.DigitalMediaType.NULL, new Media.DigitalMediaTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subtype")) {
        res.setSubtype(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operator")) {
        res.setOperator(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("view")) {
        res.setView(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("deviceName")) {
        res.setDeviceNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("height")) {
        res.setHeightElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("width")) {
        res.setWidthElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frames")) {
        res.setFramesElement(parsePositiveInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("duration")) {
        res.setDurationElement(parseUnsignedInt(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContent(parseAttachment(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Medication parseMedication(XmlPullParser xpp) throws Exception {
    Medication res = new Medication();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("isBrand")) {
        res.setIsBrandElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKindElement(parseEnumeration(xpp, Medication.MedicationKind.NULL, new Medication.MedicationKindEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.setProduct(parseMedicationMedicationProductComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("package")) {
        res.setPackage(parseMedicationMedicationPackageComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Medication.MedicationProductComponent parseMedicationMedicationProductComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationProductComponent res = new Medication.MedicationProductComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("form")) {
        res.setForm(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ingredient")) {
        res.getIngredient().add(parseMedicationMedicationProductIngredientComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("batch")) {
        res.getBatch().add(parseMedicationMedicationProductBatchComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Medication.MedicationProductIngredientComponent parseMedicationMedicationProductIngredientComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationProductIngredientComponent res = new Medication.MedicationProductIngredientComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseRatio(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Medication.MedicationProductBatchComponent parseMedicationMedicationProductBatchComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationProductBatchComponent res = new Medication.MedicationProductBatchComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lotNumber")) {
        res.setLotNumberElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expirationDate")) {
        res.setExpirationDateElement(parseDateTime(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Medication.MedicationPackageComponent parseMedicationMedicationPackageComponent(XmlPullParser xpp, Medication owner) throws Exception {
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

  protected Medication.MedicationPackageContentComponent parseMedicationMedicationPackageContentComponent(XmlPullParser xpp, Medication owner) throws Exception {
    Medication.MedicationPackageContentComponent res = new Medication.MedicationPackageContentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.setItem(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseQuantity(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationAdministration parseMedicationAdministration(XmlPullParser xpp) throws Exception {
    MedicationAdministration res = new MedicationAdministration();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, MedicationAdministration.MedicationAdministrationStatus.NULL, new MedicationAdministration.MedicationAdministrationStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("practitioner")) {
        res.setPractitioner(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prescription")) {
        res.setPrescription(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("wasNotGiven")) {
        res.setWasNotGivenElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reasonNotGiven")) {
        res.getReasonNotGiven().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reasonGiven")) {
        res.getReasonGiven().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "effectiveTime")) {
        res.setEffectiveTime(parseType("effectiveTime", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "medication")) {
        res.setMedication(parseType("medication", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.getDevice().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosage")) {
        res.setDosage(parseMedicationAdministrationMedicationAdministrationDosageComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationAdministration.MedicationAdministrationDosageComponent parseMedicationAdministrationMedicationAdministrationDosageComponent(XmlPullParser xpp, MedicationAdministration owner) throws Exception {
    MedicationAdministration.MedicationAdministrationDosageComponent res = new MedicationAdministration.MedicationAdministrationDosageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
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
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationDispense parseMedicationDispense(XmlPullParser xpp) throws Exception {
    MedicationDispense res = new MedicationDispense();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, MedicationDispense.MedicationDispenseStatus.NULL, new MedicationDispense.MedicationDispenseStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispenser")) {
        res.setDispenser(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authorizingPrescription")) {
        res.getAuthorizingPrescription().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("daysSupply")) {
        res.setDaysSupply(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "medication")) {
        res.setMedication(parseType("medication", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenPrepared")) {
        res.setWhenPreparedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenHandedOver")) {
        res.setWhenHandedOverElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.getReceiver().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosageInstruction")) {
        res.getDosageInstruction().add(parseMedicationDispenseMedicationDispenseDosageInstructionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substitution")) {
        res.setSubstitution(parseMedicationDispenseMedicationDispenseSubstitutionComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationDispense.MedicationDispenseDosageInstructionComponent parseMedicationDispenseMedicationDispenseDosageInstructionComponent(XmlPullParser xpp, MedicationDispense owner) throws Exception {
    MedicationDispense.MedicationDispenseDosageInstructionComponent res = new MedicationDispense.MedicationDispenseDosageInstructionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additionalInstructions")) {
        res.setAdditionalInstructions(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "schedule")) {
        res.setSchedule(parseType("schedule", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "dose")) {
        res.setDose(parseType("dose", xpp));
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

  protected MedicationDispense.MedicationDispenseSubstitutionComponent parseMedicationDispenseMedicationDispenseSubstitutionComponent(XmlPullParser xpp, MedicationDispense owner) throws Exception {
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
        res.getResponsibleParty().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationPrescription parseMedicationPrescription(XmlPullParser xpp) throws Exception {
    MedicationPrescription res = new MedicationPrescription();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateWritten")) {
        res.setDateWrittenElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, MedicationPrescription.MedicationPrescriptionStatus.NULL, new MedicationPrescription.MedicationPrescriptionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prescriber")) {
        res.setPrescriber(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "medication")) {
        res.setMedication(parseType("medication", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosageInstruction")) {
        res.getDosageInstruction().add(parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispense")) {
        res.setDispense(parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substitution")) {
        res.setSubstitution(parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationPrescription.MedicationPrescriptionDosageInstructionComponent parseMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(XmlPullParser xpp, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDosageInstructionComponent res = new MedicationPrescription.MedicationPrescriptionDosageInstructionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additionalInstructions")) {
        res.setAdditionalInstructions(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "scheduled")) {
        res.setScheduled(parseType("scheduled", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("site")) {
        res.setSite(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("route")) {
        res.setRoute(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "dose")) {
        res.setDose(parseType("dose", xpp));
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

  protected MedicationPrescription.MedicationPrescriptionDispenseComponent parseMedicationPrescriptionMedicationPrescriptionDispenseComponent(XmlPullParser xpp, MedicationPrescription owner) throws Exception {
    MedicationPrescription.MedicationPrescriptionDispenseComponent res = new MedicationPrescription.MedicationPrescriptionDispenseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "medication")) {
        res.setMedication(parseType("medication", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("validityPeriod")) {
        res.setValidityPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("numberOfRepeatsAllowed")) {
        res.setNumberOfRepeatsAllowedElement(parsePositiveInt(xpp));
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

  protected MedicationPrescription.MedicationPrescriptionSubstitutionComponent parseMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(XmlPullParser xpp, MedicationPrescription owner) throws Exception {
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

  protected MedicationStatement parseMedicationStatement(XmlPullParser xpp) throws Exception {
    MedicationStatement res = new MedicationStatement();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("informationSource")) {
        res.setInformationSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateAsserted")) {
        res.setDateAssertedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, MedicationStatement.MedicationStatementStatus.NULL, new MedicationStatement.MedicationStatementStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("wasNotTaken")) {
        res.setWasNotTakenElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reasonNotTaken")) {
        res.getReasonNotTaken().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reasonForUse")) {
        res.setReasonForUse(parseType("reasonForUse", xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "effective")) {
        res.setEffective(parseType("effective", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.setNoteElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "medication")) {
        res.setMedication(parseType("medication", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dosage")) {
        res.getDosage().add(parseMedicationStatementMedicationStatementDosageComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MedicationStatement.MedicationStatementDosageComponent parseMedicationStatementMedicationStatementDosageComponent(XmlPullParser xpp, MedicationStatement owner) throws Exception {
    MedicationStatement.MedicationStatementDosageComponent res = new MedicationStatement.MedicationStatementDosageComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseTiming(xpp));
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

  protected MessageHeader parseMessageHeader(XmlPullParser xpp) throws Exception {
    MessageHeader res = new MessageHeader();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifierElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("timestamp")) {
        res.setTimestampElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.setEvent(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseMessageHeaderMessageHeaderResponseComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseMessageHeaderMessageSourceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.getDestination().add(parseMessageHeaderMessageDestinationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("enterer")) {
        res.setEnterer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.setReceiver(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responsible")) {
        res.setResponsible(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("data")) {
        res.getData().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MessageHeader.MessageHeaderResponseComponent parseMessageHeaderMessageHeaderResponseComponent(XmlPullParser xpp, MessageHeader owner) throws Exception {
    MessageHeader.MessageHeaderResponseComponent res = new MessageHeader.MessageHeaderResponseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifierElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseEnumeration(xpp, MessageHeader.ResponseType.NULL, new MessageHeader.ResponseTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MessageHeader.MessageSourceComponent parseMessageHeaderMessageSourceComponent(XmlPullParser xpp, MessageHeader owner) throws Exception {
    MessageHeader.MessageSourceComponent res = new MessageHeader.MessageSourceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("software")) {
        res.setSoftwareElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.setContact(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpointElement(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected MessageHeader.MessageDestinationComponent parseMessageHeaderMessageDestinationComponent(XmlPullParser xpp, MessageHeader owner) throws Exception {
    MessageHeader.MessageDestinationComponent res = new MessageHeader.MessageDestinationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpointElement(parseUri(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NamingSystem parseNamingSystem(XmlPullParser xpp) throws Exception {
    NamingSystem res = new NamingSystem();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, NamingSystem.NamingSystemType.NULL, new NamingSystem.NamingSystemTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("country")) {
        res.setCountryElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responsible")) {
        res.setResponsibleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("usage")) {
        res.setUsageElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uniqueId")) {
        res.getUniqueId().add(parseNamingSystemNamingSystemUniqueIdComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseNamingSystemNamingSystemContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("replacedBy")) {
        res.setReplacedBy(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NamingSystem.NamingSystemUniqueIdComponent parseNamingSystemNamingSystemUniqueIdComponent(XmlPullParser xpp, NamingSystem owner) throws Exception {
    NamingSystem.NamingSystemUniqueIdComponent res = new NamingSystem.NamingSystemUniqueIdComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, NamingSystem.NamingSystemIdentifierType.NULL, new NamingSystem.NamingSystemIdentifierTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preferred")) {
        res.setPreferredElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NamingSystem.NamingSystemContactComponent parseNamingSystemNamingSystemContactComponent(XmlPullParser xpp, NamingSystem owner) throws Exception {
    NamingSystem.NamingSystemContactComponent res = new NamingSystem.NamingSystemContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder parseNutritionOrder(XmlPullParser xpp) throws Exception {
    NutritionOrder res = new NutritionOrder();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderer")) {
        res.setOrderer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateTime")) {
        res.setDateTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, NutritionOrder.NutritionOrderStatus.NULL, new NutritionOrder.NutritionOrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("allergyIntolerance")) {
        res.getAllergyIntolerance().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("foodPreferenceModifier")) {
        res.getFoodPreferenceModifier().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("excludeFoodModifier")) {
        res.getExcludeFoodModifier().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("oralDiet")) {
        res.setOralDiet(parseNutritionOrderNutritionOrderOralDietComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supplement")) {
        res.getSupplement().add(parseNutritionOrderNutritionOrderSupplementComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("enteralFormula")) {
        res.setEnteralFormula(parseNutritionOrderNutritionOrderEnteralFormulaComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder.NutritionOrderOralDietComponent parseNutritionOrderNutritionOrderOralDietComponent(XmlPullParser xpp, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderOralDietComponent res = new NutritionOrder.NutritionOrderOralDietComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.getSchedule().add(parseTiming(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("nutrient")) {
        res.getNutrient().add(parseNutritionOrderNutritionOrderOralDietNutrientComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("texture")) {
        res.getTexture().add(parseNutritionOrderNutritionOrderOralDietTextureComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fluidConsistencyType")) {
        res.getFluidConsistencyType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instruction")) {
        res.setInstructionElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder.NutritionOrderOralDietNutrientComponent parseNutritionOrderNutritionOrderOralDietNutrientComponent(XmlPullParser xpp, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderOralDietNutrientComponent res = new NutritionOrder.NutritionOrderOralDietNutrientComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifier")) {
        res.setModifier(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseQuantity(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder.NutritionOrderOralDietTextureComponent parseNutritionOrderNutritionOrderOralDietTextureComponent(XmlPullParser xpp, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderOralDietTextureComponent res = new NutritionOrder.NutritionOrderOralDietTextureComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modifier")) {
        res.setModifier(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("foodType")) {
        res.setFoodType(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder.NutritionOrderSupplementComponent parseNutritionOrderNutritionOrderSupplementComponent(XmlPullParser xpp, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderSupplementComponent res = new NutritionOrder.NutritionOrderSupplementComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("productName")) {
        res.setProductNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.getSchedule().add(parseTiming(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instruction")) {
        res.setInstructionElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder.NutritionOrderEnteralFormulaComponent parseNutritionOrderNutritionOrderEnteralFormulaComponent(XmlPullParser xpp, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderEnteralFormulaComponent res = new NutritionOrder.NutritionOrderEnteralFormulaComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("baseFormulaType")) {
        res.setBaseFormulaType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("baseFormulaProductName")) {
        res.setBaseFormulaProductNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additiveType")) {
        res.setAdditiveType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additiveProductName")) {
        res.setAdditiveProductNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("caloricDensity")) {
        res.setCaloricDensity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("routeofAdministration")) {
        res.setRouteofAdministration(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("administration")) {
        res.getAdministration().add(parseNutritionOrderNutritionOrderEnteralFormulaAdministrationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maxVolumeToDeliver")) {
        res.setMaxVolumeToDeliver(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("administrationInstruction")) {
        res.setAdministrationInstructionElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected NutritionOrder.NutritionOrderEnteralFormulaAdministrationComponent parseNutritionOrderNutritionOrderEnteralFormulaAdministrationComponent(XmlPullParser xpp, NutritionOrder owner) throws Exception {
    NutritionOrder.NutritionOrderEnteralFormulaAdministrationComponent res = new NutritionOrder.NutritionOrderEnteralFormulaAdministrationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseTiming(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "rate")) {
        res.setRate(parseType("rate", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Observation parseObservation(XmlPullParser xpp) throws Exception {
    Observation res = new Observation();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dataAbsentReason")) {
        res.setDataAbsentReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("interpretation")) {
        res.setInterpretation(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setCommentsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "effective")) {
        res.setEffective(parseType("effective", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issued")) {
        res.setIssuedElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Observation.ObservationStatus.NULL, new Observation.ObservationStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reliability")) {
        res.setReliabilityElement(parseEnumeration(xpp, Observation.ObservationReliability.NULL, new Observation.ObservationReliabilityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "bodySite")) {
        res.setBodySite(parseType("bodySite", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimen")) {
        res.setSpecimen(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.setDevice(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referenceRange")) {
        res.getReferenceRange().add(parseObservationObservationReferenceRangeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("related")) {
        res.getRelated().add(parseObservationObservationRelatedComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("component")) {
        res.getComponent().add(parseObservationObservationComponentComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Observation.ObservationReferenceRangeComponent parseObservationObservationReferenceRangeComponent(XmlPullParser xpp, Observation owner) throws Exception {
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
        res.setTextElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Observation.ObservationRelatedComponent parseObservationObservationRelatedComponent(XmlPullParser xpp, Observation owner) throws Exception {
    Observation.ObservationRelatedComponent res = new Observation.ObservationRelatedComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Observation.ObservationRelationshipType.NULL, new Observation.ObservationRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Observation.ObservationComponentComponent parseObservationObservationComponentComponent(XmlPullParser xpp, Observation owner) throws Exception {
    Observation.ObservationComponentComponent res = new Observation.ObservationComponentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dataAbsentReason")) {
        res.setDataAbsentReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("referenceRange")) {
        res.getReferenceRange().add(parseObservationObservationReferenceRangeComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OperationDefinition parseOperationDefinition(XmlPullParser xpp) throws Exception {
    OperationDefinition res = new OperationDefinition();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseOperationDefinitionOperationDefinitionContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKindElement(parseEnumeration(xpp, OperationDefinition.OperationKind.NULL, new OperationDefinition.OperationKindEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("idempotent")) {
        res.setIdempotentElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBase(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.setInstanceElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseOperationDefinitionOperationDefinitionParameterComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OperationDefinition.OperationDefinitionContactComponent parseOperationDefinitionOperationDefinitionContactComponent(XmlPullParser xpp, OperationDefinition owner) throws Exception {
    OperationDefinition.OperationDefinitionContactComponent res = new OperationDefinition.OperationDefinitionContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OperationDefinition.OperationDefinitionParameterComponent parseOperationDefinitionOperationDefinitionParameterComponent(XmlPullParser xpp, OperationDefinition owner) throws Exception {
    OperationDefinition.OperationDefinitionParameterComponent res = new OperationDefinition.OperationDefinitionParameterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUseElement(parseEnumeration(xpp, OperationDefinition.OperationParameterUse.NULL, new OperationDefinition.OperationParameterUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("min")) {
        res.setMinElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("max")) {
        res.setMaxElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentationElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("binding")) {
        res.setBinding(parseOperationDefinitionOperationDefinitionParameterBindingComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("part")) {
        res.getPart().add(parseOperationDefinitionOperationDefinitionParameterComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OperationDefinition.OperationDefinitionParameterBindingComponent parseOperationDefinitionOperationDefinitionParameterBindingComponent(XmlPullParser xpp, OperationDefinition owner) throws Exception {
    OperationDefinition.OperationDefinitionParameterBindingComponent res = new OperationDefinition.OperationDefinitionParameterBindingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("strength")) {
        res.setStrengthElement(parseEnumeration(xpp, Enumerations.BindingStrength.NULL, new Enumerations.BindingStrengthEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "valueSet")) {
        res.setValueSet(parseType("valueSet", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OperationOutcome parseOperationOutcome(XmlPullParser xpp) throws Exception {
    OperationOutcome res = new OperationOutcome();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("issue")) {
        res.getIssue().add(parseOperationOutcomeOperationOutcomeIssueComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OperationOutcome.OperationOutcomeIssueComponent parseOperationOutcomeOperationOutcomeIssueComponent(XmlPullParser xpp, OperationOutcome owner) throws Exception {
    OperationOutcome.OperationOutcomeIssueComponent res = new OperationOutcome.OperationOutcomeIssueComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("severity")) {
        res.setSeverityElement(parseEnumeration(xpp, OperationOutcome.IssueSeverity.NULL, new OperationOutcome.IssueSeverityEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetailsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Order parseOrder(XmlPullParser xpp) throws Exception {
    Order res = new Order();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhen(parseOrderOrderWhenComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Order.OrderWhenComponent parseOrderOrderWhenComponent(XmlPullParser xpp, Order owner) throws Exception {
    Order.OrderWhenComponent res = new Order.OrderWhenComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseTiming(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected OrderResponse parseOrderResponse(XmlPullParser xpp) throws Exception {
    OrderResponse res = new OrderResponse();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("who")) {
        res.setWho(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderStatus")) {
        res.setOrderStatusElement(parseEnumeration(xpp, OrderResponse.OrderStatus.NULL, new OrderResponse.OrderStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fulfillment")) {
        res.getFulfillment().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Organization parseOrganization(XmlPullParser xpp) throws Exception {
    Organization res = new Organization();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("partOf")) {
        res.setPartOf(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseOrganizationOrganizationContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("active")) {
        res.setActiveElement(parseBoolean(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Organization.OrganizationContactComponent parseOrganizationOrganizationContactComponent(XmlPullParser xpp, Organization owner) throws Exception {
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
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Patient parsePatient(XmlPullParser xpp) throws Exception {
    Patient res = new Patient();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.getName().add(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGenderElement(parseEnumeration(xpp, Enumerations.AdministrativeGender.NULL, new Enumerations.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("birthDate")) {
        res.setBirthDateElement(parseDate(xpp));
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
        res.getCommunication().add(parsePatientPatientCommunicationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("careProvider")) {
        res.getCareProvider().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parsePatientPatientLinkComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("active")) {
        res.setActiveElement(parseBoolean(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Patient.ContactComponent parsePatientContactComponent(XmlPullParser xpp, Patient owner) throws Exception {
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
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGenderElement(parseEnumeration(xpp, Enumerations.AdministrativeGender.NULL, new Enumerations.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Patient.AnimalComponent parsePatientAnimalComponent(XmlPullParser xpp, Patient owner) throws Exception {
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

  protected Patient.PatientCommunicationComponent parsePatientPatientCommunicationComponent(XmlPullParser xpp, Patient owner) throws Exception {
    Patient.PatientCommunicationComponent res = new Patient.PatientCommunicationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguage(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preferred")) {
        res.setPreferredElement(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Patient.PatientLinkComponent parsePatientPatientLinkComponent(XmlPullParser xpp, Patient owner) throws Exception {
    Patient.PatientLinkComponent res = new Patient.PatientLinkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("other")) {
        res.setOther(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Patient.LinkType.NULL, new Patient.LinkTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected PaymentNotice parsePaymentNotice(XmlPullParser xpp) throws Exception {
    PaymentNotice res = new PaymentNotice();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("paymentStatus")) {
        res.setPaymentStatus(parseCoding(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected PaymentReconciliation parsePaymentReconciliation(XmlPullParser xpp) throws Exception {
    PaymentReconciliation res = new PaymentReconciliation();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcomeElement(parseEnumeration(xpp, Enumerations.RemittanceOutcome.NULL, new Enumerations.RemittanceOutcomeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("disposition")) {
        res.setDispositionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestProvider")) {
        res.setRequestProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestOrganization")) {
        res.setRequestOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("detail")) {
        res.getDetail().add(parsePaymentReconciliationDetailsComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("form")) {
        res.setForm(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("total")) {
        res.setTotal(parseMoney(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("note")) {
        res.getNote().add(parsePaymentReconciliationNotesComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected PaymentReconciliation.DetailsComponent parsePaymentReconciliationDetailsComponent(XmlPullParser xpp, PaymentReconciliation owner) throws Exception {
    PaymentReconciliation.DetailsComponent res = new PaymentReconciliation.DetailsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responce")) {
        res.setResponce(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("submitter")) {
        res.setSubmitter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payee")) {
        res.setPayee(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("amount")) {
        res.setAmount(parseMoney(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected PaymentReconciliation.NotesComponent parsePaymentReconciliationNotesComponent(XmlPullParser xpp, PaymentReconciliation owner) throws Exception {
    PaymentReconciliation.NotesComponent res = new PaymentReconciliation.NotesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Person parsePerson(XmlPullParser xpp) throws Exception {
    Person res = new Person();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.getName().add(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGenderElement(parseEnumeration(xpp, Enumerations.AdministrativeGender.NULL, new Enumerations.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("birthDate")) {
        res.setBirthDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.setPhoto(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("active")) {
        res.setActiveElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parsePersonPersonLinkComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Person.PersonLinkComponent parsePersonPersonLinkComponent(XmlPullParser xpp, Person owner) throws Exception {
    Person.PersonLinkComponent res = new Person.PersonLinkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assurance")) {
        res.setAssuranceElement(parseEnumeration(xpp, Person.IdentityAssuranceLevel.NULL, new Person.IdentityAssuranceLevelEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Practitioner parsePractitioner(XmlPullParser xpp) throws Exception {
    Practitioner res = new Practitioner();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGenderElement(parseEnumeration(xpp, Enumerations.AdministrativeGender.NULL, new Enumerations.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("birthDate")) {
        res.setBirthDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.getPhoto().add(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("practitionerRole")) {
        res.getPractitionerRole().add(parsePractitionerPractitionerPractitionerRoleComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("qualification")) {
        res.getQualification().add(parsePractitionerPractitionerQualificationComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("communication")) {
        res.getCommunication().add(parseCodeableConcept(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Practitioner.PractitionerPractitionerRoleComponent parsePractitionerPractitionerPractitionerRoleComponent(XmlPullParser xpp, Practitioner owner) throws Exception {
    Practitioner.PractitionerPractitionerRoleComponent res = new Practitioner.PractitionerPractitionerRoleComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("managingOrganization")) {
        res.setManagingOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialty")) {
        res.getSpecialty().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.getLocation().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("healthcareService")) {
        res.getHealthcareService().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Practitioner.PractitionerQualificationComponent parsePractitionerPractitionerQualificationComponent(XmlPullParser xpp, Practitioner owner) throws Exception {
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
        res.setIssuer(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Procedure parseProcedure(XmlPullParser xpp) throws Exception {
    Procedure res = new Procedure();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Procedure.ProcedureStatus.NULL, new Procedure.ProcedureStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.getBodySite().add(parseProcedureProcedureBodySiteComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.getIndication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseProcedureProcedurePerformerComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "performed")) {
        res.setPerformed(parseType("performed", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("report")) {
        res.getReport().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("complication")) {
        res.getComplication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("followUp")) {
        res.getFollowUp().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relatedItem")) {
        res.getRelatedItem().add(parseProcedureProcedureRelatedItemComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("device")) {
        res.getDevice().add(parseProcedureProcedureDeviceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("used")) {
        res.getUsed().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Procedure.ProcedureBodySiteComponent parseProcedureProcedureBodySiteComponent(XmlPullParser xpp, Procedure owner) throws Exception {
    Procedure.ProcedureBodySiteComponent res = new Procedure.ProcedureBodySiteComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "site")) {
        res.setSite(parseType("site", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Procedure.ProcedurePerformerComponent parseProcedureProcedurePerformerComponent(XmlPullParser xpp, Procedure owner) throws Exception {
    Procedure.ProcedurePerformerComponent res = new Procedure.ProcedurePerformerComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("person")) {
        res.setPerson(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCodeableConcept(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Procedure.ProcedureRelatedItemComponent parseProcedureProcedureRelatedItemComponent(XmlPullParser xpp, Procedure owner) throws Exception {
    Procedure.ProcedureRelatedItemComponent res = new Procedure.ProcedureRelatedItemComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Procedure.ProcedureRelationshipType.NULL, new Procedure.ProcedureRelationshipTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Procedure.ProcedureDeviceComponent parseProcedureProcedureDeviceComponent(XmlPullParser xpp, Procedure owner) throws Exception {
    Procedure.ProcedureDeviceComponent res = new Procedure.ProcedureDeviceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.setAction(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manipulated")) {
        res.setManipulated(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ProcedureRequest parseProcedureRequest(XmlPullParser xpp) throws Exception {
    ProcedureRequest res = new ProcedureRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("bodySite")) {
        res.getBodySite().add(parseProcedureRequestProcedureRequestBodySiteComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("indication")) {
        res.getIndication().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "timing")) {
        res.setTiming(parseType("timing", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, ProcedureRequest.ProcedureRequestStatus.NULL, new ProcedureRequest.ProcedureRequestStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.getNotes().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "asNeeded")) {
        res.setAsNeeded(parseType("asNeeded", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedOn")) {
        res.setOrderedOnElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderer")) {
        res.setOrderer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriorityElement(parseEnumeration(xpp, ProcedureRequest.ProcedureRequestPriority.NULL, new ProcedureRequest.ProcedureRequestPriorityEnumFactory()));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ProcedureRequest.ProcedureRequestBodySiteComponent parseProcedureRequestProcedureRequestBodySiteComponent(XmlPullParser xpp, ProcedureRequest owner) throws Exception {
    ProcedureRequest.ProcedureRequestBodySiteComponent res = new ProcedureRequest.ProcedureRequestBodySiteComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "site")) {
        res.setSite(parseType("site", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ProcessRequest parseProcessRequest(XmlPullParser xpp) throws Exception {
    ProcessRequest res = new ProcessRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.setActionElement(parseEnumeration(xpp, ProcessRequest.ActionList.NULL, new ProcessRequest.ActionListEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTarget(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("nullify")) {
        res.setNullifyElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReferenceElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("item")) {
        res.getItem().add(parseProcessRequestItemsComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("include")) {
        res.getInclude().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("exclude")) {
        res.getExclude().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ProcessRequest.ItemsComponent parseProcessRequestItemsComponent(XmlPullParser xpp, ProcessRequest owner) throws Exception {
    ProcessRequest.ItemsComponent res = new ProcessRequest.ItemsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sequenceLinkId")) {
        res.setSequenceLinkIdElement(parseInteger(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ProcessResponse parseProcessResponse(XmlPullParser xpp) throws Exception {
    ProcessResponse res = new ProcessResponse();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("outcome")) {
        res.setOutcome(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("disposition")) {
        res.setDispositionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ruleset")) {
        res.setRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("originalRuleset")) {
        res.setOriginalRuleset(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("created")) {
        res.setCreatedElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestProvider")) {
        res.setRequestProvider(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requestOrganization")) {
        res.setRequestOrganization(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("form")) {
        res.setForm(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.getNotes().add(parseProcessResponseProcessResponseNotesComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("error")) {
        res.getError().add(parseCoding(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ProcessResponse.ProcessResponseNotesComponent parseProcessResponseProcessResponseNotesComponent(XmlPullParser xpp, ProcessResponse owner) throws Exception {
    ProcessResponse.ProcessResponseNotesComponent res = new ProcessResponse.ProcessResponseNotesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Provenance parseProvenance(XmlPullParser xpp) throws Exception {
    Provenance res = new Provenance();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recorded")) {
        res.setRecordedElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("policy")) {
        res.getPolicy().add(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("agent")) {
        res.getAgent().add(parseProvenanceProvenanceAgentComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entity")) {
        res.getEntity().add(parseProvenanceProvenanceEntityComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("signature")) {
        res.getSignature().add(parseSignature(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Provenance.ProvenanceAgentComponent parseProvenanceProvenanceAgentComponent(XmlPullParser xpp, Provenance owner) throws Exception {
    Provenance.ProvenanceAgentComponent res = new Provenance.ProvenanceAgentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("userId")) {
        res.setUserId(parseIdentifier(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Provenance.ProvenanceEntityComponent parseProvenanceProvenanceEntityComponent(XmlPullParser xpp, Provenance owner) throws Exception {
    Provenance.ProvenanceEntityComponent res = new Provenance.ProvenanceEntityComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRoleElement(parseEnumeration(xpp, Provenance.ProvenanceEntityRole.NULL, new Provenance.ProvenanceEntityRoleEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reference")) {
        res.setReferenceElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("agent")) {
        res.setAgent(parseProvenanceProvenanceAgentComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Questionnaire parseQuestionnaire(XmlPullParser xpp) throws Exception {
    Questionnaire res = new Questionnaire();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Questionnaire.QuestionnaireStatus.NULL, new Questionnaire.QuestionnaireStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.setGroup(parseQuestionnaireGroupComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Questionnaire.GroupComponent parseQuestionnaireGroupComponent(XmlPullParser xpp, Questionnaire owner) throws Exception {
    Questionnaire.GroupComponent res = new Questionnaire.GroupComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkIdElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("required")) {
        res.setRequiredElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeats")) {
        res.setRepeatsElement(parseBoolean(xpp));
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

  protected Questionnaire.QuestionComponent parseQuestionnaireQuestionComponent(XmlPullParser xpp, Questionnaire owner) throws Exception {
    Questionnaire.QuestionComponent res = new Questionnaire.QuestionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkIdElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Questionnaire.AnswerFormat.NULL, new Questionnaire.AnswerFormatEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("required")) {
        res.setRequiredElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeats")) {
        res.setRepeatsElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("options")) {
        res.setOptions(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.getGroup().add(parseQuestionnaireGroupComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected QuestionnaireAnswers parseQuestionnaireAnswers(XmlPullParser xpp) throws Exception {
    QuestionnaireAnswers res = new QuestionnaireAnswers();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("questionnaire")) {
        res.setQuestionnaire(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, QuestionnaireAnswers.QuestionnaireAnswersStatus.NULL, new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        res.setAuthor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("authored")) {
        res.setAuthoredElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("group")) {
        res.setGroup(parseQuestionnaireAnswersGroupComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected QuestionnaireAnswers.GroupComponent parseQuestionnaireAnswersGroupComponent(XmlPullParser xpp, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.GroupComponent res = new QuestionnaireAnswers.GroupComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkIdElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitleElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
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

  protected QuestionnaireAnswers.QuestionComponent parseQuestionnaireAnswersQuestionComponent(XmlPullParser xpp, QuestionnaireAnswers owner) throws Exception {
    QuestionnaireAnswers.QuestionComponent res = new QuestionnaireAnswers.QuestionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("linkId")) {
        res.setLinkIdElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setTextElement(parseString(xpp));
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

  protected QuestionnaireAnswers.QuestionAnswerComponent parseQuestionnaireAnswersQuestionAnswerComponent(XmlPullParser xpp, QuestionnaireAnswers owner) throws Exception {
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

  protected ReferralRequest parseReferralRequest(XmlPullParser xpp) throws Exception {
    ReferralRequest res = new ReferralRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, ReferralRequest.ReferralStatus.NULL, new ReferralRequest.ReferralStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialty")) {
        res.setSpecialty(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("priority")) {
        res.setPriority(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requester")) {
        res.setRequester(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recipient")) {
        res.getRecipient().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateSent")) {
        res.setDateSentElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReason(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serviceRequested")) {
        res.getServiceRequested().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supportingInformation")) {
        res.getSupportingInformation().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fulfillmentTime")) {
        res.setFulfillmentTime(parsePeriod(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected RelatedPerson parseRelatedPerson(XmlPullParser xpp) throws Exception {
    RelatedPerson res = new RelatedPerson();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("relationship")) {
        res.setRelationship(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGenderElement(parseEnumeration(xpp, Enumerations.AdministrativeGender.NULL, new Enumerations.AdministrativeGenderEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("photo")) {
        res.getPhoto().add(parseAttachment(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected RiskAssessment parseRiskAssessment(XmlPullParser xpp) throws Exception {
    RiskAssessment res = new RiskAssessment();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("condition")) {
        res.setCondition(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.setPerformer(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("basis")) {
        res.getBasis().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prediction")) {
        res.getPrediction().add(parseRiskAssessmentRiskAssessmentPredictionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mitigation")) {
        res.setMitigationElement(parseString(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected RiskAssessment.RiskAssessmentPredictionComponent parseRiskAssessmentRiskAssessmentPredictionComponent(XmlPullParser xpp, RiskAssessment owner) throws Exception {
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
        res.setRelativeRiskElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "when")) {
        res.setWhen(parseType("when", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rationale")) {
        res.setRationaleElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Schedule parseSchedule(XmlPullParser xpp) throws Exception {
    Schedule res = new Schedule();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.getType().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("actor")) {
        res.setActor(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("planningHorizon")) {
        res.setPlanningHorizon(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected SearchParameter parseSearchParameter(XmlPullParser xpp) throws Exception {
    SearchParameter res = new SearchParameter();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseSearchParameterSearchParameterContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBaseElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Enumerations.SearchParamType.NULL, new Enumerations.SearchParamTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("xpath")) {
        res.setXpathElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.getTarget().add(parseCode(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected SearchParameter.SearchParameterContactComponent parseSearchParameterSearchParameterContactComponent(XmlPullParser xpp, SearchParameter owner) throws Exception {
    SearchParameter.SearchParameterContactComponent res = new SearchParameter.SearchParameterContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Slot parseSlot(XmlPullParser xpp) throws Exception {
    Slot res = new Slot();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("freeBusyType")) {
        res.setFreeBusyTypeElement(parseEnumeration(xpp, Slot.SlotStatus.NULL, new Slot.SlotStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStartElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEndElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("overbooked")) {
        res.setOverbookedElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.setCommentElement(parseString(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Specimen parseSpecimen(XmlPullParser xpp) throws Exception {
    Specimen res = new Specimen();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parent")) {
        res.getParent().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("subject")) {
        res.setSubject(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("accessionIdentifier")) {
        res.setAccessionIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receivedTime")) {
        res.setReceivedTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("collection")) {
        res.setCollection(parseSpecimenSpecimenCollectionComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("treatment")) {
        res.getTreatment().add(parseSpecimenSpecimenTreatmentComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("container")) {
        res.getContainer().add(parseSpecimenSpecimenContainerComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Specimen.SpecimenCollectionComponent parseSpecimenSpecimenCollectionComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenCollectionComponent res = new Specimen.SpecimenCollectionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("collector")) {
        res.setCollector(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comment")) {
        res.getComment().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "collected")) {
        res.setCollected(parseType("collected", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("method")) {
        res.setMethod(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "bodySite")) {
        res.setBodySite(parseType("bodySite", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Specimen.SpecimenTreatmentComponent parseSpecimenSpecimenTreatmentComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenTreatmentComponent res = new Specimen.SpecimenTreatmentComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("procedure")) {
        res.setProcedure(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("additive")) {
        res.getAdditive().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Specimen.SpecimenContainerComponent parseSpecimenSpecimenContainerComponent(XmlPullParser xpp, Specimen owner) throws Exception {
    Specimen.SpecimenContainerComponent res = new Specimen.SpecimenContainerComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("capacity")) {
        res.setCapacity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specimenQuantity")) {
        res.setSpecimenQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "additive")) {
        res.setAdditive(parseType("additive", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected StructureDefinition parseStructureDefinition(XmlPullParser xpp) throws Exception {
    StructureDefinition res = new StructureDefinition();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("useContext")) {
        res.getUseContext().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseStructureDefinitionStructureDefinitionContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyrightElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.getCode().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fhirVersion")) {
        res.setFhirVersionElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mapping")) {
        res.getMapping().add(parseStructureDefinitionStructureDefinitionMappingComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, StructureDefinition.StructureDefinitionType.NULL, new StructureDefinition.StructureDefinitionTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("abstract")) {
        res.setAbstractElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contextType")) {
        res.setContextTypeElement(parseEnumeration(xpp, StructureDefinition.ExtensionContext.NULL, new StructureDefinition.ExtensionContextEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("context")) {
        res.getContext().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBaseElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("snapshot")) {
        res.setSnapshot(parseStructureDefinitionStructureDefinitionSnapshotComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("differential")) {
        res.setDifferential(parseStructureDefinitionStructureDefinitionDifferentialComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected StructureDefinition.StructureDefinitionContactComponent parseStructureDefinitionStructureDefinitionContactComponent(XmlPullParser xpp, StructureDefinition owner) throws Exception {
    StructureDefinition.StructureDefinitionContactComponent res = new StructureDefinition.StructureDefinitionContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected StructureDefinition.StructureDefinitionMappingComponent parseStructureDefinitionStructureDefinitionMappingComponent(XmlPullParser xpp, StructureDefinition owner) throws Exception {
    StructureDefinition.StructureDefinitionMappingComponent res = new StructureDefinition.StructureDefinitionMappingComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identity")) {
        res.setIdentityElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri")) {
        res.setUriElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comments")) {
        res.setCommentsElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected StructureDefinition.StructureDefinitionSnapshotComponent parseStructureDefinitionStructureDefinitionSnapshotComponent(XmlPullParser xpp, StructureDefinition owner) throws Exception {
    StructureDefinition.StructureDefinitionSnapshotComponent res = new StructureDefinition.StructureDefinitionSnapshotComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseElementDefinition(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected StructureDefinition.StructureDefinitionDifferentialComponent parseStructureDefinitionStructureDefinitionDifferentialComponent(XmlPullParser xpp, StructureDefinition owner) throws Exception {
    StructureDefinition.StructureDefinitionDifferentialComponent res = new StructureDefinition.StructureDefinitionDifferentialComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("element")) {
        res.getElement().add(parseElementDefinition(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Subscription parseSubscription(XmlPullParser xpp) throws Exception {
    Subscription res = new Subscription();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("criteria")) {
        res.setCriteriaElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContactPoint(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("reason")) {
        res.setReasonElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Subscription.SubscriptionStatus.NULL, new Subscription.SubscriptionStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("error")) {
        res.setErrorElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("channel")) {
        res.setChannel(parseSubscriptionSubscriptionChannelComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEndElement(parseInstant(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("tag")) {
        res.getTag().add(parseCoding(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Subscription.SubscriptionChannelComponent parseSubscriptionSubscriptionChannelComponent(XmlPullParser xpp, Subscription owner) throws Exception {
    Subscription.SubscriptionChannelComponent res = new Subscription.SubscriptionChannelComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, Subscription.SubscriptionChannelType.NULL, new Subscription.SubscriptionChannelTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpointElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("payload")) {
        res.setPayloadElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("header")) {
        res.setHeaderElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Substance parseSubstance(XmlPullParser xpp) throws Exception {
    Substance res = new Substance();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("instance")) {
        res.getInstance().add(parseSubstanceSubstanceInstanceComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("ingredient")) {
        res.getIngredient().add(parseSubstanceSubstanceIngredientComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Substance.SubstanceInstanceComponent parseSubstanceSubstanceInstanceComponent(XmlPullParser xpp, Substance owner) throws Exception {
    Substance.SubstanceInstanceComponent res = new Substance.SubstanceInstanceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expiry")) {
        res.setExpiryElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Substance.SubstanceIngredientComponent parseSubstanceSubstanceIngredientComponent(XmlPullParser xpp, Substance owner) throws Exception {
    Substance.SubstanceIngredientComponent res = new Substance.SubstanceIngredientComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseRatio(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("substance")) {
        res.setSubstance(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Supply parseSupply(XmlPullParser xpp) throws Exception {
    Supply res = new Supply();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKind(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Supply.SupplyStatus.NULL, new Supply.SupplyStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedItem")) {
        res.setOrderedItem(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispense")) {
        res.getDispense().add(parseSupplySupplyDispenseComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected Supply.SupplyDispenseComponent parseSupplySupplyDispenseComponent(XmlPullParser xpp, Supply owner) throws Exception {
    Supply.SupplyDispenseComponent res = new Supply.SupplyDispenseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Supply.SupplyDispenseStatus.NULL, new Supply.SupplyDispenseStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("suppliedItem")) {
        res.setSuppliedItem(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supplier")) {
        res.setSupplier(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenPrepared")) {
        res.setWhenPrepared(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenHandedOver")) {
        res.setWhenHandedOverElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.getReceiver().add(parseReference(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected SupplyDelivery parseSupplyDelivery(XmlPullParser xpp) throws Exception {
    SupplyDelivery res = new SupplyDelivery();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, SupplyDelivery.SupplyDeliveryStatus.NULL, new SupplyDelivery.SupplyDeliveryStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("suppliedItem")) {
        res.setSuppliedItem(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supplier")) {
        res.setSupplier(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("whenPrepared")) {
        res.setWhenPrepared(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("time")) {
        res.setTimeElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestination(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("receiver")) {
        res.getReceiver().add(parseReference(xpp));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected SupplyRequest parseSupplyRequest(XmlPullParser xpp) throws Exception {
    SupplyRequest res = new SupplyRequest();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, SupplyRequest.SupplyRequestStatus.NULL, new SupplyRequest.SupplyRequestStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("kind")) {
        res.setKind(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("orderedItem")) {
        res.setOrderedItem(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("supplier")) {
        res.getSupplier().add(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhen(parseSupplyRequestSupplyRequestWhenComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected SupplyRequest.SupplyRequestWhenComponent parseSupplyRequestSupplyRequestWhenComponent(XmlPullParser xpp, SupplyRequest owner) throws Exception {
    SupplyRequest.SupplyRequestWhenComponent res = new SupplyRequest.SupplyRequestWhenComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseTiming(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript parseTestScript(XmlPullParser xpp) throws Exception {
    TestScript res = new TestScript();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("multiserver")) {
        res.setMultiserverElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("fixture")) {
        res.getFixture().add(parseTestScriptTestScriptFixtureComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("setup")) {
        res.setSetup(parseTestScriptTestScriptSetupComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("test")) {
        res.getTest().add(parseTestScriptTestScriptTestComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("teardown")) {
        res.setTeardown(parseTestScriptTestScriptTeardownComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptFixtureComponent parseTestScriptTestScriptFixtureComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptFixtureComponent res = new TestScript.TestScriptFixtureComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri")) {
        res.setUriElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("resource")) {
        res.setResource(parseResourceContained(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("autocreate")) {
        res.setAutocreateElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("autodelete")) {
        res.setAutodeleteElement(parseBoolean(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptSetupComponent parseTestScriptTestScriptSetupComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptSetupComponent res = new TestScript.TestScriptSetupComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operation")) {
        res.getOperation().add(parseTestScriptTestScriptSetupOperationComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptSetupOperationComponent parseTestScriptTestScriptSetupOperationComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptSetupOperationComponent res = new TestScript.TestScriptSetupOperationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, TestScript.TestOperationType.NULL, new TestScript.TestOperationTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSourceElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTargetElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestinationElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responseId")) {
        res.setResponseIdElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contentType")) {
        res.setContentTypeElement(parseEnumeration(xpp, TestScript.ContentType.NULL, new TestScript.ContentTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTestComponent parseTestScriptTestScriptTestComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTestComponent res = new TestScript.TestScriptTestComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("metadata")) {
        res.setMetadata(parseTestScriptTestScriptTestMetadataComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operation")) {
        res.getOperation().add(parseTestScriptTestScriptTestOperationComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTestMetadataComponent parseTestScriptTestScriptTestMetadataComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTestMetadataComponent res = new TestScript.TestScriptTestMetadataComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parseTestScriptTestScriptTestMetadataLinkComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requires")) {
        res.getRequires().add(parseTestScriptTestScriptTestMetadataRequiresComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("validates")) {
        res.getValidates().add(parseTestScriptTestScriptTestMetadataValidatesComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTestMetadataLinkComponent parseTestScriptTestScriptTestMetadataLinkComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTestMetadataLinkComponent res = new TestScript.TestScriptTestMetadataLinkComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTestMetadataRequiresComponent parseTestScriptTestScriptTestMetadataRequiresComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTestMetadataRequiresComponent res = new TestScript.TestScriptTestMetadataRequiresComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operations")) {
        res.setOperationsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestinationElement(parseInteger(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTestMetadataValidatesComponent parseTestScriptTestScriptTestMetadataValidatesComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTestMetadataValidatesComponent res = new TestScript.TestScriptTestMetadataValidatesComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operations")) {
        res.setOperationsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestinationElement(parseInteger(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTestOperationComponent parseTestScriptTestScriptTestOperationComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTestOperationComponent res = new TestScript.TestScriptTestOperationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, TestScript.TestOperationType.NULL, new TestScript.TestOperationTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSourceElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTargetElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestinationElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responseId")) {
        res.setResponseIdElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contentType")) {
        res.setContentTypeElement(parseEnumeration(xpp, TestScript.ContentType.NULL, new TestScript.ContentTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTeardownComponent parseTestScriptTestScriptTeardownComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTeardownComponent res = new TestScript.TestScriptTeardownComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operation")) {
        res.getOperation().add(parseTestScriptTestScriptTeardownOperationComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected TestScript.TestScriptTeardownOperationComponent parseTestScriptTestScriptTeardownOperationComponent(XmlPullParser xpp, TestScript owner) throws Exception {
    TestScript.TestScriptTeardownOperationComponent res = new TestScript.TestScriptTeardownOperationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setTypeElement(parseEnumeration(xpp, TestScript.TestOperationType.NULL, new TestScript.TestOperationTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSourceElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("target")) {
        res.setTargetElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("destination")) {
        res.setDestinationElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("responseId")) {
        res.setResponseIdElement(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contentType")) {
        res.setContentTypeElement(parseEnumeration(xpp, TestScript.ContentType.NULL, new TestScript.ContentTypeEnumFactory()));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet parseValueSet(XmlPullParser xpp) throws Exception {
    ValueSet res = new ValueSet();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrlElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("useContext")) {
        res.getUseContext().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("immutable")) {
        res.setImmutableElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisherElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseValueSetValueSetContactComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescriptionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("requirements")) {
        res.setRequirementsElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("copyright")) {
        res.setCopyrightElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatusElement(parseEnumeration(xpp, Enumerations.ConformanceResourceStatus.NULL, new Enumerations.ConformanceResourceStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("experimental")) {
        res.setExperimentalElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extensible")) {
        res.setExtensibleElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDateElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lockedDate")) {
        res.setLockedDateElement(parseDate(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("define")) {
        res.setDefine(parseValueSetValueSetDefineComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("compose")) {
        res.setCompose(parseValueSetValueSetComposeComponent(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expansion")) {
        res.setExpansion(parseValueSetValueSetExpansionComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ValueSetContactComponent parseValueSetValueSetContactComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetContactComponent res = new ValueSet.ValueSetContactComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContactPoint(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ValueSetDefineComponent parseValueSetValueSetDefineComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetDefineComponent res = new ValueSet.ValueSetDefineComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("caseSensitive")) {
        res.setCaseSensitiveElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseValueSetConceptDefinitionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ConceptDefinitionComponent parseValueSetConceptDefinitionComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptDefinitionComponent res = new ValueSet.ConceptDefinitionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("abstract")) {
        res.setAbstractElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("definition")) {
        res.setDefinitionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("designation")) {
        res.getDesignation().add(parseValueSetConceptDefinitionDesignationComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseValueSetConceptDefinitionComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ConceptDefinitionDesignationComponent parseValueSetConceptDefinitionDesignationComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptDefinitionDesignationComponent res = new ValueSet.ConceptDefinitionDesignationComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguageElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ValueSetComposeComponent parseValueSetValueSetComposeComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
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

  protected ValueSet.ConceptSetComponent parseValueSetConceptSetComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptSetComponent res = new ValueSet.ConceptSetComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concept")) {
        res.getConcept().add(parseValueSetConceptReferenceComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("filter")) {
        res.getFilter().add(parseValueSetConceptSetFilterComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ConceptReferenceComponent parseValueSetConceptReferenceComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptReferenceComponent res = new ValueSet.ConceptReferenceComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("designation")) {
        res.getDesignation().add(parseValueSetConceptDefinitionDesignationComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ConceptSetFilterComponent parseValueSetConceptSetFilterComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ConceptSetFilterComponent res = new ValueSet.ConceptSetFilterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("property")) {
        res.setPropertyElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("op")) {
        res.setOpElement(parseEnumeration(xpp, ValueSet.FilterOperator.NULL, new ValueSet.FilterOperatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValueElement(parseCode(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ValueSetExpansionComponent parseValueSetValueSetExpansionComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionComponent res = new ValueSet.ValueSetExpansionComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifierElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("timestamp")) {
        res.setTimestampElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("total")) {
        res.setTotalElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("count")) {
        res.setCountElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("parameter")) {
        res.getParameter().add(parseValueSetValueSetExpansionParameterComponent(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contains")) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ValueSetExpansionParameterComponent parseValueSetValueSetExpansionParameterComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionParameterComponent res = new ValueSet.ValueSetExpansionParameterComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setNameElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected ValueSet.ValueSetExpansionContainsComponent parseValueSetValueSetExpansionContainsComponent(XmlPullParser xpp, ValueSet owner) throws Exception {
    ValueSet.ValueSetExpansionContainsComponent res = new ValueSet.ValueSetExpansionContainsComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystemElement(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("abstract")) {
        res.setAbstractElement(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersionElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCodeElement(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplayElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contains")) {
        res.getContains().add(parseValueSetValueSetExpansionContainsComponent(xpp, owner));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected VisionPrescription parseVisionPrescription(XmlPullParser xpp) throws Exception {
    VisionPrescription res = new VisionPrescription();
    parseDomainResourceAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dateWritten")) {
        res.setDateWrittenElement(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prescriber")) {
        res.setPrescriber(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("encounter")) {
        res.setEncounter(parseReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "reason")) {
        res.setReason(parseType("reason", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dispense")) {
        res.getDispense().add(parseVisionPrescriptionVisionPrescriptionDispenseComponent(xpp, res));
      } else if (!parseDomainResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  protected VisionPrescription.VisionPrescriptionDispenseComponent parseVisionPrescriptionVisionPrescriptionDispenseComponent(XmlPullParser xpp, VisionPrescription owner) throws Exception {
    VisionPrescription.VisionPrescriptionDispenseComponent res = new VisionPrescription.VisionPrescriptionDispenseComponent();
    parseBackboneAttributes(xpp, res);
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.setProduct(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("eye")) {
        res.setEyeElement(parseEnumeration(xpp, VisionPrescription.VisionEyes.NULL, new VisionPrescription.VisionEyesEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("sphere")) {
        res.setSphereElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("cylinder")) {
        res.setCylinderElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("axis")) {
        res.setAxisElement(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prism")) {
        res.setPrismElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("base")) {
        res.setBaseElement(parseEnumeration(xpp, VisionPrescription.VisionBase.NULL, new VisionPrescription.VisionBaseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("add")) {
        res.setAddElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("power")) {
        res.setPowerElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("backCurve")) {
        res.setBackCurveElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("diameter")) {
        res.setDiameterElement(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("duration")) {
        res.setDuration(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("color")) {
        res.setColorElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("brand")) {
        res.setBrandElement(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("notes")) {
        res.setNotesElement(parseString(xpp));
      } else if (!parseBackboneContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    next(xpp);
    return res;
  }

  @Override
  protected Resource parseResource(XmlPullParser xpp) throws Exception {
    if (xpp.getName().equals("Parameters"))
      return parseParameters(xpp);
    else if (xpp.getName().equals("AllergyIntolerance"))
      return parseAllergyIntolerance(xpp);
    else if (xpp.getName().equals("Appointment"))
      return parseAppointment(xpp);
    else if (xpp.getName().equals("AppointmentResponse"))
      return parseAppointmentResponse(xpp);
    else if (xpp.getName().equals("AuditEvent"))
      return parseAuditEvent(xpp);
    else if (xpp.getName().equals("Basic"))
      return parseBasic(xpp);
    else if (xpp.getName().equals("Binary"))
      return parseBinary(xpp);
    else if (xpp.getName().equals("BodySite"))
      return parseBodySite(xpp);
    else if (xpp.getName().equals("Bundle"))
      return parseBundle(xpp);
    else if (xpp.getName().equals("CarePlan"))
      return parseCarePlan(xpp);
    else if (xpp.getName().equals("Claim"))
      return parseClaim(xpp);
    else if (xpp.getName().equals("ClaimResponse"))
      return parseClaimResponse(xpp);
    else if (xpp.getName().equals("ClinicalImpression"))
      return parseClinicalImpression(xpp);
    else if (xpp.getName().equals("Communication"))
      return parseCommunication(xpp);
    else if (xpp.getName().equals("CommunicationRequest"))
      return parseCommunicationRequest(xpp);
    else if (xpp.getName().equals("Composition"))
      return parseComposition(xpp);
    else if (xpp.getName().equals("ConceptMap"))
      return parseConceptMap(xpp);
    else if (xpp.getName().equals("Condition"))
      return parseCondition(xpp);
    else if (xpp.getName().equals("Conformance"))
      return parseConformance(xpp);
    else if (xpp.getName().equals("Contract"))
      return parseContract(xpp);
    else if (xpp.getName().equals("Contraindication"))
      return parseContraindication(xpp);
    else if (xpp.getName().equals("Coverage"))
      return parseCoverage(xpp);
    else if (xpp.getName().equals("DataElement"))
      return parseDataElement(xpp);
    else if (xpp.getName().equals("Device"))
      return parseDevice(xpp);
    else if (xpp.getName().equals("DeviceComponent"))
      return parseDeviceComponent(xpp);
    else if (xpp.getName().equals("DeviceMetric"))
      return parseDeviceMetric(xpp);
    else if (xpp.getName().equals("DeviceUseRequest"))
      return parseDeviceUseRequest(xpp);
    else if (xpp.getName().equals("DeviceUseStatement"))
      return parseDeviceUseStatement(xpp);
    else if (xpp.getName().equals("DiagnosticOrder"))
      return parseDiagnosticOrder(xpp);
    else if (xpp.getName().equals("DiagnosticReport"))
      return parseDiagnosticReport(xpp);
    else if (xpp.getName().equals("DocumentManifest"))
      return parseDocumentManifest(xpp);
    else if (xpp.getName().equals("DocumentReference"))
      return parseDocumentReference(xpp);
    else if (xpp.getName().equals("EligibilityRequest"))
      return parseEligibilityRequest(xpp);
    else if (xpp.getName().equals("EligibilityResponse"))
      return parseEligibilityResponse(xpp);
    else if (xpp.getName().equals("Encounter"))
      return parseEncounter(xpp);
    else if (xpp.getName().equals("EnrollmentRequest"))
      return parseEnrollmentRequest(xpp);
    else if (xpp.getName().equals("EnrollmentResponse"))
      return parseEnrollmentResponse(xpp);
    else if (xpp.getName().equals("EpisodeOfCare"))
      return parseEpisodeOfCare(xpp);
    else if (xpp.getName().equals("ExplanationOfBenefit"))
      return parseExplanationOfBenefit(xpp);
    else if (xpp.getName().equals("FamilyMemberHistory"))
      return parseFamilyMemberHistory(xpp);
    else if (xpp.getName().equals("Flag"))
      return parseFlag(xpp);
    else if (xpp.getName().equals("Goal"))
      return parseGoal(xpp);
    else if (xpp.getName().equals("Group"))
      return parseGroup(xpp);
    else if (xpp.getName().equals("HealthcareService"))
      return parseHealthcareService(xpp);
    else if (xpp.getName().equals("ImagingObjectSelection"))
      return parseImagingObjectSelection(xpp);
    else if (xpp.getName().equals("ImagingStudy"))
      return parseImagingStudy(xpp);
    else if (xpp.getName().equals("Immunization"))
      return parseImmunization(xpp);
    else if (xpp.getName().equals("ImmunizationRecommendation"))
      return parseImmunizationRecommendation(xpp);
    else if (xpp.getName().equals("ImplementationGuide"))
      return parseImplementationGuide(xpp);
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
    else if (xpp.getName().equals("NamingSystem"))
      return parseNamingSystem(xpp);
    else if (xpp.getName().equals("NutritionOrder"))
      return parseNutritionOrder(xpp);
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
    else if (xpp.getName().equals("Patient"))
      return parsePatient(xpp);
    else if (xpp.getName().equals("PaymentNotice"))
      return parsePaymentNotice(xpp);
    else if (xpp.getName().equals("PaymentReconciliation"))
      return parsePaymentReconciliation(xpp);
    else if (xpp.getName().equals("Person"))
      return parsePerson(xpp);
    else if (xpp.getName().equals("Practitioner"))
      return parsePractitioner(xpp);
    else if (xpp.getName().equals("Procedure"))
      return parseProcedure(xpp);
    else if (xpp.getName().equals("ProcedureRequest"))
      return parseProcedureRequest(xpp);
    else if (xpp.getName().equals("ProcessRequest"))
      return parseProcessRequest(xpp);
    else if (xpp.getName().equals("ProcessResponse"))
      return parseProcessResponse(xpp);
    else if (xpp.getName().equals("Provenance"))
      return parseProvenance(xpp);
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
    else if (xpp.getName().equals("Schedule"))
      return parseSchedule(xpp);
    else if (xpp.getName().equals("SearchParameter"))
      return parseSearchParameter(xpp);
    else if (xpp.getName().equals("Slot"))
      return parseSlot(xpp);
    else if (xpp.getName().equals("Specimen"))
      return parseSpecimen(xpp);
    else if (xpp.getName().equals("StructureDefinition"))
      return parseStructureDefinition(xpp);
    else if (xpp.getName().equals("Subscription"))
      return parseSubscription(xpp);
    else if (xpp.getName().equals("Substance"))
      return parseSubstance(xpp);
    else if (xpp.getName().equals("Supply"))
      return parseSupply(xpp);
    else if (xpp.getName().equals("SupplyDelivery"))
      return parseSupplyDelivery(xpp);
    else if (xpp.getName().equals("SupplyRequest"))
      return parseSupplyRequest(xpp);
    else if (xpp.getName().equals("TestScript"))
      return parseTestScript(xpp);
    else if (xpp.getName().equals("ValueSet"))
      return parseValueSet(xpp);
    else if (xpp.getName().equals("VisionPrescription"))
      return parseVisionPrescription(xpp);
    else if (xpp.getName().equals("Binary"))
      return parseBinary(xpp);
    throw new Exception("Unknown resource type "+xpp.getName()+"");
  }

  protected Type parseType(String prefix, XmlPullParser xpp) throws Exception {
    if (xpp.getName().equals(prefix+"integer"))
      return parseInteger(xpp);
    else if (xpp.getName().equals(prefix+"dateTime"))
      return parseDateTime(xpp);
    else if (xpp.getName().equals(prefix+"unsignedInt"))
      return parseUnsignedInt(xpp);
    else if (xpp.getName().equals(prefix+"code"))
      return parseCode(xpp);
    else if (xpp.getName().equals(prefix+"date"))
      return parseDate(xpp);
    else if (xpp.getName().equals(prefix+"decimal"))
      return parseDecimal(xpp);
    else if (xpp.getName().equals(prefix+"uri"))
      return parseUri(xpp);
    else if (xpp.getName().equals(prefix+"id"))
      return parseId(xpp);
    else if (xpp.getName().equals(prefix+"base64Binary"))
      return parseBase64Binary(xpp);
    else if (xpp.getName().equals(prefix+"time"))
      return parseTime(xpp);
    else if (xpp.getName().equals(prefix+"oid"))
      return parseOid(xpp);
    else if (xpp.getName().equals(prefix+"positiveInt"))
      return parsePositiveInt(xpp);
    else if (xpp.getName().equals(prefix+"string"))
      return parseString(xpp);
    else if (xpp.getName().equals(prefix+"boolean"))
      return parseBoolean(xpp);
    else if (xpp.getName().equals(prefix+"uuid"))
      return parseUuid(xpp);
    else if (xpp.getName().equals(prefix+"instant"))
      return parseInstant(xpp);
    else if (xpp.getName().equals(prefix+"Extension"))
      return parseExtension(xpp);
    else if (xpp.getName().equals(prefix+"Narrative"))
      return parseNarrative(xpp);
    else if (xpp.getName().equals(prefix+"Period"))
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
    else if (xpp.getName().equals(prefix+"Annotation"))
      return parseAnnotation(xpp);
    else if (xpp.getName().equals(prefix+"SampledData"))
      return parseSampledData(xpp);
    else if (xpp.getName().equals(prefix+"Reference"))
      return parseReference(xpp);
    else if (xpp.getName().equals(prefix+"CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (xpp.getName().equals(prefix+"Identifier"))
      return parseIdentifier(xpp);
    else if (xpp.getName().equals(prefix+"Signature"))
      return parseSignature(xpp);
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
    else if (xpp.getName().equals(prefix+"ElementDefinition"))
      return parseElementDefinition(xpp);
    else if (xpp.getName().equals(prefix+"Timing"))
      return parseTiming(xpp);
    else if (xpp.getName().equals(prefix+"Address"))
      return parseAddress(xpp);
    else if (xpp.getName().equals(prefix+"HumanName"))
      return parseHumanName(xpp);
    else if (xpp.getName().equals(prefix+"Meta"))
      return parseMeta(xpp);
    else if (xpp.getName().equals(prefix+"ContactPoint"))
      return parseContactPoint(xpp);
    else if (xpp.getName().equals(prefix+"Integer"))
      return parseInteger(xpp);
    else if (xpp.getName().equals(prefix+"DateTime"))
      return parseDateTime(xpp);
    else if (xpp.getName().equals(prefix+"UnsignedInt"))
      return parseUnsignedInt(xpp);
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
    else if (xpp.getName().equals(prefix+"PositiveInt"))
      return parsePositiveInt(xpp);
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

  protected Type parseType(XmlPullParser xpp, String type) throws Exception {
    if (type.equals("integer"))
      return parseInteger(xpp);
    else if (type.equals("dateTime"))
      return parseDateTime(xpp);
    else if (type.equals("unsignedInt"))
      return parseUnsignedInt(xpp);
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
    else if (type.equals("positiveInt"))
      return parsePositiveInt(xpp);
    else if (type.equals("string"))
      return parseString(xpp);
    else if (type.equals("boolean"))
      return parseBoolean(xpp);
    else if (type.equals("uuid"))
      return parseUuid(xpp);
    else if (type.equals("instant"))
      return parseInstant(xpp);
    else if (type.equals("Extension"))
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
    else if (type.equals("Annotation"))
      return parseAnnotation(xpp);
    else if (type.equals("SampledData"))
      return parseSampledData(xpp);
    else if (type.equals("Reference"))
      return parseReference(xpp);
    else if (type.equals("CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (type.equals("Identifier"))
      return parseIdentifier(xpp);
    else if (type.equals("Signature"))
      return parseSignature(xpp);
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
    else if (type.equals("ElementDefinition"))
      return parseElementDefinition(xpp);
    else if (type.equals("Timing"))
      return parseTiming(xpp);
    else if (type.equals("Address"))
      return parseAddress(xpp);
    else if (type.equals("HumanName"))
      return parseHumanName(xpp);
    else if (type.equals("Meta"))
      return parseMeta(xpp);
    else if (type.equals("ContactPoint"))
      return parseContactPoint(xpp);
    throw new Exception("Unknown type "+type);
  }

  public Base parseFragment(XmlPullParser xpp, String type) throws Exception {
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
    else if (type.equals("Annotation"))
      return parseAnnotation(xpp);
    else if (type.equals("SampledData"))
      return parseSampledData(xpp);
    else if (type.equals("Reference"))
      return parseReference(xpp);
    else if (type.equals("CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (type.equals("Identifier"))
      return parseIdentifier(xpp);
    else if (type.equals("Signature"))
      return parseSignature(xpp);
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
    else if (type.equals("ElementDefinition"))
      return parseElementDefinition(xpp);
    else if (type.equals("Timing"))
      return parseTiming(xpp);
    else if (type.equals("Address"))
      return parseAddress(xpp);
    else if (type.equals("HumanName"))
      return parseHumanName(xpp);
    else if (type.equals("Meta"))
      return parseMeta(xpp);
    else if (type.equals("ContactPoint"))
      return parseContactPoint(xpp);
    else if (type.equals("Parameters"))
      return parseParameters(xpp);
    else if (type.equals("AllergyIntolerance"))
      return parseAllergyIntolerance(xpp);
    else if (type.equals("Appointment"))
      return parseAppointment(xpp);
    else if (type.equals("AppointmentResponse"))
      return parseAppointmentResponse(xpp);
    else if (type.equals("AuditEvent"))
      return parseAuditEvent(xpp);
    else if (type.equals("Basic"))
      return parseBasic(xpp);
    else if (type.equals("Binary"))
      return parseBinary(xpp);
    else if (type.equals("BodySite"))
      return parseBodySite(xpp);
    else if (type.equals("Bundle"))
      return parseBundle(xpp);
    else if (type.equals("CarePlan"))
      return parseCarePlan(xpp);
    else if (type.equals("Claim"))
      return parseClaim(xpp);
    else if (type.equals("ClaimResponse"))
      return parseClaimResponse(xpp);
    else if (type.equals("ClinicalImpression"))
      return parseClinicalImpression(xpp);
    else if (type.equals("Communication"))
      return parseCommunication(xpp);
    else if (type.equals("CommunicationRequest"))
      return parseCommunicationRequest(xpp);
    else if (type.equals("Composition"))
      return parseComposition(xpp);
    else if (type.equals("ConceptMap"))
      return parseConceptMap(xpp);
    else if (type.equals("Condition"))
      return parseCondition(xpp);
    else if (type.equals("Conformance"))
      return parseConformance(xpp);
    else if (type.equals("Contract"))
      return parseContract(xpp);
    else if (type.equals("Contraindication"))
      return parseContraindication(xpp);
    else if (type.equals("Coverage"))
      return parseCoverage(xpp);
    else if (type.equals("DataElement"))
      return parseDataElement(xpp);
    else if (type.equals("Device"))
      return parseDevice(xpp);
    else if (type.equals("DeviceComponent"))
      return parseDeviceComponent(xpp);
    else if (type.equals("DeviceMetric"))
      return parseDeviceMetric(xpp);
    else if (type.equals("DeviceUseRequest"))
      return parseDeviceUseRequest(xpp);
    else if (type.equals("DeviceUseStatement"))
      return parseDeviceUseStatement(xpp);
    else if (type.equals("DiagnosticOrder"))
      return parseDiagnosticOrder(xpp);
    else if (type.equals("DiagnosticReport"))
      return parseDiagnosticReport(xpp);
    else if (type.equals("DocumentManifest"))
      return parseDocumentManifest(xpp);
    else if (type.equals("DocumentReference"))
      return parseDocumentReference(xpp);
    else if (type.equals("EligibilityRequest"))
      return parseEligibilityRequest(xpp);
    else if (type.equals("EligibilityResponse"))
      return parseEligibilityResponse(xpp);
    else if (type.equals("Encounter"))
      return parseEncounter(xpp);
    else if (type.equals("EnrollmentRequest"))
      return parseEnrollmentRequest(xpp);
    else if (type.equals("EnrollmentResponse"))
      return parseEnrollmentResponse(xpp);
    else if (type.equals("EpisodeOfCare"))
      return parseEpisodeOfCare(xpp);
    else if (type.equals("ExplanationOfBenefit"))
      return parseExplanationOfBenefit(xpp);
    else if (type.equals("FamilyMemberHistory"))
      return parseFamilyMemberHistory(xpp);
    else if (type.equals("Flag"))
      return parseFlag(xpp);
    else if (type.equals("Goal"))
      return parseGoal(xpp);
    else if (type.equals("Group"))
      return parseGroup(xpp);
    else if (type.equals("HealthcareService"))
      return parseHealthcareService(xpp);
    else if (type.equals("ImagingObjectSelection"))
      return parseImagingObjectSelection(xpp);
    else if (type.equals("ImagingStudy"))
      return parseImagingStudy(xpp);
    else if (type.equals("Immunization"))
      return parseImmunization(xpp);
    else if (type.equals("ImmunizationRecommendation"))
      return parseImmunizationRecommendation(xpp);
    else if (type.equals("ImplementationGuide"))
      return parseImplementationGuide(xpp);
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
    else if (type.equals("NamingSystem"))
      return parseNamingSystem(xpp);
    else if (type.equals("NutritionOrder"))
      return parseNutritionOrder(xpp);
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
    else if (type.equals("Patient"))
      return parsePatient(xpp);
    else if (type.equals("PaymentNotice"))
      return parsePaymentNotice(xpp);
    else if (type.equals("PaymentReconciliation"))
      return parsePaymentReconciliation(xpp);
    else if (type.equals("Person"))
      return parsePerson(xpp);
    else if (type.equals("Practitioner"))
      return parsePractitioner(xpp);
    else if (type.equals("Procedure"))
      return parseProcedure(xpp);
    else if (type.equals("ProcedureRequest"))
      return parseProcedureRequest(xpp);
    else if (type.equals("ProcessRequest"))
      return parseProcessRequest(xpp);
    else if (type.equals("ProcessResponse"))
      return parseProcessResponse(xpp);
    else if (type.equals("Provenance"))
      return parseProvenance(xpp);
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
    else if (type.equals("Schedule"))
      return parseSchedule(xpp);
    else if (type.equals("SearchParameter"))
      return parseSearchParameter(xpp);
    else if (type.equals("Slot"))
      return parseSlot(xpp);
    else if (type.equals("Specimen"))
      return parseSpecimen(xpp);
    else if (type.equals("StructureDefinition"))
      return parseStructureDefinition(xpp);
    else if (type.equals("Subscription"))
      return parseSubscription(xpp);
    else if (type.equals("Substance"))
      return parseSubstance(xpp);
    else if (type.equals("Supply"))
      return parseSupply(xpp);
    else if (type.equals("SupplyDelivery"))
      return parseSupplyDelivery(xpp);
    else if (type.equals("SupplyRequest"))
      return parseSupplyRequest(xpp);
    else if (type.equals("TestScript"))
      return parseTestScript(xpp);
    else if (type.equals("ValueSet"))
      return parseValueSet(xpp);
    else if (type.equals("VisionPrescription"))
      return parseVisionPrescription(xpp);
    else if (type.equals("integer"))
      return parseInteger(xpp);
    else if (type.equals("dateTime"))
      return parseDateTime(xpp);
    else if (type.equals("unsignedInt"))
      return parseUnsignedInt(xpp);
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
    else if (type.equals("positiveInt"))
      return parsePositiveInt(xpp);
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
    if (xpp.getName().equals(prefix+"Annotation"))
      return true;
    if (xpp.getName().equals(prefix+"SampledData"))
      return true;
    if (xpp.getName().equals(prefix+"Reference"))
      return true;
    if (xpp.getName().equals(prefix+"CodeableConcept"))
      return true;
    if (xpp.getName().equals(prefix+"Identifier"))
      return true;
    if (xpp.getName().equals(prefix+"Signature"))
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
    if (xpp.getName().equals(prefix+"ElementDefinition"))
      return true;
    if (xpp.getName().equals(prefix+"Timing"))
      return true;
    if (xpp.getName().equals(prefix+"Address"))
      return true;
    if (xpp.getName().equals(prefix+"HumanName"))
      return true;
    if (xpp.getName().equals(prefix+"Meta"))
      return true;
    if (xpp.getName().equals(prefix+"ContactPoint"))
      return true;
    if (xpp.getName().equals(prefix+"Parameters"))
      return true;
    if (xpp.getName().equals(prefix+"AllergyIntolerance"))
      return true;
    if (xpp.getName().equals(prefix+"Appointment"))
      return true;
    if (xpp.getName().equals(prefix+"AppointmentResponse"))
      return true;
    if (xpp.getName().equals(prefix+"AuditEvent"))
      return true;
    if (xpp.getName().equals(prefix+"Basic"))
      return true;
    if (xpp.getName().equals(prefix+"Binary"))
      return true;
    if (xpp.getName().equals(prefix+"BodySite"))
      return true;
    if (xpp.getName().equals(prefix+"Bundle"))
      return true;
    if (xpp.getName().equals(prefix+"CarePlan"))
      return true;
    if (xpp.getName().equals(prefix+"Claim"))
      return true;
    if (xpp.getName().equals(prefix+"ClaimResponse"))
      return true;
    if (xpp.getName().equals(prefix+"ClinicalImpression"))
      return true;
    if (xpp.getName().equals(prefix+"Communication"))
      return true;
    if (xpp.getName().equals(prefix+"CommunicationRequest"))
      return true;
    if (xpp.getName().equals(prefix+"Composition"))
      return true;
    if (xpp.getName().equals(prefix+"ConceptMap"))
      return true;
    if (xpp.getName().equals(prefix+"Condition"))
      return true;
    if (xpp.getName().equals(prefix+"Conformance"))
      return true;
    if (xpp.getName().equals(prefix+"Contract"))
      return true;
    if (xpp.getName().equals(prefix+"Contraindication"))
      return true;
    if (xpp.getName().equals(prefix+"Coverage"))
      return true;
    if (xpp.getName().equals(prefix+"DataElement"))
      return true;
    if (xpp.getName().equals(prefix+"Device"))
      return true;
    if (xpp.getName().equals(prefix+"DeviceComponent"))
      return true;
    if (xpp.getName().equals(prefix+"DeviceMetric"))
      return true;
    if (xpp.getName().equals(prefix+"DeviceUseRequest"))
      return true;
    if (xpp.getName().equals(prefix+"DeviceUseStatement"))
      return true;
    if (xpp.getName().equals(prefix+"DiagnosticOrder"))
      return true;
    if (xpp.getName().equals(prefix+"DiagnosticReport"))
      return true;
    if (xpp.getName().equals(prefix+"DocumentManifest"))
      return true;
    if (xpp.getName().equals(prefix+"DocumentReference"))
      return true;
    if (xpp.getName().equals(prefix+"EligibilityRequest"))
      return true;
    if (xpp.getName().equals(prefix+"EligibilityResponse"))
      return true;
    if (xpp.getName().equals(prefix+"Encounter"))
      return true;
    if (xpp.getName().equals(prefix+"EnrollmentRequest"))
      return true;
    if (xpp.getName().equals(prefix+"EnrollmentResponse"))
      return true;
    if (xpp.getName().equals(prefix+"EpisodeOfCare"))
      return true;
    if (xpp.getName().equals(prefix+"ExplanationOfBenefit"))
      return true;
    if (xpp.getName().equals(prefix+"FamilyMemberHistory"))
      return true;
    if (xpp.getName().equals(prefix+"Flag"))
      return true;
    if (xpp.getName().equals(prefix+"Goal"))
      return true;
    if (xpp.getName().equals(prefix+"Group"))
      return true;
    if (xpp.getName().equals(prefix+"HealthcareService"))
      return true;
    if (xpp.getName().equals(prefix+"ImagingObjectSelection"))
      return true;
    if (xpp.getName().equals(prefix+"ImagingStudy"))
      return true;
    if (xpp.getName().equals(prefix+"Immunization"))
      return true;
    if (xpp.getName().equals(prefix+"ImmunizationRecommendation"))
      return true;
    if (xpp.getName().equals(prefix+"ImplementationGuide"))
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
    if (xpp.getName().equals(prefix+"NamingSystem"))
      return true;
    if (xpp.getName().equals(prefix+"NutritionOrder"))
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
    if (xpp.getName().equals(prefix+"Patient"))
      return true;
    if (xpp.getName().equals(prefix+"PaymentNotice"))
      return true;
    if (xpp.getName().equals(prefix+"PaymentReconciliation"))
      return true;
    if (xpp.getName().equals(prefix+"Person"))
      return true;
    if (xpp.getName().equals(prefix+"Practitioner"))
      return true;
    if (xpp.getName().equals(prefix+"Procedure"))
      return true;
    if (xpp.getName().equals(prefix+"ProcedureRequest"))
      return true;
    if (xpp.getName().equals(prefix+"ProcessRequest"))
      return true;
    if (xpp.getName().equals(prefix+"ProcessResponse"))
      return true;
    if (xpp.getName().equals(prefix+"Provenance"))
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
    if (xpp.getName().equals(prefix+"Schedule"))
      return true;
    if (xpp.getName().equals(prefix+"SearchParameter"))
      return true;
    if (xpp.getName().equals(prefix+"Slot"))
      return true;
    if (xpp.getName().equals(prefix+"Specimen"))
      return true;
    if (xpp.getName().equals(prefix+"StructureDefinition"))
      return true;
    if (xpp.getName().equals(prefix+"Subscription"))
      return true;
    if (xpp.getName().equals(prefix+"Substance"))
      return true;
    if (xpp.getName().equals(prefix+"Supply"))
      return true;
    if (xpp.getName().equals(prefix+"SupplyDelivery"))
      return true;
    if (xpp.getName().equals(prefix+"SupplyRequest"))
      return true;
    if (xpp.getName().equals(prefix+"TestScript"))
      return true;
    if (xpp.getName().equals(prefix+"ValueSet"))
      return true;
    if (xpp.getName().equals(prefix+"VisionPrescription"))
      return true;
    if (xpp.getName().equals(prefix+"Integer"))
      return true;
    if (xpp.getName().equals(prefix+"DateTime"))
      return true;
    if (xpp.getName().equals(prefix+"UnsignedInt"))
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
    if (xpp.getName().equals(prefix+"PositiveInt"))
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
  protected void composeElementElements(Element element) throws Exception {
    for (Extension e : element.getExtension()) {
      composeExtension("extension", e);
    }
  }

  protected void composeBackboneElements(BackboneElement element) throws Exception {
    composeElementElements(element);
    for (Extension e : element.getModifierExtension()) {
      composeExtension("modifierExtension", e);
    }
  }

  protected <E extends Enum<E>> void composeEnumeration(String name, Enumeration<E> value, EnumFactory e) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", e.toCode(value.getValue()));
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeInteger(String name, IntegerType value) throws Exception {
    if (value != null) { // integer
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeDateTime(String name, DateTimeType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {// dateTime
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeUnsignedInt(String name, UnsignedIntType value) throws Exception {
    if (value != null) { // unsignedInt
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeCode(String name, CodeType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || !Utilities.noString(value.getValue()))) {// code
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeDate(String name, DateType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {// date
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeDecimal(String name, DecimalType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {// decimal
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeUri(String name, UriType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {// uri
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeId(String name, IdType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || !Utilities.noString(value.getValue()))) {// id
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeBase64Binary(String name, Base64BinaryType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {// base64Binary
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeTime(String name, TimeType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || !Utilities.noString(value.getValue()))) {// time
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeOid(String name, OidType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || !Utilities.noString(value.getValue()))) {// oid
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composePositiveInt(String name, PositiveIntType value) throws Exception {
    if (value != null) { // positiveInt
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeString(String name, StringType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || !Utilities.noString(value.getValue()))) {// string
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeBoolean(String name, BooleanType value) throws Exception {
    if (value != null) { // boolean
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeUuid(String name, UuidType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || !Utilities.noString(value.getValue()))) {// uuid
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeInstant(String name, InstantType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getId()) || ExtensionHelper.hasExtensions(value) || value.getValue() != null)) {// instant
      composeElementAttributes(value);
      if (value.asStringValue() != null) 
        xml.attribute("value", value.asStringValue());
        
      xml.enter(FHIR_NS, name);
      composeElementElements(value);
      xml.exit(FHIR_NS, name);
    }    
  }    

  protected void composeExtension(String name, Extension element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      if (element.hasUrlElement())
        xml.attribute("url", element.getUrlElement().getValue());
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Narrative.NarrativeStatusEnumFactory());
      if (element.hasDiv()) {
        composeXhtml("div", element.getDiv());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasStartElement()) {
        composeDateTime("start", element.getStartElement());
      }
      if (element.hasEndElement()) {
        composeDateTime("end", element.getEndElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      if (element.hasUserSelectedElement()) {
        composeBoolean("userSelected", element.getUserSelectedElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeRange(String name, Range element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasLow()) {
        composeQuantity("low", element.getLow());
      }
      if (element.hasHigh()) {
        composeQuantity("high", element.getHigh());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuantity(String name, Quantity element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      if (element.hasComparatorElement())
        composeEnumeration("comparator", element.getComparatorElement(), new Quantity.QuantityComparatorEnumFactory());
      if (element.hasUnitsElement()) {
        composeString("units", element.getUnitsElement());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasContentTypeElement()) {
        composeCode("contentType", element.getContentTypeElement());
      }
      if (element.hasLanguageElement()) {
        composeCode("language", element.getLanguageElement());
      }
      if (element.hasDataElement()) {
        composeBase64Binary("data", element.getDataElement());
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasSizeElement()) {
        composeUnsignedInt("size", element.getSizeElement());
      }
      if (element.hasHashElement()) {
        composeBase64Binary("hash", element.getHashElement());
      }
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasCreationElement()) {
        composeDateTime("creation", element.getCreationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeRatio(String name, Ratio element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasNumerator()) {
        composeQuantity("numerator", element.getNumerator());
      }
      if (element.hasDenominator()) {
        composeQuantity("denominator", element.getDenominator());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAnnotation(String name, Annotation element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasAuthor()) {
        composeType("author", element.getAuthor());
      }      if (element.hasTimeElement()) {
        composeDateTime("time", element.getTimeElement());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSampledData(String name, SampledData element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasOrigin()) {
        composeQuantity("origin", element.getOrigin());
      }
      if (element.hasPeriodElement()) {
        composeDecimal("period", element.getPeriodElement());
      }
      if (element.hasFactorElement()) {
        composeDecimal("factor", element.getFactorElement());
      }
      if (element.hasLowerLimitElement()) {
        composeDecimal("lowerLimit", element.getLowerLimitElement());
      }
      if (element.hasUpperLimitElement()) {
        composeDecimal("upperLimit", element.getUpperLimitElement());
      }
      if (element.hasDimensionsElement()) {
        composePositiveInt("dimensions", element.getDimensionsElement());
      }
      if (element.hasDataElement()) {
        composeString("data", element.getDataElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeReference(String name, Reference element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasReferenceElement()) {
        composeString("reference", element.getReferenceElement());
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCodeableConcept(String name, CodeableConcept element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasCoding()) { 
        for (Coding e : element.getCoding()) 
          composeCoding("coding", e);
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasUseElement())
        composeEnumeration("use", element.getUseElement(), new Identifier.IdentifierUseEnumFactory());
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasValueElement()) {
        composeString("value", element.getValueElement());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasAssigner()) {
        composeReference("assigner", element.getAssigner());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSignature(String name, Signature element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasType()) { 
        for (Coding e : element.getType()) 
          composeCoding("type", e);
      }
      if (element.hasWhenElement()) {
        composeInstant("when", element.getWhenElement());
      }
      if (element.hasWho()) {
        composeType("who", element.getWho());
      }      if (element.hasBlobElement()) {
        composeBase64Binary("blob", element.getBlobElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      if (element.hasComparatorElement())
        composeEnumeration("comparator", element.getComparatorElement(), new Age.QuantityComparatorEnumFactory());
      if (element.hasUnitsElement()) {
        composeString("units", element.getUnitsElement());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      if (element.hasComparatorElement())
        composeEnumeration("comparator", element.getComparatorElement(), new Count.QuantityComparatorEnumFactory());
      if (element.hasUnitsElement()) {
        composeString("units", element.getUnitsElement());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      if (element.hasComparatorElement())
        composeEnumeration("comparator", element.getComparatorElement(), new Money.QuantityComparatorEnumFactory());
      if (element.hasUnitsElement()) {
        composeString("units", element.getUnitsElement());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      if (element.hasComparatorElement())
        composeEnumeration("comparator", element.getComparatorElement(), new Distance.QuantityComparatorEnumFactory());
      if (element.hasUnitsElement()) {
        composeString("units", element.getUnitsElement());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      if (element.hasComparatorElement())
        composeEnumeration("comparator", element.getComparatorElement(), new Duration.QuantityComparatorEnumFactory());
      if (element.hasUnitsElement()) {
        composeString("units", element.getUnitsElement());
      }
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeElementDefinition(String name, ElementDefinition element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasPathElement()) {
        composeString("path", element.getPathElement());
      }
        if (element.hasRepresentation()) 
          for (Enumeration<ElementDefinition.PropertyRepresentation> e : element.getRepresentation()) 
            composeEnumeration("representation", e, new ElementDefinition.PropertyRepresentationEnumFactory());
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasLabelElement()) {
        composeString("label", element.getLabelElement());
      }
      if (element.hasCode()) { 
        for (Coding e : element.getCode()) 
          composeCoding("code", e);
      }
      if (element.hasSlicing()) {
        composeElementDefinitionElementDefinitionSlicingComponent("slicing", element.getSlicing());
      }
      if (element.hasShortElement()) {
        composeString("short", element.getShortElement());
      }
      if (element.hasDefinitionElement()) {
        composeString("definition", element.getDefinitionElement());
      }
      if (element.hasCommentsElement()) {
        composeString("comments", element.getCommentsElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasAlias()) { 
        for (StringType e : element.getAlias()) 
          composeString("alias", e);
      }
      if (element.hasMinElement()) {
        composeInteger("min", element.getMinElement());
      }
      if (element.hasMaxElement()) {
        composeString("max", element.getMaxElement());
      }
      if (element.hasType()) { 
        for (ElementDefinition.TypeRefComponent e : element.getType()) 
          composeElementDefinitionTypeRefComponent("type", e);
      }
      if (element.hasNameReferenceElement()) {
        composeString("nameReference", element.getNameReferenceElement());
      }
      if (element.hasDefaultValue()) {
        composeType("defaultValue", element.getDefaultValue());
      }      if (element.hasMeaningWhenMissingElement()) {
        composeString("meaningWhenMissing", element.getMeaningWhenMissingElement());
      }
      if (element.hasFixed()) {
        composeType("fixed", element.getFixed());
      }      if (element.hasPattern()) {
        composeType("pattern", element.getPattern());
      }      if (element.hasExample()) {
        composeType("example", element.getExample());
      }      if (element.hasMaxLengthElement()) {
        composeInteger("maxLength", element.getMaxLengthElement());
      }
      if (element.hasCondition()) { 
        for (IdType e : element.getCondition()) 
          composeId("condition", e);
      }
      if (element.hasConstraint()) { 
        for (ElementDefinition.ElementDefinitionConstraintComponent e : element.getConstraint()) 
          composeElementDefinitionElementDefinitionConstraintComponent("constraint", e);
      }
      if (element.hasMustSupportElement()) {
        composeBoolean("mustSupport", element.getMustSupportElement());
      }
      if (element.hasIsModifierElement()) {
        composeBoolean("isModifier", element.getIsModifierElement());
      }
      if (element.hasIsSummaryElement()) {
        composeBoolean("isSummary", element.getIsSummaryElement());
      }
      if (element.hasBinding()) {
        composeElementDefinitionElementDefinitionBindingComponent("binding", element.getBinding());
      }
      if (element.hasMapping()) { 
        for (ElementDefinition.ElementDefinitionMappingComponent e : element.getMapping()) 
          composeElementDefinitionElementDefinitionMappingComponent("mapping", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeElementDefinitionElementDefinitionSlicingComponent(String name, ElementDefinition.ElementDefinitionSlicingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasDiscriminator()) { 
        for (StringType e : element.getDiscriminator()) 
          composeString("discriminator", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasOrderedElement()) {
        composeBoolean("ordered", element.getOrderedElement());
      }
      if (element.hasRulesElement())
        composeEnumeration("rules", element.getRulesElement(), new ElementDefinition.SlicingRulesEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeElementDefinitionTypeRefComponent(String name, ElementDefinition.TypeRefComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasProfile()) { 
        for (UriType e : element.getProfile()) 
          composeUri("profile", e);
      }
        if (element.hasAggregation()) 
          for (Enumeration<ElementDefinition.AggregationMode> e : element.getAggregation()) 
            composeEnumeration("aggregation", e, new ElementDefinition.AggregationModeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeElementDefinitionElementDefinitionConstraintComponent(String name, ElementDefinition.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasKeyElement()) {
        composeId("key", element.getKeyElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasSeverityElement())
        composeEnumeration("severity", element.getSeverityElement(), new ElementDefinition.ConstraintSeverityEnumFactory());
      if (element.hasHumanElement()) {
        composeString("human", element.getHumanElement());
      }
      if (element.hasXpathElement()) {
        composeString("xpath", element.getXpathElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeElementDefinitionElementDefinitionBindingComponent(String name, ElementDefinition.ElementDefinitionBindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasStrengthElement())
        composeEnumeration("strength", element.getStrengthElement(), new Enumerations.BindingStrengthEnumFactory());
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasValueSet()) {
        composeType("valueSet", element.getValueSet());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeElementDefinitionElementDefinitionMappingComponent(String name, ElementDefinition.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasIdentityElement()) {
        composeId("identity", element.getIdentityElement());
      }
      if (element.hasLanguageElement()) {
        composeCode("language", element.getLanguageElement());
      }
      if (element.hasMapElement()) {
        composeString("map", element.getMapElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTiming(String name, Timing element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasEvent()) { 
        for (DateTimeType e : element.getEvent()) 
          composeDateTime("event", e);
      }
      if (element.hasRepeat()) {
        composeTimingTimingRepeatComponent("repeat", element.getRepeat());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTimingTimingRepeatComponent(String name, Timing.TimingRepeatComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasBounds()) {
        composeType("bounds", element.getBounds());
      }      if (element.hasCountElement()) {
        composeInteger("count", element.getCountElement());
      }
      if (element.hasDurationElement()) {
        composeDecimal("duration", element.getDurationElement());
      }
      if (element.hasDurationMaxElement()) {
        composeDecimal("durationMax", element.getDurationMaxElement());
      }
      if (element.hasDurationUnitsElement())
        composeEnumeration("durationUnits", element.getDurationUnitsElement(), new Timing.UnitsOfTimeEnumFactory());
      if (element.hasFrequencyElement()) {
        composeInteger("frequency", element.getFrequencyElement());
      }
      if (element.hasFrequencyMaxElement()) {
        composeInteger("frequencyMax", element.getFrequencyMaxElement());
      }
      if (element.hasPeriodElement()) {
        composeDecimal("period", element.getPeriodElement());
      }
      if (element.hasPeriodMaxElement()) {
        composeDecimal("periodMax", element.getPeriodMaxElement());
      }
      if (element.hasPeriodUnitsElement())
        composeEnumeration("periodUnits", element.getPeriodUnitsElement(), new Timing.UnitsOfTimeEnumFactory());
      if (element.hasWhenElement())
        composeEnumeration("when", element.getWhenElement(), new Timing.EventTimingEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasUseElement())
        composeEnumeration("use", element.getUseElement(), new Address.AddressUseEnumFactory());
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Address.AddressTypeEnumFactory());
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasLine()) { 
        for (StringType e : element.getLine()) 
          composeString("line", e);
      }
      if (element.hasCityElement()) {
        composeString("city", element.getCityElement());
      }
      if (element.hasStateElement()) {
        composeString("state", element.getStateElement());
      }
      if (element.hasPostalCodeElement()) {
        composeString("postalCode", element.getPostalCodeElement());
      }
      if (element.hasCountryElement()) {
        composeString("country", element.getCountryElement());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasUseElement())
        composeEnumeration("use", element.getUseElement(), new HumanName.NameUseEnumFactory());
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasFamily()) { 
        for (StringType e : element.getFamily()) 
          composeString("family", e);
      }
      if (element.hasGiven()) { 
        for (StringType e : element.getGiven()) 
          composeString("given", e);
      }
      if (element.hasPrefix()) { 
        for (StringType e : element.getPrefix()) 
          composeString("prefix", e);
      }
      if (element.hasSuffix()) { 
        for (StringType e : element.getSuffix()) 
          composeString("suffix", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMeta(String name, Meta element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasVersionIdElement()) {
        composeId("versionId", element.getVersionIdElement());
      }
      if (element.hasLastUpdatedElement()) {
        composeInstant("lastUpdated", element.getLastUpdatedElement());
      }
      if (element.hasProfile()) { 
        for (UriType e : element.getProfile()) 
          composeUri("profile", e);
      }
      if (element.hasSecurity()) { 
        for (Coding e : element.getSecurity()) 
          composeCoding("security", e);
      }
      if (element.hasTag()) { 
        for (Coding e : element.getTag()) 
          composeCoding("tag", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContactPoint(String name, ContactPoint element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeElementElements(element);
      if (element.hasSystemElement())
        composeEnumeration("system", element.getSystemElement(), new ContactPoint.ContactPointSystemEnumFactory());
      if (element.hasValueElement()) {
        composeString("value", element.getValueElement());
      }
      if (element.hasUseElement())
        composeEnumeration("use", element.getUseElement(), new ContactPoint.ContactPointUseEnumFactory());
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeParameters(String name, Parameters element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeResourceElements(element);
      if (element.hasParameter()) { 
        for (Parameters.ParametersParameterComponent e : element.getParameter()) 
          composeParametersParametersParameterComponent("parameter", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeParametersParametersParameterComponent(String name, Parameters.ParametersParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      if (element.hasResource()) {
        xml.enter(FHIR_NS, "resource");
        composeResource(element.getResource());
        xml.exit(FHIR_NS, "resource");
      }
      if (element.hasPart()) { 
        for (Parameters.ParametersParameterComponent e : element.getPart()) 
          composeParametersParametersParameterComponent("part", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeResourceAttributes(Resource element) throws Exception {
  }

  protected void composeResourceElements(Resource element) throws Exception {
      if (element.hasIdElement()) {
        composeId("id", element.getIdElement());
      }
      if (element.hasMeta()) {
        composeMeta("meta", element.getMeta());
      }
      if (element.hasImplicitRulesElement()) {
        composeUri("implicitRules", element.getImplicitRulesElement());
      }
      if (element.hasLanguageElement()) {
        composeCode("language", element.getLanguageElement());
      }
  }

  protected void composeDomainResourceAttributes(DomainResource element) throws Exception {
    composeResourceAttributes(element);
  }

  protected void composeDomainResourceElements(DomainResource element) throws Exception {
    composeResourceElements(element);
      if (element.hasText()) {
        composeNarrative("text", element.getText());
      }
      if (element.hasContained()) { 
        for (Resource e : element.getContained()) 
        {
          xml.enter(FHIR_NS, "contained");
          composeResource(e);
          xml.exit(FHIR_NS, "contained");
        }
      }
      if (element.hasExtension()) 
        for (Extension e : element.getExtension()) 
          composeExtension("extension", e);
      if (element.hasModifierExtension()) { 
        for (Extension e : element.getModifierExtension()) 
          composeExtension("modifierExtension", e);
      }
  }

  protected void composeAllergyIntolerance(String name, AllergyIntolerance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRecordedDateElement()) {
        composeDateTime("recordedDate", element.getRecordedDateElement());
      }
      if (element.hasRecorder()) {
        composeReference("recorder", element.getRecorder());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasReporter()) {
        composeReference("reporter", element.getReporter());
      }
      if (element.hasSubstance()) {
        composeCodeableConcept("substance", element.getSubstance());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new AllergyIntolerance.AllergyIntoleranceStatusEnumFactory());
      if (element.hasCriticalityElement())
        composeEnumeration("criticality", element.getCriticalityElement(), new AllergyIntolerance.AllergyIntoleranceCriticalityEnumFactory());
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new AllergyIntolerance.AllergyIntoleranceTypeEnumFactory());
      if (element.hasCategoryElement())
        composeEnumeration("category", element.getCategoryElement(), new AllergyIntolerance.AllergyIntoleranceCategoryEnumFactory());
      if (element.hasLastOccurenceElement()) {
        composeDateTime("lastOccurence", element.getLastOccurenceElement());
      }
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      if (element.hasEvent()) { 
        for (AllergyIntolerance.AllergyIntoleranceEventComponent e : element.getEvent()) 
          composeAllergyIntoleranceAllergyIntoleranceEventComponent("event", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAllergyIntoleranceAllergyIntoleranceEventComponent(String name, AllergyIntolerance.AllergyIntoleranceEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSubstance()) {
        composeCodeableConcept("substance", element.getSubstance());
      }
      if (element.hasCertaintyElement())
        composeEnumeration("certainty", element.getCertaintyElement(), new AllergyIntolerance.AllergyIntoleranceCertaintyEnumFactory());
      if (element.hasManifestation()) { 
        for (CodeableConcept e : element.getManifestation()) 
          composeCodeableConcept("manifestation", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasOnsetElement()) {
        composeDateTime("onset", element.getOnsetElement());
      }
      if (element.hasDuration()) {
        composeDuration("duration", element.getDuration());
      }
      if (element.hasSeverityElement())
        composeEnumeration("severity", element.getSeverityElement(), new AllergyIntolerance.AllergyIntoleranceSeverityEnumFactory());
      if (element.hasExposureRoute()) {
        composeCodeableConcept("exposureRoute", element.getExposureRoute());
      }
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAppointment(String name, Appointment element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Appointment.AppointmentStatusEnumFactory());
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasReason()) {
        composeCodeableConcept("reason", element.getReason());
      }
      if (element.hasPriorityElement()) {
        composeUnsignedInt("priority", element.getPriorityElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasStartElement()) {
        composeInstant("start", element.getStartElement());
      }
      if (element.hasEndElement()) {
        composeInstant("end", element.getEndElement());
      }
      if (element.hasSlot()) { 
        for (Reference e : element.getSlot()) 
          composeReference("slot", e);
      }
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      if (element.hasOrder()) {
        composeReference("order", element.getOrder());
      }
      if (element.hasParticipant()) { 
        for (Appointment.AppointmentParticipantComponent e : element.getParticipant()) 
          composeAppointmentAppointmentParticipantComponent("participant", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAppointmentAppointmentParticipantComponent(String name, Appointment.AppointmentParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) { 
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept("type", e);
      }
      if (element.hasActor()) {
        composeReference("actor", element.getActor());
      }
      if (element.hasRequiredElement())
        composeEnumeration("required", element.getRequiredElement(), new Appointment.ParticipantRequiredEnumFactory());
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Appointment.ParticipationStatusEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAppointmentResponse(String name, AppointmentResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasAppointment()) {
        composeReference("appointment", element.getAppointment());
      }
      if (element.hasParticipantType()) { 
        for (CodeableConcept e : element.getParticipantType()) 
          composeCodeableConcept("participantType", e);
      }
      if (element.hasActor()) {
        composeReference("actor", element.getActor());
      }
      if (element.hasParticipantStatusElement())
        composeEnumeration("participantStatus", element.getParticipantStatusElement(), new AppointmentResponse.ParticipantStatusEnumFactory());
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      if (element.hasStartElement()) {
        composeInstant("start", element.getStartElement());
      }
      if (element.hasEndElement()) {
        composeInstant("end", element.getEndElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEvent(String name, AuditEvent element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasEvent()) {
        composeAuditEventAuditEventEventComponent("event", element.getEvent());
      }
      if (element.hasParticipant()) { 
        for (AuditEvent.AuditEventParticipantComponent e : element.getParticipant()) 
          composeAuditEventAuditEventParticipantComponent("participant", e);
      }
      if (element.hasSource()) {
        composeAuditEventAuditEventSourceComponent("source", element.getSource());
      }
      if (element.hasObject()) { 
        for (AuditEvent.AuditEventObjectComponent e : element.getObject()) 
          composeAuditEventAuditEventObjectComponent("object", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEventAuditEventEventComponent(String name, AuditEvent.AuditEventEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSubtype()) { 
        for (CodeableConcept e : element.getSubtype()) 
          composeCodeableConcept("subtype", e);
      }
      if (element.hasActionElement())
        composeEnumeration("action", element.getActionElement(), new AuditEvent.AuditEventActionEnumFactory());
      if (element.hasDateTimeElement()) {
        composeInstant("dateTime", element.getDateTimeElement());
      }
      if (element.hasOutcomeElement())
        composeEnumeration("outcome", element.getOutcomeElement(), new AuditEvent.AuditEventOutcomeEnumFactory());
      if (element.hasOutcomeDescElement()) {
        composeString("outcomeDesc", element.getOutcomeDescElement());
      }
      if (element.hasPurposeOfEvent()) { 
        for (Coding e : element.getPurposeOfEvent()) 
          composeCoding("purposeOfEvent", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEventAuditEventParticipantComponent(String name, AuditEvent.AuditEventParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasRole()) { 
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept("role", e);
      }
      if (element.hasReference()) {
        composeReference("reference", element.getReference());
      }
      if (element.hasUserIdElement()) {
        composeString("userId", element.getUserIdElement());
      }
      if (element.hasAltIdElement()) {
        composeString("altId", element.getAltIdElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasRequestorElement()) {
        composeBoolean("requestor", element.getRequestorElement());
      }
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasPolicy()) { 
        for (UriType e : element.getPolicy()) 
          composeUri("policy", e);
      }
      if (element.hasMedia()) {
        composeCoding("media", element.getMedia());
      }
      if (element.hasNetwork()) {
        composeAuditEventAuditEventParticipantNetworkComponent("network", element.getNetwork());
      }
      if (element.hasPurposeOfUse()) { 
        for (Coding e : element.getPurposeOfUse()) 
          composeCoding("purposeOfUse", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEventAuditEventParticipantNetworkComponent(String name, AuditEvent.AuditEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifierElement()) {
        composeString("identifier", element.getIdentifierElement());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new AuditEvent.AuditEventParticipantNetworkTypeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEventAuditEventSourceComponent(String name, AuditEvent.AuditEventSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSiteElement()) {
        composeString("site", element.getSiteElement());
      }
      if (element.hasIdentifierElement()) {
        composeString("identifier", element.getIdentifierElement());
      }
      if (element.hasType()) { 
        for (Coding e : element.getType()) 
          composeCoding("type", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEventAuditEventObjectComponent(String name, AuditEvent.AuditEventObjectComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasReference()) {
        composeReference("reference", element.getReference());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new AuditEvent.AuditEventObjectTypeEnumFactory());
      if (element.hasRoleElement())
        composeEnumeration("role", element.getRoleElement(), new AuditEvent.AuditEventObjectRoleEnumFactory());
      if (element.hasLifecycleElement())
        composeEnumeration("lifecycle", element.getLifecycleElement(), new AuditEvent.AuditEventObjectLifecycleEnumFactory());
      if (element.hasSensitivity()) {
        composeCodeableConcept("sensitivity", element.getSensitivity());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasQueryElement()) {
        composeBase64Binary("query", element.getQueryElement());
      }
      if (element.hasDetail()) { 
        for (AuditEvent.AuditEventObjectDetailComponent e : element.getDetail()) 
          composeAuditEventAuditEventObjectDetailComponent("detail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeAuditEventAuditEventObjectDetailComponent(String name, AuditEvent.AuditEventObjectDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement()) {
        composeString("type", element.getTypeElement());
      }
      if (element.hasValueElement()) {
        composeBase64Binary("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBasic(String name, Basic element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasCreatedElement()) {
        composeDate("created", element.getCreatedElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBinary(String name, Binary element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeResourceElements(element);
      if (element.hasContentTypeElement()) {
        composeCode("contentType", element.getContentTypeElement());
      }
      if (element.hasContentElement()) {
        composeBase64Binary("content", element.getContentElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBodySite(String name, BodySite element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasModifier()) { 
        for (CodeableConcept e : element.getModifier()) 
          composeCodeableConcept("modifier", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasImage()) { 
        for (Attachment e : element.getImage()) 
          composeAttachment("image", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBundle(String name, Bundle element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeResourceElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Bundle.BundleTypeEnumFactory());
      if (element.hasBaseElement()) {
        composeUri("base", element.getBaseElement());
      }
      if (element.hasTotalElement()) {
        composeUnsignedInt("total", element.getTotalElement());
      }
      if (element.hasLink()) { 
        for (Bundle.BundleLinkComponent e : element.getLink()) 
          composeBundleBundleLinkComponent("link", e);
      }
      if (element.hasEntry()) { 
        for (Bundle.BundleEntryComponent e : element.getEntry()) 
          composeBundleBundleEntryComponent("entry", e);
      }
      if (element.hasSignatureElement()) {
        composeBase64Binary("signature", element.getSignatureElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBundleBundleLinkComponent(String name, Bundle.BundleLinkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasRelationElement()) {
        composeString("relation", element.getRelationElement());
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBundleBundleEntryComponent(String name, Bundle.BundleEntryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasBaseElement()) {
        composeUri("base", element.getBaseElement());
      }
      if (element.hasLink()) { 
        for (Bundle.BundleLinkComponent e : element.getLink()) 
          composeBundleBundleLinkComponent("link", e);
      }
      if (element.hasResource()) {
        xml.enter(FHIR_NS, "resource");
        composeResource(element.getResource());
        xml.exit(FHIR_NS, "resource");
      }
      if (element.hasSearch()) {
        composeBundleBundleEntrySearchComponent("search", element.getSearch());
      }
      if (element.hasTransaction()) {
        composeBundleBundleEntryTransactionComponent("transaction", element.getTransaction());
      }
      if (element.hasTransactionResponse()) {
        composeBundleBundleEntryTransactionResponseComponent("transactionResponse", element.getTransactionResponse());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBundleBundleEntrySearchComponent(String name, Bundle.BundleEntrySearchComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasModeElement())
        composeEnumeration("mode", element.getModeElement(), new Bundle.SearchEntryModeEnumFactory());
      if (element.hasScoreElement()) {
        composeDecimal("score", element.getScoreElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBundleBundleEntryTransactionComponent(String name, Bundle.BundleEntryTransactionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasMethodElement())
        composeEnumeration("method", element.getMethodElement(), new Bundle.HTTPVerbEnumFactory());
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasIfNoneMatchElement()) {
        composeString("ifNoneMatch", element.getIfNoneMatchElement());
      }
      if (element.hasIfMatchElement()) {
        composeString("ifMatch", element.getIfMatchElement());
      }
      if (element.hasIfModifiedSinceElement()) {
        composeInstant("ifModifiedSince", element.getIfModifiedSinceElement());
      }
      if (element.hasIfNoneExistElement()) {
        composeString("ifNoneExist", element.getIfNoneExistElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeBundleBundleEntryTransactionResponseComponent(String name, Bundle.BundleEntryTransactionResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasStatusElement()) {
        composeString("status", element.getStatusElement());
      }
      if (element.hasLocationElement()) {
        composeUri("location", element.getLocationElement());
      }
      if (element.hasEtagElement()) {
        composeString("etag", element.getEtagElement());
      }
      if (element.hasLastModifiedElement()) {
        composeInstant("lastModified", element.getLastModifiedElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new CarePlan.CarePlanStatusEnumFactory());
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasAuthor()) { 
        for (Reference e : element.getAuthor()) 
          composeReference("author", e);
      }
      if (element.hasModifiedElement()) {
        composeDateTime("modified", element.getModifiedElement());
      }
      if (element.hasCategory()) { 
        for (CodeableConcept e : element.getCategory()) 
          composeCodeableConcept("category", e);
      }
      if (element.hasConcern()) { 
        for (Reference e : element.getConcern()) 
          composeReference("concern", e);
      }
      if (element.hasSupport()) { 
        for (Reference e : element.getSupport()) 
          composeReference("support", e);
      }
      if (element.hasParticipant()) { 
        for (CarePlan.CarePlanParticipantComponent e : element.getParticipant()) 
          composeCarePlanCarePlanParticipantComponent("participant", e);
      }
      if (element.hasGoal()) { 
        for (Reference e : element.getGoal()) 
          composeReference("goal", e);
      }
      if (element.hasActivity()) { 
        for (CarePlan.CarePlanActivityComponent e : element.getActivity()) 
          composeCarePlanCarePlanActivityComponent("activity", e);
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasRole()) {
        composeCodeableConcept("role", element.getRole());
      }
      if (element.hasMember()) {
        composeReference("member", element.getMember());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasActionResulting()) { 
        for (Reference e : element.getActionResulting()) 
          composeReference("actionResulting", e);
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      if (element.hasReference()) {
        composeReference("reference", element.getReference());
      }
      if (element.hasDetail()) {
        composeCarePlanCarePlanActivityDetailComponent("detail", element.getDetail());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCarePlanCarePlanActivityDetailComponent(String name, CarePlan.CarePlanActivityDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCategoryElement())
        composeEnumeration("category", element.getCategoryElement(), new CarePlan.CarePlanActivityCategoryEnumFactory());
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasReason()) {
        composeType("reason", element.getReason());
      }      if (element.hasGoal()) { 
        for (Reference e : element.getGoal()) 
          composeReference("goal", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new CarePlan.CarePlanActivityStatusEnumFactory());
      if (element.hasStatusReason()) {
        composeCodeableConcept("statusReason", element.getStatusReason());
      }
      if (element.hasProhibitedElement()) {
        composeBoolean("prohibited", element.getProhibitedElement());
      }
      if (element.hasScheduled()) {
        composeType("scheduled", element.getScheduled());
      }      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasPerformer()) { 
        for (Reference e : element.getPerformer()) 
          composeReference("performer", e);
      }
      if (element.hasProduct()) {
        composeReference("product", element.getProduct());
      }
      if (element.hasDailyAmount()) {
        composeQuantity("dailyAmount", element.getDailyAmount());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaim(String name, Claim element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Claim.ClaimTypeEnumFactory());
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasUseElement())
        composeEnumeration("use", element.getUseElement(), new Claim.UseEnumFactory());
      if (element.hasPriority()) {
        composeCoding("priority", element.getPriority());
      }
      if (element.hasFundsReserve()) {
        composeCoding("fundsReserve", element.getFundsReserve());
      }
      if (element.hasEnterer()) {
        composeReference("enterer", element.getEnterer());
      }
      if (element.hasFacility()) {
        composeReference("facility", element.getFacility());
      }
      if (element.hasPrescription()) {
        composeReference("prescription", element.getPrescription());
      }
      if (element.hasOriginalPrescription()) {
        composeReference("originalPrescription", element.getOriginalPrescription());
      }
      if (element.hasPayee()) {
        composeClaimPayeeComponent("payee", element.getPayee());
      }
      if (element.hasReferral()) {
        composeReference("referral", element.getReferral());
      }
      if (element.hasDiagnosis()) { 
        for (Claim.DiagnosisComponent e : element.getDiagnosis()) 
          composeClaimDiagnosisComponent("diagnosis", e);
      }
      if (element.hasCondition()) { 
        for (Coding e : element.getCondition()) 
          composeCoding("condition", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasCoverage()) { 
        for (Claim.CoverageComponent e : element.getCoverage()) 
          composeClaimCoverageComponent("coverage", e);
      }
      if (element.hasException()) { 
        for (Coding e : element.getException()) 
          composeCoding("exception", e);
      }
      if (element.hasSchoolElement()) {
        composeString("school", element.getSchoolElement());
      }
      if (element.hasAccidentElement()) {
        composeDate("accident", element.getAccidentElement());
      }
      if (element.hasAccidentType()) {
        composeCoding("accidentType", element.getAccidentType());
      }
      if (element.hasInterventionException()) { 
        for (Coding e : element.getInterventionException()) 
          composeCoding("interventionException", e);
      }
      if (element.hasItem()) { 
        for (Claim.ItemsComponent e : element.getItem()) 
          composeClaimItemsComponent("item", e);
      }
      if (element.hasAdditionalMaterials()) { 
        for (Coding e : element.getAdditionalMaterials()) 
          composeCoding("additionalMaterials", e);
      }
      if (element.hasMissingTeeth()) { 
        for (Claim.MissingTeethComponent e : element.getMissingTeeth()) 
          composeClaimMissingTeethComponent("missingTeeth", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimPayeeComponent(String name, Claim.PayeeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasPerson()) {
        composeReference("person", element.getPerson());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimDiagnosisComponent(String name, Claim.DiagnosisComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasDiagnosis()) {
        composeCoding("diagnosis", element.getDiagnosis());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimCoverageComponent(String name, Claim.CoverageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasFocalElement()) {
        composeBoolean("focal", element.getFocalElement());
      }
      if (element.hasCoverage()) {
        composeReference("coverage", element.getCoverage());
      }
      if (element.hasBusinessArrangementElement()) {
        composeString("businessArrangement", element.getBusinessArrangementElement());
      }
      if (element.hasRelationship()) {
        composeCoding("relationship", element.getRelationship());
      }
      if (element.hasPreAuthRef()) { 
        for (StringType e : element.getPreAuthRef()) 
          composeString("preAuthRef", e);
      }
      if (element.hasClaimResponse()) {
        composeReference("claimResponse", element.getClaimResponse());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimItemsComponent(String name, Claim.ItemsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasDiagnosisLinkId()) { 
        for (PositiveIntType e : element.getDiagnosisLinkId()) 
          composePositiveInt("diagnosisLinkId", e);
      }
      if (element.hasService()) {
        composeCoding("service", element.getService());
      }
      if (element.hasServiceDateElement()) {
        composeDate("serviceDate", element.getServiceDateElement());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasUnitPrice()) {
        composeMoney("unitPrice", element.getUnitPrice());
      }
      if (element.hasFactorElement()) {
        composeDecimal("factor", element.getFactorElement());
      }
      if (element.hasPointsElement()) {
        composeDecimal("points", element.getPointsElement());
      }
      if (element.hasNet()) {
        composeMoney("net", element.getNet());
      }
      if (element.hasUdi()) {
        composeCoding("udi", element.getUdi());
      }
      if (element.hasBodySite()) {
        composeCoding("bodySite", element.getBodySite());
      }
      if (element.hasSubSite()) { 
        for (Coding e : element.getSubSite()) 
          composeCoding("subSite", e);
      }
      if (element.hasModifier()) { 
        for (Coding e : element.getModifier()) 
          composeCoding("modifier", e);
      }
      if (element.hasDetail()) { 
        for (Claim.DetailComponent e : element.getDetail()) 
          composeClaimDetailComponent("detail", e);
      }
      if (element.hasProsthesis()) {
        composeClaimProsthesisComponent("prosthesis", element.getProsthesis());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimDetailComponent(String name, Claim.DetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasService()) {
        composeCoding("service", element.getService());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasUnitPrice()) {
        composeMoney("unitPrice", element.getUnitPrice());
      }
      if (element.hasFactorElement()) {
        composeDecimal("factor", element.getFactorElement());
      }
      if (element.hasPointsElement()) {
        composeDecimal("points", element.getPointsElement());
      }
      if (element.hasNet()) {
        composeMoney("net", element.getNet());
      }
      if (element.hasUdi()) {
        composeCoding("udi", element.getUdi());
      }
      if (element.hasSubDetail()) { 
        for (Claim.SubDetailComponent e : element.getSubDetail()) 
          composeClaimSubDetailComponent("subDetail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimSubDetailComponent(String name, Claim.SubDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasService()) {
        composeCoding("service", element.getService());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasUnitPrice()) {
        composeMoney("unitPrice", element.getUnitPrice());
      }
      if (element.hasFactorElement()) {
        composeDecimal("factor", element.getFactorElement());
      }
      if (element.hasPointsElement()) {
        composeDecimal("points", element.getPointsElement());
      }
      if (element.hasNet()) {
        composeMoney("net", element.getNet());
      }
      if (element.hasUdi()) {
        composeCoding("udi", element.getUdi());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimProsthesisComponent(String name, Claim.ProsthesisComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasInitialElement()) {
        composeBoolean("initial", element.getInitialElement());
      }
      if (element.hasPriorDateElement()) {
        composeDate("priorDate", element.getPriorDateElement());
      }
      if (element.hasPriorMaterial()) {
        composeCoding("priorMaterial", element.getPriorMaterial());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimMissingTeethComponent(String name, Claim.MissingTeethComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTooth()) {
        composeCoding("tooth", element.getTooth());
      }
      if (element.hasReason()) {
        composeCoding("reason", element.getReason());
      }
      if (element.hasExtractionDateElement()) {
        composeDate("extractionDate", element.getExtractionDateElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponse(String name, ClaimResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequestProvider()) {
        composeReference("requestProvider", element.getRequestProvider());
      }
      if (element.hasRequestOrganization()) {
        composeReference("requestOrganization", element.getRequestOrganization());
      }
      if (element.hasOutcomeElement())
        composeEnumeration("outcome", element.getOutcomeElement(), new Enumerations.RemittanceOutcomeEnumFactory());
      if (element.hasDispositionElement()) {
        composeString("disposition", element.getDispositionElement());
      }
      if (element.hasPayeeType()) {
        composeCoding("payeeType", element.getPayeeType());
      }
      if (element.hasItem()) { 
        for (ClaimResponse.ItemsComponent e : element.getItem()) 
          composeClaimResponseItemsComponent("item", e);
      }
      if (element.hasAddItem()) { 
        for (ClaimResponse.AddedItemComponent e : element.getAddItem()) 
          composeClaimResponseAddedItemComponent("addItem", e);
      }
      if (element.hasError()) { 
        for (ClaimResponse.ErrorsComponent e : element.getError()) 
          composeClaimResponseErrorsComponent("error", e);
      }
      if (element.hasTotalCost()) {
        composeMoney("totalCost", element.getTotalCost());
      }
      if (element.hasUnallocDeductable()) {
        composeMoney("unallocDeductable", element.getUnallocDeductable());
      }
      if (element.hasTotalBenefit()) {
        composeMoney("totalBenefit", element.getTotalBenefit());
      }
      if (element.hasPaymentAdjustment()) {
        composeMoney("paymentAdjustment", element.getPaymentAdjustment());
      }
      if (element.hasPaymentAdjustmentReason()) {
        composeCoding("paymentAdjustmentReason", element.getPaymentAdjustmentReason());
      }
      if (element.hasPaymentDateElement()) {
        composeDate("paymentDate", element.getPaymentDateElement());
      }
      if (element.hasPaymentAmount()) {
        composeMoney("paymentAmount", element.getPaymentAmount());
      }
      if (element.hasPaymentRef()) {
        composeIdentifier("paymentRef", element.getPaymentRef());
      }
      if (element.hasReserved()) {
        composeCoding("reserved", element.getReserved());
      }
      if (element.hasForm()) {
        composeCoding("form", element.getForm());
      }
      if (element.hasNote()) { 
        for (ClaimResponse.NotesComponent e : element.getNote()) 
          composeClaimResponseNotesComponent("note", e);
      }
      if (element.hasCoverage()) { 
        for (ClaimResponse.CoverageComponent e : element.getCoverage()) 
          composeClaimResponseCoverageComponent("coverage", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseItemsComponent(String name, ClaimResponse.ItemsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceLinkIdElement()) {
        composePositiveInt("sequenceLinkId", element.getSequenceLinkIdElement());
      }
      if (element.hasNoteNumber()) { 
        for (PositiveIntType e : element.getNoteNumber()) 
          composePositiveInt("noteNumber", e);
      }
      if (element.hasAdjudication()) { 
        for (ClaimResponse.ItemAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseItemAdjudicationComponent("adjudication", e);
      }
      if (element.hasDetail()) { 
        for (ClaimResponse.ItemDetailComponent e : element.getDetail()) 
          composeClaimResponseItemDetailComponent("detail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseItemAdjudicationComponent(String name, ClaimResponse.ItemAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      if (element.hasAmount()) {
        composeMoney("amount", element.getAmount());
      }
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseItemDetailComponent(String name, ClaimResponse.ItemDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceLinkIdElement()) {
        composePositiveInt("sequenceLinkId", element.getSequenceLinkIdElement());
      }
      if (element.hasAdjudication()) { 
        for (ClaimResponse.DetailAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseDetailAdjudicationComponent("adjudication", e);
      }
      if (element.hasSubDetail()) { 
        for (ClaimResponse.SubDetailComponent e : element.getSubDetail()) 
          composeClaimResponseSubDetailComponent("subDetail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseDetailAdjudicationComponent(String name, ClaimResponse.DetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      if (element.hasAmount()) {
        composeMoney("amount", element.getAmount());
      }
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseSubDetailComponent(String name, ClaimResponse.SubDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceLinkIdElement()) {
        composePositiveInt("sequenceLinkId", element.getSequenceLinkIdElement());
      }
      if (element.hasAdjudication()) { 
        for (ClaimResponse.SubdetailAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseSubdetailAdjudicationComponent("adjudication", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseSubdetailAdjudicationComponent(String name, ClaimResponse.SubdetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      if (element.hasAmount()) {
        composeMoney("amount", element.getAmount());
      }
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseAddedItemComponent(String name, ClaimResponse.AddedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceLinkId()) { 
        for (PositiveIntType e : element.getSequenceLinkId()) 
          composePositiveInt("sequenceLinkId", e);
      }
      if (element.hasService()) {
        composeCoding("service", element.getService());
      }
      if (element.hasFee()) {
        composeMoney("fee", element.getFee());
      }
      if (element.hasNoteNumberLinkId()) { 
        for (PositiveIntType e : element.getNoteNumberLinkId()) 
          composePositiveInt("noteNumberLinkId", e);
      }
      if (element.hasAdjudication()) { 
        for (ClaimResponse.AddedItemAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseAddedItemAdjudicationComponent("adjudication", e);
      }
      if (element.hasDetail()) { 
        for (ClaimResponse.AddedItemsDetailComponent e : element.getDetail()) 
          composeClaimResponseAddedItemsDetailComponent("detail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseAddedItemAdjudicationComponent(String name, ClaimResponse.AddedItemAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      if (element.hasAmount()) {
        composeMoney("amount", element.getAmount());
      }
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseAddedItemsDetailComponent(String name, ClaimResponse.AddedItemsDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasService()) {
        composeCoding("service", element.getService());
      }
      if (element.hasFee()) {
        composeMoney("fee", element.getFee());
      }
      if (element.hasAdjudication()) { 
        for (ClaimResponse.AddedItemDetailAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseAddedItemDetailAdjudicationComponent("adjudication", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseAddedItemDetailAdjudicationComponent(String name, ClaimResponse.AddedItemDetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      if (element.hasAmount()) {
        composeMoney("amount", element.getAmount());
      }
      if (element.hasValueElement()) {
        composeDecimal("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseErrorsComponent(String name, ClaimResponse.ErrorsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceLinkIdElement()) {
        composePositiveInt("sequenceLinkId", element.getSequenceLinkIdElement());
      }
      if (element.hasDetailSequenceLinkIdElement()) {
        composePositiveInt("detailSequenceLinkId", element.getDetailSequenceLinkIdElement());
      }
      if (element.hasSubdetailSequenceLinkIdElement()) {
        composePositiveInt("subdetailSequenceLinkId", element.getSubdetailSequenceLinkIdElement());
      }
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseNotesComponent(String name, ClaimResponse.NotesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNumberElement()) {
        composePositiveInt("number", element.getNumberElement());
      }
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClaimResponseCoverageComponent(String name, ClaimResponse.CoverageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasFocalElement()) {
        composeBoolean("focal", element.getFocalElement());
      }
      if (element.hasCoverage()) {
        composeReference("coverage", element.getCoverage());
      }
      if (element.hasBusinessArrangementElement()) {
        composeString("businessArrangement", element.getBusinessArrangementElement());
      }
      if (element.hasRelationship()) {
        composeCoding("relationship", element.getRelationship());
      }
      if (element.hasPreAuthRef()) { 
        for (StringType e : element.getPreAuthRef()) 
          composeString("preAuthRef", e);
      }
      if (element.hasClaimResponse()) {
        composeReference("claimResponse", element.getClaimResponse());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClinicalImpression(String name, ClinicalImpression element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasAssessor()) {
        composeReference("assessor", element.getAssessor());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new ClinicalImpression.ClinicalImpressionStatusEnumFactory());
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasPrevious()) {
        composeReference("previous", element.getPrevious());
      }
      if (element.hasProblem()) { 
        for (Reference e : element.getProblem()) 
          composeReference("problem", e);
      }
      if (element.hasTrigger()) {
        composeType("trigger", element.getTrigger());
      }      if (element.hasInvestigations()) { 
        for (ClinicalImpression.ClinicalImpressionInvestigationsComponent e : element.getInvestigations()) 
          composeClinicalImpressionClinicalImpressionInvestigationsComponent("investigations", e);
      }
      if (element.hasProtocolElement()) {
        composeUri("protocol", element.getProtocolElement());
      }
      if (element.hasSummaryElement()) {
        composeString("summary", element.getSummaryElement());
      }
      if (element.hasFinding()) { 
        for (ClinicalImpression.ClinicalImpressionFindingComponent e : element.getFinding()) 
          composeClinicalImpressionClinicalImpressionFindingComponent("finding", e);
      }
      if (element.hasResolved()) { 
        for (CodeableConcept e : element.getResolved()) 
          composeCodeableConcept("resolved", e);
      }
      if (element.hasRuledOut()) { 
        for (ClinicalImpression.ClinicalImpressionRuledOutComponent e : element.getRuledOut()) 
          composeClinicalImpressionClinicalImpressionRuledOutComponent("ruledOut", e);
      }
      if (element.hasPrognosisElement()) {
        composeString("prognosis", element.getPrognosisElement());
      }
      if (element.hasPlan()) { 
        for (Reference e : element.getPlan()) 
          composeReference("plan", e);
      }
      if (element.hasAction()) { 
        for (Reference e : element.getAction()) 
          composeReference("action", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClinicalImpressionClinicalImpressionInvestigationsComponent(String name, ClinicalImpression.ClinicalImpressionInvestigationsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasItem()) { 
        for (Reference e : element.getItem()) 
          composeReference("item", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClinicalImpressionClinicalImpressionFindingComponent(String name, ClinicalImpression.ClinicalImpressionFindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasItem()) {
        composeCodeableConcept("item", element.getItem());
      }
      if (element.hasCauseElement()) {
        composeString("cause", element.getCauseElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeClinicalImpressionClinicalImpressionRuledOutComponent(String name, ClinicalImpression.ClinicalImpressionRuledOutComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasItem()) {
        composeCodeableConcept("item", element.getItem());
      }
      if (element.hasReasonElement()) {
        composeString("reason", element.getReasonElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCommunication(String name, Communication element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasSender()) {
        composeReference("sender", element.getSender());
      }
      if (element.hasRecipient()) { 
        for (Reference e : element.getRecipient()) 
          composeReference("recipient", e);
      }
      if (element.hasPayload()) { 
        for (Communication.CommunicationPayloadComponent e : element.getPayload()) 
          composeCommunicationCommunicationPayloadComponent("payload", e);
      }
      if (element.hasMedium()) { 
        for (CodeableConcept e : element.getMedium()) 
          composeCodeableConcept("medium", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Communication.CommunicationStatusEnumFactory());
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasSentElement()) {
        composeDateTime("sent", element.getSentElement());
      }
      if (element.hasReceivedElement()) {
        composeDateTime("received", element.getReceivedElement());
      }
      if (element.hasReason()) { 
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept("reason", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCommunicationCommunicationPayloadComponent(String name, Communication.CommunicationPayloadComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasContent()) {
        composeType("content", element.getContent());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCommunicationRequest(String name, CommunicationRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasSender()) {
        composeReference("sender", element.getSender());
      }
      if (element.hasRecipient()) { 
        for (Reference e : element.getRecipient()) 
          composeReference("recipient", e);
      }
      if (element.hasPayload()) { 
        for (CommunicationRequest.CommunicationRequestPayloadComponent e : element.getPayload()) 
          composeCommunicationRequestCommunicationRequestPayloadComponent("payload", e);
      }
      if (element.hasMedium()) { 
        for (CodeableConcept e : element.getMedium()) 
          composeCodeableConcept("medium", e);
      }
      if (element.hasRequester()) {
        composeReference("requester", element.getRequester());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new CommunicationRequest.CommunicationRequestStatusEnumFactory());
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasScheduledTimeElement()) {
        composeDateTime("scheduledTime", element.getScheduledTimeElement());
      }
      if (element.hasReason()) { 
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept("reason", e);
      }
      if (element.hasOrderedOnElement()) {
        composeDateTime("orderedOn", element.getOrderedOnElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasPriority()) {
        composeCodeableConcept("priority", element.getPriority());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCommunicationRequestCommunicationRequestPayloadComponent(String name, CommunicationRequest.CommunicationRequestPayloadComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasContent()) {
        composeType("content", element.getContent());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeComposition(String name, Composition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasClass_()) {
        composeCodeableConcept("class", element.getClass_());
      }
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Composition.CompositionStatusEnumFactory());
      if (element.hasConfidentialityElement()) {
        composeCode("confidentiality", element.getConfidentialityElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasAuthor()) { 
        for (Reference e : element.getAuthor()) 
          composeReference("author", e);
      }
      if (element.hasAttester()) { 
        for (Composition.CompositionAttesterComponent e : element.getAttester()) 
          composeCompositionCompositionAttesterComponent("attester", e);
      }
      if (element.hasCustodian()) {
        composeReference("custodian", element.getCustodian());
      }
      if (element.hasEvent()) { 
        for (Composition.CompositionEventComponent e : element.getEvent()) 
          composeCompositionCompositionEventComponent("event", e);
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasSection()) { 
        for (Composition.SectionComponent e : element.getSection()) 
          composeCompositionSectionComponent("section", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCompositionCompositionAttesterComponent(String name, Composition.CompositionAttesterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
        if (element.hasMode()) 
          for (Enumeration<Composition.CompositionAttestationMode> e : element.getMode()) 
            composeEnumeration("mode", e, new Composition.CompositionAttestationModeEnumFactory());
      if (element.hasTimeElement()) {
        composeDateTime("time", element.getTimeElement());
      }
      if (element.hasParty()) {
        composeReference("party", element.getParty());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCompositionCompositionEventComponent(String name, Composition.CompositionEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) { 
        for (CodeableConcept e : element.getCode()) 
          composeCodeableConcept("code", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasDetail()) { 
        for (Reference e : element.getDetail()) 
          composeReference("detail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCompositionSectionComponent(String name, Composition.SectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasContent()) {
        composeReference("content", element.getContent());
      }
      if (element.hasSection()) { 
        for (Composition.SectionComponent e : element.getSection()) 
          composeCompositionSectionComponent("section", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConceptMap(String name, ConceptMap element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasUseContext()) { 
        for (CodeableConcept e : element.getUseContext()) 
          composeCodeableConcept("useContext", e);
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (ConceptMap.ConceptMapContactComponent e : element.getContact()) 
          composeConceptMapConceptMapContactComponent("contact", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasCopyrightElement()) {
        composeString("copyright", element.getCopyrightElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasSource()) {
        composeType("source", element.getSource());
      }      if (element.hasTarget()) {
        composeType("target", element.getTarget());
      }      if (element.hasElement()) { 
        for (ConceptMap.SourceElementComponent e : element.getElement()) 
          composeConceptMapSourceElementComponent("element", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConceptMapConceptMapContactComponent(String name, ConceptMap.ConceptMapContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConceptMapSourceElementComponent(String name, ConceptMap.SourceElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeSystemElement()) {
        composeUri("codeSystem", element.getCodeSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasTarget()) { 
        for (ConceptMap.TargetElementComponent e : element.getTarget()) 
          composeConceptMapTargetElementComponent("target", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConceptMapTargetElementComponent(String name, ConceptMap.TargetElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeSystemElement()) {
        composeUri("codeSystem", element.getCodeSystemElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasEquivalenceElement())
        composeEnumeration("equivalence", element.getEquivalenceElement(), new Enumerations.ConceptMapEquivalenceEnumFactory());
      if (element.hasCommentsElement()) {
        composeString("comments", element.getCommentsElement());
      }
      if (element.hasDependsOn()) { 
        for (ConceptMap.OtherElementComponent e : element.getDependsOn()) 
          composeConceptMapOtherElementComponent("dependsOn", e);
      }
      if (element.hasProduct()) { 
        for (ConceptMap.OtherElementComponent e : element.getProduct()) 
          composeConceptMapOtherElementComponent("product", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConceptMapOtherElementComponent(String name, ConceptMap.OtherElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasElementElement()) {
        composeUri("element", element.getElementElement());
      }
      if (element.hasCodeSystemElement()) {
        composeUri("codeSystem", element.getCodeSystemElement());
      }
      if (element.hasCodeElement()) {
        composeString("code", element.getCodeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCondition(String name, Condition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasAsserter()) {
        composeReference("asserter", element.getAsserter());
      }
      if (element.hasDateAssertedElement()) {
        composeDate("dateAsserted", element.getDateAssertedElement());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasClinicalStatusElement())
        composeEnumeration("clinicalStatus", element.getClinicalStatusElement(), new Condition.ConditionClinicalStatusEnumFactory());
      if (element.hasSeverity()) {
        composeCodeableConcept("severity", element.getSeverity());
      }
      if (element.hasOnset()) {
        composeType("onset", element.getOnset());
      }      if (element.hasAbatement()) {
        composeType("abatement", element.getAbatement());
      }      if (element.hasStage()) {
        composeConditionConditionStageComponent("stage", element.getStage());
      }
      if (element.hasEvidence()) { 
        for (Condition.ConditionEvidenceComponent e : element.getEvidence()) 
          composeConditionConditionEvidenceComponent("evidence", e);
      }
      if (element.hasLocation()) { 
        for (Condition.ConditionLocationComponent e : element.getLocation()) 
          composeConditionConditionLocationComponent("location", e);
      }
      if (element.hasDueTo()) { 
        for (Condition.ConditionDueToComponent e : element.getDueTo()) 
          composeConditionConditionDueToComponent("dueTo", e);
      }
      if (element.hasOccurredFollowing()) { 
        for (Condition.ConditionOccurredFollowingComponent e : element.getOccurredFollowing()) 
          composeConditionConditionOccurredFollowingComponent("occurredFollowing", e);
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSummary()) {
        composeCodeableConcept("summary", element.getSummary());
      }
      if (element.hasAssessment()) { 
        for (Reference e : element.getAssessment()) 
          composeReference("assessment", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConditionConditionEvidenceComponent(String name, Condition.ConditionEvidenceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasDetail()) { 
        for (Reference e : element.getDetail()) 
          composeReference("detail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConditionConditionLocationComponent(String name, Condition.ConditionLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSite()) {
        composeType("site", element.getSite());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConditionConditionDueToComponent(String name, Condition.ConditionDueToComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConditionConditionOccurredFollowingComponent(String name, Condition.ConditionOccurredFollowingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (Conformance.ConformanceContactComponent e : element.getContact()) 
          composeConformanceConformanceContactComponent("contact", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasCopyrightElement()) {
        composeString("copyright", element.getCopyrightElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasSoftware()) {
        composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      }
      if (element.hasImplementation()) {
        composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      }
      if (element.hasFhirVersionElement()) {
        composeId("fhirVersion", element.getFhirVersionElement());
      }
      if (element.hasAcceptUnknownElement()) {
        composeBoolean("acceptUnknown", element.getAcceptUnknownElement());
      }
      if (element.hasFormat()) { 
        for (CodeType e : element.getFormat()) 
          composeCode("format", e);
      }
      if (element.hasProfile()) { 
        for (Reference e : element.getProfile()) 
          composeReference("profile", e);
      }
      if (element.hasRest()) { 
        for (Conformance.ConformanceRestComponent e : element.getRest()) 
          composeConformanceConformanceRestComponent("rest", e);
      }
      if (element.hasMessaging()) { 
        for (Conformance.ConformanceMessagingComponent e : element.getMessaging()) 
          composeConformanceConformanceMessagingComponent("messaging", e);
      }
      if (element.hasDocument()) { 
        for (Conformance.ConformanceDocumentComponent e : element.getDocument()) 
          composeConformanceConformanceDocumentComponent("document", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceContactComponent(String name, Conformance.ConformanceContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceSoftwareComponent(String name, Conformance.ConformanceSoftwareComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasReleaseDateElement()) {
        composeDateTime("releaseDate", element.getReleaseDateElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasModeElement())
        composeEnumeration("mode", element.getModeElement(), new Conformance.RestfulConformanceModeEnumFactory());
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      if (element.hasSecurity()) {
        composeConformanceConformanceRestSecurityComponent("security", element.getSecurity());
      }
      if (element.hasResource()) { 
        for (Conformance.ConformanceRestResourceComponent e : element.getResource()) 
          composeConformanceConformanceRestResourceComponent("resource", e);
      }
      if (element.hasInteraction()) { 
        for (Conformance.SystemInteractionComponent e : element.getInteraction()) 
          composeConformanceSystemInteractionComponent("interaction", e);
      }
      if (element.hasOperation()) { 
        for (Conformance.ConformanceRestOperationComponent e : element.getOperation()) 
          composeConformanceConformanceRestOperationComponent("operation", e);
      }
      if (element.hasDocumentMailbox()) { 
        for (UriType e : element.getDocumentMailbox()) 
          composeUri("documentMailbox", e);
      }
      if (element.hasCompartment()) { 
        for (UriType e : element.getCompartment()) 
          composeUri("compartment", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceRestSecurityComponent(String name, Conformance.ConformanceRestSecurityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCorsElement()) {
        composeBoolean("cors", element.getCorsElement());
      }
      if (element.hasService()) { 
        for (CodeableConcept e : element.getService()) 
          composeCodeableConcept("service", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasCertificate()) { 
        for (Conformance.ConformanceRestSecurityCertificateComponent e : element.getCertificate()) 
          composeConformanceConformanceRestSecurityCertificateComponent("certificate", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceRestSecurityCertificateComponent(String name, Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement()) {
        composeCode("type", element.getTypeElement());
      }
      if (element.hasBlobElement()) {
        composeBase64Binary("blob", element.getBlobElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement()) {
        composeCode("type", element.getTypeElement());
      }
      if (element.hasProfile()) {
        composeReference("profile", element.getProfile());
      }
      if (element.hasInteraction()) { 
        for (Conformance.ResourceInteractionComponent e : element.getInteraction()) 
          composeConformanceResourceInteractionComponent("interaction", e);
      }
      if (element.hasVersioningElement())
        composeEnumeration("versioning", element.getVersioningElement(), new Conformance.ResourceVersionPolicyEnumFactory());
      if (element.hasReadHistoryElement()) {
        composeBoolean("readHistory", element.getReadHistoryElement());
      }
      if (element.hasUpdateCreateElement()) {
        composeBoolean("updateCreate", element.getUpdateCreateElement());
      }
      if (element.hasConditionalCreateElement()) {
        composeBoolean("conditionalCreate", element.getConditionalCreateElement());
      }
      if (element.hasConditionalUpdateElement()) {
        composeBoolean("conditionalUpdate", element.getConditionalUpdateElement());
      }
      if (element.hasConditionalDeleteElement()) {
        composeBoolean("conditionalDelete", element.getConditionalDeleteElement());
      }
      if (element.hasSearchInclude()) { 
        for (StringType e : element.getSearchInclude()) 
          composeString("searchInclude", e);
      }
      if (element.hasSearchParam()) { 
        for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getSearchParam()) 
          composeConformanceConformanceRestResourceSearchParamComponent("searchParam", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceResourceInteractionComponent(String name, Conformance.ResourceInteractionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeElement())
        composeEnumeration("code", element.getCodeElement(), new Conformance.TypeRestfulInteractionEnumFactory());
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDefinitionElement()) {
        composeUri("definition", element.getDefinitionElement());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Enumerations.SearchParamTypeEnumFactory());
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      if (element.hasTarget()) { 
        for (CodeType e : element.getTarget()) 
          composeCode("target", e);
      }
      if (element.hasChain()) { 
        for (StringType e : element.getChain()) 
          composeString("chain", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceSystemInteractionComponent(String name, Conformance.SystemInteractionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeElement())
        composeEnumeration("code", element.getCodeElement(), new Conformance.SystemRestfulInteractionEnumFactory());
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceRestOperationComponent(String name, Conformance.ConformanceRestOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDefinition()) {
        composeReference("definition", element.getDefinition());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasEndpointElement()) {
        composeUri("endpoint", element.getEndpointElement());
      }
      if (element.hasReliableCacheElement()) {
        composeUnsignedInt("reliableCache", element.getReliableCacheElement());
      }
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      if (element.hasEvent()) { 
        for (Conformance.ConformanceMessagingEventComponent e : element.getEvent()) 
          composeConformanceConformanceMessagingEventComponent("event", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceMessagingEventComponent(String name, Conformance.ConformanceMessagingEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCoding("code", element.getCode());
      }
      if (element.hasCategoryElement())
        composeEnumeration("category", element.getCategoryElement(), new Conformance.MessageSignificanceCategoryEnumFactory());
      if (element.hasModeElement())
        composeEnumeration("mode", element.getModeElement(), new Conformance.ConformanceEventModeEnumFactory());
      if (element.hasProtocol()) { 
        for (Coding e : element.getProtocol()) 
          composeCoding("protocol", e);
      }
      if (element.hasFocusElement()) {
        composeCode("focus", element.getFocusElement());
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasResponse()) {
        composeReference("response", element.getResponse());
      }
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasModeElement())
        composeEnumeration("mode", element.getModeElement(), new Conformance.DocumentModeEnumFactory());
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      if (element.hasProfile()) {
        composeReference("profile", element.getProfile());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContract(String name, Contract element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasIssuedElement()) {
        composeDateTime("issued", element.getIssuedElement());
      }
      if (element.hasApplies()) {
        composePeriod("applies", element.getApplies());
      }
      if (element.hasSubject()) { 
        for (Reference e : element.getSubject()) 
          composeReference("subject", e);
      }
      if (element.hasAuthority()) { 
        for (Reference e : element.getAuthority()) 
          composeReference("authority", e);
      }
      if (element.hasDomain()) { 
        for (Reference e : element.getDomain()) 
          composeReference("domain", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSubType()) { 
        for (CodeableConcept e : element.getSubType()) 
          composeCodeableConcept("subType", e);
      }
      if (element.hasAction()) { 
        for (CodeableConcept e : element.getAction()) 
          composeCodeableConcept("action", e);
      }
      if (element.hasActionReason()) { 
        for (CodeableConcept e : element.getActionReason()) 
          composeCodeableConcept("actionReason", e);
      }
      if (element.hasActor()) { 
        for (Contract.ActorComponent e : element.getActor()) 
          composeContractActorComponent("actor", e);
      }
      if (element.hasValuedItem()) { 
        for (Contract.ValuedItemComponent e : element.getValuedItem()) 
          composeContractValuedItemComponent("valuedItem", e);
      }
      if (element.hasSigner()) { 
        for (Contract.SignatoryComponent e : element.getSigner()) 
          composeContractSignatoryComponent("signer", e);
      }
      if (element.hasTerm()) { 
        for (Contract.TermComponent e : element.getTerm()) 
          composeContractTermComponent("term", e);
      }
      if (element.hasBinding()) {
        composeType("binding", element.getBinding());
      }      if (element.hasFriendly()) { 
        for (Contract.FriendlyLanguageComponent e : element.getFriendly()) 
          composeContractFriendlyLanguageComponent("friendly", e);
      }
      if (element.hasLegal()) { 
        for (Contract.LegalLanguageComponent e : element.getLegal()) 
          composeContractLegalLanguageComponent("legal", e);
      }
      if (element.hasRule()) { 
        for (Contract.ComputableLanguageComponent e : element.getRule()) 
          composeContractComputableLanguageComponent("rule", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractActorComponent(String name, Contract.ActorComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasEntity()) {
        composeReference("entity", element.getEntity());
      }
      if (element.hasRole()) { 
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept("role", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractValuedItemComponent(String name, Contract.ValuedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasEntity()) {
        composeType("entity", element.getEntity());
      }      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasEffectiveTimeElement()) {
        composeDateTime("effectiveTime", element.getEffectiveTimeElement());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasUnitPrice()) {
        composeMoney("unitPrice", element.getUnitPrice());
      }
      if (element.hasFactorElement()) {
        composeDecimal("factor", element.getFactorElement());
      }
      if (element.hasPointsElement()) {
        composeDecimal("points", element.getPointsElement());
      }
      if (element.hasNet()) {
        composeMoney("net", element.getNet());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractSignatoryComponent(String name, Contract.SignatoryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasParty()) {
        composeReference("party", element.getParty());
      }
      if (element.hasSignatureElement()) {
        composeString("signature", element.getSignatureElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractTermComponent(String name, Contract.TermComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasIssuedElement()) {
        composeDateTime("issued", element.getIssuedElement());
      }
      if (element.hasApplies()) {
        composePeriod("applies", element.getApplies());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSubType()) {
        composeCodeableConcept("subType", element.getSubType());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasAction()) { 
        for (CodeableConcept e : element.getAction()) 
          composeCodeableConcept("action", e);
      }
      if (element.hasActionReason()) { 
        for (CodeableConcept e : element.getActionReason()) 
          composeCodeableConcept("actionReason", e);
      }
      if (element.hasActor()) { 
        for (Contract.TermActorComponent e : element.getActor()) 
          composeContractTermActorComponent("actor", e);
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasValuedItem()) { 
        for (Contract.TermValuedItemComponent e : element.getValuedItem()) 
          composeContractTermValuedItemComponent("valuedItem", e);
      }
      if (element.hasGroup()) { 
        for (Contract.TermComponent e : element.getGroup()) 
          composeContractTermComponent("group", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractTermActorComponent(String name, Contract.TermActorComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasEntity()) {
        composeReference("entity", element.getEntity());
      }
      if (element.hasRole()) { 
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept("role", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractTermValuedItemComponent(String name, Contract.TermValuedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasEntity()) {
        composeType("entity", element.getEntity());
      }      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasEffectiveTimeElement()) {
        composeDateTime("effectiveTime", element.getEffectiveTimeElement());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasUnitPrice()) {
        composeMoney("unitPrice", element.getUnitPrice());
      }
      if (element.hasFactorElement()) {
        composeDecimal("factor", element.getFactorElement());
      }
      if (element.hasPointsElement()) {
        composeDecimal("points", element.getPointsElement());
      }
      if (element.hasNet()) {
        composeMoney("net", element.getNet());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractFriendlyLanguageComponent(String name, Contract.FriendlyLanguageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasContent()) {
        composeType("content", element.getContent());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractLegalLanguageComponent(String name, Contract.LegalLanguageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasContent()) {
        composeType("content", element.getContent());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContractComputableLanguageComponent(String name, Contract.ComputableLanguageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasContent()) {
        composeType("content", element.getContent());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContraindication(String name, Contraindication element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasSeverityElement())
        composeEnumeration("severity", element.getSeverityElement(), new Contraindication.ContraindicationSeverityEnumFactory());
      if (element.hasImplicated()) { 
        for (Reference e : element.getImplicated()) 
          composeReference("implicated", e);
      }
      if (element.hasDetailElement()) {
        composeString("detail", element.getDetailElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasReferenceElement()) {
        composeUri("reference", element.getReferenceElement());
      }
      if (element.hasMitigation()) { 
        for (Contraindication.ContraindicationMitigationComponent e : element.getMitigation()) 
          composeContraindicationContraindicationMitigationComponent("mitigation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeContraindicationContraindicationMitigationComponent(String name, Contraindication.ContraindicationMitigationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasAction()) {
        composeCodeableConcept("action", element.getAction());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeCoverage(String name, Coverage element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIssuer()) {
        composeReference("issuer", element.getIssuer());
      }
      if (element.hasBin()) {
        composeIdentifier("bin", element.getBin());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasSubscriberId()) {
        composeIdentifier("subscriberId", element.getSubscriberId());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasGroupElement()) {
        composeString("group", element.getGroupElement());
      }
      if (element.hasPlanElement()) {
        composeString("plan", element.getPlanElement());
      }
      if (element.hasSubPlanElement()) {
        composeString("subPlan", element.getSubPlanElement());
      }
      if (element.hasDependentElement()) {
        composePositiveInt("dependent", element.getDependentElement());
      }
      if (element.hasSequenceElement()) {
        composePositiveInt("sequence", element.getSequenceElement());
      }
      if (element.hasSubscriber()) {
        composeReference("subscriber", element.getSubscriber());
      }
      if (element.hasNetwork()) {
        composeIdentifier("network", element.getNetwork());
      }
      if (element.hasContract()) { 
        for (Reference e : element.getContract()) 
          composeReference("contract", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDataElement(String name, DataElement element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasUseContext()) { 
        for (CodeableConcept e : element.getUseContext()) 
          composeCodeableConcept("useContext", e);
      }
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasCopyrightElement()) {
        composeString("copyright", element.getCopyrightElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (DataElement.DataElementContactComponent e : element.getContact()) 
          composeDataElementDataElementContactComponent("contact", e);
      }
      if (element.hasSpecificityElement())
        composeEnumeration("specificity", element.getSpecificityElement(), new DataElement.DataElementSpecificityEnumFactory());
      if (element.hasMapping()) { 
        for (DataElement.DataElementMappingComponent e : element.getMapping()) 
          composeDataElementDataElementMappingComponent("mapping", e);
      }
      if (element.hasElement()) { 
        for (ElementDefinition e : element.getElement()) 
          composeElementDefinition("element", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDataElementDataElementContactComponent(String name, DataElement.DataElementContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDataElementDataElementMappingComponent(String name, DataElement.DataElementMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentityElement()) {
        composeId("identity", element.getIdentityElement());
      }
      if (element.hasUriElement()) {
        composeUri("uri", element.getUriElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasCommentsElement()) {
        composeString("comments", element.getCommentsElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasNote()) { 
        for (Annotation e : element.getNote()) 
          composeAnnotation("note", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Device.DeviceStatusEnumFactory());
      if (element.hasManufacturerElement()) {
        composeString("manufacturer", element.getManufacturerElement());
      }
      if (element.hasModelElement()) {
        composeString("model", element.getModelElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasManufactureDateElement()) {
        composeDateTime("manufactureDate", element.getManufactureDateElement());
      }
      if (element.hasExpiryElement()) {
        composeDateTime("expiry", element.getExpiryElement());
      }
      if (element.hasUdiElement()) {
        composeString("udi", element.getUdiElement());
      }
      if (element.hasLotNumberElement()) {
        composeString("lotNumber", element.getLotNumberElement());
      }
      if (element.hasOwner()) {
        composeReference("owner", element.getOwner());
      }
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasContact()) { 
        for (ContactPoint e : element.getContact()) 
          composeContactPoint("contact", e);
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDeviceComponent(String name, DeviceComponent element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasLastSystemChangeElement()) {
        composeInstant("lastSystemChange", element.getLastSystemChangeElement());
      }
      if (element.hasSource()) {
        composeReference("source", element.getSource());
      }
      if (element.hasParent()) {
        composeReference("parent", element.getParent());
      }
      if (element.hasOperationalStatus()) { 
        for (CodeableConcept e : element.getOperationalStatus()) 
          composeCodeableConcept("operationalStatus", e);
      }
      if (element.hasParameterGroup()) {
        composeCodeableConcept("parameterGroup", element.getParameterGroup());
      }
      if (element.hasMeasurementPrincipleElement())
        composeEnumeration("measurementPrinciple", element.getMeasurementPrincipleElement(), new DeviceComponent.MeasmntPrincipleEnumFactory());
      if (element.hasProductionSpecification()) { 
        for (DeviceComponent.DeviceComponentProductionSpecificationComponent e : element.getProductionSpecification()) 
          composeDeviceComponentDeviceComponentProductionSpecificationComponent("productionSpecification", e);
      }
      if (element.hasLanguageCode()) {
        composeCodeableConcept("languageCode", element.getLanguageCode());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDeviceComponentDeviceComponentProductionSpecificationComponent(String name, DeviceComponent.DeviceComponentProductionSpecificationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSpecType()) {
        composeCodeableConcept("specType", element.getSpecType());
      }
      if (element.hasComponentId()) {
        composeIdentifier("componentId", element.getComponentId());
      }
      if (element.hasProductionSpecElement()) {
        composeString("productionSpec", element.getProductionSpecElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDeviceMetric(String name, DeviceMetric element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasUnit()) {
        composeCodeableConcept("unit", element.getUnit());
      }
      if (element.hasSource()) {
        composeReference("source", element.getSource());
      }
      if (element.hasParent()) {
        composeReference("parent", element.getParent());
      }
      if (element.hasOperationalStatusElement())
        composeEnumeration("operationalStatus", element.getOperationalStatusElement(), new DeviceMetric.DeviceMetricOperationalStatusEnumFactory());
      if (element.hasColorElement())
        composeEnumeration("color", element.getColorElement(), new DeviceMetric.DeviceMetricColorEnumFactory());
      if (element.hasCategoryElement())
        composeEnumeration("category", element.getCategoryElement(), new DeviceMetric.DeviceMetricCategoryEnumFactory());
      if (element.hasMeasurementPeriod()) {
        composeTiming("measurementPeriod", element.getMeasurementPeriod());
      }
      if (element.hasCalibration()) { 
        for (DeviceMetric.DeviceMetricCalibrationComponent e : element.getCalibration()) 
          composeDeviceMetricDeviceMetricCalibrationComponent("calibration", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDeviceMetricDeviceMetricCalibrationComponent(String name, DeviceMetric.DeviceMetricCalibrationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new DeviceMetric.DeviceMetricCalibrationTypeEnumFactory());
      if (element.hasStateElement())
        composeEnumeration("state", element.getStateElement(), new DeviceMetric.DeviceMetricCalibrationStateEnumFactory());
      if (element.hasTimeElement()) {
        composeInstant("time", element.getTimeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDeviceUseRequest(String name, DeviceUseRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasBodySite()) {
        composeType("bodySite", element.getBodySite());
      }      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new DeviceUseRequest.DeviceUseRequestStatusEnumFactory());
      if (element.hasDevice()) {
        composeReference("device", element.getDevice());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasIndication()) { 
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept("indication", e);
      }
      if (element.hasNotes()) { 
        for (StringType e : element.getNotes()) 
          composeString("notes", e);
      }
      if (element.hasPrnReason()) { 
        for (CodeableConcept e : element.getPrnReason()) 
          composeCodeableConcept("prnReason", e);
      }
      if (element.hasOrderedOnElement()) {
        composeDateTime("orderedOn", element.getOrderedOnElement());
      }
      if (element.hasRecordedOnElement()) {
        composeDateTime("recordedOn", element.getRecordedOnElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasTiming()) {
        composeType("timing", element.getTiming());
      }      if (element.hasPriorityElement())
        composeEnumeration("priority", element.getPriorityElement(), new DeviceUseRequest.DeviceUseRequestPriorityEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDeviceUseStatement(String name, DeviceUseStatement element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasBodySite()) {
        composeType("bodySite", element.getBodySite());
      }      if (element.hasWhenUsed()) {
        composePeriod("whenUsed", element.getWhenUsed());
      }
      if (element.hasDevice()) {
        composeReference("device", element.getDevice());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasIndication()) { 
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept("indication", e);
      }
      if (element.hasNotes()) { 
        for (StringType e : element.getNotes()) 
          composeString("notes", e);
      }
      if (element.hasRecordedOnElement()) {
        composeDateTime("recordedOn", element.getRecordedOnElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasTiming()) {
        composeType("timing", element.getTiming());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasOrderer()) {
        composeReference("orderer", element.getOrderer());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasClinicalNotesElement()) {
        composeString("clinicalNotes", element.getClinicalNotesElement());
      }
      if (element.hasSupportingInformation()) { 
        for (Reference e : element.getSupportingInformation()) 
          composeReference("supportingInformation", e);
      }
      if (element.hasSpecimen()) { 
        for (Reference e : element.getSpecimen()) 
          composeReference("specimen", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      if (element.hasPriorityElement())
        composeEnumeration("priority", element.getPriorityElement(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory());
      if (element.hasEvent()) { 
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent("event", e);
      }
      if (element.hasItem()) { 
        for (DiagnosticOrder.DiagnosticOrderItemComponent e : element.getItem()) 
          composeDiagnosticOrderDiagnosticOrderItemComponent("item", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDiagnosticOrderDiagnosticOrderEventComponent(String name, DiagnosticOrder.DiagnosticOrderEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      if (element.hasDescription()) {
        composeCodeableConcept("description", element.getDescription());
      }
      if (element.hasDateTimeElement()) {
        composeDateTime("dateTime", element.getDateTimeElement());
      }
      if (element.hasActor()) {
        composeReference("actor", element.getActor());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasSpecimen()) { 
        for (Reference e : element.getSpecimen()) 
          composeReference("specimen", e);
      }
      if (element.hasBodySite()) {
        composeType("bodySite", element.getBodySite());
      }      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      if (element.hasEvent()) { 
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent("event", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDiagnosticReport(String name, DiagnosticReport element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new DiagnosticReport.DiagnosticReportStatusEnumFactory());
      if (element.hasIssuedElement()) {
        composeInstant("issued", element.getIssuedElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasPerformer()) {
        composeReference("performer", element.getPerformer());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequestDetail()) { 
        for (Reference e : element.getRequestDetail()) 
          composeReference("requestDetail", e);
      }
      if (element.hasServiceCategory()) {
        composeCodeableConcept("serviceCategory", element.getServiceCategory());
      }
      if (element.hasEffective()) {
        composeType("effective", element.getEffective());
      }      if (element.hasSpecimen()) { 
        for (Reference e : element.getSpecimen()) 
          composeReference("specimen", e);
      }
      if (element.hasResult()) { 
        for (Reference e : element.getResult()) 
          composeReference("result", e);
      }
      if (element.hasImagingStudy()) { 
        for (Reference e : element.getImagingStudy()) 
          composeReference("imagingStudy", e);
      }
      if (element.hasImage()) { 
        for (DiagnosticReport.DiagnosticReportImageComponent e : element.getImage()) 
          composeDiagnosticReportDiagnosticReportImageComponent("image", e);
      }
      if (element.hasConclusionElement()) {
        composeString("conclusion", element.getConclusionElement());
      }
      if (element.hasCodedDiagnosis()) { 
        for (CodeableConcept e : element.getCodedDiagnosis()) 
          composeCodeableConcept("codedDiagnosis", e);
      }
      if (element.hasPresentedForm()) { 
        for (Attachment e : element.getPresentedForm()) 
          composeAttachment("presentedForm", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDiagnosticReportDiagnosticReportImageComponent(String name, DiagnosticReport.DiagnosticReportImageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      if (element.hasLink()) {
        composeReference("link", element.getLink());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentManifest(String name, DocumentManifest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasMasterIdentifier()) {
        composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasRecipient()) { 
        for (Reference e : element.getRecipient()) 
          composeReference("recipient", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasAuthor()) { 
        for (Reference e : element.getAuthor()) 
          composeReference("author", e);
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasSourceElement()) {
        composeUri("source", element.getSourceElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.DocumentReferenceStatusEnumFactory());
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasContent()) { 
        for (DocumentManifest.DocumentManifestContentComponent e : element.getContent()) 
          composeDocumentManifestDocumentManifestContentComponent("content", e);
      }
      if (element.hasRelated()) { 
        for (DocumentManifest.DocumentManifestRelatedComponent e : element.getRelated()) 
          composeDocumentManifestDocumentManifestRelatedComponent("related", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentManifestDocumentManifestContentComponent(String name, DocumentManifest.DocumentManifestContentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasP()) {
        composeType("p", element.getP());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentManifestDocumentManifestRelatedComponent(String name, DocumentManifest.DocumentManifestRelatedComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasRef()) {
        composeReference("ref", element.getRef());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentReference(String name, DocumentReference element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasMasterIdentifier()) {
        composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasClass_()) {
        composeCodeableConcept("class", element.getClass_());
      }
      if (element.hasFormat()) { 
        for (UriType e : element.getFormat()) 
          composeUri("format", e);
      }
      if (element.hasAuthor()) { 
        for (Reference e : element.getAuthor()) 
          composeReference("author", e);
      }
      if (element.hasCustodian()) {
        composeReference("custodian", element.getCustodian());
      }
      if (element.hasAuthenticator()) {
        composeReference("authenticator", element.getAuthenticator());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasIndexedElement()) {
        composeInstant("indexed", element.getIndexedElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.DocumentReferenceStatusEnumFactory());
      if (element.hasDocStatus()) {
        composeCodeableConcept("docStatus", element.getDocStatus());
      }
      if (element.hasRelatesTo()) { 
        for (DocumentReference.DocumentReferenceRelatesToComponent e : element.getRelatesTo()) 
          composeDocumentReferenceDocumentReferenceRelatesToComponent("relatesTo", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasConfidentiality()) { 
        for (CodeableConcept e : element.getConfidentiality()) 
          composeCodeableConcept("confidentiality", e);
      }
      if (element.hasContent()) { 
        for (Attachment e : element.getContent()) 
          composeAttachment("content", e);
      }
      if (element.hasContext()) {
        composeDocumentReferenceDocumentReferenceContextComponent("context", element.getContext());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentReferenceDocumentReferenceRelatesToComponent(String name, DocumentReference.DocumentReferenceRelatesToComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeElement())
        composeEnumeration("code", element.getCodeElement(), new DocumentReference.DocumentRelationshipTypeEnumFactory());
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentReferenceDocumentReferenceContextComponent(String name, DocumentReference.DocumentReferenceContextComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasEvent()) { 
        for (CodeableConcept e : element.getEvent()) 
          composeCodeableConcept("event", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasFacilityType()) {
        composeCodeableConcept("facilityType", element.getFacilityType());
      }
      if (element.hasPracticeSetting()) {
        composeCodeableConcept("practiceSetting", element.getPracticeSetting());
      }
      if (element.hasSourcePatientInfo()) {
        composeReference("sourcePatientInfo", element.getSourcePatientInfo());
      }
      if (element.hasRelated()) { 
        for (DocumentReference.DocumentReferenceContextRelatedComponent e : element.getRelated()) 
          composeDocumentReferenceDocumentReferenceContextRelatedComponent("related", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeDocumentReferenceDocumentReferenceContextRelatedComponent(String name, DocumentReference.DocumentReferenceContextRelatedComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasRef()) {
        composeReference("ref", element.getRef());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEligibilityRequest(String name, EligibilityRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEligibilityResponse(String name, EligibilityResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasOutcomeElement())
        composeEnumeration("outcome", element.getOutcomeElement(), new Enumerations.RemittanceOutcomeEnumFactory());
      if (element.hasDispositionElement()) {
        composeString("disposition", element.getDispositionElement());
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequestProvider()) {
        composeReference("requestProvider", element.getRequestProvider());
      }
      if (element.hasRequestOrganization()) {
        composeReference("requestOrganization", element.getRequestOrganization());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEncounter(String name, Encounter element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Encounter.EncounterStateEnumFactory());
      if (element.hasStatusHistory()) { 
        for (Encounter.EncounterStatusHistoryComponent e : element.getStatusHistory()) 
          composeEncounterEncounterStatusHistoryComponent("statusHistory", e);
      }
      if (element.hasClass_Element())
        composeEnumeration("class", element.getClass_Element(), new Encounter.EncounterClassEnumFactory());
      if (element.hasType()) { 
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept("type", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasEpisodeOfCare()) {
        composeReference("episodeOfCare", element.getEpisodeOfCare());
      }
      if (element.hasIncomingReferralRequest()) { 
        for (Reference e : element.getIncomingReferralRequest()) 
          composeReference("incomingReferralRequest", e);
      }
      if (element.hasParticipant()) { 
        for (Encounter.EncounterParticipantComponent e : element.getParticipant()) 
          composeEncounterEncounterParticipantComponent("participant", e);
      }
      if (element.hasFulfills()) {
        composeReference("fulfills", element.getFulfills());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasLength()) {
        composeDuration("length", element.getLength());
      }
      if (element.hasReason()) { 
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept("reason", e);
      }
      if (element.hasIndication()) { 
        for (Reference e : element.getIndication()) 
          composeReference("indication", e);
      }
      if (element.hasPriority()) {
        composeCodeableConcept("priority", element.getPriority());
      }
      if (element.hasHospitalization()) {
        composeEncounterEncounterHospitalizationComponent("hospitalization", element.getHospitalization());
      }
      if (element.hasLocation()) { 
        for (Encounter.EncounterLocationComponent e : element.getLocation()) 
          composeEncounterEncounterLocationComponent("location", e);
      }
      if (element.hasServiceProvider()) {
        composeReference("serviceProvider", element.getServiceProvider());
      }
      if (element.hasPartOf()) {
        composeReference("partOf", element.getPartOf());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEncounterEncounterStatusHistoryComponent(String name, Encounter.EncounterStatusHistoryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Encounter.EncounterStateEnumFactory());
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEncounterEncounterParticipantComponent(String name, Encounter.EncounterParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) { 
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept("type", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasIndividual()) {
        composeReference("individual", element.getIndividual());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasPreAdmissionIdentifier()) {
        composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      }
      if (element.hasOrigin()) {
        composeReference("origin", element.getOrigin());
      }
      if (element.hasAdmitSource()) {
        composeCodeableConcept("admitSource", element.getAdmitSource());
      }
      if (element.hasDietPreference()) {
        composeCodeableConcept("dietPreference", element.getDietPreference());
      }
      if (element.hasSpecialCourtesy()) { 
        for (CodeableConcept e : element.getSpecialCourtesy()) 
          composeCodeableConcept("specialCourtesy", e);
      }
      if (element.hasSpecialArrangement()) { 
        for (CodeableConcept e : element.getSpecialArrangement()) 
          composeCodeableConcept("specialArrangement", e);
      }
      if (element.hasDestination()) {
        composeReference("destination", element.getDestination());
      }
      if (element.hasDischargeDisposition()) {
        composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      }
      if (element.hasDischargeDiagnosis()) {
        composeReference("dischargeDiagnosis", element.getDischargeDiagnosis());
      }
      if (element.hasReAdmissionElement()) {
        composeBoolean("reAdmission", element.getReAdmissionElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Encounter.EncounterLocationStatusEnumFactory());
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEnrollmentRequest(String name, EnrollmentRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasCoverage()) {
        composeReference("coverage", element.getCoverage());
      }
      if (element.hasRelationship()) {
        composeCoding("relationship", element.getRelationship());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEnrollmentResponse(String name, EnrollmentResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasOutcomeElement())
        composeEnumeration("outcome", element.getOutcomeElement(), new Enumerations.RemittanceOutcomeEnumFactory());
      if (element.hasDispositionElement()) {
        composeString("disposition", element.getDispositionElement());
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequestProvider()) {
        composeReference("requestProvider", element.getRequestProvider());
      }
      if (element.hasRequestOrganization()) {
        composeReference("requestOrganization", element.getRequestOrganization());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEpisodeOfCare(String name, EpisodeOfCare element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new EpisodeOfCare.EpisodeOfCareStatusEnumFactory());
      if (element.hasStatusHistory()) { 
        for (EpisodeOfCare.EpisodeOfCareStatusHistoryComponent e : element.getStatusHistory()) 
          composeEpisodeOfCareEpisodeOfCareStatusHistoryComponent("statusHistory", e);
      }
      if (element.hasType()) { 
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept("type", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasManagingOrganization()) {
        composeReference("managingOrganization", element.getManagingOrganization());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasCondition()) { 
        for (Reference e : element.getCondition()) 
          composeReference("condition", e);
      }
      if (element.hasReferralRequest()) { 
        for (Reference e : element.getReferralRequest()) 
          composeReference("referralRequest", e);
      }
      if (element.hasCareManager()) {
        composeReference("careManager", element.getCareManager());
      }
      if (element.hasCareTeam()) { 
        for (EpisodeOfCare.EpisodeOfCareCareTeamComponent e : element.getCareTeam()) 
          composeEpisodeOfCareEpisodeOfCareCareTeamComponent("careTeam", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEpisodeOfCareEpisodeOfCareStatusHistoryComponent(String name, EpisodeOfCare.EpisodeOfCareStatusHistoryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new EpisodeOfCare.EpisodeOfCareStatusEnumFactory());
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeEpisodeOfCareEpisodeOfCareCareTeamComponent(String name, EpisodeOfCare.EpisodeOfCareCareTeamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasMember()) {
        composeReference("member", element.getMember());
      }
      if (element.hasRole()) { 
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept("role", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeExplanationOfBenefit(String name, ExplanationOfBenefit element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasOutcomeElement())
        composeEnumeration("outcome", element.getOutcomeElement(), new Enumerations.RemittanceOutcomeEnumFactory());
      if (element.hasDispositionElement()) {
        composeString("disposition", element.getDispositionElement());
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequestProvider()) {
        composeReference("requestProvider", element.getRequestProvider());
      }
      if (element.hasRequestOrganization()) {
        composeReference("requestOrganization", element.getRequestOrganization());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeFamilyMemberHistory(String name, FamilyMemberHistory element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasRelationship()) {
        composeCodeableConcept("relationship", element.getRelationship());
      }
      if (element.hasGenderElement())
        composeEnumeration("gender", element.getGenderElement(), new Enumerations.AdministrativeGenderEnumFactory());
      if (element.hasBorn()) {
        composeType("born", element.getBorn());
      }      if (element.hasAge()) {
        composeType("age", element.getAge());
      }      if (element.hasDeceased()) {
        composeType("deceased", element.getDeceased());
      }      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      if (element.hasCondition()) { 
        for (FamilyMemberHistory.FamilyMemberHistoryConditionComponent e : element.getCondition()) 
          composeFamilyMemberHistoryFamilyMemberHistoryConditionComponent("condition", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeFamilyMemberHistoryFamilyMemberHistoryConditionComponent(String name, FamilyMemberHistory.FamilyMemberHistoryConditionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasOutcome()) {
        composeCodeableConcept("outcome", element.getOutcome());
      }
      if (element.hasOnset()) {
        composeType("onset", element.getOnset());
      }      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeFlag(String name, Flag element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Flag.FlagStatusEnumFactory());
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeGoal(String name, Goal element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasTargetDateElement()) {
        composeDate("targetDate", element.getTargetDateElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Goal.GoalStatusEnumFactory());
      if (element.hasStatusDateElement()) {
        composeDate("statusDate", element.getStatusDateElement());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasPriority()) {
        composeCodeableConcept("priority", element.getPriority());
      }
      if (element.hasConcern()) { 
        for (Reference e : element.getConcern()) 
          composeReference("concern", e);
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      if (element.hasOutcome()) { 
        for (Goal.GoalOutcomeComponent e : element.getOutcome()) 
          composeGoalGoalOutcomeComponent("outcome", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeGoalGoalOutcomeComponent(String name, Goal.GoalOutcomeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasResult()) {
        composeType("result", element.getResult());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Group.GroupTypeEnumFactory());
      if (element.hasActualElement()) {
        composeBoolean("actual", element.getActualElement());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasQuantityElement()) {
        composeUnsignedInt("quantity", element.getQuantityElement());
      }
      if (element.hasCharacteristic()) { 
        for (Group.GroupCharacteristicComponent e : element.getCharacteristic()) 
          composeGroupGroupCharacteristicComponent("characteristic", e);
      }
      if (element.hasMember()) { 
        for (Reference e : element.getMember()) 
          composeReference("member", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeGroupGroupCharacteristicComponent(String name, Group.GroupCharacteristicComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      if (element.hasExcludeElement()) {
        composeBoolean("exclude", element.getExcludeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeHealthcareService(String name, HealthcareService element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasProvidedBy()) {
        composeReference("providedBy", element.getProvidedBy());
      }
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasServiceCategory()) {
        composeCodeableConcept("serviceCategory", element.getServiceCategory());
      }
      if (element.hasServiceType()) { 
        for (HealthcareService.ServiceTypeComponent e : element.getServiceType()) 
          composeHealthcareServiceServiceTypeComponent("serviceType", e);
      }
      if (element.hasServiceNameElement()) {
        composeString("serviceName", element.getServiceNameElement());
      }
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      if (element.hasExtraDetailsElement()) {
        composeString("extraDetails", element.getExtraDetailsElement());
      }
      if (element.hasPhoto()) {
        composeAttachment("photo", element.getPhoto());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasCoverageArea()) { 
        for (Reference e : element.getCoverageArea()) 
          composeReference("coverageArea", e);
      }
      if (element.hasServiceProvisionCode()) { 
        for (CodeableConcept e : element.getServiceProvisionCode()) 
          composeCodeableConcept("serviceProvisionCode", e);
      }
      if (element.hasEligibility()) {
        composeCodeableConcept("eligibility", element.getEligibility());
      }
      if (element.hasEligibilityNoteElement()) {
        composeString("eligibilityNote", element.getEligibilityNoteElement());
      }
      if (element.hasProgramName()) { 
        for (StringType e : element.getProgramName()) 
          composeString("programName", e);
      }
      if (element.hasCharacteristic()) { 
        for (CodeableConcept e : element.getCharacteristic()) 
          composeCodeableConcept("characteristic", e);
      }
      if (element.hasReferralMethod()) { 
        for (CodeableConcept e : element.getReferralMethod()) 
          composeCodeableConcept("referralMethod", e);
      }
      if (element.hasPublicKeyElement()) {
        composeString("publicKey", element.getPublicKeyElement());
      }
      if (element.hasAppointmentRequiredElement()) {
        composeBoolean("appointmentRequired", element.getAppointmentRequiredElement());
      }
      if (element.hasAvailableTime()) { 
        for (HealthcareService.HealthcareServiceAvailableTimeComponent e : element.getAvailableTime()) 
          composeHealthcareServiceHealthcareServiceAvailableTimeComponent("availableTime", e);
      }
      if (element.hasNotAvailable()) { 
        for (HealthcareService.HealthcareServiceNotAvailableComponent e : element.getNotAvailable()) 
          composeHealthcareServiceHealthcareServiceNotAvailableComponent("notAvailable", e);
      }
      if (element.hasAvailabilityExceptionsElement()) {
        composeString("availabilityExceptions", element.getAvailabilityExceptionsElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeHealthcareServiceServiceTypeComponent(String name, HealthcareService.ServiceTypeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSpecialty()) { 
        for (CodeableConcept e : element.getSpecialty()) 
          composeCodeableConcept("specialty", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeHealthcareServiceHealthcareServiceAvailableTimeComponent(String name, HealthcareService.HealthcareServiceAvailableTimeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
        if (element.hasDaysOfWeek()) 
          for (Enumeration<HealthcareService.DaysOfWeek> e : element.getDaysOfWeek()) 
            composeEnumeration("daysOfWeek", e, new HealthcareService.DaysOfWeekEnumFactory());
      if (element.hasAllDayElement()) {
        composeBoolean("allDay", element.getAllDayElement());
      }
      if (element.hasAvailableStartTimeElement()) {
        composeTime("availableStartTime", element.getAvailableStartTimeElement());
      }
      if (element.hasAvailableEndTimeElement()) {
        composeTime("availableEndTime", element.getAvailableEndTimeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeHealthcareServiceHealthcareServiceNotAvailableComponent(String name, HealthcareService.HealthcareServiceNotAvailableComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasDuring()) {
        composePeriod("during", element.getDuring());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingObjectSelection(String name, ImagingObjectSelection element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasTitle()) {
        composeCodeableConcept("title", element.getTitle());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasAuthoringTimeElement()) {
        composeDateTime("authoringTime", element.getAuthoringTimeElement());
      }
      if (element.hasStudy()) { 
        for (ImagingObjectSelection.StudyComponent e : element.getStudy()) 
          composeImagingObjectSelectionStudyComponent("study", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingObjectSelectionStudyComponent(String name, ImagingObjectSelection.StudyComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasSeries()) { 
        for (ImagingObjectSelection.SeriesComponent e : element.getSeries()) 
          composeImagingObjectSelectionSeriesComponent("series", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingObjectSelectionSeriesComponent(String name, ImagingObjectSelection.SeriesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasInstance()) { 
        for (ImagingObjectSelection.InstanceComponent e : element.getInstance()) 
          composeImagingObjectSelectionInstanceComponent("instance", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingObjectSelectionInstanceComponent(String name, ImagingObjectSelection.InstanceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSopClassElement()) {
        composeOid("sopClass", element.getSopClassElement());
      }
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasFrames()) { 
        for (ImagingObjectSelection.FramesComponent e : element.getFrames()) 
          composeImagingObjectSelectionFramesComponent("frames", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingObjectSelectionFramesComponent(String name, ImagingObjectSelection.FramesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasFrameNumbers()) { 
        for (UnsignedIntType e : element.getFrameNumbers()) 
          composeUnsignedInt("frameNumbers", e);
      }
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasStartedElement()) {
        composeDateTime("started", element.getStartedElement());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasAccession()) {
        composeIdentifier("accession", element.getAccession());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasOrder()) { 
        for (Reference e : element.getOrder()) 
          composeReference("order", e);
      }
      if (element.hasModalityList()) { 
        for (Coding e : element.getModalityList()) 
          composeCoding("modalityList", e);
      }
      if (element.hasReferrer()) {
        composeReference("referrer", element.getReferrer());
      }
      if (element.hasAvailabilityElement())
        composeEnumeration("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory());
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasNumberOfSeriesElement()) {
        composeUnsignedInt("numberOfSeries", element.getNumberOfSeriesElement());
      }
      if (element.hasNumberOfInstancesElement()) {
        composeUnsignedInt("numberOfInstances", element.getNumberOfInstancesElement());
      }
      if (element.hasClinicalInformationElement()) {
        composeString("clinicalInformation", element.getClinicalInformationElement());
      }
      if (element.hasProcedure()) { 
        for (Reference e : element.getProcedure()) 
          composeReference("procedure", e);
      }
      if (element.hasInterpreter()) {
        composeReference("interpreter", element.getInterpreter());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasSeries()) { 
        for (ImagingStudy.ImagingStudySeriesComponent e : element.getSeries()) 
          composeImagingStudyImagingStudySeriesComponent("series", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingStudyImagingStudySeriesComponent(String name, ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNumberElement()) {
        composeUnsignedInt("number", element.getNumberElement());
      }
      if (element.hasModalityElement())
        composeEnumeration("modality", element.getModalityElement(), new ImagingStudy.ModalityEnumFactory());
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasNumberOfInstancesElement()) {
        composeUnsignedInt("numberOfInstances", element.getNumberOfInstancesElement());
      }
      if (element.hasAvailabilityElement())
        composeEnumeration("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory());
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasBodySite()) {
        composeCoding("bodySite", element.getBodySite());
      }
      if (element.hasLaterality()) {
        composeCoding("laterality", element.getLaterality());
      }
      if (element.hasDateTimeElement()) {
        composeDateTime("dateTime", element.getDateTimeElement());
      }
      if (element.hasInstance()) { 
        for (ImagingStudy.ImagingStudySeriesInstanceComponent e : element.getInstance()) 
          composeImagingStudyImagingStudySeriesInstanceComponent("instance", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImagingStudyImagingStudySeriesInstanceComponent(String name, ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNumberElement()) {
        composeUnsignedInt("number", element.getNumberElement());
      }
      if (element.hasUidElement()) {
        composeOid("uid", element.getUidElement());
      }
      if (element.hasSopclassElement()) {
        composeOid("sopclass", element.getSopclassElement());
      }
      if (element.hasTypeElement()) {
        composeString("type", element.getTypeElement());
      }
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasContent()) { 
        for (Attachment e : element.getContent()) 
          composeAttachment("content", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasVaccineType()) {
        composeCodeableConcept("vaccineType", element.getVaccineType());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasWasNotGivenElement()) {
        composeBoolean("wasNotGiven", element.getWasNotGivenElement());
      }
      if (element.hasReportedElement()) {
        composeBoolean("reported", element.getReportedElement());
      }
      if (element.hasPerformer()) {
        composeReference("performer", element.getPerformer());
      }
      if (element.hasRequester()) {
        composeReference("requester", element.getRequester());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasManufacturer()) {
        composeReference("manufacturer", element.getManufacturer());
      }
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasLotNumberElement()) {
        composeString("lotNumber", element.getLotNumberElement());
      }
      if (element.hasExpirationDateElement()) {
        composeDate("expirationDate", element.getExpirationDateElement());
      }
      if (element.hasSite()) {
        composeCodeableConcept("site", element.getSite());
      }
      if (element.hasRoute()) {
        composeCodeableConcept("route", element.getRoute());
      }
      if (element.hasDoseQuantity()) {
        composeQuantity("doseQuantity", element.getDoseQuantity());
      }
      if (element.hasExplanation()) {
        composeImmunizationImmunizationExplanationComponent("explanation", element.getExplanation());
      }
      if (element.hasReaction()) { 
        for (Immunization.ImmunizationReactionComponent e : element.getReaction()) 
          composeImmunizationImmunizationReactionComponent("reaction", e);
      }
      if (element.hasVaccinationProtocol()) { 
        for (Immunization.ImmunizationVaccinationProtocolComponent e : element.getVaccinationProtocol()) 
          composeImmunizationImmunizationVaccinationProtocolComponent("vaccinationProtocol", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationImmunizationExplanationComponent(String name, Immunization.ImmunizationExplanationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasReason()) { 
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept("reason", e);
      }
      if (element.hasReasonNotGiven()) { 
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept("reasonNotGiven", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationImmunizationReactionComponent(String name, Immunization.ImmunizationReactionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasDetail()) {
        composeReference("detail", element.getDetail());
      }
      if (element.hasReportedElement()) {
        composeBoolean("reported", element.getReportedElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDoseSequenceElement()) {
        composePositiveInt("doseSequence", element.getDoseSequenceElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasAuthority()) {
        composeReference("authority", element.getAuthority());
      }
      if (element.hasSeriesElement()) {
        composeString("series", element.getSeriesElement());
      }
      if (element.hasSeriesDosesElement()) {
        composePositiveInt("seriesDoses", element.getSeriesDosesElement());
      }
      if (element.hasDoseTarget()) {
        composeCodeableConcept("doseTarget", element.getDoseTarget());
      }
      if (element.hasDoseStatus()) {
        composeCodeableConcept("doseStatus", element.getDoseStatus());
      }
      if (element.hasDoseStatusReason()) {
        composeCodeableConcept("doseStatusReason", element.getDoseStatusReason());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationRecommendation(String name, ImmunizationRecommendation element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasRecommendation()) { 
        for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent e : element.getRecommendation()) 
          composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent("recommendation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasVaccineType()) {
        composeCodeableConcept("vaccineType", element.getVaccineType());
      }
      if (element.hasDoseNumberElement()) {
        composePositiveInt("doseNumber", element.getDoseNumberElement());
      }
      if (element.hasForecastStatus()) {
        composeCodeableConcept("forecastStatus", element.getForecastStatus());
      }
      if (element.hasDateCriterion()) { 
        for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent e : element.getDateCriterion()) 
          composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent("dateCriterion", e);
      }
      if (element.hasProtocol()) {
        composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent("protocol", element.getProtocol());
      }
      if (element.hasSupportingImmunization()) { 
        for (Reference e : element.getSupportingImmunization()) 
          composeReference("supportingImmunization", e);
      }
      if (element.hasSupportingPatientInformation()) { 
        for (Reference e : element.getSupportingPatientInformation()) 
          composeReference("supportingPatientInformation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasValueElement()) {
        composeDateTime("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDoseSequenceElement()) {
        composeInteger("doseSequence", element.getDoseSequenceElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasAuthority()) {
        composeReference("authority", element.getAuthority());
      }
      if (element.hasSeriesElement()) {
        composeString("series", element.getSeriesElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuide(String name, ImplementationGuide element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasUseContext()) { 
        for (CodeableConcept e : element.getUseContext()) 
          composeCodeableConcept("useContext", e);
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (ImplementationGuide.ImplementationGuideContactComponent e : element.getContact()) 
          composeImplementationGuideImplementationGuideContactComponent("contact", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasCopyrightElement()) {
        composeString("copyright", element.getCopyrightElement());
      }
      if (element.hasCode()) { 
        for (Coding e : element.getCode()) 
          composeCoding("code", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasFhirVersionElement()) {
        composeId("fhirVersion", element.getFhirVersionElement());
      }
      if (element.hasPage()) { 
        for (ImplementationGuide.ImplementationGuidePageComponent e : element.getPage()) 
          composeImplementationGuideImplementationGuidePageComponent("page", e);
      }
      if (element.hasPackage()) { 
        for (ImplementationGuide.ImplementationGuidePackageComponent e : element.getPackage()) 
          composeImplementationGuideImplementationGuidePackageComponent("package", e);
      }
      if (element.hasDefault()) { 
        for (ImplementationGuide.ImplementationGuideDefaultComponent e : element.getDefault()) 
          composeImplementationGuideImplementationGuideDefaultComponent("default", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuideImplementationGuideContactComponent(String name, ImplementationGuide.ImplementationGuideContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuideImplementationGuidePageComponent(String name, ImplementationGuide.ImplementationGuidePageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSourceElement()) {
        composeUri("source", element.getSourceElement());
      }
      if (element.hasPurposeElement())
        composeEnumeration("purpose", element.getPurposeElement(), new ImplementationGuide.PagePurposeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuideImplementationGuidePackageComponent(String name, ImplementationGuide.ImplementationGuidePackageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasItem()) { 
        for (ImplementationGuide.ImplementationGuidePackageItemComponent e : element.getItem()) 
          composeImplementationGuideImplementationGuidePackageItemComponent("item", e);
      }
      if (element.hasPage()) { 
        for (ImplementationGuide.ImplementationGuidePageComponent e : element.getPage()) 
          composeImplementationGuideImplementationGuidePageComponent("page", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuideImplementationGuidePackageItemComponent(String name, ImplementationGuide.ImplementationGuidePackageItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasContent()) {
        composeReference("content", element.getContent());
      }
      if (element.hasExample()) { 
        for (ImplementationGuide.ImplementationGuidePackageItemExampleComponent e : element.getExample()) 
          composeImplementationGuideImplementationGuidePackageItemExampleComponent("example", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuideImplementationGuidePackageItemExampleComponent(String name, ImplementationGuide.ImplementationGuidePackageItemExampleComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeImplementationGuideImplementationGuideDefaultComponent(String name, ImplementationGuide.ImplementationGuideDefaultComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement()) {
        composeCode("type", element.getTypeElement());
      }
      if (element.hasProfile()) {
        composeReference("profile", element.getProfile());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasSource()) {
        composeReference("source", element.getSource());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new List_.ListStatusEnumFactory());
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasOrderedBy()) {
        composeCodeableConcept("orderedBy", element.getOrderedBy());
      }
      if (element.hasModeElement())
        composeEnumeration("mode", element.getModeElement(), new List_.ListModeEnumFactory());
      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      if (element.hasEntry()) { 
        for (List_.ListEntryComponent e : element.getEntry()) 
          composeList_ListEntryComponent("entry", e);
      }
      if (element.hasEmptyReason()) {
        composeCodeableConcept("emptyReason", element.getEmptyReason());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeList_ListEntryComponent(String name, List_.ListEntryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasFlag()) { 
        for (CodeableConcept e : element.getFlag()) 
          composeCodeableConcept("flag", e);
      }
      if (element.hasDeletedElement()) {
        composeBoolean("deleted", element.getDeletedElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasItem()) {
        composeReference("item", element.getItem());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasModeElement())
        composeEnumeration("mode", element.getModeElement(), new Location.LocationModeEnumFactory());
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasAddress()) {
        composeAddress("address", element.getAddress());
      }
      if (element.hasPhysicalType()) {
        composeCodeableConcept("physicalType", element.getPhysicalType());
      }
      if (element.hasPosition()) {
        composeLocationLocationPositionComponent("position", element.getPosition());
      }
      if (element.hasManagingOrganization()) {
        composeReference("managingOrganization", element.getManagingOrganization());
      }
      if (element.hasPartOf()) {
        composeReference("partOf", element.getPartOf());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Location.LocationStatusEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLongitudeElement()) {
        composeDecimal("longitude", element.getLongitudeElement());
      }
      if (element.hasLatitudeElement()) {
        composeDecimal("latitude", element.getLatitudeElement());
      }
      if (element.hasAltitudeElement()) {
        composeDecimal("altitude", element.getAltitudeElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Media.DigitalMediaTypeEnumFactory());
      if (element.hasSubtype()) {
        composeCodeableConcept("subtype", element.getSubtype());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasOperator()) {
        composeReference("operator", element.getOperator());
      }
      if (element.hasView()) {
        composeCodeableConcept("view", element.getView());
      }
      if (element.hasDeviceNameElement()) {
        composeString("deviceName", element.getDeviceNameElement());
      }
      if (element.hasHeightElement()) {
        composePositiveInt("height", element.getHeightElement());
      }
      if (element.hasWidthElement()) {
        composePositiveInt("width", element.getWidthElement());
      }
      if (element.hasFramesElement()) {
        composePositiveInt("frames", element.getFramesElement());
      }
      if (element.hasDurationElement()) {
        composeUnsignedInt("duration", element.getDurationElement());
      }
      if (element.hasContent()) {
        composeAttachment("content", element.getContent());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasIsBrandElement()) {
        composeBoolean("isBrand", element.getIsBrandElement());
      }
      if (element.hasManufacturer()) {
        composeReference("manufacturer", element.getManufacturer());
      }
      if (element.hasKindElement())
        composeEnumeration("kind", element.getKindElement(), new Medication.MedicationKindEnumFactory());
      if (element.hasProduct()) {
        composeMedicationMedicationProductComponent("product", element.getProduct());
      }
      if (element.hasPackage()) {
        composeMedicationMedicationPackageComponent("package", element.getPackage());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationMedicationProductComponent(String name, Medication.MedicationProductComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasForm()) {
        composeCodeableConcept("form", element.getForm());
      }
      if (element.hasIngredient()) { 
        for (Medication.MedicationProductIngredientComponent e : element.getIngredient()) 
          composeMedicationMedicationProductIngredientComponent("ingredient", e);
      }
      if (element.hasBatch()) { 
        for (Medication.MedicationProductBatchComponent e : element.getBatch()) 
          composeMedicationMedicationProductBatchComponent("batch", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationMedicationProductIngredientComponent(String name, Medication.MedicationProductIngredientComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasItem()) {
        composeReference("item", element.getItem());
      }
      if (element.hasAmount()) {
        composeRatio("amount", element.getAmount());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationMedicationProductBatchComponent(String name, Medication.MedicationProductBatchComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLotNumberElement()) {
        composeString("lotNumber", element.getLotNumberElement());
      }
      if (element.hasExpirationDateElement()) {
        composeDateTime("expirationDate", element.getExpirationDateElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationMedicationPackageComponent(String name, Medication.MedicationPackageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasContainer()) {
        composeCodeableConcept("container", element.getContainer());
      }
      if (element.hasContent()) { 
        for (Medication.MedicationPackageContentComponent e : element.getContent()) 
          composeMedicationMedicationPackageContentComponent("content", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationMedicationPackageContentComponent(String name, Medication.MedicationPackageContentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasItem()) {
        composeReference("item", element.getItem());
      }
      if (element.hasAmount()) {
        composeQuantity("amount", element.getAmount());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationAdministration(String name, MedicationAdministration element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new MedicationAdministration.MedicationAdministrationStatusEnumFactory());
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasPractitioner()) {
        composeReference("practitioner", element.getPractitioner());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasPrescription()) {
        composeReference("prescription", element.getPrescription());
      }
      if (element.hasWasNotGivenElement()) {
        composeBoolean("wasNotGiven", element.getWasNotGivenElement());
      }
      if (element.hasReasonNotGiven()) { 
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept("reasonNotGiven", e);
      }
      if (element.hasReasonGiven()) { 
        for (CodeableConcept e : element.getReasonGiven()) 
          composeCodeableConcept("reasonGiven", e);
      }
      if (element.hasEffectiveTime()) {
        composeType("effectiveTime", element.getEffectiveTime());
      }      if (element.hasMedication()) {
        composeType("medication", element.getMedication());
      }      if (element.hasDevice()) { 
        for (Reference e : element.getDevice()) 
          composeReference("device", e);
      }
      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      if (element.hasDosage()) {
        composeMedicationAdministrationMedicationAdministrationDosageComponent("dosage", element.getDosage());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationAdministrationMedicationAdministrationDosageComponent(String name, MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasSite()) {
        composeCodeableConcept("site", element.getSite());
      }
      if (element.hasRoute()) {
        composeCodeableConcept("route", element.getRoute());
      }
      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasRate()) {
        composeRatio("rate", element.getRate());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationDispense(String name, MedicationDispense element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory());
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasDispenser()) {
        composeReference("dispenser", element.getDispenser());
      }
      if (element.hasAuthorizingPrescription()) { 
        for (Reference e : element.getAuthorizingPrescription()) 
          composeReference("authorizingPrescription", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasDaysSupply()) {
        composeQuantity("daysSupply", element.getDaysSupply());
      }
      if (element.hasMedication()) {
        composeType("medication", element.getMedication());
      }      if (element.hasWhenPreparedElement()) {
        composeDateTime("whenPrepared", element.getWhenPreparedElement());
      }
      if (element.hasWhenHandedOverElement()) {
        composeDateTime("whenHandedOver", element.getWhenHandedOverElement());
      }
      if (element.hasDestination()) {
        composeReference("destination", element.getDestination());
      }
      if (element.hasReceiver()) { 
        for (Reference e : element.getReceiver()) 
          composeReference("receiver", e);
      }
      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      if (element.hasDosageInstruction()) { 
        for (MedicationDispense.MedicationDispenseDosageInstructionComponent e : element.getDosageInstruction()) 
          composeMedicationDispenseMedicationDispenseDosageInstructionComponent("dosageInstruction", e);
      }
      if (element.hasSubstitution()) {
        composeMedicationDispenseMedicationDispenseSubstitutionComponent("substitution", element.getSubstitution());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationDispenseMedicationDispenseDosageInstructionComponent(String name, MedicationDispense.MedicationDispenseDosageInstructionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasAdditionalInstructions()) {
        composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      }
      if (element.hasSchedule()) {
        composeType("schedule", element.getSchedule());
      }      if (element.hasAsNeeded()) {
        composeType("asNeeded", element.getAsNeeded());
      }      if (element.hasSite()) {
        composeCodeableConcept("site", element.getSite());
      }
      if (element.hasRoute()) {
        composeCodeableConcept("route", element.getRoute());
      }
      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasDose()) {
        composeType("dose", element.getDose());
      }      if (element.hasRate()) {
        composeRatio("rate", element.getRate());
      }
      if (element.hasMaxDosePerPeriod()) {
        composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationDispenseMedicationDispenseSubstitutionComponent(String name, MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasReason()) { 
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept("reason", e);
      }
      if (element.hasResponsibleParty()) { 
        for (Reference e : element.getResponsibleParty()) 
          composeReference("responsibleParty", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationPrescription(String name, MedicationPrescription element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasDateWrittenElement()) {
        composeDateTime("dateWritten", element.getDateWrittenElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory());
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasPrescriber()) {
        composeReference("prescriber", element.getPrescriber());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasReason()) {
        composeType("reason", element.getReason());
      }      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      if (element.hasMedication()) {
        composeType("medication", element.getMedication());
      }      if (element.hasDosageInstruction()) { 
        for (MedicationPrescription.MedicationPrescriptionDosageInstructionComponent e : element.getDosageInstruction()) 
          composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent("dosageInstruction", e);
      }
      if (element.hasDispense()) {
        composeMedicationPrescriptionMedicationPrescriptionDispenseComponent("dispense", element.getDispense());
      }
      if (element.hasSubstitution()) {
        composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent("substitution", element.getSubstitution());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(String name, MedicationPrescription.MedicationPrescriptionDosageInstructionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasAdditionalInstructions()) {
        composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      }
      if (element.hasScheduled()) {
        composeType("scheduled", element.getScheduled());
      }      if (element.hasAsNeeded()) {
        composeType("asNeeded", element.getAsNeeded());
      }      if (element.hasSite()) {
        composeCodeableConcept("site", element.getSite());
      }
      if (element.hasRoute()) {
        composeCodeableConcept("route", element.getRoute());
      }
      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasDose()) {
        composeType("dose", element.getDose());
      }      if (element.hasRate()) {
        composeRatio("rate", element.getRate());
      }
      if (element.hasMaxDosePerPeriod()) {
        composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationPrescriptionMedicationPrescriptionDispenseComponent(String name, MedicationPrescription.MedicationPrescriptionDispenseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasMedication()) {
        composeType("medication", element.getMedication());
      }      if (element.hasValidityPeriod()) {
        composePeriod("validityPeriod", element.getValidityPeriod());
      }
      if (element.hasNumberOfRepeatsAllowedElement()) {
        composePositiveInt("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedElement());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasExpectedSupplyDuration()) {
        composeDuration("expectedSupplyDuration", element.getExpectedSupplyDuration());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(String name, MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasReason()) {
        composeCodeableConcept("reason", element.getReason());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationStatement(String name, MedicationStatement element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasInformationSource()) {
        composeReference("informationSource", element.getInformationSource());
      }
      if (element.hasDateAssertedElement()) {
        composeDateTime("dateAsserted", element.getDateAssertedElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new MedicationStatement.MedicationStatementStatusEnumFactory());
      if (element.hasWasNotTakenElement()) {
        composeBoolean("wasNotTaken", element.getWasNotTakenElement());
      }
      if (element.hasReasonNotTaken()) { 
        for (CodeableConcept e : element.getReasonNotTaken()) 
          composeCodeableConcept("reasonNotTaken", e);
      }
      if (element.hasReasonForUse()) {
        composeType("reasonForUse", element.getReasonForUse());
      }      if (element.hasEffective()) {
        composeType("effective", element.getEffective());
      }      if (element.hasNoteElement()) {
        composeString("note", element.getNoteElement());
      }
      if (element.hasMedication()) {
        composeType("medication", element.getMedication());
      }      if (element.hasDosage()) { 
        for (MedicationStatement.MedicationStatementDosageComponent e : element.getDosage()) 
          composeMedicationStatementMedicationStatementDosageComponent("dosage", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMedicationStatementMedicationStatementDosageComponent(String name, MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasSchedule()) {
        composeTiming("schedule", element.getSchedule());
      }
      if (element.hasAsNeeded()) {
        composeType("asNeeded", element.getAsNeeded());
      }      if (element.hasSite()) {
        composeCodeableConcept("site", element.getSite());
      }
      if (element.hasRoute()) {
        composeCodeableConcept("route", element.getRoute());
      }
      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasRate()) {
        composeRatio("rate", element.getRate());
      }
      if (element.hasMaxDosePerPeriod()) {
        composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMessageHeader(String name, MessageHeader element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifierElement()) {
        composeId("identifier", element.getIdentifierElement());
      }
      if (element.hasTimestampElement()) {
        composeInstant("timestamp", element.getTimestampElement());
      }
      if (element.hasEvent()) {
        composeCoding("event", element.getEvent());
      }
      if (element.hasResponse()) {
        composeMessageHeaderMessageHeaderResponseComponent("response", element.getResponse());
      }
      if (element.hasSource()) {
        composeMessageHeaderMessageSourceComponent("source", element.getSource());
      }
      if (element.hasDestination()) { 
        for (MessageHeader.MessageDestinationComponent e : element.getDestination()) 
          composeMessageHeaderMessageDestinationComponent("destination", e);
      }
      if (element.hasEnterer()) {
        composeReference("enterer", element.getEnterer());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasReceiver()) {
        composeReference("receiver", element.getReceiver());
      }
      if (element.hasResponsible()) {
        composeReference("responsible", element.getResponsible());
      }
      if (element.hasReason()) {
        composeCodeableConcept("reason", element.getReason());
      }
      if (element.hasData()) { 
        for (Reference e : element.getData()) 
          composeReference("data", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMessageHeaderMessageHeaderResponseComponent(String name, MessageHeader.MessageHeaderResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifierElement()) {
        composeId("identifier", element.getIdentifierElement());
      }
      if (element.hasCodeElement())
        composeEnumeration("code", element.getCodeElement(), new MessageHeader.ResponseTypeEnumFactory());
      if (element.hasDetails()) {
        composeReference("details", element.getDetails());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMessageHeaderMessageSourceComponent(String name, MessageHeader.MessageSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasSoftwareElement()) {
        composeString("software", element.getSoftwareElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasContact()) {
        composeContactPoint("contact", element.getContact());
      }
      if (element.hasEndpointElement()) {
        composeUri("endpoint", element.getEndpointElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeMessageHeaderMessageDestinationComponent(String name, MessageHeader.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasEndpointElement()) {
        composeUri("endpoint", element.getEndpointElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNamingSystem(String name, NamingSystem element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new NamingSystem.NamingSystemTypeEnumFactory());
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasCountryElement()) {
        composeCode("country", element.getCountryElement());
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasResponsibleElement()) {
        composeString("responsible", element.getResponsibleElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasUsageElement()) {
        composeString("usage", element.getUsageElement());
      }
      if (element.hasUniqueId()) { 
        for (NamingSystem.NamingSystemUniqueIdComponent e : element.getUniqueId()) 
          composeNamingSystemNamingSystemUniqueIdComponent("uniqueId", e);
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (NamingSystem.NamingSystemContactComponent e : element.getContact()) 
          composeNamingSystemNamingSystemContactComponent("contact", e);
      }
      if (element.hasReplacedBy()) {
        composeReference("replacedBy", element.getReplacedBy());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNamingSystemNamingSystemUniqueIdComponent(String name, NamingSystem.NamingSystemUniqueIdComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new NamingSystem.NamingSystemIdentifierTypeEnumFactory());
      if (element.hasValueElement()) {
        composeString("value", element.getValueElement());
      }
      if (element.hasPreferredElement()) {
        composeBoolean("preferred", element.getPreferredElement());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNamingSystemNamingSystemContactComponent(String name, NamingSystem.NamingSystemContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrder(String name, NutritionOrder element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasOrderer()) {
        composeReference("orderer", element.getOrderer());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasDateTimeElement()) {
        composeDateTime("dateTime", element.getDateTimeElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new NutritionOrder.NutritionOrderStatusEnumFactory());
      if (element.hasAllergyIntolerance()) { 
        for (Reference e : element.getAllergyIntolerance()) 
          composeReference("allergyIntolerance", e);
      }
      if (element.hasFoodPreferenceModifier()) { 
        for (CodeableConcept e : element.getFoodPreferenceModifier()) 
          composeCodeableConcept("foodPreferenceModifier", e);
      }
      if (element.hasExcludeFoodModifier()) { 
        for (CodeableConcept e : element.getExcludeFoodModifier()) 
          composeCodeableConcept("excludeFoodModifier", e);
      }
      if (element.hasOralDiet()) {
        composeNutritionOrderNutritionOrderOralDietComponent("oralDiet", element.getOralDiet());
      }
      if (element.hasSupplement()) { 
        for (NutritionOrder.NutritionOrderSupplementComponent e : element.getSupplement()) 
          composeNutritionOrderNutritionOrderSupplementComponent("supplement", e);
      }
      if (element.hasEnteralFormula()) {
        composeNutritionOrderNutritionOrderEnteralFormulaComponent("enteralFormula", element.getEnteralFormula());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrderNutritionOrderOralDietComponent(String name, NutritionOrder.NutritionOrderOralDietComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) { 
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept("type", e);
      }
      if (element.hasSchedule()) { 
        for (Timing e : element.getSchedule()) 
          composeTiming("schedule", e);
      }
      if (element.hasNutrient()) { 
        for (NutritionOrder.NutritionOrderOralDietNutrientComponent e : element.getNutrient()) 
          composeNutritionOrderNutritionOrderOralDietNutrientComponent("nutrient", e);
      }
      if (element.hasTexture()) { 
        for (NutritionOrder.NutritionOrderOralDietTextureComponent e : element.getTexture()) 
          composeNutritionOrderNutritionOrderOralDietTextureComponent("texture", e);
      }
      if (element.hasFluidConsistencyType()) { 
        for (CodeableConcept e : element.getFluidConsistencyType()) 
          composeCodeableConcept("fluidConsistencyType", e);
      }
      if (element.hasInstructionElement()) {
        composeString("instruction", element.getInstructionElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrderNutritionOrderOralDietNutrientComponent(String name, NutritionOrder.NutritionOrderOralDietNutrientComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasModifier()) {
        composeCodeableConcept("modifier", element.getModifier());
      }
      if (element.hasAmount()) {
        composeQuantity("amount", element.getAmount());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrderNutritionOrderOralDietTextureComponent(String name, NutritionOrder.NutritionOrderOralDietTextureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasModifier()) {
        composeCodeableConcept("modifier", element.getModifier());
      }
      if (element.hasFoodType()) {
        composeCodeableConcept("foodType", element.getFoodType());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrderNutritionOrderSupplementComponent(String name, NutritionOrder.NutritionOrderSupplementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasProductNameElement()) {
        composeString("productName", element.getProductNameElement());
      }
      if (element.hasSchedule()) { 
        for (Timing e : element.getSchedule()) 
          composeTiming("schedule", e);
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasInstructionElement()) {
        composeString("instruction", element.getInstructionElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrderNutritionOrderEnteralFormulaComponent(String name, NutritionOrder.NutritionOrderEnteralFormulaComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasBaseFormulaType()) {
        composeCodeableConcept("baseFormulaType", element.getBaseFormulaType());
      }
      if (element.hasBaseFormulaProductNameElement()) {
        composeString("baseFormulaProductName", element.getBaseFormulaProductNameElement());
      }
      if (element.hasAdditiveType()) {
        composeCodeableConcept("additiveType", element.getAdditiveType());
      }
      if (element.hasAdditiveProductNameElement()) {
        composeString("additiveProductName", element.getAdditiveProductNameElement());
      }
      if (element.hasCaloricDensity()) {
        composeQuantity("caloricDensity", element.getCaloricDensity());
      }
      if (element.hasRouteofAdministration()) {
        composeCodeableConcept("routeofAdministration", element.getRouteofAdministration());
      }
      if (element.hasAdministration()) { 
        for (NutritionOrder.NutritionOrderEnteralFormulaAdministrationComponent e : element.getAdministration()) 
          composeNutritionOrderNutritionOrderEnteralFormulaAdministrationComponent("administration", e);
      }
      if (element.hasMaxVolumeToDeliver()) {
        composeQuantity("maxVolumeToDeliver", element.getMaxVolumeToDeliver());
      }
      if (element.hasAdministrationInstructionElement()) {
        composeString("administrationInstruction", element.getAdministrationInstructionElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeNutritionOrderNutritionOrderEnteralFormulaAdministrationComponent(String name, NutritionOrder.NutritionOrderEnteralFormulaAdministrationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSchedule()) {
        composeTiming("schedule", element.getSchedule());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasRate()) {
        composeType("rate", element.getRate());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeObservation(String name, Observation element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      if (element.hasDataAbsentReason()) {
        composeCodeableConcept("dataAbsentReason", element.getDataAbsentReason());
      }
      if (element.hasInterpretation()) {
        composeCodeableConcept("interpretation", element.getInterpretation());
      }
      if (element.hasCommentsElement()) {
        composeString("comments", element.getCommentsElement());
      }
      if (element.hasEffective()) {
        composeType("effective", element.getEffective());
      }      if (element.hasIssuedElement()) {
        composeInstant("issued", element.getIssuedElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Observation.ObservationStatusEnumFactory());
      if (element.hasReliabilityElement())
        composeEnumeration("reliability", element.getReliabilityElement(), new Observation.ObservationReliabilityEnumFactory());
      if (element.hasBodySite()) {
        composeType("bodySite", element.getBodySite());
      }      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasSpecimen()) {
        composeReference("specimen", element.getSpecimen());
      }
      if (element.hasPerformer()) { 
        for (Reference e : element.getPerformer()) 
          composeReference("performer", e);
      }
      if (element.hasDevice()) {
        composeReference("device", element.getDevice());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasReferenceRange()) { 
        for (Observation.ObservationReferenceRangeComponent e : element.getReferenceRange()) 
          composeObservationObservationReferenceRangeComponent("referenceRange", e);
      }
      if (element.hasRelated()) { 
        for (Observation.ObservationRelatedComponent e : element.getRelated()) 
          composeObservationObservationRelatedComponent("related", e);
      }
      if (element.hasComponent()) { 
        for (Observation.ObservationComponentComponent e : element.getComponent()) 
          composeObservationObservationComponentComponent("component", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeObservationObservationReferenceRangeComponent(String name, Observation.ObservationReferenceRangeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLow()) {
        composeQuantity("low", element.getLow());
      }
      if (element.hasHigh()) {
        composeQuantity("high", element.getHigh());
      }
      if (element.hasMeaning()) {
        composeCodeableConcept("meaning", element.getMeaning());
      }
      if (element.hasAge()) {
        composeRange("age", element.getAge());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeObservationObservationRelatedComponent(String name, Observation.ObservationRelatedComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Observation.ObservationRelationshipTypeEnumFactory());
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeObservationObservationComponentComponent(String name, Observation.ObservationComponentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      if (element.hasDataAbsentReason()) {
        composeCodeableConcept("dataAbsentReason", element.getDataAbsentReason());
      }
      if (element.hasReferenceRange()) { 
        for (Observation.ObservationReferenceRangeComponent e : element.getReferenceRange()) 
          composeObservationObservationReferenceRangeComponent("referenceRange", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOperationDefinition(String name, OperationDefinition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (OperationDefinition.OperationDefinitionContactComponent e : element.getContact()) 
          composeOperationDefinitionOperationDefinitionContactComponent("contact", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasKindElement())
        composeEnumeration("kind", element.getKindElement(), new OperationDefinition.OperationKindEnumFactory());
      if (element.hasIdempotentElement()) {
        composeBoolean("idempotent", element.getIdempotentElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      if (element.hasBase()) {
        composeReference("base", element.getBase());
      }
      if (element.hasSystemElement()) {
        composeBoolean("system", element.getSystemElement());
      }
      if (element.hasType()) { 
        for (CodeType e : element.getType()) 
          composeCode("type", e);
      }
      if (element.hasInstanceElement()) {
        composeBoolean("instance", element.getInstanceElement());
      }
      if (element.hasParameter()) { 
        for (OperationDefinition.OperationDefinitionParameterComponent e : element.getParameter()) 
          composeOperationDefinitionOperationDefinitionParameterComponent("parameter", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOperationDefinitionOperationDefinitionContactComponent(String name, OperationDefinition.OperationDefinitionContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOperationDefinitionOperationDefinitionParameterComponent(String name, OperationDefinition.OperationDefinitionParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeCode("name", element.getNameElement());
      }
      if (element.hasUseElement())
        composeEnumeration("use", element.getUseElement(), new OperationDefinition.OperationParameterUseEnumFactory());
      if (element.hasMinElement()) {
        composeInteger("min", element.getMinElement());
      }
      if (element.hasMaxElement()) {
        composeString("max", element.getMaxElement());
      }
      if (element.hasDocumentationElement()) {
        composeString("documentation", element.getDocumentationElement());
      }
      if (element.hasTypeElement()) {
        composeCode("type", element.getTypeElement());
      }
      if (element.hasProfile()) {
        composeReference("profile", element.getProfile());
      }
      if (element.hasBinding()) {
        composeOperationDefinitionOperationDefinitionParameterBindingComponent("binding", element.getBinding());
      }
      if (element.hasPart()) { 
        for (OperationDefinition.OperationDefinitionParameterComponent e : element.getPart()) 
          composeOperationDefinitionOperationDefinitionParameterComponent("part", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOperationDefinitionOperationDefinitionParameterBindingComponent(String name, OperationDefinition.OperationDefinitionParameterBindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasStrengthElement())
        composeEnumeration("strength", element.getStrengthElement(), new Enumerations.BindingStrengthEnumFactory());
      if (element.hasValueSet()) {
        composeType("valueSet", element.getValueSet());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOperationOutcome(String name, OperationOutcome element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIssue()) { 
        for (OperationOutcome.OperationOutcomeIssueComponent e : element.getIssue()) 
          composeOperationOutcomeOperationOutcomeIssueComponent("issue", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOperationOutcomeOperationOutcomeIssueComponent(String name, OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSeverityElement())
        composeEnumeration("severity", element.getSeverityElement(), new OperationOutcome.IssueSeverityEnumFactory());
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasDetailsElement()) {
        composeString("details", element.getDetailsElement());
      }
      if (element.hasLocation()) { 
        for (StringType e : element.getLocation()) 
          composeString("location", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasSource()) {
        composeReference("source", element.getSource());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasReason()) {
        composeType("reason", element.getReason());
      }      if (element.hasWhen()) {
        composeOrderOrderWhenComponent("when", element.getWhen());
      }
      if (element.hasDetail()) { 
        for (Reference e : element.getDetail()) 
          composeReference("detail", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasSchedule()) {
        composeTiming("schedule", element.getSchedule());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOrderResponse(String name, OrderResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasWho()) {
        composeReference("who", element.getWho());
      }
      if (element.hasOrderStatusElement())
        composeEnumeration("orderStatus", element.getOrderStatusElement(), new OrderResponse.OrderStatusEnumFactory());
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasFulfillment()) { 
        for (Reference e : element.getFulfillment()) 
          composeReference("fulfillment", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOrganization(String name, Organization element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasAddress()) { 
        for (Address e : element.getAddress()) 
          composeAddress("address", e);
      }
      if (element.hasPartOf()) {
        composeReference("partOf", element.getPartOf());
      }
      if (element.hasContact()) { 
        for (Organization.OrganizationContactComponent e : element.getContact()) 
          composeOrganizationOrganizationContactComponent("contact", e);
      }
      if (element.hasActiveElement()) {
        composeBoolean("active", element.getActiveElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeOrganizationOrganizationContactComponent(String name, Organization.OrganizationContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasPurpose()) {
        composeCodeableConcept("purpose", element.getPurpose());
      }
      if (element.hasName()) {
        composeHumanName("name", element.getName());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasAddress()) {
        composeAddress("address", element.getAddress());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasName()) { 
        for (HumanName e : element.getName()) 
          composeHumanName("name", e);
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasGenderElement())
        composeEnumeration("gender", element.getGenderElement(), new Enumerations.AdministrativeGenderEnumFactory());
      if (element.hasBirthDateElement()) {
        composeDate("birthDate", element.getBirthDateElement());
      }
      if (element.hasDeceased()) {
        composeType("deceased", element.getDeceased());
      }      if (element.hasAddress()) { 
        for (Address e : element.getAddress()) 
          composeAddress("address", e);
      }
      if (element.hasMaritalStatus()) {
        composeCodeableConcept("maritalStatus", element.getMaritalStatus());
      }
      if (element.hasMultipleBirth()) {
        composeType("multipleBirth", element.getMultipleBirth());
      }      if (element.hasPhoto()) { 
        for (Attachment e : element.getPhoto()) 
          composeAttachment("photo", e);
      }
      if (element.hasContact()) { 
        for (Patient.ContactComponent e : element.getContact()) 
          composePatientContactComponent("contact", e);
      }
      if (element.hasAnimal()) {
        composePatientAnimalComponent("animal", element.getAnimal());
      }
      if (element.hasCommunication()) { 
        for (Patient.PatientCommunicationComponent e : element.getCommunication()) 
          composePatientPatientCommunicationComponent("communication", e);
      }
      if (element.hasCareProvider()) { 
        for (Reference e : element.getCareProvider()) 
          composeReference("careProvider", e);
      }
      if (element.hasManagingOrganization()) {
        composeReference("managingOrganization", element.getManagingOrganization());
      }
      if (element.hasLink()) { 
        for (Patient.PatientLinkComponent e : element.getLink()) 
          composePatientPatientLinkComponent("link", e);
      }
      if (element.hasActiveElement()) {
        composeBoolean("active", element.getActiveElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePatientContactComponent(String name, Patient.ContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasRelationship()) { 
        for (CodeableConcept e : element.getRelationship()) 
          composeCodeableConcept("relationship", e);
      }
      if (element.hasName()) {
        composeHumanName("name", element.getName());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasAddress()) {
        composeAddress("address", element.getAddress());
      }
      if (element.hasGenderElement())
        composeEnumeration("gender", element.getGenderElement(), new Enumerations.AdministrativeGenderEnumFactory());
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePatientAnimalComponent(String name, Patient.AnimalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSpecies()) {
        composeCodeableConcept("species", element.getSpecies());
      }
      if (element.hasBreed()) {
        composeCodeableConcept("breed", element.getBreed());
      }
      if (element.hasGenderStatus()) {
        composeCodeableConcept("genderStatus", element.getGenderStatus());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePatientPatientCommunicationComponent(String name, Patient.PatientCommunicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLanguage()) {
        composeCodeableConcept("language", element.getLanguage());
      }
      if (element.hasPreferredElement()) {
        composeBoolean("preferred", element.getPreferredElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePatientPatientLinkComponent(String name, Patient.PatientLinkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasOther()) {
        composeReference("other", element.getOther());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Patient.LinkTypeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePaymentNotice(String name, PaymentNotice element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasResponse()) {
        composeReference("response", element.getResponse());
      }
      if (element.hasPaymentStatus()) {
        composeCoding("paymentStatus", element.getPaymentStatus());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePaymentReconciliation(String name, PaymentReconciliation element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasOutcomeElement())
        composeEnumeration("outcome", element.getOutcomeElement(), new Enumerations.RemittanceOutcomeEnumFactory());
      if (element.hasDispositionElement()) {
        composeString("disposition", element.getDispositionElement());
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequestProvider()) {
        composeReference("requestProvider", element.getRequestProvider());
      }
      if (element.hasRequestOrganization()) {
        composeReference("requestOrganization", element.getRequestOrganization());
      }
      if (element.hasDetail()) { 
        for (PaymentReconciliation.DetailsComponent e : element.getDetail()) 
          composePaymentReconciliationDetailsComponent("detail", e);
      }
      if (element.hasForm()) {
        composeCoding("form", element.getForm());
      }
      if (element.hasTotal()) {
        composeMoney("total", element.getTotal());
      }
      if (element.hasNote()) { 
        for (PaymentReconciliation.NotesComponent e : element.getNote()) 
          composePaymentReconciliationNotesComponent("note", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePaymentReconciliationDetailsComponent(String name, PaymentReconciliation.DetailsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasResponce()) {
        composeReference("responce", element.getResponce());
      }
      if (element.hasSubmitter()) {
        composeReference("submitter", element.getSubmitter());
      }
      if (element.hasPayee()) {
        composeReference("payee", element.getPayee());
      }
      if (element.hasDateElement()) {
        composeDate("date", element.getDateElement());
      }
      if (element.hasAmount()) {
        composeMoney("amount", element.getAmount());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePaymentReconciliationNotesComponent(String name, PaymentReconciliation.NotesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePerson(String name, Person element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasName()) { 
        for (HumanName e : element.getName()) 
          composeHumanName("name", e);
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasGenderElement())
        composeEnumeration("gender", element.getGenderElement(), new Enumerations.AdministrativeGenderEnumFactory());
      if (element.hasBirthDateElement()) {
        composeDateTime("birthDate", element.getBirthDateElement());
      }
      if (element.hasAddress()) { 
        for (Address e : element.getAddress()) 
          composeAddress("address", e);
      }
      if (element.hasPhoto()) {
        composeAttachment("photo", element.getPhoto());
      }
      if (element.hasManagingOrganization()) {
        composeReference("managingOrganization", element.getManagingOrganization());
      }
      if (element.hasActiveElement()) {
        composeBoolean("active", element.getActiveElement());
      }
      if (element.hasLink()) { 
        for (Person.PersonLinkComponent e : element.getLink()) 
          composePersonPersonLinkComponent("link", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePersonPersonLinkComponent(String name, Person.PersonLinkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasAssuranceElement())
        composeEnumeration("assurance", element.getAssuranceElement(), new Person.IdentityAssuranceLevelEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePractitioner(String name, Practitioner element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasName()) {
        composeHumanName("name", element.getName());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasAddress()) { 
        for (Address e : element.getAddress()) 
          composeAddress("address", e);
      }
      if (element.hasGenderElement())
        composeEnumeration("gender", element.getGenderElement(), new Enumerations.AdministrativeGenderEnumFactory());
      if (element.hasBirthDateElement()) {
        composeDate("birthDate", element.getBirthDateElement());
      }
      if (element.hasPhoto()) { 
        for (Attachment e : element.getPhoto()) 
          composeAttachment("photo", e);
      }
      if (element.hasPractitionerRole()) { 
        for (Practitioner.PractitionerPractitionerRoleComponent e : element.getPractitionerRole()) 
          composePractitionerPractitionerPractitionerRoleComponent("practitionerRole", e);
      }
      if (element.hasQualification()) { 
        for (Practitioner.PractitionerQualificationComponent e : element.getQualification()) 
          composePractitionerPractitionerQualificationComponent("qualification", e);
      }
      if (element.hasCommunication()) { 
        for (CodeableConcept e : element.getCommunication()) 
          composeCodeableConcept("communication", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePractitionerPractitionerPractitionerRoleComponent(String name, Practitioner.PractitionerPractitionerRoleComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasManagingOrganization()) {
        composeReference("managingOrganization", element.getManagingOrganization());
      }
      if (element.hasRole()) {
        composeCodeableConcept("role", element.getRole());
      }
      if (element.hasSpecialty()) { 
        for (CodeableConcept e : element.getSpecialty()) 
          composeCodeableConcept("specialty", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasLocation()) { 
        for (Reference e : element.getLocation()) 
          composeReference("location", e);
      }
      if (element.hasHealthcareService()) { 
        for (Reference e : element.getHealthcareService()) 
          composeReference("healthcareService", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composePractitionerPractitionerQualificationComponent(String name, Practitioner.PractitionerQualificationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasIssuer()) {
        composeReference("issuer", element.getIssuer());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Procedure.ProcedureStatusEnumFactory());
      if (element.hasCategory()) {
        composeCodeableConcept("category", element.getCategory());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasBodySite()) { 
        for (Procedure.ProcedureBodySiteComponent e : element.getBodySite()) 
          composeProcedureProcedureBodySiteComponent("bodySite", e);
      }
      if (element.hasIndication()) { 
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept("indication", e);
      }
      if (element.hasPerformer()) { 
        for (Procedure.ProcedurePerformerComponent e : element.getPerformer()) 
          composeProcedureProcedurePerformerComponent("performer", e);
      }
      if (element.hasPerformed()) {
        composeType("performed", element.getPerformed());
      }      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasOutcome()) {
        composeCodeableConcept("outcome", element.getOutcome());
      }
      if (element.hasReport()) { 
        for (Reference e : element.getReport()) 
          composeReference("report", e);
      }
      if (element.hasComplication()) { 
        for (CodeableConcept e : element.getComplication()) 
          composeCodeableConcept("complication", e);
      }
      if (element.hasFollowUp()) { 
        for (CodeableConcept e : element.getFollowUp()) 
          composeCodeableConcept("followUp", e);
      }
      if (element.hasRelatedItem()) { 
        for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
          composeProcedureProcedureRelatedItemComponent("relatedItem", e);
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      if (element.hasDevice()) { 
        for (Procedure.ProcedureDeviceComponent e : element.getDevice()) 
          composeProcedureProcedureDeviceComponent("device", e);
      }
      if (element.hasUsed()) { 
        for (Reference e : element.getUsed()) 
          composeReference("used", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedureProcedureBodySiteComponent(String name, Procedure.ProcedureBodySiteComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSite()) {
        composeType("site", element.getSite());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasPerson()) {
        composeReference("person", element.getPerson());
      }
      if (element.hasRole()) {
        composeCodeableConcept("role", element.getRole());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Procedure.ProcedureRelationshipTypeEnumFactory());
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedureProcedureDeviceComponent(String name, Procedure.ProcedureDeviceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasAction()) {
        composeCodeableConcept("action", element.getAction());
      }
      if (element.hasManipulated()) {
        composeReference("manipulated", element.getManipulated());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedureRequest(String name, ProcedureRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasBodySite()) { 
        for (ProcedureRequest.ProcedureRequestBodySiteComponent e : element.getBodySite()) 
          composeProcedureRequestProcedureRequestBodySiteComponent("bodySite", e);
      }
      if (element.hasIndication()) { 
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept("indication", e);
      }
      if (element.hasTiming()) {
        composeType("timing", element.getTiming());
      }      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasPerformer()) {
        composeReference("performer", element.getPerformer());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new ProcedureRequest.ProcedureRequestStatusEnumFactory());
      if (element.hasNotes()) { 
        for (StringType e : element.getNotes()) 
          composeString("notes", e);
      }
      if (element.hasAsNeeded()) {
        composeType("asNeeded", element.getAsNeeded());
      }      if (element.hasOrderedOnElement()) {
        composeDateTime("orderedOn", element.getOrderedOnElement());
      }
      if (element.hasOrderer()) {
        composeReference("orderer", element.getOrderer());
      }
      if (element.hasPriorityElement())
        composeEnumeration("priority", element.getPriorityElement(), new ProcedureRequest.ProcedureRequestPriorityEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcedureRequestProcedureRequestBodySiteComponent(String name, ProcedureRequest.ProcedureRequestBodySiteComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSite()) {
        composeType("site", element.getSite());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcessRequest(String name, ProcessRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasActionElement())
        composeEnumeration("action", element.getActionElement(), new ProcessRequest.ActionListEnumFactory());
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasTarget()) {
        composeReference("target", element.getTarget());
      }
      if (element.hasProvider()) {
        composeReference("provider", element.getProvider());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasResponse()) {
        composeReference("response", element.getResponse());
      }
      if (element.hasNullifyElement()) {
        composeBoolean("nullify", element.getNullifyElement());
      }
      if (element.hasReferenceElement()) {
        composeString("reference", element.getReferenceElement());
      }
      if (element.hasItem()) { 
        for (ProcessRequest.ItemsComponent e : element.getItem()) 
          composeProcessRequestItemsComponent("item", e);
      }
      if (element.hasInclude()) { 
        for (StringType e : element.getInclude()) 
          composeString("include", e);
      }
      if (element.hasExclude()) { 
        for (StringType e : element.getExclude()) 
          composeString("exclude", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcessRequestItemsComponent(String name, ProcessRequest.ItemsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSequenceLinkIdElement()) {
        composeInteger("sequenceLinkId", element.getSequenceLinkIdElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcessResponse(String name, ProcessResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasRequest()) {
        composeReference("request", element.getRequest());
      }
      if (element.hasOutcome()) {
        composeCoding("outcome", element.getOutcome());
      }
      if (element.hasDispositionElement()) {
        composeString("disposition", element.getDispositionElement());
      }
      if (element.hasRuleset()) {
        composeCoding("ruleset", element.getRuleset());
      }
      if (element.hasOriginalRuleset()) {
        composeCoding("originalRuleset", element.getOriginalRuleset());
      }
      if (element.hasCreatedElement()) {
        composeDateTime("created", element.getCreatedElement());
      }
      if (element.hasOrganization()) {
        composeReference("organization", element.getOrganization());
      }
      if (element.hasRequestProvider()) {
        composeReference("requestProvider", element.getRequestProvider());
      }
      if (element.hasRequestOrganization()) {
        composeReference("requestOrganization", element.getRequestOrganization());
      }
      if (element.hasForm()) {
        composeCoding("form", element.getForm());
      }
      if (element.hasNotes()) { 
        for (ProcessResponse.ProcessResponseNotesComponent e : element.getNotes()) 
          composeProcessResponseProcessResponseNotesComponent("notes", e);
      }
      if (element.hasError()) { 
        for (Coding e : element.getError()) 
          composeCoding("error", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProcessResponseProcessResponseNotesComponent(String name, ProcessResponse.ProcessResponseNotesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasTarget()) { 
        for (Reference e : element.getTarget()) 
          composeReference("target", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      if (element.hasRecordedElement()) {
        composeInstant("recorded", element.getRecordedElement());
      }
      if (element.hasReason()) {
        composeCodeableConcept("reason", element.getReason());
      }
      if (element.hasLocation()) {
        composeReference("location", element.getLocation());
      }
      if (element.hasPolicy()) { 
        for (UriType e : element.getPolicy()) 
          composeUri("policy", e);
      }
      if (element.hasAgent()) { 
        for (Provenance.ProvenanceAgentComponent e : element.getAgent()) 
          composeProvenanceProvenanceAgentComponent("agent", e);
      }
      if (element.hasEntity()) { 
        for (Provenance.ProvenanceEntityComponent e : element.getEntity()) 
          composeProvenanceProvenanceEntityComponent("entity", e);
      }
      if (element.hasSignature()) { 
        for (Signature e : element.getSignature()) 
          composeSignature("signature", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProvenanceProvenanceAgentComponent(String name, Provenance.ProvenanceAgentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasRole()) {
        composeCoding("role", element.getRole());
      }
      if (element.hasActor()) {
        composeReference("actor", element.getActor());
      }
      if (element.hasUserId()) {
        composeIdentifier("userId", element.getUserId());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasRoleElement())
        composeEnumeration("role", element.getRoleElement(), new Provenance.ProvenanceEntityRoleEnumFactory());
      if (element.hasType()) {
        composeCoding("type", element.getType());
      }
      if (element.hasReferenceElement()) {
        composeUri("reference", element.getReferenceElement());
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      if (element.hasAgent()) {
        composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Questionnaire.QuestionnaireStatusEnumFactory());
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasGroup()) {
        composeQuestionnaireGroupComponent("group", element.getGroup());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLinkIdElement()) {
        composeString("linkId", element.getLinkIdElement());
      }
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasConcept()) { 
        for (Coding e : element.getConcept()) 
          composeCoding("concept", e);
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasRequiredElement()) {
        composeBoolean("required", element.getRequiredElement());
      }
      if (element.hasRepeatsElement()) {
        composeBoolean("repeats", element.getRepeatsElement());
      }
      if (element.hasGroup()) { 
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent("group", e);
      }
      if (element.hasQuestion()) { 
        for (Questionnaire.QuestionComponent e : element.getQuestion()) 
          composeQuestionnaireQuestionComponent("question", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaireQuestionComponent(String name, Questionnaire.QuestionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLinkIdElement()) {
        composeString("linkId", element.getLinkIdElement());
      }
      if (element.hasConcept()) { 
        for (Coding e : element.getConcept()) 
          composeCoding("concept", e);
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Questionnaire.AnswerFormatEnumFactory());
      if (element.hasRequiredElement()) {
        composeBoolean("required", element.getRequiredElement());
      }
      if (element.hasRepeatsElement()) {
        composeBoolean("repeats", element.getRepeatsElement());
      }
      if (element.hasOptions()) {
        composeReference("options", element.getOptions());
      }
      if (element.hasGroup()) { 
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent("group", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaireAnswers(String name, QuestionnaireAnswers element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasQuestionnaire()) {
        composeReference("questionnaire", element.getQuestionnaire());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory());
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasAuthor()) {
        composeReference("author", element.getAuthor());
      }
      if (element.hasAuthoredElement()) {
        composeDateTime("authored", element.getAuthoredElement());
      }
      if (element.hasSource()) {
        composeReference("source", element.getSource());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasGroup()) {
        composeQuestionnaireAnswersGroupComponent("group", element.getGroup());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaireAnswersGroupComponent(String name, QuestionnaireAnswers.GroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLinkIdElement()) {
        composeString("linkId", element.getLinkIdElement());
      }
      if (element.hasTitleElement()) {
        composeString("title", element.getTitleElement());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasGroup()) { 
        for (QuestionnaireAnswers.GroupComponent e : element.getGroup()) 
          composeQuestionnaireAnswersGroupComponent("group", e);
      }
      if (element.hasQuestion()) { 
        for (QuestionnaireAnswers.QuestionComponent e : element.getQuestion()) 
          composeQuestionnaireAnswersQuestionComponent("question", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaireAnswersQuestionComponent(String name, QuestionnaireAnswers.QuestionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLinkIdElement()) {
        composeString("linkId", element.getLinkIdElement());
      }
      if (element.hasTextElement()) {
        composeString("text", element.getTextElement());
      }
      if (element.hasAnswer()) { 
        for (QuestionnaireAnswers.QuestionAnswerComponent e : element.getAnswer()) 
          composeQuestionnaireAnswersQuestionAnswerComponent("answer", e);
      }
      if (element.hasGroup()) { 
        for (QuestionnaireAnswers.GroupComponent e : element.getGroup()) 
          composeQuestionnaireAnswersGroupComponent("group", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeQuestionnaireAnswersQuestionAnswerComponent(String name, QuestionnaireAnswers.QuestionAnswerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeReferralRequest(String name, ReferralRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new ReferralRequest.ReferralStatusEnumFactory());
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSpecialty()) {
        composeCodeableConcept("specialty", element.getSpecialty());
      }
      if (element.hasPriority()) {
        composeCodeableConcept("priority", element.getPriority());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasRequester()) {
        composeReference("requester", element.getRequester());
      }
      if (element.hasRecipient()) { 
        for (Reference e : element.getRecipient()) 
          composeReference("recipient", e);
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasDateSentElement()) {
        composeDateTime("dateSent", element.getDateSentElement());
      }
      if (element.hasReason()) {
        composeCodeableConcept("reason", element.getReason());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasServiceRequested()) { 
        for (CodeableConcept e : element.getServiceRequested()) 
          composeCodeableConcept("serviceRequested", e);
      }
      if (element.hasSupportingInformation()) { 
        for (Reference e : element.getSupportingInformation()) 
          composeReference("supportingInformation", e);
      }
      if (element.hasFulfillmentTime()) {
        composePeriod("fulfillmentTime", element.getFulfillmentTime());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeRelatedPerson(String name, RelatedPerson element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasRelationship()) {
        composeCodeableConcept("relationship", element.getRelationship());
      }
      if (element.hasName()) {
        composeHumanName("name", element.getName());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      if (element.hasGenderElement())
        composeEnumeration("gender", element.getGenderElement(), new Enumerations.AdministrativeGenderEnumFactory());
      if (element.hasAddress()) {
        composeAddress("address", element.getAddress());
      }
      if (element.hasPhoto()) { 
        for (Attachment e : element.getPhoto()) 
          composeAttachment("photo", e);
      }
      if (element.hasPeriod()) {
        composePeriod("period", element.getPeriod());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeRiskAssessment(String name, RiskAssessment element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasCondition()) {
        composeReference("condition", element.getCondition());
      }
      if (element.hasPerformer()) {
        composeReference("performer", element.getPerformer());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasBasis()) { 
        for (Reference e : element.getBasis()) 
          composeReference("basis", e);
      }
      if (element.hasPrediction()) { 
        for (RiskAssessment.RiskAssessmentPredictionComponent e : element.getPrediction()) 
          composeRiskAssessmentRiskAssessmentPredictionComponent("prediction", e);
      }
      if (element.hasMitigationElement()) {
        composeString("mitigation", element.getMitigationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeRiskAssessmentRiskAssessmentPredictionComponent(String name, RiskAssessment.RiskAssessmentPredictionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasOutcome()) {
        composeCodeableConcept("outcome", element.getOutcome());
      }
      if (element.hasProbability()) {
        composeType("probability", element.getProbability());
      }      if (element.hasRelativeRiskElement()) {
        composeDecimal("relativeRisk", element.getRelativeRiskElement());
      }
      if (element.hasWhen()) {
        composeType("when", element.getWhen());
      }      if (element.hasRationaleElement()) {
        composeString("rationale", element.getRationaleElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSchedule(String name, Schedule element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasType()) { 
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept("type", e);
      }
      if (element.hasActor()) {
        composeReference("actor", element.getActor());
      }
      if (element.hasPlanningHorizon()) {
        composePeriod("planningHorizon", element.getPlanningHorizon());
      }
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSearchParameter(String name, SearchParameter element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (SearchParameter.SearchParameterContactComponent e : element.getContact()) 
          composeSearchParameterSearchParameterContactComponent("contact", e);
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasBaseElement()) {
        composeCode("base", element.getBaseElement());
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Enumerations.SearchParamTypeEnumFactory());
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasXpathElement()) {
        composeString("xpath", element.getXpathElement());
      }
      if (element.hasTarget()) { 
        for (CodeType e : element.getTarget()) 
          composeCode("target", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSearchParameterSearchParameterContactComponent(String name, SearchParameter.SearchParameterContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSlot(String name, Slot element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasSchedule()) {
        composeReference("schedule", element.getSchedule());
      }
      if (element.hasFreeBusyTypeElement())
        composeEnumeration("freeBusyType", element.getFreeBusyTypeElement(), new Slot.SlotStatusEnumFactory());
      if (element.hasStartElement()) {
        composeInstant("start", element.getStartElement());
      }
      if (element.hasEndElement()) {
        composeInstant("end", element.getEndElement());
      }
      if (element.hasOverbookedElement()) {
        composeBoolean("overbooked", element.getOverbookedElement());
      }
      if (element.hasCommentElement()) {
        composeString("comment", element.getCommentElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasParent()) { 
        for (Reference e : element.getParent()) 
          composeReference("parent", e);
      }
      if (element.hasSubject()) {
        composeReference("subject", element.getSubject());
      }
      if (element.hasAccessionIdentifier()) {
        composeIdentifier("accessionIdentifier", element.getAccessionIdentifier());
      }
      if (element.hasReceivedTimeElement()) {
        composeDateTime("receivedTime", element.getReceivedTimeElement());
      }
      if (element.hasCollection()) {
        composeSpecimenSpecimenCollectionComponent("collection", element.getCollection());
      }
      if (element.hasTreatment()) { 
        for (Specimen.SpecimenTreatmentComponent e : element.getTreatment()) 
          composeSpecimenSpecimenTreatmentComponent("treatment", e);
      }
      if (element.hasContainer()) { 
        for (Specimen.SpecimenContainerComponent e : element.getContainer()) 
          composeSpecimenSpecimenContainerComponent("container", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCollector()) {
        composeReference("collector", element.getCollector());
      }
      if (element.hasComment()) { 
        for (StringType e : element.getComment()) 
          composeString("comment", e);
      }
      if (element.hasCollected()) {
        composeType("collected", element.getCollected());
      }      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasMethod()) {
        composeCodeableConcept("method", element.getMethod());
      }
      if (element.hasBodySite()) {
        composeType("bodySite", element.getBodySite());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSpecimenSpecimenTreatmentComponent(String name, Specimen.SpecimenTreatmentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasProcedure()) {
        composeCodeableConcept("procedure", element.getProcedure());
      }
      if (element.hasAdditive()) { 
        for (Reference e : element.getAdditive()) 
          composeReference("additive", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSpecimenSpecimenContainerComponent(String name, Specimen.SpecimenContainerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasCapacity()) {
        composeQuantity("capacity", element.getCapacity());
      }
      if (element.hasSpecimenQuantity()) {
        composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      }
      if (element.hasAdditive()) {
        composeType("additive", element.getAdditive());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeStructureDefinition(String name, StructureDefinition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasUseContext()) { 
        for (CodeableConcept e : element.getUseContext()) 
          composeCodeableConcept("useContext", e);
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (StructureDefinition.StructureDefinitionContactComponent e : element.getContact()) 
          composeStructureDefinitionStructureDefinitionContactComponent("contact", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasCopyrightElement()) {
        composeString("copyright", element.getCopyrightElement());
      }
      if (element.hasCode()) { 
        for (Coding e : element.getCode()) 
          composeCoding("code", e);
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasFhirVersionElement()) {
        composeId("fhirVersion", element.getFhirVersionElement());
      }
      if (element.hasMapping()) { 
        for (StructureDefinition.StructureDefinitionMappingComponent e : element.getMapping()) 
          composeStructureDefinitionStructureDefinitionMappingComponent("mapping", e);
      }
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new StructureDefinition.StructureDefinitionTypeEnumFactory());
      if (element.hasAbstractElement()) {
        composeBoolean("abstract", element.getAbstractElement());
      }
      if (element.hasContextTypeElement())
        composeEnumeration("contextType", element.getContextTypeElement(), new StructureDefinition.ExtensionContextEnumFactory());
      if (element.hasContext()) { 
        for (StringType e : element.getContext()) 
          composeString("context", e);
      }
      if (element.hasBaseElement()) {
        composeUri("base", element.getBaseElement());
      }
      if (element.hasSnapshot()) {
        composeStructureDefinitionStructureDefinitionSnapshotComponent("snapshot", element.getSnapshot());
      }
      if (element.hasDifferential()) {
        composeStructureDefinitionStructureDefinitionDifferentialComponent("differential", element.getDifferential());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeStructureDefinitionStructureDefinitionContactComponent(String name, StructureDefinition.StructureDefinitionContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeStructureDefinitionStructureDefinitionMappingComponent(String name, StructureDefinition.StructureDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentityElement()) {
        composeId("identity", element.getIdentityElement());
      }
      if (element.hasUriElement()) {
        composeUri("uri", element.getUriElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasCommentsElement()) {
        composeString("comments", element.getCommentsElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeStructureDefinitionStructureDefinitionSnapshotComponent(String name, StructureDefinition.StructureDefinitionSnapshotComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasElement()) { 
        for (ElementDefinition e : element.getElement()) 
          composeElementDefinition("element", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeStructureDefinitionStructureDefinitionDifferentialComponent(String name, StructureDefinition.StructureDefinitionDifferentialComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasElement()) { 
        for (ElementDefinition e : element.getElement()) 
          composeElementDefinition("element", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSubscription(String name, Subscription element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasCriteriaElement()) {
        composeString("criteria", element.getCriteriaElement());
      }
      if (element.hasContact()) { 
        for (ContactPoint e : element.getContact()) 
          composeContactPoint("contact", e);
      }
      if (element.hasReasonElement()) {
        composeString("reason", element.getReasonElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Subscription.SubscriptionStatusEnumFactory());
      if (element.hasErrorElement()) {
        composeString("error", element.getErrorElement());
      }
      if (element.hasChannel()) {
        composeSubscriptionSubscriptionChannelComponent("channel", element.getChannel());
      }
      if (element.hasEndElement()) {
        composeInstant("end", element.getEndElement());
      }
      if (element.hasTag()) { 
        for (Coding e : element.getTag()) 
          composeCoding("tag", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSubscriptionSubscriptionChannelComponent(String name, Subscription.SubscriptionChannelComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new Subscription.SubscriptionChannelTypeEnumFactory());
      if (element.hasEndpointElement()) {
        composeUri("endpoint", element.getEndpointElement());
      }
      if (element.hasPayloadElement()) {
        composeString("payload", element.getPayloadElement());
      }
      if (element.hasHeaderElement()) {
        composeString("header", element.getHeaderElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasInstance()) { 
        for (Substance.SubstanceInstanceComponent e : element.getInstance()) 
          composeSubstanceSubstanceInstanceComponent("instance", e);
      }
      if (element.hasIngredient()) { 
        for (Substance.SubstanceIngredientComponent e : element.getIngredient()) 
          composeSubstanceSubstanceIngredientComponent("ingredient", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSubstanceSubstanceInstanceComponent(String name, Substance.SubstanceInstanceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasExpiryElement()) {
        composeDateTime("expiry", element.getExpiryElement());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSubstanceSubstanceIngredientComponent(String name, Substance.SubstanceIngredientComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasQuantity()) {
        composeRatio("quantity", element.getQuantity());
      }
      if (element.hasSubstance()) {
        composeReference("substance", element.getSubstance());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasKind()) {
        composeCodeableConcept("kind", element.getKind());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Supply.SupplyStatusEnumFactory());
      if (element.hasOrderedItem()) {
        composeReference("orderedItem", element.getOrderedItem());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasDispense()) { 
        for (Supply.SupplyDispenseComponent e : element.getDispense()) 
          composeSupplySupplyDispenseComponent("dispense", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSupplySupplyDispenseComponent(String name, Supply.SupplyDispenseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Supply.SupplyDispenseStatusEnumFactory());
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasSuppliedItem()) {
        composeReference("suppliedItem", element.getSuppliedItem());
      }
      if (element.hasSupplier()) {
        composeReference("supplier", element.getSupplier());
      }
      if (element.hasWhenPrepared()) {
        composePeriod("whenPrepared", element.getWhenPrepared());
      }
      if (element.hasWhenHandedOverElement()) {
        composeDateTime("whenHandedOver", element.getWhenHandedOverElement());
      }
      if (element.hasDestination()) {
        composeReference("destination", element.getDestination());
      }
      if (element.hasReceiver()) { 
        for (Reference e : element.getReceiver()) 
          composeReference("receiver", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSupplyDelivery(String name, SupplyDelivery element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new SupplyDelivery.SupplyDeliveryStatusEnumFactory());
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasType()) {
        composeCodeableConcept("type", element.getType());
      }
      if (element.hasQuantity()) {
        composeQuantity("quantity", element.getQuantity());
      }
      if (element.hasSuppliedItem()) {
        composeReference("suppliedItem", element.getSuppliedItem());
      }
      if (element.hasSupplier()) {
        composeReference("supplier", element.getSupplier());
      }
      if (element.hasWhenPrepared()) {
        composePeriod("whenPrepared", element.getWhenPrepared());
      }
      if (element.hasTimeElement()) {
        composeDateTime("time", element.getTimeElement());
      }
      if (element.hasDestination()) {
        composeReference("destination", element.getDestination());
      }
      if (element.hasReceiver()) { 
        for (Reference e : element.getReceiver()) 
          composeReference("receiver", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSupplyRequest(String name, SupplyRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasSource()) {
        composeReference("source", element.getSource());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new SupplyRequest.SupplyRequestStatusEnumFactory());
      if (element.hasKind()) {
        composeCodeableConcept("kind", element.getKind());
      }
      if (element.hasOrderedItem()) {
        composeReference("orderedItem", element.getOrderedItem());
      }
      if (element.hasSupplier()) { 
        for (Reference e : element.getSupplier()) 
          composeReference("supplier", e);
      }
      if (element.hasReason()) {
        composeType("reason", element.getReason());
      }      if (element.hasWhen()) {
        composeSupplyRequestSupplyRequestWhenComponent("when", element.getWhen());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeSupplyRequestSupplyRequestWhenComponent(String name, SupplyRequest.SupplyRequestWhenComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCode()) {
        composeCodeableConcept("code", element.getCode());
      }
      if (element.hasSchedule()) {
        composeTiming("schedule", element.getSchedule());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScript(String name, TestScript element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasMultiserverElement()) {
        composeBoolean("multiserver", element.getMultiserverElement());
      }
      if (element.hasFixture()) { 
        for (TestScript.TestScriptFixtureComponent e : element.getFixture()) 
          composeTestScriptTestScriptFixtureComponent("fixture", e);
      }
      if (element.hasSetup()) {
        composeTestScriptTestScriptSetupComponent("setup", element.getSetup());
      }
      if (element.hasTest()) { 
        for (TestScript.TestScriptTestComponent e : element.getTest()) 
          composeTestScriptTestScriptTestComponent("test", e);
      }
      if (element.hasTeardown()) {
        composeTestScriptTestScriptTeardownComponent("teardown", element.getTeardown());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptFixtureComponent(String name, TestScript.TestScriptFixtureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasUriElement()) {
        composeUri("uri", element.getUriElement());
      }
      if (element.hasResource()) {
        xml.enter(FHIR_NS, "resource");
        composeResource(element.getResource());
        xml.exit(FHIR_NS, "resource");
      }
      if (element.hasAutocreateElement()) {
        composeBoolean("autocreate", element.getAutocreateElement());
      }
      if (element.hasAutodeleteElement()) {
        composeBoolean("autodelete", element.getAutodeleteElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptSetupComponent(String name, TestScript.TestScriptSetupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasOperation()) { 
        for (TestScript.TestScriptSetupOperationComponent e : element.getOperation()) 
          composeTestScriptTestScriptSetupOperationComponent("operation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptSetupOperationComponent(String name, TestScript.TestScriptSetupOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new TestScript.TestOperationTypeEnumFactory());
      if (element.hasSourceElement()) {
        composeId("source", element.getSourceElement());
      }
      if (element.hasTargetElement()) {
        composeId("target", element.getTargetElement());
      }
      if (element.hasDestinationElement()) {
        composeInteger("destination", element.getDestinationElement());
      }
      if (element.hasParameter()) { 
        for (StringType e : element.getParameter()) 
          composeString("parameter", e);
      }
      if (element.hasResponseIdElement()) {
        composeId("responseId", element.getResponseIdElement());
      }
      if (element.hasContentTypeElement())
        composeEnumeration("contentType", element.getContentTypeElement(), new TestScript.ContentTypeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTestComponent(String name, TestScript.TestScriptTestComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasMetadata()) {
        composeTestScriptTestScriptTestMetadataComponent("metadata", element.getMetadata());
      }
      if (element.hasOperation()) { 
        for (TestScript.TestScriptTestOperationComponent e : element.getOperation()) 
          composeTestScriptTestScriptTestOperationComponent("operation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTestMetadataComponent(String name, TestScript.TestScriptTestMetadataComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLink()) { 
        for (TestScript.TestScriptTestMetadataLinkComponent e : element.getLink()) 
          composeTestScriptTestScriptTestMetadataLinkComponent("link", e);
      }
      if (element.hasRequires()) { 
        for (TestScript.TestScriptTestMetadataRequiresComponent e : element.getRequires()) 
          composeTestScriptTestScriptTestMetadataRequiresComponent("requires", e);
      }
      if (element.hasValidates()) { 
        for (TestScript.TestScriptTestMetadataValidatesComponent e : element.getValidates()) 
          composeTestScriptTestScriptTestMetadataValidatesComponent("validates", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTestMetadataLinkComponent(String name, TestScript.TestScriptTestMetadataLinkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTestMetadataRequiresComponent(String name, TestScript.TestScriptTestMetadataRequiresComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement()) {
        composeCode("type", element.getTypeElement());
      }
      if (element.hasOperationsElement()) {
        composeString("operations", element.getOperationsElement());
      }
      if (element.hasDestinationElement()) {
        composeInteger("destination", element.getDestinationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTestMetadataValidatesComponent(String name, TestScript.TestScriptTestMetadataValidatesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement()) {
        composeCode("type", element.getTypeElement());
      }
      if (element.hasOperationsElement()) {
        composeString("operations", element.getOperationsElement());
      }
      if (element.hasDestinationElement()) {
        composeInteger("destination", element.getDestinationElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTestOperationComponent(String name, TestScript.TestScriptTestOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new TestScript.TestOperationTypeEnumFactory());
      if (element.hasSourceElement()) {
        composeId("source", element.getSourceElement());
      }
      if (element.hasTargetElement()) {
        composeId("target", element.getTargetElement());
      }
      if (element.hasDestinationElement()) {
        composeInteger("destination", element.getDestinationElement());
      }
      if (element.hasParameter()) { 
        for (StringType e : element.getParameter()) 
          composeString("parameter", e);
      }
      if (element.hasResponseIdElement()) {
        composeId("responseId", element.getResponseIdElement());
      }
      if (element.hasContentTypeElement())
        composeEnumeration("contentType", element.getContentTypeElement(), new TestScript.ContentTypeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTeardownComponent(String name, TestScript.TestScriptTeardownComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasOperation()) { 
        for (TestScript.TestScriptTeardownOperationComponent e : element.getOperation()) 
          composeTestScriptTestScriptTeardownOperationComponent("operation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeTestScriptTestScriptTeardownOperationComponent(String name, TestScript.TestScriptTeardownOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasTypeElement())
        composeEnumeration("type", element.getTypeElement(), new TestScript.TestOperationTypeEnumFactory());
      if (element.hasSourceElement()) {
        composeId("source", element.getSourceElement());
      }
      if (element.hasTargetElement()) {
        composeId("target", element.getTargetElement());
      }
      if (element.hasDestinationElement()) {
        composeInteger("destination", element.getDestinationElement());
      }
      if (element.hasParameter()) { 
        for (StringType e : element.getParameter()) 
          composeString("parameter", e);
      }
      if (element.hasResponseIdElement()) {
        composeId("responseId", element.getResponseIdElement());
      }
      if (element.hasContentTypeElement())
        composeEnumeration("contentType", element.getContentTypeElement(), new TestScript.ContentTypeEnumFactory());
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasUrlElement()) {
        composeUri("url", element.getUrlElement());
      }
      if (element.hasIdentifier()) {
        composeIdentifier("identifier", element.getIdentifier());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasUseContext()) { 
        for (CodeableConcept e : element.getUseContext()) 
          composeCodeableConcept("useContext", e);
      }
      if (element.hasImmutableElement()) {
        composeBoolean("immutable", element.getImmutableElement());
      }
      if (element.hasPublisherElement()) {
        composeString("publisher", element.getPublisherElement());
      }
      if (element.hasContact()) { 
        for (ValueSet.ValueSetContactComponent e : element.getContact()) 
          composeValueSetValueSetContactComponent("contact", e);
      }
      if (element.hasDescriptionElement()) {
        composeString("description", element.getDescriptionElement());
      }
      if (element.hasRequirementsElement()) {
        composeString("requirements", element.getRequirementsElement());
      }
      if (element.hasCopyrightElement()) {
        composeString("copyright", element.getCopyrightElement());
      }
      if (element.hasStatusElement())
        composeEnumeration("status", element.getStatusElement(), new Enumerations.ConformanceResourceStatusEnumFactory());
      if (element.hasExperimentalElement()) {
        composeBoolean("experimental", element.getExperimentalElement());
      }
      if (element.hasExtensibleElement()) {
        composeBoolean("extensible", element.getExtensibleElement());
      }
      if (element.hasDateElement()) {
        composeDateTime("date", element.getDateElement());
      }
      if (element.hasLockedDateElement()) {
        composeDate("lockedDate", element.getLockedDateElement());
      }
      if (element.hasDefine()) {
        composeValueSetValueSetDefineComponent("define", element.getDefine());
      }
      if (element.hasCompose()) {
        composeValueSetValueSetComposeComponent("compose", element.getCompose());
      }
      if (element.hasExpansion()) {
        composeValueSetValueSetExpansionComponent("expansion", element.getExpansion());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetValueSetContactComponent(String name, ValueSet.ValueSetContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasTelecom()) { 
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint("telecom", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetValueSetDefineComponent(String name, ValueSet.ValueSetDefineComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasCaseSensitiveElement()) {
        composeBoolean("caseSensitive", element.getCaseSensitiveElement());
      }
      if (element.hasConcept()) { 
        for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
          composeValueSetConceptDefinitionComponent("concept", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetConceptDefinitionComponent(String name, ValueSet.ConceptDefinitionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasAbstractElement()) {
        composeBoolean("abstract", element.getAbstractElement());
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      if (element.hasDefinitionElement()) {
        composeString("definition", element.getDefinitionElement());
      }
      if (element.hasDesignation()) { 
        for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
          composeValueSetConceptDefinitionDesignationComponent("designation", e);
      }
      if (element.hasConcept()) { 
        for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
          composeValueSetConceptDefinitionComponent("concept", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetConceptDefinitionDesignationComponent(String name, ValueSet.ConceptDefinitionDesignationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasLanguageElement()) {
        composeCode("language", element.getLanguageElement());
      }
      if (element.hasUse()) {
        composeCoding("use", element.getUse());
      }
      if (element.hasValueElement()) {
        composeString("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasImport()) { 
        for (UriType e : element.getImport()) 
          composeUri("import", e);
      }
      if (element.hasInclude()) { 
        for (ValueSet.ConceptSetComponent e : element.getInclude()) 
          composeValueSetConceptSetComponent("include", e);
      }
      if (element.hasExclude()) { 
        for (ValueSet.ConceptSetComponent e : element.getExclude()) 
          composeValueSetConceptSetComponent("exclude", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetConceptSetComponent(String name, ValueSet.ConceptSetComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasConcept()) { 
        for (ValueSet.ConceptReferenceComponent e : element.getConcept()) 
          composeValueSetConceptReferenceComponent("concept", e);
      }
      if (element.hasFilter()) { 
        for (ValueSet.ConceptSetFilterComponent e : element.getFilter()) 
          composeValueSetConceptSetFilterComponent("filter", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetConceptReferenceComponent(String name, ValueSet.ConceptReferenceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      if (element.hasDesignation()) { 
        for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
          composeValueSetConceptDefinitionDesignationComponent("designation", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasPropertyElement()) {
        composeCode("property", element.getPropertyElement());
      }
      if (element.hasOpElement())
        composeEnumeration("op", element.getOpElement(), new ValueSet.FilterOperatorEnumFactory());
      if (element.hasValueElement()) {
        composeCode("value", element.getValueElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasIdentifierElement()) {
        composeUri("identifier", element.getIdentifierElement());
      }
      if (element.hasTimestampElement()) {
        composeDateTime("timestamp", element.getTimestampElement());
      }
      if (element.hasTotalElement()) {
        composeInteger("total", element.getTotalElement());
      }
      if (element.hasCountElement()) {
        composeInteger("count", element.getCountElement());
      }
      if (element.hasParameter()) { 
        for (ValueSet.ValueSetExpansionParameterComponent e : element.getParameter()) 
          composeValueSetValueSetExpansionParameterComponent("parameter", e);
      }
      if (element.hasContains()) { 
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent("contains", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetValueSetExpansionParameterComponent(String name, ValueSet.ValueSetExpansionParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasNameElement()) {
        composeString("name", element.getNameElement());
      }
      if (element.hasValue()) {
        composeType("value", element.getValue());
      }      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeValueSetValueSetExpansionContainsComponent(String name, ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasSystemElement()) {
        composeUri("system", element.getSystemElement());
      }
      if (element.hasAbstractElement()) {
        composeBoolean("abstract", element.getAbstractElement());
      }
      if (element.hasVersionElement()) {
        composeString("version", element.getVersionElement());
      }
      if (element.hasCodeElement()) {
        composeCode("code", element.getCodeElement());
      }
      if (element.hasDisplayElement()) {
        composeString("display", element.getDisplayElement());
      }
      if (element.hasContains()) { 
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent("contains", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeVisionPrescription(String name, VisionPrescription element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.enter(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.hasIdentifier()) { 
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier("identifier", e);
      }
      if (element.hasDateWrittenElement()) {
        composeDateTime("dateWritten", element.getDateWrittenElement());
      }
      if (element.hasPatient()) {
        composeReference("patient", element.getPatient());
      }
      if (element.hasPrescriber()) {
        composeReference("prescriber", element.getPrescriber());
      }
      if (element.hasEncounter()) {
        composeReference("encounter", element.getEncounter());
      }
      if (element.hasReason()) {
        composeType("reason", element.getReason());
      }      if (element.hasDispense()) { 
        for (VisionPrescription.VisionPrescriptionDispenseComponent e : element.getDispense()) 
          composeVisionPrescriptionVisionPrescriptionDispenseComponent("dispense", e);
      }
      xml.exit(FHIR_NS, name);
    }
  }

  protected void composeVisionPrescriptionVisionPrescriptionDispenseComponent(String name, VisionPrescription.VisionPrescriptionDispenseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.enter(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.hasProduct()) {
        composeCoding("product", element.getProduct());
      }
      if (element.hasEyeElement())
        composeEnumeration("eye", element.getEyeElement(), new VisionPrescription.VisionEyesEnumFactory());
      if (element.hasSphereElement()) {
        composeDecimal("sphere", element.getSphereElement());
      }
      if (element.hasCylinderElement()) {
        composeDecimal("cylinder", element.getCylinderElement());
      }
      if (element.hasAxisElement()) {
        composeInteger("axis", element.getAxisElement());
      }
      if (element.hasPrismElement()) {
        composeDecimal("prism", element.getPrismElement());
      }
      if (element.hasBaseElement())
        composeEnumeration("base", element.getBaseElement(), new VisionPrescription.VisionBaseEnumFactory());
      if (element.hasAddElement()) {
        composeDecimal("add", element.getAddElement());
      }
      if (element.hasPowerElement()) {
        composeDecimal("power", element.getPowerElement());
      }
      if (element.hasBackCurveElement()) {
        composeDecimal("backCurve", element.getBackCurveElement());
      }
      if (element.hasDiameterElement()) {
        composeDecimal("diameter", element.getDiameterElement());
      }
      if (element.hasDuration()) {
        composeQuantity("duration", element.getDuration());
      }
      if (element.hasColorElement()) {
        composeString("color", element.getColorElement());
      }
      if (element.hasBrandElement()) {
        composeString("brand", element.getBrandElement());
      }
      if (element.hasNotesElement()) {
        composeString("notes", element.getNotesElement());
      }
      xml.exit(FHIR_NS, name);
    }
  }

  @Override
  protected void composeResource(Resource resource) throws Exception {
    if (resource instanceof Parameters)
      composeParameters("Parameters", (Parameters)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance("AllergyIntolerance", (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment("Appointment", (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse("AppointmentResponse", (AppointmentResponse)resource);
    else if (resource instanceof AuditEvent)
      composeAuditEvent("AuditEvent", (AuditEvent)resource);
    else if (resource instanceof Basic)
      composeBasic("Basic", (Basic)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else if (resource instanceof BodySite)
      composeBodySite("BodySite", (BodySite)resource);
    else if (resource instanceof Bundle)
      composeBundle("Bundle", (Bundle)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan("CarePlan", (CarePlan)resource);
    else if (resource instanceof Claim)
      composeClaim("Claim", (Claim)resource);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse("ClaimResponse", (ClaimResponse)resource);
    else if (resource instanceof ClinicalImpression)
      composeClinicalImpression("ClinicalImpression", (ClinicalImpression)resource);
    else if (resource instanceof Communication)
      composeCommunication("Communication", (Communication)resource);
    else if (resource instanceof CommunicationRequest)
      composeCommunicationRequest("CommunicationRequest", (CommunicationRequest)resource);
    else if (resource instanceof Composition)
      composeComposition("Composition", (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap("ConceptMap", (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition("Condition", (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance("Conformance", (Conformance)resource);
    else if (resource instanceof Contract)
      composeContract("Contract", (Contract)resource);
    else if (resource instanceof Contraindication)
      composeContraindication("Contraindication", (Contraindication)resource);
    else if (resource instanceof Coverage)
      composeCoverage("Coverage", (Coverage)resource);
    else if (resource instanceof DataElement)
      composeDataElement("DataElement", (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice("Device", (Device)resource);
    else if (resource instanceof DeviceComponent)
      composeDeviceComponent("DeviceComponent", (DeviceComponent)resource);
    else if (resource instanceof DeviceMetric)
      composeDeviceMetric("DeviceMetric", (DeviceMetric)resource);
    else if (resource instanceof DeviceUseRequest)
      composeDeviceUseRequest("DeviceUseRequest", (DeviceUseRequest)resource);
    else if (resource instanceof DeviceUseStatement)
      composeDeviceUseStatement("DeviceUseStatement", (DeviceUseStatement)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder("DiagnosticOrder", (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport("DiagnosticReport", (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest("DocumentManifest", (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference("DocumentReference", (DocumentReference)resource);
    else if (resource instanceof EligibilityRequest)
      composeEligibilityRequest("EligibilityRequest", (EligibilityRequest)resource);
    else if (resource instanceof EligibilityResponse)
      composeEligibilityResponse("EligibilityResponse", (EligibilityResponse)resource);
    else if (resource instanceof Encounter)
      composeEncounter("Encounter", (Encounter)resource);
    else if (resource instanceof EnrollmentRequest)
      composeEnrollmentRequest("EnrollmentRequest", (EnrollmentRequest)resource);
    else if (resource instanceof EnrollmentResponse)
      composeEnrollmentResponse("EnrollmentResponse", (EnrollmentResponse)resource);
    else if (resource instanceof EpisodeOfCare)
      composeEpisodeOfCare("EpisodeOfCare", (EpisodeOfCare)resource);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit("ExplanationOfBenefit", (ExplanationOfBenefit)resource);
    else if (resource instanceof FamilyMemberHistory)
      composeFamilyMemberHistory("FamilyMemberHistory", (FamilyMemberHistory)resource);
    else if (resource instanceof Flag)
      composeFlag("Flag", (Flag)resource);
    else if (resource instanceof Goal)
      composeGoal("Goal", (Goal)resource);
    else if (resource instanceof Group)
      composeGroup("Group", (Group)resource);
    else if (resource instanceof HealthcareService)
      composeHealthcareService("HealthcareService", (HealthcareService)resource);
    else if (resource instanceof ImagingObjectSelection)
      composeImagingObjectSelection("ImagingObjectSelection", (ImagingObjectSelection)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy("ImagingStudy", (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization("Immunization", (Immunization)resource);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation("ImmunizationRecommendation", (ImmunizationRecommendation)resource);
    else if (resource instanceof ImplementationGuide)
      composeImplementationGuide("ImplementationGuide", (ImplementationGuide)resource);
    else if (resource instanceof List_)
      composeList_("List", (List_)resource);
    else if (resource instanceof Location)
      composeLocation("Location", (Location)resource);
    else if (resource instanceof Media)
      composeMedia("Media", (Media)resource);
    else if (resource instanceof Medication)
      composeMedication("Medication", (Medication)resource);
    else if (resource instanceof MedicationAdministration)
      composeMedicationAdministration("MedicationAdministration", (MedicationAdministration)resource);
    else if (resource instanceof MedicationDispense)
      composeMedicationDispense("MedicationDispense", (MedicationDispense)resource);
    else if (resource instanceof MedicationPrescription)
      composeMedicationPrescription("MedicationPrescription", (MedicationPrescription)resource);
    else if (resource instanceof MedicationStatement)
      composeMedicationStatement("MedicationStatement", (MedicationStatement)resource);
    else if (resource instanceof MessageHeader)
      composeMessageHeader("MessageHeader", (MessageHeader)resource);
    else if (resource instanceof NamingSystem)
      composeNamingSystem("NamingSystem", (NamingSystem)resource);
    else if (resource instanceof NutritionOrder)
      composeNutritionOrder("NutritionOrder", (NutritionOrder)resource);
    else if (resource instanceof Observation)
      composeObservation("Observation", (Observation)resource);
    else if (resource instanceof OperationDefinition)
      composeOperationDefinition("OperationDefinition", (OperationDefinition)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome("OperationOutcome", (OperationOutcome)resource);
    else if (resource instanceof Order)
      composeOrder("Order", (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse("OrderResponse", (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization("Organization", (Organization)resource);
    else if (resource instanceof Patient)
      composePatient("Patient", (Patient)resource);
    else if (resource instanceof PaymentNotice)
      composePaymentNotice("PaymentNotice", (PaymentNotice)resource);
    else if (resource instanceof PaymentReconciliation)
      composePaymentReconciliation("PaymentReconciliation", (PaymentReconciliation)resource);
    else if (resource instanceof Person)
      composePerson("Person", (Person)resource);
    else if (resource instanceof Practitioner)
      composePractitioner("Practitioner", (Practitioner)resource);
    else if (resource instanceof Procedure)
      composeProcedure("Procedure", (Procedure)resource);
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest("ProcedureRequest", (ProcedureRequest)resource);
    else if (resource instanceof ProcessRequest)
      composeProcessRequest("ProcessRequest", (ProcessRequest)resource);
    else if (resource instanceof ProcessResponse)
      composeProcessResponse("ProcessResponse", (ProcessResponse)resource);
    else if (resource instanceof Provenance)
      composeProvenance("Provenance", (Provenance)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire("Questionnaire", (Questionnaire)resource);
    else if (resource instanceof QuestionnaireAnswers)
      composeQuestionnaireAnswers("QuestionnaireAnswers", (QuestionnaireAnswers)resource);
    else if (resource instanceof ReferralRequest)
      composeReferralRequest("ReferralRequest", (ReferralRequest)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson("RelatedPerson", (RelatedPerson)resource);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment("RiskAssessment", (RiskAssessment)resource);
    else if (resource instanceof Schedule)
      composeSchedule("Schedule", (Schedule)resource);
    else if (resource instanceof SearchParameter)
      composeSearchParameter("SearchParameter", (SearchParameter)resource);
    else if (resource instanceof Slot)
      composeSlot("Slot", (Slot)resource);
    else if (resource instanceof Specimen)
      composeSpecimen("Specimen", (Specimen)resource);
    else if (resource instanceof StructureDefinition)
      composeStructureDefinition("StructureDefinition", (StructureDefinition)resource);
    else if (resource instanceof Subscription)
      composeSubscription("Subscription", (Subscription)resource);
    else if (resource instanceof Substance)
      composeSubstance("Substance", (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply("Supply", (Supply)resource);
    else if (resource instanceof SupplyDelivery)
      composeSupplyDelivery("SupplyDelivery", (SupplyDelivery)resource);
    else if (resource instanceof SupplyRequest)
      composeSupplyRequest("SupplyRequest", (SupplyRequest)resource);
    else if (resource instanceof TestScript)
      composeTestScript("TestScript", (TestScript)resource);
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof VisionPrescription)
      composeVisionPrescription("VisionPrescription", (VisionPrescription)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeResource(String name, Resource resource) throws Exception {
    if (resource instanceof Parameters)
      composeParameters(name, (Parameters)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance(name, (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment(name, (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse(name, (AppointmentResponse)resource);
    else if (resource instanceof AuditEvent)
      composeAuditEvent(name, (AuditEvent)resource);
    else if (resource instanceof Basic)
      composeBasic(name, (Basic)resource);
    else if (resource instanceof Binary)
      composeBinary(name, (Binary)resource);
    else if (resource instanceof BodySite)
      composeBodySite(name, (BodySite)resource);
    else if (resource instanceof Bundle)
      composeBundle(name, (Bundle)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan(name, (CarePlan)resource);
    else if (resource instanceof Claim)
      composeClaim(name, (Claim)resource);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse(name, (ClaimResponse)resource);
    else if (resource instanceof ClinicalImpression)
      composeClinicalImpression(name, (ClinicalImpression)resource);
    else if (resource instanceof Communication)
      composeCommunication(name, (Communication)resource);
    else if (resource instanceof CommunicationRequest)
      composeCommunicationRequest(name, (CommunicationRequest)resource);
    else if (resource instanceof Composition)
      composeComposition(name, (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap(name, (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition(name, (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance(name, (Conformance)resource);
    else if (resource instanceof Contract)
      composeContract(name, (Contract)resource);
    else if (resource instanceof Contraindication)
      composeContraindication(name, (Contraindication)resource);
    else if (resource instanceof Coverage)
      composeCoverage(name, (Coverage)resource);
    else if (resource instanceof DataElement)
      composeDataElement(name, (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice(name, (Device)resource);
    else if (resource instanceof DeviceComponent)
      composeDeviceComponent(name, (DeviceComponent)resource);
    else if (resource instanceof DeviceMetric)
      composeDeviceMetric(name, (DeviceMetric)resource);
    else if (resource instanceof DeviceUseRequest)
      composeDeviceUseRequest(name, (DeviceUseRequest)resource);
    else if (resource instanceof DeviceUseStatement)
      composeDeviceUseStatement(name, (DeviceUseStatement)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder(name, (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport(name, (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest(name, (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference(name, (DocumentReference)resource);
    else if (resource instanceof EligibilityRequest)
      composeEligibilityRequest(name, (EligibilityRequest)resource);
    else if (resource instanceof EligibilityResponse)
      composeEligibilityResponse(name, (EligibilityResponse)resource);
    else if (resource instanceof Encounter)
      composeEncounter(name, (Encounter)resource);
    else if (resource instanceof EnrollmentRequest)
      composeEnrollmentRequest(name, (EnrollmentRequest)resource);
    else if (resource instanceof EnrollmentResponse)
      composeEnrollmentResponse(name, (EnrollmentResponse)resource);
    else if (resource instanceof EpisodeOfCare)
      composeEpisodeOfCare(name, (EpisodeOfCare)resource);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit(name, (ExplanationOfBenefit)resource);
    else if (resource instanceof FamilyMemberHistory)
      composeFamilyMemberHistory(name, (FamilyMemberHistory)resource);
    else if (resource instanceof Flag)
      composeFlag(name, (Flag)resource);
    else if (resource instanceof Goal)
      composeGoal(name, (Goal)resource);
    else if (resource instanceof Group)
      composeGroup(name, (Group)resource);
    else if (resource instanceof HealthcareService)
      composeHealthcareService(name, (HealthcareService)resource);
    else if (resource instanceof ImagingObjectSelection)
      composeImagingObjectSelection(name, (ImagingObjectSelection)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy(name, (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization(name, (Immunization)resource);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation(name, (ImmunizationRecommendation)resource);
    else if (resource instanceof ImplementationGuide)
      composeImplementationGuide(name, (ImplementationGuide)resource);
    else if (resource instanceof List_)
      composeList_(name, (List_)resource);
    else if (resource instanceof Location)
      composeLocation(name, (Location)resource);
    else if (resource instanceof Media)
      composeMedia(name, (Media)resource);
    else if (resource instanceof Medication)
      composeMedication(name, (Medication)resource);
    else if (resource instanceof MedicationAdministration)
      composeMedicationAdministration(name, (MedicationAdministration)resource);
    else if (resource instanceof MedicationDispense)
      composeMedicationDispense(name, (MedicationDispense)resource);
    else if (resource instanceof MedicationPrescription)
      composeMedicationPrescription(name, (MedicationPrescription)resource);
    else if (resource instanceof MedicationStatement)
      composeMedicationStatement(name, (MedicationStatement)resource);
    else if (resource instanceof MessageHeader)
      composeMessageHeader(name, (MessageHeader)resource);
    else if (resource instanceof NamingSystem)
      composeNamingSystem(name, (NamingSystem)resource);
    else if (resource instanceof NutritionOrder)
      composeNutritionOrder(name, (NutritionOrder)resource);
    else if (resource instanceof Observation)
      composeObservation(name, (Observation)resource);
    else if (resource instanceof OperationDefinition)
      composeOperationDefinition(name, (OperationDefinition)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome(name, (OperationOutcome)resource);
    else if (resource instanceof Order)
      composeOrder(name, (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse(name, (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization(name, (Organization)resource);
    else if (resource instanceof Patient)
      composePatient(name, (Patient)resource);
    else if (resource instanceof PaymentNotice)
      composePaymentNotice(name, (PaymentNotice)resource);
    else if (resource instanceof PaymentReconciliation)
      composePaymentReconciliation(name, (PaymentReconciliation)resource);
    else if (resource instanceof Person)
      composePerson(name, (Person)resource);
    else if (resource instanceof Practitioner)
      composePractitioner(name, (Practitioner)resource);
    else if (resource instanceof Procedure)
      composeProcedure(name, (Procedure)resource);
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest(name, (ProcedureRequest)resource);
    else if (resource instanceof ProcessRequest)
      composeProcessRequest(name, (ProcessRequest)resource);
    else if (resource instanceof ProcessResponse)
      composeProcessResponse(name, (ProcessResponse)resource);
    else if (resource instanceof Provenance)
      composeProvenance(name, (Provenance)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire(name, (Questionnaire)resource);
    else if (resource instanceof QuestionnaireAnswers)
      composeQuestionnaireAnswers(name, (QuestionnaireAnswers)resource);
    else if (resource instanceof ReferralRequest)
      composeReferralRequest(name, (ReferralRequest)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson(name, (RelatedPerson)resource);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment(name, (RiskAssessment)resource);
    else if (resource instanceof Schedule)
      composeSchedule(name, (Schedule)resource);
    else if (resource instanceof SearchParameter)
      composeSearchParameter(name, (SearchParameter)resource);
    else if (resource instanceof Slot)
      composeSlot(name, (Slot)resource);
    else if (resource instanceof Specimen)
      composeSpecimen(name, (Specimen)resource);
    else if (resource instanceof StructureDefinition)
      composeStructureDefinition(name, (StructureDefinition)resource);
    else if (resource instanceof Subscription)
      composeSubscription(name, (Subscription)resource);
    else if (resource instanceof Substance)
      composeSubstance(name, (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply(name, (Supply)resource);
    else if (resource instanceof SupplyDelivery)
      composeSupplyDelivery(name, (SupplyDelivery)resource);
    else if (resource instanceof SupplyRequest)
      composeSupplyRequest(name, (SupplyRequest)resource);
    else if (resource instanceof TestScript)
      composeTestScript(name, (TestScript)resource);
    else if (resource instanceof ValueSet)
      composeValueSet(name, (ValueSet)resource);
    else if (resource instanceof VisionPrescription)
      composeVisionPrescription(name, (VisionPrescription)resource);
    else if (resource instanceof Binary)
      composeBinary(name, (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeType(String prefix, Type type) throws Exception {
    if (type == null)
      ;
    else if (type instanceof Age)
       composeAge(prefix+"Age", (Age) type);
    else if (type instanceof Count)
       composeCount(prefix+"Count", (Count) type);
    else if (type instanceof Money)
       composeMoney(prefix+"Money", (Money) type);
    else if (type instanceof Distance)
       composeDistance(prefix+"Distance", (Distance) type);
    else if (type instanceof Duration)
       composeDuration(prefix+"Duration", (Duration) type);
    else if (type instanceof Period)
       composePeriod(prefix+"Period", (Period) type);
    else if (type instanceof Coding)
       composeCoding(prefix+"Coding", (Coding) type);
    else if (type instanceof Range)
       composeRange(prefix+"Range", (Range) type);
    else if (type instanceof Quantity)
       composeQuantity(prefix+"Quantity", (Quantity) type);
    else if (type instanceof Attachment)
       composeAttachment(prefix+"Attachment", (Attachment) type);
    else if (type instanceof Ratio)
       composeRatio(prefix+"Ratio", (Ratio) type);
    else if (type instanceof Annotation)
       composeAnnotation(prefix+"Annotation", (Annotation) type);
    else if (type instanceof SampledData)
       composeSampledData(prefix+"SampledData", (SampledData) type);
    else if (type instanceof Reference)
       composeReference(prefix+"Reference", (Reference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConcept(prefix+"CodeableConcept", (CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifier(prefix+"Identifier", (Identifier) type);
    else if (type instanceof Signature)
       composeSignature(prefix+"Signature", (Signature) type);
    else if (type instanceof ElementDefinition)
       composeElementDefinition(prefix+"ElementDefinition", (ElementDefinition) type);
    else if (type instanceof Timing)
       composeTiming(prefix+"Timing", (Timing) type);
    else if (type instanceof Address)
       composeAddress(prefix+"Address", (Address) type);
    else if (type instanceof HumanName)
       composeHumanName(prefix+"HumanName", (HumanName) type);
    else if (type instanceof Meta)
       composeMeta(prefix+"Meta", (Meta) type);
    else if (type instanceof ContactPoint)
       composeContactPoint(prefix+"ContactPoint", (ContactPoint) type);
    else if (type instanceof IntegerType)
       composeInteger(prefix+"Integer", (IntegerType) type);
    else if (type instanceof DateTimeType)
       composeDateTime(prefix+"DateTime", (DateTimeType) type);
    else if (type instanceof UnsignedIntType)
       composeUnsignedInt(prefix+"UnsignedInt", (UnsignedIntType) type);
    else if (type instanceof CodeType)
       composeCode(prefix+"Code", (CodeType) type);
    else if (type instanceof DateType)
       composeDate(prefix+"Date", (DateType) type);
    else if (type instanceof DecimalType)
       composeDecimal(prefix+"Decimal", (DecimalType) type);
    else if (type instanceof UriType)
       composeUri(prefix+"Uri", (UriType) type);
    else if (type instanceof IdType)
       composeId(prefix+"Id", (IdType) type);
    else if (type instanceof Base64BinaryType)
       composeBase64Binary(prefix+"Base64Binary", (Base64BinaryType) type);
    else if (type instanceof TimeType)
       composeTime(prefix+"Time", (TimeType) type);
    else if (type instanceof OidType)
       composeOid(prefix+"Oid", (OidType) type);
    else if (type instanceof PositiveIntType)
       composePositiveInt(prefix+"PositiveInt", (PositiveIntType) type);
    else if (type instanceof StringType)
       composeString(prefix+"String", (StringType) type);
    else if (type instanceof BooleanType)
       composeBoolean(prefix+"Boolean", (BooleanType) type);
    else if (type instanceof UuidType)
       composeUuid(prefix+"Uuid", (UuidType) type);
    else if (type instanceof InstantType)
       composeInstant(prefix+"Instant", (InstantType) type);
    else
      throw new Exception("Unhanded type");
  }

}

