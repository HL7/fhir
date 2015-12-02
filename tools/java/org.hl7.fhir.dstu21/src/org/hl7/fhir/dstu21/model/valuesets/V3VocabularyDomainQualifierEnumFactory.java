package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3VocabularyDomainQualifierEnumFactory implements EnumFactory<V3VocabularyDomainQualifier> {

  public V3VocabularyDomainQualifier fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("_Extensibility".equals(codeString))
      return V3VocabularyDomainQualifier._EXTENSIBILITY;
    if ("CNE".equals(codeString))
      return V3VocabularyDomainQualifier.CNE;
    if ("CWE".equals(codeString))
      return V3VocabularyDomainQualifier.CWE;
    if ("_RealmOfUse".equals(codeString))
      return V3VocabularyDomainQualifier._REALMOFUSE;
    if ("Canada".equals(codeString))
      return V3VocabularyDomainQualifier.CANADA;
    if ("NorthAmerica".equals(codeString))
      return V3VocabularyDomainQualifier.NORTHAMERICA;
    if ("USA".equals(codeString))
      return V3VocabularyDomainQualifier.USA;
    if ("UV".equals(codeString))
      return V3VocabularyDomainQualifier.UV;
    throw new IllegalArgumentException("Unknown V3VocabularyDomainQualifier code '"+codeString+"'");
  }

  public String toCode(V3VocabularyDomainQualifier code) {
    if (code == V3VocabularyDomainQualifier._EXTENSIBILITY)
      return "_Extensibility";
    if (code == V3VocabularyDomainQualifier.CNE)
      return "CNE";
    if (code == V3VocabularyDomainQualifier.CWE)
      return "CWE";
    if (code == V3VocabularyDomainQualifier._REALMOFUSE)
      return "_RealmOfUse";
    if (code == V3VocabularyDomainQualifier.CANADA)
      return "Canada";
    if (code == V3VocabularyDomainQualifier.NORTHAMERICA)
      return "NorthAmerica";
    if (code == V3VocabularyDomainQualifier.USA)
      return "USA";
    if (code == V3VocabularyDomainQualifier.UV)
      return "UV";
    return "?";
  }


}

