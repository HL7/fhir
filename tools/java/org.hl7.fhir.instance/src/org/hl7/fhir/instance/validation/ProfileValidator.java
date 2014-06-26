package org.hl7.fhir.instance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.utilities.Utilities;

public class ProfileValidator {

  private Map<String, Profile> profiles;

  public void setProfiles(Map<String, Profile> profiles) {
    this.profiles = profiles;    
  }

  public List<String> validate(Profile profile) {
    List<String> errors = new ArrayList<String>();
    // first check: extensions must exist
    for (ProfileStructureComponent sc : profile.getStructure()) {
      for (ElementComponent ec : sc.getDifferential().getElement()) {
        checkExtensions(profile, errors, ec);
      }
      for (ElementComponent ec : sc.getSnapshot().getElement()) {
        checkExtensions(profile, errors, ec);
      }
    }
    return errors;
  }

  private void checkExtensions(Profile profile, List<String> errors, ElementComponent ec) {
    if (ec.getDefinition() != null && !ec.getDefinition().getType().isEmpty() && ec.getDefinition().getType().get(0).getCodeSimple().equals("Extension")) {
      String url = ec.getDefinition().getType().get(0).getProfileSimple();
      if (!Utilities.noString(url)) {
        ProfileExtensionDefnComponent defn = getExtensionDefinition(profile, url);
        if (defn == null)
          errors.add("Unable to find Extension '"+url+"'");
      }
    }
  }

  private ProfileExtensionDefnComponent getExtensionDefinition(Profile self, String url) {
    String parts[] = url.split("#");
    if (parts.length != 2)
      return null;
    Profile p = Utilities.noString(parts[0]) ? self : profiles.get(parts[0]);
    if (p == null)
      return null;
    for (ProfileExtensionDefnComponent defn : p.getExtensionDefn()) {
      if (defn.getCodeSimple().equals(parts[1]))
        return defn;
    }
    return null;
  }

  
}
