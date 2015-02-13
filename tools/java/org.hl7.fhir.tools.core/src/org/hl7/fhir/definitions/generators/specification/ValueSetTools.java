package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.utilities.Utilities;

public class ValueSetTools {

  public static final String NAME_SPEC_USAGE = "spec.usage";

  public static void updateUsage(ValueSet vs, String usage) {
    if (Utilities.noString(usage))
      return;
    
    String s = (String) vs.getUserData(NAME_SPEC_USAGE);
    if (Utilities.noString(s))
      s = usage;
    else if (!s.equals("core")) {
      if (usage.equals("core"))
        s = "core";
      else if (!s.contains(usage))
        s = s+","+usage;
    }
    vs.setUserData(NAME_SPEC_USAGE, s);
  }

}
