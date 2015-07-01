package org.hl7.fhir.definitions.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.valuesets.IssueType;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.BaseValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.utilities.Utilities;

public class ValueSetValidator extends BaseValidator {

  private WorkerContext context;
  private List<String> fixups;

  public ValueSetValidator(WorkerContext context, List<String> fixups) {
    this.context = context;
    this.fixups = fixups;
  }

  public void validate(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, boolean internal, boolean exemptFromCopyrightRule) {
    int o_warnings = 0;
    for (ValidationMessage em : errors) {
      if (em.getLevel() == IssueSeverity.WARNING)
        o_warnings++;
    }
    if (Utilities.noString(vs.getCopyright()) && !exemptFromCopyrightRule) {
      Set<String> sources = getListOfSources(vs);
      for (String s : sources) {
        rule(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].copyright", !s.equals("http://snomed.info/sct") && !s.equals("http://loinc.org"), 
           "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement is required for any value set that includes Snomed or Loinc codes");
        warning(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].copyright", s.startsWith("http://hl7.org") || s.startsWith("urn:iso") || s.startsWith("urn:ietf") || s.startsWith("http://need.a.uri.org")
            || s.contains("cdc.gov") || s.startsWith("urn:oid:"),
           "Value set "+nameForErrors+" ("+vs.getName()+"): A copyright statement should be present for any value set that includes non-HL7 sourced codes ("+s+")");
      }
    }
    if (fixups.contains(vs.getId()))
      fixup(vs);

    if (vs.hasDefine()) {
      Set<String> codes = new HashSet<String>();
      if (rule(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].define", vs.getDefine().hasSystem(), "If a value set has a define, it must have a system")) {
        rule(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].define", vs.getDefine().hasCaseSensitiveElement() && vs.getDefine().getCaseSensitive(), 
            "Value set "+nameForErrors+" ("+vs.getName()+"): All value sets that define codes must mark them as case sensitive");
        checkCodeCaseDuplicates(errors, nameForErrors, vs, codes, vs.getDefine().getConcept());
        if (!vs.getDefine().getSystem().startsWith("http://hl7.org/fhir/v2/") && 
            !vs.getDefine().getSystem().startsWith("urn:uuid:") && 
            !vs.getDefine().getSystem().startsWith("http://hl7.org/fhir/v3/")) {
          checkCodesForDisplayAndDefinition(errors, "ValueSet["+vs.getId()+"].define", vs.getDefine().getConcept());
          checkCodesForSpaces(errors, "ValueSet["+vs.getId()+"].define", vs, vs.getDefine().getConcept());
        }
      }
    }
    if (vs.hasCompose()) {
      for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
        if (canValidate(inc.getSystem())) {
          int i = 0;
          for (ConceptReferenceComponent cc : inc.getConcept()) {
            i++;
            if (inc.getSystem().equals("http://nema.org/dicom/dicm"))
              warning(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].compose.include["+Integer.toString(i)+"]", isValidCode(cc.getCode(), inc.getSystem()), 
                  "The code '"+cc.getCode()+"' is not valid in the system "+inc.getSystem());
            else
              rule(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].compose.include["+Integer.toString(i)+"]", isValidCode(cc.getCode(), inc.getSystem()), 
                "The code '"+cc.getCode()+"' is not valid in the system "+inc.getSystem());
          }
        }
      }
    }
    int warnings = 0;
    for (ValidationMessage em : errors) {
      if (em.getLevel() == IssueSeverity.WARNING)
        warnings++;
    }
    vs.setUserData("warnings", o_warnings - warnings);
  }

  private boolean isValidCode(String code, String system) {
    ValueSet cs = context.getCodeSystems().get(system);
    if (cs == null) 
      return context.getTerminologyServices().validateCode(system, code, null) == null;
    else {
      if (hasCode(code, cs.getDefine().getConcept()))
        return true;
      return false;
    }
  }

  private boolean hasCode(String code, List<ConceptDefinitionComponent> list) {
    for (ConceptDefinitionComponent cc : list) {
      if (cc.getCode().equals(code))
        return true;
      if (hasCode(code, cc.getConcept()))
        return true;
    }
    return false;
  }

  private boolean canValidate(String system) {
    return context.getCodeSystems().containsKey(system) || context.getTerminologyServices().supportsSystem(system);
  }

  private void fixup(ValueSet vs) {
    if (vs.hasDefine()) {
      for (ConceptDefinitionComponent cc: vs.getDefine().getConcept())
        fixup(cc);
    }
  }

  private void fixup(ConceptDefinitionComponent cc) {
    if (cc.hasDisplay() && !cc.hasDefinition())
      cc.setDefinition(cc.getDisplay());
    if (!cc.hasDisplay() && cc.hasDefinition())
      cc.setDisplay(cc.getDefinition());
    for (ConceptDefinitionComponent gc: cc.getConcept())
      fixup(gc);
  }

  private void checkCodesForSpaces(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, List<ConceptDefinitionComponent> concept) {
    for (ConceptDefinitionComponent cc : concept) {
      rule(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].define", !cc.hasCode() || !cc.getCode().contains(" "), 
         "Value set "+nameForErrors+" ("+vs.getName()+"/"+vs.getDefine().getSystem()+"): Defined codes cannot include spaces: "+cc.getCode());
      checkCodesForSpaces(errors, nameForErrors, vs, cc.getConcept());  
    }
  }

  private void checkCodesForDisplayAndDefinition(List<ValidationMessage> errors, String path, List<ConceptDefinitionComponent> concept) {
    int i = 0;
    for (ConceptDefinitionComponent cc : concept) {
      String p = path +"["+Integer.toString(i)+"]";
      warning(errors, IssueType.BUSINESSRULE, p, !cc.hasCode() || cc.hasDisplay(), "code '"+cc.getCode()+"' has no display");
      warning(errors, IssueType.BUSINESSRULE, p, cc.hasDefinition(), "code '"+cc.getCode()+"' has no definition");
      checkCodesForDisplayAndDefinition(errors, p+".concept", cc.getConcept());
      i++;
    }
  }

  private void checkCodeCaseDuplicates(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, Set<String> codes, List<ConceptDefinitionComponent> concepts) {
    for (ConceptDefinitionComponent c : concepts) {
      String cc = c.getCode().toLowerCase();
        rule(errors, IssueType.BUSINESSRULE, "ValueSet["+vs.getId()+"].define", !codes.contains(cc), 
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
