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

// Generated on Wed, Oct 8, 2014 17:06+1100 for FHIR v0.3.0

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.utilities.Utilities;

public class JsonComposer extends JsonComposerBase {

  private void composeElement(Element element) throws Exception {
    if (element.getXmlId() != null)
      prop("id", element.getXmlId());
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

  private void composeIntegerCore(String name, IntegerType value, boolean inArray) throws Exception {
    if (value != null) {
        prop(name, java.lang.Integer.valueOf(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeIntegerExtras(String name, IntegerType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeDateTimeCore(String name, DateTimeType value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeDateTimeExtras(String name, DateTimeType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeCodeCore(String name, CodeType value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeCodeExtras(String name, CodeType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeDateCore(String name, DateType value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeDateExtras(String name, DateType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeDecimalCore(String name, DecimalType value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, value.getValue());
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeDecimalExtras(String name, DecimalType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeUriCore(String name, UriType value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeUriExtras(String name, UriType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeIdCore(String name, IdType value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeIdExtras(String name, IdType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeBase64BinaryCore(String name, Base64BinaryType value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeBase64BinaryExtras(String name, Base64BinaryType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeTimeCore(String name, TimeType value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeTimeExtras(String name, TimeType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeOidCore(String name, OidType value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeOidExtras(String name, OidType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeStringCore(String name, StringType value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeStringExtras(String name, StringType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeBooleanCore(String name, BooleanType value, boolean inArray) throws Exception {
    if (value != null) {
        prop(name, value.getValue());
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeBooleanExtras(String name, BooleanType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeUuidCore(String name, UuidType value, boolean inArray) throws Exception {
    if (value != null && !Utilities.noString(value.getValue())) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeUuidExtras(String name, UuidType value, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions())) {
      open(inArray ? null : "_"+name);
      composeElement(value);
      close();
    }
    else if (inArray) 
      writeNull(name); 
  }

  private void composeInstantCore(String name, InstantType value, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
        prop(name, toString(value.getValue()));
    }    
    else if (inArray) 
      writeNull(name); 
  }    

  private void composeInstantExtras(String name, InstantType value, boolean inArray) throws Exception {
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
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      composeType("value", element.getValue());
      close();
    }
  }

  private void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Narrative.NarrativeStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Narrative.NarrativeStatusEnumFactory(), false);
      }
      composeXhtml("div", element.getDiv());
      close();
    }
  }

  private void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDateTimeCore("start", element.getStartObject(), false);
      composeDateTimeExtras("start", element.getStartObject(), false);
      composeDateTimeCore("end", element.getEndObject(), false);
      composeDateTimeExtras("end", element.getEndObject(), false);
      close();
    }
  }

  private void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
      composeBooleanCore("primary", element.getPrimaryObject(), false);
      composeBooleanExtras("primary", element.getPrimaryObject(), false);
      composeReference("valueSet", element.getValueSet());
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
      composeDecimalCore("value", element.getValueObject(), false);
      composeDecimalExtras("value", element.getValueObject(), false);
      if (element.getComparatorObject() != null) {
        composeEnumerationCore("comparator", element.getComparatorObject(), new Quantity.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorObject(), new Quantity.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsObject(), false);
      composeStringExtras("units", element.getUnitsObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeCore("contentType", element.getContentTypeObject(), false);
      composeCodeExtras("contentType", element.getContentTypeObject(), false);
      composeCodeCore("language", element.getLanguageObject(), false);
      composeCodeExtras("language", element.getLanguageObject(), false);
      composeBase64BinaryCore("data", element.getDataObject(), false);
      composeBase64BinaryExtras("data", element.getDataObject(), false);
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      composeIntegerCore("size", element.getSizeObject(), false);
      composeIntegerExtras("size", element.getSizeObject(), false);
      composeBase64BinaryCore("hash", element.getHashObject(), false);
      composeBase64BinaryExtras("hash", element.getHashObject(), false);
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
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
      composeDecimalCore("period", element.getPeriodObject(), false);
      composeDecimalExtras("period", element.getPeriodObject(), false);
      composeDecimalCore("factor", element.getFactorObject(), false);
      composeDecimalExtras("factor", element.getFactorObject(), false);
      composeDecimalCore("lowerLimit", element.getLowerLimitObject(), false);
      composeDecimalExtras("lowerLimit", element.getLowerLimitObject(), false);
      composeDecimalCore("upperLimit", element.getUpperLimitObject(), false);
      composeDecimalExtras("upperLimit", element.getUpperLimitObject(), false);
      composeIntegerCore("dimensions", element.getDimensionsObject(), false);
      composeIntegerExtras("dimensions", element.getDimensionsObject(), false);
      composeStringCore("data", element.getDataObject(), false);
      composeStringExtras("data", element.getDataObject(), false);
      close();
    }
  }

  private void composeReference(String name, Reference element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeStringCore("reference", element.getReferenceObject(), false);
      composeStringExtras("reference", element.getReferenceObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
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
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      close();
    }
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUseObject() != null) {
        composeEnumerationCore("use", element.getUseObject(), new Identifier.IdentifierUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseObject(), new Identifier.IdentifierUseEnumFactory(), false);
      }
      composeStringCore("label", element.getLabelObject(), false);
      composeStringExtras("label", element.getLabelObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeStringCore("value", element.getValueObject(), false);
      composeStringExtras("value", element.getValueObject(), false);
      composePeriod("period", element.getPeriod());
      composeReference("assigner", element.getAssigner());
      close();
    }
  }

  private void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValueObject(), false);
      composeDecimalExtras("value", element.getValueObject(), false);
      if (element.getComparatorObject() != null) {
        composeEnumerationCore("comparator", element.getComparatorObject(), new Age.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorObject(), new Age.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsObject(), false);
      composeStringExtras("units", element.getUnitsObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValueObject(), false);
      composeDecimalExtras("value", element.getValueObject(), false);
      if (element.getComparatorObject() != null) {
        composeEnumerationCore("comparator", element.getComparatorObject(), new Count.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorObject(), new Count.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsObject(), false);
      composeStringExtras("units", element.getUnitsObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValueObject(), false);
      composeDecimalExtras("value", element.getValueObject(), false);
      if (element.getComparatorObject() != null) {
        composeEnumerationCore("comparator", element.getComparatorObject(), new Money.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorObject(), new Money.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsObject(), false);
      composeStringExtras("units", element.getUnitsObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValueObject(), false);
      composeDecimalExtras("value", element.getValueObject(), false);
      if (element.getComparatorObject() != null) {
        composeEnumerationCore("comparator", element.getComparatorObject(), new Distance.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorObject(), new Distance.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsObject(), false);
      composeStringExtras("units", element.getUnitsObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimalCore("value", element.getValueObject(), false);
      composeDecimalExtras("value", element.getValueObject(), false);
      if (element.getComparatorObject() != null) {
        composeEnumerationCore("comparator", element.getComparatorObject(), new Duration.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorObject(), new Duration.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsObject(), false);
      composeStringExtras("units", element.getUnitsObject(), false);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeTiming(String name, Timing element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Period e : element.getEvent()) 
          composePeriod(null, e);
        closeArray();
      };
      composeTimingTimingRepeatComponent("repeat", element.getRepeat());
      close();
    }
  }

  private void composeTimingTimingRepeatComponent(String name, Timing.TimingRepeatComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeIntegerCore("frequency", element.getFrequencyObject(), false);
      composeIntegerExtras("frequency", element.getFrequencyObject(), false);
      if (element.getWhenObject() != null) {
        composeEnumerationCore("when", element.getWhenObject(), new Timing.EventTimingEnumFactory(), false);
        composeEnumerationExtras("when", element.getWhenObject(), new Timing.EventTimingEnumFactory(), false);
      }
      composeDecimalCore("duration", element.getDurationObject(), false);
      composeDecimalExtras("duration", element.getDurationObject(), false);
      if (element.getUnitsObject() != null) {
        composeEnumerationCore("units", element.getUnitsObject(), new Timing.UnitsOfTimeEnumFactory(), false);
        composeEnumerationExtras("units", element.getUnitsObject(), new Timing.UnitsOfTimeEnumFactory(), false);
      }
      composeIntegerCore("count", element.getCountObject(), false);
      composeIntegerExtras("count", element.getCountObject(), false);
      composeDateTimeCore("end", element.getEndObject(), false);
      composeDateTimeExtras("end", element.getEndObject(), false);
      close();
    }
  }

  private void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUseObject() != null) {
        composeEnumerationCore("use", element.getUseObject(), new Address.AddressUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseObject(), new Address.AddressUseEnumFactory(), false);
      }
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      if (element.getLine().size() > 0) {
        openArray("line");
        for (StringType e : element.getLine()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getLine())) {
          openArray("_line");
          for (StringType e : element.getLine()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeStringCore("city", element.getCityObject(), false);
      composeStringExtras("city", element.getCityObject(), false);
      composeStringCore("state", element.getStateObject(), false);
      composeStringExtras("state", element.getStateObject(), false);
      composeStringCore("zip", element.getZipObject(), false);
      composeStringExtras("zip", element.getZipObject(), false);
      composeStringCore("country", element.getCountryObject(), false);
      composeStringExtras("country", element.getCountryObject(), false);
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUseObject() != null) {
        composeEnumerationCore("use", element.getUseObject(), new HumanName.NameUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseObject(), new HumanName.NameUseEnumFactory(), false);
      }
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      if (element.getFamily().size() > 0) {
        openArray("family");
        for (StringType e : element.getFamily()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getFamily())) {
          openArray("_family");
          for (StringType e : element.getFamily()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getGiven().size() > 0) {
        openArray("given");
        for (StringType e : element.getGiven()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getGiven())) {
          openArray("_given");
          for (StringType e : element.getGiven()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getPrefix().size() > 0) {
        openArray("prefix");
        for (StringType e : element.getPrefix()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getPrefix())) {
          openArray("_prefix");
          for (StringType e : element.getPrefix()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getSuffix().size() > 0) {
        openArray("suffix");
        for (StringType e : element.getSuffix()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSuffix())) {
          openArray("_suffix");
          for (StringType e : element.getSuffix()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeContactPoint(String name, ContactPoint element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getSystemObject() != null) {
        composeEnumerationCore("system", element.getSystemObject(), new ContactPoint.ContactPointSystemEnumFactory(), false);
        composeEnumerationExtras("system", element.getSystemObject(), new ContactPoint.ContactPointSystemEnumFactory(), false);
      }
      composeStringCore("value", element.getValueObject(), false);
      composeStringExtras("value", element.getValueObject(), false);
      if (element.getUseObject() != null) {
        composeEnumerationCore("use", element.getUseObject(), new ContactPoint.ContactPointUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseObject(), new ContactPoint.ContactPointUseEnumFactory(), false);
      }
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeResourceElements(Resource element) throws Exception {
    composeBackbone(element);
    if (element.getText() != null)
      composeNarrative("text", element.getText());
    if (element.getLanguageObject() != null) {
      composeCodeCore("language", element.getLanguageObject(), false);
      composeCodeExtras("language", element.getLanguageObject(), false);
    }
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
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("subject", element.getSubject());
      composeBooleanCore("didNotOccurFlag", element.getDidNotOccurFlagObject(), false);
      composeBooleanExtras("didNotOccurFlag", element.getDidNotOccurFlagObject(), false);
      composeReference("recorder", element.getRecorder());
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
      if (element.getSeverityObject() != null) {
        composeEnumerationCore("severity", element.getSeverityObject(), new AdverseReaction.ReactionSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverityObject(), new AdverseReaction.ReactionSeverityEnumFactory(), false);
      }
      close();
    }
  }

  private void composeAdverseReactionAdverseReactionExposureComponent(String name, AdverseReaction.AdverseReactionExposureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new AdverseReaction.ExposureTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new AdverseReaction.ExposureTypeEnumFactory(), false);
      }
      if (element.getCausalityExpectationObject() != null) {
        composeEnumerationCore("causalityExpectation", element.getCausalityExpectationObject(), new AdverseReaction.CausalityExpectationEnumFactory(), false);
        composeEnumerationExtras("causalityExpectation", element.getCausalityExpectationObject(), new AdverseReaction.CausalityExpectationEnumFactory(), false);
      }
      composeReference("substance", element.getSubstance());
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Alert.AlertStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Alert.AlertStatusEnumFactory(), false);
      }
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeStringCore("note", element.getNoteObject(), false);
      composeStringExtras("note", element.getNoteObject(), false);
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
      if (element.getCriticalityObject() != null) {
        composeEnumerationCore("criticality", element.getCriticalityObject(), new AllergyIntolerance.CriticalityEnumFactory(), false);
        composeEnumerationExtras("criticality", element.getCriticalityObject(), new AllergyIntolerance.CriticalityEnumFactory(), false);
      }
      if (element.getSensitivityTypeObject() != null) {
        composeEnumerationCore("sensitivityType", element.getSensitivityTypeObject(), new AllergyIntolerance.SensitivitytypeEnumFactory(), false);
        composeEnumerationExtras("sensitivityType", element.getSensitivityTypeObject(), new AllergyIntolerance.SensitivitytypeEnumFactory(), false);
      }
      composeDateTimeCore("recordedDate", element.getRecordedDateObject(), false);
      composeDateTimeExtras("recordedDate", element.getRecordedDateObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new AllergyIntolerance.SensitivitystatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new AllergyIntolerance.SensitivitystatusEnumFactory(), false);
      }
      composeReference("subject", element.getSubject());
      composeReference("recorder", element.getRecorder());
      composeReference("substance", element.getSubstance());
      if (element.getReaction().size() > 0) {
        openArray("reaction");
        for (Reference e : element.getReaction()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getSensitivityTest().size() > 0) {
        openArray("sensitivityTest");
        for (Reference e : element.getSensitivityTest()) 
          composeReference(null, e);
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
      composeIntegerCore("priority", element.getPriorityObject(), false);
      composeIntegerExtras("priority", element.getPriorityObject(), false);
      composeCodeCore("status", element.getStatusObject(), false);
      composeCodeExtras("status", element.getStatusObject(), false);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeInstantCore("start", element.getStartObject(), false);
      composeInstantExtras("start", element.getStartObject(), false);
      composeInstantCore("end", element.getEndObject(), false);
      composeInstantExtras("end", element.getEndObject(), false);
      if (element.getSlot().size() > 0) {
        openArray("slot");
        for (Reference e : element.getSlot()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("location", element.getLocation());
      composeStringCore("comment", element.getCommentObject(), false);
      composeStringExtras("comment", element.getCommentObject(), false);
      composeReference("order", element.getOrder());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (Appointment.AppointmentParticipantComponent e : element.getParticipant()) 
          composeAppointmentAppointmentParticipantComponent(null, e);
        closeArray();
      };
      composeReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTimeCore("lastModified", element.getLastModifiedObject(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedObject(), false);
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
      composeReference("actor", element.getActor());
      if (element.getRequiredObject() != null) {
        composeEnumerationCore("required", element.getRequiredObject(), new Appointment.ParticipantrequiredEnumFactory(), false);
        composeEnumerationExtras("required", element.getRequiredObject(), new Appointment.ParticipantrequiredEnumFactory(), false);
      }
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Appointment.ParticipationstatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Appointment.ParticipationstatusEnumFactory(), false);
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
      composeReference("appointment", element.getAppointment());
      if (element.getParticipantType().size() > 0) {
        openArray("participantType");
        for (CodeableConcept e : element.getParticipantType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getIndividual().size() > 0) {
        openArray("individual");
        for (Reference e : element.getIndividual()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getParticipantStatusObject() != null) {
        composeEnumerationCore("participantStatus", element.getParticipantStatusObject(), new AppointmentResponse.ParticipantstatusEnumFactory(), false);
        composeEnumerationExtras("participantStatus", element.getParticipantStatusObject(), new AppointmentResponse.ParticipantstatusEnumFactory(), false);
      }
      composeStringCore("comment", element.getCommentObject(), false);
      composeStringExtras("comment", element.getCommentObject(), false);
      composeInstantCore("start", element.getStartObject(), false);
      composeInstantExtras("start", element.getStartObject(), false);
      composeInstantCore("end", element.getEndObject(), false);
      composeInstantExtras("end", element.getEndObject(), false);
      composeReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTimeCore("lastModified", element.getLastModifiedObject(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedObject(), false);
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
      composeReference("actor", element.getActor());
      composePeriod("planningHorizon", element.getPlanningHorizon());
      composeStringCore("comment", element.getCommentObject(), false);
      composeStringExtras("comment", element.getCommentObject(), false);
      composeDateTimeCore("lastModified", element.getLastModifiedObject(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedObject(), false);
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
      composeReference("patient", element.getPatient());
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new CarePlan.CarePlanStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new CarePlan.CarePlanStatusEnumFactory(), false);
      }
      composePeriod("period", element.getPeriod());
      composeDateTimeCore("modified", element.getModifiedObject(), false);
      composeDateTimeExtras("modified", element.getModifiedObject(), false);
      if (element.getConcern().size() > 0) {
        openArray("concern");
        for (Reference e : element.getConcern()) 
          composeReference(null, e);
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
      composeStringCore("notes", element.getNotesObject(), false);
      composeStringExtras("notes", element.getNotesObject(), false);
    }
  }

  private void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("role", element.getRole());
      composeReference("member", element.getMember());
      close();
    }
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new CarePlan.CarePlanGoalStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new CarePlan.CarePlanGoalStatusEnumFactory(), false);
      }
      composeStringCore("notes", element.getNotesObject(), false);
      composeStringExtras("notes", element.getNotesObject(), false);
      if (element.getConcern().size() > 0) {
        openArray("concern");
        for (Reference e : element.getConcern()) 
          composeReference(null, e);
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
        for (StringType e : element.getGoal()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getGoal())) {
          openArray("_goal");
          for (StringType e : element.getGoal()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new CarePlan.CarePlanActivityStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new CarePlan.CarePlanActivityStatusEnumFactory(), false);
      }
      composeBooleanCore("prohibited", element.getProhibitedObject(), false);
      composeBooleanExtras("prohibited", element.getProhibitedObject(), false);
      if (element.getActionResulting().size() > 0) {
        openArray("actionResulting");
        for (Reference e : element.getActionResulting()) 
          composeReference(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotesObject(), false);
      composeStringExtras("notes", element.getNotesObject(), false);
      composeReference("detail", element.getDetail());
      composeCarePlanCarePlanActivitySimpleComponent("simple", element.getSimple());
      close();
    }
  }

  private void composeCarePlanCarePlanActivitySimpleComponent(String name, CarePlan.CarePlanActivitySimpleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCategoryObject() != null) {
        composeEnumerationCore("category", element.getCategoryObject(), new CarePlan.CarePlanActivityCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategoryObject(), new CarePlan.CarePlanActivityCategoryEnumFactory(), false);
      }
      composeCodeableConcept("code", element.getCode());
      composeType("scheduled", element.getScheduled());
      composeReference("location", element.getLocation());
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (Reference e : element.getPerformer()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("product", element.getProduct());
      composeQuantity("dailyAmount", element.getDailyAmount());
      composeQuantity("quantity", element.getQuantity());
      composeStringCore("details", element.getDetailsObject(), false);
      composeStringExtras("details", element.getDetailsObject(), false);
      close();
    }
  }

  private void composeComposition(String name, Composition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Composition.CompositionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Composition.CompositionStatusEnumFactory(), false);
      }
      composeCoding("confidentiality", element.getConfidentiality());
      composeReference("subject", element.getSubject());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (Reference e : element.getAuthor()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getAttester().size() > 0) {
        openArray("attester");
        for (Composition.CompositionAttesterComponent e : element.getAttester()) 
          composeCompositionCompositionAttesterComponent(null, e);
        closeArray();
      };
      composeReference("custodian", element.getCustodian());
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Composition.CompositionEventComponent e : element.getEvent()) 
          composeCompositionCompositionEventComponent(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
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
      composeDateTimeCore("time", element.getTimeObject(), false);
      composeDateTimeExtras("time", element.getTimeObject(), false);
      composeReference("party", element.getParty());
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
        for (Reference e : element.getDetail()) 
          composeReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeCompositionSectionComponent(String name, Composition.SectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeNarrative("text", element.getText());
      composeCodeableConcept("emptyReason", element.getEmptyReason());
      composeCodeableConcept("order", element.getOrder());
      if (element.getSection().size() > 0) {
        openArray("section");
        for (Composition.SectionComponent e : element.getSection()) 
          composeCompositionSectionComponent(null, e);
        closeArray();
      };
      if (element.getEntry().size() > 0) {
        openArray("entry");
        for (Reference e : element.getEntry()) 
          composeReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConceptMap(String name, ConceptMap element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("identifier", element.getIdentifierObject(), false);
      composeStringExtras("identifier", element.getIdentifierObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeStringCore("copyright", element.getCopyrightObject(), false);
      composeStringExtras("copyright", element.getCopyrightObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new ConceptMap.ValuesetStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new ConceptMap.ValuesetStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalObject(), false);
      composeBooleanExtras("experimental", element.getExperimentalObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
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
      composeUriCore("codeSystem", element.getCodeSystemObject(), false);
      composeUriExtras("codeSystem", element.getCodeSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
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
      composeUriCore("element", element.getElementObject(), false);
      composeUriExtras("element", element.getElementObject(), false);
      composeUriCore("codeSystem", element.getCodeSystemObject(), false);
      composeUriExtras("codeSystem", element.getCodeSystemObject(), false);
      composeStringCore("code", element.getCodeObject(), false);
      composeStringExtras("code", element.getCodeObject(), false);
      close();
    }
  }

  private void composeConceptMapConceptMapElementMapComponent(String name, ConceptMap.ConceptMapElementMapComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("codeSystem", element.getCodeSystemObject(), false);
      composeUriExtras("codeSystem", element.getCodeSystemObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      if (element.getEquivalenceObject() != null) {
        composeEnumerationCore("equivalence", element.getEquivalenceObject(), new ConceptMap.ConceptEquivalenceEnumFactory(), false);
        composeEnumerationExtras("equivalence", element.getEquivalenceObject(), new ConceptMap.ConceptEquivalenceEnumFactory(), false);
      }
      composeStringCore("comments", element.getCommentsObject(), false);
      composeStringExtras("comments", element.getCommentsObject(), false);
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
      composeReference("subject", element.getSubject());
      composeReference("encounter", element.getEncounter());
      composeReference("asserter", element.getAsserter());
      composeDateCore("dateAsserted", element.getDateAssertedObject(), false);
      composeDateExtras("dateAsserted", element.getDateAssertedObject(), false);
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Condition.ConditionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Condition.ConditionStatusEnumFactory(), false);
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
      composeStringCore("notes", element.getNotesObject(), false);
      composeStringExtras("notes", element.getNotesObject(), false);
    }
  }

  private void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("summary", element.getSummary());
      if (element.getAssessment().size() > 0) {
        openArray("assessment");
        for (Reference e : element.getAssessment()) 
          composeReference(null, e);
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
        for (Reference e : element.getDetail()) 
          composeReference(null, e);
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
      composeStringCore("detail", element.getDetailObject(), false);
      composeStringExtras("detail", element.getDetailObject(), false);
      close();
    }
  }

  private void composeConditionConditionRelatedItemComponent(String name, Condition.ConditionRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Condition.ConditionRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Condition.ConditionRelationshipTypeEnumFactory(), false);
      }
      composeCodeableConcept("code", element.getCode());
      composeReference("target", element.getTarget());
      close();
    }
  }

  private void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("identifier", element.getIdentifierObject(), false);
      composeStringExtras("identifier", element.getIdentifierObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Conformance.ConformanceStatementStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Conformance.ConformanceStatementStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalObject(), false);
      composeBooleanExtras("experimental", element.getExperimentalObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeIdCore("fhirVersion", element.getFhirVersionObject(), false);
      composeIdExtras("fhirVersion", element.getFhirVersionObject(), false);
      composeBooleanCore("acceptUnknown", element.getAcceptUnknownObject(), false);
      composeBooleanExtras("acceptUnknown", element.getAcceptUnknownObject(), false);
      if (element.getFormat().size() > 0) {
        openArray("format");
        for (CodeType e : element.getFormat()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getFormat())) {
          openArray("_format");
          for (CodeType e : element.getFormat()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getProfile().size() > 0) {
        openArray("profile");
        for (Reference e : element.getProfile()) 
          composeReference(null, e);
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
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeDateTimeCore("releaseDate", element.getReleaseDateObject(), false);
      composeDateTimeExtras("releaseDate", element.getReleaseDateObject(), false);
      close();
    }
  }

  private void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getModeObject() != null) {
        composeEnumerationCore("mode", element.getModeObject(), new Conformance.RestfulConformanceModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeObject(), new Conformance.RestfulConformanceModeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      composeConformanceConformanceRestSecurityComponent("security", element.getSecurity());
      if (element.getResource().size() > 0) {
        openArray("resource");
        for (Conformance.ConformanceRestResourceComponent e : element.getResource()) 
          composeConformanceConformanceRestResourceComponent(null, e);
        closeArray();
      };
      if (element.getInteraction().size() > 0) {
        openArray("interaction");
        for (Conformance.SystemInteractionComponent e : element.getInteraction()) 
          composeConformanceSystemInteractionComponent(null, e);
        closeArray();
      };
      if (element.getOperation().size() > 0) {
        openArray("operation");
        for (Conformance.ConformanceRestOperationComponent e : element.getOperation()) 
          composeConformanceConformanceRestOperationComponent(null, e);
        closeArray();
      };
      if (element.getDocumentMailbox().size() > 0) {
        openArray("documentMailbox");
        for (UriType e : element.getDocumentMailbox()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getDocumentMailbox())) {
          openArray("_documentMailbox");
          for (UriType e : element.getDocumentMailbox()) 
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
      composeBooleanCore("cors", element.getCorsObject(), false);
      composeBooleanExtras("cors", element.getCorsObject(), false);
      if (element.getService().size() > 0) {
        openArray("service");
        for (CodeableConcept e : element.getService()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
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
      composeCodeCore("type", element.getTypeObject(), false);
      composeCodeExtras("type", element.getTypeObject(), false);
      composeBase64BinaryCore("blob", element.getBlobObject(), false);
      composeBase64BinaryExtras("blob", element.getBlobObject(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("type", element.getTypeObject(), false);
      composeCodeExtras("type", element.getTypeObject(), false);
      composeReference("profile", element.getProfile());
      if (element.getInteraction().size() > 0) {
        openArray("interaction");
        for (Conformance.ResourceInteractionComponent e : element.getInteraction()) 
          composeConformanceResourceInteractionComponent(null, e);
        closeArray();
      };
      composeBooleanCore("readHistory", element.getReadHistoryObject(), false);
      composeBooleanExtras("readHistory", element.getReadHistoryObject(), false);
      composeBooleanCore("updateCreate", element.getUpdateCreateObject(), false);
      composeBooleanExtras("updateCreate", element.getUpdateCreateObject(), false);
      if (element.getSearchInclude().size() > 0) {
        openArray("searchInclude");
        for (StringType e : element.getSearchInclude()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSearchInclude())) {
          openArray("_searchInclude");
          for (StringType e : element.getSearchInclude()) 
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

  private void composeConformanceResourceInteractionComponent(String name, Conformance.ResourceInteractionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCodeObject() != null) {
        composeEnumerationCore("code", element.getCodeObject(), new Conformance.TypeRestfulInteractionEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeObject(), new Conformance.TypeRestfulInteractionEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeUriCore("definition", element.getDefinitionObject(), false);
      composeUriExtras("definition", element.getDefinitionObject(), false);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Conformance.SearchParamTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Conformance.SearchParamTypeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (CodeType e : element.getTarget()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getTarget())) {
          openArray("_target");
          for (CodeType e : element.getTarget()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getChain().size() > 0) {
        openArray("chain");
        for (StringType e : element.getChain()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getChain())) {
          openArray("_chain");
          for (StringType e : element.getChain()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      close();
    }
  }

  private void composeConformanceSystemInteractionComponent(String name, Conformance.SystemInteractionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCodeObject() != null) {
        composeEnumerationCore("code", element.getCodeObject(), new Conformance.SystemRestfulInteractionEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeObject(), new Conformance.SystemRestfulInteractionEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      close();
    }
  }

  private void composeConformanceConformanceRestOperationComponent(String name, Conformance.ConformanceRestOperationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeReference("definition", element.getDefinition());
      close();
    }
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("endpoint", element.getEndpointObject(), false);
      composeUriExtras("endpoint", element.getEndpointObject(), false);
      composeIntegerCore("reliableCache", element.getReliableCacheObject(), false);
      composeIntegerExtras("reliableCache", element.getReliableCacheObject(), false);
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
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
      if (element.getCategoryObject() != null) {
        composeEnumerationCore("category", element.getCategoryObject(), new Conformance.MessageSignificanceCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategoryObject(), new Conformance.MessageSignificanceCategoryEnumFactory(), false);
      }
      if (element.getModeObject() != null) {
        composeEnumerationCore("mode", element.getModeObject(), new Conformance.MessageConformanceEventModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeObject(), new Conformance.MessageConformanceEventModeEnumFactory(), false);
      }
      if (element.getProtocol().size() > 0) {
        openArray("protocol");
        for (Coding e : element.getProtocol()) 
          composeCoding(null, e);
        closeArray();
      };
      composeCodeCore("focus", element.getFocusObject(), false);
      composeCodeExtras("focus", element.getFocusObject(), false);
      composeReference("request", element.getRequest());
      composeReference("response", element.getResponse());
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      close();
    }
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getModeObject() != null) {
        composeEnumerationCore("mode", element.getModeObject(), new Conformance.DocumentModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeObject(), new Conformance.DocumentModeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      composeReference("profile", element.getProfile());
      close();
    }
  }

  private void composeContraindication(String name, Contraindication element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeReference("patient", element.getPatient());
      composeCodeableConcept("category", element.getCategory());
      composeCodeCore("severity", element.getSeverityObject(), false);
      composeCodeExtras("severity", element.getSeverityObject(), false);
      if (element.getImplicated().size() > 0) {
        openArray("implicated");
        for (Reference e : element.getImplicated()) 
          composeReference(null, e);
        closeArray();
      };
      composeStringCore("detail", element.getDetailObject(), false);
      composeStringExtras("detail", element.getDetailObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("author", element.getAuthor());
      composeIdentifier("identifier", element.getIdentifier());
      composeUriCore("reference", element.getReferenceObject(), false);
      composeUriExtras("reference", element.getReferenceObject(), false);
      if (element.getMitigation().size() > 0) {
        openArray("mitigation");
        for (Contraindication.ContraindicationMitigationComponent e : element.getMitigation()) 
          composeContraindicationContraindicationMitigationComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeContraindicationContraindicationMitigationComponent(String name, Contraindication.ContraindicationMitigationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("action", element.getAction());
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("author", element.getAuthor());
      close();
    }
  }

  private void composeDataElement(String name, DataElement element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DataElement.ResourceObservationDefStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DataElement.ResourceObservationDefStatusEnumFactory(), false);
      }
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
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
      composeStringCore("question", element.getQuestionObject(), false);
      composeStringExtras("question", element.getQuestionObject(), false);
      composeStringCore("definition", element.getDefinitionObject(), false);
      composeStringExtras("definition", element.getDefinitionObject(), false);
      composeStringCore("comments", element.getCommentsObject(), false);
      composeStringExtras("comments", element.getCommentsObject(), false);
      composeStringCore("requirements", element.getRequirementsObject(), false);
      composeStringExtras("requirements", element.getRequirementsObject(), false);
      if (element.getSynonym().size() > 0) {
        openArray("synonym");
        for (StringType e : element.getSynonym()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSynonym())) {
          openArray("_synonym");
          for (StringType e : element.getSynonym()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeCodeCore("type", element.getTypeObject(), false);
      composeCodeExtras("type", element.getTypeObject(), false);
      composeType("example", element.getExample());
      composeIntegerCore("maxLength", element.getMaxLengthObject(), false);
      composeIntegerExtras("maxLength", element.getMaxLengthObject(), false);
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
      composeBooleanCore("isExtensible", element.getIsExtensibleObject(), false);
      composeBooleanExtras("isExtensible", element.getIsExtensibleObject(), false);
      if (element.getConformanceObject() != null) {
        composeEnumerationCore("conformance", element.getConformanceObject(), new DataElement.BindingConformanceEnumFactory(), false);
        composeEnumerationExtras("conformance", element.getConformanceObject(), new DataElement.BindingConformanceEnumFactory(), false);
      }
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeReference("valueSet", element.getValueSet());
      close();
    }
  }

  private void composeDataElementDataElementMappingComponent(String name, DataElement.DataElementMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("uri", element.getUriObject(), false);
      composeUriExtras("uri", element.getUriObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("comments", element.getCommentsObject(), false);
      composeStringExtras("comments", element.getCommentsObject(), false);
      composeStringCore("map", element.getMapObject(), false);
      composeStringExtras("map", element.getMapObject(), false);
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
      composeStringCore("manufacturer", element.getManufacturerObject(), false);
      composeStringExtras("manufacturer", element.getManufacturerObject(), false);
      composeStringCore("model", element.getModelObject(), false);
      composeStringExtras("model", element.getModelObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeDateCore("expiry", element.getExpiryObject(), false);
      composeDateExtras("expiry", element.getExpiryObject(), false);
      composeStringCore("udi", element.getUdiObject(), false);
      composeStringExtras("udi", element.getUdiObject(), false);
      composeStringCore("lotNumber", element.getLotNumberObject(), false);
      composeStringExtras("lotNumber", element.getLotNumberObject(), false);
      composeReference("owner", element.getOwner());
      composeReference("location", element.getLocation());
      composeReference("patient", element.getPatient());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (ContactPoint e : element.getContact()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
    }
  }

  private void composeDeviceObservationReport(String name, DeviceObservationReport element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeInstantCore("instant", element.getInstantObject(), false);
      composeInstantExtras("instant", element.getInstantObject(), false);
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("source", element.getSource());
      composeReference("subject", element.getSubject());
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
      composeReference("observation", element.getObservation());
      close();
    }
  }

  private void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeReference("subject", element.getSubject());
      composeReference("orderer", element.getOrderer());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
      composeStringCore("clinicalNotes", element.getClinicalNotesObject(), false);
      composeStringExtras("clinicalNotes", element.getClinicalNotesObject(), false);
      if (element.getSupportingInformation().size() > 0) {
        openArray("supportingInformation");
        for (Reference e : element.getSupportingInformation()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (Reference e : element.getSpecimen()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      if (element.getPriorityObject() != null) {
        composeEnumerationCore("priority", element.getPriorityObject(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory(), false);
        composeEnumerationExtras("priority", element.getPriorityObject(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory(), false);
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      composeCodeableConcept("description", element.getDescription());
      composeDateTimeCore("dateTime", element.getDateTimeObject(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeObject(), false);
      composeReference("actor", element.getActor());
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
        for (Reference e : element.getSpecimen()) 
          composeReference(null, e);
        closeArray();
      };
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DiagnosticReport.DiagnosticReportStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DiagnosticReport.DiagnosticReportStatusEnumFactory(), false);
      }
      composeDateTimeCore("issued", element.getIssuedObject(), false);
      composeDateTimeExtras("issued", element.getIssuedObject(), false);
      composeReference("subject", element.getSubject());
      composeReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getRequestDetail().size() > 0) {
        openArray("requestDetail");
        for (Reference e : element.getRequestDetail()) 
          composeReference(null, e);
        closeArray();
      };
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      composeType("diagnostic", element.getDiagnostic());
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (Reference e : element.getSpecimen()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getResult().size() > 0) {
        openArray("result");
        for (Reference e : element.getResult()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getImagingStudy().size() > 0) {
        openArray("imagingStudy");
        for (Reference e : element.getImagingStudy()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getImage().size() > 0) {
        openArray("image");
        for (DiagnosticReport.DiagnosticReportImageComponent e : element.getImage()) 
          composeDiagnosticReportDiagnosticReportImageComponent(null, e);
        closeArray();
      };
      composeStringCore("conclusion", element.getConclusionObject(), false);
      composeStringExtras("conclusion", element.getConclusionObject(), false);
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
      composeStringCore("comment", element.getCommentObject(), false);
      composeStringExtras("comment", element.getCommentObject(), false);
      composeReference("link", element.getLink());
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
        for (Reference e : element.getSubject()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getRecipient().size() > 0) {
        openArray("recipient");
        for (Reference e : element.getRecipient()) 
          composeReference(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (Reference e : element.getAuthor()) 
          composeReference(null, e);
        closeArray();
      };
      composeDateTimeCore("created", element.getCreatedObject(), false);
      composeDateTimeExtras("created", element.getCreatedObject(), false);
      composeUriCore("source", element.getSourceObject(), false);
      composeUriExtras("source", element.getSourceObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DocumentManifest.DocumentReferenceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DocumentManifest.DocumentReferenceStatusEnumFactory(), false);
      }
      composeReference("supercedes", element.getSupercedes());
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeCodeableConcept("confidentiality", element.getConfidentiality());
      if (element.getContent().size() > 0) {
        openArray("content");
        for (Reference e : element.getContent()) 
          composeReference(null, e);
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
      composeReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (Reference e : element.getAuthor()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("custodian", element.getCustodian());
      composeUriCore("policyManager", element.getPolicyManagerObject(), false);
      composeUriExtras("policyManager", element.getPolicyManagerObject(), false);
      composeReference("authenticator", element.getAuthenticator());
      composeDateTimeCore("created", element.getCreatedObject(), false);
      composeDateTimeExtras("created", element.getCreatedObject(), false);
      composeInstantCore("indexed", element.getIndexedObject(), false);
      composeInstantExtras("indexed", element.getIndexedObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new DocumentReference.DocumentReferenceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new DocumentReference.DocumentReferenceStatusEnumFactory(), false);
      }
      composeCodeableConcept("docStatus", element.getDocStatus());
      if (element.getRelatesTo().size() > 0) {
        openArray("relatesTo");
        for (DocumentReference.DocumentReferenceRelatesToComponent e : element.getRelatesTo()) 
          composeDocumentReferenceDocumentReferenceRelatesToComponent(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getConfidentiality().size() > 0) {
        openArray("confidentiality");
        for (CodeableConcept e : element.getConfidentiality()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeCodeCore("primaryLanguage", element.getPrimaryLanguageObject(), false);
      composeCodeExtras("primaryLanguage", element.getPrimaryLanguageObject(), false);
      composeCodeCore("mimeType", element.getMimeTypeObject(), false);
      composeCodeExtras("mimeType", element.getMimeTypeObject(), false);
      if (element.getFormat().size() > 0) {
        openArray("format");
        for (UriType e : element.getFormat()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getFormat())) {
          openArray("_format");
          for (UriType e : element.getFormat()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      composeIntegerCore("size", element.getSizeObject(), false);
      composeIntegerExtras("size", element.getSizeObject(), false);
      composeStringCore("hash", element.getHashObject(), false);
      composeStringExtras("hash", element.getHashObject(), false);
      composeUriCore("location", element.getLocationObject(), false);
      composeUriExtras("location", element.getLocationObject(), false);
      composeDocumentReferenceDocumentReferenceServiceComponent("service", element.getService());
      composeDocumentReferenceDocumentReferenceContextComponent("context", element.getContext());
    }
  }

  private void composeDocumentReferenceDocumentReferenceRelatesToComponent(String name, DocumentReference.DocumentReferenceRelatesToComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCodeObject() != null) {
        composeEnumerationCore("code", element.getCodeObject(), new DocumentReference.DocumentRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeObject(), new DocumentReference.DocumentRelationshipTypeEnumFactory(), false);
      }
      composeReference("target", element.getTarget());
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeStringCore("address", element.getAddressObject(), false);
      composeStringExtras("address", element.getAddressObject(), false);
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
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("value", element.getValueObject(), false);
      composeStringExtras("value", element.getValueObject(), false);
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Encounter.EncounterStateEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Encounter.EncounterStateEnumFactory(), false);
      }
      if (element.getClass_Object() != null) {
        composeEnumerationCore("class", element.getClass_Object(), new Encounter.EncounterClassEnumFactory(), false);
        composeEnumerationExtras("class", element.getClass_Object(), new Encounter.EncounterClassEnumFactory(), false);
      }
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeReference("subject", element.getSubject());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (Encounter.EncounterParticipantComponent e : element.getParticipant()) 
          composeEncounterEncounterParticipantComponent(null, e);
        closeArray();
      };
      composeReference("fulfills", element.getFulfills());
      composePeriod("period", element.getPeriod());
      composeDuration("length", element.getLength());
      composeCodeableConcept("reason", element.getReason());
      composeReference("indication", element.getIndication());
      composeCodeableConcept("priority", element.getPriority());
      composeEncounterEncounterHospitalizationComponent("hospitalization", element.getHospitalization());
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (Encounter.EncounterLocationComponent e : element.getLocation()) 
          composeEncounterEncounterLocationComponent(null, e);
        closeArray();
      };
      composeReference("serviceProvider", element.getServiceProvider());
      composeReference("partOf", element.getPartOf());
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
      composeReference("individual", element.getIndividual());
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      composeReference("origin", element.getOrigin());
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
      composeReference("destination", element.getDestination());
      composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      composeReference("dischargeDiagnosis", element.getDischargeDiagnosis());
      composeBooleanCore("reAdmission", element.getReAdmissionObject(), false);
      composeBooleanExtras("reAdmission", element.getReAdmissionObject(), false);
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponent(String name, Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeReference("location", element.getLocation());
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
      composeReference("subject", element.getSubject());
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeStringCore("note", element.getNoteObject(), false);
      composeStringExtras("note", element.getNoteObject(), false);
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
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("born", element.getBorn());
      composeType("age", element.getAge());
      composeType("deceased", element.getDeceased());
      composeStringCore("note", element.getNoteObject(), false);
      composeStringExtras("note", element.getNoteObject(), false);
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
      composeStringCore("note", element.getNoteObject(), false);
      composeStringExtras("note", element.getNoteObject(), false);
      close();
    }
  }

  private void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Group.GroupTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Group.GroupTypeEnumFactory(), false);
      }
      composeBooleanCore("actual", element.getActualObject(), false);
      composeBooleanExtras("actual", element.getActualObject(), false);
      composeCodeableConcept("code", element.getCode());
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeIntegerCore("quantity", element.getQuantityObject(), false);
      composeIntegerExtras("quantity", element.getQuantityObject(), false);
      if (element.getCharacteristic().size() > 0) {
        openArray("characteristic");
        for (Group.GroupCharacteristicComponent e : element.getCharacteristic()) 
          composeGroupGroupCharacteristicComponent(null, e);
        closeArray();
      };
      if (element.getMember().size() > 0) {
        openArray("member");
        for (Reference e : element.getMember()) 
          composeReference(null, e);
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
      composeBooleanCore("exclude", element.getExcludeObject(), false);
      composeBooleanExtras("exclude", element.getExcludeObject(), false);
      close();
    }
  }

  private void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeDateTimeCore("dateTime", element.getDateTimeObject(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeObject(), false);
      composeReference("subject", element.getSubject());
      composeOidCore("uid", element.getUidObject(), false);
      composeOidExtras("uid", element.getUidObject(), false);
      composeIdentifier("accessionNo", element.getAccessionNo());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getOrder().size() > 0) {
        openArray("order");
        for (Reference e : element.getOrder()) 
          composeReference(null, e);
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
      composeReference("referrer", element.getReferrer());
      if (element.getAvailabilityObject() != null) {
        composeEnumerationCore("availability", element.getAvailabilityObject(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
        composeEnumerationExtras("availability", element.getAvailabilityObject(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
      }
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      composeIntegerCore("numberOfSeries", element.getNumberOfSeriesObject(), false);
      composeIntegerExtras("numberOfSeries", element.getNumberOfSeriesObject(), false);
      composeIntegerCore("numberOfInstances", element.getNumberOfInstancesObject(), false);
      composeIntegerExtras("numberOfInstances", element.getNumberOfInstancesObject(), false);
      composeStringCore("clinicalInformation", element.getClinicalInformationObject(), false);
      composeStringExtras("clinicalInformation", element.getClinicalInformationObject(), false);
      if (element.getProcedure().size() > 0) {
        openArray("procedure");
        for (Coding e : element.getProcedure()) 
          composeCoding(null, e);
        closeArray();
      };
      composeReference("interpreter", element.getInterpreter());
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
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
      composeIntegerCore("number", element.getNumberObject(), false);
      composeIntegerExtras("number", element.getNumberObject(), false);
      if (element.getModalityObject() != null) {
        composeEnumerationCore("modality", element.getModalityObject(), new ImagingStudy.ModalityEnumFactory(), false);
        composeEnumerationExtras("modality", element.getModalityObject(), new ImagingStudy.ModalityEnumFactory(), false);
      }
      composeOidCore("uid", element.getUidObject(), false);
      composeOidExtras("uid", element.getUidObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeIntegerCore("numberOfInstances", element.getNumberOfInstancesObject(), false);
      composeIntegerExtras("numberOfInstances", element.getNumberOfInstancesObject(), false);
      if (element.getAvailabilityObject() != null) {
        composeEnumerationCore("availability", element.getAvailabilityObject(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
        composeEnumerationExtras("availability", element.getAvailabilityObject(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
      }
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      composeCoding("bodySite", element.getBodySite());
      composeDateTimeCore("dateTime", element.getDateTimeObject(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeObject(), false);
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
      composeIntegerCore("number", element.getNumberObject(), false);
      composeIntegerExtras("number", element.getNumberObject(), false);
      composeOidCore("uid", element.getUidObject(), false);
      composeOidExtras("uid", element.getUidObject(), false);
      composeOidCore("sopclass", element.getSopclassObject(), false);
      composeOidExtras("sopclass", element.getSopclassObject(), false);
      composeStringCore("type", element.getTypeObject(), false);
      composeStringExtras("type", element.getTypeObject(), false);
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      composeReference("attachment", element.getAttachment());
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
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeReference("subject", element.getSubject());
      composeBooleanCore("refusedIndicator", element.getRefusedIndicatorObject(), false);
      composeBooleanExtras("refusedIndicator", element.getRefusedIndicatorObject(), false);
      composeBooleanCore("reported", element.getReportedObject(), false);
      composeBooleanExtras("reported", element.getReportedObject(), false);
      composeReference("performer", element.getPerformer());
      composeReference("requester", element.getRequester());
      composeReference("manufacturer", element.getManufacturer());
      composeReference("location", element.getLocation());
      composeStringCore("lotNumber", element.getLotNumberObject(), false);
      composeStringExtras("lotNumber", element.getLotNumberObject(), false);
      composeDateCore("expirationDate", element.getExpirationDateObject(), false);
      composeDateExtras("expirationDate", element.getExpirationDateObject(), false);
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
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("detail", element.getDetail());
      composeBooleanCore("reported", element.getReportedObject(), false);
      composeBooleanExtras("reported", element.getReportedObject(), false);
      close();
    }
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIntegerCore("doseSequence", element.getDoseSequenceObject(), false);
      composeIntegerExtras("doseSequence", element.getDoseSequenceObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeReference("authority", element.getAuthority());
      composeStringCore("series", element.getSeriesObject(), false);
      composeStringExtras("series", element.getSeriesObject(), false);
      composeIntegerCore("seriesDoses", element.getSeriesDosesObject(), false);
      composeIntegerExtras("seriesDoses", element.getSeriesDosesObject(), false);
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
      composeReference("subject", element.getSubject());
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
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeIntegerCore("doseNumber", element.getDoseNumberObject(), false);
      composeIntegerExtras("doseNumber", element.getDoseNumberObject(), false);
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
        for (Reference e : element.getSupportingImmunization()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getSupportingPatientInformation().size() > 0) {
        openArray("supportingPatientInformation");
        for (Reference e : element.getSupportingPatientInformation()) 
          composeReference(null, e);
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
      composeDateTimeCore("value", element.getValueObject(), false);
      composeDateTimeExtras("value", element.getValueObject(), false);
      close();
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIntegerCore("doseSequence", element.getDoseSequenceObject(), false);
      composeIntegerExtras("doseSequence", element.getDoseSequenceObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeReference("authority", element.getAuthority());
      composeStringCore("series", element.getSeriesObject(), false);
      composeStringExtras("series", element.getSeriesObject(), false);
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
      composeReference("subject", element.getSubject());
      composeReference("source", element.getSource());
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeBooleanCore("ordered", element.getOrderedObject(), false);
      composeBooleanExtras("ordered", element.getOrderedObject(), false);
      if (element.getModeObject() != null) {
        composeEnumerationCore("mode", element.getModeObject(), new List_.ListModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeObject(), new List_.ListModeEnumFactory(), false);
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
      composeBooleanCore("deleted", element.getDeletedObject(), false);
      composeBooleanExtras("deleted", element.getDeletedObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("item", element.getItem());
      close();
    }
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeCodeableConcept("type", element.getType());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("physicalType", element.getPhysicalType());
      composeLocationLocationPositionComponent("position", element.getPosition());
      composeReference("managingOrganization", element.getManagingOrganization());
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Location.LocationStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Location.LocationStatusEnumFactory(), false);
      }
      composeReference("partOf", element.getPartOf());
      if (element.getModeObject() != null) {
        composeEnumerationCore("mode", element.getModeObject(), new Location.LocationModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeObject(), new Location.LocationModeEnumFactory(), false);
      }
    }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeDecimalCore("longitude", element.getLongitudeObject(), false);
      composeDecimalExtras("longitude", element.getLongitudeObject(), false);
      composeDecimalCore("latitude", element.getLatitudeObject(), false);
      composeDecimalExtras("latitude", element.getLatitudeObject(), false);
      composeDecimalCore("altitude", element.getAltitudeObject(), false);
      composeDecimalExtras("altitude", element.getAltitudeObject(), false);
      close();
    }
  }

  private void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Media.MediaTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Media.MediaTypeEnumFactory(), false);
      }
      composeCodeableConcept("subtype", element.getSubtype());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("dateTime", element.getDateTimeObject(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeObject(), false);
      composeReference("subject", element.getSubject());
      composeReference("operator", element.getOperator());
      composeCodeableConcept("view", element.getView());
      composeStringCore("deviceName", element.getDeviceNameObject(), false);
      composeStringExtras("deviceName", element.getDeviceNameObject(), false);
      composeIntegerCore("height", element.getHeightObject(), false);
      composeIntegerExtras("height", element.getHeightObject(), false);
      composeIntegerCore("width", element.getWidthObject(), false);
      composeIntegerExtras("width", element.getWidthObject(), false);
      composeIntegerCore("frames", element.getFramesObject(), false);
      composeIntegerExtras("frames", element.getFramesObject(), false);
      composeIntegerCore("length", element.getLengthObject(), false);
      composeIntegerExtras("length", element.getLengthObject(), false);
      composeAttachment("content", element.getContent());
    }
  }

  private void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeCodeableConcept("code", element.getCode());
      composeBooleanCore("isBrand", element.getIsBrandObject(), false);
      composeBooleanExtras("isBrand", element.getIsBrandObject(), false);
      composeReference("manufacturer", element.getManufacturer());
      if (element.getKindObject() != null) {
        composeEnumerationCore("kind", element.getKindObject(), new Medication.MedicationKindEnumFactory(), false);
        composeEnumerationExtras("kind", element.getKindObject(), new Medication.MedicationKindEnumFactory(), false);
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
      composeReference("item", element.getItem());
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
      composeReference("item", element.getItem());
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new MedicationAdministration.MedicationAdminStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new MedicationAdministration.MedicationAdminStatusEnumFactory(), false);
      }
      composeReference("patient", element.getPatient());
      composeReference("practitioner", element.getPractitioner());
      composeReference("encounter", element.getEncounter());
      composeReference("prescription", element.getPrescription());
      composeBooleanCore("wasNotGiven", element.getWasNotGivenObject(), false);
      composeBooleanExtras("wasNotGiven", element.getWasNotGivenObject(), false);
      if (element.getReasonNotGiven().size() > 0) {
        openArray("reasonNotGiven");
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeType("effectiveTime", element.getEffectiveTime());
      composeReference("medication", element.getMedication());
      if (element.getDevice().size() > 0) {
        openArray("device");
        for (Reference e : element.getDevice()) 
          composeReference(null, e);
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
      }
      composeReference("patient", element.getPatient());
      composeReference("dispenser", element.getDispenser());
      if (element.getAuthorizingPrescription().size() > 0) {
        openArray("authorizingPrescription");
        for (Reference e : element.getAuthorizingPrescription()) 
          composeReference(null, e);
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
      }
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeReference("medication", element.getMedication());
      composeDateTimeCore("whenPrepared", element.getWhenPreparedObject(), false);
      composeDateTimeExtras("whenPrepared", element.getWhenPreparedObject(), false);
      composeDateTimeCore("whenHandedOver", element.getWhenHandedOverObject(), false);
      composeDateTimeExtras("whenHandedOver", element.getWhenHandedOverObject(), false);
      composeReference("destination", element.getDestination());
      if (element.getReceiver().size() > 0) {
        openArray("receiver");
        for (Reference e : element.getReceiver()) 
          composeReference(null, e);
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
      composeType("schedule", element.getSchedule());
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
        for (Reference e : element.getResponsibleParty()) 
          composeReference(null, e);
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
      composeDateTimeCore("dateWritten", element.getDateWrittenObject(), false);
      composeDateTimeExtras("dateWritten", element.getDateWrittenObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory(), false);
      }
      composeReference("patient", element.getPatient());
      composeReference("prescriber", element.getPrescriber());
      composeReference("encounter", element.getEncounter());
      composeType("reason", element.getReason());
      composeReference("medication", element.getMedication());
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
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("scheduled", element.getScheduled());
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
      composeReference("medication", element.getMedication());
      composePeriod("validityPeriod", element.getValidityPeriod());
      composeIntegerCore("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedObject(), false);
      composeIntegerExtras("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedObject(), false);
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
      composeReference("patient", element.getPatient());
      composeBooleanCore("wasNotGiven", element.getWasNotGivenObject(), false);
      composeBooleanExtras("wasNotGiven", element.getWasNotGivenObject(), false);
      if (element.getReasonNotGiven().size() > 0) {
        openArray("reasonNotGiven");
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("whenGiven", element.getWhenGiven());
      composeReference("medication", element.getMedication());
      if (element.getDevice().size() > 0) {
        openArray("device");
        for (Reference e : element.getDevice()) 
          composeReference(null, e);
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
      composeTiming("schedule", element.getSchedule());
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
      composeIdCore("identifier", element.getIdentifierObject(), false);
      composeIdExtras("identifier", element.getIdentifierObject(), false);
      composeInstantCore("timestamp", element.getTimestampObject(), false);
      composeInstantExtras("timestamp", element.getTimestampObject(), false);
      composeCoding("event", element.getEvent());
      composeMessageHeaderMessageHeaderResponseComponent("response", element.getResponse());
      composeMessageHeaderMessageSourceComponent("source", element.getSource());
      if (element.getDestination().size() > 0) {
        openArray("destination");
        for (MessageHeader.MessageDestinationComponent e : element.getDestination()) 
          composeMessageHeaderMessageDestinationComponent(null, e);
        closeArray();
      };
      composeReference("enterer", element.getEnterer());
      composeReference("author", element.getAuthor());
      composeReference("receiver", element.getReceiver());
      composeReference("responsible", element.getResponsible());
      composeCodeableConcept("reason", element.getReason());
      if (element.getData().size() > 0) {
        openArray("data");
        for (Reference e : element.getData()) 
          composeReference(null, e);
        closeArray();
      };
    }
  }

  private void composeMessageHeaderMessageHeaderResponseComponent(String name, MessageHeader.MessageHeaderResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("identifier", element.getIdentifierObject(), false);
      composeIdExtras("identifier", element.getIdentifierObject(), false);
      if (element.getCodeObject() != null) {
        composeEnumerationCore("code", element.getCodeObject(), new MessageHeader.ResponseCodeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeObject(), new MessageHeader.ResponseCodeEnumFactory(), false);
      }
      composeReference("details", element.getDetails());
      close();
    }
  }

  private void composeMessageHeaderMessageSourceComponent(String name, MessageHeader.MessageSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("software", element.getSoftwareObject(), false);
      composeStringExtras("software", element.getSoftwareObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeContactPoint("contact", element.getContact());
      composeUriCore("endpoint", element.getEndpointObject(), false);
      composeUriExtras("endpoint", element.getEndpointObject(), false);
      close();
    }
  }

  private void composeMessageHeaderMessageDestinationComponent(String name, MessageHeader.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeReference("target", element.getTarget());
      composeUriCore("endpoint", element.getEndpointObject(), false);
      composeUriExtras("endpoint", element.getEndpointObject(), false);
      close();
    }
  }

  private void composeNamespace(String name, Namespace element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Namespace.NamespaceTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Namespace.NamespaceTypeEnumFactory(), false);
      }
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Namespace.NamespaceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Namespace.NamespaceStatusEnumFactory(), false);
      }
      composeCodeCore("country", element.getCountryObject(), false);
      composeCodeExtras("country", element.getCountryObject(), false);
      composeCodeableConcept("category", element.getCategory());
      composeStringCore("responsible", element.getResponsibleObject(), false);
      composeStringExtras("responsible", element.getResponsibleObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeStringCore("usage", element.getUsageObject(), false);
      composeStringExtras("usage", element.getUsageObject(), false);
      if (element.getUniqueId().size() > 0) {
        openArray("uniqueId");
        for (Namespace.NamespaceUniqueIdComponent e : element.getUniqueId()) 
          composeNamespaceNamespaceUniqueIdComponent(null, e);
        closeArray();
      };
      composeNamespaceNamespaceContactComponent("contact", element.getContact());
      composeReference("replacedBy", element.getReplacedBy());
    }
  }

  private void composeNamespaceNamespaceUniqueIdComponent(String name, Namespace.NamespaceUniqueIdComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Namespace.NamespaceIdentifierTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Namespace.NamespaceIdentifierTypeEnumFactory(), false);
      }
      composeStringCore("value", element.getValueObject(), false);
      composeStringExtras("value", element.getValueObject(), false);
      composeBooleanCore("preferred", element.getPreferredObject(), false);
      composeBooleanExtras("preferred", element.getPreferredObject(), false);
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
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeNutritionOrder(String name, NutritionOrder element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeReference("subject", element.getSubject());
      composeReference("orderer", element.getOrderer());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
      composeDateTimeCore("dateTime", element.getDateTimeObject(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeObject(), false);
      if (element.getAllergyIntolerance().size() > 0) {
        openArray("allergyIntolerance");
        for (Reference e : element.getAllergyIntolerance()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getFoodPreferenceModifier().size() > 0) {
        openArray("foodPreferenceModifier");
        for (CodeableConcept e : element.getFoodPreferenceModifier()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getExcludeFoodModifier().size() > 0) {
        openArray("excludeFoodModifier");
        for (CodeableConcept e : element.getExcludeFoodModifier()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getItem().size() > 0) {
        openArray("item");
        for (NutritionOrder.NutritionOrderItemComponent e : element.getItem()) 
          composeNutritionOrderNutritionOrderItemComponent(null, e);
        closeArray();
      };
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new NutritionOrder.NutritionOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new NutritionOrder.NutritionOrderStatusEnumFactory(), false);
      }
    }
  }

  private void composeNutritionOrderNutritionOrderItemComponent(String name, NutritionOrder.NutritionOrderItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeType("scheduled", element.getScheduled());
      composeBooleanCore("isInEffect", element.getIsInEffectObject(), false);
      composeBooleanExtras("isInEffect", element.getIsInEffectObject(), false);
      composeNutritionOrderNutritionOrderItemOralDietComponent("oralDiet", element.getOralDiet());
      composeNutritionOrderNutritionOrderItemSupplementComponent("supplement", element.getSupplement());
      composeNutritionOrderNutritionOrderItemEnteralFormulaComponent("enteralFormula", element.getEnteralFormula());
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietComponent(String name, NutritionOrder.NutritionOrderItemOralDietComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (CodeableConcept e : element.getCode()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getNutrientModifier().size() > 0) {
        openArray("nutrientModifier");
        for (CodeableConcept e : element.getNutrientModifier()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeType("nutrientAmount", element.getNutrientAmount());
      if (element.getTextureModifier().size() > 0) {
        openArray("textureModifier");
        for (CodeableConcept e : element.getTextureModifier()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getFoodType().size() > 0) {
        openArray("foodType");
        for (CodeableConcept e : element.getFoodType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getFluidConsistencyType().size() > 0) {
        openArray("fluidConsistencyType");
        for (CodeableConcept e : element.getFluidConsistencyType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemSupplementComponent(String name, NutritionOrder.NutritionOrderItemSupplementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeQuantity("quantity", element.getQuantity());
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemEnteralFormulaComponent(String name, NutritionOrder.NutritionOrderItemEnteralFormulaComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("baseFormulaType", element.getBaseFormulaType());
      if (element.getAdditiveType().size() > 0) {
        openArray("additiveType");
        for (CodeableConcept e : element.getAdditiveType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getCaloricDensity().size() > 0) {
        openArray("caloricDensity");
        for (Quantity e : element.getCaloricDensity()) 
          composeQuantity(null, e);
        closeArray();
      };
      if (element.getRouteofAdministration().size() > 0) {
        openArray("routeofAdministration");
        for (CodeableConcept e : element.getRouteofAdministration()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getRate().size() > 0) {
        openArray("rate");
        for (Quantity e : element.getRate()) 
          composeQuantity(null, e);
        closeArray();
      };
      composeStringCore("baseFormulaName", element.getBaseFormulaNameObject(), false);
      composeStringExtras("baseFormulaName", element.getBaseFormulaNameObject(), false);
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
      composeStringCore("comments", element.getCommentsObject(), false);
      composeStringExtras("comments", element.getCommentsObject(), false);
      composeType("applies", element.getApplies());
      composeInstantCore("issued", element.getIssuedObject(), false);
      composeInstantExtras("issued", element.getIssuedObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Observation.ObservationStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Observation.ObservationStatusEnumFactory(), false);
      }
      if (element.getReliabilityObject() != null) {
        composeEnumerationCore("reliability", element.getReliabilityObject(), new Observation.ObservationReliabilityEnumFactory(), false);
        composeEnumerationExtras("reliability", element.getReliabilityObject(), new Observation.ObservationReliabilityEnumFactory(), false);
      }
      composeCodeableConcept("bodySite", element.getBodySite());
      composeCodeableConcept("method", element.getMethod());
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("subject", element.getSubject());
      composeReference("specimen", element.getSpecimen());
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (Reference e : element.getPerformer()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
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
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      close();
    }
  }

  private void composeObservationObservationRelatedComponent(String name, Observation.ObservationRelatedComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Observation.ObservationRelationshiptypesEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Observation.ObservationRelationshiptypesEnumFactory(), false);
      }
      composeReference("target", element.getTarget());
      close();
    }
  }

  private void composeOperationDefinition(String name, OperationDefinition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeUriCore("identifier", element.getIdentifierObject(), false);
      composeUriExtras("identifier", element.getIdentifierObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new OperationDefinition.ResourceProfileStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new OperationDefinition.ResourceProfileStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalObject(), false);
      composeBooleanExtras("experimental", element.getExperimentalObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      if (element.getKindObject() != null) {
        composeEnumerationCore("kind", element.getKindObject(), new OperationDefinition.OperationKindEnumFactory(), false);
        composeEnumerationExtras("kind", element.getKindObject(), new OperationDefinition.OperationKindEnumFactory(), false);
      }
      composeCodeCore("name", element.getNameObject(), false);
      composeCodeExtras("name", element.getNameObject(), false);
      composeStringCore("notes", element.getNotesObject(), false);
      composeStringExtras("notes", element.getNotesObject(), false);
      composeReference("base", element.getBase());
      composeBooleanCore("system", element.getSystemObject(), false);
      composeBooleanExtras("system", element.getSystemObject(), false);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeType e : element.getType()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getType())) {
          openArray("_type");
          for (CodeType e : element.getType()) 
            composeCodeExtras(null, e, true);
          closeArray();
        }
      };
      composeBooleanCore("instance", element.getInstanceObject(), false);
      composeBooleanExtras("instance", element.getInstanceObject(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (OperationDefinition.OperationDefinitionParameterComponent e : element.getParameter()) 
          composeOperationDefinitionOperationDefinitionParameterComponent(null, e);
        closeArray();
      };
    }
  }

  private void composeOperationDefinitionOperationDefinitionParameterComponent(String name, OperationDefinition.OperationDefinitionParameterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("name", element.getNameObject(), false);
      composeCodeExtras("name", element.getNameObject(), false);
      if (element.getUseObject() != null) {
        composeEnumerationCore("use", element.getUseObject(), new OperationDefinition.OperationParameterUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseObject(), new OperationDefinition.OperationParameterUseEnumFactory(), false);
      }
      composeIntegerCore("min", element.getMinObject(), false);
      composeIntegerExtras("min", element.getMinObject(), false);
      composeStringCore("max", element.getMaxObject(), false);
      composeStringExtras("max", element.getMaxObject(), false);
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      composeCoding("type", element.getType());
      composeReference("profile", element.getProfile());
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
      if (element.getSeverityObject() != null) {
        composeEnumerationCore("severity", element.getSeverityObject(), new OperationOutcome.IssueSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverityObject(), new OperationOutcome.IssueSeverityEnumFactory(), false);
      }
      composeCoding("type", element.getType());
      composeStringCore("details", element.getDetailsObject(), false);
      composeStringExtras("details", element.getDetailsObject(), false);
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (StringType e : element.getLocation()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getLocation())) {
          openArray("_location");
          for (StringType e : element.getLocation()) 
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
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("subject", element.getSubject());
      composeReference("source", element.getSource());
      composeReference("target", element.getTarget());
      composeType("reason", element.getReason());
      composeReference("authority", element.getAuthority());
      composeOrderOrderWhenComponent("when", element.getWhen());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (Reference e : element.getDetail()) 
          composeReference(null, e);
        closeArray();
      };
    }
  }

  private void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeTiming("schedule", element.getSchedule());
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
      composeReference("request", element.getRequest());
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("who", element.getWho());
      composeType("authority", element.getAuthority());
      if (element.getCodeObject() != null) {
        composeEnumerationCore("code", element.getCodeObject(), new OrderResponse.OrderOutcomeCodeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeObject(), new OrderResponse.OrderOutcomeCodeEnumFactory(), false);
      }
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getFulfillment().size() > 0) {
        openArray("fulfillment");
        for (Reference e : element.getFulfillment()) 
          composeReference(null, e);
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
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeCodeableConcept("type", element.getType());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getAddress().size() > 0) {
        openArray("address");
        for (Address e : element.getAddress()) 
          composeAddress(null, e);
        closeArray();
      };
      composeReference("partOf", element.getPartOf());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Organization.OrganizationContactComponent e : element.getContact()) 
          composeOrganizationOrganizationContactComponent(null, e);
        closeArray();
      };
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (Reference e : element.getLocation()) 
          composeReference(null, e);
        closeArray();
      };
      composeBooleanCore("active", element.getActiveObject(), false);
      composeBooleanExtras("active", element.getActiveObject(), false);
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
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      if (element.getGenderObject() != null) {
        composeEnumerationCore("gender", element.getGenderObject(), new Organization.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderObject(), new Organization.AdministrativeGenderEnumFactory(), false);
      }
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
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDateCore("created", element.getCreatedObject(), false);
      composeDateExtras("created", element.getCreatedObject(), false);
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
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getGenderObject() != null) {
        composeEnumerationCore("gender", element.getGenderObject(), new Patient.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderObject(), new Patient.AdministrativeGenderEnumFactory(), false);
      }
      composeDateTimeCore("birthDate", element.getBirthDateObject(), false);
      composeDateTimeExtras("birthDate", element.getBirthDateObject(), false);
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
        for (Reference e : element.getCareProvider()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("managingOrganization", element.getManagingOrganization());
      if (element.getLink().size() > 0) {
        openArray("link");
        for (Patient.PatientLinkComponent e : element.getLink()) 
          composePatientPatientLinkComponent(null, e);
        closeArray();
      };
      composeBooleanCore("active", element.getActiveObject(), false);
      composeBooleanExtras("active", element.getActiveObject(), false);
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
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      if (element.getGenderObject() != null) {
        composeEnumerationCore("gender", element.getGenderObject(), new Patient.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderObject(), new Patient.AdministrativeGenderEnumFactory(), false);
      }
      composeReference("organization", element.getOrganization());
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
      composeReference("other", element.getOther());
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Patient.LinkTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Patient.LinkTypeEnumFactory(), false);
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
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getAddress().size() > 0) {
        openArray("address");
        for (Address e : element.getAddress()) 
          composeAddress(null, e);
        closeArray();
      };
      if (element.getGenderObject() != null) {
        composeEnumerationCore("gender", element.getGenderObject(), new Practitioner.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderObject(), new Practitioner.AdministrativeGenderEnumFactory(), false);
      }
      composeDateTimeCore("birthDate", element.getBirthDateObject(), false);
      composeDateTimeExtras("birthDate", element.getBirthDateObject(), false);
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
      composeReference("organization", element.getOrganization());
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
        for (Reference e : element.getLocation()) 
          composeReference(null, e);
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
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composePeriod("period", element.getPeriod());
      composeReference("issuer", element.getIssuer());
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
      composeReference("subject", element.getSubject());
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
      composeReference("encounter", element.getEncounter());
      composeStringCore("outcome", element.getOutcomeObject(), false);
      composeStringExtras("outcome", element.getOutcomeObject(), false);
      if (element.getReport().size() > 0) {
        openArray("report");
        for (Reference e : element.getReport()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getComplication().size() > 0) {
        openArray("complication");
        for (CodeableConcept e : element.getComplication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("followUp", element.getFollowUpObject(), false);
      composeStringExtras("followUp", element.getFollowUpObject(), false);
      if (element.getRelatedItem().size() > 0) {
        openArray("relatedItem");
        for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
          composeProcedureProcedureRelatedItemComponent(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotesObject(), false);
      composeStringExtras("notes", element.getNotesObject(), false);
    }
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
      close();
    }
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Procedure.ProcedureRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Procedure.ProcedureRelationshipTypeEnumFactory(), false);
      }
      composeReference("target", element.getTarget());
      close();
    }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Profile.ResourceProfileStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Profile.ResourceProfileStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalObject(), false);
      composeBooleanExtras("experimental", element.getExperimentalObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeStringCore("requirements", element.getRequirementsObject(), false);
      composeStringExtras("requirements", element.getRequirementsObject(), false);
      composeIdCore("fhirVersion", element.getFhirVersionObject(), false);
      composeIdExtras("fhirVersion", element.getFhirVersionObject(), false);
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
    }
  }

  private void composeProfileProfileMappingComponent(String name, Profile.ProfileMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("identity", element.getIdentityObject(), false);
      composeIdExtras("identity", element.getIdentityObject(), false);
      composeUriCore("uri", element.getUriObject(), false);
      composeUriExtras("uri", element.getUriObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("comments", element.getCommentsObject(), false);
      composeStringExtras("comments", element.getCommentsObject(), false);
      close();
    }
  }

  private void composeProfileProfileStructureComponent(String name, Profile.ProfileStructureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("type", element.getTypeObject(), false);
      composeCodeExtras("type", element.getTypeObject(), false);
      composeUriCore("base", element.getBaseObject(), false);
      composeUriExtras("base", element.getBaseObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeBooleanCore("publish", element.getPublishObject(), false);
      composeBooleanExtras("publish", element.getPublishObject(), false);
      composeStringCore("purpose", element.getPurposeObject(), false);
      composeStringExtras("purpose", element.getPurposeObject(), false);
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
      composeStringCore("path", element.getPathObject(), false);
      composeStringExtras("path", element.getPathObject(), false);
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
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeProfileElementSlicingComponent("slicing", element.getSlicing());
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      close();
    }
  }

  private void composeProfileElementSlicingComponent(String name, Profile.ElementSlicingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("discriminator", element.getDiscriminatorObject(), false);
      composeIdExtras("discriminator", element.getDiscriminatorObject(), false);
      composeBooleanCore("ordered", element.getOrderedObject(), false);
      composeBooleanExtras("ordered", element.getOrderedObject(), false);
      if (element.getRulesObject() != null) {
        composeEnumerationCore("rules", element.getRulesObject(), new Profile.ResourceSlicingRulesEnumFactory(), false);
        composeEnumerationExtras("rules", element.getRulesObject(), new Profile.ResourceSlicingRulesEnumFactory(), false);
      }
      close();
    }
  }

  private void composeProfileElementDefinitionComponent(String name, Profile.ElementDefinitionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("short", element.getShortObject(), false);
      composeStringExtras("short", element.getShortObject(), false);
      composeStringCore("formal", element.getFormalObject(), false);
      composeStringExtras("formal", element.getFormalObject(), false);
      composeStringCore("comments", element.getCommentsObject(), false);
      composeStringExtras("comments", element.getCommentsObject(), false);
      composeStringCore("requirements", element.getRequirementsObject(), false);
      composeStringExtras("requirements", element.getRequirementsObject(), false);
      if (element.getSynonym().size() > 0) {
        openArray("synonym");
        for (StringType e : element.getSynonym()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSynonym())) {
          openArray("_synonym");
          for (StringType e : element.getSynonym()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeIntegerCore("min", element.getMinObject(), false);
      composeIntegerExtras("min", element.getMinObject(), false);
      composeStringCore("max", element.getMaxObject(), false);
      composeStringExtras("max", element.getMaxObject(), false);
      if (element.getType().size() > 0) {
        openArray("type");
        for (Profile.TypeRefComponent e : element.getType()) 
          composeProfileTypeRefComponent(null, e);
        closeArray();
      };
      composeStringCore("nameReference", element.getNameReferenceObject(), false);
      composeStringExtras("nameReference", element.getNameReferenceObject(), false);
      composeType("value", element.getValue());
      composeType("example", element.getExample());
      composeIntegerCore("maxLength", element.getMaxLengthObject(), false);
      composeIntegerExtras("maxLength", element.getMaxLengthObject(), false);
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (IdType e : element.getCondition()) 
          composeIdCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getCondition())) {
          openArray("_condition");
          for (IdType e : element.getCondition()) 
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
      composeBooleanCore("mustSupport", element.getMustSupportObject(), false);
      composeBooleanExtras("mustSupport", element.getMustSupportObject(), false);
      composeBooleanCore("isModifier", element.getIsModifierObject(), false);
      composeBooleanExtras("isModifier", element.getIsModifierObject(), false);
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
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      composeUriCore("profile", element.getProfileObject(), false);
      composeUriExtras("profile", element.getProfileObject(), false);
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
      composeIdCore("key", element.getKeyObject(), false);
      composeIdExtras("key", element.getKeyObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      if (element.getSeverityObject() != null) {
        composeEnumerationCore("severity", element.getSeverityObject(), new Profile.ConstraintSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverityObject(), new Profile.ConstraintSeverityEnumFactory(), false);
      }
      composeStringCore("human", element.getHumanObject(), false);
      composeStringExtras("human", element.getHumanObject(), false);
      composeStringCore("xpath", element.getXpathObject(), false);
      composeStringExtras("xpath", element.getXpathObject(), false);
      close();
    }
  }

  private void composeProfileElementDefinitionBindingComponent(String name, Profile.ElementDefinitionBindingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeBooleanCore("isExtensible", element.getIsExtensibleObject(), false);
      composeBooleanExtras("isExtensible", element.getIsExtensibleObject(), false);
      if (element.getConformanceObject() != null) {
        composeEnumerationCore("conformance", element.getConformanceObject(), new Profile.BindingConformanceEnumFactory(), false);
        composeEnumerationExtras("conformance", element.getConformanceObject(), new Profile.BindingConformanceEnumFactory(), false);
      }
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeType("reference", element.getReference());
      close();
    }
  }

  private void composeProfileElementDefinitionMappingComponent(String name, Profile.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdCore("identity", element.getIdentityObject(), false);
      composeIdExtras("identity", element.getIdentityObject(), false);
      composeStringCore("map", element.getMapObject(), false);
      composeStringExtras("map", element.getMapObject(), false);
      close();
    }
  }

  private void composeProfileProfileStructureSearchParamComponent(String name, Profile.ProfileStructureSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Profile.SearchParamTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Profile.SearchParamTypeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationObject(), false);
      composeStringExtras("documentation", element.getDocumentationObject(), false);
      composeStringCore("xpath", element.getXpathObject(), false);
      composeStringExtras("xpath", element.getXpathObject(), false);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (CodeType e : element.getTarget()) 
          composeCodeCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getTarget())) {
          openArray("_target");
          for (CodeType e : element.getTarget()) 
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
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
      if (element.getContextTypeObject() != null) {
        composeEnumerationCore("contextType", element.getContextTypeObject(), new Profile.ExtensionContextEnumFactory(), false);
        composeEnumerationExtras("contextType", element.getContextTypeObject(), new Profile.ExtensionContextEnumFactory(), false);
      }
      if (element.getContext().size() > 0) {
        openArray("context");
        for (StringType e : element.getContext()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getContext())) {
          openArray("_context");
          for (StringType e : element.getContext()) 
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

  private void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Reference e : element.getTarget()) 
          composeReference(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeInstantCore("recorded", element.getRecordedObject(), false);
      composeInstantExtras("recorded", element.getRecordedObject(), false);
      composeCodeableConcept("reason", element.getReason());
      composeReference("location", element.getLocation());
      if (element.getPolicy().size() > 0) {
        openArray("policy");
        for (UriType e : element.getPolicy()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getPolicy())) {
          openArray("_policy");
          for (UriType e : element.getPolicy()) 
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
      composeStringCore("integritySignature", element.getIntegritySignatureObject(), false);
      composeStringExtras("integritySignature", element.getIntegritySignatureObject(), false);
    }
  }

  private void composeProvenanceProvenanceAgentComponent(String name, Provenance.ProvenanceAgentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCoding("role", element.getRole());
      composeCoding("type", element.getType());
      composeUriCore("reference", element.getReferenceObject(), false);
      composeUriExtras("reference", element.getReferenceObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
      close();
    }
  }

  private void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getRoleObject() != null) {
        composeEnumerationCore("role", element.getRoleObject(), new Provenance.ProvenanceEntityRoleEnumFactory(), false);
        composeEnumerationExtras("role", element.getRoleObject(), new Provenance.ProvenanceEntityRoleEnumFactory(), false);
      }
      composeCoding("type", element.getType());
      composeUriCore("reference", element.getReferenceObject(), false);
      composeUriExtras("reference", element.getReferenceObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
      composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
      close();
    }
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeUriCore("identifier", element.getIdentifierObject(), false);
      composeUriExtras("identifier", element.getIdentifierObject(), false);
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
      composeUriCore("identifier", element.getIdentifierObject(), false);
      composeUriExtras("identifier", element.getIdentifierObject(), false);
      if (element.getOutcomeObject() != null) {
        composeEnumerationCore("outcome", element.getOutcomeObject(), new Query.QueryOutcomeEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcomeObject(), new Query.QueryOutcomeEnumFactory(), false);
      }
      composeIntegerCore("total", element.getTotalObject(), false);
      composeIntegerExtras("total", element.getTotalObject(), false);
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
        for (Reference e : element.getReference()) 
          composeReference(null, e);
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
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Questionnaire.QuestionnaireStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Questionnaire.QuestionnaireStatusEnumFactory(), false);
      }
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      composeQuestionnaireGroupComponent("group", element.getGroup());
    }
  }

  private void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkIdObject(), false);
      composeStringExtras("linkId", element.getLinkIdObject(), false);
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (Coding e : element.getConcept()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      composeBooleanCore("required", element.getRequiredObject(), false);
      composeBooleanExtras("required", element.getRequiredObject(), false);
      composeBooleanCore("repeats", element.getRepeatsObject(), false);
      composeBooleanExtras("repeats", element.getRepeatsObject(), false);
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
      composeStringCore("linkId", element.getLinkIdObject(), false);
      composeStringExtras("linkId", element.getLinkIdObject(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (Coding e : element.getConcept()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Questionnaire.AnswerFormatEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Questionnaire.AnswerFormatEnumFactory(), false);
      }
      composeBooleanCore("required", element.getRequiredObject(), false);
      composeBooleanExtras("required", element.getRequiredObject(), false);
      composeBooleanCore("repeats", element.getRepeatsObject(), false);
      composeBooleanExtras("repeats", element.getRepeatsObject(), false);
      composeReference("options", element.getOptions());
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
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("questionnaire", element.getQuestionnaire());
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory(), false);
      }
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDateTimeCore("authored", element.getAuthoredObject(), false);
      composeDateTimeExtras("authored", element.getAuthoredObject(), false);
      composeReference("source", element.getSource());
      composeReference("encounter", element.getEncounter());
      composeQuestionnaireAnswersGroupComponent("group", element.getGroup());
    }
  }

  private void composeQuestionnaireAnswersGroupComponent(String name, QuestionnaireAnswers.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkIdObject(), false);
      composeStringExtras("linkId", element.getLinkIdObject(), false);
      composeStringCore("title", element.getTitleObject(), false);
      composeStringExtras("title", element.getTitleObject(), false);
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
      composeReference("subject", element.getSubject());
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
      composeStringCore("linkId", element.getLinkIdObject(), false);
      composeStringExtras("linkId", element.getLinkIdObject(), false);
      composeStringCore("text", element.getTextObject(), false);
      composeStringExtras("text", element.getTextObject(), false);
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

  private void composeReferralRequest(String name, ReferralRequest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new ReferralRequest.ReferralstatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new ReferralRequest.ReferralstatusEnumFactory(), false);
      }
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("specialty", element.getSpecialty());
      composeCodeableConcept("priority", element.getPriority());
      composeReference("subject", element.getSubject());
      composeReference("requester", element.getRequester());
      if (element.getRecipient().size() > 0) {
        openArray("recipient");
        for (Reference e : element.getRecipient()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
      composeDateTimeCore("dateSent", element.getDateSentObject(), false);
      composeDateTimeExtras("dateSent", element.getDateSentObject(), false);
      composeCodeableConcept("reason", element.getReason());
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      if (element.getServiceRequested().size() > 0) {
        openArray("serviceRequested");
        for (CodeableConcept e : element.getServiceRequested()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getSupportingInformation().size() > 0) {
        openArray("supportingInformation");
        for (Reference e : element.getSupportingInformation()) 
          composeReference(null, e);
        closeArray();
      };
      composePeriod("fulfillmentTime", element.getFulfillmentTime());
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
      composeReference("patient", element.getPatient());
      composeCodeableConcept("relationship", element.getRelationship());
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getGenderObject() != null) {
        composeEnumerationCore("gender", element.getGenderObject(), new RelatedPerson.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderObject(), new RelatedPerson.AdministrativeGenderEnumFactory(), false);
      }
      composeAddress("address", element.getAddress());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
    }
  }

  private void composeRiskAssessment(String name, RiskAssessment element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeReference("subject", element.getSubject());
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeReference("condition", element.getCondition());
      composeReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("method", element.getMethod());
      if (element.getBasis().size() > 0) {
        openArray("basis");
        for (Reference e : element.getBasis()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getPrediction().size() > 0) {
        openArray("prediction");
        for (RiskAssessment.RiskAssessmentPredictionComponent e : element.getPrediction()) 
          composeRiskAssessmentRiskAssessmentPredictionComponent(null, e);
        closeArray();
      };
      composeStringCore("mitigation", element.getMitigationObject(), false);
      composeStringExtras("mitigation", element.getMitigationObject(), false);
    }
  }

  private void composeRiskAssessmentRiskAssessmentPredictionComponent(String name, RiskAssessment.RiskAssessmentPredictionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("probability", element.getProbability());
      composeDecimalCore("relativeRisk", element.getRelativeRiskObject(), false);
      composeDecimalExtras("relativeRisk", element.getRelativeRiskObject(), false);
      composeType("when", element.getWhen());
      composeStringCore("rationale", element.getRationaleObject(), false);
      composeStringExtras("rationale", element.getRationaleObject(), false);
      close();
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
      if (element.getActionObject() != null) {
        composeEnumerationCore("action", element.getActionObject(), new SecurityEvent.SecurityEventActionEnumFactory(), false);
        composeEnumerationExtras("action", element.getActionObject(), new SecurityEvent.SecurityEventActionEnumFactory(), false);
      }
      composeInstantCore("dateTime", element.getDateTimeObject(), false);
      composeInstantExtras("dateTime", element.getDateTimeObject(), false);
      if (element.getOutcomeObject() != null) {
        composeEnumerationCore("outcome", element.getOutcomeObject(), new SecurityEvent.SecurityEventOutcomeEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcomeObject(), new SecurityEvent.SecurityEventOutcomeEnumFactory(), false);
      }
      composeStringCore("outcomeDesc", element.getOutcomeDescObject(), false);
      composeStringExtras("outcomeDesc", element.getOutcomeDescObject(), false);
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
      composeReference("reference", element.getReference());
      composeStringCore("userId", element.getUserIdObject(), false);
      composeStringExtras("userId", element.getUserIdObject(), false);
      composeStringCore("altId", element.getAltIdObject(), false);
      composeStringExtras("altId", element.getAltIdObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeBooleanCore("requestor", element.getRequestorObject(), false);
      composeBooleanExtras("requestor", element.getRequestorObject(), false);
      composeCoding("media", element.getMedia());
      composeSecurityEventSecurityEventParticipantNetworkComponent("network", element.getNetwork());
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponent(String name, SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("identifier", element.getIdentifierObject(), false);
      composeStringExtras("identifier", element.getIdentifierObject(), false);
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new SecurityEvent.NetworkTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new SecurityEvent.NetworkTypeEnumFactory(), false);
      }
      close();
    }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeStringCore("site", element.getSiteObject(), false);
      composeStringExtras("site", element.getSiteObject(), false);
      composeStringCore("identifier", element.getIdentifierObject(), false);
      composeStringExtras("identifier", element.getIdentifierObject(), false);
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
      composeReference("reference", element.getReference());
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new SecurityEvent.ObjectTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new SecurityEvent.ObjectTypeEnumFactory(), false);
      }
      if (element.getRoleObject() != null) {
        composeEnumerationCore("role", element.getRoleObject(), new SecurityEvent.ObjectRoleEnumFactory(), false);
        composeEnumerationExtras("role", element.getRoleObject(), new SecurityEvent.ObjectRoleEnumFactory(), false);
      }
      if (element.getLifecycleObject() != null) {
        composeEnumerationCore("lifecycle", element.getLifecycleObject(), new SecurityEvent.ObjectLifecycleEnumFactory(), false);
        composeEnumerationExtras("lifecycle", element.getLifecycleObject(), new SecurityEvent.ObjectLifecycleEnumFactory(), false);
      }
      composeCodeableConcept("sensitivity", element.getSensitivity());
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeBase64BinaryCore("query", element.getQueryObject(), false);
      composeBase64BinaryExtras("query", element.getQueryObject(), false);
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
      composeStringCore("type", element.getTypeObject(), false);
      composeStringExtras("type", element.getTypeObject(), false);
      composeBase64BinaryCore("value", element.getValueObject(), false);
      composeBase64BinaryExtras("value", element.getValueObject(), false);
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
      composeReference("availability", element.getAvailability());
      if (element.getFreeBusyTypeObject() != null) {
        composeEnumerationCore("freeBusyType", element.getFreeBusyTypeObject(), new Slot.SlotstatusEnumFactory(), false);
        composeEnumerationExtras("freeBusyType", element.getFreeBusyTypeObject(), new Slot.SlotstatusEnumFactory(), false);
      }
      composeInstantCore("start", element.getStartObject(), false);
      composeInstantExtras("start", element.getStartObject(), false);
      composeInstantCore("end", element.getEndObject(), false);
      composeInstantExtras("end", element.getEndObject(), false);
      composeBooleanCore("overbooked", element.getOverbookedObject(), false);
      composeBooleanExtras("overbooked", element.getOverbookedObject(), false);
      composeStringCore("comment", element.getCommentObject(), false);
      composeStringExtras("comment", element.getCommentObject(), false);
      composeDateTimeCore("lastModified", element.getLastModifiedObject(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedObject(), false);
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
      composeReference("subject", element.getSubject());
      composeIdentifier("accessionIdentifier", element.getAccessionIdentifier());
      composeDateTimeCore("receivedTime", element.getReceivedTimeObject(), false);
      composeDateTimeExtras("receivedTime", element.getReceivedTimeObject(), false);
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
      if (element.getRelationshipObject() != null) {
        composeEnumerationCore("relationship", element.getRelationshipObject(), new Specimen.HierarchicalRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("relationship", element.getRelationshipObject(), new Specimen.HierarchicalRelationshipTypeEnumFactory(), false);
      }
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Reference e : element.getTarget()) 
          composeReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeReference("collector", element.getCollector());
      if (element.getComment().size() > 0) {
        openArray("comment");
        for (StringType e : element.getComment()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getComment())) {
          openArray("_comment");
          for (StringType e : element.getComment()) 
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
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeCodeableConcept("procedure", element.getProcedure());
      if (element.getAdditive().size() > 0) {
        openArray("additive");
        for (Reference e : element.getAdditive()) 
          composeReference(null, e);
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
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeCodeableConcept("type", element.getType());
      composeQuantity("capacity", element.getCapacity());
      composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      composeReference("additive", element.getAdditive());
      close();
    }
  }

  private void composeSubscription(String name, Subscription element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeStringCore("criteria", element.getCriteriaObject(), false);
      composeStringExtras("criteria", element.getCriteriaObject(), false);
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (ContactPoint e : element.getContact()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("reason", element.getReasonObject(), false);
      composeStringExtras("reason", element.getReasonObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Subscription.SubscriptionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Subscription.SubscriptionStatusEnumFactory(), false);
      }
      composeStringCore("error", element.getErrorObject(), false);
      composeStringExtras("error", element.getErrorObject(), false);
      composeSubscriptionSubscriptionChannelComponent("channel", element.getChannel());
      composeInstantCore("end", element.getEndObject(), false);
      composeInstantExtras("end", element.getEndObject(), false);
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
      if (element.getTypeObject() != null) {
        composeEnumerationCore("type", element.getTypeObject(), new Subscription.SubscriptionChannelTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeObject(), new Subscription.SubscriptionChannelTypeEnumFactory(), false);
      }
      composeUriCore("url", element.getUrlObject(), false);
      composeUriExtras("url", element.getUrlObject(), false);
      composeStringCore("payload", element.getPayloadObject(), false);
      composeStringExtras("payload", element.getPayloadObject(), false);
      composeStringCore("header", element.getHeaderObject(), false);
      composeStringExtras("header", element.getHeaderObject(), false);
      close();
    }
  }

  private void composeSubscriptionSubscriptionTagComponent(String name, Subscription.SubscriptionTagComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("term", element.getTermObject(), false);
      composeUriExtras("term", element.getTermObject(), false);
      composeUriCore("scheme", element.getSchemeObject(), false);
      composeUriExtras("scheme", element.getSchemeObject(), false);
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      close();
    }
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
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
      composeDateTimeCore("expiry", element.getExpiryObject(), false);
      composeDateTimeExtras("expiry", element.getExpiryObject(), false);
      composeQuantity("quantity", element.getQuantity());
      close();
    }
  }

  private void composeSubstanceSubstanceIngredientComponent(String name, Substance.SubstanceIngredientComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeRatio("quantity", element.getQuantity());
      composeReference("substance", element.getSubstance());
      close();
    }
  }

  private void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeCodeableConcept("kind", element.getKind());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Supply.ValuesetSupplyStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Supply.ValuesetSupplyStatusEnumFactory(), false);
      }
      composeReference("orderedItem", element.getOrderedItem());
      composeReference("patient", element.getPatient());
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
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new Supply.ValuesetSupplyDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new Supply.ValuesetSupplyDispenseStatusEnumFactory(), false);
      }
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeReference("suppliedItem", element.getSuppliedItem());
      composeReference("supplier", element.getSupplier());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeReference("destination", element.getDestination());
      if (element.getReceiver().size() > 0) {
        openArray("receiver");
        for (Reference e : element.getReceiver()) 
          composeReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeResourceElements(element);
      composeUriCore("identifier", element.getIdentifierObject(), false);
      composeUriExtras("identifier", element.getIdentifierObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeStringCore("name", element.getNameObject(), false);
      composeStringExtras("name", element.getNameObject(), false);
      composeStringCore("purpose", element.getPurposeObject(), false);
      composeStringExtras("purpose", element.getPurposeObject(), false);
      composeBooleanCore("immutable", element.getImmutableObject(), false);
      composeBooleanExtras("immutable", element.getImmutableObject(), false);
      composeStringCore("publisher", element.getPublisherObject(), false);
      composeStringExtras("publisher", element.getPublisherObject(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionObject(), false);
      composeStringExtras("description", element.getDescriptionObject(), false);
      composeStringCore("copyright", element.getCopyrightObject(), false);
      composeStringExtras("copyright", element.getCopyrightObject(), false);
      if (element.getStatusObject() != null) {
        composeEnumerationCore("status", element.getStatusObject(), new ValueSet.ValuesetStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusObject(), new ValueSet.ValuesetStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalObject(), false);
      composeBooleanExtras("experimental", element.getExperimentalObject(), false);
      composeBooleanCore("extensible", element.getExtensibleObject(), false);
      composeBooleanExtras("extensible", element.getExtensibleObject(), false);
      composeDateTimeCore("date", element.getDateObject(), false);
      composeDateTimeExtras("date", element.getDateObject(), false);
      composeDateCore("stableDate", element.getStableDateObject(), false);
      composeDateExtras("stableDate", element.getStableDateObject(), false);
      composeValueSetValueSetDefineComponent("define", element.getDefine());
      composeValueSetValueSetComposeComponent("compose", element.getCompose());
      composeValueSetValueSetExpansionComponent("expansion", element.getExpansion());
    }
  }

  private void composeValueSetValueSetDefineComponent(String name, ValueSet.ValueSetDefineComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeBooleanCore("caseSensitive", element.getCaseSensitiveObject(), false);
      composeBooleanExtras("caseSensitive", element.getCaseSensitiveObject(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
          composeValueSetConceptDefinitionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptDefinitionComponent(String name, ValueSet.ConceptDefinitionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      composeBooleanCore("abstract", element.getAbstractObject(), false);
      composeBooleanExtras("abstract", element.getAbstractObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
      composeStringCore("definition", element.getDefinitionObject(), false);
      composeStringExtras("definition", element.getDefinitionObject(), false);
      if (element.getDesignation().size() > 0) {
        openArray("designation");
        for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
          composeValueSetConceptDefinitionDesignationComponent(null, e);
        closeArray();
      };
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
          composeValueSetConceptDefinitionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptDefinitionDesignationComponent(String name, ValueSet.ConceptDefinitionDesignationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("language", element.getLanguageObject(), false);
      composeCodeExtras("language", element.getLanguageObject(), false);
      composeCoding("use", element.getUse());
      composeStringCore("value", element.getValueObject(), false);
      composeStringExtras("value", element.getValueObject(), false);
      close();
    }
  }

  private void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      if (element.getImport().size() > 0) {
        openArray("import");
        for (UriType e : element.getImport()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getImport())) {
          openArray("_import");
          for (UriType e : element.getImport()) 
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
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ConceptReferenceComponent e : element.getConcept()) 
          composeValueSetConceptReferenceComponent(null, e);
        closeArray();
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

  private void composeValueSetConceptReferenceComponent(String name, ValueSet.ConceptReferenceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
      if (element.getDesignation().size() > 0) {
        openArray("designation");
        for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
          composeValueSetConceptDefinitionDesignationComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeCodeCore("property", element.getPropertyObject(), false);
      composeCodeExtras("property", element.getPropertyObject(), false);
      if (element.getOpObject() != null) {
        composeEnumerationCore("op", element.getOpObject(), new ValueSet.FilterOperatorEnumFactory(), false);
        composeEnumerationExtras("op", element.getOpObject(), new ValueSet.FilterOperatorEnumFactory(), false);
      }
      composeCodeCore("value", element.getValueObject(), false);
      composeCodeExtras("value", element.getValueObject(), false);
      close();
    }
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeInstantCore("timestamp", element.getTimestampObject(), false);
      composeInstantExtras("timestamp", element.getTimestampObject(), false);
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
      composeUriCore("system", element.getSystemObject(), false);
      composeUriExtras("system", element.getSystemObject(), false);
      composeBooleanCore("abstract", element.getAbstractObject(), false);
      composeBooleanExtras("abstract", element.getAbstractObject(), false);
      composeStringCore("version", element.getVersionObject(), false);
      composeStringExtras("version", element.getVersionObject(), false);
      composeCodeCore("code", element.getCodeObject(), false);
      composeCodeExtras("code", element.getCodeObject(), false);
      composeStringCore("display", element.getDisplayObject(), false);
      composeStringExtras("display", element.getDisplayObject(), false);
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
    else if (resource instanceof Contraindication)
      composeContraindication("Contraindication", (Contraindication)resource);
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
    else if (resource instanceof ReferralRequest)
      composeReferralRequest("ReferralRequest", (ReferralRequest)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson("RelatedPerson", (RelatedPerson)resource);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment("RiskAssessment", (RiskAssessment)resource);
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

  protected void composeNamedReference(String name, Resource resource) throws Exception {
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
    else if (resource instanceof Contraindication)
      composeContraindication(name, (Contraindication)resource);
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
    else if (resource instanceof ReferralRequest)
      composeReferralRequest(name, (ReferralRequest)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson(name, (RelatedPerson)resource);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment(name, (RiskAssessment)resource);
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
    else if (type instanceof Reference)
       composeReference(prefix+"Reference", (Reference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConcept(prefix+"CodeableConcept", (CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifier(prefix+"Identifier", (Identifier) type);
    else if (type instanceof Timing)
       composeTiming(prefix+"Timing", (Timing) type);
    else if (type instanceof Address)
       composeAddress(prefix+"Address", (Address) type);
    else if (type instanceof HumanName)
       composeHumanName(prefix+"HumanName", (HumanName) type);
    else if (type instanceof ContactPoint)
       composeContactPoint(prefix+"ContactPoint", (ContactPoint) type);
    else if (type instanceof IntegerType) {
      composeIntegerCore(prefix+"Integer", (IntegerType) type, false);
      composeIntegerExtras(prefix+"Integer", (IntegerType) type, false);
    }
    else if (type instanceof DateTimeType) {
      composeDateTimeCore(prefix+"DateTime", (DateTimeType) type, false);
      composeDateTimeExtras(prefix+"DateTime", (DateTimeType) type, false);
    }
    else if (type instanceof CodeType) {
      composeCodeCore(prefix+"Code", (CodeType) type, false);
      composeCodeExtras(prefix+"Code", (CodeType) type, false);
    }
    else if (type instanceof DateType) {
      composeDateCore(prefix+"Date", (DateType) type, false);
      composeDateExtras(prefix+"Date", (DateType) type, false);
    }
    else if (type instanceof DecimalType) {
      composeDecimalCore(prefix+"Decimal", (DecimalType) type, false);
      composeDecimalExtras(prefix+"Decimal", (DecimalType) type, false);
    }
    else if (type instanceof UriType) {
      composeUriCore(prefix+"Uri", (UriType) type, false);
      composeUriExtras(prefix+"Uri", (UriType) type, false);
    }
    else if (type instanceof IdType) {
      composeIdCore(prefix+"Id", (IdType) type, false);
      composeIdExtras(prefix+"Id", (IdType) type, false);
    }
    else if (type instanceof Base64BinaryType) {
      composeBase64BinaryCore(prefix+"Base64Binary", (Base64BinaryType) type, false);
      composeBase64BinaryExtras(prefix+"Base64Binary", (Base64BinaryType) type, false);
    }
    else if (type instanceof TimeType) {
      composeTimeCore(prefix+"Time", (TimeType) type, false);
      composeTimeExtras(prefix+"Time", (TimeType) type, false);
    }
    else if (type instanceof OidType) {
      composeOidCore(prefix+"Oid", (OidType) type, false);
      composeOidExtras(prefix+"Oid", (OidType) type, false);
    }
    else if (type instanceof StringType) {
      composeStringCore(prefix+"String", (StringType) type, false);
      composeStringExtras(prefix+"String", (StringType) type, false);
    }
    else if (type instanceof BooleanType) {
      composeBooleanCore(prefix+"Boolean", (BooleanType) type, false);
      composeBooleanExtras(prefix+"Boolean", (BooleanType) type, false);
    }
    else if (type instanceof UuidType) {
      composeUuidCore(prefix+"Uuid", (UuidType) type, false);
      composeUuidExtras(prefix+"Uuid", (UuidType) type, false);
    }
    else if (type instanceof InstantType) {
      composeInstantCore(prefix+"Instant", (InstantType) type, false);
      composeInstantExtras(prefix+"Instant", (InstantType) type, false);
    }
    else
      throw new Exception("Unhanded type");
  }

}

