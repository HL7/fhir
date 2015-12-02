package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3PostalAddressUseEnumFactory implements EnumFactory<V3PostalAddressUse> {

  public V3PostalAddressUse fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("_AddressUse".equals(codeString))
      return V3PostalAddressUse._ADDRESSUSE;
    if ("BAD".equals(codeString))
      return V3PostalAddressUse.BAD;
    if ("H".equals(codeString))
      return V3PostalAddressUse.H;
    if ("HP".equals(codeString))
      return V3PostalAddressUse.HP;
    if ("HV".equals(codeString))
      return V3PostalAddressUse.HV;
    if ("TMP".equals(codeString))
      return V3PostalAddressUse.TMP;
    if ("WP".equals(codeString))
      return V3PostalAddressUse.WP;
    if ("DIR".equals(codeString))
      return V3PostalAddressUse.DIR;
    if ("PUB".equals(codeString))
      return V3PostalAddressUse.PUB;
    if ("PHYS".equals(codeString))
      return V3PostalAddressUse.PHYS;
    if ("PST".equals(codeString))
      return V3PostalAddressUse.PST;
    throw new IllegalArgumentException("Unknown V3PostalAddressUse code '"+codeString+"'");
  }

  public String toCode(V3PostalAddressUse code) {
    if (code == V3PostalAddressUse._ADDRESSUSE)
      return "_AddressUse";
    if (code == V3PostalAddressUse.BAD)
      return "BAD";
    if (code == V3PostalAddressUse.H)
      return "H";
    if (code == V3PostalAddressUse.HP)
      return "HP";
    if (code == V3PostalAddressUse.HV)
      return "HV";
    if (code == V3PostalAddressUse.TMP)
      return "TMP";
    if (code == V3PostalAddressUse.WP)
      return "WP";
    if (code == V3PostalAddressUse.DIR)
      return "DIR";
    if (code == V3PostalAddressUse.PUB)
      return "PUB";
    if (code == V3PostalAddressUse.PHYS)
      return "PHYS";
    if (code == V3PostalAddressUse.PST)
      return "PST";
    return "?";
  }


}

