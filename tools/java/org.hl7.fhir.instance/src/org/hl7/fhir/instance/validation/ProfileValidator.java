package org.hl7.fhir.instance.validation;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.utils.WorkerContext.ExtensionDefinitionResult;
import org.hl7.fhir.utilities.Utilities;

public class ProfileValidator {

  WorkerContext context;

  public void setContext(WorkerContext context) {
    this.context = context;    
  }

  public List<String> validate(Profile profile) throws Exception {
    List<String> errors = new ArrayList<String>();
    // first check: extensions must exist
    for (ElementDefinition ec : profile.getDifferential().getElement()) {
      checkExtensions(profile, errors, "differential", ec);
    }
    if (!profile.hasSnapshot())
      errors.add("missing Snapshot at "+profile.getName()+"."+profile.getName());
    else for (ElementDefinition ec : profile.getSnapshot().getElement()) {
      checkExtensions(profile, errors, "snapshot", ec);
    }
    return errors;
  }

  private void checkExtensions(Profile profile, List<String> errors, String kind, ElementDefinition ec) throws Exception {
    if (!ec.getType().isEmpty() && ec.getType().get(0).getCode().equals("Extension")) {
      String url = ec.getType().get(0).getProfile();
      if (!Utilities.noString(url)) {
        ExtensionDefinitionResult defn = context.getExtensionDefinition(null, url);
        if (defn == null)
          errors.add("Unable to find Extension '"+url+"' referenced at "+profile.getUrl()+" "+kind+" "+ec.getPath()+" ("+ec.getName()+")");
      }
    }
  }
  
}
