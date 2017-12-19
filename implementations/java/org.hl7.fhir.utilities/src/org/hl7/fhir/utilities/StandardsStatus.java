package org.hl7.fhir.utilities;

import org.hl7.fhir.exceptions.FHIRException;

public enum StandardsStatus {

  DRAFT, TRIAL_USE, NORMATIVE, INFORMATIVE, EXTERNAL;

  public String toDisplay() {
    switch (this) {
    case DRAFT : 
      return "Draft";  
    case NORMATIVE  : 
      return "Normative";
    case TRIAL_USE : 
      return "Trial Use";  
    case INFORMATIVE:
      return "Informative";
    case EXTERNAL:
      return "External";
    }
    return "?";
  }

  public static StandardsStatus fromCode(String value) throws FHIRException {
    if (Utilities.noString(value))
      return null;
    if (value.equalsIgnoreCase("draft"))
      return DRAFT;
    if (value.equalsIgnoreCase("NORMATIVE")) 
      return NORMATIVE;
    if (value.equalsIgnoreCase("TRIAL_USE")) 
      return TRIAL_USE;  
    if (value.equalsIgnoreCase("TRIAL-USE")) 
      return TRIAL_USE;  
    if (value.equalsIgnoreCase("TRIAL USE")) 
      return TRIAL_USE;  
    if (value.equalsIgnoreCase("INFORMATIVE"))
      return INFORMATIVE;
    if (value.equalsIgnoreCase("EXTERNAL"))
      return EXTERNAL;
    throw new FHIRException("Incorrect Standards Status '"+value+"'");
  }

  public String getAbbrev() {
    switch (this) {
    case DRAFT : 
      return "D";  
    case NORMATIVE  : 
      return "N";
    case TRIAL_USE : 
      return "TU";  
    case INFORMATIVE:
      return "I";
    case EXTERNAL:
      return "X";
    }
    return "?";
  }

  public String getColor() {
    switch (this) {
    case DRAFT : 
      return "#efefef";  
    case NORMATIVE  : 
      return "#e6ffe6";
    case TRIAL_USE : 
      return "#ffe6e6";  
    case INFORMATIVE:
      return "#ffffe6";
    case EXTERNAL:
      return "#e6f2ff";
    }
    return "?";
  }

  public String getColorSvg() {
    switch (this) {
    case DRAFT : 
      return "#efefef";  
    case NORMATIVE  : 
      return "#eaffea";
    case TRIAL_USE : 
      return "#ffeaea";  
    case INFORMATIVE:
      return "#ffffea";
    case EXTERNAL:
      return "#eaf6ff";
    }
    return "?";
  }

  public boolean canDependOn(StandardsStatus tgtSS) {
    if (this == DRAFT || this == INFORMATIVE || this == EXTERNAL)
      return true;
    if (this == TRIAL_USE)
      return (tgtSS != DRAFT);
    if (this == NORMATIVE)
      return (tgtSS == NORMATIVE || tgtSS == EXTERNAL );
    return false;
  }

  public boolean isLowerThan(StandardsStatus status) {
    return this.compareTo(status) <0;
  }
}
