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

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.utilities.Utilities;

public class JsonComposer extends JsonComposerBase {

  private void composeElement(Element element) throws Exception {
    if (element.getXmlId() != null)
      prop("id", element.getXmlId());
      if (!element.getXmlComments().isEmpty() && !canonical) {
        openArray("fhir_comments");
        for (String s : element.getXmlComments())
          prop(null,  s);
         closeArray();
      }
    if (element.getExtension().size() > 0) {
      composeExtensions(element.getExtension());
    }
  }

  private void composeBackbone(BackboneElement element) throws Exception {
    composeElement(element);
    if (element.getModifierExtension().size() > 0) {
      openObject("modifier");
      composeExtensions(element.getModifierExtension());
      close();
    }
  }

  private <E extends Enum<E>> void composeEnumerationCore(String name, Enumeration<E> value, EnumFactory e, boolean inArray) throws Exception {
    if (value != null && value.getValue() != null) {
      prop(name, e.toCode(value.getValue()));
    } else if (inArray)   
      writeNull(name);
  }    

  private <E extends Enum<E>> void composeEnumerationExtras(String name, Enumeration<E> value, EnumFactory e, boolean inArray) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || makeComments(value))) {
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
      composeExtensionInner(element);
      close();
    }
  }

  private void composeExtensionInner(Extension element) throws Exception {
      composeElement(element);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      composeType("value", element.getValue());
  }

  private void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      open(name);
      composeNarrativeInner(element);
      close();
    }
  }

  private void composeNarrativeInner(Narrative element) throws Exception {
      composeElement(element);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Narrative.NarrativeStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Narrative.NarrativeStatusEnumFactory(), false);
      }
      composeXhtml("div", element.getDiv());
  }

  private void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      open(name);
      composePeriodInner(element);
      close();
    }
  }

  private void composePeriodInner(Period element) throws Exception {
      composeElement(element);
      composeDateTimeCore("start", element.getStartElement(), false);
      composeDateTimeExtras("start", element.getStartElement(), false);
      composeDateTimeCore("end", element.getEndElement(), false);
      composeDateTimeExtras("end", element.getEndElement(), false);
  }

  private void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      open(name);
      composeCodingInner(element);
      close();
    }
  }

  private void composeCodingInner(Coding element) throws Exception {
      composeElement(element);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
      composeBooleanCore("primary", element.getPrimaryElement(), false);
      composeBooleanExtras("primary", element.getPrimaryElement(), false);
      composeReference("valueSet", element.getValueSet());
  }

  private void composeRange(String name, Range element) throws Exception {
    if (element != null) {
      open(name);
      composeRangeInner(element);
      close();
    }
  }

  private void composeRangeInner(Range element) throws Exception {
      composeElement(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
  }

  private void composeQuantity(String name, Quantity element) throws Exception {
    if (element != null) {
      open(name);
      composeQuantityInner(element);
      close();
    }
  }

  private void composeQuantityInner(Quantity element) throws Exception {
      composeElement(element);
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
      if (element.getComparatorElement() != null) {
        composeEnumerationCore("comparator", element.getComparatorElement(), new Quantity.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorElement(), new Quantity.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsElement(), false);
      composeStringExtras("units", element.getUnitsElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
  }

  private void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      open(name);
      composeAttachmentInner(element);
      close();
    }
  }

  private void composeAttachmentInner(Attachment element) throws Exception {
      composeElement(element);
      composeCodeCore("contentType", element.getContentTypeElement(), false);
      composeCodeExtras("contentType", element.getContentTypeElement(), false);
      composeCodeCore("language", element.getLanguageElement(), false);
      composeCodeExtras("language", element.getLanguageElement(), false);
      composeBase64BinaryCore("data", element.getDataElement(), false);
      composeBase64BinaryExtras("data", element.getDataElement(), false);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      composeIntegerCore("size", element.getSizeElement(), false);
      composeIntegerExtras("size", element.getSizeElement(), false);
      composeBase64BinaryCore("hash", element.getHashElement(), false);
      composeBase64BinaryExtras("hash", element.getHashElement(), false);
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
  }

  private void composeRatio(String name, Ratio element) throws Exception {
    if (element != null) {
      open(name);
      composeRatioInner(element);
      close();
    }
  }

  private void composeRatioInner(Ratio element) throws Exception {
      composeElement(element);
      composeQuantity("numerator", element.getNumerator());
      composeQuantity("denominator", element.getDenominator());
  }

  private void composeSampledData(String name, SampledData element) throws Exception {
    if (element != null) {
      open(name);
      composeSampledDataInner(element);
      close();
    }
  }

  private void composeSampledDataInner(SampledData element) throws Exception {
      composeElement(element);
      composeQuantity("origin", element.getOrigin());
      composeDecimalCore("period", element.getPeriodElement(), false);
      composeDecimalExtras("period", element.getPeriodElement(), false);
      composeDecimalCore("factor", element.getFactorElement(), false);
      composeDecimalExtras("factor", element.getFactorElement(), false);
      composeDecimalCore("lowerLimit", element.getLowerLimitElement(), false);
      composeDecimalExtras("lowerLimit", element.getLowerLimitElement(), false);
      composeDecimalCore("upperLimit", element.getUpperLimitElement(), false);
      composeDecimalExtras("upperLimit", element.getUpperLimitElement(), false);
      composeIntegerCore("dimensions", element.getDimensionsElement(), false);
      composeIntegerExtras("dimensions", element.getDimensionsElement(), false);
      composeStringCore("data", element.getDataElement(), false);
      composeStringExtras("data", element.getDataElement(), false);
  }

  private void composeReference(String name, Reference element) throws Exception {
    if (element != null) {
      open(name);
      composeReferenceInner(element);
      close();
    }
  }

  private void composeReferenceInner(Reference element) throws Exception {
      composeElement(element);
      composeStringCore("reference", element.getReferenceElement(), false);
      composeStringExtras("reference", element.getReferenceElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
  }

  private void composeCodeableConcept(String name, CodeableConcept element) throws Exception {
    if (element != null) {
      open(name);
      composeCodeableConceptInner(element);
      close();
    }
  }

  private void composeCodeableConceptInner(CodeableConcept element) throws Exception {
      composeElement(element);
      if (element.getCoding().size() > 0) {
        openArray("coding");
        for (Coding e : element.getCoding()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      open(name);
      composeIdentifierInner(element);
      close();
    }
  }

  private void composeIdentifierInner(Identifier element) throws Exception {
      composeElement(element);
      if (element.getUseElement() != null) {
        composeEnumerationCore("use", element.getUseElement(), new Identifier.IdentifierUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseElement(), new Identifier.IdentifierUseEnumFactory(), false);
      }
      composeStringCore("label", element.getLabelElement(), false);
      composeStringExtras("label", element.getLabelElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeStringCore("value", element.getValueElement(), false);
      composeStringExtras("value", element.getValueElement(), false);
      composePeriod("period", element.getPeriod());
      composeReference("assigner", element.getAssigner());
  }

  private void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      open(name);
      composeAgeInner(element);
      close();
    }
  }

  private void composeAgeInner(Age element) throws Exception {
      composeElement(element);
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
      if (element.getComparatorElement() != null) {
        composeEnumerationCore("comparator", element.getComparatorElement(), new Age.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorElement(), new Age.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsElement(), false);
      composeStringExtras("units", element.getUnitsElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
  }

  private void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      open(name);
      composeCountInner(element);
      close();
    }
  }

  private void composeCountInner(Count element) throws Exception {
      composeElement(element);
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
      if (element.getComparatorElement() != null) {
        composeEnumerationCore("comparator", element.getComparatorElement(), new Count.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorElement(), new Count.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsElement(), false);
      composeStringExtras("units", element.getUnitsElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
  }

  private void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      open(name);
      composeMoneyInner(element);
      close();
    }
  }

  private void composeMoneyInner(Money element) throws Exception {
      composeElement(element);
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
      if (element.getComparatorElement() != null) {
        composeEnumerationCore("comparator", element.getComparatorElement(), new Money.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorElement(), new Money.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsElement(), false);
      composeStringExtras("units", element.getUnitsElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
  }

  private void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      open(name);
      composeDistanceInner(element);
      close();
    }
  }

  private void composeDistanceInner(Distance element) throws Exception {
      composeElement(element);
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
      if (element.getComparatorElement() != null) {
        composeEnumerationCore("comparator", element.getComparatorElement(), new Distance.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorElement(), new Distance.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsElement(), false);
      composeStringExtras("units", element.getUnitsElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
  }

  private void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      open(name);
      composeDurationInner(element);
      close();
    }
  }

  private void composeDurationInner(Duration element) throws Exception {
      composeElement(element);
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
      if (element.getComparatorElement() != null) {
        composeEnumerationCore("comparator", element.getComparatorElement(), new Duration.QuantityComparatorEnumFactory(), false);
        composeEnumerationExtras("comparator", element.getComparatorElement(), new Duration.QuantityComparatorEnumFactory(), false);
      }
      composeStringCore("units", element.getUnitsElement(), false);
      composeStringExtras("units", element.getUnitsElement(), false);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
  }

  private void composeElementDefinition(String name, ElementDefinition element) throws Exception {
    if (element != null) {
      open(name);
      composeElementDefinitionInner(element);
      close();
    }
  }

  private void composeElementDefinitionInner(ElementDefinition element) throws Exception {
      composeElement(element);
      composeStringCore("path", element.getPathElement(), false);
      composeStringExtras("path", element.getPathElement(), false);
      if (element.getRepresentation().size() > 0) {
        openArray("representation");
        for (Enumeration<ElementDefinition.PropertyRepresentation> e : element.getRepresentation()) 
          composeEnumerationCore(null, e, new ElementDefinition.PropertyRepresentationEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getRepresentation())) {
          openArray("_representation");
          for (Enumeration<ElementDefinition.PropertyRepresentation> e : element.getRepresentation()) 
            composeEnumerationExtras(null, e, new ElementDefinition.PropertyRepresentationEnumFactory(), true);
          closeArray();
        }
      };
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeElementDefinitionElementDefinitionSlicingComponent("slicing", element.getSlicing());
      composeStringCore("short", element.getShortElement(), false);
      composeStringExtras("short", element.getShortElement(), false);
      composeStringCore("formal", element.getFormalElement(), false);
      composeStringExtras("formal", element.getFormalElement(), false);
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
      composeStringCore("requirements", element.getRequirementsElement(), false);
      composeStringExtras("requirements", element.getRequirementsElement(), false);
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
      composeIntegerCore("min", element.getMinElement(), false);
      composeIntegerExtras("min", element.getMinElement(), false);
      composeStringCore("max", element.getMaxElement(), false);
      composeStringExtras("max", element.getMaxElement(), false);
      if (element.getType().size() > 0) {
        openArray("type");
        for (ElementDefinition.TypeRefComponent e : element.getType()) 
          composeElementDefinitionTypeRefComponent(null, e);
        closeArray();
      };
      composeStringCore("nameReference", element.getNameReferenceElement(), false);
      composeStringExtras("nameReference", element.getNameReferenceElement(), false);
      composeType("fixed", element.getFixed());
      composeType("pattern", element.getPattern());
      composeType("example", element.getExample());
      composeIntegerCore("maxLength", element.getMaxLengthElement(), false);
      composeIntegerExtras("maxLength", element.getMaxLengthElement(), false);
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
        for (ElementDefinition.ElementDefinitionConstraintComponent e : element.getConstraint()) 
          composeElementDefinitionElementDefinitionConstraintComponent(null, e);
        closeArray();
      };
      composeBooleanCore("mustSupport", element.getMustSupportElement(), false);
      composeBooleanExtras("mustSupport", element.getMustSupportElement(), false);
      composeBooleanCore("isModifier", element.getIsModifierElement(), false);
      composeBooleanExtras("isModifier", element.getIsModifierElement(), false);
      composeBooleanCore("isSummary", element.getIsSummaryElement(), false);
      composeBooleanExtras("isSummary", element.getIsSummaryElement(), false);
      composeElementDefinitionElementDefinitionBindingComponent("binding", element.getBinding());
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (ElementDefinition.ElementDefinitionMappingComponent e : element.getMapping()) 
          composeElementDefinitionElementDefinitionMappingComponent(null, e);
        closeArray();
      };
  }

  private void composeElementDefinitionElementDefinitionSlicingComponent(String name, ElementDefinition.ElementDefinitionSlicingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElementDefinitionElementDefinitionSlicingComponentInner(element);
      close();
    }
  }

  private void composeElementDefinitionElementDefinitionSlicingComponentInner(ElementDefinition.ElementDefinitionSlicingComponent element) throws Exception {
      composeElement(element);
      if (element.getDiscriminator().size() > 0) {
        openArray("discriminator");
        for (IdType e : element.getDiscriminator()) 
          composeIdCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getDiscriminator())) {
          openArray("_discriminator");
          for (IdType e : element.getDiscriminator()) 
            composeIdExtras(null, e, true);
          closeArray();
        }
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeBooleanCore("ordered", element.getOrderedElement(), false);
      composeBooleanExtras("ordered", element.getOrderedElement(), false);
      if (element.getRulesElement() != null) {
        composeEnumerationCore("rules", element.getRulesElement(), new ElementDefinition.ResourceSlicingRulesEnumFactory(), false);
        composeEnumerationExtras("rules", element.getRulesElement(), new ElementDefinition.ResourceSlicingRulesEnumFactory(), false);
      }
  }

  private void composeElementDefinitionTypeRefComponent(String name, ElementDefinition.TypeRefComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElementDefinitionTypeRefComponentInner(element);
      close();
    }
  }

  private void composeElementDefinitionTypeRefComponentInner(ElementDefinition.TypeRefComponent element) throws Exception {
      composeElement(element);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
      composeUriCore("profile", element.getProfileElement(), false);
      composeUriExtras("profile", element.getProfileElement(), false);
      if (element.getAggregation().size() > 0) {
        openArray("aggregation");
        for (Enumeration<ElementDefinition.ResourceAggregationMode> e : element.getAggregation()) 
          composeEnumerationCore(null, e, new ElementDefinition.ResourceAggregationModeEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getAggregation())) {
          openArray("_aggregation");
          for (Enumeration<ElementDefinition.ResourceAggregationMode> e : element.getAggregation()) 
            composeEnumerationExtras(null, e, new ElementDefinition.ResourceAggregationModeEnumFactory(), true);
          closeArray();
        }
      };
  }

  private void composeElementDefinitionElementDefinitionConstraintComponent(String name, ElementDefinition.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElementDefinitionElementDefinitionConstraintComponentInner(element);
      close();
    }
  }

  private void composeElementDefinitionElementDefinitionConstraintComponentInner(ElementDefinition.ElementDefinitionConstraintComponent element) throws Exception {
      composeElement(element);
      composeIdCore("key", element.getKeyElement(), false);
      composeIdExtras("key", element.getKeyElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      if (element.getSeverityElement() != null) {
        composeEnumerationCore("severity", element.getSeverityElement(), new ElementDefinition.ConstraintSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverityElement(), new ElementDefinition.ConstraintSeverityEnumFactory(), false);
      }
      composeStringCore("human", element.getHumanElement(), false);
      composeStringExtras("human", element.getHumanElement(), false);
      composeStringCore("xpath", element.getXpathElement(), false);
      composeStringExtras("xpath", element.getXpathElement(), false);
  }

  private void composeElementDefinitionElementDefinitionBindingComponent(String name, ElementDefinition.ElementDefinitionBindingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElementDefinitionElementDefinitionBindingComponentInner(element);
      close();
    }
  }

  private void composeElementDefinitionElementDefinitionBindingComponentInner(ElementDefinition.ElementDefinitionBindingComponent element) throws Exception {
      composeElement(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeBooleanCore("isExtensible", element.getIsExtensibleElement(), false);
      composeBooleanExtras("isExtensible", element.getIsExtensibleElement(), false);
      if (element.getConformanceElement() != null) {
        composeEnumerationCore("conformance", element.getConformanceElement(), new ElementDefinition.BindingConformanceEnumFactory(), false);
        composeEnumerationExtras("conformance", element.getConformanceElement(), new ElementDefinition.BindingConformanceEnumFactory(), false);
      }
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeType("reference", element.getReference());
  }

  private void composeElementDefinitionElementDefinitionMappingComponent(String name, ElementDefinition.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElementDefinitionElementDefinitionMappingComponentInner(element);
      close();
    }
  }

  private void composeElementDefinitionElementDefinitionMappingComponentInner(ElementDefinition.ElementDefinitionMappingComponent element) throws Exception {
      composeElement(element);
      composeIdCore("identity", element.getIdentityElement(), false);
      composeIdExtras("identity", element.getIdentityElement(), false);
      composeStringCore("map", element.getMapElement(), false);
      composeStringExtras("map", element.getMapElement(), false);
  }

  private void composeTiming(String name, Timing element) throws Exception {
    if (element != null) {
      open(name);
      composeTimingInner(element);
      close();
    }
  }

  private void composeTimingInner(Timing element) throws Exception {
      composeElement(element);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Period e : element.getEvent()) 
          composePeriod(null, e);
        closeArray();
      };
      composeTimingTimingRepeatComponent("repeat", element.getRepeat());
  }

  private void composeTimingTimingRepeatComponent(String name, Timing.TimingRepeatComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeTimingTimingRepeatComponentInner(element);
      close();
    }
  }

  private void composeTimingTimingRepeatComponentInner(Timing.TimingRepeatComponent element) throws Exception {
      composeElement(element);
      composeIntegerCore("frequency", element.getFrequencyElement(), false);
      composeIntegerExtras("frequency", element.getFrequencyElement(), false);
      if (element.getWhenElement() != null) {
        composeEnumerationCore("when", element.getWhenElement(), new Timing.EventTimingEnumFactory(), false);
        composeEnumerationExtras("when", element.getWhenElement(), new Timing.EventTimingEnumFactory(), false);
      }
      composeDecimalCore("duration", element.getDurationElement(), false);
      composeDecimalExtras("duration", element.getDurationElement(), false);
      if (element.getUnitsElement() != null) {
        composeEnumerationCore("units", element.getUnitsElement(), new Timing.UnitsOfTimeEnumFactory(), false);
        composeEnumerationExtras("units", element.getUnitsElement(), new Timing.UnitsOfTimeEnumFactory(), false);
      }
      composeIntegerCore("count", element.getCountElement(), false);
      composeIntegerExtras("count", element.getCountElement(), false);
      composeDateTimeCore("end", element.getEndElement(), false);
      composeDateTimeExtras("end", element.getEndElement(), false);
  }

  private void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      open(name);
      composeAddressInner(element);
      close();
    }
  }

  private void composeAddressInner(Address element) throws Exception {
      composeElement(element);
      if (element.getUseElement() != null) {
        composeEnumerationCore("use", element.getUseElement(), new Address.AddressUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseElement(), new Address.AddressUseEnumFactory(), false);
      }
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
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
      composeStringCore("city", element.getCityElement(), false);
      composeStringExtras("city", element.getCityElement(), false);
      composeStringCore("state", element.getStateElement(), false);
      composeStringExtras("state", element.getStateElement(), false);
      composeStringCore("postalCode", element.getPostalCodeElement(), false);
      composeStringExtras("postalCode", element.getPostalCodeElement(), false);
      composeStringCore("country", element.getCountryElement(), false);
      composeStringExtras("country", element.getCountryElement(), false);
      composePeriod("period", element.getPeriod());
  }

  private void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      open(name);
      composeHumanNameInner(element);
      close();
    }
  }

  private void composeHumanNameInner(HumanName element) throws Exception {
      composeElement(element);
      if (element.getUseElement() != null) {
        composeEnumerationCore("use", element.getUseElement(), new HumanName.NameUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseElement(), new HumanName.NameUseEnumFactory(), false);
      }
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
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
  }

  private void composeContactPoint(String name, ContactPoint element) throws Exception {
    if (element != null) {
      open(name);
      composeContactPointInner(element);
      close();
    }
  }

  private void composeContactPointInner(ContactPoint element) throws Exception {
      composeElement(element);
      if (element.getSystemElement() != null) {
        composeEnumerationCore("system", element.getSystemElement(), new ContactPoint.ContactPointSystemEnumFactory(), false);
        composeEnumerationExtras("system", element.getSystemElement(), new ContactPoint.ContactPointSystemEnumFactory(), false);
      }
      composeStringCore("value", element.getValueElement(), false);
      composeStringExtras("value", element.getValueElement(), false);
      if (element.getUseElement() != null) {
        composeEnumerationCore("use", element.getUseElement(), new ContactPoint.ContactPointUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseElement(), new ContactPoint.ContactPointUseEnumFactory(), false);
      }
      composePeriod("period", element.getPeriod());
  }

  private void composeResourceElements(Resource element) throws Exception {
      composeIdCore("id", element.getIdElement(), false);
      composeIdExtras("id", element.getIdElement(), false);
      composeResourceResourceMetaComponent("meta", element.getMeta());
      composeUriCore("implicitRules", element.getImplicitRulesElement(), false);
      composeUriExtras("implicitRules", element.getImplicitRulesElement(), false);
      composeCodeCore("language", element.getLanguageElement(), false);
      composeCodeExtras("language", element.getLanguageElement(), false);
  }

  private void composeResourceResourceMetaComponent(String name, Resource.ResourceMetaComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceResourceMetaComponentInner(element);
      close();
    }
  }

  private void composeResourceResourceMetaComponentInner(Resource.ResourceMetaComponent element) throws Exception {
      composeBackbone(element);
      composeIdCore("versionId", element.getVersionIdElement(), false);
      composeIdExtras("versionId", element.getVersionIdElement(), false);
      composeInstantCore("lastUpdated", element.getLastUpdatedElement(), false);
      composeInstantExtras("lastUpdated", element.getLastUpdatedElement(), false);
      if (element.getProfile().size() > 0) {
        openArray("profile");
        for (UriType e : element.getProfile()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getProfile())) {
          openArray("_profile");
          for (UriType e : element.getProfile()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getSecurity().size() > 0) {
        openArray("security");
        for (Coding e : element.getSecurity()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getTag().size() > 0) {
        openArray("tag");
        for (Coding e : element.getTag()) 
          composeCoding(null, e);
        closeArray();
      };
  }

  private void composeDomainResourceElements(DomainResource element) throws Exception {
      composeResourceElements(element);
      composeNarrative("text", element.getText());
      if (element.getContained().size() > 0) {
        openArray("contained");
        for (Resource e : element.getContained()) {
          open(null);
          composeResource(e);
          close();
        }
        closeArray();
      };
      if (element.getExtension().size() > 0) {
        composeExtensions(element.getExtension());
      };
      if (element.getModifierExtension().size() > 0) {
        openObject("modifier");
        composeExtensions(element.getModifierExtension());
        close();
      };
  }

  private void composeAlert(String name, Alert element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeAlertInner(element);
    }
  }

  private void composeAlertInner(Alert element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Alert.AlertStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Alert.AlertStatusEnumFactory(), false);
      }
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeStringCore("note", element.getNoteElement(), false);
      composeStringExtras("note", element.getNoteElement(), false);
  }

  private void composeAllergyIntolerance(String name, AllergyIntolerance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeAllergyIntoleranceInner(element);
    }
  }

  private void composeAllergyIntoleranceInner(AllergyIntolerance element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("recordedDate", element.getRecordedDateElement(), false);
      composeDateTimeExtras("recordedDate", element.getRecordedDateElement(), false);
      composeReference("recorder", element.getRecorder());
      composeReference("subject", element.getSubject());
      composeCodeableConcept("substance", element.getSubstance());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new AllergyIntolerance.AllergyIntoleranceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new AllergyIntolerance.AllergyIntoleranceStatusEnumFactory(), false);
      }
      if (element.getCriticalityElement() != null) {
        composeEnumerationCore("criticality", element.getCriticalityElement(), new AllergyIntolerance.AllergyIntoleranceCriticalityEnumFactory(), false);
        composeEnumerationExtras("criticality", element.getCriticalityElement(), new AllergyIntolerance.AllergyIntoleranceCriticalityEnumFactory(), false);
      }
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new AllergyIntolerance.AllergyIntoleranceTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new AllergyIntolerance.AllergyIntoleranceTypeEnumFactory(), false);
      }
      if (element.getCategoryElement() != null) {
        composeEnumerationCore("category", element.getCategoryElement(), new AllergyIntolerance.AllergyIntoleranceCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategoryElement(), new AllergyIntolerance.AllergyIntoleranceCategoryEnumFactory(), false);
      }
      composeDateTimeCore("lastOccurence", element.getLastOccurenceElement(), false);
      composeDateTimeExtras("lastOccurence", element.getLastOccurenceElement(), false);
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (AllergyIntolerance.AllergyIntoleranceEventComponent e : element.getEvent()) 
          composeAllergyIntoleranceAllergyIntoleranceEventComponent(null, e);
        closeArray();
      };
  }

  private void composeAllergyIntoleranceAllergyIntoleranceEventComponent(String name, AllergyIntolerance.AllergyIntoleranceEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeAllergyIntoleranceAllergyIntoleranceEventComponentInner(element);
      close();
    }
  }

  private void composeAllergyIntoleranceAllergyIntoleranceEventComponentInner(AllergyIntolerance.AllergyIntoleranceEventComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("substance", element.getSubstance());
      if (element.getCertaintyElement() != null) {
        composeEnumerationCore("certainty", element.getCertaintyElement(), new AllergyIntolerance.ReactionEventCertaintyEnumFactory(), false);
        composeEnumerationExtras("certainty", element.getCertaintyElement(), new AllergyIntolerance.ReactionEventCertaintyEnumFactory(), false);
      }
      if (element.getManifestation().size() > 0) {
        openArray("manifestation");
        for (CodeableConcept e : element.getManifestation()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeDateTimeCore("onset", element.getOnsetElement(), false);
      composeDateTimeExtras("onset", element.getOnsetElement(), false);
      composeDuration("duration", element.getDuration());
      if (element.getSeverityElement() != null) {
        composeEnumerationCore("severity", element.getSeverityElement(), new AllergyIntolerance.ReactionEventSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverityElement(), new AllergyIntolerance.ReactionEventSeverityEnumFactory(), false);
      }
      composeCodeableConcept("exposureRoute", element.getExposureRoute());
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
  }

  private void composeAppointment(String name, Appointment element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeAppointmentInner(element);
    }
  }

  private void composeAppointmentInner(Appointment element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeIntegerCore("priority", element.getPriorityElement(), false);
      composeIntegerExtras("priority", element.getPriorityElement(), false);
      composeCodeCore("status", element.getStatusElement(), false);
      composeCodeExtras("status", element.getStatusElement(), false);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeInstantCore("start", element.getStartElement(), false);
      composeInstantExtras("start", element.getStartElement(), false);
      composeInstantCore("end", element.getEndElement(), false);
      composeInstantExtras("end", element.getEndElement(), false);
      if (element.getSlot().size() > 0) {
        openArray("slot");
        for (Reference e : element.getSlot()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("location", element.getLocation());
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      composeReference("order", element.getOrder());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (Appointment.AppointmentParticipantComponent e : element.getParticipant()) 
          composeAppointmentAppointmentParticipantComponent(null, e);
        closeArray();
      };
      composeReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTimeCore("lastModified", element.getLastModifiedElement(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedElement(), false);
  }

  private void composeAppointmentAppointmentParticipantComponent(String name, Appointment.AppointmentParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeAppointmentAppointmentParticipantComponentInner(element);
      close();
    }
  }

  private void composeAppointmentAppointmentParticipantComponentInner(Appointment.AppointmentParticipantComponent element) throws Exception {
      composeBackbone(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeReference("actor", element.getActor());
      if (element.getRequiredElement() != null) {
        composeEnumerationCore("required", element.getRequiredElement(), new Appointment.ParticipantrequiredEnumFactory(), false);
        composeEnumerationExtras("required", element.getRequiredElement(), new Appointment.ParticipantrequiredEnumFactory(), false);
      }
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Appointment.ParticipationstatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Appointment.ParticipationstatusEnumFactory(), false);
      }
  }

  private void composeAppointmentResponse(String name, AppointmentResponse element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeAppointmentResponseInner(element);
    }
  }

  private void composeAppointmentResponseInner(AppointmentResponse element) throws Exception {
      composeDomainResourceElements(element);
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
      if (element.getParticipantStatusElement() != null) {
        composeEnumerationCore("participantStatus", element.getParticipantStatusElement(), new AppointmentResponse.ParticipantstatusEnumFactory(), false);
        composeEnumerationExtras("participantStatus", element.getParticipantStatusElement(), new AppointmentResponse.ParticipantstatusEnumFactory(), false);
      }
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      composeInstantCore("start", element.getStartElement(), false);
      composeInstantExtras("start", element.getStartElement(), false);
      composeInstantCore("end", element.getEndElement(), false);
      composeInstantExtras("end", element.getEndElement(), false);
      composeReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTimeCore("lastModified", element.getLastModifiedElement(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedElement(), false);
  }

  private void composeAvailability(String name, Availability element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeAvailabilityInner(element);
    }
  }

  private void composeAvailabilityInner(Availability element) throws Exception {
      composeDomainResourceElements(element);
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
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      composeDateTimeCore("lastModified", element.getLastModifiedElement(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedElement(), false);
  }

  private void composeBasic(String name, Basic element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeBasicInner(element);
    }
  }

  private void composeBasicInner(Basic element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDateCore("created", element.getCreatedElement(), false);
      composeDateExtras("created", element.getCreatedElement(), false);
  }

  private void composeBinary(String name, Binary element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeBinaryInner(element);
    }
  }

  private void composeBinaryInner(Binary element) throws Exception {
      composeResourceElements(element);
      composeCodeCore("contentType", element.getContentTypeElement(), false);
      composeCodeExtras("contentType", element.getContentTypeElement(), false);
      composeBase64BinaryCore("content", element.getContentElement(), false);
      composeBase64BinaryExtras("content", element.getContentElement(), false);
  }

  private void composeBundle(String name, Bundle element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeBundleInner(element);
    }
  }

  private void composeBundleInner(Bundle element) throws Exception {
      composeResourceElements(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Bundle.BundleTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Bundle.BundleTypeEnumFactory(), false);
      }
      composeUriCore("base", element.getBaseElement(), false);
      composeUriExtras("base", element.getBaseElement(), false);
      composeIntegerCore("total", element.getTotalElement(), false);
      composeIntegerExtras("total", element.getTotalElement(), false);
      if (element.getLink().size() > 0) {
        openArray("link");
        for (Bundle.BundleLinkComponent e : element.getLink()) 
          composeBundleBundleLinkComponent(null, e);
        closeArray();
      };
      if (element.getEntry().size() > 0) {
        openArray("entry");
        for (Bundle.BundleEntryComponent e : element.getEntry()) 
          composeBundleBundleEntryComponent(null, e);
        closeArray();
      };
      composeBase64BinaryCore("signature", element.getSignatureElement(), false);
      composeBase64BinaryExtras("signature", element.getSignatureElement(), false);
  }

  private void composeBundleBundleLinkComponent(String name, Bundle.BundleLinkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBundleBundleLinkComponentInner(element);
      close();
    }
  }

  private void composeBundleBundleLinkComponentInner(Bundle.BundleLinkComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("relation", element.getRelationElement(), false);
      composeStringExtras("relation", element.getRelationElement(), false);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
  }

  private void composeBundleBundleEntryComponent(String name, Bundle.BundleEntryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBundleBundleEntryComponentInner(element);
      close();
    }
  }

  private void composeBundleBundleEntryComponentInner(Bundle.BundleEntryComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("base", element.getBaseElement(), false);
      composeUriExtras("base", element.getBaseElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Bundle.BundleEntryStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Bundle.BundleEntryStatusEnumFactory(), false);
      }
      composeUriCore("search", element.getSearchElement(), false);
      composeUriExtras("search", element.getSearchElement(), false);
      composeDecimalCore("score", element.getScoreElement(), false);
      composeDecimalExtras("score", element.getScoreElement(), false);
      composeBundleBundleEntryDeletedComponent("deleted", element.getDeleted());
        if (element.getResource() != null) {
          open("resource");
          composeResource(element.getResource());
          close();
        }
  }

  private void composeBundleBundleEntryDeletedComponent(String name, Bundle.BundleEntryDeletedComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeBundleBundleEntryDeletedComponentInner(element);
      close();
    }
  }

  private void composeBundleBundleEntryDeletedComponentInner(Bundle.BundleEntryDeletedComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("type", element.getTypeElement(), false);
      composeCodeExtras("type", element.getTypeElement(), false);
      composeIdCore("id", element.getIdElement(), false);
      composeIdExtras("id", element.getIdElement(), false);
      composeInstantCore("instant", element.getInstantElement(), false);
      composeInstantExtras("instant", element.getInstantElement(), false);
  }

  private void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeCarePlanInner(element);
    }
  }

  private void composeCarePlanInner(CarePlan element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("patient", element.getPatient());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new CarePlan.CarePlanStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new CarePlan.CarePlanStatusEnumFactory(), false);
      }
      composePeriod("period", element.getPeriod());
      composeDateTimeCore("modified", element.getModifiedElement(), false);
      composeDateTimeExtras("modified", element.getModifiedElement(), false);
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
      composeStringCore("notes", element.getNotesElement(), false);
      composeStringExtras("notes", element.getNotesElement(), false);
  }

  private void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCarePlanCarePlanParticipantComponentInner(element);
      close();
    }
  }

  private void composeCarePlanCarePlanParticipantComponentInner(CarePlan.CarePlanParticipantComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("role", element.getRole());
      composeReference("member", element.getMember());
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCarePlanCarePlanGoalComponentInner(element);
      close();
    }
  }

  private void composeCarePlanCarePlanGoalComponentInner(CarePlan.CarePlanGoalComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new CarePlan.CarePlanGoalStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new CarePlan.CarePlanGoalStatusEnumFactory(), false);
      }
      composeStringCore("notes", element.getNotesElement(), false);
      composeStringExtras("notes", element.getNotesElement(), false);
      if (element.getConcern().size() > 0) {
        openArray("concern");
        for (Reference e : element.getConcern()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCarePlanCarePlanActivityComponentInner(element);
      close();
    }
  }

  private void composeCarePlanCarePlanActivityComponentInner(CarePlan.CarePlanActivityComponent element) throws Exception {
      composeBackbone(element);
      if (element.getGoal().size() > 0) {
        openArray("goal");
        for (UriType e : element.getGoal()) 
          composeUriCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getGoal())) {
          openArray("_goal");
          for (UriType e : element.getGoal()) 
            composeUriExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new CarePlan.CarePlanActivityStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new CarePlan.CarePlanActivityStatusEnumFactory(), false);
      }
      composeBooleanCore("prohibited", element.getProhibitedElement(), false);
      composeBooleanExtras("prohibited", element.getProhibitedElement(), false);
      if (element.getActionResulting().size() > 0) {
        openArray("actionResulting");
        for (Reference e : element.getActionResulting()) 
          composeReference(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotesElement(), false);
      composeStringExtras("notes", element.getNotesElement(), false);
      composeReference("detail", element.getDetail());
      composeCarePlanCarePlanActivitySimpleComponent("simple", element.getSimple());
  }

  private void composeCarePlanCarePlanActivitySimpleComponent(String name, CarePlan.CarePlanActivitySimpleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCarePlanCarePlanActivitySimpleComponentInner(element);
      close();
    }
  }

  private void composeCarePlanCarePlanActivitySimpleComponentInner(CarePlan.CarePlanActivitySimpleComponent element) throws Exception {
      composeBackbone(element);
      if (element.getCategoryElement() != null) {
        composeEnumerationCore("category", element.getCategoryElement(), new CarePlan.CarePlanActivityCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategoryElement(), new CarePlan.CarePlanActivityCategoryEnumFactory(), false);
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
      composeStringCore("details", element.getDetailsElement(), false);
      composeStringExtras("details", element.getDetailsElement(), false);
  }

  private void composeClaimResponse(String name, ClaimResponse element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeClaimResponseInner(element);
    }
  }

  private void composeClaimResponseInner(ClaimResponse element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("request", element.getRequest());
      if (element.getRequestIdentifier().size() > 0) {
        openArray("requestIdentifier");
        for (Identifier e : element.getRequestIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDateCore("date", element.getDateElement(), false);
      composeDateExtras("date", element.getDateElement(), false);
      composeReference("organization", element.getOrganization());
      composeReference("requestProvider", element.getRequestProvider());
      composeReference("requestOrganization", element.getRequestOrganization());
      if (element.getOutcomeElement() != null) {
        composeEnumerationCore("outcome", element.getOutcomeElement(), new ClaimResponse.RSLinkEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcomeElement(), new ClaimResponse.RSLinkEnumFactory(), false);
      }
      composeStringCore("disposition", element.getDispositionElement(), false);
      composeStringExtras("disposition", element.getDispositionElement(), false);
      composeCoding("payeeType", element.getPayeeType());
      if (element.getItem().size() > 0) {
        openArray("item");
        for (ClaimResponse.ItemsComponent e : element.getItem()) 
          composeClaimResponseItemsComponent(null, e);
        closeArray();
      };
      if (element.getAdditem().size() > 0) {
        openArray("additem");
        for (ClaimResponse.AddedItemComponent e : element.getAdditem()) 
          composeClaimResponseAddedItemComponent(null, e);
        closeArray();
      };
      if (element.getError().size() > 0) {
        openArray("error");
        for (ClaimResponse.ErrorsComponent e : element.getError()) 
          composeClaimResponseErrorsComponent(null, e);
        closeArray();
      };
      composeMoney("totalCost", element.getTotalCost());
      composeMoney("unallocDeductable", element.getUnallocDeductable());
      composeMoney("totalBenefit", element.getTotalBenefit());
      composeMoney("paymentAdjustment", element.getPaymentAdjustment());
      composeCoding("paymentAdjustmentReason", element.getPaymentAdjustmentReason());
      composeDateCore("paymentDate", element.getPaymentDateElement(), false);
      composeDateExtras("paymentDate", element.getPaymentDateElement(), false);
      composeMoney("paymentAmount", element.getPaymentAmount());
      composeIdentifier("paymentRef", element.getPaymentRef());
      composeCoding("reserved", element.getReserved());
      composeCoding("form", element.getForm());
      if (element.getNote().size() > 0) {
        openArray("note");
        for (ClaimResponse.NotesComponent e : element.getNote()) 
          composeClaimResponseNotesComponent(null, e);
        closeArray();
      };
  }

  private void composeClaimResponseItemsComponent(String name, ClaimResponse.ItemsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseItemsComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseItemsComponentInner(ClaimResponse.ItemsComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      composeIntegerExtras("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      if (element.getNoteNumber().size() > 0) {
        openArray("noteNumber");
        for (IntegerType e : element.getNoteNumber()) 
          composeIntegerCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getNoteNumber())) {
          openArray("_noteNumber");
          for (IntegerType e : element.getNoteNumber()) 
            composeIntegerExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getAdjudication().size() > 0) {
        openArray("adjudication");
        for (ClaimResponse.ItemAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseItemAdjudicationComponent(null, e);
        closeArray();
      };
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ClaimResponse.ItemDetailComponent e : element.getDetail()) 
          composeClaimResponseItemDetailComponent(null, e);
        closeArray();
      };
  }

  private void composeClaimResponseItemAdjudicationComponent(String name, ClaimResponse.ItemAdjudicationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseItemAdjudicationComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseItemAdjudicationComponentInner(ClaimResponse.ItemAdjudicationComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
  }

  private void composeClaimResponseItemDetailComponent(String name, ClaimResponse.ItemDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseItemDetailComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseItemDetailComponentInner(ClaimResponse.ItemDetailComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      composeIntegerExtras("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      if (element.getAdjudication().size() > 0) {
        openArray("adjudication");
        for (ClaimResponse.DetailAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseDetailAdjudicationComponent(null, e);
        closeArray();
      };
      if (element.getSubdetail().size() > 0) {
        openArray("subdetail");
        for (ClaimResponse.ItemSubdetailComponent e : element.getSubdetail()) 
          composeClaimResponseItemSubdetailComponent(null, e);
        closeArray();
      };
  }

  private void composeClaimResponseDetailAdjudicationComponent(String name, ClaimResponse.DetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseDetailAdjudicationComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseDetailAdjudicationComponentInner(ClaimResponse.DetailAdjudicationComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
  }

  private void composeClaimResponseItemSubdetailComponent(String name, ClaimResponse.ItemSubdetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseItemSubdetailComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseItemSubdetailComponentInner(ClaimResponse.ItemSubdetailComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      composeIntegerExtras("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      if (element.getAdjudication().size() > 0) {
        openArray("adjudication");
        for (ClaimResponse.SubdetailAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseSubdetailAdjudicationComponent(null, e);
        closeArray();
      };
  }

  private void composeClaimResponseSubdetailAdjudicationComponent(String name, ClaimResponse.SubdetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseSubdetailAdjudicationComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseSubdetailAdjudicationComponentInner(ClaimResponse.SubdetailAdjudicationComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
  }

  private void composeClaimResponseAddedItemComponent(String name, ClaimResponse.AddedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseAddedItemComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseAddedItemComponentInner(ClaimResponse.AddedItemComponent element) throws Exception {
      composeBackbone(element);
      if (element.getSequenceLinkId().size() > 0) {
        openArray("sequenceLinkId");
        for (IntegerType e : element.getSequenceLinkId()) 
          composeIntegerCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getSequenceLinkId())) {
          openArray("_sequenceLinkId");
          for (IntegerType e : element.getSequenceLinkId()) 
            composeIntegerExtras(null, e, true);
          closeArray();
        }
      };
      composeCoding("service", element.getService());
      composeMoney("fee", element.getFee());
      if (element.getNoteNumberLinkId().size() > 0) {
        openArray("noteNumberLinkId");
        for (IntegerType e : element.getNoteNumberLinkId()) 
          composeIntegerCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getNoteNumberLinkId())) {
          openArray("_noteNumberLinkId");
          for (IntegerType e : element.getNoteNumberLinkId()) 
            composeIntegerExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getAdjudication().size() > 0) {
        openArray("adjudication");
        for (ClaimResponse.AddedItemAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseAddedItemAdjudicationComponent(null, e);
        closeArray();
      };
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ClaimResponse.AddedItemsDetailComponent e : element.getDetail()) 
          composeClaimResponseAddedItemsDetailComponent(null, e);
        closeArray();
      };
  }

  private void composeClaimResponseAddedItemAdjudicationComponent(String name, ClaimResponse.AddedItemAdjudicationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseAddedItemAdjudicationComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseAddedItemAdjudicationComponentInner(ClaimResponse.AddedItemAdjudicationComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
  }

  private void composeClaimResponseAddedItemsDetailComponent(String name, ClaimResponse.AddedItemsDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseAddedItemsDetailComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseAddedItemsDetailComponentInner(ClaimResponse.AddedItemsDetailComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("service", element.getService());
      composeMoney("fee", element.getFee());
      if (element.getAdjudication().size() > 0) {
        openArray("adjudication");
        for (ClaimResponse.AddedItemDetailAdjudicationComponent e : element.getAdjudication()) 
          composeClaimResponseAddedItemDetailAdjudicationComponent(null, e);
        closeArray();
      };
  }

  private void composeClaimResponseAddedItemDetailAdjudicationComponent(String name, ClaimResponse.AddedItemDetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseAddedItemDetailAdjudicationComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseAddedItemDetailAdjudicationComponentInner(ClaimResponse.AddedItemDetailAdjudicationComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimalCore("value", element.getValueElement(), false);
      composeDecimalExtras("value", element.getValueElement(), false);
  }

  private void composeClaimResponseErrorsComponent(String name, ClaimResponse.ErrorsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseErrorsComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseErrorsComponentInner(ClaimResponse.ErrorsComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      composeIntegerExtras("sequenceLinkId", element.getSequenceLinkIdElement(), false);
      composeIntegerCore("detailSequenceLinkId", element.getDetailSequenceLinkIdElement(), false);
      composeIntegerExtras("detailSequenceLinkId", element.getDetailSequenceLinkIdElement(), false);
      composeIntegerCore("subdetailSequenceLinkId", element.getSubdetailSequenceLinkIdElement(), false);
      composeIntegerExtras("subdetailSequenceLinkId", element.getSubdetailSequenceLinkIdElement(), false);
      composeCoding("code", element.getCode());
  }

  private void composeClaimResponseNotesComponent(String name, ClaimResponse.NotesComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeClaimResponseNotesComponentInner(element);
      close();
    }
  }

  private void composeClaimResponseNotesComponentInner(ClaimResponse.NotesComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("number", element.getNumberElement(), false);
      composeIntegerExtras("number", element.getNumberElement(), false);
      composeCoding("type", element.getType());
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
  }

  private void composeCommunicationRequest(String name, CommunicationRequest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeCommunicationRequestInner(element);
    }
  }

  private void composeCommunicationRequestInner(CommunicationRequest element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("category", element.getCategory());
      composeReference("sender", element.getSender());
      if (element.getRecipient().size() > 0) {
        openArray("recipient");
        for (Reference e : element.getRecipient()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getMessagePart().size() > 0) {
        openArray("messagePart");
        for (CommunicationRequest.CommunicationRequestMessagePartComponent e : element.getMessagePart()) 
          composeCommunicationRequestCommunicationRequestMessagePartComponent(null, e);
        closeArray();
      };
      if (element.getMedium().size() > 0) {
        openArray("medium");
        for (CodeableConcept e : element.getMedium()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeReference("requester", element.getRequester());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new CommunicationRequest.CommunicationRequestStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new CommunicationRequest.CommunicationRequestStatusEnumFactory(), false);
      }
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new CommunicationRequest.CommunicationRequestModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new CommunicationRequest.CommunicationRequestModeEnumFactory(), false);
      }
      composeReference("encounter", element.getEncounter());
      composeDateTimeCore("scheduledTime", element.getScheduledTimeElement(), false);
      composeDateTimeExtras("scheduledTime", element.getScheduledTimeElement(), false);
      if (element.getIndication().size() > 0) {
        openArray("indication");
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeDateTimeCore("orderedOn", element.getOrderedOnElement(), false);
      composeDateTimeExtras("orderedOn", element.getOrderedOnElement(), false);
      composeReference("subject", element.getSubject());
      composeCodeableConcept("priority", element.getPriority());
  }

  private void composeCommunicationRequestCommunicationRequestMessagePartComponent(String name, CommunicationRequest.CommunicationRequestMessagePartComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCommunicationRequestCommunicationRequestMessagePartComponentInner(element);
      close();
    }
  }

  private void composeCommunicationRequestCommunicationRequestMessagePartComponentInner(CommunicationRequest.CommunicationRequestMessagePartComponent element) throws Exception {
      composeBackbone(element);
      composeType("content", element.getContent());
  }

  private void composeComposition(String name, Composition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeCompositionInner(element);
    }
  }

  private void composeCompositionInner(Composition element) throws Exception {
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Composition.CompositionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Composition.CompositionStatusEnumFactory(), false);
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

  private void composeCompositionCompositionAttesterComponent(String name, Composition.CompositionAttesterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCompositionCompositionAttesterComponentInner(element);
      close();
    }
  }

  private void composeCompositionCompositionAttesterComponentInner(Composition.CompositionAttesterComponent element) throws Exception {
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
      composeDateTimeCore("time", element.getTimeElement(), false);
      composeDateTimeExtras("time", element.getTimeElement(), false);
      composeReference("party", element.getParty());
  }

  private void composeCompositionCompositionEventComponent(String name, Composition.CompositionEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCompositionCompositionEventComponentInner(element);
      close();
    }
  }

  private void composeCompositionCompositionEventComponentInner(Composition.CompositionEventComponent element) throws Exception {
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
  }

  private void composeCompositionSectionComponent(String name, Composition.SectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeCompositionSectionComponentInner(element);
      close();
    }
  }

  private void composeCompositionSectionComponentInner(Composition.SectionComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
      composeCodeableConcept("code", element.getCode());
      if (element.getSection().size() > 0) {
        openArray("section");
        for (Composition.SectionComponent e : element.getSection()) 
          composeCompositionSectionComponent(null, e);
        closeArray();
      };
      composeReference("content", element.getContent());
  }

  private void composeConceptMap(String name, ConceptMap element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeConceptMapInner(element);
    }
  }

  private void composeConceptMapInner(ConceptMap element) throws Exception {
      composeDomainResourceElements(element);
      composeStringCore("identifier", element.getIdentifierElement(), false);
      composeStringExtras("identifier", element.getIdentifierElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeStringCore("copyright", element.getCopyrightElement(), false);
      composeStringExtras("copyright", element.getCopyrightElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new ConceptMap.ValuesetStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new ConceptMap.ValuesetStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalElement(), false);
      composeBooleanExtras("experimental", element.getExperimentalElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeType("source", element.getSource());
      composeType("target", element.getTarget());
      if (element.getElement().size() > 0) {
        openArray("element");
        for (ConceptMap.ConceptMapElementComponent e : element.getElement()) 
          composeConceptMapConceptMapElementComponent(null, e);
        closeArray();
      };
  }

  private void composeConceptMapConceptMapElementComponent(String name, ConceptMap.ConceptMapElementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConceptMapConceptMapElementComponentInner(element);
      close();
    }
  }

  private void composeConceptMapConceptMapElementComponentInner(ConceptMap.ConceptMapElementComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("codeSystem", element.getCodeSystemElement(), false);
      composeUriExtras("codeSystem", element.getCodeSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
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
  }

  private void composeConceptMapOtherElementComponent(String name, ConceptMap.OtherElementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConceptMapOtherElementComponentInner(element);
      close();
    }
  }

  private void composeConceptMapOtherElementComponentInner(ConceptMap.OtherElementComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("element", element.getElementElement(), false);
      composeUriExtras("element", element.getElementElement(), false);
      composeUriCore("codeSystem", element.getCodeSystemElement(), false);
      composeUriExtras("codeSystem", element.getCodeSystemElement(), false);
      composeStringCore("code", element.getCodeElement(), false);
      composeStringExtras("code", element.getCodeElement(), false);
  }

  private void composeConceptMapConceptMapElementMapComponent(String name, ConceptMap.ConceptMapElementMapComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConceptMapConceptMapElementMapComponentInner(element);
      close();
    }
  }

  private void composeConceptMapConceptMapElementMapComponentInner(ConceptMap.ConceptMapElementMapComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("codeSystem", element.getCodeSystemElement(), false);
      composeUriExtras("codeSystem", element.getCodeSystemElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
      if (element.getEquivalenceElement() != null) {
        composeEnumerationCore("equivalence", element.getEquivalenceElement(), new ConceptMap.ConceptEquivalenceEnumFactory(), false);
        composeEnumerationExtras("equivalence", element.getEquivalenceElement(), new ConceptMap.ConceptEquivalenceEnumFactory(), false);
      }
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
      if (element.getProduct().size() > 0) {
        openArray("product");
        for (ConceptMap.OtherElementComponent e : element.getProduct()) 
          composeConceptMapOtherElementComponent(null, e);
        closeArray();
      };
  }

  private void composeCondition(String name, Condition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeConditionInner(element);
    }
  }

  private void composeConditionInner(Condition element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("subject", element.getSubject());
      composeReference("encounter", element.getEncounter());
      composeReference("asserter", element.getAsserter());
      composeDateCore("dateAsserted", element.getDateAssertedElement(), false);
      composeDateExtras("dateAsserted", element.getDateAssertedElement(), false);
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Condition.ConditionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Condition.ConditionStatusEnumFactory(), false);
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
      if (element.getDueTo().size() > 0) {
        openArray("dueTo");
        for (Condition.ConditionDueToComponent e : element.getDueTo()) 
          composeConditionConditionDueToComponent(null, e);
        closeArray();
      };
      if (element.getOccurredFollowing().size() > 0) {
        openArray("occurredFollowing");
        for (Condition.ConditionOccurredFollowingComponent e : element.getOccurredFollowing()) 
          composeConditionConditionOccurredFollowingComponent(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotesElement(), false);
      composeStringExtras("notes", element.getNotesElement(), false);
  }

  private void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConditionConditionStageComponentInner(element);
      close();
    }
  }

  private void composeConditionConditionStageComponentInner(Condition.ConditionStageComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("summary", element.getSummary());
      if (element.getAssessment().size() > 0) {
        openArray("assessment");
        for (Reference e : element.getAssessment()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeConditionConditionEvidenceComponent(String name, Condition.ConditionEvidenceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConditionConditionEvidenceComponentInner(element);
      close();
    }
  }

  private void composeConditionConditionEvidenceComponentInner(Condition.ConditionEvidenceComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (Reference e : element.getDetail()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeConditionConditionLocationComponent(String name, Condition.ConditionLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConditionConditionLocationComponentInner(element);
      close();
    }
  }

  private void composeConditionConditionLocationComponentInner(Condition.ConditionLocationComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeStringCore("detail", element.getDetailElement(), false);
      composeStringExtras("detail", element.getDetailElement(), false);
  }

  private void composeConditionConditionDueToComponent(String name, Condition.ConditionDueToComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConditionConditionDueToComponentInner(element);
      close();
    }
  }

  private void composeConditionConditionDueToComponentInner(Condition.ConditionDueToComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("codeableConcept", element.getCodeableConcept());
      composeReference("target", element.getTarget());
  }

  private void composeConditionConditionOccurredFollowingComponent(String name, Condition.ConditionOccurredFollowingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConditionConditionOccurredFollowingComponentInner(element);
      close();
    }
  }

  private void composeConditionConditionOccurredFollowingComponentInner(Condition.ConditionOccurredFollowingComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("codeableConcept", element.getCodeableConcept());
      composeReference("target", element.getTarget());
  }

  private void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeConformanceInner(element);
    }
  }

  private void composeConformanceInner(Conformance element) throws Exception {
      composeDomainResourceElements(element);
      composeStringCore("identifier", element.getIdentifierElement(), false);
      composeStringExtras("identifier", element.getIdentifierElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Conformance.ConformanceStatementStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Conformance.ConformanceStatementStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalElement(), false);
      composeBooleanExtras("experimental", element.getExperimentalElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeIdCore("fhirVersion", element.getFhirVersionElement(), false);
      composeIdExtras("fhirVersion", element.getFhirVersionElement(), false);
      composeBooleanCore("acceptUnknown", element.getAcceptUnknownElement(), false);
      composeBooleanExtras("acceptUnknown", element.getAcceptUnknownElement(), false);
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

  private void composeConformanceConformanceSoftwareComponent(String name, Conformance.ConformanceSoftwareComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceSoftwareComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceSoftwareComponentInner(Conformance.ConformanceSoftwareComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeDateTimeCore("releaseDate", element.getReleaseDateElement(), false);
      composeDateTimeExtras("releaseDate", element.getReleaseDateElement(), false);
  }

  private void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceImplementationComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceImplementationComponentInner(Conformance.ConformanceImplementationComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceRestComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceRestComponentInner(Conformance.ConformanceRestComponent element) throws Exception {
      composeBackbone(element);
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new Conformance.RestfulConformanceModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new Conformance.RestfulConformanceModeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
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
  }

  private void composeConformanceConformanceRestSecurityComponent(String name, Conformance.ConformanceRestSecurityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceRestSecurityComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceRestSecurityComponentInner(Conformance.ConformanceRestSecurityComponent element) throws Exception {
      composeBackbone(element);
      composeBooleanCore("cors", element.getCorsElement(), false);
      composeBooleanExtras("cors", element.getCorsElement(), false);
      if (element.getService().size() > 0) {
        openArray("service");
        for (CodeableConcept e : element.getService()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getCertificate().size() > 0) {
        openArray("certificate");
        for (Conformance.ConformanceRestSecurityCertificateComponent e : element.getCertificate()) 
          composeConformanceConformanceRestSecurityCertificateComponent(null, e);
        closeArray();
      };
  }

  private void composeConformanceConformanceRestSecurityCertificateComponent(String name, Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceRestSecurityCertificateComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceRestSecurityCertificateComponentInner(Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("type", element.getTypeElement(), false);
      composeCodeExtras("type", element.getTypeElement(), false);
      composeBase64BinaryCore("blob", element.getBlobElement(), false);
      composeBase64BinaryExtras("blob", element.getBlobElement(), false);
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceRestResourceComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceRestResourceComponentInner(Conformance.ConformanceRestResourceComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("type", element.getTypeElement(), false);
      composeCodeExtras("type", element.getTypeElement(), false);
      composeReference("profile", element.getProfile());
      if (element.getInteraction().size() > 0) {
        openArray("interaction");
        for (Conformance.ResourceInteractionComponent e : element.getInteraction()) 
          composeConformanceResourceInteractionComponent(null, e);
        closeArray();
      };
      if (element.getVersioningElement() != null) {
        composeEnumerationCore("versioning", element.getVersioningElement(), new Conformance.VersioningPolicyEnumFactory(), false);
        composeEnumerationExtras("versioning", element.getVersioningElement(), new Conformance.VersioningPolicyEnumFactory(), false);
      }
      composeBooleanCore("readHistory", element.getReadHistoryElement(), false);
      composeBooleanExtras("readHistory", element.getReadHistoryElement(), false);
      composeBooleanCore("updateCreate", element.getUpdateCreateElement(), false);
      composeBooleanExtras("updateCreate", element.getUpdateCreateElement(), false);
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
  }

  private void composeConformanceResourceInteractionComponent(String name, Conformance.ResourceInteractionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceResourceInteractionComponentInner(element);
      close();
    }
  }

  private void composeConformanceResourceInteractionComponentInner(Conformance.ResourceInteractionComponent element) throws Exception {
      composeBackbone(element);
      if (element.getCodeElement() != null) {
        composeEnumerationCore("code", element.getCodeElement(), new Conformance.TypeRestfulInteractionEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeElement(), new Conformance.TypeRestfulInteractionEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceRestResourceSearchParamComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponentInner(Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeUriCore("definition", element.getDefinitionElement(), false);
      composeUriExtras("definition", element.getDefinitionElement(), false);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Conformance.SearchParamTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Conformance.SearchParamTypeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
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
  }

  private void composeConformanceSystemInteractionComponent(String name, Conformance.SystemInteractionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceSystemInteractionComponentInner(element);
      close();
    }
  }

  private void composeConformanceSystemInteractionComponentInner(Conformance.SystemInteractionComponent element) throws Exception {
      composeBackbone(element);
      if (element.getCodeElement() != null) {
        composeEnumerationCore("code", element.getCodeElement(), new Conformance.SystemRestfulInteractionEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeElement(), new Conformance.SystemRestfulInteractionEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
  }

  private void composeConformanceConformanceRestOperationComponent(String name, Conformance.ConformanceRestOperationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceRestOperationComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceRestOperationComponentInner(Conformance.ConformanceRestOperationComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeReference("definition", element.getDefinition());
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceMessagingComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceMessagingComponentInner(Conformance.ConformanceMessagingComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("endpoint", element.getEndpointElement(), false);
      composeUriExtras("endpoint", element.getEndpointElement(), false);
      composeIntegerCore("reliableCache", element.getReliableCacheElement(), false);
      composeIntegerExtras("reliableCache", element.getReliableCacheElement(), false);
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Conformance.ConformanceMessagingEventComponent e : element.getEvent()) 
          composeConformanceConformanceMessagingEventComponent(null, e);
        closeArray();
      };
  }

  private void composeConformanceConformanceMessagingEventComponent(String name, Conformance.ConformanceMessagingEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceMessagingEventComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceMessagingEventComponentInner(Conformance.ConformanceMessagingEventComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("code", element.getCode());
      if (element.getCategoryElement() != null) {
        composeEnumerationCore("category", element.getCategoryElement(), new Conformance.MessageSignificanceCategoryEnumFactory(), false);
        composeEnumerationExtras("category", element.getCategoryElement(), new Conformance.MessageSignificanceCategoryEnumFactory(), false);
      }
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new Conformance.MessageConformanceEventModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new Conformance.MessageConformanceEventModeEnumFactory(), false);
      }
      if (element.getProtocol().size() > 0) {
        openArray("protocol");
        for (Coding e : element.getProtocol()) 
          composeCoding(null, e);
        closeArray();
      };
      composeCodeCore("focus", element.getFocusElement(), false);
      composeCodeExtras("focus", element.getFocusElement(), false);
      composeReference("request", element.getRequest());
      composeReference("response", element.getResponse());
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeConformanceConformanceDocumentComponentInner(element);
      close();
    }
  }

  private void composeConformanceConformanceDocumentComponentInner(Conformance.ConformanceDocumentComponent element) throws Exception {
      composeBackbone(element);
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new Conformance.DocumentModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new Conformance.DocumentModeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
      composeReference("profile", element.getProfile());
  }

  private void composeContract(String name, Contract element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeContractInner(element);
    }
  }

  private void composeContractInner(Contract element) throws Exception {
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("subtype", element.getSubtype());
      composeDateTimeCore("issued", element.getIssuedElement(), false);
      composeDateTimeExtras("issued", element.getIssuedElement(), false);
      composePeriod("applies", element.getApplies());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (Reference e : element.getAuthor()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getGrantor().size() > 0) {
        openArray("grantor");
        for (Reference e : element.getGrantor()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getGrantee().size() > 0) {
        openArray("grantee");
        for (Reference e : element.getGrantee()) 
          composeReference(null, e);
        closeArray();
      };
      if (element.getWitness().size() > 0) {
        openArray("witness");
        for (Reference e : element.getWitness()) 
          composeReference(null, e);
        closeArray();
      };
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getTerm().size() > 0) {
        openArray("term");
        for (Contract.ContractTermComponent e : element.getTerm()) 
          composeContractContractTermComponent(null, e);
        closeArray();
      };
      composeAttachment("friendly", element.getFriendly());
      composeAttachment("legal", element.getLegal());
      composeAttachment("rule", element.getRule());
  }

  private void composeContractContractTermComponent(String name, Contract.ContractTermComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeContractContractTermComponentInner(element);
      close();
    }
  }

  private void composeContractContractTermComponentInner(Contract.ContractTermComponent element) throws Exception {
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("subtype", element.getSubtype());
      composeReference("subject", element.getSubject());
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
  }

  private void composeContraindication(String name, Contraindication element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeContraindicationInner(element);
    }
  }

  private void composeContraindicationInner(Contraindication element) throws Exception {
      composeDomainResourceElements(element);
      composeReference("patient", element.getPatient());
      composeCodeableConcept("category", element.getCategory());
      composeCodeCore("severity", element.getSeverityElement(), false);
      composeCodeExtras("severity", element.getSeverityElement(), false);
      if (element.getImplicated().size() > 0) {
        openArray("implicated");
        for (Reference e : element.getImplicated()) 
          composeReference(null, e);
        closeArray();
      };
      composeStringCore("detail", element.getDetailElement(), false);
      composeStringExtras("detail", element.getDetailElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeReference("author", element.getAuthor());
      composeIdentifier("identifier", element.getIdentifier());
      composeUriCore("reference", element.getReferenceElement(), false);
      composeUriExtras("reference", element.getReferenceElement(), false);
      if (element.getMitigation().size() > 0) {
        openArray("mitigation");
        for (Contraindication.ContraindicationMitigationComponent e : element.getMitigation()) 
          composeContraindicationContraindicationMitigationComponent(null, e);
        closeArray();
      };
  }

  private void composeContraindicationContraindicationMitigationComponent(String name, Contraindication.ContraindicationMitigationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeContraindicationContraindicationMitigationComponentInner(element);
      close();
    }
  }

  private void composeContraindicationContraindicationMitigationComponentInner(Contraindication.ContraindicationMitigationComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("action", element.getAction());
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeReference("author", element.getAuthor());
  }

  private void composeCoverage(String name, Coverage element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeCoverageInner(element);
    }
  }

  private void composeCoverageInner(Coverage element) throws Exception {
      composeDomainResourceElements(element);
      composeReference("issuer", element.getIssuer());
      composePeriod("period", element.getPeriod());
      composeCoding("type", element.getType());
      composeIdentifier("identifier", element.getIdentifier());
      composeStringCore("group", element.getGroupElement(), false);
      composeStringExtras("group", element.getGroupElement(), false);
      composeStringCore("plan", element.getPlanElement(), false);
      composeStringExtras("plan", element.getPlanElement(), false);
      composeStringCore("subplan", element.getSubplanElement(), false);
      composeStringExtras("subplan", element.getSubplanElement(), false);
      composeIntegerCore("dependent", element.getDependentElement(), false);
      composeIntegerExtras("dependent", element.getDependentElement(), false);
      composeIntegerCore("sequence", element.getSequenceElement(), false);
      composeIntegerExtras("sequence", element.getSequenceElement(), false);
      composeReference("subscriber", element.getSubscriber());
      composeIdentifier("network", element.getNetwork());
      if (element.getContract().size() > 0) {
        openArray("contract");
        for (Reference e : element.getContract()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeDataElement(String name, DataElement element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDataElementInner(element);
    }
  }

  private void composeDataElementInner(DataElement element) throws Exception {
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DataElement.ResourceObservationDefStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DataElement.ResourceObservationDefStatusEnumFactory(), false);
      }
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
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
      composeStringCore("question", element.getQuestionElement(), false);
      composeStringExtras("question", element.getQuestionElement(), false);
      composeStringCore("definition", element.getDefinitionElement(), false);
      composeStringExtras("definition", element.getDefinitionElement(), false);
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
      composeStringCore("requirements", element.getRequirementsElement(), false);
      composeStringExtras("requirements", element.getRequirementsElement(), false);
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
      composeCodeCore("type", element.getTypeElement(), false);
      composeCodeExtras("type", element.getTypeElement(), false);
      composeType("example", element.getExample());
      composeIntegerCore("maxLength", element.getMaxLengthElement(), false);
      composeIntegerExtras("maxLength", element.getMaxLengthElement(), false);
      composeCodeableConcept("units", element.getUnits());
      composeDataElementDataElementBindingComponent("binding", element.getBinding());
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (DataElement.DataElementMappingComponent e : element.getMapping()) 
          composeDataElementDataElementMappingComponent(null, e);
        closeArray();
      };
  }

  private void composeDataElementDataElementBindingComponent(String name, DataElement.DataElementBindingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDataElementDataElementBindingComponentInner(element);
      close();
    }
  }

  private void composeDataElementDataElementBindingComponentInner(DataElement.DataElementBindingComponent element) throws Exception {
      composeBackbone(element);
      composeBooleanCore("isExtensible", element.getIsExtensibleElement(), false);
      composeBooleanExtras("isExtensible", element.getIsExtensibleElement(), false);
      if (element.getConformanceElement() != null) {
        composeEnumerationCore("conformance", element.getConformanceElement(), new DataElement.BindingConformanceEnumFactory(), false);
        composeEnumerationExtras("conformance", element.getConformanceElement(), new DataElement.BindingConformanceEnumFactory(), false);
      }
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeReference("valueSet", element.getValueSet());
  }

  private void composeDataElementDataElementMappingComponent(String name, DataElement.DataElementMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDataElementDataElementMappingComponentInner(element);
      close();
    }
  }

  private void composeDataElementDataElementMappingComponentInner(DataElement.DataElementMappingComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("uri", element.getUriElement(), false);
      composeUriExtras("uri", element.getUriElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
      composeStringCore("map", element.getMapElement(), false);
      composeStringExtras("map", element.getMapElement(), false);
  }

  private void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDeviceInner(element);
    }
  }

  private void composeDeviceInner(Device element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      composeStringCore("manufacturer", element.getManufacturerElement(), false);
      composeStringExtras("manufacturer", element.getManufacturerElement(), false);
      composeStringCore("model", element.getModelElement(), false);
      composeStringExtras("model", element.getModelElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeDateCore("expiry", element.getExpiryElement(), false);
      composeDateExtras("expiry", element.getExpiryElement(), false);
      composeStringCore("udi", element.getUdiElement(), false);
      composeStringExtras("udi", element.getUdiElement(), false);
      composeStringCore("lotNumber", element.getLotNumberElement(), false);
      composeStringExtras("lotNumber", element.getLotNumberElement(), false);
      composeReference("owner", element.getOwner());
      composeReference("location", element.getLocation());
      composeReference("patient", element.getPatient());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (ContactPoint e : element.getContact()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
  }

  private void composeDeviceComponent(String name, DeviceComponent element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDeviceComponentInner(element);
    }
  }

  private void composeDeviceComponentInner(DeviceComponent element) throws Exception {
      composeDomainResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeIdentifier("identifier", element.getIdentifier());
      composeInstantCore("lastSystemChange", element.getLastSystemChangeElement(), false);
      composeInstantExtras("lastSystemChange", element.getLastSystemChangeElement(), false);
      composeReference("source", element.getSource());
      composeReference("parent", element.getParent());
      if (element.getOperationalStatus().size() > 0) {
        openArray("operationalStatus");
        for (CodeableConcept e : element.getOperationalStatus()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeCodeableConcept("parameterGroup", element.getParameterGroup());
      composeCodeableConcept("measurementPrinciple", element.getMeasurementPrinciple());
      if (element.getProductionSpecification().size() > 0) {
        openArray("productionSpecification");
        for (DeviceComponent.DeviceComponentProductionSpecificationComponent e : element.getProductionSpecification()) 
          composeDeviceComponentDeviceComponentProductionSpecificationComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("languageCode", element.getLanguageCode());
  }

  private void composeDeviceComponentDeviceComponentProductionSpecificationComponent(String name, DeviceComponent.DeviceComponentProductionSpecificationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDeviceComponentDeviceComponentProductionSpecificationComponentInner(element);
      close();
    }
  }

  private void composeDeviceComponentDeviceComponentProductionSpecificationComponentInner(DeviceComponent.DeviceComponentProductionSpecificationComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("specType", element.getSpecType());
      composeIdentifier("componentId", element.getComponentId());
      composeStringCore("productionSpec", element.getProductionSpecElement(), false);
      composeStringExtras("productionSpec", element.getProductionSpecElement(), false);
  }

  private void composeDeviceUseRequest(String name, DeviceUseRequest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDeviceUseRequestInner(element);
    }
  }

  private void composeDeviceUseRequestInner(DeviceUseRequest element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getBodySite().size() > 0) {
        openArray("bodySite");
        for (CodeableConcept e : element.getBodySite()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DeviceUseRequest.DeviceUseRequestStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DeviceUseRequest.DeviceUseRequestStatusEnumFactory(), false);
      }
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new DeviceUseRequest.DeviceUseRequestModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new DeviceUseRequest.DeviceUseRequestModeEnumFactory(), false);
      }
      composeReference("device", element.getDevice());
      composeReference("encounter", element.getEncounter());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getIndication().size() > 0) {
        openArray("indication");
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getNotes().size() > 0) {
        openArray("notes");
        for (StringType e : element.getNotes()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getNotes())) {
          openArray("_notes");
          for (StringType e : element.getNotes()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getPrnReason().size() > 0) {
        openArray("prnReason");
        for (CodeableConcept e : element.getPrnReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeDateTimeCore("orderedOn", element.getOrderedOnElement(), false);
      composeDateTimeExtras("orderedOn", element.getOrderedOnElement(), false);
      composeDateTimeCore("recordedOn", element.getRecordedOnElement(), false);
      composeDateTimeExtras("recordedOn", element.getRecordedOnElement(), false);
      composeReference("subject", element.getSubject());
      composeType("timing", element.getTiming());
      if (element.getPriorityElement() != null) {
        composeEnumerationCore("priority", element.getPriorityElement(), new DeviceUseRequest.DeviceUseRequestPriorityEnumFactory(), false);
        composeEnumerationExtras("priority", element.getPriorityElement(), new DeviceUseRequest.DeviceUseRequestPriorityEnumFactory(), false);
      }
  }

  private void composeDeviceUseStatement(String name, DeviceUseStatement element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDeviceUseStatementInner(element);
    }
  }

  private void composeDeviceUseStatementInner(DeviceUseStatement element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getBodySite().size() > 0) {
        openArray("bodySite");
        for (CodeableConcept e : element.getBodySite()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("whenUsed", element.getWhenUsed());
      composeReference("device", element.getDevice());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getIndication().size() > 0) {
        openArray("indication");
        for (CodeableConcept e : element.getIndication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getNotes().size() > 0) {
        openArray("notes");
        for (StringType e : element.getNotes()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getNotes())) {
          openArray("_notes");
          for (StringType e : element.getNotes()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeDateTimeCore("recordedOn", element.getRecordedOnElement(), false);
      composeDateTimeExtras("recordedOn", element.getRecordedOnElement(), false);
      composeReference("subject", element.getSubject());
      composeType("timing", element.getTiming());
  }

  private void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDiagnosticOrderInner(element);
    }
  }

  private void composeDiagnosticOrderInner(DiagnosticOrder element) throws Exception {
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeReference("orderer", element.getOrderer());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
      composeStringCore("clinicalNotes", element.getClinicalNotesElement(), false);
      composeStringExtras("clinicalNotes", element.getClinicalNotesElement(), false);
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
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      if (element.getPriorityElement() != null) {
        composeEnumerationCore("priority", element.getPriorityElement(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory(), false);
        composeEnumerationExtras("priority", element.getPriorityElement(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory(), false);
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

  private void composeDiagnosticOrderDiagnosticOrderEventComponent(String name, DiagnosticOrder.DiagnosticOrderEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDiagnosticOrderDiagnosticOrderEventComponentInner(element);
      close();
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderEventComponentInner(DiagnosticOrder.DiagnosticOrderEventComponent element) throws Exception {
      composeBackbone(element);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      composeCodeableConcept("description", element.getDescription());
      composeDateTimeCore("dateTime", element.getDateTimeElement(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeElement(), false);
      composeReference("actor", element.getActor());
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDiagnosticOrderDiagnosticOrderItemComponentInner(element);
      close();
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponentInner(DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (Reference e : element.getSpecimen()) 
          composeReference(null, e);
        closeArray();
      };
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory(), false);
      }
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent(null, e);
        closeArray();
      };
  }

  private void composeDiagnosticReport(String name, DiagnosticReport element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDiagnosticReportInner(element);
    }
  }

  private void composeDiagnosticReportInner(DiagnosticReport element) throws Exception {
      composeDomainResourceElements(element);
      composeCodeableConcept("name", element.getName());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DiagnosticReport.DiagnosticReportStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DiagnosticReport.DiagnosticReportStatusEnumFactory(), false);
      }
      composeDateTimeCore("issued", element.getIssuedElement(), false);
      composeDateTimeExtras("issued", element.getIssuedElement(), false);
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
      composeStringCore("conclusion", element.getConclusionElement(), false);
      composeStringExtras("conclusion", element.getConclusionElement(), false);
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

  private void composeDiagnosticReportDiagnosticReportImageComponent(String name, DiagnosticReport.DiagnosticReportImageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDiagnosticReportDiagnosticReportImageComponentInner(element);
      close();
    }
  }

  private void composeDiagnosticReportDiagnosticReportImageComponentInner(DiagnosticReport.DiagnosticReportImageComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      composeReference("link", element.getLink());
  }

  private void composeDocumentManifest(String name, DocumentManifest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDocumentManifestInner(element);
    }
  }

  private void composeDocumentManifestInner(DocumentManifest element) throws Exception {
      composeDomainResourceElements(element);
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
      composeDateTimeCore("created", element.getCreatedElement(), false);
      composeDateTimeExtras("created", element.getCreatedElement(), false);
      composeUriCore("source", element.getSourceElement(), false);
      composeUriExtras("source", element.getSourceElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DocumentManifest.DocumentReferenceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DocumentManifest.DocumentReferenceStatusEnumFactory(), false);
      }
      composeReference("supercedes", element.getSupercedes());
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeCodeableConcept("confidentiality", element.getConfidentiality());
      if (element.getContent().size() > 0) {
        openArray("content");
        for (Reference e : element.getContent()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeDocumentReference(String name, DocumentReference element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeDocumentReferenceInner(element);
    }
  }

  private void composeDocumentReferenceInner(DocumentReference element) throws Exception {
      composeDomainResourceElements(element);
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
      composeUriCore("policyManager", element.getPolicyManagerElement(), false);
      composeUriExtras("policyManager", element.getPolicyManagerElement(), false);
      composeReference("authenticator", element.getAuthenticator());
      composeDateTimeCore("created", element.getCreatedElement(), false);
      composeDateTimeExtras("created", element.getCreatedElement(), false);
      composeInstantCore("indexed", element.getIndexedElement(), false);
      composeInstantExtras("indexed", element.getIndexedElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new DocumentReference.DocumentReferenceStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new DocumentReference.DocumentReferenceStatusEnumFactory(), false);
      }
      composeCodeableConcept("docStatus", element.getDocStatus());
      if (element.getRelatesTo().size() > 0) {
        openArray("relatesTo");
        for (DocumentReference.DocumentReferenceRelatesToComponent e : element.getRelatesTo()) 
          composeDocumentReferenceDocumentReferenceRelatesToComponent(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getConfidentiality().size() > 0) {
        openArray("confidentiality");
        for (CodeableConcept e : element.getConfidentiality()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeCodeCore("primaryLanguage", element.getPrimaryLanguageElement(), false);
      composeCodeExtras("primaryLanguage", element.getPrimaryLanguageElement(), false);
      composeCodeCore("mimeType", element.getMimeTypeElement(), false);
      composeCodeExtras("mimeType", element.getMimeTypeElement(), false);
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
      composeIntegerCore("size", element.getSizeElement(), false);
      composeIntegerExtras("size", element.getSizeElement(), false);
      composeBase64BinaryCore("hash", element.getHashElement(), false);
      composeBase64BinaryExtras("hash", element.getHashElement(), false);
      composeUriCore("location", element.getLocationElement(), false);
      composeUriExtras("location", element.getLocationElement(), false);
      composeDocumentReferenceDocumentReferenceServiceComponent("service", element.getService());
      composeDocumentReferenceDocumentReferenceContextComponent("context", element.getContext());
  }

  private void composeDocumentReferenceDocumentReferenceRelatesToComponent(String name, DocumentReference.DocumentReferenceRelatesToComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDocumentReferenceDocumentReferenceRelatesToComponentInner(element);
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceRelatesToComponentInner(DocumentReference.DocumentReferenceRelatesToComponent element) throws Exception {
      composeBackbone(element);
      if (element.getCodeElement() != null) {
        composeEnumerationCore("code", element.getCodeElement(), new DocumentReference.DocumentRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeElement(), new DocumentReference.DocumentRelationshipTypeEnumFactory(), false);
      }
      composeReference("target", element.getTarget());
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDocumentReferenceDocumentReferenceServiceComponentInner(element);
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponentInner(DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeStringCore("address", element.getAddressElement(), false);
      composeStringExtras("address", element.getAddressElement(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (DocumentReference.DocumentReferenceServiceParameterComponent e : element.getParameter()) 
          composeDocumentReferenceDocumentReferenceServiceParameterComponent(null, e);
        closeArray();
      };
  }

  private void composeDocumentReferenceDocumentReferenceServiceParameterComponent(String name, DocumentReference.DocumentReferenceServiceParameterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDocumentReferenceDocumentReferenceServiceParameterComponentInner(element);
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceParameterComponentInner(DocumentReference.DocumentReferenceServiceParameterComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("value", element.getValueElement(), false);
      composeStringExtras("value", element.getValueElement(), false);
  }

  private void composeDocumentReferenceDocumentReferenceContextComponent(String name, DocumentReference.DocumentReferenceContextComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeDocumentReferenceDocumentReferenceContextComponentInner(element);
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceContextComponentInner(DocumentReference.DocumentReferenceContextComponent element) throws Exception {
      composeBackbone(element);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (CodeableConcept e : element.getEvent()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeCodeableConcept("facilityType", element.getFacilityType());
  }

  private void composeEligibility(String name, Eligibility element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeEligibilityInner(element);
    }
  }

  private void composeEligibilityInner(Eligibility element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDateCore("date", element.getDateElement(), false);
      composeDateExtras("date", element.getDateElement(), false);
      composeReference("target", element.getTarget());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
  }

  private void composeEncounter(String name, Encounter element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeEncounterInner(element);
    }
  }

  private void composeEncounterInner(Encounter element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Encounter.EncounterStateEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Encounter.EncounterStateEnumFactory(), false);
      }
      if (element.getClass_Element() != null) {
        composeEnumerationCore("class", element.getClass_Element(), new Encounter.EncounterClassEnumFactory(), false);
        composeEnumerationExtras("class", element.getClass_Element(), new Encounter.EncounterClassEnumFactory(), false);
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

  private void composeEncounterEncounterParticipantComponent(String name, Encounter.EncounterParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeEncounterEncounterParticipantComponentInner(element);
      close();
    }
  }

  private void composeEncounterEncounterParticipantComponentInner(Encounter.EncounterParticipantComponent element) throws Exception {
      composeBackbone(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeReference("individual", element.getIndividual());
  }

  private void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeEncounterEncounterHospitalizationComponentInner(element);
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationComponentInner(Encounter.EncounterHospitalizationComponent element) throws Exception {
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
      composeBooleanCore("reAdmission", element.getReAdmissionElement(), false);
      composeBooleanExtras("reAdmission", element.getReAdmissionElement(), false);
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponent(String name, Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeEncounterEncounterHospitalizationAccomodationComponentInner(element);
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponentInner(Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
      composeBackbone(element);
      composeReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
  }

  private void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeEncounterEncounterLocationComponentInner(element);
      close();
    }
  }

  private void composeEncounterEncounterLocationComponentInner(Encounter.EncounterLocationComponent element) throws Exception {
      composeBackbone(element);
      composeReference("location", element.getLocation());
      composePeriod("period", element.getPeriod());
  }

  private void composeExplanationOfBenefit(String name, ExplanationOfBenefit element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeExplanationOfBenefitInner(element);
    }
  }

  private void composeExplanationOfBenefitInner(ExplanationOfBenefit element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("request", element.getRequest());
      if (element.getRequestIdentifier().size() > 0) {
        openArray("requestIdentifier");
        for (Identifier e : element.getRequestIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getOutcomeElement() != null) {
        composeEnumerationCore("outcome", element.getOutcomeElement(), new ExplanationOfBenefit.RSLinkEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcomeElement(), new ExplanationOfBenefit.RSLinkEnumFactory(), false);
      }
      composeStringCore("disposition", element.getDispositionElement(), false);
      composeStringExtras("disposition", element.getDispositionElement(), false);
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDateCore("date", element.getDateElement(), false);
      composeDateExtras("date", element.getDateElement(), false);
      composeReference("organization", element.getOrganization());
      composeReference("requestProvider", element.getRequestProvider());
      composeReference("requestOrganization", element.getRequestOrganization());
  }

  private void composeExtensionDefinition(String name, ExtensionDefinition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeExtensionDefinitionInner(element);
    }
  }

  private void composeExtensionDefinitionInner(ExtensionDefinition element) throws Exception {
      composeDomainResourceElements(element);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new ExtensionDefinition.ResourceProfileStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new ExtensionDefinition.ResourceProfileStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalElement(), false);
      composeBooleanExtras("experimental", element.getExperimentalElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeStringCore("requirements", element.getRequirementsElement(), false);
      composeStringExtras("requirements", element.getRequirementsElement(), false);
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (ExtensionDefinition.ExtensionDefinitionMappingComponent e : element.getMapping()) 
          composeExtensionDefinitionExtensionDefinitionMappingComponent(null, e);
        closeArray();
      };
      if (element.getContextTypeElement() != null) {
        composeEnumerationCore("contextType", element.getContextTypeElement(), new ExtensionDefinition.ExtensionContextEnumFactory(), false);
        composeEnumerationExtras("contextType", element.getContextTypeElement(), new ExtensionDefinition.ExtensionContextEnumFactory(), false);
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
        for (ElementDefinition e : element.getElement()) 
          composeElementDefinition(null, e);
        closeArray();
      };
  }

  private void composeExtensionDefinitionExtensionDefinitionMappingComponent(String name, ExtensionDefinition.ExtensionDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeExtensionDefinitionExtensionDefinitionMappingComponentInner(element);
      close();
    }
  }

  private void composeExtensionDefinitionExtensionDefinitionMappingComponentInner(ExtensionDefinition.ExtensionDefinitionMappingComponent element) throws Exception {
      composeBackbone(element);
      composeIdCore("identity", element.getIdentityElement(), false);
      composeIdExtras("identity", element.getIdentityElement(), false);
      composeUriCore("uri", element.getUriElement(), false);
      composeUriExtras("uri", element.getUriElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
  }

  private void composeFamilyHistory(String name, FamilyHistory element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeFamilyHistoryInner(element);
    }
  }

  private void composeFamilyHistoryInner(FamilyHistory element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("patient", element.getPatient());
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeStringCore("note", element.getNoteElement(), false);
      composeStringExtras("note", element.getNoteElement(), false);
      if (element.getRelation().size() > 0) {
        openArray("relation");
        for (FamilyHistory.FamilyHistoryRelationComponent e : element.getRelation()) 
          composeFamilyHistoryFamilyHistoryRelationComponent(null, e);
        closeArray();
      };
  }

  private void composeFamilyHistoryFamilyHistoryRelationComponent(String name, FamilyHistory.FamilyHistoryRelationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeFamilyHistoryFamilyHistoryRelationComponentInner(element);
      close();
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationComponentInner(FamilyHistory.FamilyHistoryRelationComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("born", element.getBorn());
      composeType("age", element.getAge());
      composeType("deceased", element.getDeceased());
      composeStringCore("note", element.getNoteElement(), false);
      composeStringExtras("note", element.getNoteElement(), false);
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (FamilyHistory.FamilyHistoryRelationConditionComponent e : element.getCondition()) 
          composeFamilyHistoryFamilyHistoryRelationConditionComponent(null, e);
        closeArray();
      };
  }

  private void composeFamilyHistoryFamilyHistoryRelationConditionComponent(String name, FamilyHistory.FamilyHistoryRelationConditionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeFamilyHistoryFamilyHistoryRelationConditionComponentInner(element);
      close();
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationConditionComponentInner(FamilyHistory.FamilyHistoryRelationConditionComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("onset", element.getOnset());
      composeStringCore("note", element.getNoteElement(), false);
      composeStringExtras("note", element.getNoteElement(), false);
  }

  private void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeGroupInner(element);
    }
  }

  private void composeGroupInner(Group element) throws Exception {
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Group.GroupTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Group.GroupTypeEnumFactory(), false);
      }
      composeBooleanCore("actual", element.getActualElement(), false);
      composeBooleanExtras("actual", element.getActualElement(), false);
      composeCodeableConcept("code", element.getCode());
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeIntegerCore("quantity", element.getQuantityElement(), false);
      composeIntegerExtras("quantity", element.getQuantityElement(), false);
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

  private void composeGroupGroupCharacteristicComponent(String name, Group.GroupCharacteristicComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeGroupGroupCharacteristicComponentInner(element);
      close();
    }
  }

  private void composeGroupGroupCharacteristicComponentInner(Group.GroupCharacteristicComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeType("value", element.getValue());
      composeBooleanCore("exclude", element.getExcludeElement(), false);
      composeBooleanExtras("exclude", element.getExcludeElement(), false);
  }

  private void composeHealthcareService(String name, HealthcareService element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeHealthcareServiceInner(element);
    }
  }

  private void composeHealthcareServiceInner(HealthcareService element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("location", element.getLocation());
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      if (element.getServiceType().size() > 0) {
        openArray("serviceType");
        for (HealthcareService.ServiceTypeComponent e : element.getServiceType()) 
          composeHealthcareServiceServiceTypeComponent(null, e);
        closeArray();
      };
      composeStringCore("serviceName", element.getServiceNameElement(), false);
      composeStringExtras("serviceName", element.getServiceNameElement(), false);
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      composeStringCore("extraDetails", element.getExtraDetailsElement(), false);
      composeStringExtras("extraDetails", element.getExtraDetailsElement(), false);
      composeCodeableConcept("freeProvisionCode", element.getFreeProvisionCode());
      composeCodeableConcept("eligibility", element.getEligibility());
      composeStringCore("eligibilityNote", element.getEligibilityNoteElement(), false);
      composeStringExtras("eligibilityNote", element.getEligibilityNoteElement(), false);
      composeCodeableConcept("appointmentRequired", element.getAppointmentRequired());
      composeUriCore("imageURI", element.getImageURIElement(), false);
      composeUriExtras("imageURI", element.getImageURIElement(), false);
      if (element.getAvailableTime().size() > 0) {
        openArray("availableTime");
        for (HealthcareService.HealthcareServiceAvailableTimeComponent e : element.getAvailableTime()) 
          composeHealthcareServiceHealthcareServiceAvailableTimeComponent(null, e);
        closeArray();
      };
      if (element.getNotAvailableTime().size() > 0) {
        openArray("notAvailableTime");
        for (HealthcareService.HealthcareServiceNotAvailableTimeComponent e : element.getNotAvailableTime()) 
          composeHealthcareServiceHealthcareServiceNotAvailableTimeComponent(null, e);
        closeArray();
      };
      composeStringCore("availabilityExceptions", element.getAvailabilityExceptionsElement(), false);
      composeStringExtras("availabilityExceptions", element.getAvailabilityExceptionsElement(), false);
      composeStringCore("publicKey", element.getPublicKeyElement(), false);
      composeStringExtras("publicKey", element.getPublicKeyElement(), false);
      if (element.getProgramName().size() > 0) {
        openArray("programName");
        for (StringType e : element.getProgramName()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getProgramName())) {
          openArray("_programName");
          for (StringType e : element.getProgramName()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      if (element.getContactPoint().size() > 0) {
        openArray("contactPoint");
        for (ContactPoint e : element.getContactPoint()) 
          composeContactPoint(null, e);
        closeArray();
      };
      if (element.getCharacteristic().size() > 0) {
        openArray("characteristic");
        for (CodeableConcept e : element.getCharacteristic()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getReferralMethod().size() > 0) {
        openArray("referralMethod");
        for (CodeableConcept e : element.getReferralMethod()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getSetting().size() > 0) {
        openArray("setting");
        for (CodeableConcept e : element.getSetting()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getTargetGroup().size() > 0) {
        openArray("targetGroup");
        for (CodeableConcept e : element.getTargetGroup()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getCoverageArea().size() > 0) {
        openArray("coverageArea");
        for (CodeableConcept e : element.getCoverageArea()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getCatchmentArea().size() > 0) {
        openArray("catchmentArea");
        for (CodeableConcept e : element.getCatchmentArea()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getServiceCode().size() > 0) {
        openArray("serviceCode");
        for (CodeableConcept e : element.getServiceCode()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
  }

  private void composeHealthcareServiceServiceTypeComponent(String name, HealthcareService.ServiceTypeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeHealthcareServiceServiceTypeComponentInner(element);
      close();
    }
  }

  private void composeHealthcareServiceServiceTypeComponentInner(HealthcareService.ServiceTypeComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      if (element.getSpecialty().size() > 0) {
        openArray("specialty");
        for (CodeableConcept e : element.getSpecialty()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
  }

  private void composeHealthcareServiceHealthcareServiceAvailableTimeComponent(String name, HealthcareService.HealthcareServiceAvailableTimeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeHealthcareServiceHealthcareServiceAvailableTimeComponentInner(element);
      close();
    }
  }

  private void composeHealthcareServiceHealthcareServiceAvailableTimeComponentInner(HealthcareService.HealthcareServiceAvailableTimeComponent element) throws Exception {
      composeBackbone(element);
      if (element.getDaysOfWeek().size() > 0) {
        openArray("daysOfWeek");
        for (CodeableConcept e : element.getDaysOfWeek()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeBooleanCore("allDay", element.getAllDayElement(), false);
      composeBooleanExtras("allDay", element.getAllDayElement(), false);
      composeDateTimeCore("availableStartTime", element.getAvailableStartTimeElement(), false);
      composeDateTimeExtras("availableStartTime", element.getAvailableStartTimeElement(), false);
      composeDateTimeCore("availableEndTime", element.getAvailableEndTimeElement(), false);
      composeDateTimeExtras("availableEndTime", element.getAvailableEndTimeElement(), false);
  }

  private void composeHealthcareServiceHealthcareServiceNotAvailableTimeComponent(String name, HealthcareService.HealthcareServiceNotAvailableTimeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeHealthcareServiceHealthcareServiceNotAvailableTimeComponentInner(element);
      close();
    }
  }

  private void composeHealthcareServiceHealthcareServiceNotAvailableTimeComponentInner(HealthcareService.HealthcareServiceNotAvailableTimeComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeDateTimeCore("startDate", element.getStartDateElement(), false);
      composeDateTimeExtras("startDate", element.getStartDateElement(), false);
      composeDateTimeCore("endDate", element.getEndDateElement(), false);
      composeDateTimeExtras("endDate", element.getEndDateElement(), false);
  }

  private void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeImagingStudyInner(element);
    }
  }

  private void composeImagingStudyInner(ImagingStudy element) throws Exception {
      composeDomainResourceElements(element);
      composeDateTimeCore("started", element.getStartedElement(), false);
      composeDateTimeExtras("started", element.getStartedElement(), false);
      composeReference("subject", element.getSubject());
      composeOidCore("uid", element.getUidElement(), false);
      composeOidExtras("uid", element.getUidElement(), false);
      composeIdentifier("accession", element.getAccession());
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
      if (element.getModalityList().size() > 0) {
        openArray("modalityList");
        for (Enumeration<ImagingStudy.ImagingModality> e : element.getModalityList()) 
          composeEnumerationCore(null, e, new ImagingStudy.ImagingModalityEnumFactory(), true);
        closeArray();
        if (anyHasExtras(element.getModalityList())) {
          openArray("_modalityList");
          for (Enumeration<ImagingStudy.ImagingModality> e : element.getModalityList()) 
            composeEnumerationExtras(null, e, new ImagingStudy.ImagingModalityEnumFactory(), true);
          closeArray();
        }
      };
      composeReference("referrer", element.getReferrer());
      if (element.getAvailabilityElement() != null) {
        composeEnumerationCore("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
        composeEnumerationExtras("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
      }
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      composeIntegerCore("numberOfSeries", element.getNumberOfSeriesElement(), false);
      composeIntegerExtras("numberOfSeries", element.getNumberOfSeriesElement(), false);
      composeIntegerCore("numberOfInstances", element.getNumberOfInstancesElement(), false);
      composeIntegerExtras("numberOfInstances", element.getNumberOfInstancesElement(), false);
      composeStringCore("clinicalInformation", element.getClinicalInformationElement(), false);
      composeStringExtras("clinicalInformation", element.getClinicalInformationElement(), false);
      if (element.getProcedure().size() > 0) {
        openArray("procedure");
        for (Coding e : element.getProcedure()) 
          composeCoding(null, e);
        closeArray();
      };
      composeReference("interpreter", element.getInterpreter());
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getSeries().size() > 0) {
        openArray("series");
        for (ImagingStudy.ImagingStudySeriesComponent e : element.getSeries()) 
          composeImagingStudyImagingStudySeriesComponent(null, e);
        closeArray();
      };
  }

  private void composeImagingStudyImagingStudySeriesComponent(String name, ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImagingStudyImagingStudySeriesComponentInner(element);
      close();
    }
  }

  private void composeImagingStudyImagingStudySeriesComponentInner(ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("number", element.getNumberElement(), false);
      composeIntegerExtras("number", element.getNumberElement(), false);
      if (element.getModalityElement() != null) {
        composeEnumerationCore("modality", element.getModalityElement(), new ImagingStudy.ModalityEnumFactory(), false);
        composeEnumerationExtras("modality", element.getModalityElement(), new ImagingStudy.ModalityEnumFactory(), false);
      }
      composeOidCore("uid", element.getUidElement(), false);
      composeOidExtras("uid", element.getUidElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeIntegerCore("numberOfInstances", element.getNumberOfInstancesElement(), false);
      composeIntegerExtras("numberOfInstances", element.getNumberOfInstancesElement(), false);
      if (element.getAvailabilityElement() != null) {
        composeEnumerationCore("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
        composeEnumerationExtras("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory(), false);
      }
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      composeCoding("bodySite", element.getBodySite());
      composeDateTimeCore("dateTime", element.getDateTimeElement(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeElement(), false);
      if (element.getInstance().size() > 0) {
        openArray("instance");
        for (ImagingStudy.ImagingStudySeriesInstanceComponent e : element.getInstance()) 
          composeImagingStudyImagingStudySeriesInstanceComponent(null, e);
        closeArray();
      };
  }

  private void composeImagingStudyImagingStudySeriesInstanceComponent(String name, ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImagingStudyImagingStudySeriesInstanceComponentInner(element);
      close();
    }
  }

  private void composeImagingStudyImagingStudySeriesInstanceComponentInner(ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("number", element.getNumberElement(), false);
      composeIntegerExtras("number", element.getNumberElement(), false);
      composeOidCore("uid", element.getUidElement(), false);
      composeOidExtras("uid", element.getUidElement(), false);
      composeOidCore("sopclass", element.getSopclassElement(), false);
      composeOidExtras("sopclass", element.getSopclassElement(), false);
      composeStringCore("type", element.getTypeElement(), false);
      composeStringExtras("type", element.getTypeElement(), false);
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      composeReference("attachment", element.getAttachment());
  }

  private void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeImmunizationInner(element);
    }
  }

  private void composeImmunizationInner(Immunization element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeReference("subject", element.getSubject());
      composeBooleanCore("refusedIndicator", element.getRefusedIndicatorElement(), false);
      composeBooleanExtras("refusedIndicator", element.getRefusedIndicatorElement(), false);
      composeBooleanCore("reported", element.getReportedElement(), false);
      composeBooleanExtras("reported", element.getReportedElement(), false);
      composeReference("performer", element.getPerformer());
      composeReference("requester", element.getRequester());
      composeReference("manufacturer", element.getManufacturer());
      composeReference("location", element.getLocation());
      composeStringCore("lotNumber", element.getLotNumberElement(), false);
      composeStringExtras("lotNumber", element.getLotNumberElement(), false);
      composeDateCore("expirationDate", element.getExpirationDateElement(), false);
      composeDateExtras("expirationDate", element.getExpirationDateElement(), false);
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

  private void composeImmunizationImmunizationExplanationComponent(String name, Immunization.ImmunizationExplanationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImmunizationImmunizationExplanationComponentInner(element);
      close();
    }
  }

  private void composeImmunizationImmunizationExplanationComponentInner(Immunization.ImmunizationExplanationComponent element) throws Exception {
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
  }

  private void composeImmunizationImmunizationReactionComponent(String name, Immunization.ImmunizationReactionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImmunizationImmunizationReactionComponentInner(element);
      close();
    }
  }

  private void composeImmunizationImmunizationReactionComponentInner(Immunization.ImmunizationReactionComponent element) throws Exception {
      composeBackbone(element);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeReference("detail", element.getDetail());
      composeBooleanCore("reported", element.getReportedElement(), false);
      composeBooleanExtras("reported", element.getReportedElement(), false);
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImmunizationImmunizationVaccinationProtocolComponentInner(element);
      close();
    }
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponentInner(Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("doseSequence", element.getDoseSequenceElement(), false);
      composeIntegerExtras("doseSequence", element.getDoseSequenceElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeReference("authority", element.getAuthority());
      composeStringCore("series", element.getSeriesElement(), false);
      composeStringExtras("series", element.getSeriesElement(), false);
      composeIntegerCore("seriesDoses", element.getSeriesDosesElement(), false);
      composeIntegerExtras("seriesDoses", element.getSeriesDosesElement(), false);
      composeCodeableConcept("doseTarget", element.getDoseTarget());
      composeCodeableConcept("doseStatus", element.getDoseStatus());
      composeCodeableConcept("doseStatusReason", element.getDoseStatusReason());
  }

  private void composeImmunizationRecommendation(String name, ImmunizationRecommendation element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeImmunizationRecommendationInner(element);
    }
  }

  private void composeImmunizationRecommendationInner(ImmunizationRecommendation element) throws Exception {
      composeDomainResourceElements(element);
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

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImmunizationRecommendationImmunizationRecommendationRecommendationComponentInner(element);
      close();
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationComponentInner(ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent element) throws Exception {
      composeBackbone(element);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeIntegerCore("doseNumber", element.getDoseNumberElement(), false);
      composeIntegerExtras("doseNumber", element.getDoseNumberElement(), false);
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
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponentInner(element);
      close();
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponentInner(ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeDateTimeCore("value", element.getValueElement(), false);
      composeDateTimeExtras("value", element.getValueElement(), false);
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponentInner(element);
      close();
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponentInner(ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("doseSequence", element.getDoseSequenceElement(), false);
      composeIntegerExtras("doseSequence", element.getDoseSequenceElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeReference("authority", element.getAuthority());
      composeStringCore("series", element.getSeriesElement(), false);
      composeStringExtras("series", element.getSeriesElement(), false);
  }

  private void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeList_Inner(element);
    }
  }

  private void composeList_Inner(List_ element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeReference("source", element.getSource());
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeBooleanCore("ordered", element.getOrderedElement(), false);
      composeBooleanExtras("ordered", element.getOrderedElement(), false);
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new List_.ListModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new List_.ListModeEnumFactory(), false);
      }
      if (element.getEntry().size() > 0) {
        openArray("entry");
        for (List_.ListEntryComponent e : element.getEntry()) 
          composeList_ListEntryComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("emptyReason", element.getEmptyReason());
  }

  private void composeList_ListEntryComponent(String name, List_.ListEntryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeList_ListEntryComponentInner(element);
      close();
    }
  }

  private void composeList_ListEntryComponentInner(List_.ListEntryComponent element) throws Exception {
      composeBackbone(element);
      if (element.getFlag().size() > 0) {
        openArray("flag");
        for (CodeableConcept e : element.getFlag()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeBooleanCore("deleted", element.getDeletedElement(), false);
      composeBooleanExtras("deleted", element.getDeletedElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeReference("item", element.getItem());
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeLocationInner(element);
    }
  }

  private void composeLocationInner(Location element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
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
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Location.LocationStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Location.LocationStatusEnumFactory(), false);
      }
      composeReference("partOf", element.getPartOf());
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new Location.LocationModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new Location.LocationModeEnumFactory(), false);
      }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeLocationLocationPositionComponentInner(element);
      close();
    }
  }

  private void composeLocationLocationPositionComponentInner(Location.LocationPositionComponent element) throws Exception {
      composeBackbone(element);
      composeDecimalCore("longitude", element.getLongitudeElement(), false);
      composeDecimalExtras("longitude", element.getLongitudeElement(), false);
      composeDecimalCore("latitude", element.getLatitudeElement(), false);
      composeDecimalExtras("latitude", element.getLatitudeElement(), false);
      composeDecimalCore("altitude", element.getAltitudeElement(), false);
      composeDecimalExtras("altitude", element.getAltitudeElement(), false);
  }

  private void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMediaInner(element);
    }
  }

  private void composeMediaInner(Media element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Media.MediaTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Media.MediaTypeEnumFactory(), false);
      }
      composeCodeableConcept("subtype", element.getSubtype());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("created", element.getCreatedElement(), false);
      composeDateTimeExtras("created", element.getCreatedElement(), false);
      composeReference("subject", element.getSubject());
      composeReference("operator", element.getOperator());
      composeCodeableConcept("view", element.getView());
      composeStringCore("deviceName", element.getDeviceNameElement(), false);
      composeStringExtras("deviceName", element.getDeviceNameElement(), false);
      composeIntegerCore("height", element.getHeightElement(), false);
      composeIntegerExtras("height", element.getHeightElement(), false);
      composeIntegerCore("width", element.getWidthElement(), false);
      composeIntegerExtras("width", element.getWidthElement(), false);
      composeIntegerCore("frames", element.getFramesElement(), false);
      composeIntegerExtras("frames", element.getFramesElement(), false);
      composeIntegerCore("duration", element.getDurationElement(), false);
      composeIntegerExtras("duration", element.getDurationElement(), false);
      composeAttachment("content", element.getContent());
  }

  private void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMedicationInner(element);
    }
  }

  private void composeMedicationInner(Medication element) throws Exception {
      composeDomainResourceElements(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeCodeableConcept("code", element.getCode());
      composeBooleanCore("isBrand", element.getIsBrandElement(), false);
      composeBooleanExtras("isBrand", element.getIsBrandElement(), false);
      composeReference("manufacturer", element.getManufacturer());
      if (element.getKindElement() != null) {
        composeEnumerationCore("kind", element.getKindElement(), new Medication.MedicationKindEnumFactory(), false);
        composeEnumerationExtras("kind", element.getKindElement(), new Medication.MedicationKindEnumFactory(), false);
      }
      composeMedicationMedicationProductComponent("product", element.getProduct());
      composeMedicationMedicationPackageComponent("package", element.getPackage());
  }

  private void composeMedicationMedicationProductComponent(String name, Medication.MedicationProductComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationMedicationProductComponentInner(element);
      close();
    }
  }

  private void composeMedicationMedicationProductComponentInner(Medication.MedicationProductComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("form", element.getForm());
      if (element.getIngredient().size() > 0) {
        openArray("ingredient");
        for (Medication.MedicationProductIngredientComponent e : element.getIngredient()) 
          composeMedicationMedicationProductIngredientComponent(null, e);
        closeArray();
      };
  }

  private void composeMedicationMedicationProductIngredientComponent(String name, Medication.MedicationProductIngredientComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationMedicationProductIngredientComponentInner(element);
      close();
    }
  }

  private void composeMedicationMedicationProductIngredientComponentInner(Medication.MedicationProductIngredientComponent element) throws Exception {
      composeBackbone(element);
      composeReference("item", element.getItem());
      composeRatio("amount", element.getAmount());
  }

  private void composeMedicationMedicationPackageComponent(String name, Medication.MedicationPackageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationMedicationPackageComponentInner(element);
      close();
    }
  }

  private void composeMedicationMedicationPackageComponentInner(Medication.MedicationPackageComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("container", element.getContainer());
      if (element.getContent().size() > 0) {
        openArray("content");
        for (Medication.MedicationPackageContentComponent e : element.getContent()) 
          composeMedicationMedicationPackageContentComponent(null, e);
        closeArray();
      };
  }

  private void composeMedicationMedicationPackageContentComponent(String name, Medication.MedicationPackageContentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationMedicationPackageContentComponentInner(element);
      close();
    }
  }

  private void composeMedicationMedicationPackageContentComponentInner(Medication.MedicationPackageContentComponent element) throws Exception {
      composeBackbone(element);
      composeReference("item", element.getItem());
      composeQuantity("amount", element.getAmount());
  }

  private void composeMedicationAdministration(String name, MedicationAdministration element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMedicationAdministrationInner(element);
    }
  }

  private void composeMedicationAdministrationInner(MedicationAdministration element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new MedicationAdministration.MedicationAdminStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new MedicationAdministration.MedicationAdminStatusEnumFactory(), false);
      }
      composeReference("patient", element.getPatient());
      composeReference("practitioner", element.getPractitioner());
      composeReference("encounter", element.getEncounter());
      composeReference("prescription", element.getPrescription());
      composeBooleanCore("wasNotGiven", element.getWasNotGivenElement(), false);
      composeBooleanExtras("wasNotGiven", element.getWasNotGivenElement(), false);
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

  private void composeMedicationAdministrationMedicationAdministrationDosageComponent(String name, MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationAdministrationMedicationAdministrationDosageComponentInner(element);
      close();
    }
  }

  private void composeMedicationAdministrationMedicationAdministrationDosageComponentInner(MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
      composeBackbone(element);
      composeType("timing", element.getTiming());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
  }

  private void composeMedicationDispense(String name, MedicationDispense element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMedicationDispenseInner(element);
    }
  }

  private void composeMedicationDispenseInner(MedicationDispense element) throws Exception {
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
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

  private void composeMedicationDispenseMedicationDispenseDispenseComponent(String name, MedicationDispense.MedicationDispenseDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationDispenseMedicationDispenseDispenseComponentInner(element);
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseComponentInner(MedicationDispense.MedicationDispenseDispenseComponent element) throws Exception {
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory(), false);
      }
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeReference("medication", element.getMedication());
      composeDateTimeCore("whenPrepared", element.getWhenPreparedElement(), false);
      composeDateTimeExtras("whenPrepared", element.getWhenPreparedElement(), false);
      composeDateTimeCore("whenHandedOver", element.getWhenHandedOverElement(), false);
      composeDateTimeExtras("whenHandedOver", element.getWhenHandedOverElement(), false);
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
  }

  private void composeMedicationDispenseMedicationDispenseDispenseDosageComponent(String name, MedicationDispense.MedicationDispenseDispenseDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationDispenseMedicationDispenseDispenseDosageComponentInner(element);
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseDosageComponentInner(MedicationDispense.MedicationDispenseDispenseDosageComponent element) throws Exception {
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
  }

  private void composeMedicationDispenseMedicationDispenseSubstitutionComponent(String name, MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationDispenseMedicationDispenseSubstitutionComponentInner(element);
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseSubstitutionComponentInner(MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
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
  }

  private void composeMedicationPrescription(String name, MedicationPrescription element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMedicationPrescriptionInner(element);
    }
  }

  private void composeMedicationPrescriptionInner(MedicationPrescription element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("dateWritten", element.getDateWrittenElement(), false);
      composeDateTimeExtras("dateWritten", element.getDateWrittenElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory(), false);
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

  private void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(String name, MedicationPrescription.MedicationPrescriptionDosageInstructionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponentInner(element);
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponentInner(MedicationPrescription.MedicationPrescriptionDosageInstructionComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("scheduled", element.getScheduled());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDispenseComponent(String name, MedicationPrescription.MedicationPrescriptionDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationPrescriptionMedicationPrescriptionDispenseComponentInner(element);
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDispenseComponentInner(MedicationPrescription.MedicationPrescriptionDispenseComponent element) throws Exception {
      composeBackbone(element);
      composeReference("medication", element.getMedication());
      composePeriod("validityPeriod", element.getValidityPeriod());
      composeIntegerCore("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedElement(), false);
      composeIntegerExtras("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedElement(), false);
      composeQuantity("quantity", element.getQuantity());
      composeDuration("expectedSupplyDuration", element.getExpectedSupplyDuration());
  }

  private void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(String name, MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponentInner(element);
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponentInner(MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
  }

  private void composeMedicationStatement(String name, MedicationStatement element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMedicationStatementInner(element);
    }
  }

  private void composeMedicationStatementInner(MedicationStatement element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("patient", element.getPatient());
      composeBooleanCore("wasNotGiven", element.getWasNotGivenElement(), false);
      composeBooleanExtras("wasNotGiven", element.getWasNotGivenElement(), false);
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

  private void composeMedicationStatementMedicationStatementDosageComponent(String name, MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMedicationStatementMedicationStatementDosageComponentInner(element);
      close();
    }
  }

  private void composeMedicationStatementMedicationStatementDosageComponentInner(MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
      composeBackbone(element);
      composeTiming("schedule", element.getSchedule());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
  }

  private void composeMessageHeader(String name, MessageHeader element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeMessageHeaderInner(element);
    }
  }

  private void composeMessageHeaderInner(MessageHeader element) throws Exception {
      composeDomainResourceElements(element);
      composeIdCore("identifier", element.getIdentifierElement(), false);
      composeIdExtras("identifier", element.getIdentifierElement(), false);
      composeInstantCore("timestamp", element.getTimestampElement(), false);
      composeInstantExtras("timestamp", element.getTimestampElement(), false);
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

  private void composeMessageHeaderMessageHeaderResponseComponent(String name, MessageHeader.MessageHeaderResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMessageHeaderMessageHeaderResponseComponentInner(element);
      close();
    }
  }

  private void composeMessageHeaderMessageHeaderResponseComponentInner(MessageHeader.MessageHeaderResponseComponent element) throws Exception {
      composeBackbone(element);
      composeIdCore("identifier", element.getIdentifierElement(), false);
      composeIdExtras("identifier", element.getIdentifierElement(), false);
      if (element.getCodeElement() != null) {
        composeEnumerationCore("code", element.getCodeElement(), new MessageHeader.ResponseCodeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeElement(), new MessageHeader.ResponseCodeEnumFactory(), false);
      }
      composeReference("details", element.getDetails());
  }

  private void composeMessageHeaderMessageSourceComponent(String name, MessageHeader.MessageSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMessageHeaderMessageSourceComponentInner(element);
      close();
    }
  }

  private void composeMessageHeaderMessageSourceComponentInner(MessageHeader.MessageSourceComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("software", element.getSoftwareElement(), false);
      composeStringExtras("software", element.getSoftwareElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeContactPoint("contact", element.getContact());
      composeUriCore("endpoint", element.getEndpointElement(), false);
      composeUriExtras("endpoint", element.getEndpointElement(), false);
  }

  private void composeMessageHeaderMessageDestinationComponent(String name, MessageHeader.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeMessageHeaderMessageDestinationComponentInner(element);
      close();
    }
  }

  private void composeMessageHeaderMessageDestinationComponentInner(MessageHeader.MessageDestinationComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeReference("target", element.getTarget());
      composeUriCore("endpoint", element.getEndpointElement(), false);
      composeUriExtras("endpoint", element.getEndpointElement(), false);
  }

  private void composeNamingSystem(String name, NamingSystem element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeNamingSystemInner(element);
    }
  }

  private void composeNamingSystemInner(NamingSystem element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new NamingSystem.NamingsystemTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new NamingSystem.NamingsystemTypeEnumFactory(), false);
      }
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new NamingSystem.NamingsystemStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new NamingSystem.NamingsystemStatusEnumFactory(), false);
      }
      composeCodeCore("country", element.getCountryElement(), false);
      composeCodeExtras("country", element.getCountryElement(), false);
      composeCodeableConcept("category", element.getCategory());
      composeStringCore("responsible", element.getResponsibleElement(), false);
      composeStringExtras("responsible", element.getResponsibleElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeStringCore("usage", element.getUsageElement(), false);
      composeStringExtras("usage", element.getUsageElement(), false);
      if (element.getUniqueId().size() > 0) {
        openArray("uniqueId");
        for (NamingSystem.NamingSystemUniqueIdComponent e : element.getUniqueId()) 
          composeNamingSystemNamingSystemUniqueIdComponent(null, e);
        closeArray();
      };
      composeNamingSystemNamingSystemContactComponent("contact", element.getContact());
      composeReference("replacedBy", element.getReplacedBy());
  }

  private void composeNamingSystemNamingSystemUniqueIdComponent(String name, NamingSystem.NamingSystemUniqueIdComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNamingSystemNamingSystemUniqueIdComponentInner(element);
      close();
    }
  }

  private void composeNamingSystemNamingSystemUniqueIdComponentInner(NamingSystem.NamingSystemUniqueIdComponent element) throws Exception {
      composeBackbone(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new NamingSystem.NamingsystemIdentifierTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new NamingSystem.NamingsystemIdentifierTypeEnumFactory(), false);
      }
      composeStringCore("value", element.getValueElement(), false);
      composeStringExtras("value", element.getValueElement(), false);
      composeBooleanCore("preferred", element.getPreferredElement(), false);
      composeBooleanExtras("preferred", element.getPreferredElement(), false);
      composePeriod("period", element.getPeriod());
  }

  private void composeNamingSystemNamingSystemContactComponent(String name, NamingSystem.NamingSystemContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNamingSystemNamingSystemContactComponentInner(element);
      close();
    }
  }

  private void composeNamingSystemNamingSystemContactComponentInner(NamingSystem.NamingSystemContactComponent element) throws Exception {
      composeBackbone(element);
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
  }

  private void composeNutritionOrder(String name, NutritionOrder element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeNutritionOrderInner(element);
    }
  }

  private void composeNutritionOrderInner(NutritionOrder element) throws Exception {
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeReference("orderer", element.getOrderer());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
      composeDateTimeCore("dateTime", element.getDateTimeElement(), false);
      composeDateTimeExtras("dateTime", element.getDateTimeElement(), false);
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
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new NutritionOrder.NutritionOrderStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new NutritionOrder.NutritionOrderStatusEnumFactory(), false);
      }
  }

  private void composeNutritionOrderNutritionOrderItemComponent(String name, NutritionOrder.NutritionOrderItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNutritionOrderNutritionOrderItemComponentInner(element);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemComponentInner(NutritionOrder.NutritionOrderItemComponent element) throws Exception {
      composeBackbone(element);
      composeType("scheduled", element.getScheduled());
      composeBooleanCore("isInEffect", element.getIsInEffectElement(), false);
      composeBooleanExtras("isInEffect", element.getIsInEffectElement(), false);
      composeNutritionOrderNutritionOrderItemOralDietComponent("oralDiet", element.getOralDiet());
      composeNutritionOrderNutritionOrderItemSupplementComponent("supplement", element.getSupplement());
      composeNutritionOrderNutritionOrderItemEnteralFormulaComponent("enteralFormula", element.getEnteralFormula());
  }

  private void composeNutritionOrderNutritionOrderItemOralDietComponent(String name, NutritionOrder.NutritionOrderItemOralDietComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNutritionOrderNutritionOrderItemOralDietComponentInner(element);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietComponentInner(NutritionOrder.NutritionOrderItemOralDietComponent element) throws Exception {
      composeBackbone(element);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (CodeableConcept e : element.getCode()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getNutrients().size() > 0) {
        openArray("nutrients");
        for (NutritionOrder.NutritionOrderItemOralDietNutrientsComponent e : element.getNutrients()) 
          composeNutritionOrderNutritionOrderItemOralDietNutrientsComponent(null, e);
        closeArray();
      };
      if (element.getTexture().size() > 0) {
        openArray("texture");
        for (NutritionOrder.NutritionOrderItemOralDietTextureComponent e : element.getTexture()) 
          composeNutritionOrderNutritionOrderItemOralDietTextureComponent(null, e);
        closeArray();
      };
      if (element.getFluidConsistencyType().size() > 0) {
        openArray("fluidConsistencyType");
        for (CodeableConcept e : element.getFluidConsistencyType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
  }

  private void composeNutritionOrderNutritionOrderItemOralDietNutrientsComponent(String name, NutritionOrder.NutritionOrderItemOralDietNutrientsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNutritionOrderNutritionOrderItemOralDietNutrientsComponentInner(element);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietNutrientsComponentInner(NutritionOrder.NutritionOrderItemOralDietNutrientsComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("modifier", element.getModifier());
      composeType("amount", element.getAmount());
  }

  private void composeNutritionOrderNutritionOrderItemOralDietTextureComponent(String name, NutritionOrder.NutritionOrderItemOralDietTextureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNutritionOrderNutritionOrderItemOralDietTextureComponentInner(element);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietTextureComponentInner(NutritionOrder.NutritionOrderItemOralDietTextureComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("modifier", element.getModifier());
      composeCodeableConcept("foodType", element.getFoodType());
  }

  private void composeNutritionOrderNutritionOrderItemSupplementComponent(String name, NutritionOrder.NutritionOrderItemSupplementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNutritionOrderNutritionOrderItemSupplementComponentInner(element);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemSupplementComponentInner(NutritionOrder.NutritionOrderItemSupplementComponent element) throws Exception {
      composeBackbone(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeQuantity("quantity", element.getQuantity());
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
  }

  private void composeNutritionOrderNutritionOrderItemEnteralFormulaComponent(String name, NutritionOrder.NutritionOrderItemEnteralFormulaComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeNutritionOrderNutritionOrderItemEnteralFormulaComponentInner(element);
      close();
    }
  }

  private void composeNutritionOrderNutritionOrderItemEnteralFormulaComponentInner(NutritionOrder.NutritionOrderItemEnteralFormulaComponent element) throws Exception {
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
      composeStringCore("baseFormulaName", element.getBaseFormulaNameElement(), false);
      composeStringExtras("baseFormulaName", element.getBaseFormulaNameElement(), false);
  }

  private void composeObservation(String name, Observation element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeObservationInner(element);
    }
  }

  private void composeObservationInner(Observation element) throws Exception {
      composeDomainResourceElements(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      if (element.getDataAbsentReasonElement() != null) {
        composeEnumerationCore("dataAbsentReason", element.getDataAbsentReasonElement(), new Observation.DataAbsentReasonEnumFactory(), false);
        composeEnumerationExtras("dataAbsentReason", element.getDataAbsentReasonElement(), new Observation.DataAbsentReasonEnumFactory(), false);
      }
      composeCodeableConcept("interpretation", element.getInterpretation());
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
      composeType("applies", element.getApplies());
      composeInstantCore("issued", element.getIssuedElement(), false);
      composeInstantExtras("issued", element.getIssuedElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Observation.ObservationStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Observation.ObservationStatusEnumFactory(), false);
      }
      if (element.getReliabilityElement() != null) {
        composeEnumerationCore("reliability", element.getReliabilityElement(), new Observation.ObservationReliabilityEnumFactory(), false);
        composeEnumerationExtras("reliability", element.getReliabilityElement(), new Observation.ObservationReliabilityEnumFactory(), false);
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

  private void composeObservationObservationReferenceRangeComponent(String name, Observation.ObservationReferenceRangeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeObservationObservationReferenceRangeComponentInner(element);
      close();
    }
  }

  private void composeObservationObservationReferenceRangeComponentInner(Observation.ObservationReferenceRangeComponent element) throws Exception {
      composeBackbone(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
      composeCodeableConcept("meaning", element.getMeaning());
      composeRange("age", element.getAge());
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
  }

  private void composeObservationObservationRelatedComponent(String name, Observation.ObservationRelatedComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeObservationObservationRelatedComponentInner(element);
      close();
    }
  }

  private void composeObservationObservationRelatedComponentInner(Observation.ObservationRelatedComponent element) throws Exception {
      composeBackbone(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Observation.ObservationRelationshiptypesEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Observation.ObservationRelationshiptypesEnumFactory(), false);
      }
      composeReference("target", element.getTarget());
  }

  private void composeOperationDefinition(String name, OperationDefinition element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOperationDefinitionInner(element);
    }
  }

  private void composeOperationDefinitionInner(OperationDefinition element) throws Exception {
      composeDomainResourceElements(element);
      composeUriCore("identifier", element.getIdentifierElement(), false);
      composeUriExtras("identifier", element.getIdentifierElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new OperationDefinition.ResourceProfileStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new OperationDefinition.ResourceProfileStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalElement(), false);
      composeBooleanExtras("experimental", element.getExperimentalElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      if (element.getKindElement() != null) {
        composeEnumerationCore("kind", element.getKindElement(), new OperationDefinition.OperationKindEnumFactory(), false);
        composeEnumerationExtras("kind", element.getKindElement(), new OperationDefinition.OperationKindEnumFactory(), false);
      }
      composeCodeCore("name", element.getNameElement(), false);
      composeCodeExtras("name", element.getNameElement(), false);
      composeStringCore("notes", element.getNotesElement(), false);
      composeStringExtras("notes", element.getNotesElement(), false);
      composeReference("base", element.getBase());
      composeBooleanCore("system", element.getSystemElement(), false);
      composeBooleanExtras("system", element.getSystemElement(), false);
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
      composeBooleanCore("instance", element.getInstanceElement(), false);
      composeBooleanExtras("instance", element.getInstanceElement(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (OperationDefinition.OperationDefinitionParameterComponent e : element.getParameter()) 
          composeOperationDefinitionOperationDefinitionParameterComponent(null, e);
        closeArray();
      };
  }

  private void composeOperationDefinitionOperationDefinitionParameterComponent(String name, OperationDefinition.OperationDefinitionParameterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOperationDefinitionOperationDefinitionParameterComponentInner(element);
      close();
    }
  }

  private void composeOperationDefinitionOperationDefinitionParameterComponentInner(OperationDefinition.OperationDefinitionParameterComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("name", element.getNameElement(), false);
      composeCodeExtras("name", element.getNameElement(), false);
      if (element.getUseElement() != null) {
        composeEnumerationCore("use", element.getUseElement(), new OperationDefinition.OperationParameterUseEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseElement(), new OperationDefinition.OperationParameterUseEnumFactory(), false);
      }
      composeIntegerCore("min", element.getMinElement(), false);
      composeIntegerExtras("min", element.getMinElement(), false);
      composeStringCore("max", element.getMaxElement(), false);
      composeStringExtras("max", element.getMaxElement(), false);
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
      composeCoding("type", element.getType());
      composeReference("profile", element.getProfile());
  }

  private void composeOperationOutcome(String name, OperationOutcome element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOperationOutcomeInner(element);
    }
  }

  private void composeOperationOutcomeInner(OperationOutcome element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIssue().size() > 0) {
        openArray("issue");
        for (OperationOutcome.OperationOutcomeIssueComponent e : element.getIssue()) 
          composeOperationOutcomeOperationOutcomeIssueComponent(null, e);
        closeArray();
      };
  }

  private void composeOperationOutcomeOperationOutcomeIssueComponent(String name, OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOperationOutcomeOperationOutcomeIssueComponentInner(element);
      close();
    }
  }

  private void composeOperationOutcomeOperationOutcomeIssueComponentInner(OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
      composeBackbone(element);
      if (element.getSeverityElement() != null) {
        composeEnumerationCore("severity", element.getSeverityElement(), new OperationOutcome.IssueSeverityEnumFactory(), false);
        composeEnumerationExtras("severity", element.getSeverityElement(), new OperationOutcome.IssueSeverityEnumFactory(), false);
      }
      composeCoding("type", element.getType());
      composeStringCore("details", element.getDetailsElement(), false);
      composeStringExtras("details", element.getDetailsElement(), false);
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
  }

  private void composeOralHealthClaim(String name, OralHealthClaim element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOralHealthClaimInner(element);
    }
  }

  private void composeOralHealthClaimInner(OralHealthClaim element) throws Exception {
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDateCore("date", element.getDateElement(), false);
      composeDateExtras("date", element.getDateElement(), false);
      composeReference("target", element.getTarget());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      if (element.getUseElement() != null) {
        composeEnumerationCore("use", element.getUseElement(), new OralHealthClaim.UseLinkEnumFactory(), false);
        composeEnumerationExtras("use", element.getUseElement(), new OralHealthClaim.UseLinkEnumFactory(), false);
      }
      composeCoding("priority", element.getPriority());
      composeCoding("fundsReserve", element.getFundsReserve());
      composeReference("enterer", element.getEnterer());
      composeReference("facility", element.getFacility());
      composeOralHealthClaimPayeeComponent("payee", element.getPayee());
      composeReference("referral", element.getReferral());
      if (element.getDiagnosis().size() > 0) {
        openArray("diagnosis");
        for (OralHealthClaim.DiagnosisComponent e : element.getDiagnosis()) 
          composeOralHealthClaimDiagnosisComponent(null, e);
        closeArray();
      };
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (Coding e : element.getCondition()) 
          composeCoding(null, e);
        closeArray();
      };
      composeReference("patient", element.getPatient());
      if (element.getCoverage().size() > 0) {
        openArray("coverage");
        for (OralHealthClaim.CoverageComponent e : element.getCoverage()) 
          composeOralHealthClaimCoverageComponent(null, e);
        closeArray();
      };
      if (element.getException().size() > 0) {
        openArray("exception");
        for (Coding e : element.getException()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("school", element.getSchoolElement(), false);
      composeStringExtras("school", element.getSchoolElement(), false);
      composeDateCore("accident", element.getAccidentElement(), false);
      composeDateExtras("accident", element.getAccidentElement(), false);
      composeCoding("accidentType", element.getAccidentType());
      if (element.getInterventionException().size() > 0) {
        openArray("interventionException");
        for (Coding e : element.getInterventionException()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getMissingteeth().size() > 0) {
        openArray("missingteeth");
        for (OralHealthClaim.MissingTeethComponent e : element.getMissingteeth()) 
          composeOralHealthClaimMissingTeethComponent(null, e);
        closeArray();
      };
      composeOralHealthClaimOrthodonticPlanComponent("orthoPlan", element.getOrthoPlan());
      if (element.getItem().size() > 0) {
        openArray("item");
        for (OralHealthClaim.ItemsComponent e : element.getItem()) 
          composeOralHealthClaimItemsComponent(null, e);
        closeArray();
      };
      if (element.getAdditionalMaterials().size() > 0) {
        openArray("additionalMaterials");
        for (Coding e : element.getAdditionalMaterials()) 
          composeCoding(null, e);
        closeArray();
      };
  }

  private void composeOralHealthClaimPayeeComponent(String name, OralHealthClaim.PayeeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimPayeeComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimPayeeComponentInner(OralHealthClaim.PayeeComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("type", element.getType());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      composeReference("person", element.getPerson());
  }

  private void composeOralHealthClaimDiagnosisComponent(String name, OralHealthClaim.DiagnosisComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimDiagnosisComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimDiagnosisComponentInner(OralHealthClaim.DiagnosisComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequence", element.getSequenceElement(), false);
      composeIntegerExtras("sequence", element.getSequenceElement(), false);
      composeCoding("diagnosis", element.getDiagnosis());
  }

  private void composeOralHealthClaimCoverageComponent(String name, OralHealthClaim.CoverageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimCoverageComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimCoverageComponentInner(OralHealthClaim.CoverageComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequence", element.getSequenceElement(), false);
      composeIntegerExtras("sequence", element.getSequenceElement(), false);
      composeBooleanCore("focal", element.getFocalElement(), false);
      composeBooleanExtras("focal", element.getFocalElement(), false);
      composeReference("coverage", element.getCoverage());
      composeStringCore("businessArrangement", element.getBusinessArrangementElement(), false);
      composeStringExtras("businessArrangement", element.getBusinessArrangementElement(), false);
      composeCoding("relationship", element.getRelationship());
      if (element.getPreauthref().size() > 0) {
        openArray("preauthref");
        for (StringType e : element.getPreauthref()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getPreauthref())) {
          openArray("_preauthref");
          for (StringType e : element.getPreauthref()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeReference("claimResponse", element.getClaimResponse());
      composeCoding("originalRuleset", element.getOriginalRuleset());
  }

  private void composeOralHealthClaimMissingTeethComponent(String name, OralHealthClaim.MissingTeethComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimMissingTeethComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimMissingTeethComponentInner(OralHealthClaim.MissingTeethComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("tooth", element.getTooth());
      composeCoding("reason", element.getReason());
      composeDateCore("extractiondate", element.getExtractiondateElement(), false);
      composeDateExtras("extractiondate", element.getExtractiondateElement(), false);
  }

  private void composeOralHealthClaimOrthodonticPlanComponent(String name, OralHealthClaim.OrthodonticPlanComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimOrthodonticPlanComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimOrthodonticPlanComponentInner(OralHealthClaim.OrthodonticPlanComponent element) throws Exception {
      composeBackbone(element);
      composeDateCore("start", element.getStartElement(), false);
      composeDateExtras("start", element.getStartElement(), false);
      composeMoney("examFee", element.getExamFee());
      composeMoney("diagnosticFee", element.getDiagnosticFee());
      composeMoney("initialPayment", element.getInitialPayment());
      composeIntegerCore("durationMonths", element.getDurationMonthsElement(), false);
      composeIntegerExtras("durationMonths", element.getDurationMonthsElement(), false);
      composeIntegerCore("paymentCount", element.getPaymentCountElement(), false);
      composeIntegerExtras("paymentCount", element.getPaymentCountElement(), false);
      composeMoney("periodicPayment", element.getPeriodicPayment());
  }

  private void composeOralHealthClaimItemsComponent(String name, OralHealthClaim.ItemsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimItemsComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimItemsComponentInner(OralHealthClaim.ItemsComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequence", element.getSequenceElement(), false);
      composeIntegerExtras("sequence", element.getSequenceElement(), false);
      composeCoding("type", element.getType());
      composeReference("provider", element.getProvider());
      composeCoding("service", element.getService());
      composeDateCore("serviceDate", element.getServiceDateElement(), false);
      composeDateExtras("serviceDate", element.getServiceDateElement(), false);
      composeQuantity("quantity", element.getQuantity());
      composeMoney("unitPrice", element.getUnitPrice());
      composeDecimalCore("factor", element.getFactorElement(), false);
      composeDecimalExtras("factor", element.getFactorElement(), false);
      composeDecimalCore("points", element.getPointsElement(), false);
      composeDecimalExtras("points", element.getPointsElement(), false);
      composeMoney("net", element.getNet());
      composeCoding("udi", element.getUdi());
      composeCoding("bodySite", element.getBodySite());
      if (element.getSubsite().size() > 0) {
        openArray("subsite");
        for (Coding e : element.getSubsite()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getModifier().size() > 0) {
        openArray("modifier");
        for (Coding e : element.getModifier()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (OralHealthClaim.DetailComponent e : element.getDetail()) 
          composeOralHealthClaimDetailComponent(null, e);
        closeArray();
      };
      composeOralHealthClaimProsthesisComponent("prosthesis", element.getProsthesis());
  }

  private void composeOralHealthClaimDetailComponent(String name, OralHealthClaim.DetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimDetailComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimDetailComponentInner(OralHealthClaim.DetailComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequence", element.getSequenceElement(), false);
      composeIntegerExtras("sequence", element.getSequenceElement(), false);
      composeCoding("type", element.getType());
      composeCoding("service", element.getService());
      composeQuantity("quantity", element.getQuantity());
      composeMoney("unitPrice", element.getUnitPrice());
      composeDecimalCore("factor", element.getFactorElement(), false);
      composeDecimalExtras("factor", element.getFactorElement(), false);
      composeDecimalCore("points", element.getPointsElement(), false);
      composeDecimalExtras("points", element.getPointsElement(), false);
      composeMoney("net", element.getNet());
      composeCoding("udi", element.getUdi());
      if (element.getSubDetail().size() > 0) {
        openArray("subDetail");
        for (OralHealthClaim.SubDetailComponent e : element.getSubDetail()) 
          composeOralHealthClaimSubDetailComponent(null, e);
        closeArray();
      };
  }

  private void composeOralHealthClaimSubDetailComponent(String name, OralHealthClaim.SubDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimSubDetailComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimSubDetailComponentInner(OralHealthClaim.SubDetailComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("sequence", element.getSequenceElement(), false);
      composeIntegerExtras("sequence", element.getSequenceElement(), false);
      composeCoding("type", element.getType());
      composeCoding("service", element.getService());
      composeQuantity("quantity", element.getQuantity());
      composeMoney("unitPrice", element.getUnitPrice());
      composeDecimalCore("factor", element.getFactorElement(), false);
      composeDecimalExtras("factor", element.getFactorElement(), false);
      composeDecimalCore("points", element.getPointsElement(), false);
      composeDecimalExtras("points", element.getPointsElement(), false);
      composeMoney("net", element.getNet());
      composeCoding("udi", element.getUdi());
  }

  private void composeOralHealthClaimProsthesisComponent(String name, OralHealthClaim.ProsthesisComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOralHealthClaimProsthesisComponentInner(element);
      close();
    }
  }

  private void composeOralHealthClaimProsthesisComponentInner(OralHealthClaim.ProsthesisComponent element) throws Exception {
      composeBackbone(element);
      composeBooleanCore("initial", element.getInitialElement(), false);
      composeBooleanExtras("initial", element.getInitialElement(), false);
      composeDateCore("priorDate", element.getPriorDateElement(), false);
      composeDateExtras("priorDate", element.getPriorDateElement(), false);
      composeCoding("priorMaterial", element.getPriorMaterial());
  }

  private void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOrderInner(element);
    }
  }

  private void composeOrderInner(Order element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
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

  private void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOrderOrderWhenComponentInner(element);
      close();
    }
  }

  private void composeOrderOrderWhenComponentInner(Order.OrderWhenComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("code", element.getCode());
      composeTiming("schedule", element.getSchedule());
  }

  private void composeOrderResponse(String name, OrderResponse element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOrderResponseInner(element);
    }
  }

  private void composeOrderResponseInner(OrderResponse element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("request", element.getRequest());
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeReference("who", element.getWho());
      composeType("authority", element.getAuthority());
      if (element.getCodeElement() != null) {
        composeEnumerationCore("code", element.getCodeElement(), new OrderResponse.OrderOutcomeCodeEnumFactory(), false);
        composeEnumerationExtras("code", element.getCodeElement(), new OrderResponse.OrderOutcomeCodeEnumFactory(), false);
      }
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getFulfillment().size() > 0) {
        openArray("fulfillment");
        for (Reference e : element.getFulfillment()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeOrganization(String name, Organization element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOrganizationInner(element);
    }
  }

  private void composeOrganizationInner(Organization element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
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
      composeBooleanCore("active", element.getActiveElement(), false);
      composeBooleanExtras("active", element.getActiveElement(), false);
  }

  private void composeOrganizationOrganizationContactComponent(String name, Organization.OrganizationContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeOrganizationOrganizationContactComponentInner(element);
      close();
    }
  }

  private void composeOrganizationOrganizationContactComponentInner(Organization.OrganizationContactComponent element) throws Exception {
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
      if (element.getGenderElement() != null) {
        composeEnumerationCore("gender", element.getGenderElement(), new Organization.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderElement(), new Organization.AdministrativeGenderEnumFactory(), false);
      }
  }

  private void composeOther(String name, Other element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeOtherInner(element);
    }
  }

  private void composeOtherInner(Other element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDateCore("created", element.getCreatedElement(), false);
      composeDateExtras("created", element.getCreatedElement(), false);
  }

  private void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composePatientInner(element);
    }
  }

  private void composePatientInner(Patient element) throws Exception {
      composeDomainResourceElements(element);
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
      if (element.getGenderElement() != null) {
        composeEnumerationCore("gender", element.getGenderElement(), new Patient.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderElement(), new Patient.AdministrativeGenderEnumFactory(), false);
      }
      composeDateTimeCore("birthDate", element.getBirthDateElement(), false);
      composeDateTimeExtras("birthDate", element.getBirthDateElement(), false);
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
      composeBooleanCore("active", element.getActiveElement(), false);
      composeBooleanExtras("active", element.getActiveElement(), false);
  }

  private void composePatientContactComponent(String name, Patient.ContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composePatientContactComponentInner(element);
      close();
    }
  }

  private void composePatientContactComponentInner(Patient.ContactComponent element) throws Exception {
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
      if (element.getGenderElement() != null) {
        composeEnumerationCore("gender", element.getGenderElement(), new Patient.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderElement(), new Patient.AdministrativeGenderEnumFactory(), false);
      }
      composeReference("organization", element.getOrganization());
      composePeriod("period", element.getPeriod());
  }

  private void composePatientAnimalComponent(String name, Patient.AnimalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composePatientAnimalComponentInner(element);
      close();
    }
  }

  private void composePatientAnimalComponentInner(Patient.AnimalComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("species", element.getSpecies());
      composeCodeableConcept("breed", element.getBreed());
      composeCodeableConcept("genderStatus", element.getGenderStatus());
  }

  private void composePatientPatientLinkComponent(String name, Patient.PatientLinkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composePatientPatientLinkComponentInner(element);
      close();
    }
  }

  private void composePatientPatientLinkComponentInner(Patient.PatientLinkComponent element) throws Exception {
      composeBackbone(element);
      composeReference("other", element.getOther());
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Patient.LinkTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Patient.LinkTypeEnumFactory(), false);
      }
  }

  private void composePractitioner(String name, Practitioner element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composePractitionerInner(element);
    }
  }

  private void composePractitionerInner(Practitioner element) throws Exception {
      composeDomainResourceElements(element);
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
      if (element.getGenderElement() != null) {
        composeEnumerationCore("gender", element.getGenderElement(), new Practitioner.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderElement(), new Practitioner.AdministrativeGenderEnumFactory(), false);
      }
      composeDateTimeCore("birthDate", element.getBirthDateElement(), false);
      composeDateTimeExtras("birthDate", element.getBirthDateElement(), false);
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

  private void composePractitionerPractitionerQualificationComponent(String name, Practitioner.PractitionerQualificationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composePractitionerPractitionerQualificationComponentInner(element);
      close();
    }
  }

  private void composePractitionerPractitionerQualificationComponentInner(Practitioner.PractitionerQualificationComponent element) throws Exception {
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
  }

  private void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeProcedureInner(element);
    }
  }

  private void composeProcedureInner(Procedure element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeReference("patient", element.getPatient());
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
      composeStringCore("outcome", element.getOutcomeElement(), false);
      composeStringExtras("outcome", element.getOutcomeElement(), false);
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
      composeStringCore("followUp", element.getFollowUpElement(), false);
      composeStringExtras("followUp", element.getFollowUpElement(), false);
      if (element.getRelatedItem().size() > 0) {
        openArray("relatedItem");
        for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
          composeProcedureProcedureRelatedItemComponent(null, e);
        closeArray();
      };
      composeStringCore("notes", element.getNotesElement(), false);
      composeStringExtras("notes", element.getNotesElement(), false);
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProcedureProcedurePerformerComponentInner(element);
      close();
    }
  }

  private void composeProcedureProcedurePerformerComponentInner(Procedure.ProcedurePerformerComponent element) throws Exception {
      composeBackbone(element);
      composeReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProcedureProcedureRelatedItemComponentInner(element);
      close();
    }
  }

  private void composeProcedureProcedureRelatedItemComponentInner(Procedure.ProcedureRelatedItemComponent element) throws Exception {
      composeBackbone(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Procedure.ProcedureRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Procedure.ProcedureRelationshipTypeEnumFactory(), false);
      }
      composeReference("target", element.getTarget());
  }

  private void composeProcedureRequest(String name, ProcedureRequest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeProcedureRequestInner(element);
    }
  }

  private void composeProcedureRequestInner(ProcedureRequest element) throws Exception {
      composeDomainResourceElements(element);
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
      composeType("timing", element.getTiming());
      composeReference("encounter", element.getEncounter());
      composeReference("performer", element.getPerformer());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new ProcedureRequest.ProcedureRequestStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new ProcedureRequest.ProcedureRequestStatusEnumFactory(), false);
      }
      if (element.getModeElement() != null) {
        composeEnumerationCore("mode", element.getModeElement(), new ProcedureRequest.ProcedureRequestModeEnumFactory(), false);
        composeEnumerationExtras("mode", element.getModeElement(), new ProcedureRequest.ProcedureRequestModeEnumFactory(), false);
      }
      if (element.getNotes().size() > 0) {
        openArray("notes");
        for (StringType e : element.getNotes()) 
          composeStringCore(null, e, true);
        closeArray();
        if (anyHasExtras(element.getNotes())) {
          openArray("_notes");
          for (StringType e : element.getNotes()) 
            composeStringExtras(null, e, true);
          closeArray();
        }
      };
      composeType("asNeeded", element.getAsNeeded());
      composeDateTimeCore("orderedOn", element.getOrderedOnElement(), false);
      composeDateTimeExtras("orderedOn", element.getOrderedOnElement(), false);
      composeReference("orderer", element.getOrderer());
      if (element.getPriorityElement() != null) {
        composeEnumerationCore("priority", element.getPriorityElement(), new ProcedureRequest.ProcedureRequestPriorityEnumFactory(), false);
        composeEnumerationExtras("priority", element.getPriorityElement(), new ProcedureRequest.ProcedureRequestPriorityEnumFactory(), false);
      }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeProfileInner(element);
    }
  }

  private void composeProfileInner(Profile element) throws Exception {
      composeDomainResourceElements(element);
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Profile.ResourceProfileStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Profile.ResourceProfileStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalElement(), false);
      composeBooleanExtras("experimental", element.getExperimentalElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeStringCore("requirements", element.getRequirementsElement(), false);
      composeStringExtras("requirements", element.getRequirementsElement(), false);
      composeIdCore("fhirVersion", element.getFhirVersionElement(), false);
      composeIdExtras("fhirVersion", element.getFhirVersionElement(), false);
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (Profile.ProfileMappingComponent e : element.getMapping()) 
          composeProfileProfileMappingComponent(null, e);
        closeArray();
      };
      composeCodeCore("type", element.getTypeElement(), false);
      composeCodeExtras("type", element.getTypeElement(), false);
      composeUriCore("base", element.getBaseElement(), false);
      composeUriExtras("base", element.getBaseElement(), false);
      composeProfileConstraintComponent("snapshot", element.getSnapshot());
      composeProfileConstraintComponent("differential", element.getDifferential());
      if (element.getSearchParam().size() > 0) {
        openArray("searchParam");
        for (Profile.ProfileSearchParamComponent e : element.getSearchParam()) 
          composeProfileProfileSearchParamComponent(null, e);
        closeArray();
      };
  }

  private void composeProfileProfileMappingComponent(String name, Profile.ProfileMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProfileProfileMappingComponentInner(element);
      close();
    }
  }

  private void composeProfileProfileMappingComponentInner(Profile.ProfileMappingComponent element) throws Exception {
      composeBackbone(element);
      composeIdCore("identity", element.getIdentityElement(), false);
      composeIdExtras("identity", element.getIdentityElement(), false);
      composeUriCore("uri", element.getUriElement(), false);
      composeUriExtras("uri", element.getUriElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("comments", element.getCommentsElement(), false);
      composeStringExtras("comments", element.getCommentsElement(), false);
  }

  private void composeProfileConstraintComponent(String name, Profile.ConstraintComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProfileConstraintComponentInner(element);
      close();
    }
  }

  private void composeProfileConstraintComponentInner(Profile.ConstraintComponent element) throws Exception {
      composeBackbone(element);
      if (element.getElement().size() > 0) {
        openArray("element");
        for (ElementDefinition e : element.getElement()) 
          composeElementDefinition(null, e);
        closeArray();
      };
  }

  private void composeProfileProfileSearchParamComponent(String name, Profile.ProfileSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProfileProfileSearchParamComponentInner(element);
      close();
    }
  }

  private void composeProfileProfileSearchParamComponentInner(Profile.ProfileSearchParamComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Profile.SearchParamTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Profile.SearchParamTypeEnumFactory(), false);
      }
      composeStringCore("documentation", element.getDocumentationElement(), false);
      composeStringExtras("documentation", element.getDocumentationElement(), false);
      composeStringCore("xpath", element.getXpathElement(), false);
      composeStringExtras("xpath", element.getXpathElement(), false);
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
  }

  private void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeProvenanceInner(element);
    }
  }

  private void composeProvenanceInner(Provenance element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Reference e : element.getTarget()) 
          composeReference(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeInstantCore("recorded", element.getRecordedElement(), false);
      composeInstantExtras("recorded", element.getRecordedElement(), false);
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
      composeStringCore("integritySignature", element.getIntegritySignatureElement(), false);
      composeStringExtras("integritySignature", element.getIntegritySignatureElement(), false);
  }

  private void composeProvenanceProvenanceAgentComponent(String name, Provenance.ProvenanceAgentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProvenanceProvenanceAgentComponentInner(element);
      close();
    }
  }

  private void composeProvenanceProvenanceAgentComponentInner(Provenance.ProvenanceAgentComponent element) throws Exception {
      composeBackbone(element);
      composeCoding("role", element.getRole());
      composeCoding("type", element.getType());
      composeUriCore("reference", element.getReferenceElement(), false);
      composeUriExtras("reference", element.getReferenceElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
  }

  private void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeProvenanceProvenanceEntityComponentInner(element);
      close();
    }
  }

  private void composeProvenanceProvenanceEntityComponentInner(Provenance.ProvenanceEntityComponent element) throws Exception {
      composeBackbone(element);
      if (element.getRoleElement() != null) {
        composeEnumerationCore("role", element.getRoleElement(), new Provenance.ProvenanceEntityRoleEnumFactory(), false);
        composeEnumerationExtras("role", element.getRoleElement(), new Provenance.ProvenanceEntityRoleEnumFactory(), false);
      }
      composeCoding("type", element.getType());
      composeUriCore("reference", element.getReferenceElement(), false);
      composeUriExtras("reference", element.getReferenceElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
      composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeQueryInner(element);
    }
  }

  private void composeQueryInner(Query element) throws Exception {
      composeDomainResourceElements(element);
      composeUriCore("identifier", element.getIdentifierElement(), false);
      composeUriExtras("identifier", element.getIdentifierElement(), false);
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Extension e : element.getParameter()) 
          composeExtension(null, e);
        closeArray();
      };
      composeQueryQueryResponseComponent("response", element.getResponse());
  }

  private void composeQueryQueryResponseComponent(String name, Query.QueryResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeQueryQueryResponseComponentInner(element);
      close();
    }
  }

  private void composeQueryQueryResponseComponentInner(Query.QueryResponseComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("identifier", element.getIdentifierElement(), false);
      composeUriExtras("identifier", element.getIdentifierElement(), false);
      if (element.getOutcomeElement() != null) {
        composeEnumerationCore("outcome", element.getOutcomeElement(), new Query.QueryOutcomeEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcomeElement(), new Query.QueryOutcomeEnumFactory(), false);
      }
      composeIntegerCore("total", element.getTotalElement(), false);
      composeIntegerExtras("total", element.getTotalElement(), false);
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
  }

  private void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeQuestionnaireInner(element);
    }
  }

  private void composeQuestionnaireInner(Questionnaire element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Questionnaire.QuestionnaireStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Questionnaire.QuestionnaireStatusEnumFactory(), false);
      }
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      composeQuestionnaireGroupComponent("group", element.getGroup());
  }

  private void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeQuestionnaireGroupComponentInner(element);
      close();
    }
  }

  private void composeQuestionnaireGroupComponentInner(Questionnaire.GroupComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkIdElement(), false);
      composeStringExtras("linkId", element.getLinkIdElement(), false);
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (Coding e : element.getConcept()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
      composeBooleanCore("required", element.getRequiredElement(), false);
      composeBooleanExtras("required", element.getRequiredElement(), false);
      composeBooleanCore("repeats", element.getRepeatsElement(), false);
      composeBooleanExtras("repeats", element.getRepeatsElement(), false);
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
  }

  private void composeQuestionnaireQuestionComponent(String name, Questionnaire.QuestionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeQuestionnaireQuestionComponentInner(element);
      close();
    }
  }

  private void composeQuestionnaireQuestionComponentInner(Questionnaire.QuestionComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkIdElement(), false);
      composeStringExtras("linkId", element.getLinkIdElement(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (Coding e : element.getConcept()) 
          composeCoding(null, e);
        closeArray();
      };
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Questionnaire.AnswerFormatEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Questionnaire.AnswerFormatEnumFactory(), false);
      }
      composeBooleanCore("required", element.getRequiredElement(), false);
      composeBooleanExtras("required", element.getRequiredElement(), false);
      composeBooleanCore("repeats", element.getRepeatsElement(), false);
      composeBooleanExtras("repeats", element.getRepeatsElement(), false);
      composeReference("options", element.getOptions());
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent(null, e);
        closeArray();
      };
  }

  private void composeQuestionnaireAnswers(String name, QuestionnaireAnswers element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeQuestionnaireAnswersInner(element);
    }
  }

  private void composeQuestionnaireAnswersInner(QuestionnaireAnswers element) throws Exception {
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("questionnaire", element.getQuestionnaire());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory(), false);
      }
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDateTimeCore("authored", element.getAuthoredElement(), false);
      composeDateTimeExtras("authored", element.getAuthoredElement(), false);
      composeReference("source", element.getSource());
      composeReference("encounter", element.getEncounter());
      composeQuestionnaireAnswersGroupComponent("group", element.getGroup());
  }

  private void composeQuestionnaireAnswersGroupComponent(String name, QuestionnaireAnswers.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeQuestionnaireAnswersGroupComponentInner(element);
      close();
    }
  }

  private void composeQuestionnaireAnswersGroupComponentInner(QuestionnaireAnswers.GroupComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkIdElement(), false);
      composeStringExtras("linkId", element.getLinkIdElement(), false);
      composeStringCore("title", element.getTitleElement(), false);
      composeStringExtras("title", element.getTitleElement(), false);
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
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
  }

  private void composeQuestionnaireAnswersQuestionComponent(String name, QuestionnaireAnswers.QuestionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeQuestionnaireAnswersQuestionComponentInner(element);
      close();
    }
  }

  private void composeQuestionnaireAnswersQuestionComponentInner(QuestionnaireAnswers.QuestionComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("linkId", element.getLinkIdElement(), false);
      composeStringExtras("linkId", element.getLinkIdElement(), false);
      composeStringCore("text", element.getTextElement(), false);
      composeStringExtras("text", element.getTextElement(), false);
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
  }

  private void composeQuestionnaireAnswersQuestionAnswerComponent(String name, QuestionnaireAnswers.QuestionAnswerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeQuestionnaireAnswersQuestionAnswerComponentInner(element);
      close();
    }
  }

  private void composeQuestionnaireAnswersQuestionAnswerComponentInner(QuestionnaireAnswers.QuestionAnswerComponent element) throws Exception {
      composeBackbone(element);
      composeType("value", element.getValue());
  }

  private void composeReferralRequest(String name, ReferralRequest element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeReferralRequestInner(element);
    }
  }

  private void composeReferralRequestInner(ReferralRequest element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new ReferralRequest.ReferralstatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new ReferralRequest.ReferralstatusEnumFactory(), false);
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
      composeReference("patient", element.getPatient());
      composeReference("requester", element.getRequester());
      if (element.getRecipient().size() > 0) {
        openArray("recipient");
        for (Reference e : element.getRecipient()) 
          composeReference(null, e);
        closeArray();
      };
      composeReference("encounter", element.getEncounter());
      composeDateTimeCore("dateSent", element.getDateSentElement(), false);
      composeDateTimeExtras("dateSent", element.getDateSentElement(), false);
      composeCodeableConcept("reason", element.getReason());
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
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

  private void composeRelatedPerson(String name, RelatedPerson element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeRelatedPersonInner(element);
    }
  }

  private void composeRelatedPersonInner(RelatedPerson element) throws Exception {
      composeDomainResourceElements(element);
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
      if (element.getGenderElement() != null) {
        composeEnumerationCore("gender", element.getGenderElement(), new RelatedPerson.AdministrativeGenderEnumFactory(), false);
        composeEnumerationExtras("gender", element.getGenderElement(), new RelatedPerson.AdministrativeGenderEnumFactory(), false);
      }
      composeAddress("address", element.getAddress());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
  }

  private void composeRiskAssessment(String name, RiskAssessment element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeRiskAssessmentInner(element);
    }
  }

  private void composeRiskAssessmentInner(RiskAssessment element) throws Exception {
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
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
      composeStringCore("mitigation", element.getMitigationElement(), false);
      composeStringExtras("mitigation", element.getMitigationElement(), false);
  }

  private void composeRiskAssessmentRiskAssessmentPredictionComponent(String name, RiskAssessment.RiskAssessmentPredictionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeRiskAssessmentRiskAssessmentPredictionComponentInner(element);
      close();
    }
  }

  private void composeRiskAssessmentRiskAssessmentPredictionComponentInner(RiskAssessment.RiskAssessmentPredictionComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("probability", element.getProbability());
      composeDecimalCore("relativeRisk", element.getRelativeRiskElement(), false);
      composeDecimalExtras("relativeRisk", element.getRelativeRiskElement(), false);
      composeType("when", element.getWhen());
      composeStringCore("rationale", element.getRationaleElement(), false);
      composeStringExtras("rationale", element.getRationaleElement(), false);
  }

  private void composeSecurityEvent(String name, SecurityEvent element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSecurityEventInner(element);
    }
  }

  private void composeSecurityEventInner(SecurityEvent element) throws Exception {
      composeDomainResourceElements(element);
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

  private void composeSecurityEventSecurityEventEventComponent(String name, SecurityEvent.SecurityEventEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSecurityEventSecurityEventEventComponentInner(element);
      close();
    }
  }

  private void composeSecurityEventSecurityEventEventComponentInner(SecurityEvent.SecurityEventEventComponent element) throws Exception {
      composeBackbone(element);
      composeCodeableConcept("type", element.getType());
      if (element.getSubtype().size() > 0) {
        openArray("subtype");
        for (CodeableConcept e : element.getSubtype()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getActionElement() != null) {
        composeEnumerationCore("action", element.getActionElement(), new SecurityEvent.SecurityEventActionEnumFactory(), false);
        composeEnumerationExtras("action", element.getActionElement(), new SecurityEvent.SecurityEventActionEnumFactory(), false);
      }
      composeInstantCore("dateTime", element.getDateTimeElement(), false);
      composeInstantExtras("dateTime", element.getDateTimeElement(), false);
      if (element.getOutcomeElement() != null) {
        composeEnumerationCore("outcome", element.getOutcomeElement(), new SecurityEvent.SecurityEventOutcomeEnumFactory(), false);
        composeEnumerationExtras("outcome", element.getOutcomeElement(), new SecurityEvent.SecurityEventOutcomeEnumFactory(), false);
      }
      composeStringCore("outcomeDesc", element.getOutcomeDescElement(), false);
      composeStringExtras("outcomeDesc", element.getOutcomeDescElement(), false);
  }

  private void composeSecurityEventSecurityEventParticipantComponent(String name, SecurityEvent.SecurityEventParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSecurityEventSecurityEventParticipantComponentInner(element);
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantComponentInner(SecurityEvent.SecurityEventParticipantComponent element) throws Exception {
      composeBackbone(element);
      if (element.getRole().size() > 0) {
        openArray("role");
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeReference("reference", element.getReference());
      composeStringCore("userId", element.getUserIdElement(), false);
      composeStringExtras("userId", element.getUserIdElement(), false);
      composeStringCore("altId", element.getAltIdElement(), false);
      composeStringExtras("altId", element.getAltIdElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeBooleanCore("requestor", element.getRequestorElement(), false);
      composeBooleanExtras("requestor", element.getRequestorElement(), false);
      composeCoding("media", element.getMedia());
      composeSecurityEventSecurityEventParticipantNetworkComponent("network", element.getNetwork());
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponent(String name, SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSecurityEventSecurityEventParticipantNetworkComponentInner(element);
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponentInner(SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("identifier", element.getIdentifierElement(), false);
      composeStringExtras("identifier", element.getIdentifierElement(), false);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new SecurityEvent.NetworkTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new SecurityEvent.NetworkTypeEnumFactory(), false);
      }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSecurityEventSecurityEventSourceComponentInner(element);
      close();
    }
  }

  private void composeSecurityEventSecurityEventSourceComponentInner(SecurityEvent.SecurityEventSourceComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("site", element.getSiteElement(), false);
      composeStringExtras("site", element.getSiteElement(), false);
      composeStringCore("identifier", element.getIdentifierElement(), false);
      composeStringExtras("identifier", element.getIdentifierElement(), false);
      if (element.getType().size() > 0) {
        openArray("type");
        for (Coding e : element.getType()) 
          composeCoding(null, e);
        closeArray();
      };
  }

  private void composeSecurityEventSecurityEventObjectComponent(String name, SecurityEvent.SecurityEventObjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSecurityEventSecurityEventObjectComponentInner(element);
      close();
    }
  }

  private void composeSecurityEventSecurityEventObjectComponentInner(SecurityEvent.SecurityEventObjectComponent element) throws Exception {
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("reference", element.getReference());
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new SecurityEvent.ObjectTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new SecurityEvent.ObjectTypeEnumFactory(), false);
      }
      if (element.getRoleElement() != null) {
        composeEnumerationCore("role", element.getRoleElement(), new SecurityEvent.ObjectRoleEnumFactory(), false);
        composeEnumerationExtras("role", element.getRoleElement(), new SecurityEvent.ObjectRoleEnumFactory(), false);
      }
      if (element.getLifecycleElement() != null) {
        composeEnumerationCore("lifecycle", element.getLifecycleElement(), new SecurityEvent.ObjectLifecycleEnumFactory(), false);
        composeEnumerationExtras("lifecycle", element.getLifecycleElement(), new SecurityEvent.ObjectLifecycleEnumFactory(), false);
      }
      composeCodeableConcept("sensitivity", element.getSensitivity());
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeBase64BinaryCore("query", element.getQueryElement(), false);
      composeBase64BinaryExtras("query", element.getQueryElement(), false);
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (SecurityEvent.SecurityEventObjectDetailComponent e : element.getDetail()) 
          composeSecurityEventSecurityEventObjectDetailComponent(null, e);
        closeArray();
      };
  }

  private void composeSecurityEventSecurityEventObjectDetailComponent(String name, SecurityEvent.SecurityEventObjectDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSecurityEventSecurityEventObjectDetailComponentInner(element);
      close();
    }
  }

  private void composeSecurityEventSecurityEventObjectDetailComponentInner(SecurityEvent.SecurityEventObjectDetailComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("type", element.getTypeElement(), false);
      composeStringExtras("type", element.getTypeElement(), false);
      composeBase64BinaryCore("value", element.getValueElement(), false);
      composeBase64BinaryExtras("value", element.getValueElement(), false);
  }

  private void composeSlot(String name, Slot element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSlotInner(element);
    }
  }

  private void composeSlotInner(Slot element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCodeableConcept("type", element.getType());
      composeReference("availability", element.getAvailability());
      if (element.getFreeBusyTypeElement() != null) {
        composeEnumerationCore("freeBusyType", element.getFreeBusyTypeElement(), new Slot.SlotstatusEnumFactory(), false);
        composeEnumerationExtras("freeBusyType", element.getFreeBusyTypeElement(), new Slot.SlotstatusEnumFactory(), false);
      }
      composeInstantCore("start", element.getStartElement(), false);
      composeInstantExtras("start", element.getStartElement(), false);
      composeInstantCore("end", element.getEndElement(), false);
      composeInstantExtras("end", element.getEndElement(), false);
      composeBooleanCore("overbooked", element.getOverbookedElement(), false);
      composeBooleanExtras("overbooked", element.getOverbookedElement(), false);
      composeStringCore("comment", element.getCommentElement(), false);
      composeStringExtras("comment", element.getCommentElement(), false);
      composeDateTimeCore("lastModified", element.getLastModifiedElement(), false);
      composeDateTimeExtras("lastModified", element.getLastModifiedElement(), false);
  }

  private void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSpecimenInner(element);
    }
  }

  private void composeSpecimenInner(Specimen element) throws Exception {
      composeDomainResourceElements(element);
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
      composeDateTimeCore("receivedTime", element.getReceivedTimeElement(), false);
      composeDateTimeExtras("receivedTime", element.getReceivedTimeElement(), false);
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

  private void composeSpecimenSpecimenSourceComponent(String name, Specimen.SpecimenSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSpecimenSpecimenSourceComponentInner(element);
      close();
    }
  }

  private void composeSpecimenSpecimenSourceComponentInner(Specimen.SpecimenSourceComponent element) throws Exception {
      composeBackbone(element);
      if (element.getRelationshipElement() != null) {
        composeEnumerationCore("relationship", element.getRelationshipElement(), new Specimen.HierarchicalRelationshipTypeEnumFactory(), false);
        composeEnumerationExtras("relationship", element.getRelationshipElement(), new Specimen.HierarchicalRelationshipTypeEnumFactory(), false);
      }
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Reference e : element.getTarget()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSpecimenSpecimenCollectionComponentInner(element);
      close();
    }
  }

  private void composeSpecimenSpecimenCollectionComponentInner(Specimen.SpecimenCollectionComponent element) throws Exception {
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
  }

  private void composeSpecimenSpecimenTreatmentComponent(String name, Specimen.SpecimenTreatmentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSpecimenSpecimenTreatmentComponentInner(element);
      close();
    }
  }

  private void composeSpecimenSpecimenTreatmentComponentInner(Specimen.SpecimenTreatmentComponent element) throws Exception {
      composeBackbone(element);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeCodeableConcept("procedure", element.getProcedure());
      if (element.getAdditive().size() > 0) {
        openArray("additive");
        for (Reference e : element.getAdditive()) 
          composeReference(null, e);
        closeArray();
      };
  }

  private void composeSpecimenSpecimenContainerComponent(String name, Specimen.SpecimenContainerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSpecimenSpecimenContainerComponentInner(element);
      close();
    }
  }

  private void composeSpecimenSpecimenContainerComponentInner(Specimen.SpecimenContainerComponent element) throws Exception {
      composeBackbone(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeCodeableConcept("type", element.getType());
      composeQuantity("capacity", element.getCapacity());
      composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      composeType("additive", element.getAdditive());
  }

  private void composeSubscription(String name, Subscription element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSubscriptionInner(element);
    }
  }

  private void composeSubscriptionInner(Subscription element) throws Exception {
      composeDomainResourceElements(element);
      composeStringCore("criteria", element.getCriteriaElement(), false);
      composeStringExtras("criteria", element.getCriteriaElement(), false);
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (ContactPoint e : element.getContact()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("reason", element.getReasonElement(), false);
      composeStringExtras("reason", element.getReasonElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Subscription.SubscriptionStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Subscription.SubscriptionStatusEnumFactory(), false);
      }
      composeStringCore("error", element.getErrorElement(), false);
      composeStringExtras("error", element.getErrorElement(), false);
      composeSubscriptionSubscriptionChannelComponent("channel", element.getChannel());
      composeInstantCore("end", element.getEndElement(), false);
      composeInstantExtras("end", element.getEndElement(), false);
      if (element.getTag().size() > 0) {
        openArray("tag");
        for (Subscription.SubscriptionTagComponent e : element.getTag()) 
          composeSubscriptionSubscriptionTagComponent(null, e);
        closeArray();
      };
  }

  private void composeSubscriptionSubscriptionChannelComponent(String name, Subscription.SubscriptionChannelComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSubscriptionSubscriptionChannelComponentInner(element);
      close();
    }
  }

  private void composeSubscriptionSubscriptionChannelComponentInner(Subscription.SubscriptionChannelComponent element) throws Exception {
      composeBackbone(element);
      if (element.getTypeElement() != null) {
        composeEnumerationCore("type", element.getTypeElement(), new Subscription.SubscriptionChannelTypeEnumFactory(), false);
        composeEnumerationExtras("type", element.getTypeElement(), new Subscription.SubscriptionChannelTypeEnumFactory(), false);
      }
      composeUriCore("url", element.getUrlElement(), false);
      composeUriExtras("url", element.getUrlElement(), false);
      composeStringCore("payload", element.getPayloadElement(), false);
      composeStringExtras("payload", element.getPayloadElement(), false);
      composeStringCore("header", element.getHeaderElement(), false);
      composeStringExtras("header", element.getHeaderElement(), false);
  }

  private void composeSubscriptionSubscriptionTagComponent(String name, Subscription.SubscriptionTagComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSubscriptionSubscriptionTagComponentInner(element);
      close();
    }
  }

  private void composeSubscriptionSubscriptionTagComponentInner(Subscription.SubscriptionTagComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("term", element.getTermElement(), false);
      composeUriExtras("term", element.getTermElement(), false);
      composeUriCore("scheme", element.getSchemeElement(), false);
      composeUriExtras("scheme", element.getSchemeElement(), false);
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSubstanceInner(element);
    }
  }

  private void composeSubstanceInner(Substance element) throws Exception {
      composeDomainResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeSubstanceSubstanceInstanceComponent("instance", element.getInstance());
      if (element.getIngredient().size() > 0) {
        openArray("ingredient");
        for (Substance.SubstanceIngredientComponent e : element.getIngredient()) 
          composeSubstanceSubstanceIngredientComponent(null, e);
        closeArray();
      };
  }

  private void composeSubstanceSubstanceInstanceComponent(String name, Substance.SubstanceInstanceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSubstanceSubstanceInstanceComponentInner(element);
      close();
    }
  }

  private void composeSubstanceSubstanceInstanceComponentInner(Substance.SubstanceInstanceComponent element) throws Exception {
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTimeCore("expiry", element.getExpiryElement(), false);
      composeDateTimeExtras("expiry", element.getExpiryElement(), false);
      composeQuantity("quantity", element.getQuantity());
  }

  private void composeSubstanceSubstanceIngredientComponent(String name, Substance.SubstanceIngredientComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSubstanceSubstanceIngredientComponentInner(element);
      close();
    }
  }

  private void composeSubstanceSubstanceIngredientComponentInner(Substance.SubstanceIngredientComponent element) throws Exception {
      composeBackbone(element);
      composeRatio("quantity", element.getQuantity());
      composeReference("substance", element.getSubstance());
  }

  private void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSupplyInner(element);
    }
  }

  private void composeSupplyInner(Supply element) throws Exception {
      composeDomainResourceElements(element);
      composeCodeableConcept("kind", element.getKind());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Supply.ValuesetSupplyStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Supply.ValuesetSupplyStatusEnumFactory(), false);
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

  private void composeSupplySupplyDispenseComponent(String name, Supply.SupplyDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSupplySupplyDispenseComponentInner(element);
      close();
    }
  }

  private void composeSupplySupplyDispenseComponentInner(Supply.SupplyDispenseComponent element) throws Exception {
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new Supply.ValuesetSupplyDispenseStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new Supply.ValuesetSupplyDispenseStatusEnumFactory(), false);
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
  }

  private void composeSupportingDocumentation(String name, SupportingDocumentation element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeSupportingDocumentationInner(element);
    }
  }

  private void composeSupportingDocumentationInner(SupportingDocumentation element) throws Exception {
      composeDomainResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDateCore("date", element.getDateElement(), false);
      composeDateExtras("date", element.getDateElement(), false);
      composeReference("target", element.getTarget());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      composeIdentifier("requestIdentifier", element.getRequestIdentifier());
      composeReference("request", element.getRequest());
      composeIdentifier("responseIdentifier", element.getResponseIdentifier());
      composeReference("response", element.getResponse());
      composeReference("author", element.getAuthor());
      composeReference("subject", element.getSubject());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (SupportingDocumentation.SupportingDocumentationDetailComponent e : element.getDetail()) 
          composeSupportingDocumentationSupportingDocumentationDetailComponent(null, e);
        closeArray();
      };
  }

  private void composeSupportingDocumentationSupportingDocumentationDetailComponent(String name, SupportingDocumentation.SupportingDocumentationDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeSupportingDocumentationSupportingDocumentationDetailComponentInner(element);
      close();
    }
  }

  private void composeSupportingDocumentationSupportingDocumentationDetailComponentInner(SupportingDocumentation.SupportingDocumentationDetailComponent element) throws Exception {
      composeBackbone(element);
      composeIntegerCore("linkId", element.getLinkIdElement(), false);
      composeIntegerExtras("linkId", element.getLinkIdElement(), false);
      composeType("content", element.getContent());
      composeDateCore("date", element.getDateElement(), false);
      composeDateExtras("date", element.getDateElement(), false);
  }

  private void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      prop("resourceType", name);
      composeValueSetInner(element);
    }
  }

  private void composeValueSetInner(ValueSet element) throws Exception {
      composeDomainResourceElements(element);
      composeUriCore("identifier", element.getIdentifierElement(), false);
      composeUriExtras("identifier", element.getIdentifierElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeStringCore("name", element.getNameElement(), false);
      composeStringExtras("name", element.getNameElement(), false);
      composeStringCore("purpose", element.getPurposeElement(), false);
      composeStringExtras("purpose", element.getPurposeElement(), false);
      composeBooleanCore("immutable", element.getImmutableElement(), false);
      composeBooleanExtras("immutable", element.getImmutableElement(), false);
      composeStringCore("publisher", element.getPublisherElement(), false);
      composeStringExtras("publisher", element.getPublisherElement(), false);
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (ContactPoint e : element.getTelecom()) 
          composeContactPoint(null, e);
        closeArray();
      };
      composeStringCore("description", element.getDescriptionElement(), false);
      composeStringExtras("description", element.getDescriptionElement(), false);
      composeStringCore("copyright", element.getCopyrightElement(), false);
      composeStringExtras("copyright", element.getCopyrightElement(), false);
      if (element.getStatusElement() != null) {
        composeEnumerationCore("status", element.getStatusElement(), new ValueSet.ValuesetStatusEnumFactory(), false);
        composeEnumerationExtras("status", element.getStatusElement(), new ValueSet.ValuesetStatusEnumFactory(), false);
      }
      composeBooleanCore("experimental", element.getExperimentalElement(), false);
      composeBooleanExtras("experimental", element.getExperimentalElement(), false);
      composeBooleanCore("extensible", element.getExtensibleElement(), false);
      composeBooleanExtras("extensible", element.getExtensibleElement(), false);
      composeDateTimeCore("date", element.getDateElement(), false);
      composeDateTimeExtras("date", element.getDateElement(), false);
      composeDateCore("stableDate", element.getStableDateElement(), false);
      composeDateExtras("stableDate", element.getStableDateElement(), false);
      composeValueSetValueSetDefineComponent("define", element.getDefine());
      composeValueSetValueSetComposeComponent("compose", element.getCompose());
      composeValueSetValueSetExpansionComponent("expansion", element.getExpansion());
  }

  private void composeValueSetValueSetDefineComponent(String name, ValueSet.ValueSetDefineComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetValueSetDefineComponentInner(element);
      close();
    }
  }

  private void composeValueSetValueSetDefineComponentInner(ValueSet.ValueSetDefineComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeBooleanCore("caseSensitive", element.getCaseSensitiveElement(), false);
      composeBooleanExtras("caseSensitive", element.getCaseSensitiveElement(), false);
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
          composeValueSetConceptDefinitionComponent(null, e);
        closeArray();
      };
  }

  private void composeValueSetConceptDefinitionComponent(String name, ValueSet.ConceptDefinitionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetConceptDefinitionComponentInner(element);
      close();
    }
  }

  private void composeValueSetConceptDefinitionComponentInner(ValueSet.ConceptDefinitionComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
      composeBooleanCore("abstract", element.getAbstractElement(), false);
      composeBooleanExtras("abstract", element.getAbstractElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
      composeStringCore("definition", element.getDefinitionElement(), false);
      composeStringExtras("definition", element.getDefinitionElement(), false);
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
  }

  private void composeValueSetConceptDefinitionDesignationComponent(String name, ValueSet.ConceptDefinitionDesignationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetConceptDefinitionDesignationComponentInner(element);
      close();
    }
  }

  private void composeValueSetConceptDefinitionDesignationComponentInner(ValueSet.ConceptDefinitionDesignationComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("language", element.getLanguageElement(), false);
      composeCodeExtras("language", element.getLanguageElement(), false);
      composeCoding("use", element.getUse());
      composeStringCore("value", element.getValueElement(), false);
      composeStringExtras("value", element.getValueElement(), false);
  }

  private void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetValueSetComposeComponentInner(element);
      close();
    }
  }

  private void composeValueSetValueSetComposeComponentInner(ValueSet.ValueSetComposeComponent element) throws Exception {
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
  }

  private void composeValueSetConceptSetComponent(String name, ValueSet.ConceptSetComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetConceptSetComponentInner(element);
      close();
    }
  }

  private void composeValueSetConceptSetComponentInner(ValueSet.ConceptSetComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
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
  }

  private void composeValueSetConceptReferenceComponent(String name, ValueSet.ConceptReferenceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetConceptReferenceComponentInner(element);
      close();
    }
  }

  private void composeValueSetConceptReferenceComponentInner(ValueSet.ConceptReferenceComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
      if (element.getDesignation().size() > 0) {
        openArray("designation");
        for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
          composeValueSetConceptDefinitionDesignationComponent(null, e);
        closeArray();
      };
  }

  private void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetConceptSetFilterComponentInner(element);
      close();
    }
  }

  private void composeValueSetConceptSetFilterComponentInner(ValueSet.ConceptSetFilterComponent element) throws Exception {
      composeBackbone(element);
      composeCodeCore("property", element.getPropertyElement(), false);
      composeCodeExtras("property", element.getPropertyElement(), false);
      if (element.getOpElement() != null) {
        composeEnumerationCore("op", element.getOpElement(), new ValueSet.FilterOperatorEnumFactory(), false);
        composeEnumerationExtras("op", element.getOpElement(), new ValueSet.FilterOperatorEnumFactory(), false);
      }
      composeCodeCore("value", element.getValueElement(), false);
      composeCodeExtras("value", element.getValueElement(), false);
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetValueSetExpansionComponentInner(element);
      close();
    }
  }

  private void composeValueSetValueSetExpansionComponentInner(ValueSet.ValueSetExpansionComponent element) throws Exception {
      composeBackbone(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTimeCore("timestamp", element.getTimestampElement(), false);
      composeDateTimeExtras("timestamp", element.getTimestampElement(), false);
      if (element.getContains().size() > 0) {
        openArray("contains");
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent(null, e);
        closeArray();
      };
  }

  private void composeValueSetValueSetExpansionContainsComponent(String name, ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeValueSetValueSetExpansionContainsComponentInner(element);
      close();
    }
  }

  private void composeValueSetValueSetExpansionContainsComponentInner(ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
      composeBackbone(element);
      composeUriCore("system", element.getSystemElement(), false);
      composeUriExtras("system", element.getSystemElement(), false);
      composeBooleanCore("abstract", element.getAbstractElement(), false);
      composeBooleanExtras("abstract", element.getAbstractElement(), false);
      composeStringCore("version", element.getVersionElement(), false);
      composeStringExtras("version", element.getVersionElement(), false);
      composeCodeCore("code", element.getCodeElement(), false);
      composeCodeExtras("code", element.getCodeElement(), false);
      composeStringCore("display", element.getDisplayElement(), false);
      composeStringExtras("display", element.getDisplayElement(), false);
      if (element.getContains().size() > 0) {
        openArray("contains");
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent(null, e);
        closeArray();
      };
  }

  @Override
  protected void composeResource(Resource resource) throws Exception {
    if (resource instanceof Alert)
      composeAlert("Alert", (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance("AllergyIntolerance", (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment("Appointment", (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse("AppointmentResponse", (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability("Availability", (Availability)resource);
    else if (resource instanceof Basic)
      composeBasic("Basic", (Basic)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else if (resource instanceof Bundle)
      composeBundle("Bundle", (Bundle)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan("CarePlan", (CarePlan)resource);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse("ClaimResponse", (ClaimResponse)resource);
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
    else if (resource instanceof Eligibility)
      composeEligibility("Eligibility", (Eligibility)resource);
    else if (resource instanceof Encounter)
      composeEncounter("Encounter", (Encounter)resource);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit("ExplanationOfBenefit", (ExplanationOfBenefit)resource);
    else if (resource instanceof ExtensionDefinition)
      composeExtensionDefinition("ExtensionDefinition", (ExtensionDefinition)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory("FamilyHistory", (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup("Group", (Group)resource);
    else if (resource instanceof HealthcareService)
      composeHealthcareService("HealthcareService", (HealthcareService)resource);
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
    else if (resource instanceof OralHealthClaim)
      composeOralHealthClaim("OralHealthClaim", (OralHealthClaim)resource);
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
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest("ProcedureRequest", (ProcedureRequest)resource);
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
    else if (resource instanceof SupportingDocumentation)
      composeSupportingDocumentation("SupportingDocumentation", (SupportingDocumentation)resource);
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeNamedReference(String name, Resource resource) throws Exception {
    if (resource instanceof Alert)
      composeAlert(name, (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance(name, (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment(name, (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse(name, (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability(name, (Availability)resource);
    else if (resource instanceof Basic)
      composeBasic(name, (Basic)resource);
    else if (resource instanceof Binary)
      composeBinary(name, (Binary)resource);
    else if (resource instanceof Bundle)
      composeBundle(name, (Bundle)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan(name, (CarePlan)resource);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse(name, (ClaimResponse)resource);
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
    else if (resource instanceof Eligibility)
      composeEligibility(name, (Eligibility)resource);
    else if (resource instanceof Encounter)
      composeEncounter(name, (Encounter)resource);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit(name, (ExplanationOfBenefit)resource);
    else if (resource instanceof ExtensionDefinition)
      composeExtensionDefinition(name, (ExtensionDefinition)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory(name, (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup(name, (Group)resource);
    else if (resource instanceof HealthcareService)
      composeHealthcareService(name, (HealthcareService)resource);
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
    else if (resource instanceof OralHealthClaim)
      composeOralHealthClaim(name, (OralHealthClaim)resource);
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
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest(name, (ProcedureRequest)resource);
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
    else if (resource instanceof SupportingDocumentation)
      composeSupportingDocumentation(name, (SupportingDocumentation)resource);
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
    else if (type instanceof ElementDefinition)
       composeElementDefinition(prefix+"ElementDefinition", (ElementDefinition) type);
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

  protected void composeTypeInner(Type type) throws Exception {
    if (type == null)
      ;
    else if (type instanceof Period)
       composePeriodInner((Period) type);
    else if (type instanceof Coding)
       composeCodingInner((Coding) type);
    else if (type instanceof Range)
       composeRangeInner((Range) type);
    else if (type instanceof Quantity)
       composeQuantityInner((Quantity) type);
    else if (type instanceof Attachment)
       composeAttachmentInner((Attachment) type);
    else if (type instanceof Ratio)
       composeRatioInner((Ratio) type);
    else if (type instanceof SampledData)
       composeSampledDataInner((SampledData) type);
    else if (type instanceof Reference)
       composeReferenceInner((Reference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConceptInner((CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifierInner((Identifier) type);
    else if (type instanceof Age)
       composeAgeInner((Age) type);
    else if (type instanceof Count)
       composeCountInner((Count) type);
    else if (type instanceof Money)
       composeMoneyInner((Money) type);
    else if (type instanceof Distance)
       composeDistanceInner((Distance) type);
    else if (type instanceof Duration)
       composeDurationInner((Duration) type);
    else if (type instanceof ElementDefinition)
       composeElementDefinitionInner((ElementDefinition) type);
    else if (type instanceof Timing)
       composeTimingInner((Timing) type);
    else if (type instanceof Address)
       composeAddressInner((Address) type);
    else if (type instanceof HumanName)
       composeHumanNameInner((HumanName) type);
    else if (type instanceof ContactPoint)
       composeContactPointInner((ContactPoint) type);
    else
      throw new Exception("Unhanded type");
  }

}

