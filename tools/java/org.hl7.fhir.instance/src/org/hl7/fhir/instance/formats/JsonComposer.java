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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.utilities.Utilities;

public class JsonComposer extends JsonComposerBase {

  private void composeElement(Element element) throws Exception {
    if (element.getXmlId() != null)
      prop("_id", element.getXmlId());
    if (element.getExtensions().size() > 0) {
      openArray("extension");
      for (Extension ex : element.getExtensions())
        composeExtension(null, ex);
      closeArray();
    }
  }

  private void composeBackbone(BackboneElement element) throws Exception {
    composeElement(element);
    if (element.getModifierExtensions().size() > 0) {
      openArray("modifierExtension");
      for (Extension ex : element.getModifierExtensions())
        composeExtension(null, ex);
      closeArray();
    }
  }

  private <E extends Enum<E>> void composeEnumerationCore(String name, Enumeration<E> value, EnumFactory e, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
      prop(name, e.toCode(value.getValue()));
    } else if (inArray)   
      writeNull(name);
  }    

  private <E extends Enum<E>> void composeEnumerationExtras(String name, Enumeration<E> value, EnumFactory e, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    } else if (inArray)   
      writeNull(name);
  }    

  private void composeIntegerCore(String name, Integer value, boolean inArray) throws Exception {
    if (value != null) {
        prop(name, java.lang.Integer.valueOf(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeIntegerExtras(String name, Integer value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeDateTimeCore(String name, DateTime value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeDateTimeExtras(String name, DateTime value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeCodeCore(String name, Code value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeCodeExtras(String name, Code value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeDateCore(String name, Date value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeDateExtras(String name, Date value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeDecimalCore(String name, Decimal value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, value.getValue());
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeDecimalExtras(String name, Decimal value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeUriCore(String name, Uri value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeUriExtras(String name, Uri value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeIdCore(String name, Id value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeIdExtras(String name, Id value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeBase64BinaryCore(String name, Base64Binary value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeBase64BinaryExtras(String name, Base64Binary value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeOidCore(String name, Oid value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeOidExtras(String name, Oid value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeStringCore(String name, String_ value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeStringExtras(String name, String_ value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeBooleanCore(String name, Boolean value, boolean inArray) throws Exception {
    if (value != null) {
        prop(name, value.getValue());
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeBooleanExtras(String name, Boolean value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeUuidCore(String name, Uuid value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeUuidExtras(String name, Uuid value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeInstantCore(String name, Instant value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeInstantExtras(String name, Instant value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeExtension(String name, Extension element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      composeType("value", element.getValue());
      close();
    }
  }

  private void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Narrative.NarrativeStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Narrative.NarrativeStatusEnumFactory(), false);
      }
      composeXhtml("div", element.getDiv());
      close();
    }
  }

  private void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDateTimeCore("start", element.getStart(), false);
      composeDateTimeExtras("start", element.getStart(), false);
      composeDateTimeCore("end", element.getEnd(), false);
      composeDateTimeExtras("end", element.getEnd(), false);
      close();
    }
  }

  private void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      composeBooleanCore("primary", element.getPrimary(), false);
      composeBooleanExtras("primary", element.getPrimary(), false);
      composeResourceReference("valueSet", element.getValueSet());
      close();
    }
  }

  private void composeRange(String name, Range element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
      close();
    }
  }

  private void composeQuantity(String name, Quantity element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValue(), false);
      composeDecimalExtras("value", element.getValue(), false);
      if (element.getComparator() != null) {
        composeEnumerationCore("comparator", element.getComparator(), new Quantity.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparator(), new Quantity.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnits(), false);
      composeStringExtras("units", element.getUnits(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeCore("contentType", element.getContentType(), false);
      composeCodeExtras("contentType", element.getContentType(), false);
      composeCodeCore("language", element.getLanguage(), false);
      composeCodeExtras("language", element.getLanguage(), false);
      composeBase64BinaryCore("data", element.getData(), false);
      composeBase64BinaryExtras("data", element.getData(), false);
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      composeIntegerCore("size", element.getSize(), false);
      composeIntegerExtras("size", element.getSize(), false);
      composeBase64BinaryCore("hash", element.getHash(), false);
      composeBase64BinaryExtras("hash", element.getHash(), false);
      composeStringCore("title", element.getTitle(), false);
      composeStringExtras("title", element.getTitle(), false);
      close();
    }
  }

  private void composeRatio(String name, Ratio element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeQuantity("numerator", element.getNumerator());
      composeQuantity("denominator", element.getDenominator());
      close();
    }
  }

  private void composeSampledData(String name, SampledData element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeQuantity("origin", element.getOrigin());
      composeDecimalCore("period", element.getPeriod(), false);
      composeDecimalExtras("period", element.getPeriod(), false);
      composeDecimalCore("factor", element.getFactor(), false);
      composeDecimalExtras("factor", element.getFactor(), false);
      composeDecimalCore("lowerLimit", element.getLowerLimit(), false);
      composeDecimalExtras("lowerLimit", element.getLowerLimit(), false);
      composeDecimalCore("upperLimit", element.getUpperLimit(), false);
      composeDecimalExtras("upperLimit", element.getUpperLimit(), false);
      composeIntegerCore("dimensions", element.getDimensions(), false);
      composeIntegerExtras("dimensions", element.getDimensions(), false);
      composeStringCore("data", element.getData(), false);
      composeStringExtras("data", element.getData(), false);
      close();
    }
  }

  private void composeResourceReference(String name, ResourceReference element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeStringCore("reference", element.getReference(), false);
      composeStringExtras("reference", element.getReference(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      close();
    }
  }

  private void composeCodeableConcept(String name, CodeableConcept element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getCoding().size() > 0) {
        openArray("coding");
        for (Coding e : element.getCoding()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      close();
    }
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUse() != null) {
        composeEnumerationCore("use", element.getUse(), new Identifier.IdentifierUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUse(), new Identifier.IdentifierUseEnumFactory(), false);
      }
      composeStringCore("label", element.getLabel(), false);
      composeStringExtras("label", element.getLabel(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeStringCore("value", element.getValue(), false);
      composeStringExtras("value", element.getValue(), false);
      composePeriod("period", element.getPeriod());
      composeResourceReference("assigner", element.getAssigner());
      close();
    }
  }

  private void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValue(), false);
      composeDecimalExtras("value", element.getValue(), false);
      if (element.getComparator() != null) {
        composeEnumerationCore("comparator", element.getComparator(), new Age.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparator(), new Age.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnits(), false);
      composeStringExtras("units", element.getUnits(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValue(), false);
      composeDecimalExtras("value", element.getValue(), false);
      if (element.getComparator() != null) {
        composeEnumerationCore("comparator", element.getComparator(), new Count.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparator(), new Count.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnits(), false);
      composeStringExtras("units", element.getUnits(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValue(), false);
      composeDecimalExtras("value", element.getValue(), false);
      if (element.getComparator() != null) {
        composeEnumerationCore("comparator", element.getComparator(), new Money.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparator(), new Money.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnits(), false);
      composeStringExtras("units", element.getUnits(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValue(), false);
      composeDecimalExtras("value", element.getValue(), false);
      if (element.getComparator() != null) {
        composeEnumerationCore("comparator", element.getComparator(), new Distance.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparator(), new Distance.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnits(), false);
      composeStringExtras("units", element.getUnits(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValue(), false);
      composeDecimalExtras("value", element.getValue(), false);
      if (element.getComparator() != null) {
        composeEnumerationCore("comparator", element.getComparator(), new Duration.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparator(), new Duration.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnits(), false);
      composeStringExtras("units", element.getUnits(), false);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeSchedule(String name, Schedule element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Period e : element.getEvent()) 
          composePeriod(null, e);
        closeArray();
      };
      composeScheduleScheduleRepeatComponent("repeat", element.getRepeat());
      close();
    }
  }

  private void composeScheduleScheduleRepeatComponent(String name, Schedule.ScheduleRepeatComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeIntegerCore("frequency", element.getFrequency(), false);
      composeIntegerExtras("frequency", element.getFrequency(), false);
      if (element.getWhen() != null) {
        composeEnumerationCore("when", element.getWhen(), new Schedule.EventTimingEnumFactory(), false);
        composeEnumerationExtras("when", element.getWhen(), new Schedule.EventTimingEnumFactory(), false);
      }
      composeDecimalCore("duration", element.getDuration(), false);
      composeDecimalExtras("duration", element.getDuration(), false);
      if (element.getUnits() != null) {
        composeEnumerationCore("units", element.getUnits(), new Schedule.UnitsOfTimeEnumFactory(), false);
        composeEnumerationExtras("units", element.getUnits(), new Schedule.UnitsOfTimeEnumFactory(), false);
      }
      composeIntegerCore("count", element.getCount(), false);
      composeIntegerExtras("count", element.getCount(), false);
      composeDateTimeCore("end", element.getEnd(), false);
      composeDateTimeExtras("end", element.getEnd(), false);
      close();
    }
  }

  private void composeContact(String name, Contact element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getSystem() != null) {
        composeEnumerationCore("system", element.getSystem(), new Contact.ContactSystemEnumFactory(), false);
        composeEnumerationExtras("system", element.getSystem(), new Contact.ContactSystemEnumFactory(), false);
      }
      composeStringCore("value", element.getValue(), false);
      composeStringExtras("value", element.getValue(), false);
      if (element.getUse() != null) {
        composeEnumerationCore("use", element.getUse(), new Contact.ContactUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUse(), new Contact.ContactUseEnumFactory(), false);
      }
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUse() != null) {
        composeEnumerationCore("use", element.getUse(), new Address.AddressUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUse(), new Address.AddressUseEnumFactory(), false);
      }
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      if (element.getLine().size() > 0) {
        openArray("line");
        for (String_ e : element.getLine()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getLine())) {
          openArray("_line");
          for (String_ e : element.getLine()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeStringCore("city", element.getCity(), false);
      composeStringExtras("city", element.getCity(), false);
      composeStringCore("state", element.getState(), false);
      composeStringExtras("state", element.getState(), false);
      composeStringCore("zip", element.getZip(), false);
      composeStringExtras("zip", element.getZip(), false);
      composeStringCore("country", element.getCountry(), false);
      composeStringExtras("country", element.getCountry(), false);
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUse() != null) {
        composeEnumerationCore("use", element.getUse(), new HumanName.NameUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUse(), new HumanName.NameUseEnumFactory(), false);
      }
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      if (element.getFamily().size() > 0) {
        openArray("family");
        for (String_ e : element.getFamily()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getFamily())) {
          openArray("_family");
          for (String_ e : element.getFamily()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getGiven().size() > 0) {
        openArray("given");
        for (String_ e : element.getGiven()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getGiven())) {
          openArray("_given");
          for (String_ e : element.getGiven()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getPrefix().size() > 0) {
        openArray("prefix");
        for (String_ e : element.getPrefix()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getPrefix())) {
          openArray("_prefix");
          for (String_ e : element.getPrefix()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getSuffix().size() > 0) {
        openArray("suffix");
        for (String_ e : element.getSuffix()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSuffix())) {
          openArray("_suffix");
          for (String_ e : element.getSuffix()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeResourceElements(Resource element) throws Exception {
    composeBackbone(element);
    if (element.getText() != null)
      composeNarrative("text", element.getText());
    if (element.getContained().size() > 0) {
      openArray("contained");
      for (Resource r : element.getContained()) {
        if (r.getXmlId() == null)
          throw new Exception("Contained Resource has no id - one must be assigned"); // we can't assign one here - what points to it?
        open(null);
        composeResource(r);
        close();
      }
      closeArray();
    }
  }

  private void composeAdverseReaction(String name, AdverseReaction element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeResourceReference("subject", element.getSubject());
      composeBooleanCore("didNotOccurFlag", element.getDidNotOccurFlag(), false);
      composeBooleanExtras("didNotOccurFlag", element.getDidNotOccurFlag(), false);
      composeResourceReference("recorder", element.getRecorder());
      if (element.getSymptom().size() > 0) {
        openArray("symptom");
        for (AdverseReaction.AdverseReactionSymptomComponent e : element.getSymptom()) 
          composeAdverseReactionAdverseReactionSymptomComponent(null, e);
        closeArray();
      };
      if (element.getExposure().size() > 0) {
        openArray("exposure");
        for (AdverseReaction.AdverseReactionExposureComponent e : element.getExposure()) 
          composeAdverseReactionAdverseReactionExposureComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeAdverseReactionAdverseReactionSymptomComponent(String name, AdverseReaction.AdverseReactionSymptomComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSeverity() != null) {
        composeEnumerationCore("severity", element.getSeverity(), new AdverseReaction.ReactionSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverity(), new AdverseReaction.ReactionSeverityEnumFactory(), false);
      }
      close();
    }
  }

  private void composeAdverseReactionAdverseReactionExposureComponent(String name, AdverseReaction.AdverseReactionExposureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new AdverseReaction.ExposureTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new AdverseReaction.ExposureTypeEnumFactory(), false);
      }
      if (element.getCausalityExpectation() != null) {
        composeEnumerationCore("causalityExpectation", element.getCausalityExpectation(), new AdverseReaction.CausalityExpectationEnumFactory(), false);
        composeEnumerationExtras("causalityExpectation", element.getCausalityExpectation(), new AdverseReaction.CausalityExpectationEnumFactory(), false);
      }
      composeResourceReference("substance", element.getSubstance());
      close();
    }
  }

  private void composeAlert(String name, Alert element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Alert.AlertStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Alert.AlertStatusEnumFactory(), false);
      }
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeStringCore("note", element.getNote(), false);
      composeStringExtras("note", element.getNote(), false);
    }
  }

  private void composeAllergyIntolerance(String name, AllergyIntolerance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getCriticality() != null) {
        composeEnumerationCore("criticality", element.getCriticality(), new AllergyIntolerance.CriticalityEnumFactory(), false);
        composeEnumerationExtras("criticality", element.getCriticality(), new AllergyIntolerance.CriticalityEnumFactory(), false);
      }
      if (element.getSensitivityType() != null) {
        composeEnumerationCore("sensitivityType", element.getSensitivityType(), new AllergyIntolerance.SensitivitytypeEnumFactory(), false);
        composeEnumerationExtras("sensitivityType", element.getSensitivityType(), new AllergyIntolerance.SensitivitytypeEnumFactory(), false);
      }
      composeDateTimeCore("recordedDate", element.getRecordedDate(), false);
      composeDateTimeExtras("recordedDate", element.getRecordedDate(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new AllergyIntolerance.SensitivitystatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new AllergyIntolerance.SensitivitystatusEnumFactory(), false);
      }
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("recorder", element.getRecorder());
      composeResourceReference("substance", element.getSubstance());
      if (element.getReaction().size() > 0) {
        openArray("reaction");
        for (ResourceReference e : element.getReaction()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getSensitivityTest().size() > 0) {
        openArray("sensitivityTest");
        for (ResourceReference e : element.getSensitivityTest()) 
          composeResourceReference(null, e);
        closeArray();
      };
    }
  }

  private void composeAppointment(String name, Appointment element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeIntegerCore("priority", element.getPriority(), false);
      composeIntegerExtras("priority", element.getPriority(), false);
      composeCodeCore("status", element.getStatus(), false);
      composeCodeExtras("status", element.getStatus(), false);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeInstantCore("start", element.getStart(), false);
      composeInstantExtras("start", element.getStart(), false);
      composeInstantCore("end", element.getEnd(), false);
      composeInstantExtras("end", element.getEnd(), false);
      if (element.getSlot().size() > 0) {
        openArray("slot");
        for (ResourceReference e : element.getSlot()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeResourceReference("location", element.getLocation());
      composeStringCore("comment", element.getComment(), false);
      composeStringExtras("comment", element.getComment(), false);
      composeResourceReference("order", element.getOrder());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (Appointment.AppointmentParticipantComponent e : element.getParticipant()) 
          composeAppointmentAppointmentParticipantComponent(null, e);
        closeArray();
      };
      composeResourceReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTimeCore("lastModified", element.getLastModified(), false);
      composeDateTimeExtras("lastModified", element.getLastModified(), false);
    }
  }

  private void composeAppointmentAppointmentParticipantComponent(String name, Appointment.AppointmentParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("individual", element.getIndividual());
      if (element.getRequired() != null) {
        composeEnumerationCore("required", element.getRequired(), new Appointment.ParticipantrequiredEnumFactory(), false);
        composeEnumerationExtras("required", element.getRequired(), new Appointment.ParticipantrequiredEnumFactory(), false);
      }
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Appointment.ParticipationstatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Appointment.ParticipationstatusEnumFactory(), false);
      }
      close();
    }
  }

  private void composeAppointmentResponse(String name, AppointmentResponse element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("appointment", element.getAppointment());
      if (element.getParticipantType().size() > 0) {
        openArray("participantType");
        for (CodeableConcept e : element.getParticipantType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getIndividual().size() > 0) {
        openArray("individual");
        for (ResourceReference e : element.getIndividual()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getParticipantStatus() != null) {
        composeEnumerationCore("participantStatus", element.getParticipantStatus(), new AppointmentResponse.ParticipantstatusEnumFactory(), false);
        composeEnumerationExtras("participantStatus", element.getParticipantStatus(), new AppointmentResponse.ParticipantstatusEnumFactory(), false);
      }
      composeStringCore("comment", element.getComment(), false);
      composeStringExtras("comment", element.getComment(), false);
      composeInstantCore("start", element.getStart(), false);
      composeInstantExtras("start", element.getStart(), false);
      composeInstantCore("end", element.getEnd(), false);
      composeInstantExtras("end", element.getEnd(), false);
      composeResourceReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTimeCore("lastModified", element.getLastModified(), false);
      composeDateTimeExtras("lastModified", element.getLastModified(), false);
    }
  }

  private void composeAvailability(String name, Availability element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("individual", element.getIndividual());
      composePeriod("planningHorizon", element.getPlanningHorizon());
      composeStringCore("comment", element.getComment(), false);
      composeStringExtras("comment", element.getComment(), false);
      composeDateTimeCore("lastModified", element.getLastModified(), false);
      composeDateTimeExtras("lastModified", element.getLastModified(), false);
    }
  }

  private void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("patient", element.getPatient());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new CarePlan.CarePlanStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new CarePlan.CarePlanStatusEnumFactory(), false);
      }
      composePeriod("period", element.getPeriod());
      composeDateTimeCore("modified", element.getModified(), false);
      composeDateTimeExtras("modified", element.getModified(), false);
      if (element.getConcern().size() > 0) {
        openArray("concern");
        for (ResourceReference e : element.getConcern()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (CarePlan.CarePlanParticipantComponent e : element.getParticipant()) 
          composeCarePlanCarePlanParticipantComponent(null, e);
        closeArray();
      };
      if (element.getGoal().size() > 0) {
        openArray("goal");
        for (CarePlan.CarePlanGoalComponent e : element.getGoal()) 
          composeCarePlanCarePlanGoalComponent(null, e);
        closeArray();
      };
      if (element.getActivity().size() > 0) {
        openArray("activity");
        for (CarePlan.CarePlanActivityComponent e : element.getActivity()) 
          composeCarePlanCarePlanActivityComponent(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotes(), false);
      composeStringExtras("notes", element.getNotes(), false);
    }
  }

  private void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("role", element.getRole());
      composeResourceReference("member", element.getMember());
      close();
    }
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new CarePlan.CarePlanGoalStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new CarePlan.CarePlanGoalStatusEnumFactory(), false);
      }
      composeStringCore("notes", element.getNotes(), false);
      composeStringExtras("notes", element.getNotes(), false);
      if (element.getConcern().size() > 0) {
        openArray("concern");
        for (ResourceReference e : element.getConcern()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getGoal().size() > 0) {
        openArray("goal");
        for (String_ e : element.getGoal()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getGoal())) {
          openArray("_goal");
          for (String_ e : element.getGoal()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new CarePlan.CarePlanActivityStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new CarePlan.CarePlanActivityStatusEnumFactory(), false);
      }
      composeBooleanCore("prohibited", element.getProhibited(), false);
      composeBooleanExtras("prohibited", element.getProhibited(), false);
      if (element.getActionResulting().size() > 0) {
        openArray("actionResulting");
        for (ResourceReference e : element.getActionResulting()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotes(), false);
      composeStringExtras("notes", element.getNotes(), false);
      composeResourceReference("detail", element.getDetail());
      composeCarePlanCarePlanActivitySimpleComponent("simple", element.getSimple());
      close();
    }
  }

  private void composeCarePlanCarePlanActivitySimpleComponent(String name, CarePlan.CarePlanActivitySimpleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCategory() != null) {
        composeEnumerationCore("category", element.getCategory(), new CarePlan.CarePlanActivityCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategory(), new CarePlan.CarePlanActivityCategoryEnumFactory(), false);
      }
      composeCodeableConcept("code", element.getCode());
      composeType("timing", element.getTiming());
      composeResourceReference("location", element.getLocation());
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (ResourceReference e : element.getPerformer()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeResourceReference("product", element.getProduct());
      composeQuantity("dailyAmount", element.getDailyAmount());
      composeQuantity("quantity", element.getQuantity());
      composeStringCore("details", element.getDetails(), false);
      composeStringExtras("details", element.getDetails(), false);
      close();
    }
  }

  private void composeComposition(String name, Composition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      composeStringCore("title", element.getTitle(), false);
      composeStringExtras("title", element.getTitle(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Composition.CompositionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Composition.CompositionStatusEnumFactory(), false);
      }
      composeCoding("confidentiality", element.getConfidentiality());
      composeResourceReference("subject", element.getSubject());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (ResourceReference e : element.getAuthor()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getAttester().size() > 0) {
        openArray("attester");
        for (Composition.CompositionAttesterComponent e : element.getAttester()) 
          composeCompositionCompositionAttesterComponent(null, e);
        closeArray();
      };
      composeResourceReference("custodian", element.getCustodian());
      composeCompositionCompositionEventComponent("event", element.getEvent());
      composeResourceReference("encounter", element.getEncounter());
      if (element.getSection().size() > 0) {
        openArray("section");
        for (Composition.SectionComponent e : element.getSection()) 
          composeCompositionSectionComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeCompositionCompositionAttesterComponent(String name, Composition.CompositionAttesterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getMode().size() > 0) {
        openArray("mode");
        for (Enumeration<Composition.CompositionAttestationMode> e : element.getMode()) 
          composeEnumerationCore(null, e, new Composition.CompositionAttestationModeEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getMode())) {
          openArray("_mode");
          for (Enumeration<Composition.CompositionAttestationMode> e : element.getMode()) 
            composeEnumerationExtras(null, e, new Composition.CompositionAttestationModeEnumFactory(), true);
          closeArray();
        }
      };
      composeDateTimeCore("time", element.getTime(), false);
      composeDateTimeExtras("time", element.getTime(), false);
      composeResourceReference("party", element.getParty());
      close();
    }
  }

  private void composeCompositionCompositionEventComponent(String name, Composition.CompositionEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (CodeableConcept e : element.getCode()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ResourceReference e : element.getDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeCompositionSectionComponent(String name, Composition.SectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("title", element.getTitle(), false);
      composeStringExtras("title", element.getTitle(), false);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("content", element.getContent());
      if (element.getSection().size() > 0) {
        openArray("section");
        for (Composition.SectionComponent e : element.getSection()) 
          composeCompositionSectionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConceptMap(String name, ConceptMap element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("identifier", element.getIdentifier(), false);
      composeStringExtras("identifier", element.getIdentifier(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("publisher", element.getPublisher(), false);
      composeStringExtras("publisher", element.getPublisher(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeStringCore("copyright", element.getCopyright(), false);
      composeStringExtras("copyright", element.getCopyright(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new ConceptMap.ValuesetStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new ConceptMap.ValuesetStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimental(), false);
      composeBooleanExtras("experimental", element.getExperimental(), false);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeType("source", element.getSource());
      composeType("target", element.getTarget());
      if (element.getElement().size() > 0) {
        openArray("element");
        for (ConceptMap.ConceptMapElementComponent e : element.getElement()) 
          composeConceptMapConceptMapElementComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeConceptMapConceptMapElementComponent(String name, ConceptMap.ConceptMapElementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("codeSystem", element.getCodeSystem(), false);
      composeUriExtras("codeSystem", element.getCodeSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      if (element.getDependsOn().size() > 0) {
        openArray("dependsOn");
        for (ConceptMap.OtherElementComponent e : element.getDependsOn()) 
          composeConceptMapOtherElementComponent(null, e);
        closeArray();
      };
      if (element.getMap().size() > 0) {
        openArray("map");
        for (ConceptMap.ConceptMapElementMapComponent e : element.getMap()) 
          composeConceptMapConceptMapElementMapComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConceptMapOtherElementComponent(String name, ConceptMap.OtherElementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("element", element.getElement(), false);
      composeUriExtras("element", element.getElement(), false);
      composeUriCore("codeSystem", element.getCodeSystem(), false);
      composeUriExtras("codeSystem", element.getCodeSystem(), false);
      composeStringCore("code", element.getCode(), false);
      composeStringExtras("code", element.getCode(), false);
      close();
    }
  }

  private void composeConceptMapConceptMapElementMapComponent(String name, ConceptMap.ConceptMapElementMapComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("codeSystem", element.getCodeSystem(), false);
      composeUriExtras("codeSystem", element.getCodeSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      if (element.getEquivalence() != null) {
        composeEnumerationCore("equivalence", element.getEquivalence(), new ConceptMap.ConceptEquivalenceEnumFactory(), false);
        composeEnumerationExtras("equivalence", element.getEquivalence(), new ConceptMap.ConceptEquivalenceEnumFactory(), false);
      }
      composeStringCore("comments", element.getComments(), false);
      composeStringExtras("comments", element.getComments(), false);
      if (element.getProduct().size() > 0) {
        openArray("product");
        for (ConceptMap.OtherElementComponent e : element.getProduct()) 
          composeConceptMapOtherElementComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeCondition(String name, Condition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("encounter", element.getEncounter());
      composeResourceReference("asserter", element.getAsserter());
      composeDateCore("dateAsserted", element.getDateAsserted(), false);
      composeDateExtras("dateAsserted", element.getDateAsserted(), false);
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Condition.ConditionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Condition.ConditionStatusEnumFactory(), false);
      }
      composeCodeableConcept("certainty", element.getCertainty());
      composeCodeableConcept("severity", element.getSeverity());
      composeType("onset", element.getOnset());
      composeType("abatement", element.getAbatement());
      composeConditionConditionStageComponent("stage", element.getStage());
      if (element.getEvidence().size() > 0) {
        openArray("evidence");
        for (Condition.ConditionEvidenceComponent e : element.getEvidence()) 
          composeConditionConditionEvidenceComponent(null, e);
        closeArray();
      };
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (Condition.ConditionLocationComponent e : element.getLocation()) 
          composeConditionConditionLocationComponent(null, e);
        closeArray();
      };
      if (element.getRelatedItem().size() > 0) {
        openArray("relatedItem");
        for (Condition.ConditionRelatedItemComponent e : element.getRelatedItem()) 
          composeConditionConditionRelatedItemComponent(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotes(), false);
      composeStringExtras("notes", element.getNotes(), false);
    }
  }

  private void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("summary", element.getSummary());
      if (element.getAssessment().size() > 0) {
        openArray("assessment");
        for (ResourceReference e : element.getAssessment()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConditionConditionEvidenceComponent(String name, Condition.ConditionEvidenceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ResourceReference e : element.getDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConditionConditionLocationComponent(String name, Condition.ConditionLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeStringCore("detail", element.getDetail(), false);
      composeStringExtras("detail", element.getDetail(), false);
      close();
    }
  }

  private void composeConditionConditionRelatedItemComponent(String name, Condition.ConditionRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Condition.ConditionRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Condition.ConditionRelationshipTypeEnumFactory(), false);
      }
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("target", element.getTarget());
      close();
    }
  }

  private void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("identifier", element.getIdentifier(), false);
      composeStringExtras("identifier", element.getIdentifier(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("publisher", element.getPublisher(), false);
      composeStringExtras("publisher", element.getPublisher(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Conformance.ConformanceStatementStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Conformance.ConformanceStatementStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimental(), false);
      composeBooleanExtras("experimental", element.getExperimental(), false);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeIdCore("fhirVersion", element.getFhirVersion(), false);
      composeIdExtras("fhirVersion", element.getFhirVersion(), false);
      composeBooleanCore("acceptUnknown", element.getAcceptUnknown(), false);
      composeBooleanExtras("acceptUnknown", element.getAcceptUnknown(), false);
      if (element.getFormat().size() > 0) {
        openArray("format");
        for (Code e : element.getFormat()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getFormat())) {
          openArray("_format");
          for (Code e : element.getFormat()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getProfile().size() > 0) {
        openArray("profile");
        for (ResourceReference e : element.getProfile()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getRest().size() > 0) {
        openArray("rest");
        for (Conformance.ConformanceRestComponent e : element.getRest()) 
          composeConformanceConformanceRestComponent(null, e);
        closeArray();
      };
      if (element.getMessaging().size() > 0) {
        openArray("messaging");
        for (Conformance.ConformanceMessagingComponent e : element.getMessaging()) 
          composeConformanceConformanceMessagingComponent(null, e);
        closeArray();
      };
      if (element.getDocument().size() > 0) {
        openArray("document");
        for (Conformance.ConformanceDocumentComponent e : element.getDocument()) 
          composeConformanceConformanceDocumentComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeConformanceConformanceSoftwareComponent(String name, Conformance.ConformanceSoftwareComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeDateTimeCore("releaseDate", element.getReleaseDate(), false);
      composeDateTimeExtras("releaseDate", element.getReleaseDate(), false);
      close();
    }
  }

  private void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getMode() != null) {
        composeEnumerationCore("mode", element.getMode(), new Conformance.RestfulConformanceModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getMode(), new Conformance.RestfulConformanceModeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      composeConformanceConformanceRestSecurityComponent("security", element.getSecurity());
      if (element.getResource().size() > 0) {
        openArray("resource");
        for (Conformance.ConformanceRestResourceComponent e : element.getResource()) 
          composeConformanceConformanceRestResourceComponent(null, e);
        closeArray();
      };
      if (element.getOperation().size() > 0) {
        openArray("operation");
        for (Conformance.ConformanceRestOperationComponent e : element.getOperation()) 
          composeConformanceConformanceRestOperationComponent(null, e);
        closeArray();
      };
      if (element.getQuery().size() > 0) {
        openArray("query");
        for (Conformance.ConformanceRestQueryComponent e : element.getQuery()) 
          composeConformanceConformanceRestQueryComponent(null, e);
        closeArray();
      };
      if (element.getDocumentMailbox().size() > 0) {
        openArray("documentMailbox");
        for (Uri e : element.getDocumentMailbox()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getDocumentMailbox())) {
          openArray("_documentMailbox");
          for (Uri e : element.getDocumentMailbox()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      close();
    }
  }

  private void composeConformanceConformanceRestSecurityComponent(String name, Conformance.ConformanceRestSecurityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeBooleanCore("cors", element.getCors(), false);
      composeBooleanExtras("cors", element.getCors(), false);
      if (element.getService().size() > 0) {
        openArray("service");
        for (CodeableConcept e : element.getService()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getCertificate().size() > 0) {
        openArray("certificate");
        for (Conformance.ConformanceRestSecurityCertificateComponent e : element.getCertificate()) 
          composeConformanceConformanceRestSecurityCertificateComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceRestSecurityCertificateComponent(String name, Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("type", element.getType(), false);
      composeCodeExtras("type", element.getType(), false);
      composeBase64BinaryCore("blob", element.getBlob(), false);
      composeBase64BinaryExtras("blob", element.getBlob(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("type", element.getType(), false);
      composeCodeExtras("type", element.getType(), false);
      composeResourceReference("profile", element.getProfile());
      if (element.getOperation().size() > 0) {
        openArray("operation");
        for (Conformance.ConformanceRestResourceOperationComponent e : element.getOperation()) 
          composeConformanceConformanceRestResourceOperationComponent(null, e);
        closeArray();
      };
      composeBooleanCore("readHistory", element.getReadHistory(), false);
      composeBooleanExtras("readHistory", element.getReadHistory(), false);
      composeBooleanCore("updateCreate", element.getUpdateCreate(), false);
      composeBooleanExtras("updateCreate", element.getUpdateCreate(), false);
      if (element.getSearchInclude().size() > 0) {
        openArray("searchInclude");
        for (String_ e : element.getSearchInclude()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSearchInclude())) {
          openArray("_searchInclude");
          for (String_ e : element.getSearchInclude()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getSearchParam().size() > 0) {
        openArray("searchParam");
        for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getSearchParam()) 
          composeConformanceConformanceRestResourceSearchParamComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceRestResourceOperationComponent(String name, Conformance.ConformanceRestResourceOperationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCode() != null) {
        composeEnumerationCore("code", element.getCode(), new Conformance.TypeRestfulOperationEnumFactory(), false);
        composeEnumerationExtras("code", element.getCode(), new Conformance.TypeRestfulOperationEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeUriCore("definition", element.getDefinition(), false);
      composeUriExtras("definition", element.getDefinition(), false);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Conformance.SearchParamTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Conformance.SearchParamTypeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Code e : element.getTarget()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getTarget())) {
          openArray("_target");
          for (Code e : element.getTarget()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getChain().size() > 0) {
        openArray("chain");
        for (String_ e : element.getChain()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getChain())) {
          openArray("_chain");
          for (String_ e : element.getChain()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      close();
    }
  }

  private void composeConformanceConformanceRestOperationComponent(String name, Conformance.ConformanceRestOperationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCode() != null) {
        composeEnumerationCore("code", element.getCode(), new Conformance.SystemRestfulOperationEnumFactory(), false);
        composeEnumerationExtras("code", element.getCode(), new Conformance.SystemRestfulOperationEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestQueryComponent(String name, Conformance.ConformanceRestQueryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeUriCore("definition", element.getDefinition(), false);
      composeUriExtras("definition", element.getDefinition(), false);
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getParameter()) 
          composeConformanceConformanceRestResourceSearchParamComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("endpoint", element.getEndpoint(), false);
      composeUriExtras("endpoint", element.getEndpoint(), false);
      composeIntegerCore("reliableCache", element.getReliableCache(), false);
      composeIntegerExtras("reliableCache", element.getReliableCache(), false);
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Conformance.ConformanceMessagingEventComponent e : element.getEvent()) 
          composeConformanceConformanceMessagingEventComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceMessagingEventComponent(String name, Conformance.ConformanceMessagingEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCoding("code", element.getCode());
      if (element.getCategory() != null) {
        composeEnumerationCore("category", element.getCategory(), new Conformance.MessageSignificanceCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategory(), new Conformance.MessageSignificanceCategoryEnumFactory(), false);
      }
      if (element.getMode() != null) {
        composeEnumerationCore("mode", element.getMode(), new Conformance.MessageConformanceEventModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getMode(), new Conformance.MessageConformanceEventModeEnumFactory(), false);
      }
      if (element.getProtocol().size() > 0) {
        openArray("protocol");
        for (Coding e : element.getProtocol()) 
          composeCoding(null, e);
        closeArray();
      };
      composeCodeCore("focus", element.getFocus(), false);
      composeCodeExtras("focus", element.getFocus(), false);
      composeResourceReference("request", element.getRequest());
      composeResourceReference("response", element.getResponse());
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      close();
    }
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getMode() != null) {
        composeEnumerationCore("mode", element.getMode(), new Conformance.DocumentModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getMode(), new Conformance.DocumentModeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      composeResourceReference("profile", element.getProfile());
      close();
    }
  }

  private void composeDataElement(String name, DataElement element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeStringCore("publisher", element.getPublisher(), false);
      composeStringExtras("publisher", element.getPublisher(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DataElement.ResourceObservationDefStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DataElement.ResourceObservationDefStatusEnumFactory(), false);
      }
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      if (element.getCategory().size() > 0) {
        openArray("category");
        for (CodeableConcept e : element.getCategory()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("question", element.getQuestion(), false);
      composeStringExtras("question", element.getQuestion(), false);
      composeStringCore("definition", element.getDefinition(), false);
      composeStringExtras("definition", element.getDefinition(), false);
      composeStringCore("comments", element.getComments(), false);
      composeStringExtras("comments", element.getComments(), false);
      composeStringCore("requirements", element.getRequirements(), false);
      composeStringExtras("requirements", element.getRequirements(), false);
      if (element.getSynonym().size() > 0) {
        openArray("synonym");
        for (String_ e : element.getSynonym()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSynonym())) {
          openArray("_synonym");
          for (String_ e : element.getSynonym()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeCodeCore("type", element.getType(), false);
      composeCodeExtras("type", element.getType(), false);
      composeType("example", element.getExample());
      composeIntegerCore("maxLength", element.getMaxLength(), false);
      composeIntegerExtras("maxLength", element.getMaxLength(), false);
      composeCodeableConcept("units", element.getUnits());
      composeDataElementDataElementBindingComponent("binding", element.getBinding());
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (DataElement.DataElementMappingComponent e : element.getMapping()) 
          composeDataElementDataElementMappingComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeDataElementDataElementBindingComponent(String name, DataElement.DataElementBindingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeBooleanCore("isExtensible", element.getIsExtensible(), false);
      composeBooleanExtras("isExtensible", element.getIsExtensible(), false);
      if (element.getConformance() != null) {
        composeEnumerationCore("conformance", element.getConformance(), new DataElement.BindingConformanceEnumFactory(), false);
        composeEnumerationExtras("conformance", element.getConformance(), new DataElement.BindingConformanceEnumFactory(), false);
      }
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeResourceReference("valueSet", element.getValueSet());
      close();
    }
  }

  private void composeDataElementDataElementMappingComponent(String name, DataElement.DataElementMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("uri", element.getUri(), false);
      composeUriExtras("uri", element.getUri(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("comments", element.getComments(), false);
      composeStringExtras("comments", element.getComments(), false);
      composeStringCore("map", element.getMap(), false);
      composeStringExtras("map", element.getMap(), false);
      close();
    }
  }

  private void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      composeStringCore("manufacturer", element.getManufacturer(), false);
      composeStringExtras("manufacturer", element.getManufacturer(), false);
      composeStringCore("model", element.getModel(), false);
      composeStringExtras("model", element.getModel(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeDateCore("expiry", element.getExpiry(), false);
      composeDateExtras("expiry", element.getExpiry(), false);
      composeStringCore("udi", element.getUdi(), false);
      composeStringExtras("udi", element.getUdi(), false);
      composeStringCore("lotNumber", element.getLotNumber(), false);
      composeStringExtras("lotNumber", element.getLotNumber(), false);
      composeResourceReference("owner", element.getOwner());
      composeResourceReference("location", element.getLocation());
      composeResourceReference("patient", element.getPatient());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Contact e : element.getContact()) 
          composeContact(null, e);
        closeArray();
      };
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
    }
  }

  private void composeDeviceObservationReport(String name, DeviceObservationReport element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeInstantCore("instant", element.getInstant(), false);
      composeInstantExtras("instant", element.getInstant(), false);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("source", element.getSource());
      composeResourceReference("subject", element.getSubject());
      if (element.getVirtualDevice().size() > 0) {
        openArray("virtualDevice");
        for (DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent e : element.getVirtualDevice()) 
          composeDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(String name, DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getChannel().size() > 0) {
        openArray("channel");
        for (DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent e : element.getChannel()) 
          composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(String name, DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getMetric().size() > 0) {
        openArray("metric");
        for (DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent e : element.getMetric()) 
          composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(String name, DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("observation", element.getObservation());
      close();
    }
  }

  private void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("orderer", element.getOrderer());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("encounter", element.getEncounter());
      composeStringCore("clinicalNotes", element.getClinicalNotes(), false);
      composeStringExtras("clinicalNotes", element.getClinicalNotes(), false);
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (ResourceReference e : element.getSpecimen()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      if (element.getPriority() != null) {
        composeEnumerationCore("priority", element.getPriority(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory(), false);
        composeEnumerationExtras("priority", element.getPriority(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory(), false);
      }
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent(null, e);
        closeArray();
      };
      if (element.getItem().size() > 0) {
        openArray("item");
        for (DiagnosticOrder.DiagnosticOrderItemComponent e : element.getItem()) 
          composeDiagnosticOrderDiagnosticOrderItemComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderEventComponent(String name, DiagnosticOrder.DiagnosticOrderEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      composeCodeableConcept("description", element.getDescription());
      composeDateTimeCore("dateTime", element.getDateTime(), false);
      composeDateTimeExtras("dateTime", element.getDateTime(), false);
      composeResourceReference("actor", element.getActor());
      close();
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (ResourceReference e : element.getSpecimen()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDiagnosticReport(String name, DiagnosticReport element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeCodeableConcept("name", element.getName());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DiagnosticReport.DiagnosticReportStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DiagnosticReport.DiagnosticReportStatusEnumFactory(), false);
      }
      composeDateTimeCore("issued", element.getIssued(), false);
      composeDateTimeExtras("issued", element.getIssued(), false);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getRequestDetail().size() > 0) {
        openArray("requestDetail");
        for (ResourceReference e : element.getRequestDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      composeType("diagnostic", element.getDiagnostic());
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (ResourceReference e : element.getSpecimen()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getResult().size() > 0) {
        openArray("result");
        for (ResourceReference e : element.getResult()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getImagingStudy().size() > 0) {
        openArray("imagingStudy");
        for (ResourceReference e : element.getImagingStudy()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getImage().size() > 0) {
        openArray("image");
        for (DiagnosticReport.DiagnosticReportImageComponent e : element.getImage()) 
          composeDiagnosticReportDiagnosticReportImageComponent(null, e);
        closeArray();
      };
      composeStringCore("conclusion", element.getConclusion(), false);
      composeStringExtras("conclusion", element.getConclusion(), false);
      if (element.getCodedDiagnosis().size() > 0) {
        openArray("codedDiagnosis");
        for (CodeableConcept e : element.getCodedDiagnosis()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getPresentedForm().size() > 0) {
        openArray("presentedForm");
        for (Attachment e : element.getPresentedForm()) 
          composeAttachment(null, e);
        closeArray();
      };
    }
  }

  private void composeDiagnosticReportDiagnosticReportImageComponent(String name, DiagnosticReport.DiagnosticReportImageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("comment", element.getComment(), false);
      composeStringExtras("comment", element.getComment(), false);
      composeResourceReference("link", element.getLink());
      close();
    }
  }

  private void composeDocumentManifest(String name, DocumentManifest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getSubject().size() > 0) {
        openArray("subject");
        for (ResourceReference e : element.getSubject()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getRecipient().size() > 0) {
        openArray("recipient");
        for (ResourceReference e : element.getRecipient()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (ResourceReference e : element.getAuthor()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeDateTimeCore("created", element.getCreated(), false);
      composeDateTimeExtras("created", element.getCreated(), false);
      composeUriCore("source", element.getSource(), false);
      composeUriExtras("source", element.getSource(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DocumentManifest.DocumentReferenceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DocumentManifest.DocumentReferenceStatusEnumFactory(), false);
      }
      composeResourceReference("supercedes", element.getSupercedes());
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeCodeableConcept("confidentiality", element.getConfidentiality());
      if (element.getContent().size() > 0) {
        openArray("content");
        for (ResourceReference e : element.getContent()) 
          composeResourceReference(null, e);
        closeArray();
      };
    }
  }

  private void composeDocumentReference(String name, DocumentReference element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (ResourceReference e : element.getAuthor()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeResourceReference("custodian", element.getCustodian());
      composeUriCore("policyManager", element.getPolicyManager(), false);
      composeUriExtras("policyManager", element.getPolicyManager(), false);
      composeResourceReference("authenticator", element.getAuthenticator());
      composeDateTimeCore("created", element.getCreated(), false);
      composeDateTimeExtras("created", element.getCreated(), false);
      composeInstantCore("indexed", element.getIndexed(), false);
      composeInstantExtras("indexed", element.getIndexed(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new DocumentReference.DocumentReferenceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new DocumentReference.DocumentReferenceStatusEnumFactory(), false);
      }
      composeCodeableConcept("docStatus", element.getDocStatus());
      if (element.getRelatesTo().size() > 0) {
        openArray("relatesTo");
        for (DocumentReference.DocumentReferenceRelatesToComponent e : element.getRelatesTo()) 
          composeDocumentReferenceDocumentReferenceRelatesToComponent(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getConfidentiality().size() > 0) {
        openArray("confidentiality");
        for (CodeableConcept e : element.getConfidentiality()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeCodeCore("primaryLanguage", element.getPrimaryLanguage(), false);
      composeCodeExtras("primaryLanguage", element.getPrimaryLanguage(), false);
      composeCodeCore("mimeType", element.getMimeType(), false);
      composeCodeExtras("mimeType", element.getMimeType(), false);
      if (element.getFormat().size() > 0) {
        openArray("format");
        for (Uri e : element.getFormat()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getFormat())) {
          openArray("_format");
          for (Uri e : element.getFormat()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      composeIntegerCore("size", element.getSize(), false);
      composeIntegerExtras("size", element.getSize(), false);
      composeStringCore("hash", element.getHash(), false);
      composeStringExtras("hash", element.getHash(), false);
      composeUriCore("location", element.getLocation(), false);
      composeUriExtras("location", element.getLocation(), false);
      composeDocumentReferenceDocumentReferenceServiceComponent("service", element.getService());
      composeDocumentReferenceDocumentReferenceContextComponent("context", element.getContext());
    }
  }

  private void composeDocumentReferenceDocumentReferenceRelatesToComponent(String name, DocumentReference.DocumentReferenceRelatesToComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCode() != null) {
        composeEnumerationCore("code", element.getCode(), new DocumentReference.DocumentRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCode(), new DocumentReference.DocumentRelationshipTypeEnumFactory(), false);
      }
      composeResourceReference("target", element.getTarget());
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeStringCore("address", element.getAddress(), false);
      composeStringExtras("address", element.getAddress(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (DocumentReference.DocumentReferenceServiceParameterComponent e : element.getParameter()) 
          composeDocumentReferenceDocumentReferenceServiceParameterComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceParameterComponent(String name, DocumentReference.DocumentReferenceServiceParameterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("value", element.getValue(), false);
      composeStringExtras("value", element.getValue(), false);
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceContextComponent(String name, DocumentReference.DocumentReferenceContextComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (CodeableConcept e : element.getEvent()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeCodeableConcept("facilityType", element.getFacilityType());
      close();
    }
  }

  private void composeEncounter(String name, Encounter element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Encounter.EncounterStateEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Encounter.EncounterStateEnumFactory(), false);
      }
      if (element.getClass() != null) {
        composeEnumerationCore("class", element.getClass_(), new Encounter.EncounterClassEnumFactory(), false);
        composeEnumerationExtras("class", element.getClass_(), new Encounter.EncounterClassEnumFactory(), false);
      }
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (Encounter.EncounterParticipantComponent e : element.getParticipant()) 
          composeEncounterEncounterParticipantComponent(null, e);
        closeArray();
      };
      composeResourceReference("fulfills", element.getFulfills());
      composePeriod("period", element.getPeriod());
      composeDuration("length", element.getLength());
      composeCodeableConcept("reason", element.getReason());
      composeResourceReference("indication", element.getIndication());
      composeCodeableConcept("priority", element.getPriority());
      composeEncounterEncounterHospitalizationComponent("hospitalization", element.getHospitalization());
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (Encounter.EncounterLocationComponent e : element.getLocation()) 
          composeEncounterEncounterLocationComponent(null, e);
        closeArray();
      };
      composeResourceReference("serviceProvider", element.getServiceProvider());
      composeResourceReference("partOf", element.getPartOf());
    }
  }

  private void composeEncounterEncounterParticipantComponent(String name, Encounter.EncounterParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("individual", element.getIndividual());
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      composeResourceReference("origin", element.getOrigin());
      composeCodeableConcept("admitSource", element.getAdmitSource());
      composePeriod("period", element.getPeriod());
      if (element.getAccomodation().size() > 0) {
        openArray("accomodation");
        for (Encounter.EncounterHospitalizationAccomodationComponent e : element.getAccomodation()) 
          composeEncounterEncounterHospitalizationAccomodationComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("diet", element.getDiet());
      if (element.getSpecialCourtesy().size() > 0) {
        openArray("specialCourtesy");
        for (CodeableConcept e : element.getSpecialCourtesy()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getSpecialArrangement().size() > 0) {
        openArray("specialArrangement");
        for (CodeableConcept e : element.getSpecialArrangement()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("destination", element.getDestination());
      composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      composeResourceReference("dischargeDiagnosis", element.getDischargeDiagnosis());
      composeBooleanCore("reAdmission", element.getReAdmission(), false);
      composeBooleanExtras("reAdmission", element.getReAdmission(), false);
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponent(String name, Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("location", element.getLocation());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeFamilyHistory(String name, FamilyHistory element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeStringCore("note", element.getNote(), false);
      composeStringExtras("note", element.getNote(), false);
      if (element.getRelation().size() > 0) {
        openArray("relation");
        for (FamilyHistory.FamilyHistoryRelationComponent e : element.getRelation()) 
          composeFamilyHistoryFamilyHistoryRelationComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationComponent(String name, FamilyHistory.FamilyHistoryRelationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("born", element.getBorn());
      composeType("deceased", element.getDeceased());
      composeStringCore("note", element.getNote(), false);
      composeStringExtras("note", element.getNote(), false);
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (FamilyHistory.FamilyHistoryRelationConditionComponent e : element.getCondition()) 
          composeFamilyHistoryFamilyHistoryRelationConditionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationConditionComponent(String name, FamilyHistory.FamilyHistoryRelationConditionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("onset", element.getOnset());
      composeStringCore("note", element.getNote(), false);
      composeStringExtras("note", element.getNote(), false);
      close();
    }
  }

  private void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Group.GroupTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Group.GroupTypeEnumFactory(), false);
      }
      composeBooleanCore("actual", element.getActual(), false);
      composeBooleanExtras("actual", element.getActual(), false);
      composeCodeableConcept("code", element.getCode());
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeIntegerCore("quantity", element.getQuantity(), false);
      composeIntegerExtras("quantity", element.getQuantity(), false);
      if (element.getCharacteristic().size() > 0) {
        openArray("characteristic");
        for (Group.GroupCharacteristicComponent e : element.getCharacteristic()) 
          composeGroupGroupCharacteristicComponent(null, e);
        closeArray();
      };
      if (element.getMember().size() > 0) {
        openArray("member");
        for (ResourceReference e : element.getMember()) 
          composeResourceReference(null, e);
        closeArray();
      };
    }
  }

  private void composeGroupGroupCharacteristicComponent(String name, Group.GroupCharacteristicComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeType("value", element.getValue());
      composeBooleanCore("exclude", element.getExclude(), false);
      composeBooleanExtras("exclude", element.getExclude(), false);
      close();
    }
  }

  private void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeDateTimeCore("dateTime", element.getDateTime(), false);
      composeDateTimeExtras("dateTime", element.getDateTime(), false);
      composeResourceReference("subject", element.getSubject());
      composeOidCore("uid", element.getUid(), false);
      composeOidExtras("uid", element.getUid(), false);
      composeIdentifier("accessionNo", element.getAccessionNo());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getOrder().size() > 0) {
        openArray("order");
        for (ResourceReference e : element.getOrder()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getModality().size() > 0) {
        openArray("modality");
        for (Enumeration<ImagingStudy.ImagingModality> e : element.getModality()) 
          composeEnumerationCore(null, e, new ImagingStudy.ImagingModalityEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getModality())) {
          openArray("_modality");
          for (Enumeration<ImagingStudy.ImagingModality> e : element.getModality()) 
            composeEnumerationExtras(null, e, new ImagingStudy.ImagingModalityEnumFactory(), true);
          closeArray();
        }
      };
      composeResourceReference("referrer", element.getReferrer());
      if (element.getAvailability() != null) {
        composeEnumerationCore("availability", element.getAvailability(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
        composeEnumerationExtras("availability", element.getAvailability(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
      }
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      composeIntegerCore("numberOfSeries", element.getNumberOfSeries(), false);
      composeIntegerExtras("numberOfSeries", element.getNumberOfSeries(), false);
      composeIntegerCore("numberOfInstances", element.getNumberOfInstances(), false);
      composeIntegerExtras("numberOfInstances", element.getNumberOfInstances(), false);
      composeStringCore("clinicalInformation", element.getClinicalInformation(), false);
      composeStringExtras("clinicalInformation", element.getClinicalInformation(), false);
      if (element.getProcedure().size() > 0) {
        openArray("procedure");
        for (Coding e : element.getProcedure()) 
          composeCoding(null, e);
        closeArray();
      };
      composeResourceReference("interpreter", element.getInterpreter());
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getSeries().size() > 0) {
        openArray("series");
        for (ImagingStudy.ImagingStudySeriesComponent e : element.getSeries()) 
          composeImagingStudyImagingStudySeriesComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeImagingStudyImagingStudySeriesComponent(String name, ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIntegerCore("number", element.getNumber(), false);
      composeIntegerExtras("number", element.getNumber(), false);
      if (element.getModality() != null) {
        composeEnumerationCore("modality", element.getModality(), new ImagingStudy.ModalityEnumFactory(), false);
        composeEnumerationExtras("modality", element.getModality(), new ImagingStudy.ModalityEnumFactory(), false);
      }
      composeOidCore("uid", element.getUid(), false);
      composeOidExtras("uid", element.getUid(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeIntegerCore("numberOfInstances", element.getNumberOfInstances(), false);
      composeIntegerExtras("numberOfInstances", element.getNumberOfInstances(), false);
      if (element.getAvailability() != null) {
        composeEnumerationCore("availability", element.getAvailability(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
        composeEnumerationExtras("availability", element.getAvailability(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
      }
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      composeCoding("bodySite", element.getBodySite());
      composeDateTimeCore("dateTime", element.getDateTime(), false);
      composeDateTimeExtras("dateTime", element.getDateTime(), false);
      if (element.getInstance().size() > 0) {
        openArray("instance");
        for (ImagingStudy.ImagingStudySeriesInstanceComponent e : element.getInstance()) 
          composeImagingStudyImagingStudySeriesInstanceComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImagingStudyImagingStudySeriesInstanceComponent(String name, ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIntegerCore("number", element.getNumber(), false);
      composeIntegerExtras("number", element.getNumber(), false);
      composeOidCore("uid", element.getUid(), false);
      composeOidExtras("uid", element.getUid(), false);
      composeOidCore("sopclass", element.getSopclass(), false);
      composeOidExtras("sopclass", element.getSopclass(), false);
      composeStringCore("type", element.getType(), false);
      composeStringExtras("type", element.getType(), false);
      composeStringCore("title", element.getTitle(), false);
      composeStringExtras("title", element.getTitle(), false);
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      composeResourceReference("attachment", element.getAttachment());
      close();
    }
  }

  private void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeResourceReference("subject", element.getSubject());
      composeBooleanCore("refusedIndicator", element.getRefusedIndicator(), false);
      composeBooleanExtras("refusedIndicator", element.getRefusedIndicator(), false);
      composeBooleanCore("reported", element.getReported(), false);
      composeBooleanExtras("reported", element.getReported(), false);
      composeResourceReference("performer", element.getPerformer());
      composeResourceReference("requester", element.getRequester());
      composeResourceReference("manufacturer", element.getManufacturer());
      composeResourceReference("location", element.getLocation());
      composeStringCore("lotNumber", element.getLotNumber(), false);
      composeStringExtras("lotNumber", element.getLotNumber(), false);
      composeDateCore("expirationDate", element.getExpirationDate(), false);
      composeDateExtras("expirationDate", element.getExpirationDate(), false);
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeImmunizationImmunizationExplanationComponent("explanation", element.getExplanation());
      if (element.getReaction().size() > 0) {
        openArray("reaction");
        for (Immunization.ImmunizationReactionComponent e : element.getReaction()) 
          composeImmunizationImmunizationReactionComponent(null, e);
        closeArray();
      };
      if (element.getVaccinationProtocol().size() > 0) {
        openArray("vaccinationProtocol");
        for (Immunization.ImmunizationVaccinationProtocolComponent e : element.getVaccinationProtocol()) 
          composeImmunizationImmunizationVaccinationProtocolComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeImmunizationImmunizationExplanationComponent(String name, Immunization.ImmunizationExplanationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getReason().size() > 0) {
        openArray("reason");
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getRefusalReason().size() > 0) {
        openArray("refusalReason");
        for (CodeableConcept e : element.getRefusalReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImmunizationImmunizationReactionComponent(String name, Immunization.ImmunizationReactionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeResourceReference("detail", element.getDetail());
      composeBooleanCore("reported", element.getReported(), false);
      composeBooleanExtras("reported", element.getReported(), false);
      close();
    }
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIntegerCore("doseSequence", element.getDoseSequence(), false);
      composeIntegerExtras("doseSequence", element.getDoseSequence(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeResourceReference("authority", element.getAuthority());
      composeStringCore("series", element.getSeries(), false);
      composeStringExtras("series", element.getSeries(), false);
      composeIntegerCore("seriesDoses", element.getSeriesDoses(), false);
      composeIntegerExtras("seriesDoses", element.getSeriesDoses(), false);
      composeCodeableConcept("doseTarget", element.getDoseTarget());
      composeCodeableConcept("doseStatus", element.getDoseStatus());
      composeCodeableConcept("doseStatusReason", element.getDoseStatusReason());
      close();
    }
  }

  private void composeImmunizationRecommendation(String name, ImmunizationRecommendation element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      if (element.getRecommendation().size() > 0) {
        openArray("recommendation");
        for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent e : element.getRecommendation()) 
          composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeIntegerCore("doseNumber", element.getDoseNumber(), false);
      composeIntegerExtras("doseNumber", element.getDoseNumber(), false);
      composeCodeableConcept("forecastStatus", element.getForecastStatus());
      if (element.getDateCriterion().size() > 0) {
        openArray("dateCriterion");
        for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent e : element.getDateCriterion()) 
          composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(null, e);
        closeArray();
      };
      composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent("protocol", element.getProtocol());
      if (element.getSupportingImmunization().size() > 0) {
        openArray("supportingImmunization");
        for (ResourceReference e : element.getSupportingImmunization()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getSupportingPatientInformation().size() > 0) {
        openArray("supportingPatientInformation");
        for (ResourceReference e : element.getSupportingPatientInformation()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeDateTimeCore("value", element.getValue(), false);
      composeDateTimeExtras("value", element.getValue(), false);
      close();
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIntegerCore("doseSequence", element.getDoseSequence(), false);
      composeIntegerExtras("doseSequence", element.getDoseSequence(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeResourceReference("authority", element.getAuthority());
      composeStringCore("series", element.getSeries(), false);
      composeStringExtras("series", element.getSeries(), false);
      close();
    }
  }

  private void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("source", element.getSource());
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeBooleanCore("ordered", element.getOrdered(), false);
      composeBooleanExtras("ordered", element.getOrdered(), false);
      if (element.getMode() != null) {
        composeEnumerationCore("mode", element.getMode(), new List_.ListModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getMode(), new List_.ListModeEnumFactory(), false);
      }
      if (element.getEntry().size() > 0) {
        openArray("entry");
        for (List_.ListEntryComponent e : element.getEntry()) 
          composeList_ListEntryComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("emptyReason", element.getEmptyReason());
    }
  }

  private void composeList_ListEntryComponent(String name, List_.ListEntryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getFlag().size() > 0) {
        openArray("flag");
        for (CodeableConcept e : element.getFlag()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeBooleanCore("deleted", element.getDeleted(), false);
      composeBooleanExtras("deleted", element.getDeleted(), false);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeResourceReference("item", element.getItem());
      close();
    }
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeCodeableConcept("type", element.getType());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("physicalType", element.getPhysicalType());
      composeLocationLocationPositionComponent("position", element.getPosition());
      composeResourceReference("managingOrganization", element.getManagingOrganization());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Location.LocationStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Location.LocationStatusEnumFactory(), false);
      }
      composeResourceReference("partOf", element.getPartOf());
      if (element.getMode() != null) {
        composeEnumerationCore("mode", element.getMode(), new Location.LocationModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getMode(), new Location.LocationModeEnumFactory(), false);
      }
    }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeDecimalCore("longitude", element.getLongitude(), false);
      composeDecimalExtras("longitude", element.getLongitude(), false);
      composeDecimalCore("latitude", element.getLatitude(), false);
      composeDecimalExtras("latitude", element.getLatitude(), false);
      composeDecimalCore("altitude", element.getAltitude(), false);
      composeDecimalExtras("altitude", element.getAltitude(), false);
      close();
    }
  }

  private void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Media.MediaTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Media.MediaTypeEnumFactory(), false);
      }
      composeCodeableConcept("subtype", element.getSubtype());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("dateTime", element.getDateTime(), false);
      composeDateTimeExtras("dateTime", element.getDateTime(), false);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("operator", element.getOperator());
      composeCodeableConcept("view", element.getView());
      composeStringCore("deviceName", element.getDeviceName(), false);
      composeStringExtras("deviceName", element.getDeviceName(), false);
      composeIntegerCore("height", element.getHeight(), false);
      composeIntegerExtras("height", element.getHeight(), false);
      composeIntegerCore("width", element.getWidth(), false);
      composeIntegerExtras("width", element.getWidth(), false);
      composeIntegerCore("frames", element.getFrames(), false);
      composeIntegerExtras("frames", element.getFrames(), false);
      composeIntegerCore("length", element.getLength(), false);
      composeIntegerExtras("length", element.getLength(), false);
      composeAttachment("content", element.getContent());
    }
  }

  private void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeCodeableConcept("code", element.getCode());
      composeBooleanCore("isBrand", element.getIsBrand(), false);
      composeBooleanExtras("isBrand", element.getIsBrand(), false);
      composeResourceReference("manufacturer", element.getManufacturer());
      if (element.getKind() != null) {
        composeEnumerationCore("kind", element.getKind(), new Medication.MedicationKindEnumFactory(), false);
        composeEnumerationExtras("kind", element.getKind(), new Medication.MedicationKindEnumFactory(), false);
      }
      composeMedicationMedicationProductComponent("product", element.getProduct());
      composeMedicationMedicationPackageComponent("package", element.getPackage());
    }
  }

  private void composeMedicationMedicationProductComponent(String name, Medication.MedicationProductComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("form", element.getForm());
      if (element.getIngredient().size() > 0) {
        openArray("ingredient");
        for (Medication.MedicationProductIngredientComponent e : element.getIngredient()) 
          composeMedicationMedicationProductIngredientComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationMedicationProductIngredientComponent(String name, Medication.MedicationProductIngredientComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("item", element.getItem());
      composeRatio("amount", element.getAmount());
      close();
    }
  }

  private void composeMedicationMedicationPackageComponent(String name, Medication.MedicationPackageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("container", element.getContainer());
      if (element.getContent().size() > 0) {
        openArray("content");
        for (Medication.MedicationPackageContentComponent e : element.getContent()) 
          composeMedicationMedicationPackageContentComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationMedicationPackageContentComponent(String name, Medication.MedicationPackageContentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("item", element.getItem());
      composeQuantity("amount", element.getAmount());
      close();
    }
  }

  private void composeMedicationAdministration(String name, MedicationAdministration element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new MedicationAdministration.MedicationAdminStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new MedicationAdministration.MedicationAdminStatusEnumFactory(), false);
      }
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("practitioner", element.getPractitioner());
      composeResourceReference("encounter", element.getEncounter());
      composeResourceReference("prescription", element.getPrescription());
      composeBooleanCore("wasNotGiven", element.getWasNotGiven(), false);
      composeBooleanExtras("wasNotGiven", element.getWasNotGiven(), false);
      if (element.getReasonNotGiven().size() > 0) {
        openArray("reasonNotGiven");
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      if (element.getDevice().size() > 0) {
        openArray("device");
        for (ResourceReference e : element.getDevice()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDosage().size() > 0) {
        openArray("dosage");
        for (MedicationAdministration.MedicationAdministrationDosageComponent e : element.getDosage()) 
          composeMedicationAdministrationMedicationAdministrationDosageComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeMedicationAdministrationMedicationAdministrationDosageComponent(String name, MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeType("timing", element.getTiming());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMedicationDispense(String name, MedicationDispense element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
      }
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("dispenser", element.getDispenser());
      if (element.getAuthorizingPrescription().size() > 0) {
        openArray("authorizingPrescription");
        for (ResourceReference e : element.getAuthorizingPrescription()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDispense().size() > 0) {
        openArray("dispense");
        for (MedicationDispense.MedicationDispenseDispenseComponent e : element.getDispense()) 
          composeMedicationDispenseMedicationDispenseDispenseComponent(null, e);
        closeArray();
      };
      composeMedicationDispenseMedicationDispenseSubstitutionComponent("substitution", element.getSubstitution());
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseComponent(String name, MedicationDispense.MedicationDispenseDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
      }
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("medication", element.getMedication());
      composeDateTimeCore("whenPrepared", element.getWhenPrepared(), false);
      composeDateTimeExtras("whenPrepared", element.getWhenPrepared(), false);
      composeDateTimeCore("whenHandedOver", element.getWhenHandedOver(), false);
      composeDateTimeExtras("whenHandedOver", element.getWhenHandedOver(), false);
      composeResourceReference("destination", element.getDestination());
      if (element.getReceiver().size() > 0) {
        openArray("receiver");
        for (ResourceReference e : element.getReceiver()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDosage().size() > 0) {
        openArray("dosage");
        for (MedicationDispense.MedicationDispenseDispenseDosageComponent e : element.getDosage()) 
          composeMedicationDispenseMedicationDispenseDispenseDosageComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseDosageComponent(String name, MedicationDispense.MedicationDispenseDispenseDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("timing", element.getTiming());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseSubstitutionComponent(String name, MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      if (element.getReason().size() > 0) {
        openArray("reason");
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getResponsibleParty().size() > 0) {
        openArray("responsibleParty");
        for (ResourceReference e : element.getResponsibleParty()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationPrescription(String name, MedicationPrescription element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("dateWritten", element.getDateWritten(), false);
      composeDateTimeExtras("dateWritten", element.getDateWritten(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory(), false);
      }
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("prescriber", element.getPrescriber());
      composeResourceReference("encounter", element.getEncounter());
      composeType("reason", element.getReason());
      composeResourceReference("medication", element.getMedication());
      if (element.getDosageInstruction().size() > 0) {
        openArray("dosageInstruction");
        for (MedicationPrescription.MedicationPrescriptionDosageInstructionComponent e : element.getDosageInstruction()) 
          composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(null, e);
        closeArray();
      };
      composeMedicationPrescriptionMedicationPrescriptionDispenseComponent("dispense", element.getDispense());
      composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent("substitution", element.getSubstitution());
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(String name, MedicationPrescription.MedicationPrescriptionDosageInstructionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("timing", element.getTiming());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDispenseComponent(String name, MedicationPrescription.MedicationPrescriptionDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("medication", element.getMedication());
      composePeriod("validityPeriod", element.getValidityPeriod());
      composeIntegerCore("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowed(), false);
      composeIntegerExtras("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowed(), false);
      composeQuantity("quantity", element.getQuantity());
      composeDuration("expectedSupplyDuration", element.getExpectedSupplyDuration());
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(String name, MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      close();
    }
  }

  private void composeMedicationStatement(String name, MedicationStatement element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("patient", element.getPatient());
      composeBooleanCore("wasNotGiven", element.getWasNotGiven(), false);
      composeBooleanExtras("wasNotGiven", element.getWasNotGiven(), false);
      if (element.getReasonNotGiven().size() > 0) {
        openArray("reasonNotGiven");
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      if (element.getDevice().size() > 0) {
        openArray("device");
        for (ResourceReference e : element.getDevice()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDosage().size() > 0) {
        openArray("dosage");
        for (MedicationStatement.MedicationStatementDosageComponent e : element.getDosage()) 
          composeMedicationStatementMedicationStatementDosageComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeMedicationStatementMedicationStatementDosageComponent(String name, MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeSchedule("timing", element.getTiming());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMessageHeader(String name, MessageHeader element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdCore("identifier", element.getIdentifier(), false);
      composeIdExtras("identifier", element.getIdentifier(), false);
      composeInstantCore("timestamp", element.getTimestamp(), false);
      composeInstantExtras("timestamp", element.getTimestamp(), false);
      composeCoding("event", element.getEvent());
      composeMessageHeaderMessageHeaderResponseComponent("response", element.getResponse());
      composeMessageHeaderMessageSourceComponent("source", element.getSource());
      if (element.getDestination().size() > 0) {
        openArray("destination");
        for (MessageHeader.MessageDestinationComponent e : element.getDestination()) 
          composeMessageHeaderMessageDestinationComponent(null, e);
        closeArray();
      };
      composeResourceReference("enterer", element.getEnterer());
      composeResourceReference("author", element.getAuthor());
      composeResourceReference("receiver", element.getReceiver());
      composeResourceReference("responsible", element.getResponsible());
      composeCodeableConcept("reason", element.getReason());
      if (element.getData().size() > 0) {
        openArray("data");
        for (ResourceReference e : element.getData()) 
          composeResourceReference(null, e);
        closeArray();
      };
    }
  }

  private void composeMessageHeaderMessageHeaderResponseComponent(String name, MessageHeader.MessageHeaderResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("identifier", element.getIdentifier(), false);
      composeIdExtras("identifier", element.getIdentifier(), false);
      if (element.getCode() != null) {
        composeEnumerationCore("code", element.getCode(), new MessageHeader.ResponseCodeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCode(), new MessageHeader.ResponseCodeEnumFactory(), false);
      }
      composeResourceReference("details", element.getDetails());
      close();
    }
  }

  private void composeMessageHeaderMessageSourceComponent(String name, MessageHeader.MessageSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("software", element.getSoftware(), false);
      composeStringExtras("software", element.getSoftware(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeContact("contact", element.getContact());
      composeUriCore("endpoint", element.getEndpoint(), false);
      composeUriExtras("endpoint", element.getEndpoint(), false);
      close();
    }
  }

  private void composeMessageHeaderMessageDestinationComponent(String name, MessageHeader.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeResourceReference("target", element.getTarget());
      composeUriCore("endpoint", element.getEndpoint(), false);
      composeUriExtras("endpoint", element.getEndpoint(), false);
      close();
    }
  }

  private void composeNamespace(String name, Namespace element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Namespace.NamespaceTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Namespace.NamespaceTypeEnumFactory(), false);
      }
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Namespace.NamespaceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Namespace.NamespaceStatusEnumFactory(), false);
      }
      composeCodeCore("country", element.getCountry(), false);
      composeCodeExtras("country", element.getCountry(), false);
      composeCodeableConcept("category", element.getCategory());
      composeStringCore("responsible", element.getResponsible(), false);
      composeStringExtras("responsible", element.getResponsible(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeStringCore("usage", element.getUsage(), false);
      composeStringExtras("usage", element.getUsage(), false);
      if (element.getUniqueId().size() > 0) {
        openArray("uniqueId");
        for (Namespace.NamespaceUniqueIdComponent e : element.getUniqueId()) 
          composeNamespaceNamespaceUniqueIdComponent(null, e);
        closeArray();
      };
      composeNamespaceNamespaceContactComponent("contact", element.getContact());
      composeResourceReference("replacedBy", element.getReplacedBy());
    }
  }

  private void composeNamespaceNamespaceUniqueIdComponent(String name, Namespace.NamespaceUniqueIdComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Namespace.NamespaceIdentifierTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Namespace.NamespaceIdentifierTypeEnumFactory(), false);
      }
      composeStringCore("value", element.getValue(), false);
      composeStringExtras("value", element.getValue(), false);
      composeBooleanCore("preferred", element.getPreferred(), false);
      composeBooleanExtras("preferred", element.getPreferred(), false);
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeNamespaceNamespaceContactComponent(String name, Namespace.NamespaceContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeObservation(String name, Observation element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      composeCodeableConcept("interpretation", element.getInterpretation());
      composeStringCore("comments", element.getComments(), false);
      composeStringExtras("comments", element.getComments(), false);
      composeType("applies", element.getApplies());
      composeInstantCore("issued", element.getIssued(), false);
      composeInstantExtras("issued", element.getIssued(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Observation.ObservationStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Observation.ObservationStatusEnumFactory(), false);
      }
      if (element.getReliability() != null) {
        composeEnumerationCore("reliability", element.getReliability(), new Observation.ObservationReliabilityEnumFactory(), false);
        composeEnumerationExtras("reliability", element.getReliability(), new Observation.ObservationReliabilityEnumFactory(), false);
      }
      composeCodeableConcept("bodySite", element.getBodySite());
      composeCodeableConcept("method", element.getMethod());
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("specimen", element.getSpecimen());
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (ResourceReference e : element.getPerformer()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getReferenceRange().size() > 0) {
        openArray("referenceRange");
        for (Observation.ObservationReferenceRangeComponent e : element.getReferenceRange()) 
          composeObservationObservationReferenceRangeComponent(null, e);
        closeArray();
      };
      if (element.getRelated().size() > 0) {
        openArray("related");
        for (Observation.ObservationRelatedComponent e : element.getRelated()) 
          composeObservationObservationRelatedComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeObservationObservationReferenceRangeComponent(String name, Observation.ObservationReferenceRangeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
      composeCodeableConcept("meaning", element.getMeaning());
      composeRange("age", element.getAge());
      close();
    }
  }

  private void composeObservationObservationRelatedComponent(String name, Observation.ObservationRelatedComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Observation.ObservationRelationshiptypesEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Observation.ObservationRelationshiptypesEnumFactory(), false);
      }
      composeResourceReference("target", element.getTarget());
      close();
    }
  }

  private void composeOperationOutcome(String name, OperationOutcome element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIssue().size() > 0) {
        openArray("issue");
        for (OperationOutcome.OperationOutcomeIssueComponent e : element.getIssue()) 
          composeOperationOutcomeOperationOutcomeIssueComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeOperationOutcomeOperationOutcomeIssueComponent(String name, OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getSeverity() != null) {
        composeEnumerationCore("severity", element.getSeverity(), new OperationOutcome.IssueSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverity(), new OperationOutcome.IssueSeverityEnumFactory(), false);
      }
      composeCoding("type", element.getType());
      composeStringCore("details", element.getDetails(), false);
      composeStringExtras("details", element.getDetails(), false);
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (String_ e : element.getLocation()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getLocation())) {
          openArray("_location");
          for (String_ e : element.getLocation()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      close();
    }
  }

  private void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("source", element.getSource());
      composeResourceReference("target", element.getTarget());
      composeType("reason", element.getReason());
      composeResourceReference("authority", element.getAuthority());
      composeOrderOrderWhenComponent("when", element.getWhen());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ResourceReference e : element.getDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
    }
  }

  private void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeSchedule("schedule", element.getSchedule());
      close();
    }
  }

  private void composeOrderResponse(String name, OrderResponse element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("request", element.getRequest());
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeResourceReference("who", element.getWho());
      composeType("authority", element.getAuthority());
      if (element.getCode() != null) {
        composeEnumerationCore("code", element.getCode(), new OrderResponse.OrderOutcomeCodeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCode(), new OrderResponse.OrderOutcomeCodeEnumFactory(), false);
      }
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getFulfillment().size() > 0) {
        openArray("fulfillment");
        for (ResourceReference e : element.getFulfillment()) 
          composeResourceReference(null, e);
        closeArray();
      };
    }
  }

  private void composeOrganization(String name, Organization element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeCodeableConcept("type", element.getType());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      if (element.getAddress().size() > 0) {
        openArray("address");
        for (Address e : element.getAddress()) 
          composeAddress(null, e);
        closeArray();
      };
      composeResourceReference("partOf", element.getPartOf());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Organization.OrganizationContactComponent e : element.getContact()) 
          composeOrganizationOrganizationContactComponent(null, e);
        closeArray();
      };
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (ResourceReference e : element.getLocation()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeBooleanCore("active", element.getActive(), false);
      composeBooleanExtras("active", element.getActive(), false);
    }
  }

  private void composeOrganizationOrganizationContactComponent(String name, Organization.OrganizationContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("purpose", element.getPurpose());
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("gender", element.getGender());
      close();
    }
  }

  private void composeOther(String name, Other element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeDateCore("created", element.getCreated(), false);
      composeDateExtras("created", element.getCreated(), false);
    }
  }

  private void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getName().size() > 0) {
        openArray("name");
        for (HumanName e : element.getName()) 
          composeHumanName(null, e);
        closeArray();
      };
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeCodeableConcept("gender", element.getGender());
      composeDateTimeCore("birthDate", element.getBirthDate(), false);
      composeDateTimeExtras("birthDate", element.getBirthDate(), false);
      composeType("deceased", element.getDeceased());
      if (element.getAddress().size() > 0) {
        openArray("address");
        for (Address e : element.getAddress()) 
          composeAddress(null, e);
        closeArray();
      };
      composeCodeableConcept("maritalStatus", element.getMaritalStatus());
      composeType("multipleBirth", element.getMultipleBirth());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Patient.ContactComponent e : element.getContact()) 
          composePatientContactComponent(null, e);
        closeArray();
      };
      composePatientAnimalComponent("animal", element.getAnimal());
      if (element.getCommunication().size() > 0) {
        openArray("communication");
        for (CodeableConcept e : element.getCommunication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getCareProvider().size() > 0) {
        openArray("careProvider");
        for (ResourceReference e : element.getCareProvider()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeResourceReference("managingOrganization", element.getManagingOrganization());
      if (element.getLink().size() > 0) {
        openArray("link");
        for (Patient.PatientLinkComponent e : element.getLink()) 
          composePatientPatientLinkComponent(null, e);
        closeArray();
      };
      composeBooleanCore("active", element.getActive(), false);
      composeBooleanExtras("active", element.getActive(), false);
    }
  }

  private void composePatientContactComponent(String name, Patient.ContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getRelationship().size() > 0) {
        openArray("relationship");
        for (CodeableConcept e : element.getRelationship()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("gender", element.getGender());
      composeResourceReference("organization", element.getOrganization());
      close();
    }
  }

  private void composePatientAnimalComponent(String name, Patient.AnimalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("species", element.getSpecies());
      composeCodeableConcept("breed", element.getBreed());
      composeCodeableConcept("genderStatus", element.getGenderStatus());
      close();
    }
  }

  private void composePatientPatientLinkComponent(String name, Patient.PatientLinkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("other", element.getOther());
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Patient.LinkTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Patient.LinkTypeEnumFactory(), false);
      }
      close();
    }
  }

  private void composePractitioner(String name, Practitioner element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("gender", element.getGender());
      composeDateTimeCore("birthDate", element.getBirthDate(), false);
      composeDateTimeExtras("birthDate", element.getBirthDate(), false);
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
      composeResourceReference("organization", element.getOrganization());
      if (element.getRole().size() > 0) {
        openArray("role");
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getSpecialty().size() > 0) {
        openArray("specialty");
        for (CodeableConcept e : element.getSpecialty()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (ResourceReference e : element.getLocation()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getQualification().size() > 0) {
        openArray("qualification");
        for (Practitioner.PractitionerQualificationComponent e : element.getQualification()) 
          composePractitionerPractitionerQualificationComponent(null, e);
        closeArray();
      };
      if (element.getCommunication().size() > 0) {
        openArray("communication");
        for (CodeableConcept e : element.getCommunication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
    }
  }

  private void composePractitionerPractitionerQualificationComponent(String name, Practitioner.PractitionerQualificationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composePeriod("period", element.getPeriod());
      composeResourceReference("issuer", element.getIssuer());
      close();
    }
  }

  private void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      if (element.getBodySite().size() > 0) {
        openArray("bodySite");
        for (CodeableConcept e : element.getBodySite()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getIndication().size() > 0) {
        openArray("indication");
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (Procedure.ProcedurePerformerComponent e : element.getPerformer()) 
          composeProcedureProcedurePerformerComponent(null, e);
        closeArray();
      };
      composePeriod("date", element.getDate());
      composeResourceReference("encounter", element.getEncounter());
      composeStringCore("outcome", element.getOutcome(), false);
      composeStringExtras("outcome", element.getOutcome(), false);
      if (element.getReport().size() > 0) {
        openArray("report");
        for (ResourceReference e : element.getReport()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getComplication().size() > 0) {
        openArray("complication");
        for (CodeableConcept e : element.getComplication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("followUp", element.getFollowUp(), false);
      composeStringExtras("followUp", element.getFollowUp(), false);
      if (element.getRelatedItem().size() > 0) {
        openArray("relatedItem");
        for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
          composeProcedureProcedureRelatedItemComponent(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotes(), false);
      composeStringExtras("notes", element.getNotes(), false);
    }
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
      close();
    }
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Procedure.ProcedureRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Procedure.ProcedureRelationshipTypeEnumFactory(), false);
      }
      composeResourceReference("target", element.getTarget());
      close();
    }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("publisher", element.getPublisher(), false);
      composeStringExtras("publisher", element.getPublisher(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Profile.ResourceProfileStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Profile.ResourceProfileStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimental(), false);
      composeBooleanExtras("experimental", element.getExperimental(), false);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeStringCore("requirements", element.getRequirements(), false);
      composeStringExtras("requirements", element.getRequirements(), false);
      composeIdCore("fhirVersion", element.getFhirVersion(), false);
      composeIdExtras("fhirVersion", element.getFhirVersion(), false);
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (Profile.ProfileMappingComponent e : element.getMapping()) 
          composeProfileProfileMappingComponent(null, e);
        closeArray();
      };
      if (element.getStructure().size() > 0) {
        openArray("structure");
        for (Profile.ProfileStructureComponent e : element.getStructure()) 
          composeProfileProfileStructureComponent(null, e);
        closeArray();
      };
      if (element.getExtensionDefn().size() > 0) {
        openArray("extensionDefn");
        for (Profile.ProfileExtensionDefnComponent e : element.getExtensionDefn()) 
          composeProfileProfileExtensionDefnComponent(null, e);
        closeArray();
      };
      if (element.getQuery().size() > 0) {
        openArray("query");
        for (Profile.ProfileQueryComponent e : element.getQuery()) 
          composeProfileProfileQueryComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeProfileProfileMappingComponent(String name, Profile.ProfileMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("identity", element.getIdentity(), false);
      composeIdExtras("identity", element.getIdentity(), false);
      composeUriCore("uri", element.getUri(), false);
      composeUriExtras("uri", element.getUri(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("comments", element.getComments(), false);
      composeStringExtras("comments", element.getComments(), false);
      close();
    }
  }

  private void composeProfileProfileStructureComponent(String name, Profile.ProfileStructureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("type", element.getType(), false);
      composeCodeExtras("type", element.getType(), false);
      composeUriCore("base", element.getBase(), false);
      composeUriExtras("base", element.getBase(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeBooleanCore("publish", element.getPublish(), false);
      composeBooleanExtras("publish", element.getPublish(), false);
      composeStringCore("purpose", element.getPurpose(), false);
      composeStringExtras("purpose", element.getPurpose(), false);
      composeProfileConstraintComponent("snapshot", element.getSnapshot());
      composeProfileConstraintComponent("differential", element.getDifferential());
      if (element.getSearchParam().size() > 0) {
        openArray("searchParam");
        for (Profile.ProfileStructureSearchParamComponent e : element.getSearchParam()) 
          composeProfileProfileStructureSearchParamComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileConstraintComponent(String name, Profile.ConstraintComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getElement().size() > 0) {
        openArray("element");
        for (Profile.ElementComponent e : element.getElement()) 
          composeProfileElementComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileElementComponent(String name, Profile.ElementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("path", element.getPath(), false);
      composeStringExtras("path", element.getPath(), false);
      if (element.getRepresentation().size() > 0) {
        openArray("representation");
        for (Enumeration<Profile.PropertyRepresentation> e : element.getRepresentation()) 
          composeEnumerationCore(null, e, new Profile.PropertyRepresentationEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getRepresentation())) {
          openArray("_representation");
          for (Enumeration<Profile.PropertyRepresentation> e : element.getRepresentation()) 
            composeEnumerationExtras(null, e, new Profile.PropertyRepresentationEnumFactory(), true);
          closeArray();
        }
      };
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeProfileElementSlicingComponent("slicing", element.getSlicing());
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      close();
    }
  }

  private void composeProfileElementSlicingComponent(String name, Profile.ElementSlicingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("discriminator", element.getDiscriminator(), false);
      composeIdExtras("discriminator", element.getDiscriminator(), false);
      composeBooleanCore("ordered", element.getOrdered(), false);
      composeBooleanExtras("ordered", element.getOrdered(), false);
      if (element.getRules() != null) {
        composeEnumerationCore("rules", element.getRules(), new Profile.ResourceSlicingRulesEnumFactory(), false);
        composeEnumerationExtras("rules", element.getRules(), new Profile.ResourceSlicingRulesEnumFactory(), false);
      }
      close();
    }
  }

  private void composeProfileElementDefinitionComponent(String name, Profile.ElementDefinitionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("short", element.getShort(), false);
      composeStringExtras("short", element.getShort(), false);
      composeStringCore("formal", element.getFormal(), false);
      composeStringExtras("formal", element.getFormal(), false);
      composeStringCore("comments", element.getComments(), false);
      composeStringExtras("comments", element.getComments(), false);
      composeStringCore("requirements", element.getRequirements(), false);
      composeStringExtras("requirements", element.getRequirements(), false);
      if (element.getSynonym().size() > 0) {
        openArray("synonym");
        for (String_ e : element.getSynonym()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSynonym())) {
          openArray("_synonym");
          for (String_ e : element.getSynonym()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeIntegerCore("min", element.getMin(), false);
      composeIntegerExtras("min", element.getMin(), false);
      composeStringCore("max", element.getMax(), false);
      composeStringExtras("max", element.getMax(), false);
      if (element.getType().size() > 0) {
        openArray("type");
        for (Profile.TypeRefComponent e : element.getType()) 
          composeProfileTypeRefComponent(null, e);
        closeArray();
      };
      composeStringCore("nameReference", element.getNameReference(), false);
      composeStringExtras("nameReference", element.getNameReference(), false);
      composeType("value", element.getValue());
      composeType("example", element.getExample());
      composeIntegerCore("maxLength", element.getMaxLength(), false);
      composeIntegerExtras("maxLength", element.getMaxLength(), false);
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (Id e : element.getCondition()) 
          composeIdCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getCondition())) {
          openArray("_condition");
          for (Id e : element.getCondition()) 
            composeIdExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getConstraint().size() > 0) {
        openArray("constraint");
        for (Profile.ElementDefinitionConstraintComponent e : element.getConstraint()) 
          composeProfileElementDefinitionConstraintComponent(null, e);
        closeArray();
      };
      composeBooleanCore("mustSupport", element.getMustSupport(), false);
      composeBooleanExtras("mustSupport", element.getMustSupport(), false);
      composeBooleanCore("isModifier", element.getIsModifier(), false);
      composeBooleanExtras("isModifier", element.getIsModifier(), false);
      composeProfileElementDefinitionBindingComponent("binding", element.getBinding());
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (Profile.ElementDefinitionMappingComponent e : element.getMapping()) 
          composeProfileElementDefinitionMappingComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileTypeRefComponent(String name, Profile.TypeRefComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      composeUriCore("profile", element.getProfile(), false);
      composeUriExtras("profile", element.getProfile(), false);
      if (element.getAggregation().size() > 0) {
        openArray("aggregation");
        for (Enumeration<Profile.ResourceAggregationMode> e : element.getAggregation()) 
          composeEnumerationCore(null, e, new Profile.ResourceAggregationModeEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getAggregation())) {
          openArray("_aggregation");
          for (Enumeration<Profile.ResourceAggregationMode> e : element.getAggregation()) 
            composeEnumerationExtras(null, e, new Profile.ResourceAggregationModeEnumFactory(), true);
          closeArray();
        }
      };
      close();
    }
  }

  private void composeProfileElementDefinitionConstraintComponent(String name, Profile.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("key", element.getKey(), false);
      composeIdExtras("key", element.getKey(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      if (element.getSeverity() != null) {
        composeEnumerationCore("severity", element.getSeverity(), new Profile.ConstraintSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverity(), new Profile.ConstraintSeverityEnumFactory(), false);
      }
      composeStringCore("human", element.getHuman(), false);
      composeStringExtras("human", element.getHuman(), false);
      composeStringCore("xpath", element.getXpath(), false);
      composeStringExtras("xpath", element.getXpath(), false);
      close();
    }
  }

  private void composeProfileElementDefinitionBindingComponent(String name, Profile.ElementDefinitionBindingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeBooleanCore("isExtensible", element.getIsExtensible(), false);
      composeBooleanExtras("isExtensible", element.getIsExtensible(), false);
      if (element.getConformance() != null) {
        composeEnumerationCore("conformance", element.getConformance(), new Profile.BindingConformanceEnumFactory(), false);
        composeEnumerationExtras("conformance", element.getConformance(), new Profile.BindingConformanceEnumFactory(), false);
      }
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeType("reference", element.getReference());
      close();
    }
  }

  private void composeProfileElementDefinitionMappingComponent(String name, Profile.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("identity", element.getIdentity(), false);
      composeIdExtras("identity", element.getIdentity(), false);
      composeStringCore("map", element.getMap(), false);
      composeStringExtras("map", element.getMap(), false);
      close();
    }
  }

  private void composeProfileProfileStructureSearchParamComponent(String name, Profile.ProfileStructureSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Profile.SearchParamTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Profile.SearchParamTypeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      composeStringCore("xpath", element.getXpath(), false);
      composeStringExtras("xpath", element.getXpath(), false);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Code e : element.getTarget()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getTarget())) {
          openArray("_target");
          for (Code e : element.getTarget()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      close();
    }
  }

  private void composeProfileProfileExtensionDefnComponent(String name, Profile.ProfileExtensionDefnComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      if (element.getContextType() != null) {
        composeEnumerationCore("contextType", element.getContextType(), new Profile.ExtensionContextEnumFactory(), false);
        composeEnumerationExtras("contextType", element.getContextType(), new Profile.ExtensionContextEnumFactory(), false);
      }
      if (element.getContext().size() > 0) {
        openArray("context");
        for (String_ e : element.getContext()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getContext())) {
          openArray("_context");
          for (String_ e : element.getContext()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getElement().size() > 0) {
        openArray("element");
        for (Profile.ElementComponent e : element.getElement()) 
          composeProfileElementComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileProfileQueryComponent(String name, Profile.ProfileQueryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("documentation", element.getDocumentation(), false);
      composeStringExtras("documentation", element.getDocumentation(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Profile.ProfileStructureSearchParamComponent e : element.getParameter()) 
          composeProfileProfileStructureSearchParamComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (ResourceReference e : element.getTarget()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeInstantCore("recorded", element.getRecorded(), false);
      composeInstantExtras("recorded", element.getRecorded(), false);
      composeCodeableConcept("reason", element.getReason());
      composeResourceReference("location", element.getLocation());
      if (element.getPolicy().size() > 0) {
        openArray("policy");
        for (Uri e : element.getPolicy()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getPolicy())) {
          openArray("_policy");
          for (Uri e : element.getPolicy()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getAgent().size() > 0) {
        openArray("agent");
        for (Provenance.ProvenanceAgentComponent e : element.getAgent()) 
          composeProvenanceProvenanceAgentComponent(null, e);
        closeArray();
      };
      if (element.getEntity().size() > 0) {
        openArray("entity");
        for (Provenance.ProvenanceEntityComponent e : element.getEntity()) 
          composeProvenanceProvenanceEntityComponent(null, e);
        closeArray();
      };
      composeStringCore("integritySignature", element.getIntegritySignature(), false);
      composeStringExtras("integritySignature", element.getIntegritySignature(), false);
    }
  }

  private void composeProvenanceProvenanceAgentComponent(String name, Provenance.ProvenanceAgentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCoding("role", element.getRole());
      composeCoding("type", element.getType());
      composeUriCore("reference", element.getReference(), false);
      composeUriExtras("reference", element.getReference(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      close();
    }
  }

  private void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getRole() != null) {
        composeEnumerationCore("role", element.getRole(), new Provenance.ProvenanceEntityRoleEnumFactory(), false);
        composeEnumerationExtras("role", element.getRole(), new Provenance.ProvenanceEntityRoleEnumFactory(), false);
      }
      composeCoding("type", element.getType());
      composeUriCore("reference", element.getReference(), false);
      composeUriExtras("reference", element.getReference(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
      close();
    }
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeUriCore("identifier", element.getIdentifier(), false);
      composeUriExtras("identifier", element.getIdentifier(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Extension e : element.getParameter()) 
          composeExtension(null, e);
        closeArray();
      };
      composeQueryQueryResponseComponent("response", element.getResponse());
    }
  }

  private void composeQueryQueryResponseComponent(String name, Query.QueryResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("identifier", element.getIdentifier(), false);
      composeUriExtras("identifier", element.getIdentifier(), false);
      if (element.getOutcome() != null) {
        composeEnumerationCore("outcome", element.getOutcome(), new Query.QueryOutcomeEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcome(), new Query.QueryOutcomeEnumFactory(), false);
      }
      composeIntegerCore("total", element.getTotal(), false);
      composeIntegerExtras("total", element.getTotal(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Extension e : element.getParameter()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getFirst().size() > 0) {
        openArray("first");
        for (Extension e : element.getFirst()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getPrevious().size() > 0) {
        openArray("previous");
        for (Extension e : element.getPrevious()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getNext().size() > 0) {
        openArray("next");
        for (Extension e : element.getNext()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getLast().size() > 0) {
        openArray("last");
        for (Extension e : element.getLast()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getReference().size() > 0) {
        openArray("reference");
        for (ResourceReference e : element.getReference()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Questionnaire.QuestionnaireStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Questionnaire.QuestionnaireStatusEnumFactory(), false);
      }
      composeDateCore("date", element.getDate(), false);
      composeDateExtras("date", element.getDate(), false);
      composeStringCore("publisher", element.getPublisher(), false);
      composeStringExtras("publisher", element.getPublisher(), false);
      composeQuestionnaireGroupComponent("group", element.getGroup());
    }
  }

  private void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkId(), false);
      composeStringExtras("linkId", element.getLinkId(), false);
      composeStringCore("title", element.getTitle(), false);
      composeStringExtras("title", element.getTitle(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (Coding e : element.getConcept()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      composeBooleanCore("required", element.getRequired(), false);
      composeBooleanExtras("required", element.getRequired(), false);
      composeBooleanCore("repeats", element.getRepeats(), false);
      composeBooleanExtras("repeats", element.getRepeats(), false);
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent(null, e);
        closeArray();
      };
      if (element.getQuestion().size() > 0) {
        openArray("question");
        for (Questionnaire.QuestionComponent e : element.getQuestion()) 
          composeQuestionnaireQuestionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaireQuestionComponent(String name, Questionnaire.QuestionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkId(), false);
      composeStringExtras("linkId", element.getLinkId(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (Coding e : element.getConcept()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Questionnaire.AnswerFormatEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Questionnaire.AnswerFormatEnumFactory(), false);
      }
      composeBooleanCore("required", element.getRequired(), false);
      composeBooleanExtras("required", element.getRequired(), false);
      composeBooleanCore("repeats", element.getRepeats(), false);
      composeBooleanExtras("repeats", element.getRepeats(), false);
      composeResourceReference("options", element.getOptions());
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaireAnswers(String name, QuestionnaireAnswers element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeResourceReference("questionnaire", element.getQuestionnaire());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory(), false);
      }
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeDateTimeCore("authored", element.getAuthored(), false);
      composeDateTimeExtras("authored", element.getAuthored(), false);
      composeResourceReference("source", element.getSource());
      composeResourceReference("encounter", element.getEncounter());
      composeQuestionnaireAnswersGroupComponent("group", element.getGroup());
    }
  }

  private void composeQuestionnaireAnswersGroupComponent(String name, QuestionnaireAnswers.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkId(), false);
      composeStringExtras("linkId", element.getLinkId(), false);
      composeStringCore("title", element.getTitle(), false);
      composeStringExtras("title", element.getTitle(), false);
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      composeResourceReference("subject", element.getSubject());
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (QuestionnaireAnswers.GroupComponent e : element.getGroup()) 
          composeQuestionnaireAnswersGroupComponent(null, e);
        closeArray();
      };
      if (element.getQuestion().size() > 0) {
        openArray("question");
        for (QuestionnaireAnswers.QuestionComponent e : element.getQuestion()) 
          composeQuestionnaireAnswersQuestionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaireAnswersQuestionComponent(String name, QuestionnaireAnswers.QuestionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkId(), false);
      composeStringExtras("linkId", element.getLinkId(), false);
      composeStringCore("text", element.getText(), false);
      composeStringExtras("text", element.getText(), false);
      if (element.getAnswer().size() > 0) {
        openArray("answer");
        for (QuestionnaireAnswers.QuestionAnswerComponent e : element.getAnswer()) 
          composeQuestionnaireAnswersQuestionAnswerComponent(null, e);
        closeArray();
      };
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (QuestionnaireAnswers.GroupComponent e : element.getGroup()) 
          composeQuestionnaireAnswersGroupComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaireAnswersQuestionAnswerComponent(String name, QuestionnaireAnswers.QuestionAnswerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeType("value", element.getValue());
      close();
    }
  }

  private void composeRelatedPerson(String name, RelatedPerson element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("patient", element.getPatient());
      composeCodeableConcept("relationship", element.getRelationship());
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeCodeableConcept("gender", element.getGender());
      composeAddress("address", element.getAddress());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
    }
  }

  private void composeSecurityEvent(String name, SecurityEvent element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeSecurityEventSecurityEventEventComponent("event", element.getEvent());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (SecurityEvent.SecurityEventParticipantComponent e : element.getParticipant()) 
          composeSecurityEventSecurityEventParticipantComponent(null, e);
        closeArray();
      };
      composeSecurityEventSecurityEventSourceComponent("source", element.getSource());
      if (element.getObject().size() > 0) {
        openArray("object");
        for (SecurityEvent.SecurityEventObjectComponent e : element.getObject()) 
          composeSecurityEventSecurityEventObjectComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeSecurityEventSecurityEventEventComponent(String name, SecurityEvent.SecurityEventEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      if (element.getSubtype().size() > 0) {
        openArray("subtype");
        for (CodeableConcept e : element.getSubtype()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getAction() != null) {
        composeEnumerationCore("action", element.getAction(), new SecurityEvent.SecurityEventActionEnumFactory(), false);
        composeEnumerationExtras("action", element.getAction(), new SecurityEvent.SecurityEventActionEnumFactory(), false);
      }
      composeInstantCore("dateTime", element.getDateTime(), false);
      composeInstantExtras("dateTime", element.getDateTime(), false);
      if (element.getOutcome() != null) {
        composeEnumerationCore("outcome", element.getOutcome(), new SecurityEvent.SecurityEventOutcomeEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcome(), new SecurityEvent.SecurityEventOutcomeEnumFactory(), false);
      }
      composeStringCore("outcomeDesc", element.getOutcomeDesc(), false);
      composeStringExtras("outcomeDesc", element.getOutcomeDesc(), false);
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantComponent(String name, SecurityEvent.SecurityEventParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getRole().size() > 0) {
        openArray("role");
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("reference", element.getReference());
      composeStringCore("userId", element.getUserId(), false);
      composeStringExtras("userId", element.getUserId(), false);
      composeStringCore("altId", element.getAltId(), false);
      composeStringExtras("altId", element.getAltId(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeBooleanCore("requestor", element.getRequestor(), false);
      composeBooleanExtras("requestor", element.getRequestor(), false);
      composeCoding("media", element.getMedia());
      composeSecurityEventSecurityEventParticipantNetworkComponent("network", element.getNetwork());
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponent(String name, SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("identifier", element.getIdentifier(), false);
      composeStringExtras("identifier", element.getIdentifier(), false);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new SecurityEvent.NetworkTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new SecurityEvent.NetworkTypeEnumFactory(), false);
      }
      close();
    }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("site", element.getSite(), false);
      composeStringExtras("site", element.getSite(), false);
      composeStringCore("identifier", element.getIdentifier(), false);
      composeStringExtras("identifier", element.getIdentifier(), false);
      if (element.getType().size() > 0) {
        openArray("type");
        for (Coding e : element.getType()) 
          composeCoding(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSecurityEventSecurityEventObjectComponent(String name, SecurityEvent.SecurityEventObjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("reference", element.getReference());
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new SecurityEvent.ObjectTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new SecurityEvent.ObjectTypeEnumFactory(), false);
      }
      if (element.getRole() != null) {
        composeEnumerationCore("role", element.getRole(), new SecurityEvent.ObjectRoleEnumFactory(), false);
        composeEnumerationExtras("role", element.getRole(), new SecurityEvent.ObjectRoleEnumFactory(), false);
      }
      if (element.getLifecycle() != null) {
        composeEnumerationCore("lifecycle", element.getLifecycle(), new SecurityEvent.ObjectLifecycleEnumFactory(), false);
        composeEnumerationExtras("lifecycle", element.getLifecycle(), new SecurityEvent.ObjectLifecycleEnumFactory(), false);
      }
      composeCodeableConcept("sensitivity", element.getSensitivity());
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeBase64BinaryCore("query", element.getQuery(), false);
      composeBase64BinaryExtras("query", element.getQuery(), false);
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (SecurityEvent.SecurityEventObjectDetailComponent e : element.getDetail()) 
          composeSecurityEventSecurityEventObjectDetailComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSecurityEventSecurityEventObjectDetailComponent(String name, SecurityEvent.SecurityEventObjectDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("type", element.getType(), false);
      composeStringExtras("type", element.getType(), false);
      composeBase64BinaryCore("value", element.getValue(), false);
      composeBase64BinaryExtras("value", element.getValue(), false);
      close();
    }
  }

  private void composeSlot(String name, Slot element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      composeResourceReference("availability", element.getAvailability());
      if (element.getFreeBusyType() != null) {
        composeEnumerationCore("freeBusyType", element.getFreeBusyType(), new Slot.SlotstatusEnumFactory(), false);
        composeEnumerationExtras("freeBusyType", element.getFreeBusyType(), new Slot.SlotstatusEnumFactory(), false);
      }
      composeInstantCore("start", element.getStart(), false);
      composeInstantExtras("start", element.getStart(), false);
      composeInstantCore("end", element.getEnd(), false);
      composeInstantExtras("end", element.getEnd(), false);
      composeBooleanCore("overbooked", element.getOverbooked(), false);
      composeBooleanExtras("overbooked", element.getOverbooked(), false);
      composeStringCore("comment", element.getComment(), false);
      composeStringExtras("comment", element.getComment(), false);
      composeDateTimeCore("lastModified", element.getLastModified(), false);
      composeDateTimeExtras("lastModified", element.getLastModified(), false);
    }
  }

  private void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      if (element.getSource().size() > 0) {
        openArray("source");
        for (Specimen.SpecimenSourceComponent e : element.getSource()) 
          composeSpecimenSpecimenSourceComponent(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      composeIdentifier("accessionIdentifier", element.getAccessionIdentifier());
      composeDateTimeCore("receivedTime", element.getReceivedTime(), false);
      composeDateTimeExtras("receivedTime", element.getReceivedTime(), false);
      composeSpecimenSpecimenCollectionComponent("collection", element.getCollection());
      if (element.getTreatment().size() > 0) {
        openArray("treatment");
        for (Specimen.SpecimenTreatmentComponent e : element.getTreatment()) 
          composeSpecimenSpecimenTreatmentComponent(null, e);
        closeArray();
      };
      if (element.getContainer().size() > 0) {
        openArray("container");
        for (Specimen.SpecimenContainerComponent e : element.getContainer()) 
          composeSpecimenSpecimenContainerComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeSpecimenSpecimenSourceComponent(String name, Specimen.SpecimenSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getRelationship() != null) {
        composeEnumerationCore("relationship", element.getRelationship(), new Specimen.HierarchicalRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("relationship", element.getRelationship(), new Specimen.HierarchicalRelationshipTypeEnumFactory(), false);
      }
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (ResourceReference e : element.getTarget()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeResourceReference("collector", element.getCollector());
      if (element.getComment().size() > 0) {
        openArray("comment");
        for (String_ e : element.getComment()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getComment())) {
          openArray("_comment");
          for (String_ e : element.getComment()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeType("collected", element.getCollected());
      composeQuantity("quantity", element.getQuantity());
      composeCodeableConcept("method", element.getMethod());
      composeCodeableConcept("sourceSite", element.getSourceSite());
      close();
    }
  }

  private void composeSpecimenSpecimenTreatmentComponent(String name, Specimen.SpecimenTreatmentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeCodeableConcept("procedure", element.getProcedure());
      if (element.getAdditive().size() > 0) {
        openArray("additive");
        for (ResourceReference e : element.getAdditive()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSpecimenSpecimenContainerComponent(String name, Specimen.SpecimenContainerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeCodeableConcept("type", element.getType());
      composeQuantity("capacity", element.getCapacity());
      composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      composeResourceReference("additive", element.getAdditive());
      close();
    }
  }

  private void composeSubscription(String name, Subscription element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("criteria", element.getCriteria(), false);
      composeStringExtras("criteria", element.getCriteria(), false);
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Contact e : element.getContact()) 
          composeContact(null, e);
        closeArray();
      };
      composeStringCore("reason", element.getReason(), false);
      composeStringExtras("reason", element.getReason(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Subscription.SubscriptionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Subscription.SubscriptionStatusEnumFactory(), false);
      }
      composeStringCore("error", element.getError(), false);
      composeStringExtras("error", element.getError(), false);
      composeSubscriptionSubscriptionChannelComponent("channel", element.getChannel());
      composeInstantCore("end", element.getEnd(), false);
      composeInstantExtras("end", element.getEnd(), false);
      if (element.getTag().size() > 0) {
        openArray("tag");
        for (Subscription.SubscriptionTagComponent e : element.getTag()) 
          composeSubscriptionSubscriptionTagComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeSubscriptionSubscriptionChannelComponent(String name, Subscription.SubscriptionChannelComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType() != null) {
        composeEnumerationCore("type", element.getType(), new Subscription.SubscriptionChannelTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getType(), new Subscription.SubscriptionChannelTypeEnumFactory(), false);
      }
      composeUriCore("url", element.getUrl(), false);
      composeUriExtras("url", element.getUrl(), false);
      composeStringCore("payload", element.getPayload(), false);
      composeStringExtras("payload", element.getPayload(), false);
      composeStringCore("header", element.getHeader(), false);
      composeStringExtras("header", element.getHeader(), false);
      close();
    }
  }

  private void composeSubscriptionSubscriptionTagComponent(String name, Subscription.SubscriptionTagComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("term", element.getTerm(), false);
      composeUriExtras("term", element.getTerm(), false);
      composeUriCore("scheme", element.getScheme(), false);
      composeUriExtras("scheme", element.getScheme(), false);
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      close();
    }
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeSubstanceSubstanceInstanceComponent("instance", element.getInstance());
      if (element.getIngredient().size() > 0) {
        openArray("ingredient");
        for (Substance.SubstanceIngredientComponent e : element.getIngredient()) 
          composeSubstanceSubstanceIngredientComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeSubstanceSubstanceInstanceComponent(String name, Substance.SubstanceInstanceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTimeCore("expiry", element.getExpiry(), false);
      composeDateTimeExtras("expiry", element.getExpiry(), false);
      composeQuantity("quantity", element.getQuantity());
      close();
    }
  }

  private void composeSubstanceSubstanceIngredientComponent(String name, Substance.SubstanceIngredientComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeRatio("quantity", element.getQuantity());
      composeResourceReference("substance", element.getSubstance());
      close();
    }
  }

  private void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeCodeableConcept("kind", element.getKind());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Supply.ValuesetSupplyStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Supply.ValuesetSupplyStatusEnumFactory(), false);
      }
      composeResourceReference("orderedItem", element.getOrderedItem());
      composeResourceReference("patient", element.getPatient());
      if (element.getDispense().size() > 0) {
        openArray("dispense");
        for (Supply.SupplyDispenseComponent e : element.getDispense()) 
          composeSupplySupplyDispenseComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeSupplySupplyDispenseComponent(String name, Supply.SupplyDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new Supply.ValuesetSupplyDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new Supply.ValuesetSupplyDispenseStatusEnumFactory(), false);
      }
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("suppliedItem", element.getSuppliedItem());
      composeResourceReference("supplier", element.getSupplier());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeResourceReference("destination", element.getDestination());
      if (element.getReceiver().size() > 0) {
        openArray("receiver");
        for (ResourceReference e : element.getReceiver()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("identifier", element.getIdentifier(), false);
      composeStringExtras("identifier", element.getIdentifier(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeStringCore("name", element.getName(), false);
      composeStringExtras("name", element.getName(), false);
      composeStringCore("purpose", element.getPurpose(), false);
      composeStringExtras("purpose", element.getPurpose(), false);
      composeBooleanCore("immutable", element.getImmutable(), false);
      composeBooleanExtras("immutable", element.getImmutable(), false);
      composeStringCore("publisher", element.getPublisher(), false);
      composeStringExtras("publisher", element.getPublisher(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescription(), false);
      composeStringExtras("description", element.getDescription(), false);
      composeStringCore("copyright", element.getCopyright(), false);
      composeStringExtras("copyright", element.getCopyright(), false);
      if (element.getStatus() != null) {
        composeEnumerationCore("status", element.getStatus(), new ValueSet.ValuesetStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatus(), new ValueSet.ValuesetStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimental(), false);
      composeBooleanExtras("experimental", element.getExperimental(), false);
      composeBooleanCore("extensible", element.getExtensible(), false);
      composeBooleanExtras("extensible", element.getExtensible(), false);
      composeDateTimeCore("date", element.getDate(), false);
      composeDateTimeExtras("date", element.getDate(), false);
      composeDateCore("stableDate", element.getStableDate(), false);
      composeDateExtras("stableDate", element.getStableDate(), false);
      composeValueSetValueSetDefineComponent("define", element.getDefine());
      composeValueSetValueSetComposeComponent("compose", element.getCompose());
      composeValueSetValueSetExpansionComponent("expansion", element.getExpansion());
    }
  }

  private void composeValueSetValueSetDefineComponent(String name, ValueSet.ValueSetDefineComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      composeBooleanCore("caseSensitive", element.getCaseSensitive(), false);
      composeBooleanExtras("caseSensitive", element.getCaseSensitive(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
          composeValueSetValueSetDefineConceptComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetValueSetDefineConceptComponent(String name, ValueSet.ValueSetDefineConceptComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      composeBooleanCore("abstract", element.getAbstract(), false);
      composeBooleanExtras("abstract", element.getAbstract(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      composeStringCore("definition", element.getDefinition(), false);
      composeStringExtras("definition", element.getDefinition(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
          composeValueSetValueSetDefineConceptComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getImport().size() > 0) {
        openArray("import");
        for (Uri e : element.getImport()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getImport())) {
          openArray("_import");
          for (Uri e : element.getImport()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getInclude().size() > 0) {
        openArray("include");
        for (ValueSet.ConceptSetComponent e : element.getInclude()) 
          composeValueSetConceptSetComponent(null, e);
        closeArray();
      };
      if (element.getExclude().size() > 0) {
        openArray("exclude");
        for (ValueSet.ConceptSetComponent e : element.getExclude()) 
          composeValueSetConceptSetComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptSetComponent(String name, ValueSet.ConceptSetComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeStringCore("version", element.getVersion(), false);
      composeStringExtras("version", element.getVersion(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Code e : element.getCode()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getCode())) {
          openArray("_code");
          for (Code e : element.getCode()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getFilter().size() > 0) {
        openArray("filter");
        for (ValueSet.ConceptSetFilterComponent e : element.getFilter()) 
          composeValueSetConceptSetFilterComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("property", element.getProperty(), false);
      composeCodeExtras("property", element.getProperty(), false);
      if (element.getOp() != null) {
        composeEnumerationCore("op", element.getOp(), new ValueSet.FilterOperatorEnumFactory(), false);
        composeEnumerationExtras("op", element.getOp(), new ValueSet.FilterOperatorEnumFactory(), false);
      }
      composeCodeCore("value", element.getValue(), false);
      composeCodeExtras("value", element.getValue(), false);
      close();
    }
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeInstantCore("timestamp", element.getTimestamp(), false);
      composeInstantExtras("timestamp", element.getTimestamp(), false);
      if (element.getContains().size() > 0) {
        openArray("contains");
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetValueSetExpansionContainsComponent(String name, ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("system", element.getSystem(), false);
      composeUriExtras("system", element.getSystem(), false);
      composeCodeCore("code", element.getCode(), false);
      composeCodeExtras("code", element.getCode(), false);
      composeStringCore("display", element.getDisplay(), false);
      composeStringExtras("display", element.getDisplay(), false);
      if (element.getContains().size() > 0) {
        openArray("contains");
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  @Override
  protected void composeResource(Resource resource) throws Exception {
    if (resource instanceof AdverseReaction)
      composeAdverseReaction("AdverseReaction", (AdverseReaction)resource);
    else if (resource instanceof Alert)
      composeAlert("Alert", (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance("AllergyIntolerance", (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment("Appointment", (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse("AppointmentResponse", (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability("Availability", (Availability)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan("CarePlan", (CarePlan)resource);
    else if (resource instanceof Composition)
      composeComposition("Composition", (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap("ConceptMap", (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition("Condition", (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance("Conformance", (Conformance)resource);
    else if (resource instanceof DataElement)
      composeDataElement("DataElement", (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice("Device", (Device)resource);
    else if (resource instanceof DeviceObservationReport)
      composeDeviceObservationReport("DeviceObservationReport", (DeviceObservationReport)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder("DiagnosticOrder", (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport("DiagnosticReport", (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest("DocumentManifest", (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference("DocumentReference", (DocumentReference)resource);
    else if (resource instanceof Encounter)
      composeEncounter("Encounter", (Encounter)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory("FamilyHistory", (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup("Group", (Group)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy("ImagingStudy", (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization("Immunization", (Immunization)resource);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation("ImmunizationRecommendation", (ImmunizationRecommendation)resource);
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
    else if (resource instanceof Namespace)
      composeNamespace("Namespace", (Namespace)resource);
    else if (resource instanceof Observation)
      composeObservation("Observation", (Observation)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome("OperationOutcome", (OperationOutcome)resource);
    else if (resource instanceof Order)
      composeOrder("Order", (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse("OrderResponse", (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization("Organization", (Organization)resource);
    else if (resource instanceof Other)
      composeOther("Other", (Other)resource);
    else if (resource instanceof Patient)
      composePatient("Patient", (Patient)resource);
    else if (resource instanceof Practitioner)
      composePractitioner("Practitioner", (Practitioner)resource);
    else if (resource instanceof Procedure)
      composeProcedure("Procedure", (Procedure)resource);
    else if (resource instanceof Profile)
      composeProfile("Profile", (Profile)resource);
    else if (resource instanceof Provenance)
      composeProvenance("Provenance", (Provenance)resource);
    else if (resource instanceof Query)
      composeQuery("Query", (Query)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire("Questionnaire", (Questionnaire)resource);
    else if (resource instanceof QuestionnaireAnswers)
      composeQuestionnaireAnswers("QuestionnaireAnswers", (QuestionnaireAnswers)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson("RelatedPerson", (RelatedPerson)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent("SecurityEvent", (SecurityEvent)resource);
    else if (resource instanceof Slot)
      composeSlot("Slot", (Slot)resource);
    else if (resource instanceof Specimen)
      composeSpecimen("Specimen", (Specimen)resource);
    else if (resource instanceof Subscription)
      composeSubscription("Subscription", (Subscription)resource);
    else if (resource instanceof Substance)
      composeSubstance("Substance", (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply("Supply", (Supply)resource);
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeNamedResource(String name, Resource resource) throws Exception {
    if (resource instanceof AdverseReaction)
      composeAdverseReaction(name, (AdverseReaction)resource);
    else if (resource instanceof Alert)
      composeAlert(name, (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance(name, (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment(name, (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse(name, (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability(name, (Availability)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan(name, (CarePlan)resource);
    else if (resource instanceof Composition)
      composeComposition(name, (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap(name, (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition(name, (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance(name, (Conformance)resource);
    else if (resource instanceof DataElement)
      composeDataElement(name, (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice(name, (Device)resource);
    else if (resource instanceof DeviceObservationReport)
      composeDeviceObservationReport(name, (DeviceObservationReport)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder(name, (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport(name, (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest(name, (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference(name, (DocumentReference)resource);
    else if (resource instanceof Encounter)
      composeEncounter(name, (Encounter)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory(name, (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup(name, (Group)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy(name, (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization(name, (Immunization)resource);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation(name, (ImmunizationRecommendation)resource);
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
    else if (resource instanceof Namespace)
      composeNamespace(name, (Namespace)resource);
    else if (resource instanceof Observation)
      composeObservation(name, (Observation)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome(name, (OperationOutcome)resource);
    else if (resource instanceof Order)
      composeOrder(name, (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse(name, (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization(name, (Organization)resource);
    else if (resource instanceof Other)
      composeOther(name, (Other)resource);
    else if (resource instanceof Patient)
      composePatient(name, (Patient)resource);
    else if (resource instanceof Practitioner)
      composePractitioner(name, (Practitioner)resource);
    else if (resource instanceof Procedure)
      composeProcedure(name, (Procedure)resource);
    else if (resource instanceof Profile)
      composeProfile(name, (Profile)resource);
    else if (resource instanceof Provenance)
      composeProvenance(name, (Provenance)resource);
    else if (resource instanceof Query)
      composeQuery(name, (Query)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire(name, (Questionnaire)resource);
    else if (resource instanceof QuestionnaireAnswers)
      composeQuestionnaireAnswers(name, (QuestionnaireAnswers)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson(name, (RelatedPerson)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent(name, (SecurityEvent)resource);
    else if (resource instanceof Slot)
      composeSlot(name, (Slot)resource);
    else if (resource instanceof Specimen)
      composeSpecimen(name, (Specimen)resource);
    else if (resource instanceof Subscription)
      composeSubscription(name, (Subscription)resource);
    else if (resource instanceof Substance)
      composeSubstance(name, (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply(name, (Supply)resource);
    else if (resource instanceof ValueSet)
      composeValueSet(name, (ValueSet)resource);
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
    else if (type instanceof SampledData)
       composeSampledData(prefix+"SampledData", (SampledData) type);
    else if (type instanceof ResourceReference)
       composeResourceReference(prefix+"Resource", (ResourceReference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConcept(prefix+"CodeableConcept", (CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifier(prefix+"Identifier", (Identifier) type);
    else if (type instanceof Schedule)
       composeSchedule(prefix+"Schedule", (Schedule) type);
    else if (type instanceof Contact)
       composeContact(prefix+"Contact", (Contact) type);
    else if (type instanceof Address)
       composeAddress(prefix+"Address", (Address) type);
    else if (type instanceof HumanName)
       composeHumanName(prefix+"HumanName", (HumanName) type);
    else if (type instanceof Integer) {
      composeIntegerCore(prefix+"Integer", (Integer) type, false);
      composeIntegerExtras(prefix+"Integer", (Integer) type, false);
    }
    else if (type instanceof DateTime) {
      composeDateTimeCore(prefix+"DateTime", (DateTime) type, false);
      composeDateTimeExtras(prefix+"DateTime", (DateTime) type, false);
    }
    else if (type instanceof Code) {
      composeCodeCore(prefix+"Code", (Code) type, false);
      composeCodeExtras(prefix+"Code", (Code) type, false);
    }
    else if (type instanceof Date) {
      composeDateCore(prefix+"Date", (Date) type, false);
      composeDateExtras(prefix+"Date", (Date) type, false);
    }
    else if (type instanceof Decimal) {
      composeDecimalCore(prefix+"Decimal", (Decimal) type, false);
      composeDecimalExtras(prefix+"Decimal", (Decimal) type, false);
    }
    else if (type instanceof Uri) {
      composeUriCore(prefix+"Uri", (Uri) type, false);
      composeUriExtras(prefix+"Uri", (Uri) type, false);
    }
    else if (type instanceof Id) {
      composeIdCore(prefix+"Id", (Id) type, false);
      composeIdExtras(prefix+"Id", (Id) type, false);
    }
    else if (type instanceof Base64Binary) {
      composeBase64BinaryCore(prefix+"Base64Binary", (Base64Binary) type, false);
      composeBase64BinaryExtras(prefix+"Base64Binary", (Base64Binary) type, false);
    }
    else if (type instanceof Oid) {
      composeOidCore(prefix+"Oid", (Oid) type, false);
      composeOidExtras(prefix+"Oid", (Oid) type, false);
    }
    else if (type instanceof String_) {
      composeStringCore(prefix+"String", (String_) type, false);
      composeStringExtras(prefix+"String", (String_) type, false);
    }
    else if (type instanceof Boolean) {
      composeBooleanCore(prefix+"Boolean", (Boolean) type, false);
      composeBooleanExtras(prefix+"Boolean", (Boolean) type, false);
    }
    else if (type instanceof Uuid) {
      composeUuidCore(prefix+"Uuid", (Uuid) type, false);
      composeUuidExtras(prefix+"Uuid", (Uuid) type, false);
    }
    else if (type instanceof Instant) {
      composeInstantCore(prefix+"Instant", (Instant) type, false);
      composeInstantExtras(prefix+"Instant", (Instant) type, false);
    }
    else
      throw new Exception("Unhanded type");
  }

}

