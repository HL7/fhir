package org.hl7.fhir.definitions.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.BaseValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.utilities.Utilities;

public class ValueSetValidator extends BaseValidator {

  private WorkerContext context;

  public ValueSetValidator(WorkerContext context) {
    this.context = context;
  }

  public void validate(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, boolean internal, boolean exemptFromCopyrightRule) {
    if (Utilities.noString(vs.getCopyright()) && !exemptFromCopyrightRule) {
      Set<String> sources = getListOfSources(vs);
      for (String s : sources) {
        rule(errors, "business-rule", "ValueSet["+vs.getId()+"].copyright", !s.equals("http://snomed.info/sct") && !s.equals("http://loinc.org"), 
           "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement is required for any value set that includes Snomed or Loinc codes");
        warning(errors, "business-rule", "ValueSet["+vs.getId()+"].copyright", s.startsWith("http://hl7.org"),
           "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement should be present for any value set that includes non-HL7 sourced codes");
      }
    }
    if (vs.hasDefine()) {
      Set<String> codes = new HashSet<String>();
      rule(errors, "business-rule", "ValueSet["+vs.getId()+"].define", vs.getDefine().hasCaseSensitiveElement() && vs.getDefine().getCaseSensitive(), 
        "Value set "+nameForErrors+" ("+vs.getName()+"): All value sets that define codes must mark them as case sensitive");
      checkCodeCaseDuplicates(errors, nameForErrors, vs, codes, vs.getDefine().getConcept());
      if (!vs.getDefine().getSystem().startsWith("http://hl7.org/fhir/v2/") && !vs.getDefine().getSystem().startsWith("urn:uuid:"))
        checkCodesForSpaces(errors, nameForErrors, vs, vs.getDefine().getConcept());
      if (!vs.getDefine().getSystem().startsWith("http://hl7.org/fhir/v2/") && !vs.getDefine().getSystem().startsWith("http://hl7.org/fhir/v3/") && !vs.getDefine().getSystem().startsWith("urn:uuid:"))
        warning(errors, "business-rule", "ValueSet["+vs.getId()+"].define", checkCodesForDisplayAndDefinition(vs.getDefine().getConcept()),
          "Value set "+nameForErrors+" ("+vs.getName()+") contains codes with missing display or definition");
    }
  }

  private void checkCodesForSpaces(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent cc : concept) {
      rule(errors, "business-rule", "ValueSet["+vs.getId()+"].define", !cc.hasCode() || !cc.getCode().contains(" "), 
         "Value set "+nameForErrors+" ("+vs.getName()+"/"+vs.getDefine().getSystem()+"): Defined codes cannot include spaces: "+cc.getCode());
      checkCodesForSpaces(errors, nameForErrors, vs, cc.getConcept());  
    }
  }

  private boolean checkCodesForDisplayAndDefinition(List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent cc : concept) {
      if (cc.hasCode() && !cc.hasDisplay())
        return false;
      if (!cc.hasDefinition())
        return false;
      if (!checkCodesForDisplayAndDefinition(cc.getConcept()))
        return false;
    }
    return true;
  }

  private void checkCodeCaseDuplicates(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, Set<String> codes, List<ConceptDefinitionComponent> concepts) {
    for (ConceptDefinitionComponent c : concepts) {
      String cc = c.getCode().toLowerCase();
        rule(errors, "business-rule", "ValueSet["+vs.getId()+"].define", !codes.contains(cc), 
          "Value set "+nameForErrors+" ("+vs.getName()+"): Code '"+cc+"' is defined twice, different by case - this is not allowed in a FHIR definition");
      if (c.hasConcept())
        checkCodeCaseDuplicates(errors, nameForErrors, vs, codes, c.getConcept());
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
