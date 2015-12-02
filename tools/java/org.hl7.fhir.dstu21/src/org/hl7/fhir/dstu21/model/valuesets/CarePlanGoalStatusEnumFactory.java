package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class CarePlanGoalStatusEnumFactory implements EnumFactory<CarePlanGoalStatus> {

  public CarePlanGoalStatus fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("in-progress".equals(codeString))
      return CarePlanGoalStatus.INPROGRESS;
    if ("achieved".equals(codeString))
      return CarePlanGoalStatus.ACHIEVED;
    if ("sustaining".equals(codeString))
      return CarePlanGoalStatus.SUSTAINING;
    if ("cancelled".equals(codeString))
      return CarePlanGoalStatus.CANCELLED;
    throw new IllegalArgumentException("Unknown CarePlanGoalStatus code '"+codeString+"'");
  }

  public String toCode(CarePlanGoalStatus code) {
    if (code == CarePlanGoalStatus.INPROGRESS)
      return "in-progress";
    if (code == CarePlanGoalStatus.ACHIEVED)
      return "achieved";
    if (code == CarePlanGoalStatus.SUSTAINING)
      return "sustaining";
    if (code == CarePlanGoalStatus.CANCELLED)
      return "cancelled";
    return "?";
  }


}

