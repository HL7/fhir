package org.hl7.fhir.dstu3.formats;

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

// Generated on Wed, Feb 24, 2016 10:46+1100 for FHIR v1.3.0

import org.hl7.fhir.dstu3.model.MarkdownType;
import org.hl7.fhir.dstu3.model.IntegerType;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.UnsignedIntType;
import org.hl7.fhir.dstu3.model.CodeType;
import org.hl7.fhir.dstu3.model.DateType;
import org.hl7.fhir.dstu3.model.DecimalType;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Base64BinaryType;
import org.hl7.fhir.dstu3.model.TimeType;
import org.hl7.fhir.dstu3.model.OidType;
import org.hl7.fhir.dstu3.model.PositiveIntType;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.UuidType;
import org.hl7.fhir.dstu3.model.InstantType;
import org.hl7.fhir.dstu3.model.*;
import org.xmlpull.v1.*;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.formats.RdfGenerator.Complex;
import java.io.IOException;

public class RdfParser extends RdfParserBase {

  public RdfParser() {
    super();
  }

  public RdfParser(boolean allowUnknownContent) {
    super();
    setAllowUnknownContent(allowUnknownContent);
  }


  protected void composeElement(Complex t, String parentType, String name, Element element, int index) {
    if (element == null) 
      return;
    if (index > -1)
      t.predicate("fhir:index", Integer.toString(index));
    composeId(t, "Element", "id", element.getIdElement(), -1);
    for (int i = 0; i < element.getExtension().size(); i++)
      composeExtension(t, "Element", "extension", element.getExtension().get(i), i);
  }

  protected void composeBackboneElement(Complex t, String tType, String name, BackboneElement element, int index) {
    composeElement(t, tType, name, element, index);
    for (int i = 0; i < element.getModifierExtension().size(); i++)
      composeExtension(t, "Element", "modifierExtension", element.getModifierExtension().get(i), i);
  }

private void composeEnum(Complex parent, String parentType, String name, Enumeration<? extends Enum> value, int index) {
  if (value == null)
    return;
  Complex t = parent.predicate("fhir:"+parentType+"."+name);
  t.predicate("fhir:value", ttlLiteral(value.toSystem()));
  composeElement(t, parentType, name, value, index);
}


  protected void composeMarkdown(Complex parent, String parentType, String name, MarkdownType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeInteger(Complex parent, String parentType, String name, IntegerType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeDateTime(Complex parent, String parentType, String name, DateTimeType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeUnsignedInt(Complex parent, String parentType, String name, UnsignedIntType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeCode(Complex parent, String parentType, String name, CodeType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeDate(Complex parent, String parentType, String name, DateType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeDecimal(Complex parent, String parentType, String name, DecimalType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeUri(Complex parent, String parentType, String name, UriType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeId(Complex parent, String parentType, String name, IdType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeBase64Binary(Complex parent, String parentType, String name, Base64BinaryType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeTime(Complex parent, String parentType, String name, TimeType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeOid(Complex parent, String parentType, String name, OidType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composePositiveInt(Complex parent, String parentType, String name, PositiveIntType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeString(Complex parent, String parentType, String name, StringType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeBoolean(Complex parent, String parentType, String name, BooleanType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeUuid(Complex parent, String parentType, String name, UuidType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeInstant(Complex parent, String parentType, String name, InstantType value, int index) {
    if (value == null)
      return;
    Complex t = parent.predicate("fhir:"+parentType+"."+name);
    t.predicate("fhir:value", ttlLiteral(value.toString()));
    composeElement(t, parentType, name, value, index);
  }

  protected void composeExtension(Complex parent, String parentType, String name, Extension element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Extension", name, element, index);
    composeUri(t, "Extension", "url", element.getUrlElement(), -1);
    composeType(t, "Extension", "value", element.getValue(), -1);
  }

  protected void composeNarrative(Complex parent, String parentType, String name, Narrative element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Narrative", name, element, index);
    composeEnum(t, "Narrative", "status", element.getStatusElement(), -1);
    composeXhtml(t, "Narrative", "div", element.getDiv(), -1);
  }

  protected void composePeriod(Complex parent, String parentType, String name, Period element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Period", name, element, index);
    composeDateTime(t, "Period", "start", element.getStartElement(), -1);
    composeDateTime(t, "Period", "end", element.getEndElement(), -1);
  }

  protected void composeCoding(Complex parent, String parentType, String name, Coding element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Coding", name, element, index);
    composeUri(t, "Coding", "system", element.getSystemElement(), -1);
    composeString(t, "Coding", "version", element.getVersionElement(), -1);
    composeCode(t, "Coding", "code", element.getCodeElement(), -1);
    composeString(t, "Coding", "display", element.getDisplayElement(), -1);
    composeBoolean(t, "Coding", "userSelected", element.getUserSelectedElement(), -1);
  }

  protected void composeRange(Complex parent, String parentType, String name, Range element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Range", name, element, index);
    composeQuantity(t, "Range", "low", element.getLow(), -1);
    composeQuantity(t, "Range", "high", element.getHigh(), -1);
  }

  protected void composeQuantity(Complex parent, String parentType, String name, Quantity element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Quantity", name, element, index);
    composeDecimal(t, "Quantity", "value", element.getValueElement(), -1);
    composeEnum(t, "Quantity", "comparator", element.getComparatorElement(), -1);
    composeString(t, "Quantity", "unit", element.getUnitElement(), -1);
    composeUri(t, "Quantity", "system", element.getSystemElement(), -1);
    composeCode(t, "Quantity", "code", element.getCodeElement(), -1);
  }

  protected void composeAttachment(Complex parent, String parentType, String name, Attachment element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Attachment", name, element, index);
    composeCode(t, "Attachment", "contentType", element.getContentTypeElement(), -1);
    composeCode(t, "Attachment", "language", element.getLanguageElement(), -1);
    composeBase64Binary(t, "Attachment", "data", element.getDataElement(), -1);
    composeUri(t, "Attachment", "url", element.getUrlElement(), -1);
    composeUnsignedInt(t, "Attachment", "size", element.getSizeElement(), -1);
    composeBase64Binary(t, "Attachment", "hash", element.getHashElement(), -1);
    composeString(t, "Attachment", "title", element.getTitleElement(), -1);
    composeDateTime(t, "Attachment", "creation", element.getCreationElement(), -1);
  }

  protected void composeRatio(Complex parent, String parentType, String name, Ratio element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Ratio", name, element, index);
    composeQuantity(t, "Ratio", "numerator", element.getNumerator(), -1);
    composeQuantity(t, "Ratio", "denominator", element.getDenominator(), -1);
  }

  protected void composeAnnotation(Complex parent, String parentType, String name, Annotation element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Annotation", name, element, index);
    composeType(t, "Annotation", "author", element.getAuthor(), -1);
    composeDateTime(t, "Annotation", "time", element.getTimeElement(), -1);
    composeString(t, "Annotation", "text", element.getTextElement(), -1);
  }

  protected void composeSampledData(Complex parent, String parentType, String name, SampledData element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "SampledData", name, element, index);
    composeQuantity(t, "SampledData", "origin", element.getOrigin(), -1);
    composeDecimal(t, "SampledData", "period", element.getPeriodElement(), -1);
    composeDecimal(t, "SampledData", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "SampledData", "lowerLimit", element.getLowerLimitElement(), -1);
    composeDecimal(t, "SampledData", "upperLimit", element.getUpperLimitElement(), -1);
    composePositiveInt(t, "SampledData", "dimensions", element.getDimensionsElement(), -1);
    composeString(t, "SampledData", "data", element.getDataElement(), -1);
  }

  protected void composeReference(Complex parent, String parentType, String name, Reference element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Reference", name, element, index);
    composeString(t, "Reference", "reference", element.getReferenceElement_(), -1);
    composeString(t, "Reference", "display", element.getDisplayElement(), -1);
  }

  protected void composeCodeableConcept(Complex parent, String parentType, String name, CodeableConcept element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "CodeableConcept", name, element, index);
    for (int i = 0; i < element.getCoding().size(); i++)
      composeCoding(t, "CodeableConcept", "coding", element.getCoding().get(i), i);
    composeString(t, "CodeableConcept", "text", element.getTextElement(), -1);
  }

  protected void composeIdentifier(Complex parent, String parentType, String name, Identifier element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Identifier", name, element, index);
    composeEnum(t, "Identifier", "use", element.getUseElement(), -1);
    composeCodeableConcept(t, "Identifier", "type", element.getType(), -1);
    composeUri(t, "Identifier", "system", element.getSystemElement(), -1);
    composeString(t, "Identifier", "value", element.getValueElement(), -1);
    composePeriod(t, "Identifier", "period", element.getPeriod(), -1);
    composeReference(t, "Identifier", "assigner", element.getAssigner(), -1);
  }

  protected void composeSignature(Complex parent, String parentType, String name, Signature element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Signature", name, element, index);
    for (int i = 0; i < element.getType().size(); i++)
      composeCoding(t, "Signature", "type", element.getType().get(i), i);
    composeInstant(t, "Signature", "when", element.getWhenElement(), -1);
    composeType(t, "Signature", "who", element.getWho(), -1);
    composeCode(t, "Signature", "contentType", element.getContentTypeElement(), -1);
    composeBase64Binary(t, "Signature", "blob", element.getBlobElement(), -1);
  }

  protected void composeElementDefinition(Complex parent, String parentType, String name, ElementDefinition element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "ElementDefinition", name, element, index);
    composeString(t, "ElementDefinition", "path", element.getPathElement(), -1);
    for (int i = 0; i < element.getRepresentation().size(); i++)
      composeEnum(t, "ElementDefinition", "representation", element.getRepresentation().get(i), i);
    composeString(t, "ElementDefinition", "name", element.getNameElement(), -1);
    composeString(t, "ElementDefinition", "label", element.getLabelElement(), -1);
    for (int i = 0; i < element.getCode().size(); i++)
      composeCoding(t, "ElementDefinition", "code", element.getCode().get(i), i);
    composeElementDefinitionElementDefinitionSlicingComponent(t, "ElementDefinition", "slicing", element.getSlicing(), -1);
    composeString(t, "ElementDefinition", "short", element.getShortElement(), -1);
    composeMarkdown(t, "ElementDefinition", "definition", element.getDefinitionElement(), -1);
    composeMarkdown(t, "ElementDefinition", "comments", element.getCommentsElement(), -1);
    composeMarkdown(t, "ElementDefinition", "requirements", element.getRequirementsElement(), -1);
    for (int i = 0; i < element.getAlias().size(); i++)
      composeString(t, "ElementDefinition", "alias", element.getAlias().get(i), i);
    composeInteger(t, "ElementDefinition", "min", element.getMinElement(), -1);
    composeString(t, "ElementDefinition", "max", element.getMaxElement(), -1);
    composeElementDefinitionElementDefinitionBaseComponent(t, "ElementDefinition", "base", element.getBase(), -1);
    for (int i = 0; i < element.getType().size(); i++)
      composeElementDefinitionTypeRefComponent(t, "ElementDefinition", "type", element.getType().get(i), i);
    composeString(t, "ElementDefinition", "nameReference", element.getNameReferenceElement(), -1);
    composeType(t, "ElementDefinition", "defaultValue", element.getDefaultValue(), -1);
    composeMarkdown(t, "ElementDefinition", "meaningWhenMissing", element.getMeaningWhenMissingElement(), -1);
    composeType(t, "ElementDefinition", "fixed", element.getFixed(), -1);
    composeType(t, "ElementDefinition", "pattern", element.getPattern(), -1);
    composeType(t, "ElementDefinition", "example", element.getExample(), -1);
    composeType(t, "ElementDefinition", "minValue", element.getMinValue(), -1);
    composeType(t, "ElementDefinition", "maxValue", element.getMaxValue(), -1);
    composeInteger(t, "ElementDefinition", "maxLength", element.getMaxLengthElement(), -1);
    for (int i = 0; i < element.getCondition().size(); i++)
      composeId(t, "ElementDefinition", "condition", element.getCondition().get(i), i);
    for (int i = 0; i < element.getConstraint().size(); i++)
      composeElementDefinitionElementDefinitionConstraintComponent(t, "ElementDefinition", "constraint", element.getConstraint().get(i), i);
    composeBoolean(t, "ElementDefinition", "mustSupport", element.getMustSupportElement(), -1);
    composeBoolean(t, "ElementDefinition", "isModifier", element.getIsModifierElement(), -1);
    composeBoolean(t, "ElementDefinition", "isSummary", element.getIsSummaryElement(), -1);
    composeElementDefinitionElementDefinitionBindingComponent(t, "ElementDefinition", "binding", element.getBinding(), -1);
    for (int i = 0; i < element.getMapping().size(); i++)
      composeElementDefinitionElementDefinitionMappingComponent(t, "ElementDefinition", "mapping", element.getMapping().get(i), i);
  }

  protected void composeElementDefinitionElementDefinitionSlicingComponent(Complex parent, String parentType, String name, ElementDefinition.ElementDefinitionSlicingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "slicing", name, element, index);
    for (int i = 0; i < element.getDiscriminator().size(); i++)
      composeString(t, "ElementDefinition", "discriminator", element.getDiscriminator().get(i), i);
    composeString(t, "ElementDefinition", "description", element.getDescriptionElement(), -1);
    composeBoolean(t, "ElementDefinition", "ordered", element.getOrderedElement(), -1);
    composeEnum(t, "ElementDefinition", "rules", element.getRulesElement(), -1);
  }

  protected void composeElementDefinitionElementDefinitionBaseComponent(Complex parent, String parentType, String name, ElementDefinition.ElementDefinitionBaseComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "base", name, element, index);
    composeString(t, "ElementDefinition", "path", element.getPathElement(), -1);
    composeInteger(t, "ElementDefinition", "min", element.getMinElement(), -1);
    composeString(t, "ElementDefinition", "max", element.getMaxElement(), -1);
  }

  protected void composeElementDefinitionTypeRefComponent(Complex parent, String parentType, String name, ElementDefinition.TypeRefComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "type", name, element, index);
    composeCode(t, "ElementDefinition", "code", element.getCodeElement(), -1);
    for (int i = 0; i < element.getProfile().size(); i++)
      composeUri(t, "ElementDefinition", "profile", element.getProfile().get(i), i);
    for (int i = 0; i < element.getAggregation().size(); i++)
      composeEnum(t, "ElementDefinition", "aggregation", element.getAggregation().get(i), i);
  }

  protected void composeElementDefinitionElementDefinitionConstraintComponent(Complex parent, String parentType, String name, ElementDefinition.ElementDefinitionConstraintComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "constraint", name, element, index);
    composeId(t, "ElementDefinition", "key", element.getKeyElement(), -1);
    composeString(t, "ElementDefinition", "requirements", element.getRequirementsElement(), -1);
    composeEnum(t, "ElementDefinition", "severity", element.getSeverityElement(), -1);
    composeString(t, "ElementDefinition", "human", element.getHumanElement(), -1);
    composeString(t, "ElementDefinition", "xpath", element.getXpathElement(), -1);
  }

  protected void composeElementDefinitionElementDefinitionBindingComponent(Complex parent, String parentType, String name, ElementDefinition.ElementDefinitionBindingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "binding", name, element, index);
    composeEnum(t, "ElementDefinition", "strength", element.getStrengthElement(), -1);
    composeString(t, "ElementDefinition", "description", element.getDescriptionElement(), -1);
    composeType(t, "ElementDefinition", "valueSet", element.getValueSet(), -1);
  }

  protected void composeElementDefinitionElementDefinitionMappingComponent(Complex parent, String parentType, String name, ElementDefinition.ElementDefinitionMappingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "mapping", name, element, index);
    composeId(t, "ElementDefinition", "identity", element.getIdentityElement(), -1);
    composeCode(t, "ElementDefinition", "language", element.getLanguageElement(), -1);
    composeString(t, "ElementDefinition", "map", element.getMapElement(), -1);
  }

  protected void composeTiming(Complex parent, String parentType, String name, Timing element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Timing", name, element, index);
    for (int i = 0; i < element.getEvent().size(); i++)
      composeDateTime(t, "Timing", "event", element.getEvent().get(i), i);
    composeTimingTimingRepeatComponent(t, "Timing", "repeat", element.getRepeat(), -1);
    composeCodeableConcept(t, "Timing", "code", element.getCode(), -1);
  }

  protected void composeTimingTimingRepeatComponent(Complex parent, String parentType, String name, Timing.TimingRepeatComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "repeat", name, element, index);
    composeType(t, "Timing", "bounds", element.getBounds(), -1);
    composeInteger(t, "Timing", "count", element.getCountElement(), -1);
    composeDecimal(t, "Timing", "duration", element.getDurationElement(), -1);
    composeDecimal(t, "Timing", "durationMax", element.getDurationMaxElement(), -1);
    composeEnum(t, "Timing", "durationUnits", element.getDurationUnitsElement(), -1);
    composeInteger(t, "Timing", "frequency", element.getFrequencyElement(), -1);
    composeInteger(t, "Timing", "frequencyMax", element.getFrequencyMaxElement(), -1);
    composeDecimal(t, "Timing", "period", element.getPeriodElement(), -1);
    composeDecimal(t, "Timing", "periodMax", element.getPeriodMaxElement(), -1);
    composeEnum(t, "Timing", "periodUnits", element.getPeriodUnitsElement(), -1);
    composeEnum(t, "Timing", "when", element.getWhenElement(), -1);
  }

  protected void composeModuleMetadata(Complex parent, String parentType, String name, ModuleMetadata element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "ModuleMetadata", name, element, index);
    composeUri(t, "ModuleMetadata", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ModuleMetadata", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "ModuleMetadata", "version", element.getVersionElement(), -1);
    composeString(t, "ModuleMetadata", "name", element.getNameElement(), -1);
    composeString(t, "ModuleMetadata", "title", element.getTitleElement(), -1);
    composeEnum(t, "ModuleMetadata", "type", element.getTypeElement(), -1);
    composeEnum(t, "ModuleMetadata", "status", element.getStatusElement(), -1);
    composeBoolean(t, "ModuleMetadata", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "ModuleMetadata", "description", element.getDescriptionElement(), -1);
    composeString(t, "ModuleMetadata", "purpose", element.getPurposeElement(), -1);
    composeString(t, "ModuleMetadata", "usage", element.getUsageElement(), -1);
    composeDate(t, "ModuleMetadata", "publicationDate", element.getPublicationDateElement(), -1);
    composeDate(t, "ModuleMetadata", "lastReviewDate", element.getLastReviewDateElement(), -1);
    composePeriod(t, "ModuleMetadata", "effectivePeriod", element.getEffectivePeriod(), -1);
    for (int i = 0; i < element.getCoverage().size(); i++)
      composeModuleMetadataModuleMetadataCoverageComponent(t, "ModuleMetadata", "coverage", element.getCoverage().get(i), i);
    for (int i = 0; i < element.getTopic().size(); i++)
      composeCodeableConcept(t, "ModuleMetadata", "topic", element.getTopic().get(i), i);
    for (int i = 0; i < element.getContributor().size(); i++)
      composeModuleMetadataModuleMetadataContributorComponent(t, "ModuleMetadata", "contributor", element.getContributor().get(i), i);
    composeString(t, "ModuleMetadata", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeModuleMetadataModuleMetadataContactComponent(t, "ModuleMetadata", "contact", element.getContact().get(i), i);
    composeString(t, "ModuleMetadata", "copyright", element.getCopyrightElement(), -1);
    for (int i = 0; i < element.getRelatedResource().size(); i++)
      composeModuleMetadataModuleMetadataRelatedResourceComponent(t, "ModuleMetadata", "relatedResource", element.getRelatedResource().get(i), i);
  }

  protected void composeModuleMetadataModuleMetadataCoverageComponent(Complex parent, String parentType, String name, ModuleMetadata.ModuleMetadataCoverageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "coverage", name, element, index);
    composeCoding(t, "ModuleMetadata", "focus", element.getFocus(), -1);
    composeCodeableConcept(t, "ModuleMetadata", "value", element.getValue(), -1);
  }

  protected void composeModuleMetadataModuleMetadataContributorComponent(Complex parent, String parentType, String name, ModuleMetadata.ModuleMetadataContributorComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "contributor", name, element, index);
    composeEnum(t, "ModuleMetadata", "type", element.getTypeElement(), -1);
    composeString(t, "ModuleMetadata", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeModuleMetadataModuleMetadataContributorContactComponent(t, "ModuleMetadata", "contact", element.getContact().get(i), i);
  }

  protected void composeModuleMetadataModuleMetadataContributorContactComponent(Complex parent, String parentType, String name, ModuleMetadata.ModuleMetadataContributorContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "contact", name, element, index);
    composeString(t, "ModuleMetadata", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "ModuleMetadata", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeModuleMetadataModuleMetadataContactComponent(Complex parent, String parentType, String name, ModuleMetadata.ModuleMetadataContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "contact", name, element, index);
    composeString(t, "ModuleMetadata", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "ModuleMetadata", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeModuleMetadataModuleMetadataRelatedResourceComponent(Complex parent, String parentType, String name, ModuleMetadata.ModuleMetadataRelatedResourceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "relatedResource", name, element, index);
    composeEnum(t, "ModuleMetadata", "type", element.getTypeElement(), -1);
    composeAttachment(t, "ModuleMetadata", "document", element.getDocument(), -1);
    composeReference(t, "ModuleMetadata", "resource", element.getResource(), -1);
  }

  protected void composeAddress(Complex parent, String parentType, String name, Address element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Address", name, element, index);
    composeEnum(t, "Address", "use", element.getUseElement(), -1);
    composeEnum(t, "Address", "type", element.getTypeElement(), -1);
    composeString(t, "Address", "text", element.getTextElement(), -1);
    for (int i = 0; i < element.getLine().size(); i++)
      composeString(t, "Address", "line", element.getLine().get(i), i);
    composeString(t, "Address", "city", element.getCityElement(), -1);
    composeString(t, "Address", "district", element.getDistrictElement(), -1);
    composeString(t, "Address", "state", element.getStateElement(), -1);
    composeString(t, "Address", "postalCode", element.getPostalCodeElement(), -1);
    composeString(t, "Address", "country", element.getCountryElement(), -1);
    composePeriod(t, "Address", "period", element.getPeriod(), -1);
  }

  protected void composeHumanName(Complex parent, String parentType, String name, HumanName element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "HumanName", name, element, index);
    composeEnum(t, "HumanName", "use", element.getUseElement(), -1);
    composeString(t, "HumanName", "text", element.getTextElement(), -1);
    for (int i = 0; i < element.getFamily().size(); i++)
      composeString(t, "HumanName", "family", element.getFamily().get(i), i);
    for (int i = 0; i < element.getGiven().size(); i++)
      composeString(t, "HumanName", "given", element.getGiven().get(i), i);
    for (int i = 0; i < element.getPrefix().size(); i++)
      composeString(t, "HumanName", "prefix", element.getPrefix().get(i), i);
    for (int i = 0; i < element.getSuffix().size(); i++)
      composeString(t, "HumanName", "suffix", element.getSuffix().get(i), i);
    composePeriod(t, "HumanName", "period", element.getPeriod(), -1);
  }

  protected void composeMeta(Complex parent, String parentType, String name, Meta element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "Meta", name, element, index);
    composeId(t, "Meta", "versionId", element.getVersionIdElement(), -1);
    composeInstant(t, "Meta", "lastUpdated", element.getLastUpdatedElement(), -1);
    for (int i = 0; i < element.getProfile().size(); i++)
      composeUri(t, "Meta", "profile", element.getProfile().get(i), i);
    for (int i = 0; i < element.getSecurity().size(); i++)
      composeCoding(t, "Meta", "security", element.getSecurity().get(i), i);
    for (int i = 0; i < element.getTag().size(); i++)
      composeCoding(t, "Meta", "tag", element.getTag().get(i), i);
  }

  protected void composeContactPoint(Complex parent, String parentType, String name, ContactPoint element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeElement(t, "ContactPoint", name, element, index);
    composeEnum(t, "ContactPoint", "system", element.getSystemElement(), -1);
    composeString(t, "ContactPoint", "value", element.getValueElement(), -1);
    composeEnum(t, "ContactPoint", "use", element.getUseElement(), -1);
    composePositiveInt(t, "ContactPoint", "rank", element.getRankElement(), -1);
    composePeriod(t, "ContactPoint", "period", element.getPeriod(), -1);
  }

  protected void composeParameters(Complex parent, String parentType, String name, Parameters element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeResource(t, "Parameters", name, element, index);
    for (int i = 0; i < element.getParameter().size(); i++)
      composeParametersParametersParameterComponent(t, "Parameters", "parameter", element.getParameter().get(i), i);
  }

  protected void composeParametersParametersParameterComponent(Complex parent, String parentType, String name, Parameters.ParametersParameterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "parameter", name, element, index);
    composeString(t, "Parameters", "name", element.getNameElement(), -1);
    composeType(t, "Parameters", "value", element.getValue(), -1);
    composeResource(t, "Parameters", "resource", element.getResource(), -1);
    for (int i = 0; i < element.getPart().size(); i++)
      composeParametersParametersParameterComponent(t, "Parameters", "part", element.getPart().get(i), i);
  }

  protected void composeResource(Complex t, String parentType, String name, Resource element, int index) {
    composeId(t, "Resource", "id", element.getIdElement(), -1);
    composeMeta(t, "Resource", "meta", element.getMeta(), -1);
    composeUri(t, "Resource", "implicitRules", element.getImplicitRulesElement(), -1);
    composeCode(t, "Resource", "language", element.getLanguageElement(), -1);
  }

  protected void composeDomainResource(Complex t, String parentType, String name, DomainResource element, int index) {
    composeResource(t, parentType, name, element, index);
    composeNarrative(t, "DomainResource", "text", element.getText(), -1);
    for (int i = 0; i < element.getContained().size(); i++)
      composeResource(t, "DomainResource", "contained", element.getContained().get(i), i);
    for (int i = 0; i < element.getExtension().size(); i++)
      composeExtension(t, "DomainResource", "extension", element.getExtension().get(i), i);
    for (int i = 0; i < element.getModifierExtension().size(); i++)
      composeExtension(t, "DomainResource", "modifierExtension", element.getModifierExtension().get(i), i);
  }

  protected void composeAccount(Complex parent, String parentType, String name, Account element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Account", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Account", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Account", "name", element.getNameElement(), -1);
    composeCodeableConcept(t, "Account", "type", element.getType(), -1);
    composeEnum(t, "Account", "status", element.getStatusElement(), -1);
    composePeriod(t, "Account", "activePeriod", element.getActivePeriod(), -1);
    composeCoding(t, "Account", "currency", element.getCurrency(), -1);
    composeQuantity(t, "Account", "balance", element.getBalance(), -1);
    composePeriod(t, "Account", "coveragePeriod", element.getCoveragePeriod(), -1);
    composeReference(t, "Account", "subject", element.getSubject(), -1);
    composeReference(t, "Account", "owner", element.getOwner(), -1);
    composeString(t, "Account", "description", element.getDescriptionElement(), -1);
  }

  protected void composeAllergyIntolerance(Complex parent, String parentType, String name, AllergyIntolerance element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "AllergyIntolerance", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "AllergyIntolerance", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "AllergyIntolerance", "status", element.getStatusElement(), -1);
    composeEnum(t, "AllergyIntolerance", "type", element.getTypeElement(), -1);
    composeEnum(t, "AllergyIntolerance", "category", element.getCategoryElement(), -1);
    composeEnum(t, "AllergyIntolerance", "criticality", element.getCriticalityElement(), -1);
    composeCodeableConcept(t, "AllergyIntolerance", "substance", element.getSubstance(), -1);
    composeReference(t, "AllergyIntolerance", "patient", element.getPatient(), -1);
    composeDateTime(t, "AllergyIntolerance", "recordedDate", element.getRecordedDateElement(), -1);
    composeReference(t, "AllergyIntolerance", "recorder", element.getRecorder(), -1);
    composeReference(t, "AllergyIntolerance", "reporter", element.getReporter(), -1);
    composeDateTime(t, "AllergyIntolerance", "onset", element.getOnsetElement(), -1);
    composeDateTime(t, "AllergyIntolerance", "lastOccurence", element.getLastOccurenceElement(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "AllergyIntolerance", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getReaction().size(); i++)
      composeAllergyIntoleranceAllergyIntoleranceReactionComponent(t, "AllergyIntolerance", "reaction", element.getReaction().get(i), i);
  }

  protected void composeAllergyIntoleranceAllergyIntoleranceReactionComponent(Complex parent, String parentType, String name, AllergyIntolerance.AllergyIntoleranceReactionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "reaction", name, element, index);
    composeCodeableConcept(t, "AllergyIntolerance", "substance", element.getSubstance(), -1);
    composeEnum(t, "AllergyIntolerance", "certainty", element.getCertaintyElement(), -1);
    for (int i = 0; i < element.getManifestation().size(); i++)
      composeCodeableConcept(t, "AllergyIntolerance", "manifestation", element.getManifestation().get(i), i);
    composeString(t, "AllergyIntolerance", "description", element.getDescriptionElement(), -1);
    composeDateTime(t, "AllergyIntolerance", "onset", element.getOnsetElement(), -1);
    composeEnum(t, "AllergyIntolerance", "severity", element.getSeverityElement(), -1);
    composeCodeableConcept(t, "AllergyIntolerance", "exposureRoute", element.getExposureRoute(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "AllergyIntolerance", "note", element.getNote().get(i), i);
  }

  protected void composeAppointment(Complex parent, String parentType, String name, Appointment element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Appointment", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Appointment", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "Appointment", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "Appointment", "type", element.getType(), -1);
    composeCodeableConcept(t, "Appointment", "reason", element.getReason(), -1);
    composeUnsignedInt(t, "Appointment", "priority", element.getPriorityElement(), -1);
    composeString(t, "Appointment", "description", element.getDescriptionElement(), -1);
    composeInstant(t, "Appointment", "start", element.getStartElement(), -1);
    composeInstant(t, "Appointment", "end", element.getEndElement(), -1);
    composePositiveInt(t, "Appointment", "minutesDuration", element.getMinutesDurationElement(), -1);
    for (int i = 0; i < element.getSlot().size(); i++)
      composeReference(t, "Appointment", "slot", element.getSlot().get(i), i);
    composeString(t, "Appointment", "comment", element.getCommentElement(), -1);
    for (int i = 0; i < element.getParticipant().size(); i++)
      composeAppointmentAppointmentParticipantComponent(t, "Appointment", "participant", element.getParticipant().get(i), i);
  }

  protected void composeAppointmentAppointmentParticipantComponent(Complex parent, String parentType, String name, Appointment.AppointmentParticipantComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "participant", name, element, index);
    for (int i = 0; i < element.getType().size(); i++)
      composeCodeableConcept(t, "Appointment", "type", element.getType().get(i), i);
    composeReference(t, "Appointment", "actor", element.getActor(), -1);
    composeEnum(t, "Appointment", "required", element.getRequiredElement(), -1);
    composeEnum(t, "Appointment", "status", element.getStatusElement(), -1);
  }

  protected void composeAppointmentResponse(Complex parent, String parentType, String name, AppointmentResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "AppointmentResponse", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "AppointmentResponse", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "AppointmentResponse", "appointment", element.getAppointment(), -1);
    composeInstant(t, "AppointmentResponse", "start", element.getStartElement(), -1);
    composeInstant(t, "AppointmentResponse", "end", element.getEndElement(), -1);
    for (int i = 0; i < element.getParticipantType().size(); i++)
      composeCodeableConcept(t, "AppointmentResponse", "participantType", element.getParticipantType().get(i), i);
    composeReference(t, "AppointmentResponse", "actor", element.getActor(), -1);
    composeEnum(t, "AppointmentResponse", "participantStatus", element.getParticipantStatusElement(), -1);
    composeString(t, "AppointmentResponse", "comment", element.getCommentElement(), -1);
  }

  protected void composeAuditEvent(Complex parent, String parentType, String name, AuditEvent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "AuditEvent", name, element, index);
    composeCoding(t, "AuditEvent", "type", element.getType(), -1);
    for (int i = 0; i < element.getSubtype().size(); i++)
      composeCoding(t, "AuditEvent", "subtype", element.getSubtype().get(i), i);
    composeEnum(t, "AuditEvent", "action", element.getActionElement(), -1);
    composeInstant(t, "AuditEvent", "recorded", element.getRecordedElement(), -1);
    composeEnum(t, "AuditEvent", "outcome", element.getOutcomeElement(), -1);
    composeString(t, "AuditEvent", "outcomeDesc", element.getOutcomeDescElement(), -1);
    for (int i = 0; i < element.getPurposeOfEvent().size(); i++)
      composeCoding(t, "AuditEvent", "purposeOfEvent", element.getPurposeOfEvent().get(i), i);
    for (int i = 0; i < element.getAgent().size(); i++)
      composeAuditEventAuditEventAgentComponent(t, "AuditEvent", "agent", element.getAgent().get(i), i);
    composeAuditEventAuditEventSourceComponent(t, "AuditEvent", "source", element.getSource(), -1);
    for (int i = 0; i < element.getEntity().size(); i++)
      composeAuditEventAuditEventEntityComponent(t, "AuditEvent", "entity", element.getEntity().get(i), i);
  }

  protected void composeAuditEventAuditEventAgentComponent(Complex parent, String parentType, String name, AuditEvent.AuditEventAgentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "agent", name, element, index);
    for (int i = 0; i < element.getRole().size(); i++)
      composeCodeableConcept(t, "AuditEvent", "role", element.getRole().get(i), i);
    composeReference(t, "AuditEvent", "reference", element.getReference(), -1);
    composeIdentifier(t, "AuditEvent", "userId", element.getUserId(), -1);
    composeString(t, "AuditEvent", "altId", element.getAltIdElement(), -1);
    composeString(t, "AuditEvent", "name", element.getNameElement(), -1);
    composeBoolean(t, "AuditEvent", "requestor", element.getRequestorElement(), -1);
    composeReference(t, "AuditEvent", "location", element.getLocation(), -1);
    for (int i = 0; i < element.getPolicy().size(); i++)
      composeUri(t, "AuditEvent", "policy", element.getPolicy().get(i), i);
    composeCoding(t, "AuditEvent", "media", element.getMedia(), -1);
    composeAuditEventAuditEventAgentNetworkComponent(t, "AuditEvent", "network", element.getNetwork(), -1);
    for (int i = 0; i < element.getPurposeOfUse().size(); i++)
      composeCoding(t, "AuditEvent", "purposeOfUse", element.getPurposeOfUse().get(i), i);
  }

  protected void composeAuditEventAuditEventAgentNetworkComponent(Complex parent, String parentType, String name, AuditEvent.AuditEventAgentNetworkComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "network", name, element, index);
    composeString(t, "AuditEvent", "address", element.getAddressElement(), -1);
    composeEnum(t, "AuditEvent", "type", element.getTypeElement(), -1);
  }

  protected void composeAuditEventAuditEventSourceComponent(Complex parent, String parentType, String name, AuditEvent.AuditEventSourceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "source", name, element, index);
    composeString(t, "AuditEvent", "site", element.getSiteElement(), -1);
    composeIdentifier(t, "AuditEvent", "identifier", element.getIdentifier(), -1);
    for (int i = 0; i < element.getType().size(); i++)
      composeCoding(t, "AuditEvent", "type", element.getType().get(i), i);
  }

  protected void composeAuditEventAuditEventEntityComponent(Complex parent, String parentType, String name, AuditEvent.AuditEventEntityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "entity", name, element, index);
    composeIdentifier(t, "AuditEvent", "identifier", element.getIdentifier(), -1);
    composeReference(t, "AuditEvent", "reference", element.getReference(), -1);
    composeCoding(t, "AuditEvent", "type", element.getType(), -1);
    composeCoding(t, "AuditEvent", "role", element.getRole(), -1);
    composeCoding(t, "AuditEvent", "lifecycle", element.getLifecycle(), -1);
    for (int i = 0; i < element.getSecurityLabel().size(); i++)
      composeCoding(t, "AuditEvent", "securityLabel", element.getSecurityLabel().get(i), i);
    composeString(t, "AuditEvent", "name", element.getNameElement(), -1);
    composeString(t, "AuditEvent", "description", element.getDescriptionElement(), -1);
    composeBase64Binary(t, "AuditEvent", "query", element.getQueryElement(), -1);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeAuditEventAuditEventEntityDetailComponent(t, "AuditEvent", "detail", element.getDetail().get(i), i);
  }

  protected void composeAuditEventAuditEventEntityDetailComponent(Complex parent, String parentType, String name, AuditEvent.AuditEventEntityDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composeString(t, "AuditEvent", "type", element.getTypeElement(), -1);
    composeBase64Binary(t, "AuditEvent", "value", element.getValueElement(), -1);
  }

  protected void composeBasic(Complex parent, String parentType, String name, Basic element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Basic", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Basic", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "Basic", "code", element.getCode(), -1);
    composeReference(t, "Basic", "subject", element.getSubject(), -1);
    composeDate(t, "Basic", "created", element.getCreatedElement(), -1);
    composeReference(t, "Basic", "author", element.getAuthor(), -1);
  }

  protected void composeBinary(Complex parent, String parentType, String name, Binary element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeResource(t, "Binary", name, element, index);
    composeCode(t, "Binary", "contentType", element.getContentTypeElement(), -1);
    composeBase64Binary(t, "Binary", "content", element.getContentElement(), -1);
  }

  protected void composeBodySite(Complex parent, String parentType, String name, BodySite element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "BodySite", name, element, index);
    composeReference(t, "BodySite", "patient", element.getPatient(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "BodySite", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "BodySite", "code", element.getCode(), -1);
    for (int i = 0; i < element.getModifier().size(); i++)
      composeCodeableConcept(t, "BodySite", "modifier", element.getModifier().get(i), i);
    composeString(t, "BodySite", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getImage().size(); i++)
      composeAttachment(t, "BodySite", "image", element.getImage().get(i), i);
  }

  protected void composeBundle(Complex parent, String parentType, String name, Bundle element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeResource(t, "Bundle", name, element, index);
    composeEnum(t, "Bundle", "type", element.getTypeElement(), -1);
    composeUnsignedInt(t, "Bundle", "total", element.getTotalElement(), -1);
    for (int i = 0; i < element.getLink().size(); i++)
      composeBundleBundleLinkComponent(t, "Bundle", "link", element.getLink().get(i), i);
    for (int i = 0; i < element.getEntry().size(); i++)
      composeBundleBundleEntryComponent(t, "Bundle", "entry", element.getEntry().get(i), i);
    composeSignature(t, "Bundle", "signature", element.getSignature(), -1);
  }

  protected void composeBundleBundleLinkComponent(Complex parent, String parentType, String name, Bundle.BundleLinkComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "link", name, element, index);
    composeString(t, "Bundle", "relation", element.getRelationElement(), -1);
    composeUri(t, "Bundle", "url", element.getUrlElement(), -1);
  }

  protected void composeBundleBundleEntryComponent(Complex parent, String parentType, String name, Bundle.BundleEntryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "entry", name, element, index);
    for (int i = 0; i < element.getLink().size(); i++)
      composeBundleBundleLinkComponent(t, "Bundle", "link", element.getLink().get(i), i);
    composeUri(t, "Bundle", "fullUrl", element.getFullUrlElement(), -1);
    composeResource(t, "Bundle", "resource", element.getResource(), -1);
    composeBundleBundleEntrySearchComponent(t, "Bundle", "search", element.getSearch(), -1);
    composeBundleBundleEntryRequestComponent(t, "Bundle", "request", element.getRequest(), -1);
    composeBundleBundleEntryResponseComponent(t, "Bundle", "response", element.getResponse(), -1);
  }

  protected void composeBundleBundleEntrySearchComponent(Complex parent, String parentType, String name, Bundle.BundleEntrySearchComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "search", name, element, index);
    composeEnum(t, "Bundle", "mode", element.getModeElement(), -1);
    composeDecimal(t, "Bundle", "score", element.getScoreElement(), -1);
  }

  protected void composeBundleBundleEntryRequestComponent(Complex parent, String parentType, String name, Bundle.BundleEntryRequestComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "request", name, element, index);
    composeEnum(t, "Bundle", "method", element.getMethodElement(), -1);
    composeUri(t, "Bundle", "url", element.getUrlElement(), -1);
    composeString(t, "Bundle", "ifNoneMatch", element.getIfNoneMatchElement(), -1);
    composeInstant(t, "Bundle", "ifModifiedSince", element.getIfModifiedSinceElement(), -1);
    composeString(t, "Bundle", "ifMatch", element.getIfMatchElement(), -1);
    composeString(t, "Bundle", "ifNoneExist", element.getIfNoneExistElement(), -1);
  }

  protected void composeBundleBundleEntryResponseComponent(Complex parent, String parentType, String name, Bundle.BundleEntryResponseComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "response", name, element, index);
    composeString(t, "Bundle", "status", element.getStatusElement(), -1);
    composeUri(t, "Bundle", "location", element.getLocationElement(), -1);
    composeString(t, "Bundle", "etag", element.getEtagElement(), -1);
    composeInstant(t, "Bundle", "lastModified", element.getLastModifiedElement(), -1);
  }

  protected void composeCarePlan(Complex parent, String parentType, String name, CarePlan element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "CarePlan", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "CarePlan", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "CarePlan", "subject", element.getSubject(), -1);
    composeEnum(t, "CarePlan", "status", element.getStatusElement(), -1);
    composeReference(t, "CarePlan", "context", element.getContext(), -1);
    composePeriod(t, "CarePlan", "period", element.getPeriod(), -1);
    for (int i = 0; i < element.getAuthor().size(); i++)
      composeReference(t, "CarePlan", "author", element.getAuthor().get(i), i);
    composeDateTime(t, "CarePlan", "modified", element.getModifiedElement(), -1);
    for (int i = 0; i < element.getCategory().size(); i++)
      composeCodeableConcept(t, "CarePlan", "category", element.getCategory().get(i), i);
    composeString(t, "CarePlan", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getAddresses().size(); i++)
      composeReference(t, "CarePlan", "addresses", element.getAddresses().get(i), i);
    for (int i = 0; i < element.getSupport().size(); i++)
      composeReference(t, "CarePlan", "support", element.getSupport().get(i), i);
    for (int i = 0; i < element.getRelatedPlan().size(); i++)
      composeCarePlanCarePlanRelatedPlanComponent(t, "CarePlan", "relatedPlan", element.getRelatedPlan().get(i), i);
    for (int i = 0; i < element.getParticipant().size(); i++)
      composeCarePlanCarePlanParticipantComponent(t, "CarePlan", "participant", element.getParticipant().get(i), i);
    for (int i = 0; i < element.getGoal().size(); i++)
      composeReference(t, "CarePlan", "goal", element.getGoal().get(i), i);
    for (int i = 0; i < element.getActivity().size(); i++)
      composeCarePlanCarePlanActivityComponent(t, "CarePlan", "activity", element.getActivity().get(i), i);
    composeAnnotation(t, "CarePlan", "note", element.getNote(), -1);
  }

  protected void composeCarePlanCarePlanRelatedPlanComponent(Complex parent, String parentType, String name, CarePlan.CarePlanRelatedPlanComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "relatedPlan", name, element, index);
    composeEnum(t, "CarePlan", "code", element.getCodeElement(), -1);
    composeReference(t, "CarePlan", "plan", element.getPlan(), -1);
  }

  protected void composeCarePlanCarePlanParticipantComponent(Complex parent, String parentType, String name, CarePlan.CarePlanParticipantComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "participant", name, element, index);
    composeCodeableConcept(t, "CarePlan", "role", element.getRole(), -1);
    composeReference(t, "CarePlan", "member", element.getMember(), -1);
  }

  protected void composeCarePlanCarePlanActivityComponent(Complex parent, String parentType, String name, CarePlan.CarePlanActivityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "activity", name, element, index);
    for (int i = 0; i < element.getActionResulting().size(); i++)
      composeReference(t, "CarePlan", "actionResulting", element.getActionResulting().get(i), i);
    for (int i = 0; i < element.getProgress().size(); i++)
      composeAnnotation(t, "CarePlan", "progress", element.getProgress().get(i), i);
    composeReference(t, "CarePlan", "reference", element.getReference(), -1);
    composeCarePlanCarePlanActivityDetailComponent(t, "CarePlan", "detail", element.getDetail(), -1);
  }

  protected void composeCarePlanCarePlanActivityDetailComponent(Complex parent, String parentType, String name, CarePlan.CarePlanActivityDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composeCodeableConcept(t, "CarePlan", "category", element.getCategory(), -1);
    composeCodeableConcept(t, "CarePlan", "code", element.getCode(), -1);
    for (int i = 0; i < element.getReasonCode().size(); i++)
      composeCodeableConcept(t, "CarePlan", "reasonCode", element.getReasonCode().get(i), i);
    for (int i = 0; i < element.getReasonReference().size(); i++)
      composeReference(t, "CarePlan", "reasonReference", element.getReasonReference().get(i), i);
    for (int i = 0; i < element.getGoal().size(); i++)
      composeReference(t, "CarePlan", "goal", element.getGoal().get(i), i);
    composeEnum(t, "CarePlan", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "CarePlan", "statusReason", element.getStatusReason(), -1);
    composeBoolean(t, "CarePlan", "prohibited", element.getProhibitedElement(), -1);
    composeType(t, "CarePlan", "scheduled", element.getScheduled(), -1);
    composeReference(t, "CarePlan", "location", element.getLocation(), -1);
    for (int i = 0; i < element.getPerformer().size(); i++)
      composeReference(t, "CarePlan", "performer", element.getPerformer().get(i), i);
    composeType(t, "CarePlan", "product", element.getProduct(), -1);
    composeQuantity(t, "CarePlan", "dailyAmount", element.getDailyAmount(), -1);
    composeQuantity(t, "CarePlan", "quantity", element.getQuantity(), -1);
    composeString(t, "CarePlan", "description", element.getDescriptionElement(), -1);
  }

  protected void composeClaim(Complex parent, String parentType, String name, Claim element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Claim", name, element, index);
    composeEnum(t, "Claim", "type", element.getTypeElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Claim", "identifier", element.getIdentifier().get(i), i);
    composeCoding(t, "Claim", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "Claim", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "Claim", "created", element.getCreatedElement(), -1);
    composePeriod(t, "Claim", "billablePeriod", element.getBillablePeriod(), -1);
    composeReference(t, "Claim", "target", element.getTarget(), -1);
    composeReference(t, "Claim", "provider", element.getProvider(), -1);
    composeReference(t, "Claim", "organization", element.getOrganization(), -1);
    composeEnum(t, "Claim", "use", element.getUseElement(), -1);
    composeCoding(t, "Claim", "priority", element.getPriority(), -1);
    composeCoding(t, "Claim", "fundsReserve", element.getFundsReserve(), -1);
    composeReference(t, "Claim", "enterer", element.getEnterer(), -1);
    composeReference(t, "Claim", "facility", element.getFacility(), -1);
    for (int i = 0; i < element.getRelatedClaim().size(); i++)
      composeReference(t, "Claim", "relatedClaim", element.getRelatedClaim().get(i), i);
    composeReference(t, "Claim", "prescription", element.getPrescription(), -1);
    composeReference(t, "Claim", "originalPrescription", element.getOriginalPrescription(), -1);
    composeClaimPayeeComponent(t, "Claim", "payee", element.getPayee(), -1);
    composeReference(t, "Claim", "referral", element.getReferral(), -1);
    for (int i = 0; i < element.getDiagnosis().size(); i++)
      composeClaimDiagnosisComponent(t, "Claim", "diagnosis", element.getDiagnosis().get(i), i);
    for (int i = 0; i < element.getSpecialCondition().size(); i++)
      composeCoding(t, "Claim", "specialCondition", element.getSpecialCondition().get(i), i);
    composeReference(t, "Claim", "patient", element.getPatient(), -1);
    for (int i = 0; i < element.getCoverage().size(); i++)
      composeClaimCoverageComponent(t, "Claim", "coverage", element.getCoverage().get(i), i);
    for (int i = 0; i < element.getException().size(); i++)
      composeCoding(t, "Claim", "exception", element.getException().get(i), i);
    composeString(t, "Claim", "school", element.getSchoolElement(), -1);
    composeDate(t, "Claim", "accidentDate", element.getAccidentDateElement(), -1);
    composeCoding(t, "Claim", "accidentType", element.getAccidentType(), -1);
    composeType(t, "Claim", "accidentLocation", element.getAccidentLocation(), -1);
    for (int i = 0; i < element.getInterventionException().size(); i++)
      composeCoding(t, "Claim", "interventionException", element.getInterventionException().get(i), i);
    composeType(t, "Claim", "onset", element.getOnset(), -1);
    composePeriod(t, "Claim", "employmentImpacted", element.getEmploymentImpacted(), -1);
    composePeriod(t, "Claim", "hospitalization", element.getHospitalization(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeClaimItemsComponent(t, "Claim", "item", element.getItem().get(i), i);
    composeQuantity(t, "Claim", "total", element.getTotal(), -1);
    for (int i = 0; i < element.getAdditionalMaterials().size(); i++)
      composeCoding(t, "Claim", "additionalMaterials", element.getAdditionalMaterials().get(i), i);
    for (int i = 0; i < element.getMissingTeeth().size(); i++)
      composeClaimMissingTeethComponent(t, "Claim", "missingTeeth", element.getMissingTeeth().get(i), i);
  }

  protected void composeClaimPayeeComponent(Complex parent, String parentType, String name, Claim.PayeeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "payee", name, element, index);
    composeCoding(t, "Claim", "type", element.getType(), -1);
    composeReference(t, "Claim", "provider", element.getProvider(), -1);
    composeReference(t, "Claim", "organization", element.getOrganization(), -1);
    composeReference(t, "Claim", "person", element.getPerson(), -1);
  }

  protected void composeClaimDiagnosisComponent(Complex parent, String parentType, String name, Claim.DiagnosisComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "diagnosis", name, element, index);
    composePositiveInt(t, "Claim", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "Claim", "diagnosis", element.getDiagnosis(), -1);
  }

  protected void composeClaimCoverageComponent(Complex parent, String parentType, String name, Claim.CoverageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "coverage", name, element, index);
    composePositiveInt(t, "Claim", "sequence", element.getSequenceElement(), -1);
    composeBoolean(t, "Claim", "focal", element.getFocalElement(), -1);
    composeReference(t, "Claim", "coverage", element.getCoverage(), -1);
    composeString(t, "Claim", "businessArrangement", element.getBusinessArrangementElement(), -1);
    composeCoding(t, "Claim", "relationship", element.getRelationship(), -1);
    for (int i = 0; i < element.getPreAuthRef().size(); i++)
      composeString(t, "Claim", "preAuthRef", element.getPreAuthRef().get(i), i);
    composeReference(t, "Claim", "claimResponse", element.getClaimResponse(), -1);
    composeCoding(t, "Claim", "originalRuleset", element.getOriginalRuleset(), -1);
  }

  protected void composeClaimItemsComponent(Complex parent, String parentType, String name, Claim.ItemsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composePositiveInt(t, "Claim", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "Claim", "type", element.getType(), -1);
    composeReference(t, "Claim", "provider", element.getProvider(), -1);
    for (int i = 0; i < element.getDiagnosisLinkId().size(); i++)
      composePositiveInt(t, "Claim", "diagnosisLinkId", element.getDiagnosisLinkId().get(i), i);
    composeCoding(t, "Claim", "service", element.getService(), -1);
    composeType(t, "Claim", "serviced", element.getServiced(), -1);
    composeCoding(t, "Claim", "place", element.getPlace(), -1);
    composeQuantity(t, "Claim", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "Claim", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "Claim", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "Claim", "points", element.getPointsElement(), -1);
    composeQuantity(t, "Claim", "net", element.getNet(), -1);
    composeCoding(t, "Claim", "udi", element.getUdi(), -1);
    composeCoding(t, "Claim", "bodySite", element.getBodySite(), -1);
    for (int i = 0; i < element.getSubSite().size(); i++)
      composeCoding(t, "Claim", "subSite", element.getSubSite().get(i), i);
    for (int i = 0; i < element.getModifier().size(); i++)
      composeCoding(t, "Claim", "modifier", element.getModifier().get(i), i);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeClaimDetailComponent(t, "Claim", "detail", element.getDetail().get(i), i);
    composeClaimProsthesisComponent(t, "Claim", "prosthesis", element.getProsthesis(), -1);
  }

  protected void composeClaimDetailComponent(Complex parent, String parentType, String name, Claim.DetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composePositiveInt(t, "Claim", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "Claim", "type", element.getType(), -1);
    composeCoding(t, "Claim", "service", element.getService(), -1);
    composeQuantity(t, "Claim", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "Claim", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "Claim", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "Claim", "points", element.getPointsElement(), -1);
    composeQuantity(t, "Claim", "net", element.getNet(), -1);
    composeCoding(t, "Claim", "udi", element.getUdi(), -1);
    for (int i = 0; i < element.getSubDetail().size(); i++)
      composeClaimSubDetailComponent(t, "Claim", "subDetail", element.getSubDetail().get(i), i);
  }

  protected void composeClaimSubDetailComponent(Complex parent, String parentType, String name, Claim.SubDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "subDetail", name, element, index);
    composePositiveInt(t, "Claim", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "Claim", "type", element.getType(), -1);
    composeCoding(t, "Claim", "service", element.getService(), -1);
    composeQuantity(t, "Claim", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "Claim", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "Claim", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "Claim", "points", element.getPointsElement(), -1);
    composeQuantity(t, "Claim", "net", element.getNet(), -1);
    composeCoding(t, "Claim", "udi", element.getUdi(), -1);
  }

  protected void composeClaimProsthesisComponent(Complex parent, String parentType, String name, Claim.ProsthesisComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "prosthesis", name, element, index);
    composeBoolean(t, "Claim", "initial", element.getInitialElement(), -1);
    composeDate(t, "Claim", "priorDate", element.getPriorDateElement(), -1);
    composeCoding(t, "Claim", "priorMaterial", element.getPriorMaterial(), -1);
  }

  protected void composeClaimMissingTeethComponent(Complex parent, String parentType, String name, Claim.MissingTeethComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "missingTeeth", name, element, index);
    composeCoding(t, "Claim", "tooth", element.getTooth(), -1);
    composeCoding(t, "Claim", "reason", element.getReason(), -1);
    composeDate(t, "Claim", "extractionDate", element.getExtractionDateElement(), -1);
  }

  protected void composeClaimResponse(Complex parent, String parentType, String name, ClaimResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ClaimResponse", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ClaimResponse", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "ClaimResponse", "request", element.getRequest(), -1);
    composeCoding(t, "ClaimResponse", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "ClaimResponse", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "ClaimResponse", "created", element.getCreatedElement(), -1);
    composeReference(t, "ClaimResponse", "organization", element.getOrganization(), -1);
    composeReference(t, "ClaimResponse", "requestProvider", element.getRequestProvider(), -1);
    composeReference(t, "ClaimResponse", "requestOrganization", element.getRequestOrganization(), -1);
    composeEnum(t, "ClaimResponse", "outcome", element.getOutcomeElement(), -1);
    composeString(t, "ClaimResponse", "disposition", element.getDispositionElement(), -1);
    composeCoding(t, "ClaimResponse", "payeeType", element.getPayeeType(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeClaimResponseItemsComponent(t, "ClaimResponse", "item", element.getItem().get(i), i);
    for (int i = 0; i < element.getAddItem().size(); i++)
      composeClaimResponseAddedItemComponent(t, "ClaimResponse", "addItem", element.getAddItem().get(i), i);
    for (int i = 0; i < element.getError().size(); i++)
      composeClaimResponseErrorsComponent(t, "ClaimResponse", "error", element.getError().get(i), i);
    composeQuantity(t, "ClaimResponse", "totalCost", element.getTotalCost(), -1);
    composeQuantity(t, "ClaimResponse", "unallocDeductable", element.getUnallocDeductable(), -1);
    composeQuantity(t, "ClaimResponse", "totalBenefit", element.getTotalBenefit(), -1);
    composeQuantity(t, "ClaimResponse", "paymentAdjustment", element.getPaymentAdjustment(), -1);
    composeCoding(t, "ClaimResponse", "paymentAdjustmentReason", element.getPaymentAdjustmentReason(), -1);
    composeDate(t, "ClaimResponse", "paymentDate", element.getPaymentDateElement(), -1);
    composeQuantity(t, "ClaimResponse", "paymentAmount", element.getPaymentAmount(), -1);
    composeIdentifier(t, "ClaimResponse", "paymentRef", element.getPaymentRef(), -1);
    composeCoding(t, "ClaimResponse", "reserved", element.getReserved(), -1);
    composeCoding(t, "ClaimResponse", "form", element.getForm(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeClaimResponseNotesComponent(t, "ClaimResponse", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getCoverage().size(); i++)
      composeClaimResponseCoverageComponent(t, "ClaimResponse", "coverage", element.getCoverage().get(i), i);
  }

  protected void composeClaimResponseItemsComponent(Complex parent, String parentType, String name, ClaimResponse.ItemsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composePositiveInt(t, "ClaimResponse", "sequenceLinkId", element.getSequenceLinkIdElement(), -1);
    for (int i = 0; i < element.getNoteNumber().size(); i++)
      composePositiveInt(t, "ClaimResponse", "noteNumber", element.getNoteNumber().get(i), i);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeClaimResponseItemAdjudicationComponent(t, "ClaimResponse", "adjudication", element.getAdjudication().get(i), i);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeClaimResponseItemDetailComponent(t, "ClaimResponse", "detail", element.getDetail().get(i), i);
  }

  protected void composeClaimResponseItemAdjudicationComponent(Complex parent, String parentType, String name, ClaimResponse.ItemAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ClaimResponse", "code", element.getCode(), -1);
    composeQuantity(t, "ClaimResponse", "amount", element.getAmount(), -1);
    composeDecimal(t, "ClaimResponse", "value", element.getValueElement(), -1);
  }

  protected void composeClaimResponseItemDetailComponent(Complex parent, String parentType, String name, ClaimResponse.ItemDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composePositiveInt(t, "ClaimResponse", "sequenceLinkId", element.getSequenceLinkIdElement(), -1);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeClaimResponseDetailAdjudicationComponent(t, "ClaimResponse", "adjudication", element.getAdjudication().get(i), i);
    for (int i = 0; i < element.getSubDetail().size(); i++)
      composeClaimResponseSubDetailComponent(t, "ClaimResponse", "subDetail", element.getSubDetail().get(i), i);
  }

  protected void composeClaimResponseDetailAdjudicationComponent(Complex parent, String parentType, String name, ClaimResponse.DetailAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ClaimResponse", "code", element.getCode(), -1);
    composeQuantity(t, "ClaimResponse", "amount", element.getAmount(), -1);
    composeDecimal(t, "ClaimResponse", "value", element.getValueElement(), -1);
  }

  protected void composeClaimResponseSubDetailComponent(Complex parent, String parentType, String name, ClaimResponse.SubDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "subDetail", name, element, index);
    composePositiveInt(t, "ClaimResponse", "sequenceLinkId", element.getSequenceLinkIdElement(), -1);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeClaimResponseSubdetailAdjudicationComponent(t, "ClaimResponse", "adjudication", element.getAdjudication().get(i), i);
  }

  protected void composeClaimResponseSubdetailAdjudicationComponent(Complex parent, String parentType, String name, ClaimResponse.SubdetailAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ClaimResponse", "code", element.getCode(), -1);
    composeQuantity(t, "ClaimResponse", "amount", element.getAmount(), -1);
    composeDecimal(t, "ClaimResponse", "value", element.getValueElement(), -1);
  }

  protected void composeClaimResponseAddedItemComponent(Complex parent, String parentType, String name, ClaimResponse.AddedItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "addItem", name, element, index);
    for (int i = 0; i < element.getSequenceLinkId().size(); i++)
      composePositiveInt(t, "ClaimResponse", "sequenceLinkId", element.getSequenceLinkId().get(i), i);
    composeCoding(t, "ClaimResponse", "service", element.getService(), -1);
    composeQuantity(t, "ClaimResponse", "fee", element.getFee(), -1);
    for (int i = 0; i < element.getNoteNumberLinkId().size(); i++)
      composePositiveInt(t, "ClaimResponse", "noteNumberLinkId", element.getNoteNumberLinkId().get(i), i);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeClaimResponseAddedItemAdjudicationComponent(t, "ClaimResponse", "adjudication", element.getAdjudication().get(i), i);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeClaimResponseAddedItemsDetailComponent(t, "ClaimResponse", "detail", element.getDetail().get(i), i);
  }

  protected void composeClaimResponseAddedItemAdjudicationComponent(Complex parent, String parentType, String name, ClaimResponse.AddedItemAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ClaimResponse", "code", element.getCode(), -1);
    composeQuantity(t, "ClaimResponse", "amount", element.getAmount(), -1);
    composeDecimal(t, "ClaimResponse", "value", element.getValueElement(), -1);
  }

  protected void composeClaimResponseAddedItemsDetailComponent(Complex parent, String parentType, String name, ClaimResponse.AddedItemsDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composeCoding(t, "ClaimResponse", "service", element.getService(), -1);
    composeQuantity(t, "ClaimResponse", "fee", element.getFee(), -1);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeClaimResponseAddedItemDetailAdjudicationComponent(t, "ClaimResponse", "adjudication", element.getAdjudication().get(i), i);
  }

  protected void composeClaimResponseAddedItemDetailAdjudicationComponent(Complex parent, String parentType, String name, ClaimResponse.AddedItemDetailAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ClaimResponse", "code", element.getCode(), -1);
    composeQuantity(t, "ClaimResponse", "amount", element.getAmount(), -1);
    composeDecimal(t, "ClaimResponse", "value", element.getValueElement(), -1);
  }

  protected void composeClaimResponseErrorsComponent(Complex parent, String parentType, String name, ClaimResponse.ErrorsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "error", name, element, index);
    composePositiveInt(t, "ClaimResponse", "sequenceLinkId", element.getSequenceLinkIdElement(), -1);
    composePositiveInt(t, "ClaimResponse", "detailSequenceLinkId", element.getDetailSequenceLinkIdElement(), -1);
    composePositiveInt(t, "ClaimResponse", "subdetailSequenceLinkId", element.getSubdetailSequenceLinkIdElement(), -1);
    composeCoding(t, "ClaimResponse", "code", element.getCode(), -1);
  }

  protected void composeClaimResponseNotesComponent(Complex parent, String parentType, String name, ClaimResponse.NotesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "note", name, element, index);
    composePositiveInt(t, "ClaimResponse", "number", element.getNumberElement(), -1);
    composeCoding(t, "ClaimResponse", "type", element.getType(), -1);
    composeString(t, "ClaimResponse", "text", element.getTextElement(), -1);
  }

  protected void composeClaimResponseCoverageComponent(Complex parent, String parentType, String name, ClaimResponse.CoverageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "coverage", name, element, index);
    composePositiveInt(t, "ClaimResponse", "sequence", element.getSequenceElement(), -1);
    composeBoolean(t, "ClaimResponse", "focal", element.getFocalElement(), -1);
    composeReference(t, "ClaimResponse", "coverage", element.getCoverage(), -1);
    composeString(t, "ClaimResponse", "businessArrangement", element.getBusinessArrangementElement(), -1);
    composeCoding(t, "ClaimResponse", "relationship", element.getRelationship(), -1);
    for (int i = 0; i < element.getPreAuthRef().size(); i++)
      composeString(t, "ClaimResponse", "preAuthRef", element.getPreAuthRef().get(i), i);
    composeReference(t, "ClaimResponse", "claimResponse", element.getClaimResponse(), -1);
    composeCoding(t, "ClaimResponse", "originalRuleset", element.getOriginalRuleset(), -1);
  }

  protected void composeClinicalImpression(Complex parent, String parentType, String name, ClinicalImpression element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ClinicalImpression", name, element, index);
    composeReference(t, "ClinicalImpression", "patient", element.getPatient(), -1);
    composeReference(t, "ClinicalImpression", "assessor", element.getAssessor(), -1);
    composeEnum(t, "ClinicalImpression", "status", element.getStatusElement(), -1);
    composeDateTime(t, "ClinicalImpression", "date", element.getDateElement(), -1);
    composeString(t, "ClinicalImpression", "description", element.getDescriptionElement(), -1);
    composeReference(t, "ClinicalImpression", "previous", element.getPrevious(), -1);
    for (int i = 0; i < element.getProblem().size(); i++)
      composeReference(t, "ClinicalImpression", "problem", element.getProblem().get(i), i);
    composeType(t, "ClinicalImpression", "trigger", element.getTrigger(), -1);
    for (int i = 0; i < element.getInvestigations().size(); i++)
      composeClinicalImpressionClinicalImpressionInvestigationsComponent(t, "ClinicalImpression", "investigations", element.getInvestigations().get(i), i);
    composeUri(t, "ClinicalImpression", "protocol", element.getProtocolElement(), -1);
    composeString(t, "ClinicalImpression", "summary", element.getSummaryElement(), -1);
    for (int i = 0; i < element.getFinding().size(); i++)
      composeClinicalImpressionClinicalImpressionFindingComponent(t, "ClinicalImpression", "finding", element.getFinding().get(i), i);
    for (int i = 0; i < element.getResolved().size(); i++)
      composeCodeableConcept(t, "ClinicalImpression", "resolved", element.getResolved().get(i), i);
    for (int i = 0; i < element.getRuledOut().size(); i++)
      composeClinicalImpressionClinicalImpressionRuledOutComponent(t, "ClinicalImpression", "ruledOut", element.getRuledOut().get(i), i);
    composeString(t, "ClinicalImpression", "prognosis", element.getPrognosisElement(), -1);
    for (int i = 0; i < element.getPlan().size(); i++)
      composeReference(t, "ClinicalImpression", "plan", element.getPlan().get(i), i);
    for (int i = 0; i < element.getAction().size(); i++)
      composeReference(t, "ClinicalImpression", "action", element.getAction().get(i), i);
  }

  protected void composeClinicalImpressionClinicalImpressionInvestigationsComponent(Complex parent, String parentType, String name, ClinicalImpression.ClinicalImpressionInvestigationsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "investigations", name, element, index);
    composeCodeableConcept(t, "ClinicalImpression", "code", element.getCode(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeReference(t, "ClinicalImpression", "item", element.getItem().get(i), i);
  }

  protected void composeClinicalImpressionClinicalImpressionFindingComponent(Complex parent, String parentType, String name, ClinicalImpression.ClinicalImpressionFindingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "finding", name, element, index);
    composeCodeableConcept(t, "ClinicalImpression", "item", element.getItem(), -1);
    composeString(t, "ClinicalImpression", "cause", element.getCauseElement(), -1);
  }

  protected void composeClinicalImpressionClinicalImpressionRuledOutComponent(Complex parent, String parentType, String name, ClinicalImpression.ClinicalImpressionRuledOutComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "ruledOut", name, element, index);
    composeCodeableConcept(t, "ClinicalImpression", "item", element.getItem(), -1);
    composeString(t, "ClinicalImpression", "reason", element.getReasonElement(), -1);
  }

  protected void composeCodeSystem(Complex parent, String parentType, String name, CodeSystem element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "CodeSystem", name, element, index);
    composeUri(t, "CodeSystem", "url", element.getUrlElement(), -1);
    composeIdentifier(t, "CodeSystem", "identifier", element.getIdentifier(), -1);
    composeString(t, "CodeSystem", "version", element.getVersionElement(), -1);
    composeString(t, "CodeSystem", "name", element.getNameElement(), -1);
    composeEnum(t, "CodeSystem", "status", element.getStatusElement(), -1);
    composeBoolean(t, "CodeSystem", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "CodeSystem", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeCodeSystemCodeSystemContactComponent(t, "CodeSystem", "contact", element.getContact().get(i), i);
    composeDateTime(t, "CodeSystem", "date", element.getDateElement(), -1);
    composeString(t, "CodeSystem", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "CodeSystem", "useContext", element.getUseContext().get(i), i);
    composeString(t, "CodeSystem", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "CodeSystem", "copyright", element.getCopyrightElement(), -1);
    composeBoolean(t, "CodeSystem", "caseSensitive", element.getCaseSensitiveElement(), -1);
    composeUri(t, "CodeSystem", "valueSet", element.getValueSetElement(), -1);
    composeBoolean(t, "CodeSystem", "compositional", element.getCompositionalElement(), -1);
    composeBoolean(t, "CodeSystem", "versionNeeded", element.getVersionNeededElement(), -1);
    composeEnum(t, "CodeSystem", "content", element.getContentElement(), -1);
    composeUnsignedInt(t, "CodeSystem", "count", element.getCountElement(), -1);
    for (int i = 0; i < element.getFilter().size(); i++)
      composeCodeSystemCodeSystemFilterComponent(t, "CodeSystem", "filter", element.getFilter().get(i), i);
    for (int i = 0; i < element.getProperty().size(); i++)
      composeCodeSystemCodeSystemPropertyComponent(t, "CodeSystem", "property", element.getProperty().get(i), i);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCodeSystemConceptDefinitionComponent(t, "CodeSystem", "concept", element.getConcept().get(i), i);
  }

  protected void composeCodeSystemCodeSystemContactComponent(Complex parent, String parentType, String name, CodeSystem.CodeSystemContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "CodeSystem", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "CodeSystem", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeCodeSystemCodeSystemFilterComponent(Complex parent, String parentType, String name, CodeSystem.CodeSystemFilterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "filter", name, element, index);
    composeCode(t, "CodeSystem", "code", element.getCodeElement(), -1);
    composeString(t, "CodeSystem", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getOperator().size(); i++)
      composeCode(t, "CodeSystem", "operator", element.getOperator().get(i), i);
    composeString(t, "CodeSystem", "value", element.getValueElement(), -1);
  }

  protected void composeCodeSystemCodeSystemPropertyComponent(Complex parent, String parentType, String name, CodeSystem.CodeSystemPropertyComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "property", name, element, index);
    composeCode(t, "CodeSystem", "code", element.getCodeElement(), -1);
    composeString(t, "CodeSystem", "description", element.getDescriptionElement(), -1);
    composeEnum(t, "CodeSystem", "type", element.getTypeElement(), -1);
  }

  protected void composeCodeSystemConceptDefinitionComponent(Complex parent, String parentType, String name, CodeSystem.ConceptDefinitionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "concept", name, element, index);
    composeCode(t, "CodeSystem", "code", element.getCodeElement(), -1);
    composeString(t, "CodeSystem", "display", element.getDisplayElement(), -1);
    composeString(t, "CodeSystem", "definition", element.getDefinitionElement(), -1);
    for (int i = 0; i < element.getDesignation().size(); i++)
      composeCodeSystemConceptDefinitionDesignationComponent(t, "CodeSystem", "designation", element.getDesignation().get(i), i);
    for (int i = 0; i < element.getProperty().size(); i++)
      composeCodeSystemConceptDefinitionPropertyComponent(t, "CodeSystem", "property", element.getProperty().get(i), i);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCodeSystemConceptDefinitionComponent(t, "CodeSystem", "concept", element.getConcept().get(i), i);
  }

  protected void composeCodeSystemConceptDefinitionDesignationComponent(Complex parent, String parentType, String name, CodeSystem.ConceptDefinitionDesignationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "designation", name, element, index);
    composeCode(t, "CodeSystem", "language", element.getLanguageElement(), -1);
    composeCoding(t, "CodeSystem", "use", element.getUse(), -1);
    composeString(t, "CodeSystem", "value", element.getValueElement(), -1);
  }

  protected void composeCodeSystemConceptDefinitionPropertyComponent(Complex parent, String parentType, String name, CodeSystem.ConceptDefinitionPropertyComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "property", name, element, index);
    composeCode(t, "CodeSystem", "code", element.getCodeElement(), -1);
    composeType(t, "CodeSystem", "value", element.getValue(), -1);
  }

  protected void composeCommunication(Complex parent, String parentType, String name, Communication element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Communication", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Communication", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "Communication", "category", element.getCategory(), -1);
    composeReference(t, "Communication", "sender", element.getSender(), -1);
    for (int i = 0; i < element.getRecipient().size(); i++)
      composeReference(t, "Communication", "recipient", element.getRecipient().get(i), i);
    for (int i = 0; i < element.getPayload().size(); i++)
      composeCommunicationCommunicationPayloadComponent(t, "Communication", "payload", element.getPayload().get(i), i);
    for (int i = 0; i < element.getMedium().size(); i++)
      composeCodeableConcept(t, "Communication", "medium", element.getMedium().get(i), i);
    composeEnum(t, "Communication", "status", element.getStatusElement(), -1);
    composeReference(t, "Communication", "encounter", element.getEncounter(), -1);
    composeDateTime(t, "Communication", "sent", element.getSentElement(), -1);
    composeDateTime(t, "Communication", "received", element.getReceivedElement(), -1);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCodeableConcept(t, "Communication", "reason", element.getReason().get(i), i);
    composeReference(t, "Communication", "subject", element.getSubject(), -1);
    composeReference(t, "Communication", "requestDetail", element.getRequestDetail(), -1);
  }

  protected void composeCommunicationCommunicationPayloadComponent(Complex parent, String parentType, String name, Communication.CommunicationPayloadComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "payload", name, element, index);
    composeType(t, "Communication", "content", element.getContent(), -1);
  }

  protected void composeCommunicationRequest(Complex parent, String parentType, String name, CommunicationRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "CommunicationRequest", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "CommunicationRequest", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "CommunicationRequest", "category", element.getCategory(), -1);
    composeReference(t, "CommunicationRequest", "sender", element.getSender(), -1);
    for (int i = 0; i < element.getRecipient().size(); i++)
      composeReference(t, "CommunicationRequest", "recipient", element.getRecipient().get(i), i);
    for (int i = 0; i < element.getPayload().size(); i++)
      composeCommunicationRequestCommunicationRequestPayloadComponent(t, "CommunicationRequest", "payload", element.getPayload().get(i), i);
    for (int i = 0; i < element.getMedium().size(); i++)
      composeCodeableConcept(t, "CommunicationRequest", "medium", element.getMedium().get(i), i);
    composeReference(t, "CommunicationRequest", "requester", element.getRequester(), -1);
    composeEnum(t, "CommunicationRequest", "status", element.getStatusElement(), -1);
    composeReference(t, "CommunicationRequest", "encounter", element.getEncounter(), -1);
    composeType(t, "CommunicationRequest", "scheduled", element.getScheduled(), -1);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCodeableConcept(t, "CommunicationRequest", "reason", element.getReason().get(i), i);
    composeDateTime(t, "CommunicationRequest", "requestedOn", element.getRequestedOnElement(), -1);
    composeReference(t, "CommunicationRequest", "subject", element.getSubject(), -1);
    composeCodeableConcept(t, "CommunicationRequest", "priority", element.getPriority(), -1);
  }

  protected void composeCommunicationRequestCommunicationRequestPayloadComponent(Complex parent, String parentType, String name, CommunicationRequest.CommunicationRequestPayloadComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "payload", name, element, index);
    composeType(t, "CommunicationRequest", "content", element.getContent(), -1);
  }

  protected void composeComposition(Complex parent, String parentType, String name, Composition element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Composition", name, element, index);
    composeIdentifier(t, "Composition", "identifier", element.getIdentifier(), -1);
    composeDateTime(t, "Composition", "date", element.getDateElement(), -1);
    composeCodeableConcept(t, "Composition", "type", element.getType(), -1);
    composeCodeableConcept(t, "Composition", "class", element.getClass_(), -1);
    composeString(t, "Composition", "title", element.getTitleElement(), -1);
    composeEnum(t, "Composition", "status", element.getStatusElement(), -1);
    composeCode(t, "Composition", "confidentiality", element.getConfidentialityElement(), -1);
    composeReference(t, "Composition", "subject", element.getSubject(), -1);
    for (int i = 0; i < element.getAuthor().size(); i++)
      composeReference(t, "Composition", "author", element.getAuthor().get(i), i);
    for (int i = 0; i < element.getAttester().size(); i++)
      composeCompositionCompositionAttesterComponent(t, "Composition", "attester", element.getAttester().get(i), i);
    composeReference(t, "Composition", "custodian", element.getCustodian(), -1);
    for (int i = 0; i < element.getEvent().size(); i++)
      composeCompositionCompositionEventComponent(t, "Composition", "event", element.getEvent().get(i), i);
    composeReference(t, "Composition", "encounter", element.getEncounter(), -1);
    for (int i = 0; i < element.getSection().size(); i++)
      composeCompositionSectionComponent(t, "Composition", "section", element.getSection().get(i), i);
  }

  protected void composeCompositionCompositionAttesterComponent(Complex parent, String parentType, String name, Composition.CompositionAttesterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "attester", name, element, index);
    for (int i = 0; i < element.getMode().size(); i++)
      composeEnum(t, "Composition", "mode", element.getMode().get(i), i);
    composeDateTime(t, "Composition", "time", element.getTimeElement(), -1);
    composeReference(t, "Composition", "party", element.getParty(), -1);
  }

  protected void composeCompositionCompositionEventComponent(Complex parent, String parentType, String name, Composition.CompositionEventComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "event", name, element, index);
    for (int i = 0; i < element.getCode().size(); i++)
      composeCodeableConcept(t, "Composition", "code", element.getCode().get(i), i);
    composePeriod(t, "Composition", "period", element.getPeriod(), -1);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeReference(t, "Composition", "detail", element.getDetail().get(i), i);
  }

  protected void composeCompositionSectionComponent(Complex parent, String parentType, String name, Composition.SectionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "section", name, element, index);
    composeString(t, "Composition", "title", element.getTitleElement(), -1);
    composeCodeableConcept(t, "Composition", "code", element.getCode(), -1);
    composeNarrative(t, "Composition", "text", element.getText(), -1);
    composeCode(t, "Composition", "mode", element.getModeElement(), -1);
    composeCodeableConcept(t, "Composition", "orderedBy", element.getOrderedBy(), -1);
    for (int i = 0; i < element.getEntry().size(); i++)
      composeReference(t, "Composition", "entry", element.getEntry().get(i), i);
    composeCodeableConcept(t, "Composition", "emptyReason", element.getEmptyReason(), -1);
    for (int i = 0; i < element.getSection().size(); i++)
      composeCompositionSectionComponent(t, "Composition", "section", element.getSection().get(i), i);
  }

  protected void composeConceptMap(Complex parent, String parentType, String name, ConceptMap element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ConceptMap", name, element, index);
    composeUri(t, "ConceptMap", "url", element.getUrlElement(), -1);
    composeIdentifier(t, "ConceptMap", "identifier", element.getIdentifier(), -1);
    composeString(t, "ConceptMap", "version", element.getVersionElement(), -1);
    composeString(t, "ConceptMap", "name", element.getNameElement(), -1);
    composeEnum(t, "ConceptMap", "status", element.getStatusElement(), -1);
    composeBoolean(t, "ConceptMap", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "ConceptMap", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeConceptMapConceptMapContactComponent(t, "ConceptMap", "contact", element.getContact().get(i), i);
    composeDateTime(t, "ConceptMap", "date", element.getDateElement(), -1);
    composeString(t, "ConceptMap", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "ConceptMap", "useContext", element.getUseContext().get(i), i);
    composeString(t, "ConceptMap", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "ConceptMap", "copyright", element.getCopyrightElement(), -1);
    composeType(t, "ConceptMap", "source", element.getSource(), -1);
    composeType(t, "ConceptMap", "target", element.getTarget(), -1);
    for (int i = 0; i < element.getElement().size(); i++)
      composeConceptMapSourceElementComponent(t, "ConceptMap", "element", element.getElement().get(i), i);
  }

  protected void composeConceptMapConceptMapContactComponent(Complex parent, String parentType, String name, ConceptMap.ConceptMapContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "ConceptMap", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "ConceptMap", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeConceptMapSourceElementComponent(Complex parent, String parentType, String name, ConceptMap.SourceElementComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "element", name, element, index);
    composeUri(t, "ConceptMap", "codeSystem", element.getCodeSystemElement(), -1);
    composeString(t, "ConceptMap", "codeSystemVersion", element.getCodeSystemVersionElement(), -1);
    composeCode(t, "ConceptMap", "code", element.getCodeElement(), -1);
    for (int i = 0; i < element.getTarget().size(); i++)
      composeConceptMapTargetElementComponent(t, "ConceptMap", "target", element.getTarget().get(i), i);
  }

  protected void composeConceptMapTargetElementComponent(Complex parent, String parentType, String name, ConceptMap.TargetElementComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "target", name, element, index);
    composeUri(t, "ConceptMap", "codeSystem", element.getCodeSystemElement(), -1);
    composeString(t, "ConceptMap", "codeSystemVersion", element.getCodeSystemVersionElement(), -1);
    composeCode(t, "ConceptMap", "code", element.getCodeElement(), -1);
    composeEnum(t, "ConceptMap", "equivalence", element.getEquivalenceElement(), -1);
    composeString(t, "ConceptMap", "comments", element.getCommentsElement(), -1);
    for (int i = 0; i < element.getDependsOn().size(); i++)
      composeConceptMapOtherElementComponent(t, "ConceptMap", "dependsOn", element.getDependsOn().get(i), i);
    for (int i = 0; i < element.getProduct().size(); i++)
      composeConceptMapOtherElementComponent(t, "ConceptMap", "product", element.getProduct().get(i), i);
  }

  protected void composeConceptMapOtherElementComponent(Complex parent, String parentType, String name, ConceptMap.OtherElementComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dependsOn", name, element, index);
    composeUri(t, "ConceptMap", "element", element.getElementElement(), -1);
    composeUri(t, "ConceptMap", "codeSystem", element.getCodeSystemElement(), -1);
    composeString(t, "ConceptMap", "code", element.getCodeElement(), -1);
  }

  protected void composeCondition(Complex parent, String parentType, String name, Condition element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Condition", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Condition", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "Condition", "patient", element.getPatient(), -1);
    composeReference(t, "Condition", "encounter", element.getEncounter(), -1);
    composeReference(t, "Condition", "asserter", element.getAsserter(), -1);
    composeDate(t, "Condition", "dateRecorded", element.getDateRecordedElement(), -1);
    composeCodeableConcept(t, "Condition", "code", element.getCode(), -1);
    composeCodeableConcept(t, "Condition", "category", element.getCategory(), -1);
    composeCode(t, "Condition", "clinicalStatus", element.getClinicalStatusElement(), -1);
    composeEnum(t, "Condition", "verificationStatus", element.getVerificationStatusElement(), -1);
    composeCodeableConcept(t, "Condition", "severity", element.getSeverity(), -1);
    composeType(t, "Condition", "onset", element.getOnset(), -1);
    composeType(t, "Condition", "abatement", element.getAbatement(), -1);
    composeConditionConditionStageComponent(t, "Condition", "stage", element.getStage(), -1);
    for (int i = 0; i < element.getEvidence().size(); i++)
      composeConditionConditionEvidenceComponent(t, "Condition", "evidence", element.getEvidence().get(i), i);
    for (int i = 0; i < element.getBodySite().size(); i++)
      composeCodeableConcept(t, "Condition", "bodySite", element.getBodySite().get(i), i);
    composeString(t, "Condition", "notes", element.getNotesElement(), -1);
  }

  protected void composeConditionConditionStageComponent(Complex parent, String parentType, String name, Condition.ConditionStageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "stage", name, element, index);
    composeCodeableConcept(t, "Condition", "summary", element.getSummary(), -1);
    for (int i = 0; i < element.getAssessment().size(); i++)
      composeReference(t, "Condition", "assessment", element.getAssessment().get(i), i);
  }

  protected void composeConditionConditionEvidenceComponent(Complex parent, String parentType, String name, Condition.ConditionEvidenceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "evidence", name, element, index);
    composeCodeableConcept(t, "Condition", "code", element.getCode(), -1);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeReference(t, "Condition", "detail", element.getDetail().get(i), i);
  }

  protected void composeConformance(Complex parent, String parentType, String name, Conformance element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Conformance", name, element, index);
    composeUri(t, "Conformance", "url", element.getUrlElement(), -1);
    composeString(t, "Conformance", "version", element.getVersionElement(), -1);
    composeString(t, "Conformance", "name", element.getNameElement(), -1);
    composeEnum(t, "Conformance", "status", element.getStatusElement(), -1);
    composeBoolean(t, "Conformance", "experimental", element.getExperimentalElement(), -1);
    composeDateTime(t, "Conformance", "date", element.getDateElement(), -1);
    composeString(t, "Conformance", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeConformanceConformanceContactComponent(t, "Conformance", "contact", element.getContact().get(i), i);
    composeString(t, "Conformance", "description", element.getDescriptionElement(), -1);
    composeString(t, "Conformance", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "Conformance", "copyright", element.getCopyrightElement(), -1);
    composeEnum(t, "Conformance", "kind", element.getKindElement(), -1);
    composeConformanceConformanceSoftwareComponent(t, "Conformance", "software", element.getSoftware(), -1);
    composeConformanceConformanceImplementationComponent(t, "Conformance", "implementation", element.getImplementation(), -1);
    composeId(t, "Conformance", "fhirVersion", element.getFhirVersionElement(), -1);
    composeEnum(t, "Conformance", "acceptUnknown", element.getAcceptUnknownElement(), -1);
    for (int i = 0; i < element.getFormat().size(); i++)
      composeCode(t, "Conformance", "format", element.getFormat().get(i), i);
    for (int i = 0; i < element.getProfile().size(); i++)
      composeReference(t, "Conformance", "profile", element.getProfile().get(i), i);
    for (int i = 0; i < element.getRest().size(); i++)
      composeConformanceConformanceRestComponent(t, "Conformance", "rest", element.getRest().get(i), i);
    for (int i = 0; i < element.getMessaging().size(); i++)
      composeConformanceConformanceMessagingComponent(t, "Conformance", "messaging", element.getMessaging().get(i), i);
    for (int i = 0; i < element.getDocument().size(); i++)
      composeConformanceConformanceDocumentComponent(t, "Conformance", "document", element.getDocument().get(i), i);
  }

  protected void composeConformanceConformanceContactComponent(Complex parent, String parentType, String name, Conformance.ConformanceContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "Conformance", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Conformance", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeConformanceConformanceSoftwareComponent(Complex parent, String parentType, String name, Conformance.ConformanceSoftwareComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "software", name, element, index);
    composeString(t, "Conformance", "name", element.getNameElement(), -1);
    composeString(t, "Conformance", "version", element.getVersionElement(), -1);
    composeDateTime(t, "Conformance", "releaseDate", element.getReleaseDateElement(), -1);
  }

  protected void composeConformanceConformanceImplementationComponent(Complex parent, String parentType, String name, Conformance.ConformanceImplementationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "implementation", name, element, index);
    composeString(t, "Conformance", "description", element.getDescriptionElement(), -1);
    composeUri(t, "Conformance", "url", element.getUrlElement(), -1);
  }

  protected void composeConformanceConformanceRestComponent(Complex parent, String parentType, String name, Conformance.ConformanceRestComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "rest", name, element, index);
    composeEnum(t, "Conformance", "mode", element.getModeElement(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
    composeConformanceConformanceRestSecurityComponent(t, "Conformance", "security", element.getSecurity(), -1);
    for (int i = 0; i < element.getResource().size(); i++)
      composeConformanceConformanceRestResourceComponent(t, "Conformance", "resource", element.getResource().get(i), i);
    for (int i = 0; i < element.getInteraction().size(); i++)
      composeConformanceSystemInteractionComponent(t, "Conformance", "interaction", element.getInteraction().get(i), i);
    composeEnum(t, "Conformance", "transactionMode", element.getTransactionModeElement(), -1);
    for (int i = 0; i < element.getSearchParam().size(); i++)
      composeConformanceConformanceRestResourceSearchParamComponent(t, "Conformance", "searchParam", element.getSearchParam().get(i), i);
    for (int i = 0; i < element.getOperation().size(); i++)
      composeConformanceConformanceRestOperationComponent(t, "Conformance", "operation", element.getOperation().get(i), i);
    for (int i = 0; i < element.getCompartment().size(); i++)
      composeUri(t, "Conformance", "compartment", element.getCompartment().get(i), i);
  }

  protected void composeConformanceConformanceRestSecurityComponent(Complex parent, String parentType, String name, Conformance.ConformanceRestSecurityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "security", name, element, index);
    composeBoolean(t, "Conformance", "cors", element.getCorsElement(), -1);
    for (int i = 0; i < element.getService().size(); i++)
      composeCodeableConcept(t, "Conformance", "service", element.getService().get(i), i);
    composeString(t, "Conformance", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getCertificate().size(); i++)
      composeConformanceConformanceRestSecurityCertificateComponent(t, "Conformance", "certificate", element.getCertificate().get(i), i);
  }

  protected void composeConformanceConformanceRestSecurityCertificateComponent(Complex parent, String parentType, String name, Conformance.ConformanceRestSecurityCertificateComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "certificate", name, element, index);
    composeCode(t, "Conformance", "type", element.getTypeElement(), -1);
    composeBase64Binary(t, "Conformance", "blob", element.getBlobElement(), -1);
  }

  protected void composeConformanceConformanceRestResourceComponent(Complex parent, String parentType, String name, Conformance.ConformanceRestResourceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "resource", name, element, index);
    composeCode(t, "Conformance", "type", element.getTypeElement(), -1);
    composeReference(t, "Conformance", "profile", element.getProfile(), -1);
    for (int i = 0; i < element.getInteraction().size(); i++)
      composeConformanceResourceInteractionComponent(t, "Conformance", "interaction", element.getInteraction().get(i), i);
    composeEnum(t, "Conformance", "versioning", element.getVersioningElement(), -1);
    composeBoolean(t, "Conformance", "readHistory", element.getReadHistoryElement(), -1);
    composeBoolean(t, "Conformance", "updateCreate", element.getUpdateCreateElement(), -1);
    composeBoolean(t, "Conformance", "conditionalCreate", element.getConditionalCreateElement(), -1);
    composeBoolean(t, "Conformance", "conditionalUpdate", element.getConditionalUpdateElement(), -1);
    composeEnum(t, "Conformance", "conditionalDelete", element.getConditionalDeleteElement(), -1);
    for (int i = 0; i < element.getSearchInclude().size(); i++)
      composeString(t, "Conformance", "searchInclude", element.getSearchInclude().get(i), i);
    for (int i = 0; i < element.getSearchRevInclude().size(); i++)
      composeString(t, "Conformance", "searchRevInclude", element.getSearchRevInclude().get(i), i);
    for (int i = 0; i < element.getSearchParam().size(); i++)
      composeConformanceConformanceRestResourceSearchParamComponent(t, "Conformance", "searchParam", element.getSearchParam().get(i), i);
  }

  protected void composeConformanceResourceInteractionComponent(Complex parent, String parentType, String name, Conformance.ResourceInteractionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "interaction", name, element, index);
    composeEnum(t, "Conformance", "code", element.getCodeElement(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
  }

  protected void composeConformanceConformanceRestResourceSearchParamComponent(Complex parent, String parentType, String name, Conformance.ConformanceRestResourceSearchParamComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "searchParam", name, element, index);
    composeString(t, "Conformance", "name", element.getNameElement(), -1);
    composeUri(t, "Conformance", "definition", element.getDefinitionElement(), -1);
    composeEnum(t, "Conformance", "type", element.getTypeElement(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
    for (int i = 0; i < element.getTarget().size(); i++)
      composeCode(t, "Conformance", "target", element.getTarget().get(i), i);
    for (int i = 0; i < element.getModifier().size(); i++)
      composeEnum(t, "Conformance", "modifier", element.getModifier().get(i), i);
    for (int i = 0; i < element.getChain().size(); i++)
      composeString(t, "Conformance", "chain", element.getChain().get(i), i);
  }

  protected void composeConformanceSystemInteractionComponent(Complex parent, String parentType, String name, Conformance.SystemInteractionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "interaction", name, element, index);
    composeEnum(t, "Conformance", "code", element.getCodeElement(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
  }

  protected void composeConformanceConformanceRestOperationComponent(Complex parent, String parentType, String name, Conformance.ConformanceRestOperationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "operation", name, element, index);
    composeString(t, "Conformance", "name", element.getNameElement(), -1);
    composeReference(t, "Conformance", "definition", element.getDefinition(), -1);
  }

  protected void composeConformanceConformanceMessagingComponent(Complex parent, String parentType, String name, Conformance.ConformanceMessagingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "messaging", name, element, index);
    for (int i = 0; i < element.getEndpoint().size(); i++)
      composeConformanceConformanceMessagingEndpointComponent(t, "Conformance", "endpoint", element.getEndpoint().get(i), i);
    composeUnsignedInt(t, "Conformance", "reliableCache", element.getReliableCacheElement(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
    for (int i = 0; i < element.getEvent().size(); i++)
      composeConformanceConformanceMessagingEventComponent(t, "Conformance", "event", element.getEvent().get(i), i);
  }

  protected void composeConformanceConformanceMessagingEndpointComponent(Complex parent, String parentType, String name, Conformance.ConformanceMessagingEndpointComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "endpoint", name, element, index);
    composeCoding(t, "Conformance", "protocol", element.getProtocol(), -1);
    composeUri(t, "Conformance", "address", element.getAddressElement(), -1);
  }

  protected void composeConformanceConformanceMessagingEventComponent(Complex parent, String parentType, String name, Conformance.ConformanceMessagingEventComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "event", name, element, index);
    composeCoding(t, "Conformance", "code", element.getCode(), -1);
    composeEnum(t, "Conformance", "category", element.getCategoryElement(), -1);
    composeEnum(t, "Conformance", "mode", element.getModeElement(), -1);
    composeCode(t, "Conformance", "focus", element.getFocusElement(), -1);
    composeReference(t, "Conformance", "request", element.getRequest(), -1);
    composeReference(t, "Conformance", "response", element.getResponse(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
  }

  protected void composeConformanceConformanceDocumentComponent(Complex parent, String parentType, String name, Conformance.ConformanceDocumentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "document", name, element, index);
    composeEnum(t, "Conformance", "mode", element.getModeElement(), -1);
    composeString(t, "Conformance", "documentation", element.getDocumentationElement(), -1);
    composeReference(t, "Conformance", "profile", element.getProfile(), -1);
  }

  protected void composeContract(Complex parent, String parentType, String name, Contract element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Contract", name, element, index);
    composeIdentifier(t, "Contract", "identifier", element.getIdentifier(), -1);
    composeDateTime(t, "Contract", "issued", element.getIssuedElement(), -1);
    composePeriod(t, "Contract", "applies", element.getApplies(), -1);
    for (int i = 0; i < element.getTopic().size(); i++)
      composeReference(t, "Contract", "topic", element.getTopic().get(i), i);
    for (int i = 0; i < element.getAuthority().size(); i++)
      composeReference(t, "Contract", "authority", element.getAuthority().get(i), i);
    for (int i = 0; i < element.getDomain().size(); i++)
      composeReference(t, "Contract", "domain", element.getDomain().get(i), i);
    composeCodeableConcept(t, "Contract", "type", element.getType(), -1);
    for (int i = 0; i < element.getSubType().size(); i++)
      composeCodeableConcept(t, "Contract", "subType", element.getSubType().get(i), i);
    for (int i = 0; i < element.getAction().size(); i++)
      composeCodeableConcept(t, "Contract", "action", element.getAction().get(i), i);
    for (int i = 0; i < element.getActionReason().size(); i++)
      composeCodeableConcept(t, "Contract", "actionReason", element.getActionReason().get(i), i);
    for (int i = 0; i < element.getAgent().size(); i++)
      composeContractAgentComponent(t, "Contract", "agent", element.getAgent().get(i), i);
    for (int i = 0; i < element.getSigner().size(); i++)
      composeContractSignatoryComponent(t, "Contract", "signer", element.getSigner().get(i), i);
    for (int i = 0; i < element.getValuedItem().size(); i++)
      composeContractValuedItemComponent(t, "Contract", "valuedItem", element.getValuedItem().get(i), i);
    for (int i = 0; i < element.getTerm().size(); i++)
      composeContractTermComponent(t, "Contract", "term", element.getTerm().get(i), i);
    composeType(t, "Contract", "binding", element.getBinding(), -1);
    for (int i = 0; i < element.getFriendly().size(); i++)
      composeContractFriendlyLanguageComponent(t, "Contract", "friendly", element.getFriendly().get(i), i);
    for (int i = 0; i < element.getLegal().size(); i++)
      composeContractLegalLanguageComponent(t, "Contract", "legal", element.getLegal().get(i), i);
    for (int i = 0; i < element.getRule().size(); i++)
      composeContractComputableLanguageComponent(t, "Contract", "rule", element.getRule().get(i), i);
  }

  protected void composeContractAgentComponent(Complex parent, String parentType, String name, Contract.AgentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "agent", name, element, index);
    composeReference(t, "Contract", "actor", element.getActor(), -1);
    for (int i = 0; i < element.getRole().size(); i++)
      composeCodeableConcept(t, "Contract", "role", element.getRole().get(i), i);
  }

  protected void composeContractSignatoryComponent(Complex parent, String parentType, String name, Contract.SignatoryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "signer", name, element, index);
    composeCoding(t, "Contract", "type", element.getType(), -1);
    composeReference(t, "Contract", "party", element.getParty(), -1);
    for (int i = 0; i < element.getSignature().size(); i++)
      composeSignature(t, "Contract", "signature", element.getSignature().get(i), i);
  }

  protected void composeContractValuedItemComponent(Complex parent, String parentType, String name, Contract.ValuedItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "valuedItem", name, element, index);
    composeType(t, "Contract", "entity", element.getEntity(), -1);
    composeIdentifier(t, "Contract", "identifier", element.getIdentifier(), -1);
    composeDateTime(t, "Contract", "effectiveTime", element.getEffectiveTimeElement(), -1);
    composeQuantity(t, "Contract", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "Contract", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "Contract", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "Contract", "points", element.getPointsElement(), -1);
    composeQuantity(t, "Contract", "net", element.getNet(), -1);
  }

  protected void composeContractTermComponent(Complex parent, String parentType, String name, Contract.TermComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "term", name, element, index);
    composeIdentifier(t, "Contract", "identifier", element.getIdentifier(), -1);
    composeDateTime(t, "Contract", "issued", element.getIssuedElement(), -1);
    composePeriod(t, "Contract", "applies", element.getApplies(), -1);
    composeCodeableConcept(t, "Contract", "type", element.getType(), -1);
    composeCodeableConcept(t, "Contract", "subType", element.getSubType(), -1);
    for (int i = 0; i < element.getTopic().size(); i++)
      composeReference(t, "Contract", "topic", element.getTopic().get(i), i);
    for (int i = 0; i < element.getAction().size(); i++)
      composeCodeableConcept(t, "Contract", "action", element.getAction().get(i), i);
    for (int i = 0; i < element.getActionReason().size(); i++)
      composeCodeableConcept(t, "Contract", "actionReason", element.getActionReason().get(i), i);
    for (int i = 0; i < element.getAgent().size(); i++)
      composeContractTermAgentComponent(t, "Contract", "agent", element.getAgent().get(i), i);
    composeString(t, "Contract", "text", element.getTextElement(), -1);
    for (int i = 0; i < element.getValuedItem().size(); i++)
      composeContractTermValuedItemComponent(t, "Contract", "valuedItem", element.getValuedItem().get(i), i);
    for (int i = 0; i < element.getGroup().size(); i++)
      composeContractTermComponent(t, "Contract", "group", element.getGroup().get(i), i);
  }

  protected void composeContractTermAgentComponent(Complex parent, String parentType, String name, Contract.TermAgentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "agent", name, element, index);
    composeReference(t, "Contract", "actor", element.getActor(), -1);
    for (int i = 0; i < element.getRole().size(); i++)
      composeCodeableConcept(t, "Contract", "role", element.getRole().get(i), i);
  }

  protected void composeContractTermValuedItemComponent(Complex parent, String parentType, String name, Contract.TermValuedItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "valuedItem", name, element, index);
    composeType(t, "Contract", "entity", element.getEntity(), -1);
    composeIdentifier(t, "Contract", "identifier", element.getIdentifier(), -1);
    composeDateTime(t, "Contract", "effectiveTime", element.getEffectiveTimeElement(), -1);
    composeQuantity(t, "Contract", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "Contract", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "Contract", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "Contract", "points", element.getPointsElement(), -1);
    composeQuantity(t, "Contract", "net", element.getNet(), -1);
  }

  protected void composeContractFriendlyLanguageComponent(Complex parent, String parentType, String name, Contract.FriendlyLanguageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "friendly", name, element, index);
    composeType(t, "Contract", "content", element.getContent(), -1);
  }

  protected void composeContractLegalLanguageComponent(Complex parent, String parentType, String name, Contract.LegalLanguageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "legal", name, element, index);
    composeType(t, "Contract", "content", element.getContent(), -1);
  }

  protected void composeContractComputableLanguageComponent(Complex parent, String parentType, String name, Contract.ComputableLanguageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "rule", name, element, index);
    composeType(t, "Contract", "content", element.getContent(), -1);
  }

  protected void composeCoverage(Complex parent, String parentType, String name, Coverage element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Coverage", name, element, index);
    composeReference(t, "Coverage", "issuer", element.getIssuer(), -1);
    composeIdentifier(t, "Coverage", "bin", element.getBin(), -1);
    composePeriod(t, "Coverage", "period", element.getPeriod(), -1);
    composeCoding(t, "Coverage", "type", element.getType(), -1);
    composeIdentifier(t, "Coverage", "subscriberId", element.getSubscriberId(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Coverage", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Coverage", "group", element.getGroupElement(), -1);
    composeString(t, "Coverage", "plan", element.getPlanElement(), -1);
    composeString(t, "Coverage", "subPlan", element.getSubPlanElement(), -1);
    composePositiveInt(t, "Coverage", "dependent", element.getDependentElement(), -1);
    composePositiveInt(t, "Coverage", "sequence", element.getSequenceElement(), -1);
    composeReference(t, "Coverage", "subscriber", element.getSubscriber(), -1);
    composeIdentifier(t, "Coverage", "network", element.getNetwork(), -1);
    for (int i = 0; i < element.getContract().size(); i++)
      composeReference(t, "Coverage", "contract", element.getContract().get(i), i);
  }

  protected void composeDataElement(Complex parent, String parentType, String name, DataElement element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DataElement", name, element, index);
    composeUri(t, "DataElement", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DataElement", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "DataElement", "version", element.getVersionElement(), -1);
    composeEnum(t, "DataElement", "status", element.getStatusElement(), -1);
    composeBoolean(t, "DataElement", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "DataElement", "publisher", element.getPublisherElement(), -1);
    composeDateTime(t, "DataElement", "date", element.getDateElement(), -1);
    composeString(t, "DataElement", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeDataElementDataElementContactComponent(t, "DataElement", "contact", element.getContact().get(i), i);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "DataElement", "useContext", element.getUseContext().get(i), i);
    composeString(t, "DataElement", "copyright", element.getCopyrightElement(), -1);
    composeEnum(t, "DataElement", "stringency", element.getStringencyElement(), -1);
    for (int i = 0; i < element.getMapping().size(); i++)
      composeDataElementDataElementMappingComponent(t, "DataElement", "mapping", element.getMapping().get(i), i);
    for (int i = 0; i < element.getElement().size(); i++)
      composeElementDefinition(t, "DataElement", "element", element.getElement().get(i), i);
  }

  protected void composeDataElementDataElementContactComponent(Complex parent, String parentType, String name, DataElement.DataElementContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "DataElement", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "DataElement", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeDataElementDataElementMappingComponent(Complex parent, String parentType, String name, DataElement.DataElementMappingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "mapping", name, element, index);
    composeId(t, "DataElement", "identity", element.getIdentityElement(), -1);
    composeUri(t, "DataElement", "uri", element.getUriElement(), -1);
    composeString(t, "DataElement", "name", element.getNameElement(), -1);
    composeString(t, "DataElement", "comment", element.getCommentElement(), -1);
  }

  protected void composeDecisionSupportRule(Complex parent, String parentType, String name, DecisionSupportRule element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DecisionSupportRule", name, element, index);
    composeModuleMetadata(t, "DecisionSupportRule", "moduleMetadata", element.getModuleMetadata(), -1);
    for (int i = 0; i < element.getLibrary().size(); i++)
      composeReference(t, "DecisionSupportRule", "library", element.getLibrary().get(i), i);
    for (int i = 0; i < element.getTrigger().size(); i++)
      composeDecisionSupportRuleDecisionSupportRuleTriggerComponent(t, "DecisionSupportRule", "trigger", element.getTrigger().get(i), i);
    composeString(t, "DecisionSupportRule", "condition", element.getConditionElement(), -1);
    for (int i = 0; i < element.getAction().size(); i++)
      composeDecisionSupportRuleDecisionSupportRuleActionComponent(t, "DecisionSupportRule", "action", element.getAction().get(i), i);
  }

  protected void composeDecisionSupportRuleDecisionSupportRuleTriggerComponent(Complex parent, String parentType, String name, DecisionSupportRule.DecisionSupportRuleTriggerComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "trigger", name, element, index);
    composeEnum(t, "DecisionSupportRule", "type", element.getTypeElement(), -1);
    composeString(t, "DecisionSupportRule", "eventName", element.getEventNameElement(), -1);
    composeType(t, "DecisionSupportRule", "eventTiming", element.getEventTiming(), -1);
  }

  protected void composeDecisionSupportRuleDecisionSupportRuleActionComponent(Complex parent, String parentType, String name, DecisionSupportRule.DecisionSupportRuleActionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "action", name, element, index);
    composeIdentifier(t, "DecisionSupportRule", "actionIdentifier", element.getActionIdentifier(), -1);
    composeString(t, "DecisionSupportRule", "number", element.getNumberElement(), -1);
    for (int i = 0; i < element.getSupportingEvidence().size(); i++)
      composeAttachment(t, "DecisionSupportRule", "supportingEvidence", element.getSupportingEvidence().get(i), i);
    for (int i = 0; i < element.getDocumentation().size(); i++)
      composeAttachment(t, "DecisionSupportRule", "documentation", element.getDocumentation().get(i), i);
    for (int i = 0; i < element.getParticipantType().size(); i++)
      composeEnum(t, "DecisionSupportRule", "participantType", element.getParticipantType().get(i), i);
    composeString(t, "DecisionSupportRule", "title", element.getTitleElement(), -1);
    composeString(t, "DecisionSupportRule", "description", element.getDescriptionElement(), -1);
    composeString(t, "DecisionSupportRule", "textEquivalent", element.getTextEquivalentElement(), -1);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCodeableConcept(t, "DecisionSupportRule", "concept", element.getConcept().get(i), i);
    composeEnum(t, "DecisionSupportRule", "type", element.getTypeElement(), -1);
    composeReference(t, "DecisionSupportRule", "resource", element.getResource(), -1);
    for (int i = 0; i < element.getCustomization().size(); i++)
      composeDecisionSupportRuleDecisionSupportRuleActionCustomizationComponent(t, "DecisionSupportRule", "customization", element.getCustomization().get(i), i);
    for (int i = 0; i < element.getActions().size(); i++)
      composeDecisionSupportRuleDecisionSupportRuleActionComponent(t, "DecisionSupportRule", "actions", element.getActions().get(i), i);
  }

  protected void composeDecisionSupportRuleDecisionSupportRuleActionCustomizationComponent(Complex parent, String parentType, String name, DecisionSupportRule.DecisionSupportRuleActionCustomizationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "customization", name, element, index);
    composeString(t, "DecisionSupportRule", "path", element.getPathElement(), -1);
    composeString(t, "DecisionSupportRule", "expression", element.getExpressionElement(), -1);
  }

  protected void composeDecisionSupportServiceModule(Complex parent, String parentType, String name, DecisionSupportServiceModule element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DecisionSupportServiceModule", name, element, index);
    composeModuleMetadata(t, "DecisionSupportServiceModule", "moduleMetadata", element.getModuleMetadata(), -1);
    for (int i = 0; i < element.getParameter().size(); i++)
      composeDecisionSupportServiceModuleDecisionSupportServiceModuleParameterComponent(t, "DecisionSupportServiceModule", "parameter", element.getParameter().get(i), i);
  }

  protected void composeDecisionSupportServiceModuleDecisionSupportServiceModuleParameterComponent(Complex parent, String parentType, String name, DecisionSupportServiceModule.DecisionSupportServiceModuleParameterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "parameter", name, element, index);
    composeCode(t, "DecisionSupportServiceModule", "name", element.getNameElement(), -1);
    composeCode(t, "DecisionSupportServiceModule", "use", element.getUseElement(), -1);
    composeString(t, "DecisionSupportServiceModule", "documentation", element.getDocumentationElement(), -1);
    composeCode(t, "DecisionSupportServiceModule", "type", element.getTypeElement(), -1);
    composeReference(t, "DecisionSupportServiceModule", "profile", element.getProfile(), -1);
    for (int i = 0; i < element.getMustSupport().size(); i++)
      composeString(t, "DecisionSupportServiceModule", "mustSupport", element.getMustSupport().get(i), i);
    for (int i = 0; i < element.getCodeFilter().size(); i++)
      composeDecisionSupportServiceModuleDecisionSupportServiceModuleParameterCodeFilterComponent(t, "DecisionSupportServiceModule", "codeFilter", element.getCodeFilter().get(i), i);
    for (int i = 0; i < element.getDateFilter().size(); i++)
      composeDecisionSupportServiceModuleDecisionSupportServiceModuleParameterDateFilterComponent(t, "DecisionSupportServiceModule", "dateFilter", element.getDateFilter().get(i), i);
  }

  protected void composeDecisionSupportServiceModuleDecisionSupportServiceModuleParameterCodeFilterComponent(Complex parent, String parentType, String name, DecisionSupportServiceModule.DecisionSupportServiceModuleParameterCodeFilterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeFilter", name, element, index);
    composeString(t, "DecisionSupportServiceModule", "path", element.getPathElement(), -1);
    composeType(t, "DecisionSupportServiceModule", "valueSet", element.getValueSet(), -1);
    for (int i = 0; i < element.getCodeableConcept().size(); i++)
      composeCodeableConcept(t, "DecisionSupportServiceModule", "codeableConcept", element.getCodeableConcept().get(i), i);
  }

  protected void composeDecisionSupportServiceModuleDecisionSupportServiceModuleParameterDateFilterComponent(Complex parent, String parentType, String name, DecisionSupportServiceModule.DecisionSupportServiceModuleParameterDateFilterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dateFilter", name, element, index);
    composeString(t, "DecisionSupportServiceModule", "path", element.getPathElement(), -1);
    composeType(t, "DecisionSupportServiceModule", "value", element.getValue(), -1);
  }

  protected void composeDetectedIssue(Complex parent, String parentType, String name, DetectedIssue element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DetectedIssue", name, element, index);
    composeReference(t, "DetectedIssue", "patient", element.getPatient(), -1);
    composeCodeableConcept(t, "DetectedIssue", "category", element.getCategory(), -1);
    composeEnum(t, "DetectedIssue", "severity", element.getSeverityElement(), -1);
    for (int i = 0; i < element.getImplicated().size(); i++)
      composeReference(t, "DetectedIssue", "implicated", element.getImplicated().get(i), i);
    composeString(t, "DetectedIssue", "detail", element.getDetailElement(), -1);
    composeDateTime(t, "DetectedIssue", "date", element.getDateElement(), -1);
    composeReference(t, "DetectedIssue", "author", element.getAuthor(), -1);
    composeIdentifier(t, "DetectedIssue", "identifier", element.getIdentifier(), -1);
    composeUri(t, "DetectedIssue", "reference", element.getReferenceElement(), -1);
    for (int i = 0; i < element.getMitigation().size(); i++)
      composeDetectedIssueDetectedIssueMitigationComponent(t, "DetectedIssue", "mitigation", element.getMitigation().get(i), i);
  }

  protected void composeDetectedIssueDetectedIssueMitigationComponent(Complex parent, String parentType, String name, DetectedIssue.DetectedIssueMitigationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "mitigation", name, element, index);
    composeCodeableConcept(t, "DetectedIssue", "action", element.getAction(), -1);
    composeDateTime(t, "DetectedIssue", "date", element.getDateElement(), -1);
    composeReference(t, "DetectedIssue", "author", element.getAuthor(), -1);
  }

  protected void composeDevice(Complex parent, String parentType, String name, Device element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Device", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Device", "identifier", element.getIdentifier().get(i), i);
    composeIdentifier(t, "Device", "udiCarrier", element.getUdiCarrier(), -1);
    composeEnum(t, "Device", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "Device", "type", element.getType(), -1);
    composeString(t, "Device", "lotNumber", element.getLotNumberElement(), -1);
    composeString(t, "Device", "manufacturer", element.getManufacturerElement(), -1);
    composeDateTime(t, "Device", "manufactureDate", element.getManufactureDateElement(), -1);
    composeDateTime(t, "Device", "expirationDate", element.getExpirationDateElement(), -1);
    composeString(t, "Device", "model", element.getModelElement(), -1);
    composeString(t, "Device", "version", element.getVersionElement(), -1);
    composeReference(t, "Device", "patient", element.getPatient(), -1);
    composeReference(t, "Device", "owner", element.getOwner(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeContactPoint(t, "Device", "contact", element.getContact().get(i), i);
    composeReference(t, "Device", "location", element.getLocation(), -1);
    composeUri(t, "Device", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "Device", "note", element.getNote().get(i), i);
  }

  protected void composeDeviceComponent(Complex parent, String parentType, String name, DeviceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DeviceComponent", name, element, index);
    composeCodeableConcept(t, "DeviceComponent", "type", element.getType(), -1);
    composeIdentifier(t, "DeviceComponent", "identifier", element.getIdentifier(), -1);
    composeInstant(t, "DeviceComponent", "lastSystemChange", element.getLastSystemChangeElement(), -1);
    composeReference(t, "DeviceComponent", "source", element.getSource(), -1);
    composeReference(t, "DeviceComponent", "parent", element.getParent(), -1);
    for (int i = 0; i < element.getOperationalStatus().size(); i++)
      composeCodeableConcept(t, "DeviceComponent", "operationalStatus", element.getOperationalStatus().get(i), i);
    composeCodeableConcept(t, "DeviceComponent", "parameterGroup", element.getParameterGroup(), -1);
    composeEnum(t, "DeviceComponent", "measurementPrinciple", element.getMeasurementPrincipleElement(), -1);
    for (int i = 0; i < element.getProductionSpecification().size(); i++)
      composeDeviceComponentDeviceComponentProductionSpecificationComponent(t, "DeviceComponent", "productionSpecification", element.getProductionSpecification().get(i), i);
    composeCodeableConcept(t, "DeviceComponent", "languageCode", element.getLanguageCode(), -1);
  }

  protected void composeDeviceComponentDeviceComponentProductionSpecificationComponent(Complex parent, String parentType, String name, DeviceComponent.DeviceComponentProductionSpecificationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "productionSpecification", name, element, index);
    composeCodeableConcept(t, "DeviceComponent", "specType", element.getSpecType(), -1);
    composeIdentifier(t, "DeviceComponent", "componentId", element.getComponentId(), -1);
    composeString(t, "DeviceComponent", "productionSpec", element.getProductionSpecElement(), -1);
  }

  protected void composeDeviceMetric(Complex parent, String parentType, String name, DeviceMetric element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DeviceMetric", name, element, index);
    composeCodeableConcept(t, "DeviceMetric", "type", element.getType(), -1);
    composeIdentifier(t, "DeviceMetric", "identifier", element.getIdentifier(), -1);
    composeCodeableConcept(t, "DeviceMetric", "unit", element.getUnit(), -1);
    composeReference(t, "DeviceMetric", "source", element.getSource(), -1);
    composeReference(t, "DeviceMetric", "parent", element.getParent(), -1);
    composeEnum(t, "DeviceMetric", "operationalStatus", element.getOperationalStatusElement(), -1);
    composeEnum(t, "DeviceMetric", "color", element.getColorElement(), -1);
    composeEnum(t, "DeviceMetric", "category", element.getCategoryElement(), -1);
    composeTiming(t, "DeviceMetric", "measurementPeriod", element.getMeasurementPeriod(), -1);
    for (int i = 0; i < element.getCalibration().size(); i++)
      composeDeviceMetricDeviceMetricCalibrationComponent(t, "DeviceMetric", "calibration", element.getCalibration().get(i), i);
  }

  protected void composeDeviceMetricDeviceMetricCalibrationComponent(Complex parent, String parentType, String name, DeviceMetric.DeviceMetricCalibrationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "calibration", name, element, index);
    composeEnum(t, "DeviceMetric", "type", element.getTypeElement(), -1);
    composeEnum(t, "DeviceMetric", "state", element.getStateElement(), -1);
    composeInstant(t, "DeviceMetric", "time", element.getTimeElement(), -1);
  }

  protected void composeDeviceUseRequest(Complex parent, String parentType, String name, DeviceUseRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DeviceUseRequest", name, element, index);
    composeType(t, "DeviceUseRequest", "bodySite", element.getBodySite(), -1);
    composeEnum(t, "DeviceUseRequest", "status", element.getStatusElement(), -1);
    composeReference(t, "DeviceUseRequest", "device", element.getDevice(), -1);
    composeReference(t, "DeviceUseRequest", "encounter", element.getEncounter(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DeviceUseRequest", "identifier", element.getIdentifier().get(i), i);
    for (int i = 0; i < element.getIndication().size(); i++)
      composeCodeableConcept(t, "DeviceUseRequest", "indication", element.getIndication().get(i), i);
    for (int i = 0; i < element.getNotes().size(); i++)
      composeString(t, "DeviceUseRequest", "notes", element.getNotes().get(i), i);
    for (int i = 0; i < element.getPrnReason().size(); i++)
      composeCodeableConcept(t, "DeviceUseRequest", "prnReason", element.getPrnReason().get(i), i);
    composeDateTime(t, "DeviceUseRequest", "orderedOn", element.getOrderedOnElement(), -1);
    composeDateTime(t, "DeviceUseRequest", "recordedOn", element.getRecordedOnElement(), -1);
    composeReference(t, "DeviceUseRequest", "subject", element.getSubject(), -1);
    composeType(t, "DeviceUseRequest", "timing", element.getTiming(), -1);
    composeEnum(t, "DeviceUseRequest", "priority", element.getPriorityElement(), -1);
  }

  protected void composeDeviceUseStatement(Complex parent, String parentType, String name, DeviceUseStatement element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DeviceUseStatement", name, element, index);
    composeType(t, "DeviceUseStatement", "bodySite", element.getBodySite(), -1);
    composePeriod(t, "DeviceUseStatement", "whenUsed", element.getWhenUsed(), -1);
    composeReference(t, "DeviceUseStatement", "device", element.getDevice(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DeviceUseStatement", "identifier", element.getIdentifier().get(i), i);
    for (int i = 0; i < element.getIndication().size(); i++)
      composeCodeableConcept(t, "DeviceUseStatement", "indication", element.getIndication().get(i), i);
    for (int i = 0; i < element.getNotes().size(); i++)
      composeString(t, "DeviceUseStatement", "notes", element.getNotes().get(i), i);
    composeDateTime(t, "DeviceUseStatement", "recordedOn", element.getRecordedOnElement(), -1);
    composeReference(t, "DeviceUseStatement", "subject", element.getSubject(), -1);
    composeType(t, "DeviceUseStatement", "timing", element.getTiming(), -1);
  }

  protected void composeDiagnosticOrder(Complex parent, String parentType, String name, DiagnosticOrder element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DiagnosticOrder", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DiagnosticOrder", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "DiagnosticOrder", "status", element.getStatusElement(), -1);
    composeEnum(t, "DiagnosticOrder", "priority", element.getPriorityElement(), -1);
    composeReference(t, "DiagnosticOrder", "subject", element.getSubject(), -1);
    composeReference(t, "DiagnosticOrder", "encounter", element.getEncounter(), -1);
    composeReference(t, "DiagnosticOrder", "orderer", element.getOrderer(), -1);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCodeableConcept(t, "DiagnosticOrder", "reason", element.getReason().get(i), i);
    for (int i = 0; i < element.getSupportingInformation().size(); i++)
      composeReference(t, "DiagnosticOrder", "supportingInformation", element.getSupportingInformation().get(i), i);
    for (int i = 0; i < element.getSpecimen().size(); i++)
      composeReference(t, "DiagnosticOrder", "specimen", element.getSpecimen().get(i), i);
    for (int i = 0; i < element.getEvent().size(); i++)
      composeDiagnosticOrderDiagnosticOrderEventComponent(t, "DiagnosticOrder", "event", element.getEvent().get(i), i);
    for (int i = 0; i < element.getItem().size(); i++)
      composeDiagnosticOrderDiagnosticOrderItemComponent(t, "DiagnosticOrder", "item", element.getItem().get(i), i);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "DiagnosticOrder", "note", element.getNote().get(i), i);
  }

  protected void composeDiagnosticOrderDiagnosticOrderEventComponent(Complex parent, String parentType, String name, DiagnosticOrder.DiagnosticOrderEventComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "event", name, element, index);
    composeEnum(t, "DiagnosticOrder", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "DiagnosticOrder", "description", element.getDescription(), -1);
    composeDateTime(t, "DiagnosticOrder", "dateTime", element.getDateTimeElement(), -1);
    composeReference(t, "DiagnosticOrder", "actor", element.getActor(), -1);
  }

  protected void composeDiagnosticOrderDiagnosticOrderItemComponent(Complex parent, String parentType, String name, DiagnosticOrder.DiagnosticOrderItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composeCodeableConcept(t, "DiagnosticOrder", "code", element.getCode(), -1);
    for (int i = 0; i < element.getSpecimen().size(); i++)
      composeReference(t, "DiagnosticOrder", "specimen", element.getSpecimen().get(i), i);
    composeCodeableConcept(t, "DiagnosticOrder", "bodySite", element.getBodySite(), -1);
    composeEnum(t, "DiagnosticOrder", "status", element.getStatusElement(), -1);
    for (int i = 0; i < element.getEvent().size(); i++)
      composeDiagnosticOrderDiagnosticOrderEventComponent(t, "DiagnosticOrder", "event", element.getEvent().get(i), i);
  }

  protected void composeDiagnosticReport(Complex parent, String parentType, String name, DiagnosticReport element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DiagnosticReport", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DiagnosticReport", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "DiagnosticReport", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "DiagnosticReport", "category", element.getCategory(), -1);
    composeCodeableConcept(t, "DiagnosticReport", "code", element.getCode(), -1);
    composeReference(t, "DiagnosticReport", "subject", element.getSubject(), -1);
    composeReference(t, "DiagnosticReport", "encounter", element.getEncounter(), -1);
    composeType(t, "DiagnosticReport", "effective", element.getEffective(), -1);
    composeInstant(t, "DiagnosticReport", "issued", element.getIssuedElement(), -1);
    composeReference(t, "DiagnosticReport", "performer", element.getPerformer(), -1);
    for (int i = 0; i < element.getRequest().size(); i++)
      composeReference(t, "DiagnosticReport", "request", element.getRequest().get(i), i);
    for (int i = 0; i < element.getSpecimen().size(); i++)
      composeReference(t, "DiagnosticReport", "specimen", element.getSpecimen().get(i), i);
    for (int i = 0; i < element.getResult().size(); i++)
      composeReference(t, "DiagnosticReport", "result", element.getResult().get(i), i);
    for (int i = 0; i < element.getImagingStudy().size(); i++)
      composeReference(t, "DiagnosticReport", "imagingStudy", element.getImagingStudy().get(i), i);
    for (int i = 0; i < element.getImage().size(); i++)
      composeDiagnosticReportDiagnosticReportImageComponent(t, "DiagnosticReport", "image", element.getImage().get(i), i);
    composeString(t, "DiagnosticReport", "conclusion", element.getConclusionElement(), -1);
    for (int i = 0; i < element.getCodedDiagnosis().size(); i++)
      composeCodeableConcept(t, "DiagnosticReport", "codedDiagnosis", element.getCodedDiagnosis().get(i), i);
    for (int i = 0; i < element.getPresentedForm().size(); i++)
      composeAttachment(t, "DiagnosticReport", "presentedForm", element.getPresentedForm().get(i), i);
  }

  protected void composeDiagnosticReportDiagnosticReportImageComponent(Complex parent, String parentType, String name, DiagnosticReport.DiagnosticReportImageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "image", name, element, index);
    composeString(t, "DiagnosticReport", "comment", element.getCommentElement(), -1);
    composeReference(t, "DiagnosticReport", "link", element.getLink(), -1);
  }

  protected void composeDocumentManifest(Complex parent, String parentType, String name, DocumentManifest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DocumentManifest", name, element, index);
    composeIdentifier(t, "DocumentManifest", "masterIdentifier", element.getMasterIdentifier(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DocumentManifest", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "DocumentManifest", "subject", element.getSubject(), -1);
    for (int i = 0; i < element.getRecipient().size(); i++)
      composeReference(t, "DocumentManifest", "recipient", element.getRecipient().get(i), i);
    composeCodeableConcept(t, "DocumentManifest", "type", element.getType(), -1);
    for (int i = 0; i < element.getAuthor().size(); i++)
      composeReference(t, "DocumentManifest", "author", element.getAuthor().get(i), i);
    composeDateTime(t, "DocumentManifest", "created", element.getCreatedElement(), -1);
    composeUri(t, "DocumentManifest", "source", element.getSourceElement(), -1);
    composeEnum(t, "DocumentManifest", "status", element.getStatusElement(), -1);
    composeString(t, "DocumentManifest", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getContent().size(); i++)
      composeDocumentManifestDocumentManifestContentComponent(t, "DocumentManifest", "content", element.getContent().get(i), i);
    for (int i = 0; i < element.getRelated().size(); i++)
      composeDocumentManifestDocumentManifestRelatedComponent(t, "DocumentManifest", "related", element.getRelated().get(i), i);
  }

  protected void composeDocumentManifestDocumentManifestContentComponent(Complex parent, String parentType, String name, DocumentManifest.DocumentManifestContentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "content", name, element, index);
    composeType(t, "DocumentManifest", "p", element.getP(), -1);
  }

  protected void composeDocumentManifestDocumentManifestRelatedComponent(Complex parent, String parentType, String name, DocumentManifest.DocumentManifestRelatedComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "related", name, element, index);
    composeIdentifier(t, "DocumentManifest", "identifier", element.getIdentifier(), -1);
    composeReference(t, "DocumentManifest", "ref", element.getRef(), -1);
  }

  protected void composeDocumentReference(Complex parent, String parentType, String name, DocumentReference element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "DocumentReference", name, element, index);
    composeIdentifier(t, "DocumentReference", "masterIdentifier", element.getMasterIdentifier(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "DocumentReference", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "DocumentReference", "subject", element.getSubject(), -1);
    composeCodeableConcept(t, "DocumentReference", "type", element.getType(), -1);
    composeCodeableConcept(t, "DocumentReference", "class", element.getClass_(), -1);
    for (int i = 0; i < element.getAuthor().size(); i++)
      composeReference(t, "DocumentReference", "author", element.getAuthor().get(i), i);
    composeReference(t, "DocumentReference", "custodian", element.getCustodian(), -1);
    composeReference(t, "DocumentReference", "authenticator", element.getAuthenticator(), -1);
    composeDateTime(t, "DocumentReference", "created", element.getCreatedElement(), -1);
    composeInstant(t, "DocumentReference", "indexed", element.getIndexedElement(), -1);
    composeEnum(t, "DocumentReference", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "DocumentReference", "docStatus", element.getDocStatus(), -1);
    for (int i = 0; i < element.getRelatesTo().size(); i++)
      composeDocumentReferenceDocumentReferenceRelatesToComponent(t, "DocumentReference", "relatesTo", element.getRelatesTo().get(i), i);
    composeString(t, "DocumentReference", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getSecurityLabel().size(); i++)
      composeCodeableConcept(t, "DocumentReference", "securityLabel", element.getSecurityLabel().get(i), i);
    for (int i = 0; i < element.getContent().size(); i++)
      composeDocumentReferenceDocumentReferenceContentComponent(t, "DocumentReference", "content", element.getContent().get(i), i);
    composeDocumentReferenceDocumentReferenceContextComponent(t, "DocumentReference", "context", element.getContext(), -1);
  }

  protected void composeDocumentReferenceDocumentReferenceRelatesToComponent(Complex parent, String parentType, String name, DocumentReference.DocumentReferenceRelatesToComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "relatesTo", name, element, index);
    composeEnum(t, "DocumentReference", "code", element.getCodeElement(), -1);
    composeReference(t, "DocumentReference", "target", element.getTarget(), -1);
  }

  protected void composeDocumentReferenceDocumentReferenceContentComponent(Complex parent, String parentType, String name, DocumentReference.DocumentReferenceContentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "content", name, element, index);
    composeAttachment(t, "DocumentReference", "attachment", element.getAttachment(), -1);
    for (int i = 0; i < element.getFormat().size(); i++)
      composeCoding(t, "DocumentReference", "format", element.getFormat().get(i), i);
  }

  protected void composeDocumentReferenceDocumentReferenceContextComponent(Complex parent, String parentType, String name, DocumentReference.DocumentReferenceContextComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "context", name, element, index);
    composeReference(t, "DocumentReference", "encounter", element.getEncounter(), -1);
    for (int i = 0; i < element.getEvent().size(); i++)
      composeCodeableConcept(t, "DocumentReference", "event", element.getEvent().get(i), i);
    composePeriod(t, "DocumentReference", "period", element.getPeriod(), -1);
    composeCodeableConcept(t, "DocumentReference", "facilityType", element.getFacilityType(), -1);
    composeCodeableConcept(t, "DocumentReference", "practiceSetting", element.getPracticeSetting(), -1);
    composeReference(t, "DocumentReference", "sourcePatientInfo", element.getSourcePatientInfo(), -1);
    for (int i = 0; i < element.getRelated().size(); i++)
      composeDocumentReferenceDocumentReferenceContextRelatedComponent(t, "DocumentReference", "related", element.getRelated().get(i), i);
  }

  protected void composeDocumentReferenceDocumentReferenceContextRelatedComponent(Complex parent, String parentType, String name, DocumentReference.DocumentReferenceContextRelatedComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "related", name, element, index);
    composeIdentifier(t, "DocumentReference", "identifier", element.getIdentifier(), -1);
    composeReference(t, "DocumentReference", "ref", element.getRef(), -1);
  }

  protected void composeEligibilityRequest(Complex parent, String parentType, String name, EligibilityRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "EligibilityRequest", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "EligibilityRequest", "identifier", element.getIdentifier().get(i), i);
    composeCoding(t, "EligibilityRequest", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "EligibilityRequest", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "EligibilityRequest", "created", element.getCreatedElement(), -1);
    composeReference(t, "EligibilityRequest", "target", element.getTarget(), -1);
    composeReference(t, "EligibilityRequest", "provider", element.getProvider(), -1);
    composeReference(t, "EligibilityRequest", "organization", element.getOrganization(), -1);
    composeCoding(t, "EligibilityRequest", "priority", element.getPriority(), -1);
    composeReference(t, "EligibilityRequest", "enterer", element.getEnterer(), -1);
    composeReference(t, "EligibilityRequest", "facility", element.getFacility(), -1);
    composeReference(t, "EligibilityRequest", "patient", element.getPatient(), -1);
    composeReference(t, "EligibilityRequest", "coverage", element.getCoverage(), -1);
    composeString(t, "EligibilityRequest", "businessArrangement", element.getBusinessArrangementElement(), -1);
    composeCoding(t, "EligibilityRequest", "relationship", element.getRelationship(), -1);
    composeType(t, "EligibilityRequest", "serviced", element.getServiced(), -1);
    composeCoding(t, "EligibilityRequest", "benefitCategory", element.getBenefitCategory(), -1);
    composeCoding(t, "EligibilityRequest", "benefitSubCategory", element.getBenefitSubCategory(), -1);
  }

  protected void composeEligibilityResponse(Complex parent, String parentType, String name, EligibilityResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "EligibilityResponse", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "EligibilityResponse", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "EligibilityResponse", "request", element.getRequest(), -1);
    composeEnum(t, "EligibilityResponse", "outcome", element.getOutcomeElement(), -1);
    composeString(t, "EligibilityResponse", "disposition", element.getDispositionElement(), -1);
    composeCoding(t, "EligibilityResponse", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "EligibilityResponse", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "EligibilityResponse", "created", element.getCreatedElement(), -1);
    composeReference(t, "EligibilityResponse", "organization", element.getOrganization(), -1);
    composeReference(t, "EligibilityResponse", "requestProvider", element.getRequestProvider(), -1);
    composeReference(t, "EligibilityResponse", "requestOrganization", element.getRequestOrganization(), -1);
    composeBoolean(t, "EligibilityResponse", "inforce", element.getInforceElement(), -1);
    composeReference(t, "EligibilityResponse", "contract", element.getContract(), -1);
    composeCoding(t, "EligibilityResponse", "form", element.getForm(), -1);
    for (int i = 0; i < element.getBenefitBalance().size(); i++)
      composeEligibilityResponseBenefitsComponent(t, "EligibilityResponse", "benefitBalance", element.getBenefitBalance().get(i), i);
    for (int i = 0; i < element.getError().size(); i++)
      composeEligibilityResponseErrorsComponent(t, "EligibilityResponse", "error", element.getError().get(i), i);
  }

  protected void composeEligibilityResponseBenefitsComponent(Complex parent, String parentType, String name, EligibilityResponse.BenefitsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "benefitBalance", name, element, index);
    composeCoding(t, "EligibilityResponse", "category", element.getCategory(), -1);
    composeCoding(t, "EligibilityResponse", "subCategory", element.getSubCategory(), -1);
    composeCoding(t, "EligibilityResponse", "network", element.getNetwork(), -1);
    composeCoding(t, "EligibilityResponse", "unit", element.getUnit(), -1);
    composeCoding(t, "EligibilityResponse", "term", element.getTerm(), -1);
    for (int i = 0; i < element.getFinancial().size(); i++)
      composeEligibilityResponseBenefitComponent(t, "EligibilityResponse", "financial", element.getFinancial().get(i), i);
  }

  protected void composeEligibilityResponseBenefitComponent(Complex parent, String parentType, String name, EligibilityResponse.BenefitComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "financial", name, element, index);
    composeCoding(t, "EligibilityResponse", "type", element.getType(), -1);
    composeType(t, "EligibilityResponse", "benefit", element.getBenefit(), -1);
    composeType(t, "EligibilityResponse", "benefitUsed", element.getBenefitUsed(), -1);
  }

  protected void composeEligibilityResponseErrorsComponent(Complex parent, String parentType, String name, EligibilityResponse.ErrorsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "error", name, element, index);
    composeCoding(t, "EligibilityResponse", "code", element.getCode(), -1);
  }

  protected void composeEncounter(Complex parent, String parentType, String name, Encounter element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Encounter", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Encounter", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "Encounter", "status", element.getStatusElement(), -1);
    for (int i = 0; i < element.getStatusHistory().size(); i++)
      composeEncounterEncounterStatusHistoryComponent(t, "Encounter", "statusHistory", element.getStatusHistory().get(i), i);
    composeEnum(t, "Encounter", "class", element.getClass_Element(), -1);
    for (int i = 0; i < element.getType().size(); i++)
      composeCodeableConcept(t, "Encounter", "type", element.getType().get(i), i);
    composeCodeableConcept(t, "Encounter", "priority", element.getPriority(), -1);
    composeReference(t, "Encounter", "patient", element.getPatient(), -1);
    for (int i = 0; i < element.getEpisodeOfCare().size(); i++)
      composeReference(t, "Encounter", "episodeOfCare", element.getEpisodeOfCare().get(i), i);
    for (int i = 0; i < element.getIncomingReferral().size(); i++)
      composeReference(t, "Encounter", "incomingReferral", element.getIncomingReferral().get(i), i);
    for (int i = 0; i < element.getParticipant().size(); i++)
      composeEncounterEncounterParticipantComponent(t, "Encounter", "participant", element.getParticipant().get(i), i);
    composeReference(t, "Encounter", "appointment", element.getAppointment(), -1);
    composePeriod(t, "Encounter", "period", element.getPeriod(), -1);
    composeQuantity(t, "Encounter", "length", element.getLength(), -1);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCodeableConcept(t, "Encounter", "reason", element.getReason().get(i), i);
    for (int i = 0; i < element.getIndication().size(); i++)
      composeReference(t, "Encounter", "indication", element.getIndication().get(i), i);
    composeEncounterEncounterHospitalizationComponent(t, "Encounter", "hospitalization", element.getHospitalization(), -1);
    for (int i = 0; i < element.getLocation().size(); i++)
      composeEncounterEncounterLocationComponent(t, "Encounter", "location", element.getLocation().get(i), i);
    composeReference(t, "Encounter", "serviceProvider", element.getServiceProvider(), -1);
    composeReference(t, "Encounter", "partOf", element.getPartOf(), -1);
  }

  protected void composeEncounterEncounterStatusHistoryComponent(Complex parent, String parentType, String name, Encounter.EncounterStatusHistoryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "statusHistory", name, element, index);
    composeEnum(t, "Encounter", "status", element.getStatusElement(), -1);
    composePeriod(t, "Encounter", "period", element.getPeriod(), -1);
  }

  protected void composeEncounterEncounterParticipantComponent(Complex parent, String parentType, String name, Encounter.EncounterParticipantComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "participant", name, element, index);
    for (int i = 0; i < element.getType().size(); i++)
      composeCodeableConcept(t, "Encounter", "type", element.getType().get(i), i);
    composePeriod(t, "Encounter", "period", element.getPeriod(), -1);
    composeReference(t, "Encounter", "individual", element.getIndividual(), -1);
  }

  protected void composeEncounterEncounterHospitalizationComponent(Complex parent, String parentType, String name, Encounter.EncounterHospitalizationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "hospitalization", name, element, index);
    composeIdentifier(t, "Encounter", "preAdmissionIdentifier", element.getPreAdmissionIdentifier(), -1);
    composeReference(t, "Encounter", "origin", element.getOrigin(), -1);
    composeCodeableConcept(t, "Encounter", "admitSource", element.getAdmitSource(), -1);
    for (int i = 0; i < element.getAdmittingDiagnosis().size(); i++)
      composeReference(t, "Encounter", "admittingDiagnosis", element.getAdmittingDiagnosis().get(i), i);
    composeCodeableConcept(t, "Encounter", "reAdmission", element.getReAdmission(), -1);
    for (int i = 0; i < element.getDietPreference().size(); i++)
      composeCodeableConcept(t, "Encounter", "dietPreference", element.getDietPreference().get(i), i);
    for (int i = 0; i < element.getSpecialCourtesy().size(); i++)
      composeCodeableConcept(t, "Encounter", "specialCourtesy", element.getSpecialCourtesy().get(i), i);
    for (int i = 0; i < element.getSpecialArrangement().size(); i++)
      composeCodeableConcept(t, "Encounter", "specialArrangement", element.getSpecialArrangement().get(i), i);
    composeReference(t, "Encounter", "destination", element.getDestination(), -1);
    composeCodeableConcept(t, "Encounter", "dischargeDisposition", element.getDischargeDisposition(), -1);
    for (int i = 0; i < element.getDischargeDiagnosis().size(); i++)
      composeReference(t, "Encounter", "dischargeDiagnosis", element.getDischargeDiagnosis().get(i), i);
  }

  protected void composeEncounterEncounterLocationComponent(Complex parent, String parentType, String name, Encounter.EncounterLocationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "location", name, element, index);
    composeReference(t, "Encounter", "location", element.getLocation(), -1);
    composeEnum(t, "Encounter", "status", element.getStatusElement(), -1);
    composePeriod(t, "Encounter", "period", element.getPeriod(), -1);
  }

  protected void composeEnrollmentRequest(Complex parent, String parentType, String name, EnrollmentRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "EnrollmentRequest", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "EnrollmentRequest", "identifier", element.getIdentifier().get(i), i);
    composeCoding(t, "EnrollmentRequest", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "EnrollmentRequest", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "EnrollmentRequest", "created", element.getCreatedElement(), -1);
    composeReference(t, "EnrollmentRequest", "target", element.getTarget(), -1);
    composeReference(t, "EnrollmentRequest", "provider", element.getProvider(), -1);
    composeReference(t, "EnrollmentRequest", "organization", element.getOrganization(), -1);
    composeReference(t, "EnrollmentRequest", "subject", element.getSubject(), -1);
    composeReference(t, "EnrollmentRequest", "coverage", element.getCoverage(), -1);
    composeCoding(t, "EnrollmentRequest", "relationship", element.getRelationship(), -1);
  }

  protected void composeEnrollmentResponse(Complex parent, String parentType, String name, EnrollmentResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "EnrollmentResponse", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "EnrollmentResponse", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "EnrollmentResponse", "request", element.getRequest(), -1);
    composeEnum(t, "EnrollmentResponse", "outcome", element.getOutcomeElement(), -1);
    composeString(t, "EnrollmentResponse", "disposition", element.getDispositionElement(), -1);
    composeCoding(t, "EnrollmentResponse", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "EnrollmentResponse", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "EnrollmentResponse", "created", element.getCreatedElement(), -1);
    composeReference(t, "EnrollmentResponse", "organization", element.getOrganization(), -1);
    composeReference(t, "EnrollmentResponse", "requestProvider", element.getRequestProvider(), -1);
    composeReference(t, "EnrollmentResponse", "requestOrganization", element.getRequestOrganization(), -1);
  }

  protected void composeEpisodeOfCare(Complex parent, String parentType, String name, EpisodeOfCare element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "EpisodeOfCare", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "EpisodeOfCare", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "EpisodeOfCare", "status", element.getStatusElement(), -1);
    for (int i = 0; i < element.getStatusHistory().size(); i++)
      composeEpisodeOfCareEpisodeOfCareStatusHistoryComponent(t, "EpisodeOfCare", "statusHistory", element.getStatusHistory().get(i), i);
    for (int i = 0; i < element.getType().size(); i++)
      composeCodeableConcept(t, "EpisodeOfCare", "type", element.getType().get(i), i);
    for (int i = 0; i < element.getCondition().size(); i++)
      composeReference(t, "EpisodeOfCare", "condition", element.getCondition().get(i), i);
    composeReference(t, "EpisodeOfCare", "patient", element.getPatient(), -1);
    composeReference(t, "EpisodeOfCare", "managingOrganization", element.getManagingOrganization(), -1);
    composePeriod(t, "EpisodeOfCare", "period", element.getPeriod(), -1);
    for (int i = 0; i < element.getReferralRequest().size(); i++)
      composeReference(t, "EpisodeOfCare", "referralRequest", element.getReferralRequest().get(i), i);
    composeReference(t, "EpisodeOfCare", "careManager", element.getCareManager(), -1);
    for (int i = 0; i < element.getCareTeam().size(); i++)
      composeEpisodeOfCareEpisodeOfCareCareTeamComponent(t, "EpisodeOfCare", "careTeam", element.getCareTeam().get(i), i);
  }

  protected void composeEpisodeOfCareEpisodeOfCareStatusHistoryComponent(Complex parent, String parentType, String name, EpisodeOfCare.EpisodeOfCareStatusHistoryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "statusHistory", name, element, index);
    composeEnum(t, "EpisodeOfCare", "status", element.getStatusElement(), -1);
    composePeriod(t, "EpisodeOfCare", "period", element.getPeriod(), -1);
  }

  protected void composeEpisodeOfCareEpisodeOfCareCareTeamComponent(Complex parent, String parentType, String name, EpisodeOfCare.EpisodeOfCareCareTeamComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "careTeam", name, element, index);
    for (int i = 0; i < element.getRole().size(); i++)
      composeCodeableConcept(t, "EpisodeOfCare", "role", element.getRole().get(i), i);
    composePeriod(t, "EpisodeOfCare", "period", element.getPeriod(), -1);
    composeReference(t, "EpisodeOfCare", "member", element.getMember(), -1);
  }

  protected void composeExpansionProfile(Complex parent, String parentType, String name, ExpansionProfile element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ExpansionProfile", name, element, index);
    composeUri(t, "ExpansionProfile", "url", element.getUrlElement(), -1);
    composeIdentifier(t, "ExpansionProfile", "identifier", element.getIdentifier(), -1);
    composeString(t, "ExpansionProfile", "version", element.getVersionElement(), -1);
    composeString(t, "ExpansionProfile", "name", element.getNameElement(), -1);
    composeEnum(t, "ExpansionProfile", "status", element.getStatusElement(), -1);
    composeBoolean(t, "ExpansionProfile", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "ExpansionProfile", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeExpansionProfileExpansionProfileContactComponent(t, "ExpansionProfile", "contact", element.getContact().get(i), i);
    composeDateTime(t, "ExpansionProfile", "date", element.getDateElement(), -1);
    composeString(t, "ExpansionProfile", "description", element.getDescriptionElement(), -1);
    composeExpansionProfileExpansionProfileCodeSystemComponent(t, "ExpansionProfile", "codeSystem", element.getCodeSystem(), -1);
    composeBoolean(t, "ExpansionProfile", "includeDesignations", element.getIncludeDesignationsElement(), -1);
    composeExpansionProfileExpansionProfileDesignationComponent(t, "ExpansionProfile", "designation", element.getDesignation(), -1);
    composeBoolean(t, "ExpansionProfile", "includeDefinition", element.getIncludeDefinitionElement(), -1);
    composeBoolean(t, "ExpansionProfile", "includeInactive", element.getIncludeInactiveElement(), -1);
    composeBoolean(t, "ExpansionProfile", "excludeNested", element.getExcludeNestedElement(), -1);
    composeBoolean(t, "ExpansionProfile", "excludeNotForUI", element.getExcludeNotForUIElement(), -1);
    composeBoolean(t, "ExpansionProfile", "excludePostCoordinated", element.getExcludePostCoordinatedElement(), -1);
    composeCode(t, "ExpansionProfile", "displayLanguage", element.getDisplayLanguageElement(), -1);
    composeBoolean(t, "ExpansionProfile", "limitedExpansion", element.getLimitedExpansionElement(), -1);
  }

  protected void composeExpansionProfileExpansionProfileContactComponent(Complex parent, String parentType, String name, ExpansionProfile.ExpansionProfileContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "ExpansionProfile", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "ExpansionProfile", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeExpansionProfileExpansionProfileCodeSystemComponent(Complex parent, String parentType, String name, ExpansionProfile.ExpansionProfileCodeSystemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeSystem", name, element, index);
    composeExpansionProfileCodeSystemIncludeComponent(t, "ExpansionProfile", "include", element.getInclude(), -1);
    composeExpansionProfileCodeSystemExcludeComponent(t, "ExpansionProfile", "exclude", element.getExclude(), -1);
  }

  protected void composeExpansionProfileCodeSystemIncludeComponent(Complex parent, String parentType, String name, ExpansionProfile.CodeSystemIncludeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "include", name, element, index);
    for (int i = 0; i < element.getCodeSystem().size(); i++)
      composeExpansionProfileCodeSystemIncludeCodeSystemComponent(t, "ExpansionProfile", "codeSystem", element.getCodeSystem().get(i), i);
  }

  protected void composeExpansionProfileCodeSystemIncludeCodeSystemComponent(Complex parent, String parentType, String name, ExpansionProfile.CodeSystemIncludeCodeSystemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeSystem", name, element, index);
    composeUri(t, "ExpansionProfile", "system", element.getSystemElement(), -1);
    composeString(t, "ExpansionProfile", "version", element.getVersionElement(), -1);
  }

  protected void composeExpansionProfileCodeSystemExcludeComponent(Complex parent, String parentType, String name, ExpansionProfile.CodeSystemExcludeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "exclude", name, element, index);
    for (int i = 0; i < element.getCodeSystem().size(); i++)
      composeExpansionProfileCodeSystemExcludeCodeSystemComponent(t, "ExpansionProfile", "codeSystem", element.getCodeSystem().get(i), i);
  }

  protected void composeExpansionProfileCodeSystemExcludeCodeSystemComponent(Complex parent, String parentType, String name, ExpansionProfile.CodeSystemExcludeCodeSystemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeSystem", name, element, index);
    composeUri(t, "ExpansionProfile", "system", element.getSystemElement(), -1);
    composeString(t, "ExpansionProfile", "version", element.getVersionElement(), -1);
  }

  protected void composeExpansionProfileExpansionProfileDesignationComponent(Complex parent, String parentType, String name, ExpansionProfile.ExpansionProfileDesignationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "designation", name, element, index);
    composeExpansionProfileDesignationIncludeComponent(t, "ExpansionProfile", "include", element.getInclude(), -1);
    composeExpansionProfileDesignationExcludeComponent(t, "ExpansionProfile", "exclude", element.getExclude(), -1);
  }

  protected void composeExpansionProfileDesignationIncludeComponent(Complex parent, String parentType, String name, ExpansionProfile.DesignationIncludeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "include", name, element, index);
    for (int i = 0; i < element.getDesignation().size(); i++)
      composeExpansionProfileDesignationIncludeDesignationComponent(t, "ExpansionProfile", "designation", element.getDesignation().get(i), i);
  }

  protected void composeExpansionProfileDesignationIncludeDesignationComponent(Complex parent, String parentType, String name, ExpansionProfile.DesignationIncludeDesignationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "designation", name, element, index);
    composeCode(t, "ExpansionProfile", "language", element.getLanguageElement(), -1);
    composeCoding(t, "ExpansionProfile", "use", element.getUse(), -1);
  }

  protected void composeExpansionProfileDesignationExcludeComponent(Complex parent, String parentType, String name, ExpansionProfile.DesignationExcludeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "exclude", name, element, index);
    for (int i = 0; i < element.getDesignation().size(); i++)
      composeExpansionProfileDesignationExcludeDesignationComponent(t, "ExpansionProfile", "designation", element.getDesignation().get(i), i);
  }

  protected void composeExpansionProfileDesignationExcludeDesignationComponent(Complex parent, String parentType, String name, ExpansionProfile.DesignationExcludeDesignationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "designation", name, element, index);
    composeCode(t, "ExpansionProfile", "language", element.getLanguageElement(), -1);
    composeCoding(t, "ExpansionProfile", "use", element.getUse(), -1);
  }

  protected void composeExplanationOfBenefit(Complex parent, String parentType, String name, ExplanationOfBenefit element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ExplanationOfBenefit", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ExplanationOfBenefit", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "ExplanationOfBenefit", "claim", element.getClaim(), -1);
    composeReference(t, "ExplanationOfBenefit", "claimResponse", element.getClaimResponse(), -1);
    composeCoding(t, "ExplanationOfBenefit", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "ExplanationOfBenefit", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "ExplanationOfBenefit", "created", element.getCreatedElement(), -1);
    composePeriod(t, "ExplanationOfBenefit", "billablePeriod", element.getBillablePeriod(), -1);
    composeString(t, "ExplanationOfBenefit", "disposition", element.getDispositionElement(), -1);
    composeReference(t, "ExplanationOfBenefit", "provider", element.getProvider(), -1);
    composeReference(t, "ExplanationOfBenefit", "organization", element.getOrganization(), -1);
    composeReference(t, "ExplanationOfBenefit", "facility", element.getFacility(), -1);
    for (int i = 0; i < element.getRelatedClaim().size(); i++)
      composeReference(t, "ExplanationOfBenefit", "relatedClaim", element.getRelatedClaim().get(i), i);
    composeReference(t, "ExplanationOfBenefit", "prescription", element.getPrescription(), -1);
    composeReference(t, "ExplanationOfBenefit", "originalPrescription", element.getOriginalPrescription(), -1);
    composeExplanationOfBenefitPayeeComponent(t, "ExplanationOfBenefit", "payee", element.getPayee(), -1);
    composeReference(t, "ExplanationOfBenefit", "referral", element.getReferral(), -1);
    for (int i = 0; i < element.getDiagnosis().size(); i++)
      composeExplanationOfBenefitDiagnosisComponent(t, "ExplanationOfBenefit", "diagnosis", element.getDiagnosis().get(i), i);
    for (int i = 0; i < element.getSpecialCondition().size(); i++)
      composeCoding(t, "ExplanationOfBenefit", "specialCondition", element.getSpecialCondition().get(i), i);
    composeReference(t, "ExplanationOfBenefit", "patient", element.getPatient(), -1);
    composePositiveInt(t, "ExplanationOfBenefit", "precedence", element.getPrecedenceElement(), -1);
    composeExplanationOfBenefitCoverageComponent(t, "ExplanationOfBenefit", "coverage", element.getCoverage(), -1);
    for (int i = 0; i < element.getException().size(); i++)
      composeCoding(t, "ExplanationOfBenefit", "exception", element.getException().get(i), i);
    composeString(t, "ExplanationOfBenefit", "school", element.getSchoolElement(), -1);
    composeDate(t, "ExplanationOfBenefit", "accidentDate", element.getAccidentDateElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "accidentType", element.getAccidentType(), -1);
    composeType(t, "ExplanationOfBenefit", "accidentLocation", element.getAccidentLocation(), -1);
    for (int i = 0; i < element.getInterventionException().size(); i++)
      composeCoding(t, "ExplanationOfBenefit", "interventionException", element.getInterventionException().get(i), i);
    composeType(t, "ExplanationOfBenefit", "onset", element.getOnset(), -1);
    composePeriod(t, "ExplanationOfBenefit", "employmentImpacted", element.getEmploymentImpacted(), -1);
    composePeriod(t, "ExplanationOfBenefit", "hospitalization", element.getHospitalization(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeExplanationOfBenefitItemsComponent(t, "ExplanationOfBenefit", "item", element.getItem().get(i), i);
    for (int i = 0; i < element.getAddItem().size(); i++)
      composeExplanationOfBenefitAddedItemComponent(t, "ExplanationOfBenefit", "addItem", element.getAddItem().get(i), i);
    composeQuantity(t, "ExplanationOfBenefit", "claimTotal", element.getClaimTotal(), -1);
    for (int i = 0; i < element.getMissingTeeth().size(); i++)
      composeExplanationOfBenefitMissingTeethComponent(t, "ExplanationOfBenefit", "missingTeeth", element.getMissingTeeth().get(i), i);
    composeQuantity(t, "ExplanationOfBenefit", "unallocDeductable", element.getUnallocDeductable(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "totalBenefit", element.getTotalBenefit(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "paymentAdjustment", element.getPaymentAdjustment(), -1);
    composeCoding(t, "ExplanationOfBenefit", "paymentAdjustmentReason", element.getPaymentAdjustmentReason(), -1);
    composeDate(t, "ExplanationOfBenefit", "paymentDate", element.getPaymentDateElement(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "paymentAmount", element.getPaymentAmount(), -1);
    composeIdentifier(t, "ExplanationOfBenefit", "paymentRef", element.getPaymentRef(), -1);
    composeCoding(t, "ExplanationOfBenefit", "reserved", element.getReserved(), -1);
    composeCoding(t, "ExplanationOfBenefit", "form", element.getForm(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeExplanationOfBenefitNotesComponent(t, "ExplanationOfBenefit", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getBenefitBalance().size(); i++)
      composeExplanationOfBenefitBenefitBalanceComponent(t, "ExplanationOfBenefit", "benefitBalance", element.getBenefitBalance().get(i), i);
  }

  protected void composeExplanationOfBenefitPayeeComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.PayeeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "payee", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "type", element.getType(), -1);
    composeReference(t, "ExplanationOfBenefit", "provider", element.getProvider(), -1);
    composeReference(t, "ExplanationOfBenefit", "organization", element.getOrganization(), -1);
    composeReference(t, "ExplanationOfBenefit", "person", element.getPerson(), -1);
  }

  protected void composeExplanationOfBenefitDiagnosisComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.DiagnosisComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "diagnosis", name, element, index);
    composePositiveInt(t, "ExplanationOfBenefit", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "diagnosis", element.getDiagnosis(), -1);
  }

  protected void composeExplanationOfBenefitCoverageComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.CoverageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "coverage", name, element, index);
    composeReference(t, "ExplanationOfBenefit", "coverage", element.getCoverage(), -1);
    composeCoding(t, "ExplanationOfBenefit", "relationship", element.getRelationship(), -1);
    for (int i = 0; i < element.getPreAuthRef().size(); i++)
      composeString(t, "ExplanationOfBenefit", "preAuthRef", element.getPreAuthRef().get(i), i);
  }

  protected void composeExplanationOfBenefitItemsComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.ItemsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composePositiveInt(t, "ExplanationOfBenefit", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "type", element.getType(), -1);
    composeReference(t, "ExplanationOfBenefit", "provider", element.getProvider(), -1);
    for (int i = 0; i < element.getDiagnosisLinkId().size(); i++)
      composePositiveInt(t, "ExplanationOfBenefit", "diagnosisLinkId", element.getDiagnosisLinkId().get(i), i);
    composeCoding(t, "ExplanationOfBenefit", "service", element.getService(), -1);
    composeType(t, "ExplanationOfBenefit", "serviced", element.getServiced(), -1);
    composeCoding(t, "ExplanationOfBenefit", "place", element.getPlace(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "points", element.getPointsElement(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "net", element.getNet(), -1);
    composeCoding(t, "ExplanationOfBenefit", "udi", element.getUdi(), -1);
    composeCoding(t, "ExplanationOfBenefit", "bodySite", element.getBodySite(), -1);
    for (int i = 0; i < element.getSubSite().size(); i++)
      composeCoding(t, "ExplanationOfBenefit", "subSite", element.getSubSite().get(i), i);
    for (int i = 0; i < element.getModifier().size(); i++)
      composeCoding(t, "ExplanationOfBenefit", "modifier", element.getModifier().get(i), i);
    for (int i = 0; i < element.getNoteNumber().size(); i++)
      composePositiveInt(t, "ExplanationOfBenefit", "noteNumber", element.getNoteNumber().get(i), i);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeExplanationOfBenefitItemAdjudicationComponent(t, "ExplanationOfBenefit", "adjudication", element.getAdjudication().get(i), i);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeExplanationOfBenefitDetailComponent(t, "ExplanationOfBenefit", "detail", element.getDetail().get(i), i);
    composeExplanationOfBenefitProsthesisComponent(t, "ExplanationOfBenefit", "prosthesis", element.getProsthesis(), -1);
  }

  protected void composeExplanationOfBenefitItemAdjudicationComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.ItemAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "category", element.getCategory(), -1);
    composeCoding(t, "ExplanationOfBenefit", "reason", element.getReason(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "amount", element.getAmount(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "value", element.getValueElement(), -1);
  }

  protected void composeExplanationOfBenefitDetailComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.DetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composePositiveInt(t, "ExplanationOfBenefit", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "type", element.getType(), -1);
    composeCoding(t, "ExplanationOfBenefit", "service", element.getService(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "points", element.getPointsElement(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "net", element.getNet(), -1);
    composeCoding(t, "ExplanationOfBenefit", "udi", element.getUdi(), -1);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeExplanationOfBenefitDetailAdjudicationComponent(t, "ExplanationOfBenefit", "adjudication", element.getAdjudication().get(i), i);
    for (int i = 0; i < element.getSubDetail().size(); i++)
      composeExplanationOfBenefitSubDetailComponent(t, "ExplanationOfBenefit", "subDetail", element.getSubDetail().get(i), i);
  }

  protected void composeExplanationOfBenefitDetailAdjudicationComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.DetailAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "code", element.getCode(), -1);
    composeCoding(t, "ExplanationOfBenefit", "reason", element.getReason(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "amount", element.getAmount(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "value", element.getValueElement(), -1);
  }

  protected void composeExplanationOfBenefitSubDetailComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.SubDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "subDetail", name, element, index);
    composePositiveInt(t, "ExplanationOfBenefit", "sequence", element.getSequenceElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "type", element.getType(), -1);
    composeCoding(t, "ExplanationOfBenefit", "service", element.getService(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "unitPrice", element.getUnitPrice(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "factor", element.getFactorElement(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "points", element.getPointsElement(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "net", element.getNet(), -1);
    composeCoding(t, "ExplanationOfBenefit", "udi", element.getUdi(), -1);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeExplanationOfBenefitSubDetailAdjudicationComponent(t, "ExplanationOfBenefit", "adjudication", element.getAdjudication().get(i), i);
  }

  protected void composeExplanationOfBenefitSubDetailAdjudicationComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.SubDetailAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "code", element.getCode(), -1);
    composeCoding(t, "ExplanationOfBenefit", "reason", element.getReason(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "amount", element.getAmount(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "value", element.getValueElement(), -1);
  }

  protected void composeExplanationOfBenefitProsthesisComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.ProsthesisComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "prosthesis", name, element, index);
    composeBoolean(t, "ExplanationOfBenefit", "initial", element.getInitialElement(), -1);
    composeDate(t, "ExplanationOfBenefit", "priorDate", element.getPriorDateElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "priorMaterial", element.getPriorMaterial(), -1);
  }

  protected void composeExplanationOfBenefitAddedItemComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.AddedItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "addItem", name, element, index);
    for (int i = 0; i < element.getSequenceLinkId().size(); i++)
      composePositiveInt(t, "ExplanationOfBenefit", "sequenceLinkId", element.getSequenceLinkId().get(i), i);
    composeCoding(t, "ExplanationOfBenefit", "service", element.getService(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "fee", element.getFee(), -1);
    for (int i = 0; i < element.getNoteNumberLinkId().size(); i++)
      composePositiveInt(t, "ExplanationOfBenefit", "noteNumberLinkId", element.getNoteNumberLinkId().get(i), i);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeExplanationOfBenefitAddedItemAdjudicationComponent(t, "ExplanationOfBenefit", "adjudication", element.getAdjudication().get(i), i);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeExplanationOfBenefitAddedItemsDetailComponent(t, "ExplanationOfBenefit", "detail", element.getDetail().get(i), i);
  }

  protected void composeExplanationOfBenefitAddedItemAdjudicationComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.AddedItemAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "code", element.getCode(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "amount", element.getAmount(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "value", element.getValueElement(), -1);
  }

  protected void composeExplanationOfBenefitAddedItemsDetailComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.AddedItemsDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "service", element.getService(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "fee", element.getFee(), -1);
    for (int i = 0; i < element.getAdjudication().size(); i++)
      composeExplanationOfBenefitAddedItemDetailAdjudicationComponent(t, "ExplanationOfBenefit", "adjudication", element.getAdjudication().get(i), i);
  }

  protected void composeExplanationOfBenefitAddedItemDetailAdjudicationComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.AddedItemDetailAdjudicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "adjudication", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "code", element.getCode(), -1);
    composeQuantity(t, "ExplanationOfBenefit", "amount", element.getAmount(), -1);
    composeDecimal(t, "ExplanationOfBenefit", "value", element.getValueElement(), -1);
  }

  protected void composeExplanationOfBenefitMissingTeethComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.MissingTeethComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "missingTeeth", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "tooth", element.getTooth(), -1);
    composeCoding(t, "ExplanationOfBenefit", "reason", element.getReason(), -1);
    composeDate(t, "ExplanationOfBenefit", "extractionDate", element.getExtractionDateElement(), -1);
  }

  protected void composeExplanationOfBenefitNotesComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.NotesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "note", name, element, index);
    composePositiveInt(t, "ExplanationOfBenefit", "number", element.getNumberElement(), -1);
    composeCoding(t, "ExplanationOfBenefit", "type", element.getType(), -1);
    composeString(t, "ExplanationOfBenefit", "text", element.getTextElement(), -1);
  }

  protected void composeExplanationOfBenefitBenefitBalanceComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.BenefitBalanceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "benefitBalance", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "category", element.getCategory(), -1);
    composeCoding(t, "ExplanationOfBenefit", "subCategory", element.getSubCategory(), -1);
    composeCoding(t, "ExplanationOfBenefit", "network", element.getNetwork(), -1);
    composeCoding(t, "ExplanationOfBenefit", "unit", element.getUnit(), -1);
    composeCoding(t, "ExplanationOfBenefit", "term", element.getTerm(), -1);
    for (int i = 0; i < element.getFinancial().size(); i++)
      composeExplanationOfBenefitBenefitComponent(t, "ExplanationOfBenefit", "financial", element.getFinancial().get(i), i);
  }

  protected void composeExplanationOfBenefitBenefitComponent(Complex parent, String parentType, String name, ExplanationOfBenefit.BenefitComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "financial", name, element, index);
    composeCoding(t, "ExplanationOfBenefit", "type", element.getType(), -1);
    composeType(t, "ExplanationOfBenefit", "benefit", element.getBenefit(), -1);
    composeType(t, "ExplanationOfBenefit", "benefitUsed", element.getBenefitUsed(), -1);
  }

  protected void composeFamilyMemberHistory(Complex parent, String parentType, String name, FamilyMemberHistory element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "FamilyMemberHistory", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "FamilyMemberHistory", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "FamilyMemberHistory", "patient", element.getPatient(), -1);
    composeDateTime(t, "FamilyMemberHistory", "date", element.getDateElement(), -1);
    composeEnum(t, "FamilyMemberHistory", "status", element.getStatusElement(), -1);
    composeString(t, "FamilyMemberHistory", "name", element.getNameElement(), -1);
    composeCodeableConcept(t, "FamilyMemberHistory", "relationship", element.getRelationship(), -1);
    composeEnum(t, "FamilyMemberHistory", "gender", element.getGenderElement(), -1);
    composeType(t, "FamilyMemberHistory", "born", element.getBorn(), -1);
    composeType(t, "FamilyMemberHistory", "age", element.getAge(), -1);
    composeType(t, "FamilyMemberHistory", "deceased", element.getDeceased(), -1);
    composeAnnotation(t, "FamilyMemberHistory", "note", element.getNote(), -1);
    for (int i = 0; i < element.getCondition().size(); i++)
      composeFamilyMemberHistoryFamilyMemberHistoryConditionComponent(t, "FamilyMemberHistory", "condition", element.getCondition().get(i), i);
  }

  protected void composeFamilyMemberHistoryFamilyMemberHistoryConditionComponent(Complex parent, String parentType, String name, FamilyMemberHistory.FamilyMemberHistoryConditionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "condition", name, element, index);
    composeCodeableConcept(t, "FamilyMemberHistory", "code", element.getCode(), -1);
    composeCodeableConcept(t, "FamilyMemberHistory", "outcome", element.getOutcome(), -1);
    composeType(t, "FamilyMemberHistory", "onset", element.getOnset(), -1);
    composeAnnotation(t, "FamilyMemberHistory", "note", element.getNote(), -1);
  }

  protected void composeFlag(Complex parent, String parentType, String name, Flag element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Flag", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Flag", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "Flag", "category", element.getCategory(), -1);
    composeEnum(t, "Flag", "status", element.getStatusElement(), -1);
    composePeriod(t, "Flag", "period", element.getPeriod(), -1);
    composeReference(t, "Flag", "subject", element.getSubject(), -1);
    composeReference(t, "Flag", "encounter", element.getEncounter(), -1);
    composeReference(t, "Flag", "author", element.getAuthor(), -1);
    composeCodeableConcept(t, "Flag", "code", element.getCode(), -1);
  }

  protected void composeGoal(Complex parent, String parentType, String name, Goal element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Goal", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Goal", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "Goal", "subject", element.getSubject(), -1);
    composeType(t, "Goal", "start", element.getStart(), -1);
    composeType(t, "Goal", "target", element.getTarget(), -1);
    for (int i = 0; i < element.getCategory().size(); i++)
      composeCodeableConcept(t, "Goal", "category", element.getCategory().get(i), i);
    composeString(t, "Goal", "description", element.getDescriptionElement(), -1);
    composeEnum(t, "Goal", "status", element.getStatusElement(), -1);
    composeDate(t, "Goal", "statusDate", element.getStatusDateElement(), -1);
    composeCodeableConcept(t, "Goal", "statusReason", element.getStatusReason(), -1);
    composeReference(t, "Goal", "author", element.getAuthor(), -1);
    composeCodeableConcept(t, "Goal", "priority", element.getPriority(), -1);
    for (int i = 0; i < element.getAddresses().size(); i++)
      composeReference(t, "Goal", "addresses", element.getAddresses().get(i), i);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "Goal", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getOutcome().size(); i++)
      composeGoalGoalOutcomeComponent(t, "Goal", "outcome", element.getOutcome().get(i), i);
  }

  protected void composeGoalGoalOutcomeComponent(Complex parent, String parentType, String name, Goal.GoalOutcomeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "outcome", name, element, index);
    composeType(t, "Goal", "result", element.getResult(), -1);
  }

  protected void composeGroup(Complex parent, String parentType, String name, Group element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Group", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Group", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "Group", "type", element.getTypeElement(), -1);
    composeBoolean(t, "Group", "actual", element.getActualElement(), -1);
    composeBoolean(t, "Group", "active", element.getActiveElement(), -1);
    composeCodeableConcept(t, "Group", "code", element.getCode(), -1);
    composeString(t, "Group", "name", element.getNameElement(), -1);
    composeUnsignedInt(t, "Group", "quantity", element.getQuantityElement(), -1);
    for (int i = 0; i < element.getCharacteristic().size(); i++)
      composeGroupGroupCharacteristicComponent(t, "Group", "characteristic", element.getCharacteristic().get(i), i);
    for (int i = 0; i < element.getMember().size(); i++)
      composeGroupGroupMemberComponent(t, "Group", "member", element.getMember().get(i), i);
  }

  protected void composeGroupGroupCharacteristicComponent(Complex parent, String parentType, String name, Group.GroupCharacteristicComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "characteristic", name, element, index);
    composeCodeableConcept(t, "Group", "code", element.getCode(), -1);
    composeType(t, "Group", "value", element.getValue(), -1);
    composeBoolean(t, "Group", "exclude", element.getExcludeElement(), -1);
    composePeriod(t, "Group", "period", element.getPeriod(), -1);
  }

  protected void composeGroupGroupMemberComponent(Complex parent, String parentType, String name, Group.GroupMemberComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "member", name, element, index);
    composeReference(t, "Group", "entity", element.getEntity(), -1);
    composePeriod(t, "Group", "period", element.getPeriod(), -1);
    composeBoolean(t, "Group", "inactive", element.getInactiveElement(), -1);
  }

  protected void composeGuidanceResponse(Complex parent, String parentType, String name, GuidanceResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "GuidanceResponse", name, element, index);
    composeString(t, "GuidanceResponse", "requestId", element.getRequestIdElement(), -1);
    composeReference(t, "GuidanceResponse", "module", element.getModule(), -1);
    composeEnum(t, "GuidanceResponse", "status", element.getStatusElement(), -1);
    for (int i = 0; i < element.getEvaluationMessage().size(); i++)
      composeReference(t, "GuidanceResponse", "evaluationMessage", element.getEvaluationMessage().get(i), i);
    composeReference(t, "GuidanceResponse", "outputParameters", element.getOutputParameters(), -1);
    for (int i = 0; i < element.getAction().size(); i++)
      composeGuidanceResponseGuidanceResponseActionComponent(t, "GuidanceResponse", "action", element.getAction().get(i), i);
  }

  protected void composeGuidanceResponseGuidanceResponseActionComponent(Complex parent, String parentType, String name, GuidanceResponse.GuidanceResponseActionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "action", name, element, index);
    composeIdentifier(t, "GuidanceResponse", "actionIdentifier", element.getActionIdentifier(), -1);
    composeString(t, "GuidanceResponse", "number", element.getNumberElement(), -1);
    for (int i = 0; i < element.getSupportingEvidence().size(); i++)
      composeAttachment(t, "GuidanceResponse", "supportingEvidence", element.getSupportingEvidence().get(i), i);
    for (int i = 0; i < element.getDocumentation().size(); i++)
      composeAttachment(t, "GuidanceResponse", "documentation", element.getDocumentation().get(i), i);
    for (int i = 0; i < element.getParticipant().size(); i++)
      composeReference(t, "GuidanceResponse", "participant", element.getParticipant().get(i), i);
    composeString(t, "GuidanceResponse", "title", element.getTitleElement(), -1);
    composeString(t, "GuidanceResponse", "description", element.getDescriptionElement(), -1);
    composeString(t, "GuidanceResponse", "textEquivalent", element.getTextEquivalentElement(), -1);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCodeableConcept(t, "GuidanceResponse", "concept", element.getConcept().get(i), i);
    composeEnum(t, "GuidanceResponse", "type", element.getTypeElement(), -1);
    composeReference(t, "GuidanceResponse", "resource", element.getResource(), -1);
    for (int i = 0; i < element.getActions().size(); i++)
      composeGuidanceResponseGuidanceResponseActionComponent(t, "GuidanceResponse", "actions", element.getActions().get(i), i);
  }

  protected void composeHealthcareService(Complex parent, String parentType, String name, HealthcareService element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "HealthcareService", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "HealthcareService", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "HealthcareService", "providedBy", element.getProvidedBy(), -1);
    composeCodeableConcept(t, "HealthcareService", "serviceCategory", element.getServiceCategory(), -1);
    for (int i = 0; i < element.getServiceType().size(); i++)
      composeHealthcareServiceServiceTypeComponent(t, "HealthcareService", "serviceType", element.getServiceType().get(i), i);
    composeReference(t, "HealthcareService", "location", element.getLocation(), -1);
    composeString(t, "HealthcareService", "serviceName", element.getServiceNameElement(), -1);
    composeString(t, "HealthcareService", "comment", element.getCommentElement(), -1);
    composeString(t, "HealthcareService", "extraDetails", element.getExtraDetailsElement(), -1);
    composeAttachment(t, "HealthcareService", "photo", element.getPhoto(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "HealthcareService", "telecom", element.getTelecom().get(i), i);
    for (int i = 0; i < element.getCoverageArea().size(); i++)
      composeReference(t, "HealthcareService", "coverageArea", element.getCoverageArea().get(i), i);
    for (int i = 0; i < element.getServiceProvisionCode().size(); i++)
      composeCodeableConcept(t, "HealthcareService", "serviceProvisionCode", element.getServiceProvisionCode().get(i), i);
    composeCodeableConcept(t, "HealthcareService", "eligibility", element.getEligibility(), -1);
    composeString(t, "HealthcareService", "eligibilityNote", element.getEligibilityNoteElement(), -1);
    for (int i = 0; i < element.getProgramName().size(); i++)
      composeString(t, "HealthcareService", "programName", element.getProgramName().get(i), i);
    for (int i = 0; i < element.getCharacteristic().size(); i++)
      composeCodeableConcept(t, "HealthcareService", "characteristic", element.getCharacteristic().get(i), i);
    for (int i = 0; i < element.getReferralMethod().size(); i++)
      composeCodeableConcept(t, "HealthcareService", "referralMethod", element.getReferralMethod().get(i), i);
    composeString(t, "HealthcareService", "publicKey", element.getPublicKeyElement(), -1);
    composeBoolean(t, "HealthcareService", "appointmentRequired", element.getAppointmentRequiredElement(), -1);
    for (int i = 0; i < element.getAvailableTime().size(); i++)
      composeHealthcareServiceHealthcareServiceAvailableTimeComponent(t, "HealthcareService", "availableTime", element.getAvailableTime().get(i), i);
    for (int i = 0; i < element.getNotAvailable().size(); i++)
      composeHealthcareServiceHealthcareServiceNotAvailableComponent(t, "HealthcareService", "notAvailable", element.getNotAvailable().get(i), i);
    composeString(t, "HealthcareService", "availabilityExceptions", element.getAvailabilityExceptionsElement(), -1);
  }

  protected void composeHealthcareServiceServiceTypeComponent(Complex parent, String parentType, String name, HealthcareService.ServiceTypeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "serviceType", name, element, index);
    composeCodeableConcept(t, "HealthcareService", "type", element.getType(), -1);
    for (int i = 0; i < element.getSpecialty().size(); i++)
      composeCodeableConcept(t, "HealthcareService", "specialty", element.getSpecialty().get(i), i);
  }

  protected void composeHealthcareServiceHealthcareServiceAvailableTimeComponent(Complex parent, String parentType, String name, HealthcareService.HealthcareServiceAvailableTimeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "availableTime", name, element, index);
    for (int i = 0; i < element.getDaysOfWeek().size(); i++)
      composeEnum(t, "HealthcareService", "daysOfWeek", element.getDaysOfWeek().get(i), i);
    composeBoolean(t, "HealthcareService", "allDay", element.getAllDayElement(), -1);
    composeTime(t, "HealthcareService", "availableStartTime", element.getAvailableStartTimeElement(), -1);
    composeTime(t, "HealthcareService", "availableEndTime", element.getAvailableEndTimeElement(), -1);
  }

  protected void composeHealthcareServiceHealthcareServiceNotAvailableComponent(Complex parent, String parentType, String name, HealthcareService.HealthcareServiceNotAvailableComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "notAvailable", name, element, index);
    composeString(t, "HealthcareService", "description", element.getDescriptionElement(), -1);
    composePeriod(t, "HealthcareService", "during", element.getDuring(), -1);
  }

  protected void composeImagingObjectSelection(Complex parent, String parentType, String name, ImagingObjectSelection element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ImagingObjectSelection", name, element, index);
    composeOid(t, "ImagingObjectSelection", "uid", element.getUidElement(), -1);
    composeReference(t, "ImagingObjectSelection", "patient", element.getPatient(), -1);
    composeDateTime(t, "ImagingObjectSelection", "authoringTime", element.getAuthoringTimeElement(), -1);
    composeReference(t, "ImagingObjectSelection", "author", element.getAuthor(), -1);
    composeCodeableConcept(t, "ImagingObjectSelection", "title", element.getTitle(), -1);
    composeString(t, "ImagingObjectSelection", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getStudy().size(); i++)
      composeImagingObjectSelectionStudyComponent(t, "ImagingObjectSelection", "study", element.getStudy().get(i), i);
  }

  protected void composeImagingObjectSelectionStudyComponent(Complex parent, String parentType, String name, ImagingObjectSelection.StudyComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "study", name, element, index);
    composeOid(t, "ImagingObjectSelection", "uid", element.getUidElement(), -1);
    composeUri(t, "ImagingObjectSelection", "url", element.getUrlElement(), -1);
    composeReference(t, "ImagingObjectSelection", "imagingStudy", element.getImagingStudy(), -1);
    for (int i = 0; i < element.getSeries().size(); i++)
      composeImagingObjectSelectionSeriesComponent(t, "ImagingObjectSelection", "series", element.getSeries().get(i), i);
  }

  protected void composeImagingObjectSelectionSeriesComponent(Complex parent, String parentType, String name, ImagingObjectSelection.SeriesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "series", name, element, index);
    composeOid(t, "ImagingObjectSelection", "uid", element.getUidElement(), -1);
    composeUri(t, "ImagingObjectSelection", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getInstance().size(); i++)
      composeImagingObjectSelectionInstanceComponent(t, "ImagingObjectSelection", "instance", element.getInstance().get(i), i);
  }

  protected void composeImagingObjectSelectionInstanceComponent(Complex parent, String parentType, String name, ImagingObjectSelection.InstanceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "instance", name, element, index);
    composeOid(t, "ImagingObjectSelection", "sopClass", element.getSopClassElement(), -1);
    composeOid(t, "ImagingObjectSelection", "uid", element.getUidElement(), -1);
    composeUri(t, "ImagingObjectSelection", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getFrame().size(); i++)
      composeImagingObjectSelectionFramesComponent(t, "ImagingObjectSelection", "frame", element.getFrame().get(i), i);
  }

  protected void composeImagingObjectSelectionFramesComponent(Complex parent, String parentType, String name, ImagingObjectSelection.FramesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "frame", name, element, index);
    for (int i = 0; i < element.getNumber().size(); i++)
      composeUnsignedInt(t, "ImagingObjectSelection", "number", element.getNumber().get(i), i);
    composeUri(t, "ImagingObjectSelection", "url", element.getUrlElement(), -1);
  }

  protected void composeImagingStudy(Complex parent, String parentType, String name, ImagingStudy element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ImagingStudy", name, element, index);
    composeOid(t, "ImagingStudy", "uid", element.getUidElement(), -1);
    composeIdentifier(t, "ImagingStudy", "accession", element.getAccession(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ImagingStudy", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "ImagingStudy", "availability", element.getAvailabilityElement(), -1);
    for (int i = 0; i < element.getModalityList().size(); i++)
      composeCoding(t, "ImagingStudy", "modalityList", element.getModalityList().get(i), i);
    composeReference(t, "ImagingStudy", "patient", element.getPatient(), -1);
    composeDateTime(t, "ImagingStudy", "started", element.getStartedElement(), -1);
    for (int i = 0; i < element.getOrder().size(); i++)
      composeReference(t, "ImagingStudy", "order", element.getOrder().get(i), i);
    composeReference(t, "ImagingStudy", "referrer", element.getReferrer(), -1);
    composeReference(t, "ImagingStudy", "interpreter", element.getInterpreter(), -1);
    composeUri(t, "ImagingStudy", "url", element.getUrlElement(), -1);
    composeUnsignedInt(t, "ImagingStudy", "numberOfSeries", element.getNumberOfSeriesElement(), -1);
    composeUnsignedInt(t, "ImagingStudy", "numberOfInstances", element.getNumberOfInstancesElement(), -1);
    for (int i = 0; i < element.getProcedure().size(); i++)
      composeReference(t, "ImagingStudy", "procedure", element.getProcedure().get(i), i);
    composeString(t, "ImagingStudy", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getSeries().size(); i++)
      composeImagingStudyImagingStudySeriesComponent(t, "ImagingStudy", "series", element.getSeries().get(i), i);
  }

  protected void composeImagingStudyImagingStudySeriesComponent(Complex parent, String parentType, String name, ImagingStudy.ImagingStudySeriesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "series", name, element, index);
    composeOid(t, "ImagingStudy", "uid", element.getUidElement(), -1);
    composeUnsignedInt(t, "ImagingStudy", "number", element.getNumberElement(), -1);
    composeCoding(t, "ImagingStudy", "modality", element.getModality(), -1);
    composeString(t, "ImagingStudy", "description", element.getDescriptionElement(), -1);
    composeUnsignedInt(t, "ImagingStudy", "numberOfInstances", element.getNumberOfInstancesElement(), -1);
    composeEnum(t, "ImagingStudy", "availability", element.getAvailabilityElement(), -1);
    composeUri(t, "ImagingStudy", "url", element.getUrlElement(), -1);
    composeCoding(t, "ImagingStudy", "bodySite", element.getBodySite(), -1);
    composeCoding(t, "ImagingStudy", "laterality", element.getLaterality(), -1);
    composeDateTime(t, "ImagingStudy", "started", element.getStartedElement(), -1);
    for (int i = 0; i < element.getInstance().size(); i++)
      composeImagingStudyImagingStudySeriesInstanceComponent(t, "ImagingStudy", "instance", element.getInstance().get(i), i);
  }

  protected void composeImagingStudyImagingStudySeriesInstanceComponent(Complex parent, String parentType, String name, ImagingStudy.ImagingStudySeriesInstanceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "instance", name, element, index);
    composeOid(t, "ImagingStudy", "uid", element.getUidElement(), -1);
    composeUnsignedInt(t, "ImagingStudy", "number", element.getNumberElement(), -1);
    composeOid(t, "ImagingStudy", "sopClass", element.getSopClassElement(), -1);
    composeString(t, "ImagingStudy", "type", element.getTypeElement(), -1);
    composeString(t, "ImagingStudy", "title", element.getTitleElement(), -1);
    for (int i = 0; i < element.getContent().size(); i++)
      composeAttachment(t, "ImagingStudy", "content", element.getContent().get(i), i);
  }

  protected void composeImmunization(Complex parent, String parentType, String name, Immunization element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Immunization", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Immunization", "identifier", element.getIdentifier().get(i), i);
    composeCode(t, "Immunization", "status", element.getStatusElement(), -1);
    composeDateTime(t, "Immunization", "date", element.getDateElement(), -1);
    composeCodeableConcept(t, "Immunization", "vaccineCode", element.getVaccineCode(), -1);
    composeReference(t, "Immunization", "patient", element.getPatient(), -1);
    composeBoolean(t, "Immunization", "wasNotGiven", element.getWasNotGivenElement(), -1);
    composeBoolean(t, "Immunization", "reported", element.getReportedElement(), -1);
    composeReference(t, "Immunization", "performer", element.getPerformer(), -1);
    composeReference(t, "Immunization", "requester", element.getRequester(), -1);
    composeReference(t, "Immunization", "encounter", element.getEncounter(), -1);
    composeReference(t, "Immunization", "manufacturer", element.getManufacturer(), -1);
    composeReference(t, "Immunization", "location", element.getLocation(), -1);
    composeString(t, "Immunization", "lotNumber", element.getLotNumberElement(), -1);
    composeDate(t, "Immunization", "expirationDate", element.getExpirationDateElement(), -1);
    composeCodeableConcept(t, "Immunization", "site", element.getSite(), -1);
    composeCodeableConcept(t, "Immunization", "route", element.getRoute(), -1);
    composeQuantity(t, "Immunization", "doseQuantity", element.getDoseQuantity(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "Immunization", "note", element.getNote().get(i), i);
    composeImmunizationImmunizationExplanationComponent(t, "Immunization", "explanation", element.getExplanation(), -1);
    for (int i = 0; i < element.getReaction().size(); i++)
      composeImmunizationImmunizationReactionComponent(t, "Immunization", "reaction", element.getReaction().get(i), i);
    for (int i = 0; i < element.getVaccinationProtocol().size(); i++)
      composeImmunizationImmunizationVaccinationProtocolComponent(t, "Immunization", "vaccinationProtocol", element.getVaccinationProtocol().get(i), i);
  }

  protected void composeImmunizationImmunizationExplanationComponent(Complex parent, String parentType, String name, Immunization.ImmunizationExplanationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "explanation", name, element, index);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCodeableConcept(t, "Immunization", "reason", element.getReason().get(i), i);
    for (int i = 0; i < element.getReasonNotGiven().size(); i++)
      composeCodeableConcept(t, "Immunization", "reasonNotGiven", element.getReasonNotGiven().get(i), i);
  }

  protected void composeImmunizationImmunizationReactionComponent(Complex parent, String parentType, String name, Immunization.ImmunizationReactionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "reaction", name, element, index);
    composeDateTime(t, "Immunization", "date", element.getDateElement(), -1);
    composeReference(t, "Immunization", "detail", element.getDetail(), -1);
    composeBoolean(t, "Immunization", "reported", element.getReportedElement(), -1);
  }

  protected void composeImmunizationImmunizationVaccinationProtocolComponent(Complex parent, String parentType, String name, Immunization.ImmunizationVaccinationProtocolComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "vaccinationProtocol", name, element, index);
    composePositiveInt(t, "Immunization", "doseSequence", element.getDoseSequenceElement(), -1);
    composeString(t, "Immunization", "description", element.getDescriptionElement(), -1);
    composeReference(t, "Immunization", "authority", element.getAuthority(), -1);
    composeString(t, "Immunization", "series", element.getSeriesElement(), -1);
    composePositiveInt(t, "Immunization", "seriesDoses", element.getSeriesDosesElement(), -1);
    for (int i = 0; i < element.getTargetDisease().size(); i++)
      composeCodeableConcept(t, "Immunization", "targetDisease", element.getTargetDisease().get(i), i);
    composeCodeableConcept(t, "Immunization", "doseStatus", element.getDoseStatus(), -1);
    composeCodeableConcept(t, "Immunization", "doseStatusReason", element.getDoseStatusReason(), -1);
  }

  protected void composeImmunizationRecommendation(Complex parent, String parentType, String name, ImmunizationRecommendation element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ImmunizationRecommendation", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ImmunizationRecommendation", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "ImmunizationRecommendation", "patient", element.getPatient(), -1);
    for (int i = 0; i < element.getRecommendation().size(); i++)
      composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(t, "ImmunizationRecommendation", "recommendation", element.getRecommendation().get(i), i);
  }

  protected void composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(Complex parent, String parentType, String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "recommendation", name, element, index);
    composeDateTime(t, "ImmunizationRecommendation", "date", element.getDateElement(), -1);
    composeCodeableConcept(t, "ImmunizationRecommendation", "vaccineCode", element.getVaccineCode(), -1);
    composePositiveInt(t, "ImmunizationRecommendation", "doseNumber", element.getDoseNumberElement(), -1);
    composeCodeableConcept(t, "ImmunizationRecommendation", "forecastStatus", element.getForecastStatus(), -1);
    for (int i = 0; i < element.getDateCriterion().size(); i++)
      composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(t, "ImmunizationRecommendation", "dateCriterion", element.getDateCriterion().get(i), i);
    composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(t, "ImmunizationRecommendation", "protocol", element.getProtocol(), -1);
    for (int i = 0; i < element.getSupportingImmunization().size(); i++)
      composeReference(t, "ImmunizationRecommendation", "supportingImmunization", element.getSupportingImmunization().get(i), i);
    for (int i = 0; i < element.getSupportingPatientInformation().size(); i++)
      composeReference(t, "ImmunizationRecommendation", "supportingPatientInformation", element.getSupportingPatientInformation().get(i), i);
  }

  protected void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(Complex parent, String parentType, String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dateCriterion", name, element, index);
    composeCodeableConcept(t, "ImmunizationRecommendation", "code", element.getCode(), -1);
    composeDateTime(t, "ImmunizationRecommendation", "value", element.getValueElement(), -1);
  }

  protected void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(Complex parent, String parentType, String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "protocol", name, element, index);
    composeInteger(t, "ImmunizationRecommendation", "doseSequence", element.getDoseSequenceElement(), -1);
    composeString(t, "ImmunizationRecommendation", "description", element.getDescriptionElement(), -1);
    composeReference(t, "ImmunizationRecommendation", "authority", element.getAuthority(), -1);
    composeString(t, "ImmunizationRecommendation", "series", element.getSeriesElement(), -1);
  }

  protected void composeImplementationGuide(Complex parent, String parentType, String name, ImplementationGuide element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ImplementationGuide", name, element, index);
    composeUri(t, "ImplementationGuide", "url", element.getUrlElement(), -1);
    composeString(t, "ImplementationGuide", "version", element.getVersionElement(), -1);
    composeString(t, "ImplementationGuide", "name", element.getNameElement(), -1);
    composeEnum(t, "ImplementationGuide", "status", element.getStatusElement(), -1);
    composeBoolean(t, "ImplementationGuide", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "ImplementationGuide", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeImplementationGuideImplementationGuideContactComponent(t, "ImplementationGuide", "contact", element.getContact().get(i), i);
    composeDateTime(t, "ImplementationGuide", "date", element.getDateElement(), -1);
    composeString(t, "ImplementationGuide", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "ImplementationGuide", "useContext", element.getUseContext().get(i), i);
    composeString(t, "ImplementationGuide", "copyright", element.getCopyrightElement(), -1);
    composeId(t, "ImplementationGuide", "fhirVersion", element.getFhirVersionElement(), -1);
    for (int i = 0; i < element.getDependency().size(); i++)
      composeImplementationGuideImplementationGuideDependencyComponent(t, "ImplementationGuide", "dependency", element.getDependency().get(i), i);
    for (int i = 0; i < element.getPackage().size(); i++)
      composeImplementationGuideImplementationGuidePackageComponent(t, "ImplementationGuide", "package", element.getPackage().get(i), i);
    for (int i = 0; i < element.getGlobal().size(); i++)
      composeImplementationGuideImplementationGuideGlobalComponent(t, "ImplementationGuide", "global", element.getGlobal().get(i), i);
    for (int i = 0; i < element.getBinary().size(); i++)
      composeUri(t, "ImplementationGuide", "binary", element.getBinary().get(i), i);
    composeImplementationGuideImplementationGuidePageComponent(t, "ImplementationGuide", "page", element.getPage(), -1);
  }

  protected void composeImplementationGuideImplementationGuideContactComponent(Complex parent, String parentType, String name, ImplementationGuide.ImplementationGuideContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "ImplementationGuide", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "ImplementationGuide", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeImplementationGuideImplementationGuideDependencyComponent(Complex parent, String parentType, String name, ImplementationGuide.ImplementationGuideDependencyComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dependency", name, element, index);
    composeEnum(t, "ImplementationGuide", "type", element.getTypeElement(), -1);
    composeUri(t, "ImplementationGuide", "uri", element.getUriElement(), -1);
  }

  protected void composeImplementationGuideImplementationGuidePackageComponent(Complex parent, String parentType, String name, ImplementationGuide.ImplementationGuidePackageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "package", name, element, index);
    composeString(t, "ImplementationGuide", "name", element.getNameElement(), -1);
    composeString(t, "ImplementationGuide", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getResource().size(); i++)
      composeImplementationGuideImplementationGuidePackageResourceComponent(t, "ImplementationGuide", "resource", element.getResource().get(i), i);
  }

  protected void composeImplementationGuideImplementationGuidePackageResourceComponent(Complex parent, String parentType, String name, ImplementationGuide.ImplementationGuidePackageResourceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "resource", name, element, index);
    composeEnum(t, "ImplementationGuide", "purpose", element.getPurposeElement(), -1);
    composeString(t, "ImplementationGuide", "name", element.getNameElement(), -1);
    composeString(t, "ImplementationGuide", "description", element.getDescriptionElement(), -1);
    composeString(t, "ImplementationGuide", "acronym", element.getAcronymElement(), -1);
    composeType(t, "ImplementationGuide", "source", element.getSource(), -1);
    composeReference(t, "ImplementationGuide", "exampleFor", element.getExampleFor(), -1);
  }

  protected void composeImplementationGuideImplementationGuideGlobalComponent(Complex parent, String parentType, String name, ImplementationGuide.ImplementationGuideGlobalComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "global", name, element, index);
    composeCode(t, "ImplementationGuide", "type", element.getTypeElement(), -1);
    composeReference(t, "ImplementationGuide", "profile", element.getProfile(), -1);
  }

  protected void composeImplementationGuideImplementationGuidePageComponent(Complex parent, String parentType, String name, ImplementationGuide.ImplementationGuidePageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "page", name, element, index);
    composeUri(t, "ImplementationGuide", "source", element.getSourceElement(), -1);
    composeString(t, "ImplementationGuide", "name", element.getNameElement(), -1);
    composeEnum(t, "ImplementationGuide", "kind", element.getKindElement(), -1);
    for (int i = 0; i < element.getType().size(); i++)
      composeCode(t, "ImplementationGuide", "type", element.getType().get(i), i);
    for (int i = 0; i < element.getPackage().size(); i++)
      composeString(t, "ImplementationGuide", "package", element.getPackage().get(i), i);
    composeCode(t, "ImplementationGuide", "format", element.getFormatElement(), -1);
    for (int i = 0; i < element.getPage().size(); i++)
      composeImplementationGuideImplementationGuidePageComponent(t, "ImplementationGuide", "page", element.getPage().get(i), i);
  }

  protected void composeLibrary(Complex parent, String parentType, String name, Library element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Library", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Library", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Library", "version", element.getVersionElement(), -1);
    composeModuleMetadata(t, "Library", "moduleMetadata", element.getModuleMetadata(), -1);
    composeReference(t, "Library", "moduleDefinition", element.getModuleDefinition(), -1);
    composeAttachment(t, "Library", "document", element.getDocument(), -1);
  }

  protected void composeLinkage(Complex parent, String parentType, String name, Linkage element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Linkage", name, element, index);
    composeReference(t, "Linkage", "author", element.getAuthor(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeLinkageLinkageItemComponent(t, "Linkage", "item", element.getItem().get(i), i);
  }

  protected void composeLinkageLinkageItemComponent(Complex parent, String parentType, String name, Linkage.LinkageItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composeEnum(t, "Linkage", "type", element.getTypeElement(), -1);
    composeReference(t, "Linkage", "resource", element.getResource(), -1);
  }

  protected void composeListResource(Complex parent, String parentType, String name, ListResource element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "List", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "List", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "List", "status", element.getStatusElement(), -1);
    composeEnum(t, "List", "mode", element.getModeElement(), -1);
    composeString(t, "List", "title", element.getTitleElement(), -1);
    composeCodeableConcept(t, "List", "code", element.getCode(), -1);
    composeReference(t, "List", "subject", element.getSubject(), -1);
    composeReference(t, "List", "encounter", element.getEncounter(), -1);
    composeDateTime(t, "List", "date", element.getDateElement(), -1);
    composeReference(t, "List", "source", element.getSource(), -1);
    composeCodeableConcept(t, "List", "orderedBy", element.getOrderedBy(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "List", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getEntry().size(); i++)
      composeListResourceListEntryComponent(t, "List", "entry", element.getEntry().get(i), i);
    composeCodeableConcept(t, "List", "emptyReason", element.getEmptyReason(), -1);
  }

  protected void composeListResourceListEntryComponent(Complex parent, String parentType, String name, ListResource.ListEntryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "entry", name, element, index);
    composeCodeableConcept(t, "List", "flag", element.getFlag(), -1);
    composeBoolean(t, "List", "deleted", element.getDeletedElement(), -1);
    composeDateTime(t, "List", "date", element.getDateElement(), -1);
    composeReference(t, "List", "item", element.getItem(), -1);
  }

  protected void composeLocation(Complex parent, String parentType, String name, Location element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Location", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Location", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "Location", "status", element.getStatusElement(), -1);
    composeString(t, "Location", "name", element.getNameElement(), -1);
    composeString(t, "Location", "description", element.getDescriptionElement(), -1);
    composeEnum(t, "Location", "mode", element.getModeElement(), -1);
    composeCodeableConcept(t, "Location", "type", element.getType(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Location", "telecom", element.getTelecom().get(i), i);
    composeAddress(t, "Location", "address", element.getAddress(), -1);
    composeCodeableConcept(t, "Location", "physicalType", element.getPhysicalType(), -1);
    composeLocationLocationPositionComponent(t, "Location", "position", element.getPosition(), -1);
    composeReference(t, "Location", "managingOrganization", element.getManagingOrganization(), -1);
    composeReference(t, "Location", "partOf", element.getPartOf(), -1);
  }

  protected void composeLocationLocationPositionComponent(Complex parent, String parentType, String name, Location.LocationPositionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "position", name, element, index);
    composeDecimal(t, "Location", "longitude", element.getLongitudeElement(), -1);
    composeDecimal(t, "Location", "latitude", element.getLatitudeElement(), -1);
    composeDecimal(t, "Location", "altitude", element.getAltitudeElement(), -1);
  }

  protected void composeMeasure(Complex parent, String parentType, String name, Measure element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Measure", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Measure", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Measure", "version", element.getVersionElement(), -1);
    composeModuleMetadata(t, "Measure", "moduleMetadata", element.getModuleMetadata(), -1);
    for (int i = 0; i < element.getLibrary().size(); i++)
      composeReference(t, "Measure", "library", element.getLibrary().get(i), i);
    for (int i = 0; i < element.getPopulation().size(); i++)
      composeMeasureMeasurePopulationComponent(t, "Measure", "population", element.getPopulation().get(i), i);
    for (int i = 0; i < element.getStratifier().size(); i++)
      composeString(t, "Measure", "stratifier", element.getStratifier().get(i), i);
    for (int i = 0; i < element.getSupplementalData().size(); i++)
      composeString(t, "Measure", "supplementalData", element.getSupplementalData().get(i), i);
  }

  protected void composeMeasureMeasurePopulationComponent(Complex parent, String parentType, String name, Measure.MeasurePopulationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "population", name, element, index);
    composeEnum(t, "Measure", "type", element.getTypeElement(), -1);
    composeString(t, "Measure", "name", element.getNameElement(), -1);
    composeString(t, "Measure", "description", element.getDescriptionElement(), -1);
    composeString(t, "Measure", "criteria", element.getCriteriaElement(), -1);
  }

  protected void composeMedia(Complex parent, String parentType, String name, Media element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Media", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Media", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "Media", "type", element.getTypeElement(), -1);
    composeCodeableConcept(t, "Media", "subtype", element.getSubtype(), -1);
    composeCodeableConcept(t, "Media", "view", element.getView(), -1);
    composeReference(t, "Media", "subject", element.getSubject(), -1);
    composeReference(t, "Media", "operator", element.getOperator(), -1);
    composeString(t, "Media", "deviceName", element.getDeviceNameElement(), -1);
    composePositiveInt(t, "Media", "height", element.getHeightElement(), -1);
    composePositiveInt(t, "Media", "width", element.getWidthElement(), -1);
    composePositiveInt(t, "Media", "frames", element.getFramesElement(), -1);
    composeUnsignedInt(t, "Media", "duration", element.getDurationElement(), -1);
    composeAttachment(t, "Media", "content", element.getContent(), -1);
  }

  protected void composeMedication(Complex parent, String parentType, String name, Medication element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Medication", name, element, index);
    composeCodeableConcept(t, "Medication", "code", element.getCode(), -1);
    composeBoolean(t, "Medication", "isBrand", element.getIsBrandElement(), -1);
    composeReference(t, "Medication", "manufacturer", element.getManufacturer(), -1);
    composeMedicationMedicationProductComponent(t, "Medication", "product", element.getProduct(), -1);
    composeMedicationMedicationPackageComponent(t, "Medication", "package", element.getPackage(), -1);
  }

  protected void composeMedicationMedicationProductComponent(Complex parent, String parentType, String name, Medication.MedicationProductComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "product", name, element, index);
    composeCodeableConcept(t, "Medication", "form", element.getForm(), -1);
    for (int i = 0; i < element.getIngredient().size(); i++)
      composeMedicationMedicationProductIngredientComponent(t, "Medication", "ingredient", element.getIngredient().get(i), i);
    for (int i = 0; i < element.getBatch().size(); i++)
      composeMedicationMedicationProductBatchComponent(t, "Medication", "batch", element.getBatch().get(i), i);
  }

  protected void composeMedicationMedicationProductIngredientComponent(Complex parent, String parentType, String name, Medication.MedicationProductIngredientComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "ingredient", name, element, index);
    composeReference(t, "Medication", "item", element.getItem(), -1);
    composeRatio(t, "Medication", "amount", element.getAmount(), -1);
  }

  protected void composeMedicationMedicationProductBatchComponent(Complex parent, String parentType, String name, Medication.MedicationProductBatchComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "batch", name, element, index);
    composeString(t, "Medication", "lotNumber", element.getLotNumberElement(), -1);
    composeDateTime(t, "Medication", "expirationDate", element.getExpirationDateElement(), -1);
  }

  protected void composeMedicationMedicationPackageComponent(Complex parent, String parentType, String name, Medication.MedicationPackageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "package", name, element, index);
    composeCodeableConcept(t, "Medication", "container", element.getContainer(), -1);
    for (int i = 0; i < element.getContent().size(); i++)
      composeMedicationMedicationPackageContentComponent(t, "Medication", "content", element.getContent().get(i), i);
  }

  protected void composeMedicationMedicationPackageContentComponent(Complex parent, String parentType, String name, Medication.MedicationPackageContentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "content", name, element, index);
    composeReference(t, "Medication", "item", element.getItem(), -1);
    composeQuantity(t, "Medication", "amount", element.getAmount(), -1);
  }

  protected void composeMedicationAdministration(Complex parent, String parentType, String name, MedicationAdministration element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "MedicationAdministration", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "MedicationAdministration", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "MedicationAdministration", "status", element.getStatusElement(), -1);
    composeType(t, "MedicationAdministration", "medication", element.getMedication(), -1);
    composeReference(t, "MedicationAdministration", "patient", element.getPatient(), -1);
    composeReference(t, "MedicationAdministration", "encounter", element.getEncounter(), -1);
    composeType(t, "MedicationAdministration", "effectiveTime", element.getEffectiveTime(), -1);
    composeReference(t, "MedicationAdministration", "practitioner", element.getPractitioner(), -1);
    composeReference(t, "MedicationAdministration", "prescription", element.getPrescription(), -1);
    composeBoolean(t, "MedicationAdministration", "wasNotGiven", element.getWasNotGivenElement(), -1);
    for (int i = 0; i < element.getReasonNotGiven().size(); i++)
      composeCodeableConcept(t, "MedicationAdministration", "reasonNotGiven", element.getReasonNotGiven().get(i), i);
    for (int i = 0; i < element.getReasonGiven().size(); i++)
      composeCodeableConcept(t, "MedicationAdministration", "reasonGiven", element.getReasonGiven().get(i), i);
    for (int i = 0; i < element.getDevice().size(); i++)
      composeReference(t, "MedicationAdministration", "device", element.getDevice().get(i), i);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "MedicationAdministration", "note", element.getNote().get(i), i);
    composeMedicationAdministrationMedicationAdministrationDosageComponent(t, "MedicationAdministration", "dosage", element.getDosage(), -1);
  }

  protected void composeMedicationAdministrationMedicationAdministrationDosageComponent(Complex parent, String parentType, String name, MedicationAdministration.MedicationAdministrationDosageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dosage", name, element, index);
    composeString(t, "MedicationAdministration", "text", element.getTextElement(), -1);
    composeType(t, "MedicationAdministration", "site", element.getSite(), -1);
    composeCodeableConcept(t, "MedicationAdministration", "route", element.getRoute(), -1);
    composeCodeableConcept(t, "MedicationAdministration", "method", element.getMethod(), -1);
    composeQuantity(t, "MedicationAdministration", "quantity", element.getQuantity(), -1);
    composeType(t, "MedicationAdministration", "rate", element.getRate(), -1);
  }

  protected void composeMedicationDispense(Complex parent, String parentType, String name, MedicationDispense element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "MedicationDispense", name, element, index);
    composeIdentifier(t, "MedicationDispense", "identifier", element.getIdentifier(), -1);
    composeEnum(t, "MedicationDispense", "status", element.getStatusElement(), -1);
    composeType(t, "MedicationDispense", "medication", element.getMedication(), -1);
    composeReference(t, "MedicationDispense", "patient", element.getPatient(), -1);
    composeReference(t, "MedicationDispense", "dispenser", element.getDispenser(), -1);
    for (int i = 0; i < element.getAuthorizingPrescription().size(); i++)
      composeReference(t, "MedicationDispense", "authorizingPrescription", element.getAuthorizingPrescription().get(i), i);
    composeCodeableConcept(t, "MedicationDispense", "type", element.getType(), -1);
    composeQuantity(t, "MedicationDispense", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "MedicationDispense", "daysSupply", element.getDaysSupply(), -1);
    composeDateTime(t, "MedicationDispense", "whenPrepared", element.getWhenPreparedElement(), -1);
    composeDateTime(t, "MedicationDispense", "whenHandedOver", element.getWhenHandedOverElement(), -1);
    composeReference(t, "MedicationDispense", "destination", element.getDestination(), -1);
    for (int i = 0; i < element.getReceiver().size(); i++)
      composeReference(t, "MedicationDispense", "receiver", element.getReceiver().get(i), i);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "MedicationDispense", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getDosageInstruction().size(); i++)
      composeMedicationDispenseMedicationDispenseDosageInstructionComponent(t, "MedicationDispense", "dosageInstruction", element.getDosageInstruction().get(i), i);
    composeMedicationDispenseMedicationDispenseSubstitutionComponent(t, "MedicationDispense", "substitution", element.getSubstitution(), -1);
  }

  protected void composeMedicationDispenseMedicationDispenseDosageInstructionComponent(Complex parent, String parentType, String name, MedicationDispense.MedicationDispenseDosageInstructionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dosageInstruction", name, element, index);
    composeString(t, "MedicationDispense", "text", element.getTextElement(), -1);
    composeCodeableConcept(t, "MedicationDispense", "additionalInstructions", element.getAdditionalInstructions(), -1);
    composeTiming(t, "MedicationDispense", "timing", element.getTiming(), -1);
    composeType(t, "MedicationDispense", "asNeeded", element.getAsNeeded(), -1);
    composeType(t, "MedicationDispense", "site", element.getSite(), -1);
    composeCodeableConcept(t, "MedicationDispense", "route", element.getRoute(), -1);
    composeCodeableConcept(t, "MedicationDispense", "method", element.getMethod(), -1);
    composeType(t, "MedicationDispense", "dose", element.getDose(), -1);
    composeType(t, "MedicationDispense", "rate", element.getRate(), -1);
    composeRatio(t, "MedicationDispense", "maxDosePerPeriod", element.getMaxDosePerPeriod(), -1);
  }

  protected void composeMedicationDispenseMedicationDispenseSubstitutionComponent(Complex parent, String parentType, String name, MedicationDispense.MedicationDispenseSubstitutionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "substitution", name, element, index);
    composeCodeableConcept(t, "MedicationDispense", "type", element.getType(), -1);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCodeableConcept(t, "MedicationDispense", "reason", element.getReason().get(i), i);
    for (int i = 0; i < element.getResponsibleParty().size(); i++)
      composeReference(t, "MedicationDispense", "responsibleParty", element.getResponsibleParty().get(i), i);
  }

  protected void composeMedicationOrder(Complex parent, String parentType, String name, MedicationOrder element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "MedicationOrder", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "MedicationOrder", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "MedicationOrder", "status", element.getStatusElement(), -1);
    composeType(t, "MedicationOrder", "medication", element.getMedication(), -1);
    composeReference(t, "MedicationOrder", "patient", element.getPatient(), -1);
    composeReference(t, "MedicationOrder", "encounter", element.getEncounter(), -1);
    composeDateTime(t, "MedicationOrder", "dateWritten", element.getDateWrittenElement(), -1);
    composeReference(t, "MedicationOrder", "prescriber", element.getPrescriber(), -1);
    composeType(t, "MedicationOrder", "reason", element.getReason(), -1);
    composeDateTime(t, "MedicationOrder", "dateEnded", element.getDateEndedElement(), -1);
    composeCodeableConcept(t, "MedicationOrder", "reasonEnded", element.getReasonEnded(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "MedicationOrder", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getDosageInstruction().size(); i++)
      composeMedicationOrderMedicationOrderDosageInstructionComponent(t, "MedicationOrder", "dosageInstruction", element.getDosageInstruction().get(i), i);
    composeMedicationOrderMedicationOrderDispenseRequestComponent(t, "MedicationOrder", "dispenseRequest", element.getDispenseRequest(), -1);
    composeMedicationOrderMedicationOrderSubstitutionComponent(t, "MedicationOrder", "substitution", element.getSubstitution(), -1);
    composeReference(t, "MedicationOrder", "priorPrescription", element.getPriorPrescription(), -1);
  }

  protected void composeMedicationOrderMedicationOrderDosageInstructionComponent(Complex parent, String parentType, String name, MedicationOrder.MedicationOrderDosageInstructionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dosageInstruction", name, element, index);
    composeString(t, "MedicationOrder", "text", element.getTextElement(), -1);
    composeCodeableConcept(t, "MedicationOrder", "additionalInstructions", element.getAdditionalInstructions(), -1);
    composeTiming(t, "MedicationOrder", "timing", element.getTiming(), -1);
    composeType(t, "MedicationOrder", "asNeeded", element.getAsNeeded(), -1);
    composeType(t, "MedicationOrder", "site", element.getSite(), -1);
    composeCodeableConcept(t, "MedicationOrder", "route", element.getRoute(), -1);
    composeCodeableConcept(t, "MedicationOrder", "method", element.getMethod(), -1);
    composeType(t, "MedicationOrder", "dose", element.getDose(), -1);
    composeType(t, "MedicationOrder", "rate", element.getRate(), -1);
    composeRatio(t, "MedicationOrder", "maxDosePerPeriod", element.getMaxDosePerPeriod(), -1);
  }

  protected void composeMedicationOrderMedicationOrderDispenseRequestComponent(Complex parent, String parentType, String name, MedicationOrder.MedicationOrderDispenseRequestComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dispenseRequest", name, element, index);
    composeType(t, "MedicationOrder", "medication", element.getMedication(), -1);
    composePeriod(t, "MedicationOrder", "validityPeriod", element.getValidityPeriod(), -1);
    composePositiveInt(t, "MedicationOrder", "numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedElement(), -1);
    composeQuantity(t, "MedicationOrder", "quantity", element.getQuantity(), -1);
    composeQuantity(t, "MedicationOrder", "expectedSupplyDuration", element.getExpectedSupplyDuration(), -1);
  }

  protected void composeMedicationOrderMedicationOrderSubstitutionComponent(Complex parent, String parentType, String name, MedicationOrder.MedicationOrderSubstitutionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "substitution", name, element, index);
    composeCodeableConcept(t, "MedicationOrder", "type", element.getType(), -1);
    composeCodeableConcept(t, "MedicationOrder", "reason", element.getReason(), -1);
  }

  protected void composeMedicationStatement(Complex parent, String parentType, String name, MedicationStatement element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "MedicationStatement", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "MedicationStatement", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "MedicationStatement", "status", element.getStatusElement(), -1);
    composeType(t, "MedicationStatement", "medication", element.getMedication(), -1);
    composeReference(t, "MedicationStatement", "patient", element.getPatient(), -1);
    composeType(t, "MedicationStatement", "effective", element.getEffective(), -1);
    composeReference(t, "MedicationStatement", "informationSource", element.getInformationSource(), -1);
    composeDateTime(t, "MedicationStatement", "dateAsserted", element.getDateAssertedElement(), -1);
    composeBoolean(t, "MedicationStatement", "wasNotTaken", element.getWasNotTakenElement(), -1);
    for (int i = 0; i < element.getReasonNotTaken().size(); i++)
      composeCodeableConcept(t, "MedicationStatement", "reasonNotTaken", element.getReasonNotTaken().get(i), i);
    composeType(t, "MedicationStatement", "reasonForUse", element.getReasonForUse(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composeAnnotation(t, "MedicationStatement", "note", element.getNote().get(i), i);
    for (int i = 0; i < element.getSupportingInformation().size(); i++)
      composeReference(t, "MedicationStatement", "supportingInformation", element.getSupportingInformation().get(i), i);
    for (int i = 0; i < element.getDosage().size(); i++)
      composeMedicationStatementMedicationStatementDosageComponent(t, "MedicationStatement", "dosage", element.getDosage().get(i), i);
  }

  protected void composeMedicationStatementMedicationStatementDosageComponent(Complex parent, String parentType, String name, MedicationStatement.MedicationStatementDosageComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dosage", name, element, index);
    composeString(t, "MedicationStatement", "text", element.getTextElement(), -1);
    composeTiming(t, "MedicationStatement", "timing", element.getTiming(), -1);
    composeType(t, "MedicationStatement", "asNeeded", element.getAsNeeded(), -1);
    composeType(t, "MedicationStatement", "site", element.getSite(), -1);
    composeCodeableConcept(t, "MedicationStatement", "route", element.getRoute(), -1);
    composeCodeableConcept(t, "MedicationStatement", "method", element.getMethod(), -1);
    composeType(t, "MedicationStatement", "quantity", element.getQuantity(), -1);
    composeType(t, "MedicationStatement", "rate", element.getRate(), -1);
    composeRatio(t, "MedicationStatement", "maxDosePerPeriod", element.getMaxDosePerPeriod(), -1);
  }

  protected void composeMessageHeader(Complex parent, String parentType, String name, MessageHeader element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "MessageHeader", name, element, index);
    composeInstant(t, "MessageHeader", "timestamp", element.getTimestampElement(), -1);
    composeCoding(t, "MessageHeader", "event", element.getEvent(), -1);
    composeMessageHeaderMessageHeaderResponseComponent(t, "MessageHeader", "response", element.getResponse(), -1);
    composeMessageHeaderMessageSourceComponent(t, "MessageHeader", "source", element.getSource(), -1);
    for (int i = 0; i < element.getDestination().size(); i++)
      composeMessageHeaderMessageDestinationComponent(t, "MessageHeader", "destination", element.getDestination().get(i), i);
    composeReference(t, "MessageHeader", "enterer", element.getEnterer(), -1);
    composeReference(t, "MessageHeader", "author", element.getAuthor(), -1);
    composeReference(t, "MessageHeader", "receiver", element.getReceiver(), -1);
    composeReference(t, "MessageHeader", "responsible", element.getResponsible(), -1);
    composeCodeableConcept(t, "MessageHeader", "reason", element.getReason(), -1);
    for (int i = 0; i < element.getData().size(); i++)
      composeReference(t, "MessageHeader", "data", element.getData().get(i), i);
  }

  protected void composeMessageHeaderMessageHeaderResponseComponent(Complex parent, String parentType, String name, MessageHeader.MessageHeaderResponseComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "response", name, element, index);
    composeId(t, "MessageHeader", "identifier", element.getIdentifierElement(), -1);
    composeEnum(t, "MessageHeader", "code", element.getCodeElement(), -1);
    composeReference(t, "MessageHeader", "details", element.getDetails(), -1);
  }

  protected void composeMessageHeaderMessageSourceComponent(Complex parent, String parentType, String name, MessageHeader.MessageSourceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "source", name, element, index);
    composeString(t, "MessageHeader", "name", element.getNameElement(), -1);
    composeString(t, "MessageHeader", "software", element.getSoftwareElement(), -1);
    composeString(t, "MessageHeader", "version", element.getVersionElement(), -1);
    composeContactPoint(t, "MessageHeader", "contact", element.getContact(), -1);
    composeUri(t, "MessageHeader", "endpoint", element.getEndpointElement(), -1);
  }

  protected void composeMessageHeaderMessageDestinationComponent(Complex parent, String parentType, String name, MessageHeader.MessageDestinationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "destination", name, element, index);
    composeString(t, "MessageHeader", "name", element.getNameElement(), -1);
    composeReference(t, "MessageHeader", "target", element.getTarget(), -1);
    composeUri(t, "MessageHeader", "endpoint", element.getEndpointElement(), -1);
  }

  protected void composeModuleDefinition(Complex parent, String parentType, String name, ModuleDefinition element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ModuleDefinition", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ModuleDefinition", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "ModuleDefinition", "version", element.getVersionElement(), -1);
    for (int i = 0; i < element.getModel().size(); i++)
      composeModuleDefinitionModuleDefinitionModelComponent(t, "ModuleDefinition", "model", element.getModel().get(i), i);
    for (int i = 0; i < element.getLibrary().size(); i++)
      composeModuleDefinitionModuleDefinitionLibraryComponent(t, "ModuleDefinition", "library", element.getLibrary().get(i), i);
    for (int i = 0; i < element.getCodeSystem().size(); i++)
      composeModuleDefinitionModuleDefinitionCodeSystemComponent(t, "ModuleDefinition", "codeSystem", element.getCodeSystem().get(i), i);
    for (int i = 0; i < element.getValueSet().size(); i++)
      composeModuleDefinitionModuleDefinitionValueSetComponent(t, "ModuleDefinition", "valueSet", element.getValueSet().get(i), i);
    for (int i = 0; i < element.getParameter().size(); i++)
      composeModuleDefinitionModuleDefinitionParameterComponent(t, "ModuleDefinition", "parameter", element.getParameter().get(i), i);
    for (int i = 0; i < element.getData().size(); i++)
      composeModuleDefinitionModuleDefinitionDataComponent(t, "ModuleDefinition", "data", element.getData().get(i), i);
  }

  protected void composeModuleDefinitionModuleDefinitionModelComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionModelComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "model", name, element, index);
    composeString(t, "ModuleDefinition", "name", element.getNameElement(), -1);
    composeString(t, "ModuleDefinition", "identifier", element.getIdentifierElement(), -1);
    composeString(t, "ModuleDefinition", "version", element.getVersionElement(), -1);
  }

  protected void composeModuleDefinitionModuleDefinitionLibraryComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionLibraryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "library", name, element, index);
    composeString(t, "ModuleDefinition", "name", element.getNameElement(), -1);
    composeString(t, "ModuleDefinition", "identifier", element.getIdentifierElement(), -1);
    composeString(t, "ModuleDefinition", "version", element.getVersionElement(), -1);
    composeType(t, "ModuleDefinition", "document", element.getDocument(), -1);
  }

  protected void composeModuleDefinitionModuleDefinitionCodeSystemComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionCodeSystemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeSystem", name, element, index);
    composeString(t, "ModuleDefinition", "name", element.getNameElement(), -1);
    composeString(t, "ModuleDefinition", "identifier", element.getIdentifierElement(), -1);
    composeString(t, "ModuleDefinition", "version", element.getVersionElement(), -1);
  }

  protected void composeModuleDefinitionModuleDefinitionValueSetComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionValueSetComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "valueSet", name, element, index);
    composeString(t, "ModuleDefinition", "name", element.getNameElement(), -1);
    composeString(t, "ModuleDefinition", "identifier", element.getIdentifierElement(), -1);
    composeString(t, "ModuleDefinition", "version", element.getVersionElement(), -1);
    for (int i = 0; i < element.getCodeSystem().size(); i++)
      composeString(t, "ModuleDefinition", "codeSystem", element.getCodeSystem().get(i), i);
  }

  protected void composeModuleDefinitionModuleDefinitionParameterComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionParameterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "parameter", name, element, index);
    composeCode(t, "ModuleDefinition", "name", element.getNameElement(), -1);
    composeCode(t, "ModuleDefinition", "use", element.getUseElement(), -1);
    composeString(t, "ModuleDefinition", "documentation", element.getDocumentationElement(), -1);
    composeCode(t, "ModuleDefinition", "type", element.getTypeElement(), -1);
    composeReference(t, "ModuleDefinition", "profile", element.getProfile(), -1);
  }

  protected void composeModuleDefinitionModuleDefinitionDataComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionDataComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "data", name, element, index);
    composeCode(t, "ModuleDefinition", "type", element.getTypeElement(), -1);
    composeReference(t, "ModuleDefinition", "profile", element.getProfile(), -1);
    for (int i = 0; i < element.getMustSupport().size(); i++)
      composeString(t, "ModuleDefinition", "mustSupport", element.getMustSupport().get(i), i);
    for (int i = 0; i < element.getCodeFilter().size(); i++)
      composeModuleDefinitionModuleDefinitionDataCodeFilterComponent(t, "ModuleDefinition", "codeFilter", element.getCodeFilter().get(i), i);
    for (int i = 0; i < element.getDateFilter().size(); i++)
      composeModuleDefinitionModuleDefinitionDataDateFilterComponent(t, "ModuleDefinition", "dateFilter", element.getDateFilter().get(i), i);
  }

  protected void composeModuleDefinitionModuleDefinitionDataCodeFilterComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionDataCodeFilterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeFilter", name, element, index);
    composeString(t, "ModuleDefinition", "path", element.getPathElement(), -1);
    composeType(t, "ModuleDefinition", "valueSet", element.getValueSet(), -1);
    for (int i = 0; i < element.getCodeableConcept().size(); i++)
      composeCodeableConcept(t, "ModuleDefinition", "codeableConcept", element.getCodeableConcept().get(i), i);
  }

  protected void composeModuleDefinitionModuleDefinitionDataDateFilterComponent(Complex parent, String parentType, String name, ModuleDefinition.ModuleDefinitionDataDateFilterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dateFilter", name, element, index);
    composeString(t, "ModuleDefinition", "path", element.getPathElement(), -1);
    composeType(t, "ModuleDefinition", "value", element.getValue(), -1);
  }

  protected void composeNamingSystem(Complex parent, String parentType, String name, NamingSystem element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "NamingSystem", name, element, index);
    composeString(t, "NamingSystem", "name", element.getNameElement(), -1);
    composeEnum(t, "NamingSystem", "status", element.getStatusElement(), -1);
    composeEnum(t, "NamingSystem", "kind", element.getKindElement(), -1);
    composeString(t, "NamingSystem", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeNamingSystemNamingSystemContactComponent(t, "NamingSystem", "contact", element.getContact().get(i), i);
    composeString(t, "NamingSystem", "responsible", element.getResponsibleElement(), -1);
    composeDateTime(t, "NamingSystem", "date", element.getDateElement(), -1);
    composeCodeableConcept(t, "NamingSystem", "type", element.getType(), -1);
    composeString(t, "NamingSystem", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "NamingSystem", "useContext", element.getUseContext().get(i), i);
    composeString(t, "NamingSystem", "usage", element.getUsageElement(), -1);
    for (int i = 0; i < element.getUniqueId().size(); i++)
      composeNamingSystemNamingSystemUniqueIdComponent(t, "NamingSystem", "uniqueId", element.getUniqueId().get(i), i);
    composeReference(t, "NamingSystem", "replacedBy", element.getReplacedBy(), -1);
  }

  protected void composeNamingSystemNamingSystemContactComponent(Complex parent, String parentType, String name, NamingSystem.NamingSystemContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "NamingSystem", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "NamingSystem", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeNamingSystemNamingSystemUniqueIdComponent(Complex parent, String parentType, String name, NamingSystem.NamingSystemUniqueIdComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "uniqueId", name, element, index);
    composeEnum(t, "NamingSystem", "type", element.getTypeElement(), -1);
    composeString(t, "NamingSystem", "value", element.getValueElement(), -1);
    composeBoolean(t, "NamingSystem", "preferred", element.getPreferredElement(), -1);
    composePeriod(t, "NamingSystem", "period", element.getPeriod(), -1);
  }

  protected void composeNutritionOrder(Complex parent, String parentType, String name, NutritionOrder element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "NutritionOrder", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "NutritionOrder", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "NutritionOrder", "status", element.getStatusElement(), -1);
    composeReference(t, "NutritionOrder", "patient", element.getPatient(), -1);
    composeReference(t, "NutritionOrder", "encounter", element.getEncounter(), -1);
    composeDateTime(t, "NutritionOrder", "dateTime", element.getDateTimeElement(), -1);
    composeReference(t, "NutritionOrder", "orderer", element.getOrderer(), -1);
    for (int i = 0; i < element.getAllergyIntolerance().size(); i++)
      composeReference(t, "NutritionOrder", "allergyIntolerance", element.getAllergyIntolerance().get(i), i);
    for (int i = 0; i < element.getFoodPreferenceModifier().size(); i++)
      composeCodeableConcept(t, "NutritionOrder", "foodPreferenceModifier", element.getFoodPreferenceModifier().get(i), i);
    for (int i = 0; i < element.getExcludeFoodModifier().size(); i++)
      composeCodeableConcept(t, "NutritionOrder", "excludeFoodModifier", element.getExcludeFoodModifier().get(i), i);
    composeNutritionOrderNutritionOrderOralDietComponent(t, "NutritionOrder", "oralDiet", element.getOralDiet(), -1);
    for (int i = 0; i < element.getSupplement().size(); i++)
      composeNutritionOrderNutritionOrderSupplementComponent(t, "NutritionOrder", "supplement", element.getSupplement().get(i), i);
    composeNutritionOrderNutritionOrderEnteralFormulaComponent(t, "NutritionOrder", "enteralFormula", element.getEnteralFormula(), -1);
  }

  protected void composeNutritionOrderNutritionOrderOralDietComponent(Complex parent, String parentType, String name, NutritionOrder.NutritionOrderOralDietComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "oralDiet", name, element, index);
    for (int i = 0; i < element.getType().size(); i++)
      composeCodeableConcept(t, "NutritionOrder", "type", element.getType().get(i), i);
    for (int i = 0; i < element.getSchedule().size(); i++)
      composeTiming(t, "NutritionOrder", "schedule", element.getSchedule().get(i), i);
    for (int i = 0; i < element.getNutrient().size(); i++)
      composeNutritionOrderNutritionOrderOralDietNutrientComponent(t, "NutritionOrder", "nutrient", element.getNutrient().get(i), i);
    for (int i = 0; i < element.getTexture().size(); i++)
      composeNutritionOrderNutritionOrderOralDietTextureComponent(t, "NutritionOrder", "texture", element.getTexture().get(i), i);
    for (int i = 0; i < element.getFluidConsistencyType().size(); i++)
      composeCodeableConcept(t, "NutritionOrder", "fluidConsistencyType", element.getFluidConsistencyType().get(i), i);
    composeString(t, "NutritionOrder", "instruction", element.getInstructionElement(), -1);
  }

  protected void composeNutritionOrderNutritionOrderOralDietNutrientComponent(Complex parent, String parentType, String name, NutritionOrder.NutritionOrderOralDietNutrientComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "nutrient", name, element, index);
    composeCodeableConcept(t, "NutritionOrder", "modifier", element.getModifier(), -1);
    composeQuantity(t, "NutritionOrder", "amount", element.getAmount(), -1);
  }

  protected void composeNutritionOrderNutritionOrderOralDietTextureComponent(Complex parent, String parentType, String name, NutritionOrder.NutritionOrderOralDietTextureComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "texture", name, element, index);
    composeCodeableConcept(t, "NutritionOrder", "modifier", element.getModifier(), -1);
    composeCodeableConcept(t, "NutritionOrder", "foodType", element.getFoodType(), -1);
  }

  protected void composeNutritionOrderNutritionOrderSupplementComponent(Complex parent, String parentType, String name, NutritionOrder.NutritionOrderSupplementComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "supplement", name, element, index);
    composeCodeableConcept(t, "NutritionOrder", "type", element.getType(), -1);
    composeString(t, "NutritionOrder", "productName", element.getProductNameElement(), -1);
    for (int i = 0; i < element.getSchedule().size(); i++)
      composeTiming(t, "NutritionOrder", "schedule", element.getSchedule().get(i), i);
    composeQuantity(t, "NutritionOrder", "quantity", element.getQuantity(), -1);
    composeString(t, "NutritionOrder", "instruction", element.getInstructionElement(), -1);
  }

  protected void composeNutritionOrderNutritionOrderEnteralFormulaComponent(Complex parent, String parentType, String name, NutritionOrder.NutritionOrderEnteralFormulaComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "enteralFormula", name, element, index);
    composeCodeableConcept(t, "NutritionOrder", "baseFormulaType", element.getBaseFormulaType(), -1);
    composeString(t, "NutritionOrder", "baseFormulaProductName", element.getBaseFormulaProductNameElement(), -1);
    composeCodeableConcept(t, "NutritionOrder", "additiveType", element.getAdditiveType(), -1);
    composeString(t, "NutritionOrder", "additiveProductName", element.getAdditiveProductNameElement(), -1);
    composeQuantity(t, "NutritionOrder", "caloricDensity", element.getCaloricDensity(), -1);
    composeCodeableConcept(t, "NutritionOrder", "routeofAdministration", element.getRouteofAdministration(), -1);
    for (int i = 0; i < element.getAdministration().size(); i++)
      composeNutritionOrderNutritionOrderEnteralFormulaAdministrationComponent(t, "NutritionOrder", "administration", element.getAdministration().get(i), i);
    composeQuantity(t, "NutritionOrder", "maxVolumeToDeliver", element.getMaxVolumeToDeliver(), -1);
    composeString(t, "NutritionOrder", "administrationInstruction", element.getAdministrationInstructionElement(), -1);
  }

  protected void composeNutritionOrderNutritionOrderEnteralFormulaAdministrationComponent(Complex parent, String parentType, String name, NutritionOrder.NutritionOrderEnteralFormulaAdministrationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "administration", name, element, index);
    composeTiming(t, "NutritionOrder", "schedule", element.getSchedule(), -1);
    composeQuantity(t, "NutritionOrder", "quantity", element.getQuantity(), -1);
    composeType(t, "NutritionOrder", "rate", element.getRate(), -1);
  }

  protected void composeObservation(Complex parent, String parentType, String name, Observation element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Observation", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Observation", "identifier", element.getIdentifier().get(i), i);
    composeEnum(t, "Observation", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "Observation", "category", element.getCategory(), -1);
    composeCodeableConcept(t, "Observation", "code", element.getCode(), -1);
    composeReference(t, "Observation", "subject", element.getSubject(), -1);
    composeReference(t, "Observation", "encounter", element.getEncounter(), -1);
    composeType(t, "Observation", "effective", element.getEffective(), -1);
    composeInstant(t, "Observation", "issued", element.getIssuedElement(), -1);
    for (int i = 0; i < element.getPerformer().size(); i++)
      composeReference(t, "Observation", "performer", element.getPerformer().get(i), i);
    composeType(t, "Observation", "value", element.getValue(), -1);
    composeCodeableConcept(t, "Observation", "dataAbsentReason", element.getDataAbsentReason(), -1);
    composeCodeableConcept(t, "Observation", "interpretation", element.getInterpretation(), -1);
    composeString(t, "Observation", "comment", element.getCommentElement(), -1);
    composeCodeableConcept(t, "Observation", "bodySite", element.getBodySite(), -1);
    composeCodeableConcept(t, "Observation", "method", element.getMethod(), -1);
    composeReference(t, "Observation", "specimen", element.getSpecimen(), -1);
    composeReference(t, "Observation", "device", element.getDevice(), -1);
    for (int i = 0; i < element.getReferenceRange().size(); i++)
      composeObservationObservationReferenceRangeComponent(t, "Observation", "referenceRange", element.getReferenceRange().get(i), i);
    for (int i = 0; i < element.getRelated().size(); i++)
      composeObservationObservationRelatedComponent(t, "Observation", "related", element.getRelated().get(i), i);
    for (int i = 0; i < element.getComponent().size(); i++)
      composeObservationObservationComponentComponent(t, "Observation", "component", element.getComponent().get(i), i);
  }

  protected void composeObservationObservationReferenceRangeComponent(Complex parent, String parentType, String name, Observation.ObservationReferenceRangeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "referenceRange", name, element, index);
    composeQuantity(t, "Observation", "low", element.getLow(), -1);
    composeQuantity(t, "Observation", "high", element.getHigh(), -1);
    composeCodeableConcept(t, "Observation", "meaning", element.getMeaning(), -1);
    composeRange(t, "Observation", "age", element.getAge(), -1);
    composeString(t, "Observation", "text", element.getTextElement(), -1);
  }

  protected void composeObservationObservationRelatedComponent(Complex parent, String parentType, String name, Observation.ObservationRelatedComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "related", name, element, index);
    composeEnum(t, "Observation", "type", element.getTypeElement(), -1);
    composeReference(t, "Observation", "target", element.getTarget(), -1);
  }

  protected void composeObservationObservationComponentComponent(Complex parent, String parentType, String name, Observation.ObservationComponentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "component", name, element, index);
    composeCodeableConcept(t, "Observation", "code", element.getCode(), -1);
    composeType(t, "Observation", "value", element.getValue(), -1);
    composeCodeableConcept(t, "Observation", "dataAbsentReason", element.getDataAbsentReason(), -1);
    for (int i = 0; i < element.getReferenceRange().size(); i++)
      composeObservationObservationReferenceRangeComponent(t, "Observation", "referenceRange", element.getReferenceRange().get(i), i);
  }

  protected void composeOperationDefinition(Complex parent, String parentType, String name, OperationDefinition element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "OperationDefinition", name, element, index);
    composeUri(t, "OperationDefinition", "url", element.getUrlElement(), -1);
    composeString(t, "OperationDefinition", "version", element.getVersionElement(), -1);
    composeString(t, "OperationDefinition", "name", element.getNameElement(), -1);
    composeEnum(t, "OperationDefinition", "status", element.getStatusElement(), -1);
    composeEnum(t, "OperationDefinition", "kind", element.getKindElement(), -1);
    composeBoolean(t, "OperationDefinition", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "OperationDefinition", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeOperationDefinitionOperationDefinitionContactComponent(t, "OperationDefinition", "contact", element.getContact().get(i), i);
    composeDateTime(t, "OperationDefinition", "date", element.getDateElement(), -1);
    composeString(t, "OperationDefinition", "description", element.getDescriptionElement(), -1);
    composeString(t, "OperationDefinition", "requirements", element.getRequirementsElement(), -1);
    composeBoolean(t, "OperationDefinition", "idempotent", element.getIdempotentElement(), -1);
    composeCode(t, "OperationDefinition", "code", element.getCodeElement(), -1);
    composeString(t, "OperationDefinition", "notes", element.getNotesElement(), -1);
    composeReference(t, "OperationDefinition", "base", element.getBase(), -1);
    composeBoolean(t, "OperationDefinition", "system", element.getSystemElement(), -1);
    for (int i = 0; i < element.getType().size(); i++)
      composeCode(t, "OperationDefinition", "type", element.getType().get(i), i);
    composeBoolean(t, "OperationDefinition", "instance", element.getInstanceElement(), -1);
    for (int i = 0; i < element.getParameter().size(); i++)
      composeOperationDefinitionOperationDefinitionParameterComponent(t, "OperationDefinition", "parameter", element.getParameter().get(i), i);
  }

  protected void composeOperationDefinitionOperationDefinitionContactComponent(Complex parent, String parentType, String name, OperationDefinition.OperationDefinitionContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "OperationDefinition", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "OperationDefinition", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeOperationDefinitionOperationDefinitionParameterComponent(Complex parent, String parentType, String name, OperationDefinition.OperationDefinitionParameterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "parameter", name, element, index);
    composeCode(t, "OperationDefinition", "name", element.getNameElement(), -1);
    composeEnum(t, "OperationDefinition", "use", element.getUseElement(), -1);
    composeInteger(t, "OperationDefinition", "min", element.getMinElement(), -1);
    composeString(t, "OperationDefinition", "max", element.getMaxElement(), -1);
    composeString(t, "OperationDefinition", "documentation", element.getDocumentationElement(), -1);
    composeCode(t, "OperationDefinition", "type", element.getTypeElement(), -1);
    composeReference(t, "OperationDefinition", "profile", element.getProfile(), -1);
    composeOperationDefinitionOperationDefinitionParameterBindingComponent(t, "OperationDefinition", "binding", element.getBinding(), -1);
    for (int i = 0; i < element.getPart().size(); i++)
      composeOperationDefinitionOperationDefinitionParameterComponent(t, "OperationDefinition", "part", element.getPart().get(i), i);
  }

  protected void composeOperationDefinitionOperationDefinitionParameterBindingComponent(Complex parent, String parentType, String name, OperationDefinition.OperationDefinitionParameterBindingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "binding", name, element, index);
    composeEnum(t, "OperationDefinition", "strength", element.getStrengthElement(), -1);
    composeType(t, "OperationDefinition", "valueSet", element.getValueSet(), -1);
  }

  protected void composeOperationOutcome(Complex parent, String parentType, String name, OperationOutcome element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "OperationOutcome", name, element, index);
    for (int i = 0; i < element.getIssue().size(); i++)
      composeOperationOutcomeOperationOutcomeIssueComponent(t, "OperationOutcome", "issue", element.getIssue().get(i), i);
  }

  protected void composeOperationOutcomeOperationOutcomeIssueComponent(Complex parent, String parentType, String name, OperationOutcome.OperationOutcomeIssueComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "issue", name, element, index);
    composeEnum(t, "OperationOutcome", "severity", element.getSeverityElement(), -1);
    composeEnum(t, "OperationOutcome", "code", element.getCodeElement(), -1);
    composeCodeableConcept(t, "OperationOutcome", "details", element.getDetails(), -1);
    composeString(t, "OperationOutcome", "diagnostics", element.getDiagnosticsElement(), -1);
    for (int i = 0; i < element.getLocation().size(); i++)
      composeString(t, "OperationOutcome", "location", element.getLocation().get(i), i);
  }

  protected void composeOrder(Complex parent, String parentType, String name, Order element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Order", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Order", "identifier", element.getIdentifier().get(i), i);
    composeDateTime(t, "Order", "date", element.getDateElement(), -1);
    composeReference(t, "Order", "subject", element.getSubject(), -1);
    composeReference(t, "Order", "source", element.getSource(), -1);
    composeReference(t, "Order", "target", element.getTarget(), -1);
    composeType(t, "Order", "reason", element.getReason(), -1);
    composeOrderOrderWhenComponent(t, "Order", "when", element.getWhen(), -1);
    for (int i = 0; i < element.getDetail().size(); i++)
      composeReference(t, "Order", "detail", element.getDetail().get(i), i);
  }

  protected void composeOrderOrderWhenComponent(Complex parent, String parentType, String name, Order.OrderWhenComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "when", name, element, index);
    composeCodeableConcept(t, "Order", "code", element.getCode(), -1);
    composeTiming(t, "Order", "schedule", element.getSchedule(), -1);
  }

  protected void composeOrderResponse(Complex parent, String parentType, String name, OrderResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "OrderResponse", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "OrderResponse", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "OrderResponse", "request", element.getRequest(), -1);
    composeDateTime(t, "OrderResponse", "date", element.getDateElement(), -1);
    composeReference(t, "OrderResponse", "who", element.getWho(), -1);
    composeEnum(t, "OrderResponse", "orderStatus", element.getOrderStatusElement(), -1);
    composeString(t, "OrderResponse", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getFulfillment().size(); i++)
      composeReference(t, "OrderResponse", "fulfillment", element.getFulfillment().get(i), i);
  }

  protected void composeOrderSet(Complex parent, String parentType, String name, OrderSet element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "OrderSet", name, element, index);
    composeModuleMetadata(t, "OrderSet", "moduleMetadata", element.getModuleMetadata(), -1);
    for (int i = 0; i < element.getLibrary().size(); i++)
      composeReference(t, "OrderSet", "library", element.getLibrary().get(i), i);
    for (int i = 0; i < element.getItem().size(); i++)
      composeOrderSetOrderSetItemComponent(t, "OrderSet", "item", element.getItem().get(i), i);
  }

  protected void composeOrderSetOrderSetItemComponent(Complex parent, String parentType, String name, OrderSet.OrderSetItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composeIdentifier(t, "OrderSet", "identifier", element.getIdentifier(), -1);
    composeString(t, "OrderSet", "number", element.getNumberElement(), -1);
    composeString(t, "OrderSet", "title", element.getTitleElement(), -1);
    composeString(t, "OrderSet", "description", element.getDescriptionElement(), -1);
    composeString(t, "OrderSet", "textEquivalent", element.getTextEquivalentElement(), -1);
    for (int i = 0; i < element.getSupportingEvidence().size(); i++)
      composeAttachment(t, "OrderSet", "supportingEvidence", element.getSupportingEvidence().get(i), i);
    for (int i = 0; i < element.getDocumentation().size(); i++)
      composeAttachment(t, "OrderSet", "documentation", element.getDocumentation().get(i), i);
    for (int i = 0; i < element.getParticipantType().size(); i++)
      composeEnum(t, "OrderSet", "participantType", element.getParticipantType().get(i), i);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCodeableConcept(t, "OrderSet", "concept", element.getConcept().get(i), i);
    composeEnum(t, "OrderSet", "type", element.getTypeElement(), -1);
    composeEnum(t, "OrderSet", "groupingBehavior", element.getGroupingBehaviorElement(), -1);
    composeEnum(t, "OrderSet", "selectionBehavior", element.getSelectionBehaviorElement(), -1);
    composeEnum(t, "OrderSet", "requiredBehavior", element.getRequiredBehaviorElement(), -1);
    composeEnum(t, "OrderSet", "precheckBehavior", element.getPrecheckBehaviorElement(), -1);
    composeEnum(t, "OrderSet", "cardinalityBehavior", element.getCardinalityBehaviorElement(), -1);
    composeReference(t, "OrderSet", "resource", element.getResource(), -1);
    for (int i = 0; i < element.getCustomization().size(); i++)
      composeOrderSetOrderSetItemCustomizationComponent(t, "OrderSet", "customization", element.getCustomization().get(i), i);
    for (int i = 0; i < element.getItems().size(); i++)
      composeOrderSetOrderSetItemComponent(t, "OrderSet", "items", element.getItems().get(i), i);
  }

  protected void composeOrderSetOrderSetItemCustomizationComponent(Complex parent, String parentType, String name, OrderSet.OrderSetItemCustomizationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "customization", name, element, index);
    composeString(t, "OrderSet", "path", element.getPathElement(), -1);
    composeString(t, "OrderSet", "expression", element.getExpressionElement(), -1);
  }

  protected void composeOrganization(Complex parent, String parentType, String name, Organization element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Organization", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Organization", "identifier", element.getIdentifier().get(i), i);
    composeBoolean(t, "Organization", "active", element.getActiveElement(), -1);
    composeCodeableConcept(t, "Organization", "type", element.getType(), -1);
    composeString(t, "Organization", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Organization", "telecom", element.getTelecom().get(i), i);
    for (int i = 0; i < element.getAddress().size(); i++)
      composeAddress(t, "Organization", "address", element.getAddress().get(i), i);
    composeReference(t, "Organization", "partOf", element.getPartOf(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeOrganizationOrganizationContactComponent(t, "Organization", "contact", element.getContact().get(i), i);
  }

  protected void composeOrganizationOrganizationContactComponent(Complex parent, String parentType, String name, Organization.OrganizationContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeCodeableConcept(t, "Organization", "purpose", element.getPurpose(), -1);
    composeHumanName(t, "Organization", "name", element.getName(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Organization", "telecom", element.getTelecom().get(i), i);
    composeAddress(t, "Organization", "address", element.getAddress(), -1);
  }

  protected void composePatient(Complex parent, String parentType, String name, Patient element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Patient", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Patient", "identifier", element.getIdentifier().get(i), i);
    composeBoolean(t, "Patient", "active", element.getActiveElement(), -1);
    for (int i = 0; i < element.getName().size(); i++)
      composeHumanName(t, "Patient", "name", element.getName().get(i), i);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Patient", "telecom", element.getTelecom().get(i), i);
    composeEnum(t, "Patient", "gender", element.getGenderElement(), -1);
    composeDate(t, "Patient", "birthDate", element.getBirthDateElement(), -1);
    composeType(t, "Patient", "deceased", element.getDeceased(), -1);
    for (int i = 0; i < element.getAddress().size(); i++)
      composeAddress(t, "Patient", "address", element.getAddress().get(i), i);
    composeCodeableConcept(t, "Patient", "maritalStatus", element.getMaritalStatus(), -1);
    composeType(t, "Patient", "multipleBirth", element.getMultipleBirth(), -1);
    for (int i = 0; i < element.getPhoto().size(); i++)
      composeAttachment(t, "Patient", "photo", element.getPhoto().get(i), i);
    for (int i = 0; i < element.getContact().size(); i++)
      composePatientContactComponent(t, "Patient", "contact", element.getContact().get(i), i);
    composePatientAnimalComponent(t, "Patient", "animal", element.getAnimal(), -1);
    for (int i = 0; i < element.getCommunication().size(); i++)
      composePatientPatientCommunicationComponent(t, "Patient", "communication", element.getCommunication().get(i), i);
    for (int i = 0; i < element.getCareProvider().size(); i++)
      composeReference(t, "Patient", "careProvider", element.getCareProvider().get(i), i);
    composeReference(t, "Patient", "managingOrganization", element.getManagingOrganization(), -1);
    for (int i = 0; i < element.getLink().size(); i++)
      composePatientPatientLinkComponent(t, "Patient", "link", element.getLink().get(i), i);
  }

  protected void composePatientContactComponent(Complex parent, String parentType, String name, Patient.ContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    for (int i = 0; i < element.getRelationship().size(); i++)
      composeCodeableConcept(t, "Patient", "relationship", element.getRelationship().get(i), i);
    composeHumanName(t, "Patient", "name", element.getName(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Patient", "telecom", element.getTelecom().get(i), i);
    composeAddress(t, "Patient", "address", element.getAddress(), -1);
    composeEnum(t, "Patient", "gender", element.getGenderElement(), -1);
    composeReference(t, "Patient", "organization", element.getOrganization(), -1);
    composePeriod(t, "Patient", "period", element.getPeriod(), -1);
  }

  protected void composePatientAnimalComponent(Complex parent, String parentType, String name, Patient.AnimalComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "animal", name, element, index);
    composeCodeableConcept(t, "Patient", "species", element.getSpecies(), -1);
    composeCodeableConcept(t, "Patient", "breed", element.getBreed(), -1);
    composeCodeableConcept(t, "Patient", "genderStatus", element.getGenderStatus(), -1);
  }

  protected void composePatientPatientCommunicationComponent(Complex parent, String parentType, String name, Patient.PatientCommunicationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "communication", name, element, index);
    composeCodeableConcept(t, "Patient", "language", element.getLanguage(), -1);
    composeBoolean(t, "Patient", "preferred", element.getPreferredElement(), -1);
  }

  protected void composePatientPatientLinkComponent(Complex parent, String parentType, String name, Patient.PatientLinkComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "link", name, element, index);
    composeReference(t, "Patient", "other", element.getOther(), -1);
    composeEnum(t, "Patient", "type", element.getTypeElement(), -1);
  }

  protected void composePaymentNotice(Complex parent, String parentType, String name, PaymentNotice element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "PaymentNotice", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "PaymentNotice", "identifier", element.getIdentifier().get(i), i);
    composeCoding(t, "PaymentNotice", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "PaymentNotice", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "PaymentNotice", "created", element.getCreatedElement(), -1);
    composeReference(t, "PaymentNotice", "target", element.getTarget(), -1);
    composeReference(t, "PaymentNotice", "provider", element.getProvider(), -1);
    composeReference(t, "PaymentNotice", "organization", element.getOrganization(), -1);
    composeReference(t, "PaymentNotice", "request", element.getRequest(), -1);
    composeReference(t, "PaymentNotice", "response", element.getResponse(), -1);
    composeCoding(t, "PaymentNotice", "paymentStatus", element.getPaymentStatus(), -1);
    composeDate(t, "PaymentNotice", "statusDate", element.getStatusDateElement(), -1);
  }

  protected void composePaymentReconciliation(Complex parent, String parentType, String name, PaymentReconciliation element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "PaymentReconciliation", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "PaymentReconciliation", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "PaymentReconciliation", "request", element.getRequest(), -1);
    composeEnum(t, "PaymentReconciliation", "outcome", element.getOutcomeElement(), -1);
    composeString(t, "PaymentReconciliation", "disposition", element.getDispositionElement(), -1);
    composeCoding(t, "PaymentReconciliation", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "PaymentReconciliation", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "PaymentReconciliation", "created", element.getCreatedElement(), -1);
    composePeriod(t, "PaymentReconciliation", "period", element.getPeriod(), -1);
    composeReference(t, "PaymentReconciliation", "organization", element.getOrganization(), -1);
    composeReference(t, "PaymentReconciliation", "requestProvider", element.getRequestProvider(), -1);
    composeReference(t, "PaymentReconciliation", "requestOrganization", element.getRequestOrganization(), -1);
    for (int i = 0; i < element.getDetail().size(); i++)
      composePaymentReconciliationDetailsComponent(t, "PaymentReconciliation", "detail", element.getDetail().get(i), i);
    composeCoding(t, "PaymentReconciliation", "form", element.getForm(), -1);
    composeQuantity(t, "PaymentReconciliation", "total", element.getTotal(), -1);
    for (int i = 0; i < element.getNote().size(); i++)
      composePaymentReconciliationNotesComponent(t, "PaymentReconciliation", "note", element.getNote().get(i), i);
  }

  protected void composePaymentReconciliationDetailsComponent(Complex parent, String parentType, String name, PaymentReconciliation.DetailsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composeCoding(t, "PaymentReconciliation", "type", element.getType(), -1);
    composeReference(t, "PaymentReconciliation", "request", element.getRequest(), -1);
    composeReference(t, "PaymentReconciliation", "responce", element.getResponce(), -1);
    composeReference(t, "PaymentReconciliation", "submitter", element.getSubmitter(), -1);
    composeReference(t, "PaymentReconciliation", "payee", element.getPayee(), -1);
    composeDate(t, "PaymentReconciliation", "date", element.getDateElement(), -1);
    composeQuantity(t, "PaymentReconciliation", "amount", element.getAmount(), -1);
  }

  protected void composePaymentReconciliationNotesComponent(Complex parent, String parentType, String name, PaymentReconciliation.NotesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "note", name, element, index);
    composeCoding(t, "PaymentReconciliation", "type", element.getType(), -1);
    composeString(t, "PaymentReconciliation", "text", element.getTextElement(), -1);
  }

  protected void composePerson(Complex parent, String parentType, String name, Person element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Person", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Person", "identifier", element.getIdentifier().get(i), i);
    for (int i = 0; i < element.getName().size(); i++)
      composeHumanName(t, "Person", "name", element.getName().get(i), i);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Person", "telecom", element.getTelecom().get(i), i);
    composeEnum(t, "Person", "gender", element.getGenderElement(), -1);
    composeDate(t, "Person", "birthDate", element.getBirthDateElement(), -1);
    for (int i = 0; i < element.getAddress().size(); i++)
      composeAddress(t, "Person", "address", element.getAddress().get(i), i);
    composeAttachment(t, "Person", "photo", element.getPhoto(), -1);
    composeReference(t, "Person", "managingOrganization", element.getManagingOrganization(), -1);
    composeBoolean(t, "Person", "active", element.getActiveElement(), -1);
    for (int i = 0; i < element.getLink().size(); i++)
      composePersonPersonLinkComponent(t, "Person", "link", element.getLink().get(i), i);
  }

  protected void composePersonPersonLinkComponent(Complex parent, String parentType, String name, Person.PersonLinkComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "link", name, element, index);
    composeReference(t, "Person", "target", element.getTarget(), -1);
    composeEnum(t, "Person", "assurance", element.getAssuranceElement(), -1);
  }

  protected void composePractitioner(Complex parent, String parentType, String name, Practitioner element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Practitioner", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Practitioner", "identifier", element.getIdentifier().get(i), i);
    composeBoolean(t, "Practitioner", "active", element.getActiveElement(), -1);
    composeHumanName(t, "Practitioner", "name", element.getName(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Practitioner", "telecom", element.getTelecom().get(i), i);
    for (int i = 0; i < element.getAddress().size(); i++)
      composeAddress(t, "Practitioner", "address", element.getAddress().get(i), i);
    composeEnum(t, "Practitioner", "gender", element.getGenderElement(), -1);
    composeDate(t, "Practitioner", "birthDate", element.getBirthDateElement(), -1);
    for (int i = 0; i < element.getPhoto().size(); i++)
      composeAttachment(t, "Practitioner", "photo", element.getPhoto().get(i), i);
    for (int i = 0; i < element.getPractitionerRole().size(); i++)
      composePractitionerPractitionerPractitionerRoleComponent(t, "Practitioner", "practitionerRole", element.getPractitionerRole().get(i), i);
    for (int i = 0; i < element.getQualification().size(); i++)
      composePractitionerPractitionerQualificationComponent(t, "Practitioner", "qualification", element.getQualification().get(i), i);
    for (int i = 0; i < element.getCommunication().size(); i++)
      composeCodeableConcept(t, "Practitioner", "communication", element.getCommunication().get(i), i);
  }

  protected void composePractitionerPractitionerPractitionerRoleComponent(Complex parent, String parentType, String name, Practitioner.PractitionerPractitionerRoleComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "practitionerRole", name, element, index);
    composeReference(t, "Practitioner", "managingOrganization", element.getManagingOrganization(), -1);
    composeCodeableConcept(t, "Practitioner", "role", element.getRole(), -1);
    for (int i = 0; i < element.getSpecialty().size(); i++)
      composeCodeableConcept(t, "Practitioner", "specialty", element.getSpecialty().get(i), i);
    composePeriod(t, "Practitioner", "period", element.getPeriod(), -1);
    for (int i = 0; i < element.getLocation().size(); i++)
      composeReference(t, "Practitioner", "location", element.getLocation().get(i), i);
    for (int i = 0; i < element.getHealthcareService().size(); i++)
      composeReference(t, "Practitioner", "healthcareService", element.getHealthcareService().get(i), i);
  }

  protected void composePractitionerPractitionerQualificationComponent(Complex parent, String parentType, String name, Practitioner.PractitionerQualificationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "qualification", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Practitioner", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "Practitioner", "code", element.getCode(), -1);
    composePeriod(t, "Practitioner", "period", element.getPeriod(), -1);
    composeReference(t, "Practitioner", "issuer", element.getIssuer(), -1);
  }

  protected void composeProcedure(Complex parent, String parentType, String name, Procedure element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Procedure", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Procedure", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "Procedure", "subject", element.getSubject(), -1);
    composeEnum(t, "Procedure", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "Procedure", "category", element.getCategory(), -1);
    composeCodeableConcept(t, "Procedure", "code", element.getCode(), -1);
    composeBoolean(t, "Procedure", "notPerformed", element.getNotPerformedElement(), -1);
    for (int i = 0; i < element.getReasonNotPerformed().size(); i++)
      composeCodeableConcept(t, "Procedure", "reasonNotPerformed", element.getReasonNotPerformed().get(i), i);
    for (int i = 0; i < element.getBodySite().size(); i++)
      composeCodeableConcept(t, "Procedure", "bodySite", element.getBodySite().get(i), i);
    composeType(t, "Procedure", "reason", element.getReason(), -1);
    for (int i = 0; i < element.getPerformer().size(); i++)
      composeProcedureProcedurePerformerComponent(t, "Procedure", "performer", element.getPerformer().get(i), i);
    composeType(t, "Procedure", "performed", element.getPerformed(), -1);
    composeReference(t, "Procedure", "encounter", element.getEncounter(), -1);
    composeReference(t, "Procedure", "location", element.getLocation(), -1);
    composeCodeableConcept(t, "Procedure", "outcome", element.getOutcome(), -1);
    for (int i = 0; i < element.getReport().size(); i++)
      composeReference(t, "Procedure", "report", element.getReport().get(i), i);
    for (int i = 0; i < element.getComplication().size(); i++)
      composeCodeableConcept(t, "Procedure", "complication", element.getComplication().get(i), i);
    for (int i = 0; i < element.getFollowUp().size(); i++)
      composeCodeableConcept(t, "Procedure", "followUp", element.getFollowUp().get(i), i);
    composeReference(t, "Procedure", "request", element.getRequest(), -1);
    for (int i = 0; i < element.getNotes().size(); i++)
      composeAnnotation(t, "Procedure", "notes", element.getNotes().get(i), i);
    for (int i = 0; i < element.getFocalDevice().size(); i++)
      composeProcedureProcedureFocalDeviceComponent(t, "Procedure", "focalDevice", element.getFocalDevice().get(i), i);
    for (int i = 0; i < element.getUsed().size(); i++)
      composeReference(t, "Procedure", "used", element.getUsed().get(i), i);
  }

  protected void composeProcedureProcedurePerformerComponent(Complex parent, String parentType, String name, Procedure.ProcedurePerformerComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "performer", name, element, index);
    composeReference(t, "Procedure", "actor", element.getActor(), -1);
    composeCodeableConcept(t, "Procedure", "role", element.getRole(), -1);
  }

  protected void composeProcedureProcedureFocalDeviceComponent(Complex parent, String parentType, String name, Procedure.ProcedureFocalDeviceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "focalDevice", name, element, index);
    composeCodeableConcept(t, "Procedure", "action", element.getAction(), -1);
    composeReference(t, "Procedure", "manipulated", element.getManipulated(), -1);
  }

  protected void composeProcedureRequest(Complex parent, String parentType, String name, ProcedureRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ProcedureRequest", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ProcedureRequest", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "ProcedureRequest", "subject", element.getSubject(), -1);
    composeCodeableConcept(t, "ProcedureRequest", "code", element.getCode(), -1);
    for (int i = 0; i < element.getBodySite().size(); i++)
      composeCodeableConcept(t, "ProcedureRequest", "bodySite", element.getBodySite().get(i), i);
    composeType(t, "ProcedureRequest", "reason", element.getReason(), -1);
    composeType(t, "ProcedureRequest", "scheduled", element.getScheduled(), -1);
    composeReference(t, "ProcedureRequest", "encounter", element.getEncounter(), -1);
    composeReference(t, "ProcedureRequest", "performer", element.getPerformer(), -1);
    composeEnum(t, "ProcedureRequest", "status", element.getStatusElement(), -1);
    for (int i = 0; i < element.getNotes().size(); i++)
      composeAnnotation(t, "ProcedureRequest", "notes", element.getNotes().get(i), i);
    composeType(t, "ProcedureRequest", "asNeeded", element.getAsNeeded(), -1);
    composeDateTime(t, "ProcedureRequest", "orderedOn", element.getOrderedOnElement(), -1);
    composeReference(t, "ProcedureRequest", "orderer", element.getOrderer(), -1);
    composeEnum(t, "ProcedureRequest", "priority", element.getPriorityElement(), -1);
  }

  protected void composeProcessRequest(Complex parent, String parentType, String name, ProcessRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ProcessRequest", name, element, index);
    composeEnum(t, "ProcessRequest", "action", element.getActionElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ProcessRequest", "identifier", element.getIdentifier().get(i), i);
    composeCoding(t, "ProcessRequest", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "ProcessRequest", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "ProcessRequest", "created", element.getCreatedElement(), -1);
    composeReference(t, "ProcessRequest", "target", element.getTarget(), -1);
    composeReference(t, "ProcessRequest", "provider", element.getProvider(), -1);
    composeReference(t, "ProcessRequest", "organization", element.getOrganization(), -1);
    composeReference(t, "ProcessRequest", "request", element.getRequest(), -1);
    composeReference(t, "ProcessRequest", "response", element.getResponse(), -1);
    composeBoolean(t, "ProcessRequest", "nullify", element.getNullifyElement(), -1);
    composeString(t, "ProcessRequest", "reference", element.getReferenceElement(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeProcessRequestItemsComponent(t, "ProcessRequest", "item", element.getItem().get(i), i);
    for (int i = 0; i < element.getInclude().size(); i++)
      composeString(t, "ProcessRequest", "include", element.getInclude().get(i), i);
    for (int i = 0; i < element.getExclude().size(); i++)
      composeString(t, "ProcessRequest", "exclude", element.getExclude().get(i), i);
    composePeriod(t, "ProcessRequest", "period", element.getPeriod(), -1);
  }

  protected void composeProcessRequestItemsComponent(Complex parent, String parentType, String name, ProcessRequest.ItemsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composeInteger(t, "ProcessRequest", "sequenceLinkId", element.getSequenceLinkIdElement(), -1);
  }

  protected void composeProcessResponse(Complex parent, String parentType, String name, ProcessResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ProcessResponse", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ProcessResponse", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "ProcessResponse", "request", element.getRequest(), -1);
    composeCoding(t, "ProcessResponse", "outcome", element.getOutcome(), -1);
    composeString(t, "ProcessResponse", "disposition", element.getDispositionElement(), -1);
    composeCoding(t, "ProcessResponse", "ruleset", element.getRuleset(), -1);
    composeCoding(t, "ProcessResponse", "originalRuleset", element.getOriginalRuleset(), -1);
    composeDateTime(t, "ProcessResponse", "created", element.getCreatedElement(), -1);
    composeReference(t, "ProcessResponse", "organization", element.getOrganization(), -1);
    composeReference(t, "ProcessResponse", "requestProvider", element.getRequestProvider(), -1);
    composeReference(t, "ProcessResponse", "requestOrganization", element.getRequestOrganization(), -1);
    composeCoding(t, "ProcessResponse", "form", element.getForm(), -1);
    for (int i = 0; i < element.getNotes().size(); i++)
      composeProcessResponseProcessResponseNotesComponent(t, "ProcessResponse", "notes", element.getNotes().get(i), i);
    for (int i = 0; i < element.getError().size(); i++)
      composeCoding(t, "ProcessResponse", "error", element.getError().get(i), i);
  }

  protected void composeProcessResponseProcessResponseNotesComponent(Complex parent, String parentType, String name, ProcessResponse.ProcessResponseNotesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "notes", name, element, index);
    composeCoding(t, "ProcessResponse", "type", element.getType(), -1);
    composeString(t, "ProcessResponse", "text", element.getTextElement(), -1);
  }

  protected void composeProtocol(Complex parent, String parentType, String name, Protocol element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Protocol", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Protocol", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Protocol", "title", element.getTitleElement(), -1);
    composeEnum(t, "Protocol", "status", element.getStatusElement(), -1);
    composeEnum(t, "Protocol", "type", element.getTypeElement(), -1);
    composeReference(t, "Protocol", "subject", element.getSubject(), -1);
    composeReference(t, "Protocol", "group", element.getGroup(), -1);
    composeString(t, "Protocol", "purpose", element.getPurposeElement(), -1);
    composeReference(t, "Protocol", "author", element.getAuthor(), -1);
    for (int i = 0; i < element.getStep().size(); i++)
      composeProtocolProtocolStepComponent(t, "Protocol", "step", element.getStep().get(i), i);
  }

  protected void composeProtocolProtocolStepComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "step", name, element, index);
    composeString(t, "Protocol", "name", element.getNameElement(), -1);
    composeString(t, "Protocol", "description", element.getDescriptionElement(), -1);
    composeQuantity(t, "Protocol", "duration", element.getDuration(), -1);
    composeProtocolProtocolStepPreconditionComponent(t, "Protocol", "precondition", element.getPrecondition(), -1);
    composeProtocolProtocolStepPreconditionComponent(t, "Protocol", "exit", element.getExit(), -1);
    composeUri(t, "Protocol", "firstActivity", element.getFirstActivityElement(), -1);
    for (int i = 0; i < element.getActivity().size(); i++)
      composeProtocolProtocolStepActivityComponent(t, "Protocol", "activity", element.getActivity().get(i), i);
    for (int i = 0; i < element.getNext().size(); i++)
      composeProtocolProtocolStepNextComponent(t, "Protocol", "next", element.getNext().get(i), i);
  }

  protected void composeProtocolProtocolStepPreconditionComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepPreconditionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "precondition", name, element, index);
    composeString(t, "Protocol", "description", element.getDescriptionElement(), -1);
    composeProtocolProtocolStepPreconditionConditionComponent(t, "Protocol", "condition", element.getCondition(), -1);
    for (int i = 0; i < element.getIntersection().size(); i++)
      composeProtocolProtocolStepPreconditionComponent(t, "Protocol", "intersection", element.getIntersection().get(i), i);
    for (int i = 0; i < element.getUnion().size(); i++)
      composeProtocolProtocolStepPreconditionComponent(t, "Protocol", "union", element.getUnion().get(i), i);
    for (int i = 0; i < element.getExclude().size(); i++)
      composeProtocolProtocolStepPreconditionComponent(t, "Protocol", "exclude", element.getExclude().get(i), i);
  }

  protected void composeProtocolProtocolStepPreconditionConditionComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepPreconditionConditionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "condition", name, element, index);
    composeCodeableConcept(t, "Protocol", "type", element.getType(), -1);
    composeType(t, "Protocol", "value", element.getValue(), -1);
  }

  protected void composeProtocolProtocolStepActivityComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepActivityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "activity", name, element, index);
    for (int i = 0; i < element.getAlternative().size(); i++)
      composeUri(t, "Protocol", "alternative", element.getAlternative().get(i), i);
    for (int i = 0; i < element.getComponent().size(); i++)
      composeProtocolProtocolStepActivityComponentComponent(t, "Protocol", "component", element.getComponent().get(i), i);
    for (int i = 0; i < element.getFollowing().size(); i++)
      composeUri(t, "Protocol", "following", element.getFollowing().get(i), i);
    composeQuantity(t, "Protocol", "wait", element.getWait(), -1);
    composeProtocolProtocolStepActivityDetailComponent(t, "Protocol", "detail", element.getDetail(), -1);
  }

  protected void composeProtocolProtocolStepActivityComponentComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepActivityComponentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "component", name, element, index);
    composeInteger(t, "Protocol", "sequence", element.getSequenceElement(), -1);
    composeUri(t, "Protocol", "activity", element.getActivityElement(), -1);
  }

  protected void composeProtocolProtocolStepActivityDetailComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepActivityDetailComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "detail", name, element, index);
    composeEnum(t, "Protocol", "category", element.getCategoryElement(), -1);
    composeCodeableConcept(t, "Protocol", "code", element.getCode(), -1);
    composeType(t, "Protocol", "timing", element.getTiming(), -1);
    composeReference(t, "Protocol", "location", element.getLocation(), -1);
    for (int i = 0; i < element.getPerformer().size(); i++)
      composeReference(t, "Protocol", "performer", element.getPerformer().get(i), i);
    composeReference(t, "Protocol", "product", element.getProduct(), -1);
    composeQuantity(t, "Protocol", "quantity", element.getQuantity(), -1);
    composeString(t, "Protocol", "description", element.getDescriptionElement(), -1);
  }

  protected void composeProtocolProtocolStepNextComponent(Complex parent, String parentType, String name, Protocol.ProtocolStepNextComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "next", name, element, index);
    composeString(t, "Protocol", "description", element.getDescriptionElement(), -1);
    composeUri(t, "Protocol", "reference", element.getReferenceElement(), -1);
    composeProtocolProtocolStepPreconditionComponent(t, "Protocol", "condition", element.getCondition(), -1);
  }

  protected void composeProvenance(Complex parent, String parentType, String name, Provenance element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Provenance", name, element, index);
    for (int i = 0; i < element.getTarget().size(); i++)
      composeReference(t, "Provenance", "target", element.getTarget().get(i), i);
    composePeriod(t, "Provenance", "period", element.getPeriod(), -1);
    composeInstant(t, "Provenance", "recorded", element.getRecordedElement(), -1);
    for (int i = 0; i < element.getReason().size(); i++)
      composeCoding(t, "Provenance", "reason", element.getReason().get(i), i);
    composeCoding(t, "Provenance", "activity", element.getActivity(), -1);
    composeReference(t, "Provenance", "location", element.getLocation(), -1);
    for (int i = 0; i < element.getPolicy().size(); i++)
      composeUri(t, "Provenance", "policy", element.getPolicy().get(i), i);
    for (int i = 0; i < element.getAgent().size(); i++)
      composeProvenanceProvenanceAgentComponent(t, "Provenance", "agent", element.getAgent().get(i), i);
    for (int i = 0; i < element.getEntity().size(); i++)
      composeProvenanceProvenanceEntityComponent(t, "Provenance", "entity", element.getEntity().get(i), i);
    for (int i = 0; i < element.getSignature().size(); i++)
      composeSignature(t, "Provenance", "signature", element.getSignature().get(i), i);
  }

  protected void composeProvenanceProvenanceAgentComponent(Complex parent, String parentType, String name, Provenance.ProvenanceAgentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "agent", name, element, index);
    composeCoding(t, "Provenance", "role", element.getRole(), -1);
    composeReference(t, "Provenance", "actor", element.getActor(), -1);
    composeIdentifier(t, "Provenance", "userId", element.getUserId(), -1);
    for (int i = 0; i < element.getRelatedAgent().size(); i++)
      composeProvenanceProvenanceAgentRelatedAgentComponent(t, "Provenance", "relatedAgent", element.getRelatedAgent().get(i), i);
  }

  protected void composeProvenanceProvenanceAgentRelatedAgentComponent(Complex parent, String parentType, String name, Provenance.ProvenanceAgentRelatedAgentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "relatedAgent", name, element, index);
    composeCodeableConcept(t, "Provenance", "type", element.getType(), -1);
    composeUri(t, "Provenance", "target", element.getTargetElement(), -1);
  }

  protected void composeProvenanceProvenanceEntityComponent(Complex parent, String parentType, String name, Provenance.ProvenanceEntityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "entity", name, element, index);
    composeEnum(t, "Provenance", "role", element.getRoleElement(), -1);
    composeCoding(t, "Provenance", "type", element.getType(), -1);
    composeUri(t, "Provenance", "reference", element.getReferenceElement(), -1);
    composeString(t, "Provenance", "display", element.getDisplayElement(), -1);
    composeProvenanceProvenanceAgentComponent(t, "Provenance", "agent", element.getAgent(), -1);
  }

  protected void composeQuestionnaire(Complex parent, String parentType, String name, Questionnaire element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Questionnaire", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Questionnaire", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Questionnaire", "version", element.getVersionElement(), -1);
    composeEnum(t, "Questionnaire", "status", element.getStatusElement(), -1);
    composeDateTime(t, "Questionnaire", "date", element.getDateElement(), -1);
    composeString(t, "Questionnaire", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "Questionnaire", "telecom", element.getTelecom().get(i), i);
    composeString(t, "Questionnaire", "title", element.getTitleElement(), -1);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCoding(t, "Questionnaire", "concept", element.getConcept().get(i), i);
    for (int i = 0; i < element.getSubjectType().size(); i++)
      composeCode(t, "Questionnaire", "subjectType", element.getSubjectType().get(i), i);
    for (int i = 0; i < element.getItem().size(); i++)
      composeQuestionnaireQuestionnaireItemComponent(t, "Questionnaire", "item", element.getItem().get(i), i);
  }

  protected void composeQuestionnaireQuestionnaireItemComponent(Complex parent, String parentType, String name, Questionnaire.QuestionnaireItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composeString(t, "Questionnaire", "linkId", element.getLinkIdElement(), -1);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeCoding(t, "Questionnaire", "concept", element.getConcept().get(i), i);
    composeString(t, "Questionnaire", "text", element.getTextElement(), -1);
    composeEnum(t, "Questionnaire", "type", element.getTypeElement(), -1);
    composeBoolean(t, "Questionnaire", "required", element.getRequiredElement(), -1);
    composeBoolean(t, "Questionnaire", "repeats", element.getRepeatsElement(), -1);
    composeReference(t, "Questionnaire", "options", element.getOptions(), -1);
    for (int i = 0; i < element.getOption().size(); i++)
      composeCoding(t, "Questionnaire", "option", element.getOption().get(i), i);
    for (int i = 0; i < element.getItem().size(); i++)
      composeQuestionnaireQuestionnaireItemComponent(t, "Questionnaire", "item", element.getItem().get(i), i);
  }

  protected void composeQuestionnaireResponse(Complex parent, String parentType, String name, QuestionnaireResponse element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "QuestionnaireResponse", name, element, index);
    composeIdentifier(t, "QuestionnaireResponse", "identifier", element.getIdentifier(), -1);
    composeReference(t, "QuestionnaireResponse", "questionnaire", element.getQuestionnaire(), -1);
    composeEnum(t, "QuestionnaireResponse", "status", element.getStatusElement(), -1);
    composeReference(t, "QuestionnaireResponse", "subject", element.getSubject(), -1);
    composeReference(t, "QuestionnaireResponse", "author", element.getAuthor(), -1);
    composeDateTime(t, "QuestionnaireResponse", "authored", element.getAuthoredElement(), -1);
    composeReference(t, "QuestionnaireResponse", "source", element.getSource(), -1);
    composeReference(t, "QuestionnaireResponse", "encounter", element.getEncounter(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeQuestionnaireResponseQuestionnaireResponseItemComponent(t, "QuestionnaireResponse", "item", element.getItem().get(i), i);
  }

  protected void composeQuestionnaireResponseQuestionnaireResponseItemComponent(Complex parent, String parentType, String name, QuestionnaireResponse.QuestionnaireResponseItemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "item", name, element, index);
    composeString(t, "QuestionnaireResponse", "linkId", element.getLinkIdElement(), -1);
    composeString(t, "QuestionnaireResponse", "text", element.getTextElement(), -1);
    composeReference(t, "QuestionnaireResponse", "subject", element.getSubject(), -1);
    for (int i = 0; i < element.getAnswer().size(); i++)
      composeQuestionnaireResponseQuestionnaireResponseItemAnswerComponent(t, "QuestionnaireResponse", "answer", element.getAnswer().get(i), i);
    for (int i = 0; i < element.getItem().size(); i++)
      composeQuestionnaireResponseQuestionnaireResponseItemComponent(t, "QuestionnaireResponse", "item", element.getItem().get(i), i);
  }

  protected void composeQuestionnaireResponseQuestionnaireResponseItemAnswerComponent(Complex parent, String parentType, String name, QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "answer", name, element, index);
    composeType(t, "QuestionnaireResponse", "value", element.getValue(), -1);
    for (int i = 0; i < element.getItem().size(); i++)
      composeQuestionnaireResponseQuestionnaireResponseItemComponent(t, "QuestionnaireResponse", "item", element.getItem().get(i), i);
  }

  protected void composeReferralRequest(Complex parent, String parentType, String name, ReferralRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ReferralRequest", name, element, index);
    composeEnum(t, "ReferralRequest", "status", element.getStatusElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "ReferralRequest", "identifier", element.getIdentifier().get(i), i);
    composeDateTime(t, "ReferralRequest", "date", element.getDateElement(), -1);
    composeCodeableConcept(t, "ReferralRequest", "type", element.getType(), -1);
    composeCodeableConcept(t, "ReferralRequest", "specialty", element.getSpecialty(), -1);
    composeCodeableConcept(t, "ReferralRequest", "priority", element.getPriority(), -1);
    composeReference(t, "ReferralRequest", "patient", element.getPatient(), -1);
    composeReference(t, "ReferralRequest", "requester", element.getRequester(), -1);
    for (int i = 0; i < element.getRecipient().size(); i++)
      composeReference(t, "ReferralRequest", "recipient", element.getRecipient().get(i), i);
    composeReference(t, "ReferralRequest", "encounter", element.getEncounter(), -1);
    composeDateTime(t, "ReferralRequest", "dateSent", element.getDateSentElement(), -1);
    composeCodeableConcept(t, "ReferralRequest", "reason", element.getReason(), -1);
    composeString(t, "ReferralRequest", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getServiceRequested().size(); i++)
      composeCodeableConcept(t, "ReferralRequest", "serviceRequested", element.getServiceRequested().get(i), i);
    for (int i = 0; i < element.getSupportingInformation().size(); i++)
      composeReference(t, "ReferralRequest", "supportingInformation", element.getSupportingInformation().get(i), i);
    composePeriod(t, "ReferralRequest", "fulfillmentTime", element.getFulfillmentTime(), -1);
  }

  protected void composeRelatedPerson(Complex parent, String parentType, String name, RelatedPerson element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "RelatedPerson", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "RelatedPerson", "identifier", element.getIdentifier().get(i), i);
    composeReference(t, "RelatedPerson", "patient", element.getPatient(), -1);
    composeCodeableConcept(t, "RelatedPerson", "relationship", element.getRelationship(), -1);
    composeHumanName(t, "RelatedPerson", "name", element.getName(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "RelatedPerson", "telecom", element.getTelecom().get(i), i);
    composeEnum(t, "RelatedPerson", "gender", element.getGenderElement(), -1);
    composeDate(t, "RelatedPerson", "birthDate", element.getBirthDateElement(), -1);
    for (int i = 0; i < element.getAddress().size(); i++)
      composeAddress(t, "RelatedPerson", "address", element.getAddress().get(i), i);
    for (int i = 0; i < element.getPhoto().size(); i++)
      composeAttachment(t, "RelatedPerson", "photo", element.getPhoto().get(i), i);
    composePeriod(t, "RelatedPerson", "period", element.getPeriod(), -1);
  }

  protected void composeRiskAssessment(Complex parent, String parentType, String name, RiskAssessment element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "RiskAssessment", name, element, index);
    composeReference(t, "RiskAssessment", "subject", element.getSubject(), -1);
    composeDateTime(t, "RiskAssessment", "date", element.getDateElement(), -1);
    composeReference(t, "RiskAssessment", "condition", element.getCondition(), -1);
    composeReference(t, "RiskAssessment", "encounter", element.getEncounter(), -1);
    composeReference(t, "RiskAssessment", "performer", element.getPerformer(), -1);
    composeIdentifier(t, "RiskAssessment", "identifier", element.getIdentifier(), -1);
    composeCodeableConcept(t, "RiskAssessment", "method", element.getMethod(), -1);
    for (int i = 0; i < element.getBasis().size(); i++)
      composeReference(t, "RiskAssessment", "basis", element.getBasis().get(i), i);
    for (int i = 0; i < element.getPrediction().size(); i++)
      composeRiskAssessmentRiskAssessmentPredictionComponent(t, "RiskAssessment", "prediction", element.getPrediction().get(i), i);
    composeString(t, "RiskAssessment", "mitigation", element.getMitigationElement(), -1);
  }

  protected void composeRiskAssessmentRiskAssessmentPredictionComponent(Complex parent, String parentType, String name, RiskAssessment.RiskAssessmentPredictionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "prediction", name, element, index);
    composeCodeableConcept(t, "RiskAssessment", "outcome", element.getOutcome(), -1);
    composeType(t, "RiskAssessment", "probability", element.getProbability(), -1);
    composeDecimal(t, "RiskAssessment", "relativeRisk", element.getRelativeRiskElement(), -1);
    composeType(t, "RiskAssessment", "when", element.getWhen(), -1);
    composeString(t, "RiskAssessment", "rationale", element.getRationaleElement(), -1);
  }

  protected void composeSchedule(Complex parent, String parentType, String name, Schedule element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Schedule", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Schedule", "identifier", element.getIdentifier().get(i), i);
    for (int i = 0; i < element.getType().size(); i++)
      composeCodeableConcept(t, "Schedule", "type", element.getType().get(i), i);
    composeReference(t, "Schedule", "actor", element.getActor(), -1);
    composePeriod(t, "Schedule", "planningHorizon", element.getPlanningHorizon(), -1);
    composeString(t, "Schedule", "comment", element.getCommentElement(), -1);
  }

  protected void composeSearchParameter(Complex parent, String parentType, String name, SearchParameter element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "SearchParameter", name, element, index);
    composeUri(t, "SearchParameter", "url", element.getUrlElement(), -1);
    composeString(t, "SearchParameter", "name", element.getNameElement(), -1);
    composeEnum(t, "SearchParameter", "status", element.getStatusElement(), -1);
    composeBoolean(t, "SearchParameter", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "SearchParameter", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeSearchParameterSearchParameterContactComponent(t, "SearchParameter", "contact", element.getContact().get(i), i);
    composeDateTime(t, "SearchParameter", "date", element.getDateElement(), -1);
    composeString(t, "SearchParameter", "requirements", element.getRequirementsElement(), -1);
    composeCode(t, "SearchParameter", "code", element.getCodeElement(), -1);
    composeCode(t, "SearchParameter", "base", element.getBaseElement(), -1);
    composeEnum(t, "SearchParameter", "type", element.getTypeElement(), -1);
    composeString(t, "SearchParameter", "description", element.getDescriptionElement(), -1);
    composeString(t, "SearchParameter", "xpath", element.getXpathElement(), -1);
    composeEnum(t, "SearchParameter", "xpathUsage", element.getXpathUsageElement(), -1);
    for (int i = 0; i < element.getTarget().size(); i++)
      composeCode(t, "SearchParameter", "target", element.getTarget().get(i), i);
  }

  protected void composeSearchParameterSearchParameterContactComponent(Complex parent, String parentType, String name, SearchParameter.SearchParameterContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "SearchParameter", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "SearchParameter", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeSequence(Complex parent, String parentType, String name, Sequence element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Sequence", name, element, index);
    composeEnum(t, "Sequence", "type", element.getTypeElement(), -1);
    for (int i = 0; i < element.getVariationID().size(); i++)
      composeCodeableConcept(t, "Sequence", "variationID", element.getVariationID().get(i), i);
    composeCodeableConcept(t, "Sequence", "referenceSeq", element.getReferenceSeq(), -1);
    composeQuantity(t, "Sequence", "quantity", element.getQuantity(), -1);
    for (int i = 0; i < element.getCoordinate().size(); i++)
      composeSequenceSequenceCoordinateComponent(t, "Sequence", "coordinate", element.getCoordinate().get(i), i);
    composeCodeableConcept(t, "Sequence", "species", element.getSpecies(), -1);
    composeString(t, "Sequence", "observedAllele", element.getObservedAlleleElement(), -1);
    composeString(t, "Sequence", "referenceAllele", element.getReferenceAlleleElement(), -1);
    composeString(t, "Sequence", "cigar", element.getCigarElement(), -1);
    for (int i = 0; i < element.getQuality().size(); i++)
      composeSequenceSequenceQualityComponent(t, "Sequence", "quality", element.getQuality().get(i), i);
    composeCodeableConcept(t, "Sequence", "allelicState", element.getAllelicState(), -1);
    composeDecimal(t, "Sequence", "allelicFrequency", element.getAllelicFrequencyElement(), -1);
    composeCodeableConcept(t, "Sequence", "copyNumberEvent", element.getCopyNumberEvent(), -1);
    composeInteger(t, "Sequence", "readCoverage", element.getReadCoverageElement(), -1);
    composeSequenceSequenceChipComponent(t, "Sequence", "chip", element.getChip(), -1);
    for (int i = 0; i < element.getRepository().size(); i++)
      composeSequenceSequenceRepositoryComponent(t, "Sequence", "repository", element.getRepository().get(i), i);
  }

  protected void composeSequenceSequenceCoordinateComponent(Complex parent, String parentType, String name, Sequence.SequenceCoordinateComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "coordinate", name, element, index);
    composeCodeableConcept(t, "Sequence", "chromosome", element.getChromosome(), -1);
    composeInteger(t, "Sequence", "start", element.getStartElement(), -1);
    composeInteger(t, "Sequence", "end", element.getEndElement(), -1);
    composeString(t, "Sequence", "genomeBuild", element.getGenomeBuildElement(), -1);
  }

  protected void composeSequenceSequenceQualityComponent(Complex parent, String parentType, String name, Sequence.SequenceQualityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "quality", name, element, index);
    composeInteger(t, "Sequence", "start", element.getStartElement(), -1);
    composeInteger(t, "Sequence", "end", element.getEndElement(), -1);
    composeQuantity(t, "Sequence", "score", element.getScore(), -1);
    composeString(t, "Sequence", "platform", element.getPlatformElement(), -1);
  }

  protected void composeSequenceSequenceChipComponent(Complex parent, String parentType, String name, Sequence.SequenceChipComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "chip", name, element, index);
    composeString(t, "Sequence", "chipId", element.getChipIdElement(), -1);
    composeString(t, "Sequence", "manufacturerId", element.getManufacturerIdElement(), -1);
    composeString(t, "Sequence", "version", element.getVersionElement(), -1);
  }

  protected void composeSequenceSequenceRepositoryComponent(Complex parent, String parentType, String name, Sequence.SequenceRepositoryComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "repository", name, element, index);
    composeUri(t, "Sequence", "url", element.getUrlElement(), -1);
    composeString(t, "Sequence", "name", element.getNameElement(), -1);
    composeUri(t, "Sequence", "structure", element.getStructureElement(), -1);
    composeString(t, "Sequence", "variantId", element.getVariantIdElement(), -1);
    composeString(t, "Sequence", "readGroupSetId", element.getReadGroupSetIdElement(), -1);
  }

  protected void composeSlot(Complex parent, String parentType, String name, Slot element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Slot", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Slot", "identifier", element.getIdentifier().get(i), i);
    composeCodeableConcept(t, "Slot", "type", element.getType(), -1);
    composeReference(t, "Slot", "schedule", element.getSchedule(), -1);
    composeEnum(t, "Slot", "freeBusyType", element.getFreeBusyTypeElement(), -1);
    composeInstant(t, "Slot", "start", element.getStartElement(), -1);
    composeInstant(t, "Slot", "end", element.getEndElement(), -1);
    composeBoolean(t, "Slot", "overbooked", element.getOverbookedElement(), -1);
    composeString(t, "Slot", "comment", element.getCommentElement(), -1);
  }

  protected void composeSpecimen(Complex parent, String parentType, String name, Specimen element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Specimen", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Specimen", "identifier", element.getIdentifier().get(i), i);
    composeIdentifier(t, "Specimen", "accessionIdentifier", element.getAccessionIdentifier(), -1);
    composeEnum(t, "Specimen", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "Specimen", "type", element.getType(), -1);
    composeReference(t, "Specimen", "subject", element.getSubject(), -1);
    composeDateTime(t, "Specimen", "receivedTime", element.getReceivedTimeElement(), -1);
    for (int i = 0; i < element.getParent().size(); i++)
      composeReference(t, "Specimen", "parent", element.getParent().get(i), i);
    composeSpecimenSpecimenCollectionComponent(t, "Specimen", "collection", element.getCollection(), -1);
    for (int i = 0; i < element.getTreatment().size(); i++)
      composeSpecimenSpecimenTreatmentComponent(t, "Specimen", "treatment", element.getTreatment().get(i), i);
    for (int i = 0; i < element.getContainer().size(); i++)
      composeSpecimenSpecimenContainerComponent(t, "Specimen", "container", element.getContainer().get(i), i);
  }

  protected void composeSpecimenSpecimenCollectionComponent(Complex parent, String parentType, String name, Specimen.SpecimenCollectionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "collection", name, element, index);
    composeReference(t, "Specimen", "collector", element.getCollector(), -1);
    composeString(t, "Specimen", "comment", element.getCommentElement(), -1);
    composeType(t, "Specimen", "collected", element.getCollected(), -1);
    composeQuantity(t, "Specimen", "quantity", element.getQuantity(), -1);
    composeCodeableConcept(t, "Specimen", "method", element.getMethod(), -1);
    composeCodeableConcept(t, "Specimen", "bodySite", element.getBodySite(), -1);
  }

  protected void composeSpecimenSpecimenTreatmentComponent(Complex parent, String parentType, String name, Specimen.SpecimenTreatmentComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "treatment", name, element, index);
    composeString(t, "Specimen", "description", element.getDescriptionElement(), -1);
    composeCodeableConcept(t, "Specimen", "procedure", element.getProcedure(), -1);
    for (int i = 0; i < element.getAdditive().size(); i++)
      composeReference(t, "Specimen", "additive", element.getAdditive().get(i), i);
  }

  protected void composeSpecimenSpecimenContainerComponent(Complex parent, String parentType, String name, Specimen.SpecimenContainerComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "container", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Specimen", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "Specimen", "description", element.getDescriptionElement(), -1);
    composeCodeableConcept(t, "Specimen", "type", element.getType(), -1);
    composeQuantity(t, "Specimen", "capacity", element.getCapacity(), -1);
    composeQuantity(t, "Specimen", "specimenQuantity", element.getSpecimenQuantity(), -1);
    composeType(t, "Specimen", "additive", element.getAdditive(), -1);
  }

  protected void composeStructureDefinition(Complex parent, String parentType, String name, StructureDefinition element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "StructureDefinition", name, element, index);
    composeUri(t, "StructureDefinition", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "StructureDefinition", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "StructureDefinition", "version", element.getVersionElement(), -1);
    composeString(t, "StructureDefinition", "name", element.getNameElement(), -1);
    composeString(t, "StructureDefinition", "display", element.getDisplayElement(), -1);
    composeEnum(t, "StructureDefinition", "status", element.getStatusElement(), -1);
    composeBoolean(t, "StructureDefinition", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "StructureDefinition", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeStructureDefinitionStructureDefinitionContactComponent(t, "StructureDefinition", "contact", element.getContact().get(i), i);
    composeDateTime(t, "StructureDefinition", "date", element.getDateElement(), -1);
    composeString(t, "StructureDefinition", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "StructureDefinition", "useContext", element.getUseContext().get(i), i);
    composeString(t, "StructureDefinition", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "StructureDefinition", "copyright", element.getCopyrightElement(), -1);
    for (int i = 0; i < element.getCode().size(); i++)
      composeCoding(t, "StructureDefinition", "code", element.getCode().get(i), i);
    composeId(t, "StructureDefinition", "fhirVersion", element.getFhirVersionElement(), -1);
    for (int i = 0; i < element.getMapping().size(); i++)
      composeStructureDefinitionStructureDefinitionMappingComponent(t, "StructureDefinition", "mapping", element.getMapping().get(i), i);
    composeEnum(t, "StructureDefinition", "kind", element.getKindElement(), -1);
    composeCode(t, "StructureDefinition", "constrainedType", element.getConstrainedTypeElement(), -1);
    composeBoolean(t, "StructureDefinition", "abstract", element.getAbstractElement(), -1);
    composeEnum(t, "StructureDefinition", "contextType", element.getContextTypeElement(), -1);
    for (int i = 0; i < element.getContext().size(); i++)
      composeString(t, "StructureDefinition", "context", element.getContext().get(i), i);
    composeUri(t, "StructureDefinition", "base", element.getBaseElement(), -1);
    composeStructureDefinitionStructureDefinitionSnapshotComponent(t, "StructureDefinition", "snapshot", element.getSnapshot(), -1);
    composeStructureDefinitionStructureDefinitionDifferentialComponent(t, "StructureDefinition", "differential", element.getDifferential(), -1);
  }

  protected void composeStructureDefinitionStructureDefinitionContactComponent(Complex parent, String parentType, String name, StructureDefinition.StructureDefinitionContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "StructureDefinition", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "StructureDefinition", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeStructureDefinitionStructureDefinitionMappingComponent(Complex parent, String parentType, String name, StructureDefinition.StructureDefinitionMappingComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "mapping", name, element, index);
    composeId(t, "StructureDefinition", "identity", element.getIdentityElement(), -1);
    composeUri(t, "StructureDefinition", "uri", element.getUriElement(), -1);
    composeString(t, "StructureDefinition", "name", element.getNameElement(), -1);
    composeString(t, "StructureDefinition", "comments", element.getCommentsElement(), -1);
  }

  protected void composeStructureDefinitionStructureDefinitionSnapshotComponent(Complex parent, String parentType, String name, StructureDefinition.StructureDefinitionSnapshotComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "snapshot", name, element, index);
    for (int i = 0; i < element.getElement().size(); i++)
      composeElementDefinition(t, "StructureDefinition", "element", element.getElement().get(i), i);
  }

  protected void composeStructureDefinitionStructureDefinitionDifferentialComponent(Complex parent, String parentType, String name, StructureDefinition.StructureDefinitionDifferentialComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "differential", name, element, index);
    for (int i = 0; i < element.getElement().size(); i++)
      composeElementDefinition(t, "StructureDefinition", "element", element.getElement().get(i), i);
  }

  protected void composeStructureMap(Complex parent, String parentType, String name, StructureMap element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "StructureMap", name, element, index);
    composeUri(t, "StructureMap", "url", element.getUrlElement(), -1);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "StructureMap", "identifier", element.getIdentifier().get(i), i);
    composeString(t, "StructureMap", "version", element.getVersionElement(), -1);
    composeString(t, "StructureMap", "name", element.getNameElement(), -1);
    composeEnum(t, "StructureMap", "status", element.getStatusElement(), -1);
    composeBoolean(t, "StructureMap", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "StructureMap", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeStructureMapStructureMapContactComponent(t, "StructureMap", "contact", element.getContact().get(i), i);
    composeDateTime(t, "StructureMap", "date", element.getDateElement(), -1);
    composeString(t, "StructureMap", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "StructureMap", "useContext", element.getUseContext().get(i), i);
    composeString(t, "StructureMap", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "StructureMap", "copyright", element.getCopyrightElement(), -1);
    for (int i = 0; i < element.getStructure().size(); i++)
      composeStructureMapStructureMapStructureComponent(t, "StructureMap", "structure", element.getStructure().get(i), i);
    for (int i = 0; i < element.getImport().size(); i++)
      composeUri(t, "StructureMap", "import", element.getImport().get(i), i);
    for (int i = 0; i < element.getInput().size(); i++)
      composeStructureMapStructureMapInputComponent(t, "StructureMap", "input", element.getInput().get(i), i);
    for (int i = 0; i < element.getSection().size(); i++)
      composeStructureMapStructureMapSectionComponent(t, "StructureMap", "section", element.getSection().get(i), i);
  }

  protected void composeStructureMapStructureMapContactComponent(Complex parent, String parentType, String name, StructureMap.StructureMapContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "StructureMap", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "StructureMap", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeStructureMapStructureMapStructureComponent(Complex parent, String parentType, String name, StructureMap.StructureMapStructureComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "structure", name, element, index);
    composeUri(t, "StructureMap", "url", element.getUrlElement(), -1);
    composeEnum(t, "StructureMap", "mode", element.getModeElement(), -1);
    composeString(t, "StructureMap", "documentation", element.getDocumentationElement(), -1);
  }

  protected void composeStructureMapStructureMapInputComponent(Complex parent, String parentType, String name, StructureMap.StructureMapInputComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "input", name, element, index);
    composeId(t, "StructureMap", "name", element.getNameElement(), -1);
    composeString(t, "StructureMap", "type", element.getTypeElement(), -1);
    composeEnum(t, "StructureMap", "mode", element.getModeElement(), -1);
    composeString(t, "StructureMap", "documentation", element.getDocumentationElement(), -1);
  }

  protected void composeStructureMapStructureMapSectionComponent(Complex parent, String parentType, String name, StructureMap.StructureMapSectionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "section", name, element, index);
    composeString(t, "StructureMap", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getRule().size(); i++)
      composeStructureMapStructureMapSectionRuleComponent(t, "StructureMap", "rule", element.getRule().get(i), i);
  }

  protected void composeStructureMapStructureMapSectionRuleComponent(Complex parent, String parentType, String name, StructureMap.StructureMapSectionRuleComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "rule", name, element, index);
    composeId(t, "StructureMap", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getSource().size(); i++)
      composeStructureMapStructureMapSectionRuleSourceComponent(t, "StructureMap", "source", element.getSource().get(i), i);
    for (int i = 0; i < element.getTarget().size(); i++)
      composeStructureMapStructureMapSectionRuleTargetComponent(t, "StructureMap", "target", element.getTarget().get(i), i);
    composeStructureMapStructureMapSectionRuleInnerRulesComponent(t, "StructureMap", "innerRules", element.getInnerRules(), -1);
    composeString(t, "StructureMap", "documentation", element.getDocumentationElement(), -1);
  }

  protected void composeStructureMapStructureMapSectionRuleSourceComponent(Complex parent, String parentType, String name, StructureMap.StructureMapSectionRuleSourceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "source", name, element, index);
    composeBoolean(t, "StructureMap", "required", element.getRequiredElement(), -1);
    composeId(t, "StructureMap", "context", element.getContextElement(), -1);
    composeEnum(t, "StructureMap", "contextType", element.getContextTypeElement(), -1);
    composeString(t, "StructureMap", "field", element.getFieldElement(), -1);
    composeId(t, "StructureMap", "variable", element.getVariableElement(), -1);
    composeString(t, "StructureMap", "condition", element.getConditionElement(), -1);
    composeString(t, "StructureMap", "check", element.getCheckElement(), -1);
  }

  protected void composeStructureMapStructureMapSectionRuleTargetComponent(Complex parent, String parentType, String name, StructureMap.StructureMapSectionRuleTargetComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "target", name, element, index);
    composeId(t, "StructureMap", "context", element.getContextElement(), -1);
    composeEnum(t, "StructureMap", "contextType", element.getContextTypeElement(), -1);
    composeString(t, "StructureMap", "field", element.getFieldElement(), -1);
    composeId(t, "StructureMap", "variable", element.getVariableElement(), -1);
    for (int i = 0; i < element.getListMode().size(); i++)
      composeEnum(t, "StructureMap", "listMode", element.getListMode().get(i), i);
    composeId(t, "StructureMap", "listRuleId", element.getListRuleIdElement(), -1);
    composeEnum(t, "StructureMap", "transform", element.getTransformElement(), -1);
    for (int i = 0; i < element.getParameter().size(); i++)
      composeStructureMapStructureMapSectionRuleTargetParameterComponent(t, "StructureMap", "parameter", element.getParameter().get(i), i);
  }

  protected void composeStructureMapStructureMapSectionRuleTargetParameterComponent(Complex parent, String parentType, String name, StructureMap.StructureMapSectionRuleTargetParameterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "parameter", name, element, index);
    composeType(t, "StructureMap", "value", element.getValue(), -1);
  }

  protected void composeStructureMapStructureMapSectionRuleInnerRulesComponent(Complex parent, String parentType, String name, StructureMap.StructureMapSectionRuleInnerRulesComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "innerRules", name, element, index);
    composeBoolean(t, "StructureMap", "byVariables", element.getByVariablesElement(), -1);
    composeBoolean(t, "StructureMap", "byType", element.getByTypeElement(), -1);
    for (int i = 0; i < element.getName().size(); i++)
      composeId(t, "StructureMap", "name", element.getName().get(i), i);
  }

  protected void composeSubscription(Complex parent, String parentType, String name, Subscription element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Subscription", name, element, index);
    composeString(t, "Subscription", "criteria", element.getCriteriaElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeContactPoint(t, "Subscription", "contact", element.getContact().get(i), i);
    composeString(t, "Subscription", "reason", element.getReasonElement(), -1);
    composeEnum(t, "Subscription", "status", element.getStatusElement(), -1);
    composeString(t, "Subscription", "error", element.getErrorElement(), -1);
    composeSubscriptionSubscriptionChannelComponent(t, "Subscription", "channel", element.getChannel(), -1);
    composeInstant(t, "Subscription", "end", element.getEndElement(), -1);
    for (int i = 0; i < element.getTag().size(); i++)
      composeCoding(t, "Subscription", "tag", element.getTag().get(i), i);
  }

  protected void composeSubscriptionSubscriptionChannelComponent(Complex parent, String parentType, String name, Subscription.SubscriptionChannelComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "channel", name, element, index);
    composeEnum(t, "Subscription", "type", element.getTypeElement(), -1);
    composeUri(t, "Subscription", "endpoint", element.getEndpointElement(), -1);
    composeString(t, "Subscription", "payload", element.getPayloadElement(), -1);
    composeString(t, "Subscription", "header", element.getHeaderElement(), -1);
  }

  protected void composeSubstance(Complex parent, String parentType, String name, Substance element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "Substance", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "Substance", "identifier", element.getIdentifier().get(i), i);
    for (int i = 0; i < element.getCategory().size(); i++)
      composeCodeableConcept(t, "Substance", "category", element.getCategory().get(i), i);
    composeCodeableConcept(t, "Substance", "code", element.getCode(), -1);
    composeString(t, "Substance", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getInstance().size(); i++)
      composeSubstanceSubstanceInstanceComponent(t, "Substance", "instance", element.getInstance().get(i), i);
    for (int i = 0; i < element.getIngredient().size(); i++)
      composeSubstanceSubstanceIngredientComponent(t, "Substance", "ingredient", element.getIngredient().get(i), i);
  }

  protected void composeSubstanceSubstanceInstanceComponent(Complex parent, String parentType, String name, Substance.SubstanceInstanceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "instance", name, element, index);
    composeIdentifier(t, "Substance", "identifier", element.getIdentifier(), -1);
    composeDateTime(t, "Substance", "expiry", element.getExpiryElement(), -1);
    composeQuantity(t, "Substance", "quantity", element.getQuantity(), -1);
  }

  protected void composeSubstanceSubstanceIngredientComponent(Complex parent, String parentType, String name, Substance.SubstanceIngredientComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "ingredient", name, element, index);
    composeRatio(t, "Substance", "quantity", element.getQuantity(), -1);
    composeReference(t, "Substance", "substance", element.getSubstance(), -1);
  }

  protected void composeSupplyDelivery(Complex parent, String parentType, String name, SupplyDelivery element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "SupplyDelivery", name, element, index);
    composeIdentifier(t, "SupplyDelivery", "identifier", element.getIdentifier(), -1);
    composeEnum(t, "SupplyDelivery", "status", element.getStatusElement(), -1);
    composeReference(t, "SupplyDelivery", "patient", element.getPatient(), -1);
    composeCodeableConcept(t, "SupplyDelivery", "type", element.getType(), -1);
    composeQuantity(t, "SupplyDelivery", "quantity", element.getQuantity(), -1);
    composeReference(t, "SupplyDelivery", "suppliedItem", element.getSuppliedItem(), -1);
    composeReference(t, "SupplyDelivery", "supplier", element.getSupplier(), -1);
    composePeriod(t, "SupplyDelivery", "whenPrepared", element.getWhenPrepared(), -1);
    composeDateTime(t, "SupplyDelivery", "time", element.getTimeElement(), -1);
    composeReference(t, "SupplyDelivery", "destination", element.getDestination(), -1);
    for (int i = 0; i < element.getReceiver().size(); i++)
      composeReference(t, "SupplyDelivery", "receiver", element.getReceiver().get(i), i);
  }

  protected void composeSupplyRequest(Complex parent, String parentType, String name, SupplyRequest element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "SupplyRequest", name, element, index);
    composeReference(t, "SupplyRequest", "patient", element.getPatient(), -1);
    composeReference(t, "SupplyRequest", "source", element.getSource(), -1);
    composeDateTime(t, "SupplyRequest", "date", element.getDateElement(), -1);
    composeIdentifier(t, "SupplyRequest", "identifier", element.getIdentifier(), -1);
    composeEnum(t, "SupplyRequest", "status", element.getStatusElement(), -1);
    composeCodeableConcept(t, "SupplyRequest", "kind", element.getKind(), -1);
    composeReference(t, "SupplyRequest", "orderedItem", element.getOrderedItem(), -1);
    for (int i = 0; i < element.getSupplier().size(); i++)
      composeReference(t, "SupplyRequest", "supplier", element.getSupplier().get(i), i);
    composeType(t, "SupplyRequest", "reason", element.getReason(), -1);
    composeSupplyRequestSupplyRequestWhenComponent(t, "SupplyRequest", "when", element.getWhen(), -1);
  }

  protected void composeSupplyRequestSupplyRequestWhenComponent(Complex parent, String parentType, String name, SupplyRequest.SupplyRequestWhenComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "when", name, element, index);
    composeCodeableConcept(t, "SupplyRequest", "code", element.getCode(), -1);
    composeTiming(t, "SupplyRequest", "schedule", element.getSchedule(), -1);
  }

  protected void composeTestScript(Complex parent, String parentType, String name, TestScript element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "TestScript", name, element, index);
    composeUri(t, "TestScript", "url", element.getUrlElement(), -1);
    composeString(t, "TestScript", "version", element.getVersionElement(), -1);
    composeString(t, "TestScript", "name", element.getNameElement(), -1);
    composeEnum(t, "TestScript", "status", element.getStatusElement(), -1);
    composeIdentifier(t, "TestScript", "identifier", element.getIdentifier(), -1);
    composeBoolean(t, "TestScript", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "TestScript", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeTestScriptTestScriptContactComponent(t, "TestScript", "contact", element.getContact().get(i), i);
    composeDateTime(t, "TestScript", "date", element.getDateElement(), -1);
    composeString(t, "TestScript", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "TestScript", "useContext", element.getUseContext().get(i), i);
    composeString(t, "TestScript", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "TestScript", "copyright", element.getCopyrightElement(), -1);
    composeTestScriptTestScriptMetadataComponent(t, "TestScript", "metadata", element.getMetadata(), -1);
    for (int i = 0; i < element.getOrigin().size(); i++)
      composeTestScriptTestScriptOriginComponent(t, "TestScript", "origin", element.getOrigin().get(i), i);
    for (int i = 0; i < element.getDestination().size(); i++)
      composeTestScriptTestScriptDestinationComponent(t, "TestScript", "destination", element.getDestination().get(i), i);
    composeBoolean(t, "TestScript", "multiserver", element.getMultiserverElement(), -1);
    for (int i = 0; i < element.getFixture().size(); i++)
      composeTestScriptTestScriptFixtureComponent(t, "TestScript", "fixture", element.getFixture().get(i), i);
    for (int i = 0; i < element.getProfile().size(); i++)
      composeReference(t, "TestScript", "profile", element.getProfile().get(i), i);
    for (int i = 0; i < element.getVariable().size(); i++)
      composeTestScriptTestScriptVariableComponent(t, "TestScript", "variable", element.getVariable().get(i), i);
    composeTestScriptTestScriptSetupComponent(t, "TestScript", "setup", element.getSetup(), -1);
    for (int i = 0; i < element.getTest().size(); i++)
      composeTestScriptTestScriptTestComponent(t, "TestScript", "test", element.getTest().get(i), i);
    composeTestScriptTestScriptTeardownComponent(t, "TestScript", "teardown", element.getTeardown(), -1);
  }

  protected void composeTestScriptTestScriptContactComponent(Complex parent, String parentType, String name, TestScript.TestScriptContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "TestScript", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "TestScript", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeTestScriptTestScriptMetadataComponent(Complex parent, String parentType, String name, TestScript.TestScriptMetadataComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "metadata", name, element, index);
    for (int i = 0; i < element.getLink().size(); i++)
      composeTestScriptTestScriptMetadataLinkComponent(t, "TestScript", "link", element.getLink().get(i), i);
    for (int i = 0; i < element.getCapability().size(); i++)
      composeTestScriptTestScriptMetadataCapabilityComponent(t, "TestScript", "capability", element.getCapability().get(i), i);
  }

  protected void composeTestScriptTestScriptMetadataLinkComponent(Complex parent, String parentType, String name, TestScript.TestScriptMetadataLinkComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "link", name, element, index);
    composeUri(t, "TestScript", "url", element.getUrlElement(), -1);
    composeString(t, "TestScript", "description", element.getDescriptionElement(), -1);
  }

  protected void composeTestScriptTestScriptMetadataCapabilityComponent(Complex parent, String parentType, String name, TestScript.TestScriptMetadataCapabilityComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "capability", name, element, index);
    composeBoolean(t, "TestScript", "required", element.getRequiredElement(), -1);
    composeBoolean(t, "TestScript", "validated", element.getValidatedElement(), -1);
    composeString(t, "TestScript", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getOrigin().size(); i++)
      composeInteger(t, "TestScript", "origin", element.getOrigin().get(i), i);
    composeInteger(t, "TestScript", "destination", element.getDestinationElement(), -1);
    for (int i = 0; i < element.getLink().size(); i++)
      composeUri(t, "TestScript", "link", element.getLink().get(i), i);
    composeReference(t, "TestScript", "conformance", element.getConformance(), -1);
  }

  protected void composeTestScriptTestScriptOriginComponent(Complex parent, String parentType, String name, TestScript.TestScriptOriginComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "origin", name, element, index);
    composeInteger(t, "TestScript", "index", element.getIndexElement(), -1);
    composeEnum(t, "TestScript", "profile", element.getProfileElement(), -1);
  }

  protected void composeTestScriptTestScriptDestinationComponent(Complex parent, String parentType, String name, TestScript.TestScriptDestinationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "destination", name, element, index);
    composeInteger(t, "TestScript", "index", element.getIndexElement(), -1);
    composeEnum(t, "TestScript", "profile", element.getProfileElement(), -1);
  }

  protected void composeTestScriptTestScriptFixtureComponent(Complex parent, String parentType, String name, TestScript.TestScriptFixtureComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "fixture", name, element, index);
    composeBoolean(t, "TestScript", "autocreate", element.getAutocreateElement(), -1);
    composeBoolean(t, "TestScript", "autodelete", element.getAutodeleteElement(), -1);
    composeReference(t, "TestScript", "resource", element.getResource(), -1);
  }

  protected void composeTestScriptTestScriptVariableComponent(Complex parent, String parentType, String name, TestScript.TestScriptVariableComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "variable", name, element, index);
    composeString(t, "TestScript", "name", element.getNameElement(), -1);
    composeString(t, "TestScript", "defaultValue", element.getDefaultValueElement(), -1);
    composeString(t, "TestScript", "headerField", element.getHeaderFieldElement(), -1);
    composeString(t, "TestScript", "path", element.getPathElement(), -1);
    composeId(t, "TestScript", "sourceId", element.getSourceIdElement(), -1);
  }

  protected void composeTestScriptTestScriptSetupComponent(Complex parent, String parentType, String name, TestScript.TestScriptSetupComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "setup", name, element, index);
    composeTestScriptTestScriptMetadataComponent(t, "TestScript", "metadata", element.getMetadata(), -1);
    for (int i = 0; i < element.getAction().size(); i++)
      composeTestScriptSetupActionComponent(t, "TestScript", "action", element.getAction().get(i), i);
  }

  protected void composeTestScriptSetupActionComponent(Complex parent, String parentType, String name, TestScript.SetupActionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "action", name, element, index);
    composeTestScriptSetupActionOperationComponent(t, "TestScript", "operation", element.getOperation(), -1);
    composeTestScriptSetupActionAssertComponent(t, "TestScript", "assert", element.getAssert(), -1);
  }

  protected void composeTestScriptSetupActionOperationComponent(Complex parent, String parentType, String name, TestScript.SetupActionOperationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "operation", name, element, index);
    composeCoding(t, "TestScript", "type", element.getType(), -1);
    composeCode(t, "TestScript", "resource", element.getResourceElement(), -1);
    composeString(t, "TestScript", "label", element.getLabelElement(), -1);
    composeString(t, "TestScript", "description", element.getDescriptionElement(), -1);
    composeEnum(t, "TestScript", "accept", element.getAcceptElement(), -1);
    composeEnum(t, "TestScript", "contentType", element.getContentTypeElement(), -1);
    composeInteger(t, "TestScript", "destination", element.getDestinationElement(), -1);
    composeBoolean(t, "TestScript", "encodeRequestUrl", element.getEncodeRequestUrlElement(), -1);
    composeInteger(t, "TestScript", "origin", element.getOriginElement(), -1);
    composeString(t, "TestScript", "params", element.getParamsElement(), -1);
    for (int i = 0; i < element.getRequestHeader().size(); i++)
      composeTestScriptSetupActionOperationRequestHeaderComponent(t, "TestScript", "requestHeader", element.getRequestHeader().get(i), i);
    composeId(t, "TestScript", "responseId", element.getResponseIdElement(), -1);
    composeId(t, "TestScript", "sourceId", element.getSourceIdElement(), -1);
    composeId(t, "TestScript", "targetId", element.getTargetIdElement(), -1);
    composeString(t, "TestScript", "url", element.getUrlElement(), -1);
  }

  protected void composeTestScriptSetupActionOperationRequestHeaderComponent(Complex parent, String parentType, String name, TestScript.SetupActionOperationRequestHeaderComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "requestHeader", name, element, index);
    composeString(t, "TestScript", "field", element.getFieldElement(), -1);
    composeString(t, "TestScript", "value", element.getValueElement(), -1);
  }

  protected void composeTestScriptSetupActionAssertComponent(Complex parent, String parentType, String name, TestScript.SetupActionAssertComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "assert", name, element, index);
    composeString(t, "TestScript", "label", element.getLabelElement(), -1);
    composeString(t, "TestScript", "description", element.getDescriptionElement(), -1);
    composeEnum(t, "TestScript", "direction", element.getDirectionElement(), -1);
    composeString(t, "TestScript", "compareToSourceId", element.getCompareToSourceIdElement(), -1);
    composeString(t, "TestScript", "compareToSourcePath", element.getCompareToSourcePathElement(), -1);
    composeEnum(t, "TestScript", "contentType", element.getContentTypeElement(), -1);
    composeString(t, "TestScript", "headerField", element.getHeaderFieldElement(), -1);
    composeString(t, "TestScript", "minimumId", element.getMinimumIdElement(), -1);
    composeBoolean(t, "TestScript", "navigationLinks", element.getNavigationLinksElement(), -1);
    composeEnum(t, "TestScript", "operator", element.getOperatorElement(), -1);
    composeString(t, "TestScript", "path", element.getPathElement(), -1);
    composeCode(t, "TestScript", "resource", element.getResourceElement(), -1);
    composeEnum(t, "TestScript", "response", element.getResponseElement(), -1);
    composeString(t, "TestScript", "responseCode", element.getResponseCodeElement(), -1);
    composeId(t, "TestScript", "sourceId", element.getSourceIdElement(), -1);
    composeId(t, "TestScript", "validateProfileId", element.getValidateProfileIdElement(), -1);
    composeString(t, "TestScript", "value", element.getValueElement(), -1);
    composeBoolean(t, "TestScript", "warningOnly", element.getWarningOnlyElement(), -1);
  }

  protected void composeTestScriptTestScriptTestComponent(Complex parent, String parentType, String name, TestScript.TestScriptTestComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "test", name, element, index);
    composeString(t, "TestScript", "name", element.getNameElement(), -1);
    composeString(t, "TestScript", "description", element.getDescriptionElement(), -1);
    composeTestScriptTestScriptMetadataComponent(t, "TestScript", "metadata", element.getMetadata(), -1);
    for (int i = 0; i < element.getAction().size(); i++)
      composeTestScriptTestActionComponent(t, "TestScript", "action", element.getAction().get(i), i);
  }

  protected void composeTestScriptTestActionComponent(Complex parent, String parentType, String name, TestScript.TestActionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "action", name, element, index);
    composeTestScriptSetupActionOperationComponent(t, "TestScript", "operation", element.getOperation(), -1);
    composeTestScriptSetupActionAssertComponent(t, "TestScript", "assert", element.getAssert(), -1);
  }

  protected void composeTestScriptTestScriptTeardownComponent(Complex parent, String parentType, String name, TestScript.TestScriptTeardownComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "teardown", name, element, index);
    for (int i = 0; i < element.getAction().size(); i++)
      composeTestScriptTeardownActionComponent(t, "TestScript", "action", element.getAction().get(i), i);
  }

  protected void composeTestScriptTeardownActionComponent(Complex parent, String parentType, String name, TestScript.TeardownActionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "action", name, element, index);
    composeTestScriptSetupActionOperationComponent(t, "TestScript", "operation", element.getOperation(), -1);
  }

  protected void composeValueSet(Complex parent, String parentType, String name, ValueSet element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "ValueSet", name, element, index);
    composeUri(t, "ValueSet", "url", element.getUrlElement(), -1);
    composeIdentifier(t, "ValueSet", "identifier", element.getIdentifier(), -1);
    composeString(t, "ValueSet", "version", element.getVersionElement(), -1);
    composeString(t, "ValueSet", "name", element.getNameElement(), -1);
    composeEnum(t, "ValueSet", "status", element.getStatusElement(), -1);
    composeBoolean(t, "ValueSet", "experimental", element.getExperimentalElement(), -1);
    composeString(t, "ValueSet", "publisher", element.getPublisherElement(), -1);
    for (int i = 0; i < element.getContact().size(); i++)
      composeValueSetValueSetContactComponent(t, "ValueSet", "contact", element.getContact().get(i), i);
    composeDateTime(t, "ValueSet", "date", element.getDateElement(), -1);
    composeDate(t, "ValueSet", "lockedDate", element.getLockedDateElement(), -1);
    composeString(t, "ValueSet", "description", element.getDescriptionElement(), -1);
    for (int i = 0; i < element.getUseContext().size(); i++)
      composeCodeableConcept(t, "ValueSet", "useContext", element.getUseContext().get(i), i);
    composeBoolean(t, "ValueSet", "immutable", element.getImmutableElement(), -1);
    composeString(t, "ValueSet", "requirements", element.getRequirementsElement(), -1);
    composeString(t, "ValueSet", "copyright", element.getCopyrightElement(), -1);
    composeBoolean(t, "ValueSet", "extensible", element.getExtensibleElement(), -1);
    composeValueSetValueSetCodeSystemComponent(t, "ValueSet", "codeSystem", element.getCodeSystem(), -1);
    composeValueSetValueSetComposeComponent(t, "ValueSet", "compose", element.getCompose(), -1);
    composeValueSetValueSetExpansionComponent(t, "ValueSet", "expansion", element.getExpansion(), -1);
  }

  protected void composeValueSetValueSetContactComponent(Complex parent, String parentType, String name, ValueSet.ValueSetContactComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contact", name, element, index);
    composeString(t, "ValueSet", "name", element.getNameElement(), -1);
    for (int i = 0; i < element.getTelecom().size(); i++)
      composeContactPoint(t, "ValueSet", "telecom", element.getTelecom().get(i), i);
  }

  protected void composeValueSetValueSetCodeSystemComponent(Complex parent, String parentType, String name, ValueSet.ValueSetCodeSystemComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "codeSystem", name, element, index);
    composeUri(t, "ValueSet", "system", element.getSystemElement(), -1);
    composeString(t, "ValueSet", "version", element.getVersionElement(), -1);
    composeBoolean(t, "ValueSet", "caseSensitive", element.getCaseSensitiveElement(), -1);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeValueSetConceptDefinitionComponent(t, "ValueSet", "concept", element.getConcept().get(i), i);
  }

  protected void composeValueSetConceptDefinitionComponent(Complex parent, String parentType, String name, ValueSet.ConceptDefinitionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "concept", name, element, index);
    composeCode(t, "ValueSet", "code", element.getCodeElement(), -1);
    composeBoolean(t, "ValueSet", "abstract", element.getAbstractElement(), -1);
    composeString(t, "ValueSet", "display", element.getDisplayElement(), -1);
    composeString(t, "ValueSet", "definition", element.getDefinitionElement(), -1);
    for (int i = 0; i < element.getDesignation().size(); i++)
      composeValueSetConceptDefinitionDesignationComponent(t, "ValueSet", "designation", element.getDesignation().get(i), i);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeValueSetConceptDefinitionComponent(t, "ValueSet", "concept", element.getConcept().get(i), i);
  }

  protected void composeValueSetConceptDefinitionDesignationComponent(Complex parent, String parentType, String name, ValueSet.ConceptDefinitionDesignationComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "designation", name, element, index);
    composeCode(t, "ValueSet", "language", element.getLanguageElement(), -1);
    composeCoding(t, "ValueSet", "use", element.getUse(), -1);
    composeString(t, "ValueSet", "value", element.getValueElement(), -1);
  }

  protected void composeValueSetValueSetComposeComponent(Complex parent, String parentType, String name, ValueSet.ValueSetComposeComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "compose", name, element, index);
    for (int i = 0; i < element.getImport().size(); i++)
      composeUri(t, "ValueSet", "import", element.getImport().get(i), i);
    for (int i = 0; i < element.getInclude().size(); i++)
      composeValueSetConceptSetComponent(t, "ValueSet", "include", element.getInclude().get(i), i);
    for (int i = 0; i < element.getExclude().size(); i++)
      composeValueSetConceptSetComponent(t, "ValueSet", "exclude", element.getExclude().get(i), i);
  }

  protected void composeValueSetConceptSetComponent(Complex parent, String parentType, String name, ValueSet.ConceptSetComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "include", name, element, index);
    composeUri(t, "ValueSet", "system", element.getSystemElement(), -1);
    composeString(t, "ValueSet", "version", element.getVersionElement(), -1);
    for (int i = 0; i < element.getConcept().size(); i++)
      composeValueSetConceptReferenceComponent(t, "ValueSet", "concept", element.getConcept().get(i), i);
    for (int i = 0; i < element.getFilter().size(); i++)
      composeValueSetConceptSetFilterComponent(t, "ValueSet", "filter", element.getFilter().get(i), i);
  }

  protected void composeValueSetConceptReferenceComponent(Complex parent, String parentType, String name, ValueSet.ConceptReferenceComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "concept", name, element, index);
    composeCode(t, "ValueSet", "code", element.getCodeElement(), -1);
    composeString(t, "ValueSet", "display", element.getDisplayElement(), -1);
    for (int i = 0; i < element.getDesignation().size(); i++)
      composeValueSetConceptDefinitionDesignationComponent(t, "ValueSet", "designation", element.getDesignation().get(i), i);
  }

  protected void composeValueSetConceptSetFilterComponent(Complex parent, String parentType, String name, ValueSet.ConceptSetFilterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "filter", name, element, index);
    composeCode(t, "ValueSet", "property", element.getPropertyElement(), -1);
    composeEnum(t, "ValueSet", "op", element.getOpElement(), -1);
    composeCode(t, "ValueSet", "value", element.getValueElement(), -1);
  }

  protected void composeValueSetValueSetExpansionComponent(Complex parent, String parentType, String name, ValueSet.ValueSetExpansionComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "expansion", name, element, index);
    composeUri(t, "ValueSet", "identifier", element.getIdentifierElement(), -1);
    composeDateTime(t, "ValueSet", "timestamp", element.getTimestampElement(), -1);
    composeInteger(t, "ValueSet", "total", element.getTotalElement(), -1);
    composeInteger(t, "ValueSet", "offset", element.getOffsetElement(), -1);
    for (int i = 0; i < element.getParameter().size(); i++)
      composeValueSetValueSetExpansionParameterComponent(t, "ValueSet", "parameter", element.getParameter().get(i), i);
    for (int i = 0; i < element.getContains().size(); i++)
      composeValueSetValueSetExpansionContainsComponent(t, "ValueSet", "contains", element.getContains().get(i), i);
  }

  protected void composeValueSetValueSetExpansionParameterComponent(Complex parent, String parentType, String name, ValueSet.ValueSetExpansionParameterComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "parameter", name, element, index);
    composeString(t, "ValueSet", "name", element.getNameElement(), -1);
    composeType(t, "ValueSet", "value", element.getValue(), -1);
  }

  protected void composeValueSetValueSetExpansionContainsComponent(Complex parent, String parentType, String name, ValueSet.ValueSetExpansionContainsComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "contains", name, element, index);
    composeUri(t, "ValueSet", "system", element.getSystemElement(), -1);
    composeBoolean(t, "ValueSet", "abstract", element.getAbstractElement(), -1);
    composeString(t, "ValueSet", "version", element.getVersionElement(), -1);
    composeCode(t, "ValueSet", "code", element.getCodeElement(), -1);
    composeString(t, "ValueSet", "display", element.getDisplayElement(), -1);
    for (int i = 0; i < element.getContains().size(); i++)
      composeValueSetValueSetExpansionContainsComponent(t, "ValueSet", "contains", element.getContains().get(i), i);
  }

  protected void composeVisionPrescription(Complex parent, String parentType, String name, VisionPrescription element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeDomainResource(t, "VisionPrescription", name, element, index);
    for (int i = 0; i < element.getIdentifier().size(); i++)
      composeIdentifier(t, "VisionPrescription", "identifier", element.getIdentifier().get(i), i);
    composeDateTime(t, "VisionPrescription", "dateWritten", element.getDateWrittenElement(), -1);
    composeReference(t, "VisionPrescription", "patient", element.getPatient(), -1);
    composeReference(t, "VisionPrescription", "prescriber", element.getPrescriber(), -1);
    composeReference(t, "VisionPrescription", "encounter", element.getEncounter(), -1);
    composeType(t, "VisionPrescription", "reason", element.getReason(), -1);
    for (int i = 0; i < element.getDispense().size(); i++)
      composeVisionPrescriptionVisionPrescriptionDispenseComponent(t, "VisionPrescription", "dispense", element.getDispense().get(i), i);
  }

  protected void composeVisionPrescriptionVisionPrescriptionDispenseComponent(Complex parent, String parentType, String name, VisionPrescription.VisionPrescriptionDispenseComponent element, int index) {
    if (element == null) 
      return;
    Complex t;
    if (Utilities.noString(parentType))
      t = parent;
    else {
      t = parent.predicate("fhir:"+parentType+'.'+name);
    }
    composeBackboneElement(t, "dispense", name, element, index);
    composeCoding(t, "VisionPrescription", "product", element.getProduct(), -1);
    composeEnum(t, "VisionPrescription", "eye", element.getEyeElement(), -1);
    composeDecimal(t, "VisionPrescription", "sphere", element.getSphereElement(), -1);
    composeDecimal(t, "VisionPrescription", "cylinder", element.getCylinderElement(), -1);
    composeInteger(t, "VisionPrescription", "axis", element.getAxisElement(), -1);
    composeDecimal(t, "VisionPrescription", "prism", element.getPrismElement(), -1);
    composeEnum(t, "VisionPrescription", "base", element.getBaseElement(), -1);
    composeDecimal(t, "VisionPrescription", "add", element.getAddElement(), -1);
    composeDecimal(t, "VisionPrescription", "power", element.getPowerElement(), -1);
    composeDecimal(t, "VisionPrescription", "backCurve", element.getBackCurveElement(), -1);
    composeDecimal(t, "VisionPrescription", "diameter", element.getDiameterElement(), -1);
    composeQuantity(t, "VisionPrescription", "duration", element.getDuration(), -1);
    composeString(t, "VisionPrescription", "color", element.getColorElement(), -1);
    composeString(t, "VisionPrescription", "brand", element.getBrandElement(), -1);
    composeString(t, "VisionPrescription", "notes", element.getNotesElement(), -1);
  }

  @Override
  protected void composeResource(Complex parent, Resource resource) {
    if (resource instanceof Parameters)
      composeParameters(parent, "Parameters", "Parameters", (Parameters)resource, -1);
    else if (resource instanceof Account)
      composeAccount(parent, "Account", "Account", (Account)resource, -1);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance(parent, "AllergyIntolerance", "AllergyIntolerance", (AllergyIntolerance)resource, -1);
    else if (resource instanceof Appointment)
      composeAppointment(parent, "Appointment", "Appointment", (Appointment)resource, -1);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse(parent, "AppointmentResponse", "AppointmentResponse", (AppointmentResponse)resource, -1);
    else if (resource instanceof AuditEvent)
      composeAuditEvent(parent, "AuditEvent", "AuditEvent", (AuditEvent)resource, -1);
    else if (resource instanceof Basic)
      composeBasic(parent, "Basic", "Basic", (Basic)resource, -1);
    else if (resource instanceof Binary)
      composeBinary(parent, "Binary", "Binary", (Binary)resource, -1);
    else if (resource instanceof BodySite)
      composeBodySite(parent, "BodySite", "BodySite", (BodySite)resource, -1);
    else if (resource instanceof Bundle)
      composeBundle(parent, "Bundle", "Bundle", (Bundle)resource, -1);
    else if (resource instanceof CarePlan)
      composeCarePlan(parent, "CarePlan", "CarePlan", (CarePlan)resource, -1);
    else if (resource instanceof Claim)
      composeClaim(parent, "Claim", "Claim", (Claim)resource, -1);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse(parent, "ClaimResponse", "ClaimResponse", (ClaimResponse)resource, -1);
    else if (resource instanceof ClinicalImpression)
      composeClinicalImpression(parent, "ClinicalImpression", "ClinicalImpression", (ClinicalImpression)resource, -1);
    else if (resource instanceof CodeSystem)
      composeCodeSystem(parent, "CodeSystem", "CodeSystem", (CodeSystem)resource, -1);
    else if (resource instanceof Communication)
      composeCommunication(parent, "Communication", "Communication", (Communication)resource, -1);
    else if (resource instanceof CommunicationRequest)
      composeCommunicationRequest(parent, "CommunicationRequest", "CommunicationRequest", (CommunicationRequest)resource, -1);
    else if (resource instanceof Composition)
      composeComposition(parent, "Composition", "Composition", (Composition)resource, -1);
    else if (resource instanceof ConceptMap)
      composeConceptMap(parent, "ConceptMap", "ConceptMap", (ConceptMap)resource, -1);
    else if (resource instanceof Condition)
      composeCondition(parent, "Condition", "Condition", (Condition)resource, -1);
    else if (resource instanceof Conformance)
      composeConformance(parent, "Conformance", "Conformance", (Conformance)resource, -1);
    else if (resource instanceof Contract)
      composeContract(parent, "Contract", "Contract", (Contract)resource, -1);
    else if (resource instanceof Coverage)
      composeCoverage(parent, "Coverage", "Coverage", (Coverage)resource, -1);
    else if (resource instanceof DataElement)
      composeDataElement(parent, "DataElement", "DataElement", (DataElement)resource, -1);
    else if (resource instanceof DecisionSupportRule)
      composeDecisionSupportRule(parent, "DecisionSupportRule", "DecisionSupportRule", (DecisionSupportRule)resource, -1);
    else if (resource instanceof DecisionSupportServiceModule)
      composeDecisionSupportServiceModule(parent, "DecisionSupportServiceModule", "DecisionSupportServiceModule", (DecisionSupportServiceModule)resource, -1);
    else if (resource instanceof DetectedIssue)
      composeDetectedIssue(parent, "DetectedIssue", "DetectedIssue", (DetectedIssue)resource, -1);
    else if (resource instanceof Device)
      composeDevice(parent, "Device", "Device", (Device)resource, -1);
    else if (resource instanceof DeviceComponent)
      composeDeviceComponent(parent, "DeviceComponent", "DeviceComponent", (DeviceComponent)resource, -1);
    else if (resource instanceof DeviceMetric)
      composeDeviceMetric(parent, "DeviceMetric", "DeviceMetric", (DeviceMetric)resource, -1);
    else if (resource instanceof DeviceUseRequest)
      composeDeviceUseRequest(parent, "DeviceUseRequest", "DeviceUseRequest", (DeviceUseRequest)resource, -1);
    else if (resource instanceof DeviceUseStatement)
      composeDeviceUseStatement(parent, "DeviceUseStatement", "DeviceUseStatement", (DeviceUseStatement)resource, -1);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder(parent, "DiagnosticOrder", "DiagnosticOrder", (DiagnosticOrder)resource, -1);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport(parent, "DiagnosticReport", "DiagnosticReport", (DiagnosticReport)resource, -1);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest(parent, "DocumentManifest", "DocumentManifest", (DocumentManifest)resource, -1);
    else if (resource instanceof DocumentReference)
      composeDocumentReference(parent, "DocumentReference", "DocumentReference", (DocumentReference)resource, -1);
    else if (resource instanceof EligibilityRequest)
      composeEligibilityRequest(parent, "EligibilityRequest", "EligibilityRequest", (EligibilityRequest)resource, -1);
    else if (resource instanceof EligibilityResponse)
      composeEligibilityResponse(parent, "EligibilityResponse", "EligibilityResponse", (EligibilityResponse)resource, -1);
    else if (resource instanceof Encounter)
      composeEncounter(parent, "Encounter", "Encounter", (Encounter)resource, -1);
    else if (resource instanceof EnrollmentRequest)
      composeEnrollmentRequest(parent, "EnrollmentRequest", "EnrollmentRequest", (EnrollmentRequest)resource, -1);
    else if (resource instanceof EnrollmentResponse)
      composeEnrollmentResponse(parent, "EnrollmentResponse", "EnrollmentResponse", (EnrollmentResponse)resource, -1);
    else if (resource instanceof EpisodeOfCare)
      composeEpisodeOfCare(parent, "EpisodeOfCare", "EpisodeOfCare", (EpisodeOfCare)resource, -1);
    else if (resource instanceof ExpansionProfile)
      composeExpansionProfile(parent, "ExpansionProfile", "ExpansionProfile", (ExpansionProfile)resource, -1);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit(parent, "ExplanationOfBenefit", "ExplanationOfBenefit", (ExplanationOfBenefit)resource, -1);
    else if (resource instanceof FamilyMemberHistory)
      composeFamilyMemberHistory(parent, "FamilyMemberHistory", "FamilyMemberHistory", (FamilyMemberHistory)resource, -1);
    else if (resource instanceof Flag)
      composeFlag(parent, "Flag", "Flag", (Flag)resource, -1);
    else if (resource instanceof Goal)
      composeGoal(parent, "Goal", "Goal", (Goal)resource, -1);
    else if (resource instanceof Group)
      composeGroup(parent, "Group", "Group", (Group)resource, -1);
    else if (resource instanceof GuidanceResponse)
      composeGuidanceResponse(parent, "GuidanceResponse", "GuidanceResponse", (GuidanceResponse)resource, -1);
    else if (resource instanceof HealthcareService)
      composeHealthcareService(parent, "HealthcareService", "HealthcareService", (HealthcareService)resource, -1);
    else if (resource instanceof ImagingObjectSelection)
      composeImagingObjectSelection(parent, "ImagingObjectSelection", "ImagingObjectSelection", (ImagingObjectSelection)resource, -1);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy(parent, "ImagingStudy", "ImagingStudy", (ImagingStudy)resource, -1);
    else if (resource instanceof Immunization)
      composeImmunization(parent, "Immunization", "Immunization", (Immunization)resource, -1);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation(parent, "ImmunizationRecommendation", "ImmunizationRecommendation", (ImmunizationRecommendation)resource, -1);
    else if (resource instanceof ImplementationGuide)
      composeImplementationGuide(parent, "ImplementationGuide", "ImplementationGuide", (ImplementationGuide)resource, -1);
    else if (resource instanceof Library)
      composeLibrary(parent, "Library", "Library", (Library)resource, -1);
    else if (resource instanceof Linkage)
      composeLinkage(parent, "Linkage", "Linkage", (Linkage)resource, -1);
    else if (resource instanceof ListResource)
      composeListResource(parent, "List", "ListResource", (ListResource)resource, -1);
    else if (resource instanceof Location)
      composeLocation(parent, "Location", "Location", (Location)resource, -1);
    else if (resource instanceof Measure)
      composeMeasure(parent, "Measure", "Measure", (Measure)resource, -1);
    else if (resource instanceof Media)
      composeMedia(parent, "Media", "Media", (Media)resource, -1);
    else if (resource instanceof Medication)
      composeMedication(parent, "Medication", "Medication", (Medication)resource, -1);
    else if (resource instanceof MedicationAdministration)
      composeMedicationAdministration(parent, "MedicationAdministration", "MedicationAdministration", (MedicationAdministration)resource, -1);
    else if (resource instanceof MedicationDispense)
      composeMedicationDispense(parent, "MedicationDispense", "MedicationDispense", (MedicationDispense)resource, -1);
    else if (resource instanceof MedicationOrder)
      composeMedicationOrder(parent, "MedicationOrder", "MedicationOrder", (MedicationOrder)resource, -1);
    else if (resource instanceof MedicationStatement)
      composeMedicationStatement(parent, "MedicationStatement", "MedicationStatement", (MedicationStatement)resource, -1);
    else if (resource instanceof MessageHeader)
      composeMessageHeader(parent, "MessageHeader", "MessageHeader", (MessageHeader)resource, -1);
    else if (resource instanceof ModuleDefinition)
      composeModuleDefinition(parent, "ModuleDefinition", "ModuleDefinition", (ModuleDefinition)resource, -1);
    else if (resource instanceof NamingSystem)
      composeNamingSystem(parent, "NamingSystem", "NamingSystem", (NamingSystem)resource, -1);
    else if (resource instanceof NutritionOrder)
      composeNutritionOrder(parent, "NutritionOrder", "NutritionOrder", (NutritionOrder)resource, -1);
    else if (resource instanceof Observation)
      composeObservation(parent, "Observation", "Observation", (Observation)resource, -1);
    else if (resource instanceof OperationDefinition)
      composeOperationDefinition(parent, "OperationDefinition", "OperationDefinition", (OperationDefinition)resource, -1);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome(parent, "OperationOutcome", "OperationOutcome", (OperationOutcome)resource, -1);
    else if (resource instanceof Order)
      composeOrder(parent, "Order", "Order", (Order)resource, -1);
    else if (resource instanceof OrderResponse)
      composeOrderResponse(parent, "OrderResponse", "OrderResponse", (OrderResponse)resource, -1);
    else if (resource instanceof OrderSet)
      composeOrderSet(parent, "OrderSet", "OrderSet", (OrderSet)resource, -1);
    else if (resource instanceof Organization)
      composeOrganization(parent, "Organization", "Organization", (Organization)resource, -1);
    else if (resource instanceof Patient)
      composePatient(parent, "Patient", "Patient", (Patient)resource, -1);
    else if (resource instanceof PaymentNotice)
      composePaymentNotice(parent, "PaymentNotice", "PaymentNotice", (PaymentNotice)resource, -1);
    else if (resource instanceof PaymentReconciliation)
      composePaymentReconciliation(parent, "PaymentReconciliation", "PaymentReconciliation", (PaymentReconciliation)resource, -1);
    else if (resource instanceof Person)
      composePerson(parent, "Person", "Person", (Person)resource, -1);
    else if (resource instanceof Practitioner)
      composePractitioner(parent, "Practitioner", "Practitioner", (Practitioner)resource, -1);
    else if (resource instanceof Procedure)
      composeProcedure(parent, "Procedure", "Procedure", (Procedure)resource, -1);
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest(parent, "ProcedureRequest", "ProcedureRequest", (ProcedureRequest)resource, -1);
    else if (resource instanceof ProcessRequest)
      composeProcessRequest(parent, "ProcessRequest", "ProcessRequest", (ProcessRequest)resource, -1);
    else if (resource instanceof ProcessResponse)
      composeProcessResponse(parent, "ProcessResponse", "ProcessResponse", (ProcessResponse)resource, -1);
    else if (resource instanceof Protocol)
      composeProtocol(parent, "Protocol", "Protocol", (Protocol)resource, -1);
    else if (resource instanceof Provenance)
      composeProvenance(parent, "Provenance", "Provenance", (Provenance)resource, -1);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire(parent, "Questionnaire", "Questionnaire", (Questionnaire)resource, -1);
    else if (resource instanceof QuestionnaireResponse)
      composeQuestionnaireResponse(parent, "QuestionnaireResponse", "QuestionnaireResponse", (QuestionnaireResponse)resource, -1);
    else if (resource instanceof ReferralRequest)
      composeReferralRequest(parent, "ReferralRequest", "ReferralRequest", (ReferralRequest)resource, -1);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson(parent, "RelatedPerson", "RelatedPerson", (RelatedPerson)resource, -1);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment(parent, "RiskAssessment", "RiskAssessment", (RiskAssessment)resource, -1);
    else if (resource instanceof Schedule)
      composeSchedule(parent, "Schedule", "Schedule", (Schedule)resource, -1);
    else if (resource instanceof SearchParameter)
      composeSearchParameter(parent, "SearchParameter", "SearchParameter", (SearchParameter)resource, -1);
    else if (resource instanceof Sequence)
      composeSequence(parent, "Sequence", "Sequence", (Sequence)resource, -1);
    else if (resource instanceof Slot)
      composeSlot(parent, "Slot", "Slot", (Slot)resource, -1);
    else if (resource instanceof Specimen)
      composeSpecimen(parent, "Specimen", "Specimen", (Specimen)resource, -1);
    else if (resource instanceof StructureDefinition)
      composeStructureDefinition(parent, "StructureDefinition", "StructureDefinition", (StructureDefinition)resource, -1);
    else if (resource instanceof StructureMap)
      composeStructureMap(parent, "StructureMap", "StructureMap", (StructureMap)resource, -1);
    else if (resource instanceof Subscription)
      composeSubscription(parent, "Subscription", "Subscription", (Subscription)resource, -1);
    else if (resource instanceof Substance)
      composeSubstance(parent, "Substance", "Substance", (Substance)resource, -1);
    else if (resource instanceof SupplyDelivery)
      composeSupplyDelivery(parent, "SupplyDelivery", "SupplyDelivery", (SupplyDelivery)resource, -1);
    else if (resource instanceof SupplyRequest)
      composeSupplyRequest(parent, "SupplyRequest", "SupplyRequest", (SupplyRequest)resource, -1);
    else if (resource instanceof TestScript)
      composeTestScript(parent, "TestScript", "TestScript", (TestScript)resource, -1);
    else if (resource instanceof ValueSet)
      composeValueSet(parent, "ValueSet", "ValueSet", (ValueSet)resource, -1);
    else if (resource instanceof VisionPrescription)
      composeVisionPrescription(parent, "VisionPrescription", "VisionPrescription", (VisionPrescription)resource, -1);
    else
      throw new Error("Unhandled resource type "+resource.getClass().getName());
  }

  protected void composeType(Complex parent, String parentType, String name, Type value, int index) {
    if (value == null)
      return;
    else if (value instanceof MarkdownType)
      composeMarkdown(parent, parentType, name, (MarkdownType)value, index);
    else if (value instanceof IntegerType)
      composeInteger(parent, parentType, name, (IntegerType)value, index);
    else if (value instanceof DateTimeType)
      composeDateTime(parent, parentType, name, (DateTimeType)value, index);
    else if (value instanceof UnsignedIntType)
      composeUnsignedInt(parent, parentType, name, (UnsignedIntType)value, index);
    else if (value instanceof CodeType)
      composeCode(parent, parentType, name, (CodeType)value, index);
    else if (value instanceof DateType)
      composeDate(parent, parentType, name, (DateType)value, index);
    else if (value instanceof DecimalType)
      composeDecimal(parent, parentType, name, (DecimalType)value, index);
    else if (value instanceof UriType)
      composeUri(parent, parentType, name, (UriType)value, index);
    else if (value instanceof IdType)
      composeId(parent, parentType, name, (IdType)value, index);
    else if (value instanceof Base64BinaryType)
      composeBase64Binary(parent, parentType, name, (Base64BinaryType)value, index);
    else if (value instanceof TimeType)
      composeTime(parent, parentType, name, (TimeType)value, index);
    else if (value instanceof OidType)
      composeOid(parent, parentType, name, (OidType)value, index);
    else if (value instanceof PositiveIntType)
      composePositiveInt(parent, parentType, name, (PositiveIntType)value, index);
    else if (value instanceof StringType)
      composeString(parent, parentType, name, (StringType)value, index);
    else if (value instanceof BooleanType)
      composeBoolean(parent, parentType, name, (BooleanType)value, index);
    else if (value instanceof UuidType)
      composeUuid(parent, parentType, name, (UuidType)value, index);
    else if (value instanceof InstantType)
      composeInstant(parent, parentType, name, (InstantType)value, index);
    else if (value instanceof Extension)
      composeExtension(parent, parentType, name, (Extension)value, index);
    else if (value instanceof Narrative)
      composeNarrative(parent, parentType, name, (Narrative)value, index);
    else if (value instanceof Period)
      composePeriod(parent, parentType, name, (Period)value, index);
    else if (value instanceof Coding)
      composeCoding(parent, parentType, name, (Coding)value, index);
    else if (value instanceof Range)
      composeRange(parent, parentType, name, (Range)value, index);
    else if (value instanceof Quantity)
      composeQuantity(parent, parentType, name, (Quantity)value, index);
    else if (value instanceof Attachment)
      composeAttachment(parent, parentType, name, (Attachment)value, index);
    else if (value instanceof Ratio)
      composeRatio(parent, parentType, name, (Ratio)value, index);
    else if (value instanceof Annotation)
      composeAnnotation(parent, parentType, name, (Annotation)value, index);
    else if (value instanceof SampledData)
      composeSampledData(parent, parentType, name, (SampledData)value, index);
    else if (value instanceof Reference)
      composeReference(parent, parentType, name, (Reference)value, index);
    else if (value instanceof CodeableConcept)
      composeCodeableConcept(parent, parentType, name, (CodeableConcept)value, index);
    else if (value instanceof Identifier)
      composeIdentifier(parent, parentType, name, (Identifier)value, index);
    else if (value instanceof Signature)
      composeSignature(parent, parentType, name, (Signature)value, index);
    else if (value instanceof ElementDefinition)
      composeElementDefinition(parent, parentType, name, (ElementDefinition)value, index);
    else if (value instanceof Timing)
      composeTiming(parent, parentType, name, (Timing)value, index);
    else if (value instanceof ModuleMetadata)
      composeModuleMetadata(parent, parentType, name, (ModuleMetadata)value, index);
    else if (value instanceof Address)
      composeAddress(parent, parentType, name, (Address)value, index);
    else if (value instanceof HumanName)
      composeHumanName(parent, parentType, name, (HumanName)value, index);
    else if (value instanceof Meta)
      composeMeta(parent, parentType, name, (Meta)value, index);
    else if (value instanceof ContactPoint)
      composeContactPoint(parent, parentType, name, (ContactPoint)value, index);
    else
      throw new Error("Unhandled type");
  }

}

