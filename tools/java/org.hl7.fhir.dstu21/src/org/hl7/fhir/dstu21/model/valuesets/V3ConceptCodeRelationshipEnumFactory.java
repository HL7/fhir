package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3ConceptCodeRelationshipEnumFactory implements EnumFactory<V3ConceptCodeRelationship> {

  public V3ConceptCodeRelationship fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("hasPart".equals(codeString))
      return V3ConceptCodeRelationship.HASPART;
    if ("hasSubtype".equals(codeString))
      return V3ConceptCodeRelationship.HASSUBTYPE;
    if ("smallerThan".equals(codeString))
      return V3ConceptCodeRelationship.SMALLERTHAN;
    throw new IllegalArgumentException("Unknown V3ConceptCodeRelationship code '"+codeString+"'");
  }

  public String toCode(V3ConceptCodeRelationship code) {
    if (code == V3ConceptCodeRelationship.HASPART)
      return "hasPart";
    if (code == V3ConceptCodeRelationship.HASSUBTYPE)
      return "hasSubtype";
    if (code == V3ConceptCodeRelationship.SMALLERTHAN)
      return "smallerThan";
    return "?";
  }


}

