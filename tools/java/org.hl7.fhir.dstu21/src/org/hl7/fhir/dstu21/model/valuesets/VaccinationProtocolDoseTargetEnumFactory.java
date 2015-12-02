package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class VaccinationProtocolDoseTargetEnumFactory implements EnumFactory<VaccinationProtocolDoseTarget> {

  public VaccinationProtocolDoseTarget fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("crs".equals(codeString))
      return VaccinationProtocolDoseTarget.CRS;
    if ("dip".equals(codeString))
      return VaccinationProtocolDoseTarget.DIP;
    if ("mea".equals(codeString))
      return VaccinationProtocolDoseTarget.MEA;
    if ("mum".equals(codeString))
      return VaccinationProtocolDoseTarget.MUM;
    if ("rub".equals(codeString))
      return VaccinationProtocolDoseTarget.RUB;
    if ("tet".equals(codeString))
      return VaccinationProtocolDoseTarget.TET;
    if ("hib".equals(codeString))
      return VaccinationProtocolDoseTarget.HIB;
    if ("per".equals(codeString))
      return VaccinationProtocolDoseTarget.PER;
    if ("pol".equals(codeString))
      return VaccinationProtocolDoseTarget.POL;
    throw new IllegalArgumentException("Unknown VaccinationProtocolDoseTarget code '"+codeString+"'");
  }

  public String toCode(VaccinationProtocolDoseTarget code) {
    if (code == VaccinationProtocolDoseTarget.CRS)
      return "crs";
    if (code == VaccinationProtocolDoseTarget.DIP)
      return "dip";
    if (code == VaccinationProtocolDoseTarget.MEA)
      return "mea";
    if (code == VaccinationProtocolDoseTarget.MUM)
      return "mum";
    if (code == VaccinationProtocolDoseTarget.RUB)
      return "rub";
    if (code == VaccinationProtocolDoseTarget.TET)
      return "tet";
    if (code == VaccinationProtocolDoseTarget.HIB)
      return "hib";
    if (code == VaccinationProtocolDoseTarget.PER)
      return "per";
    if (code == VaccinationProtocolDoseTarget.POL)
      return "pol";
    return "?";
  }


}

