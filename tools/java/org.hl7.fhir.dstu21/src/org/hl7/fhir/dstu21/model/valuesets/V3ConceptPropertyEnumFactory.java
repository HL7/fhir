package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3ConceptPropertyEnumFactory implements EnumFactory<V3ConceptProperty> {

  public V3ConceptProperty fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("_ValueSetPropertyId".equals(codeString))
      return V3ConceptProperty._VALUESETPROPERTYID;
    if ("appliesTo".equals(codeString))
      return V3ConceptProperty.APPLIESTO;
    if ("howApplies".equals(codeString))
      return V3ConceptProperty.HOWAPPLIES;
    if ("openIssue".equals(codeString))
      return V3ConceptProperty.OPENISSUE;
    if ("conceptStatusQualifier".equals(codeString))
      return V3ConceptProperty.CONCEPTSTATUSQUALIFIER;
    if ("inverseRelationship".equals(codeString))
      return V3ConceptProperty.INVERSERELATIONSHIP;
    if ("OID".equals(codeString))
      return V3ConceptProperty.OID;
    if ("specializedByDomain".equals(codeString))
      return V3ConceptProperty.SPECIALIZEDBYDOMAIN;
    throw new IllegalArgumentException("Unknown V3ConceptProperty code '"+codeString+"'");
  }

  public String toCode(V3ConceptProperty code) {
    if (code == V3ConceptProperty._VALUESETPROPERTYID)
      return "_ValueSetPropertyId";
    if (code == V3ConceptProperty.APPLIESTO)
      return "appliesTo";
    if (code == V3ConceptProperty.HOWAPPLIES)
      return "howApplies";
    if (code == V3ConceptProperty.OPENISSUE)
      return "openIssue";
    if (code == V3ConceptProperty.CONCEPTSTATUSQUALIFIER)
      return "conceptStatusQualifier";
    if (code == V3ConceptProperty.INVERSERELATIONSHIP)
      return "inverseRelationship";
    if (code == V3ConceptProperty.OID)
      return "OID";
    if (code == V3ConceptProperty.SPECIALIZEDBYDOMAIN)
      return "specializedByDomain";
    return "?";
  }


}

