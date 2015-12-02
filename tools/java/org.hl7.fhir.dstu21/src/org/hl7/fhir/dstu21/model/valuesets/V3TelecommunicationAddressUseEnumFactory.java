package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3TelecommunicationAddressUseEnumFactory implements EnumFactory<V3TelecommunicationAddressUse> {

  public V3TelecommunicationAddressUse fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("AS".equals(codeString))
      return V3TelecommunicationAddressUse.AS;
    if ("EC".equals(codeString))
      return V3TelecommunicationAddressUse.EC;
    if ("MC".equals(codeString))
      return V3TelecommunicationAddressUse.MC;
    if ("PG".equals(codeString))
      return V3TelecommunicationAddressUse.PG;
    throw new IllegalArgumentException("Unknown V3TelecommunicationAddressUse code '"+codeString+"'");
  }

  public String toCode(V3TelecommunicationAddressUse code) {
    if (code == V3TelecommunicationAddressUse.AS)
      return "AS";
    if (code == V3TelecommunicationAddressUse.EC)
      return "EC";
    if (code == V3TelecommunicationAddressUse.MC)
      return "MC";
    if (code == V3TelecommunicationAddressUse.PG)
      return "PG";
    return "?";
  }


}

