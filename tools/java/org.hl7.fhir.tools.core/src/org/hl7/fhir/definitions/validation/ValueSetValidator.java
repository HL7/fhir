package org.hl7.fhir.definitions.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
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
    if (vs.hasDefine()) {
      Set<String> codes = new HashSet<String>();
      if (!vs.getDefine().hasCaseSensitiveElement() || !vs.getDefine().getCaseSensitive())
        throw new Exception("Value set "+nameForErrors+" ("+vs.getName()+"): All value sets that define codes must mark them as case sensitive");
      checkCodeCaseDuplicates(nameForErrors, vs, codes, vs.getDefine().getConcept());
      if (!vs.getDefine().getSystem().startsWith("http://hl7.org/fhir/v2/") && !vs.getDefine().getSystem().startsWith("urn:uuid:"))
        checkCodesForSpaces(nameForErrors, vs, vs.getDefine().getConcept());
    }
  }

  private void checkCodesForSpaces(String nameForErrors, ValueSet vs, List<ConceptDefinitionComponent> concept) throws Exception {
    for (ConceptDefinitionComponent cc : concept) {
      if (cc.hasCode() && cc.getCode().contains(" "))
        throw new Exception("Value set "+nameForErrors+" ("+vs.getName()+"/"+vs.getDefine().getSystem()+"): Defined codes cannot include spaces: "+cc.getCode());
      checkCodesForSpaces(nameForErrors, vs, cc.getConcept());  
    }
  }

  private void checkCodeCaseDuplicates(String nameForErrors, ValueSet vs, Set<String> codes, List<ConceptDefinitionComponent> concepts) throws Exception {
    for (ConceptDefinitionComponent c : concepts) {
      String cc = c.getCode().toLowerCase();
      if (codes.contains(cc))
        throw new Exception("Value set "+nameForErrors+" ("+vs.getName()+"): Code '"+cc+"' is defined twice, different by case - this is not allowed in a FHIR definition");
      if (c.hasConcept())
        checkCodeCaseDuplicates(nameForErrors, vs, codes, c.getConcept());
    }
  }

  private Set<String> getListOfSources(ValueSet vs) {
    Set<String> sources = new HashSet<String>();
    if (vs.hasDefine())
      sources.add(vs.getDefine().getSystem());
    if (vs.hasCompose()) {
      for (ConceptSetComponent imp : vs.getCompose().getInclude()) 
        sources.add(imp.getSystem());
      for (ConceptSetComponent imp : vs.getCompose().getExclude()) 
        sources.add(imp.getSystem());
    }
    return sources;
  }


}
