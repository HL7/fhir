package org.hl7.fhir.definitions.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.utilities.Utilities;

public class ValueSetValidator {

  private WorkerContext context;

  public ValueSetValidator(WorkerContext context) {
    this.context = context;
  }

  public void validate(String nameForErrors, ValueSet vs, boolean internal, boolean exemptFromCopyrightRule) throws Exception {
    if (Utilities.noString(vs.getCopyright()) && !exemptFromCopyrightRule) {
      Set<String> sources = getListOfSources(vs);
      for (String s : sources) {
        if (s.equals("http://snomed.info/sct") || s.equals("http://loinc.org"))
          throw new Exception("Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement is required for any value set that includes Snomed or Loinc codes");
        if (!s.startsWith("http://hl7.org"))
          System.out.println( "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement should be present for any value set that includes non-HL7 sourced codes");
      }
    }
  }

  private Set<String> getListOfSources(ValueSet vs) {
    Set<String> sources = new HashSet<String>();
    if (vs.getDefine() != null)
      sources.add(vs.getDefine().getSystem());
    if (vs.getCompose() != null) {
      for (ConceptSetComponent imp : vs.getCompose().getInclude()) 
        sources.add(imp.getSystem());
      for (ConceptSetComponent imp : vs.getCompose().getExclude()) 
        sources.add(imp.getSystem());
    }
    return sources;
  }


}
