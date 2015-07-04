package org.hl7.fhir.definitions.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.definitions.validation.ValueSetValidator.VSDuplicateList;
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

  public class VSDuplicateList {
    private ValueSet vs;
    private String id;
    private String url;
    private Set<String> name = new HashSet<String>();
    private Set<String> description = new HashSet<String>();
    
    public VSDuplicateList(ValueSet vs) {
      super();
      this.vs = vs;
      id = vs.getId();
      url = vs.getUrl();
      for (String w : stripPunctuation(splitByCamelCase(vs.getName()), true).split(" ")) {
        String wl = w.toLowerCase();
        if (!Utilities.noString(w) && !grammarWord(wl) && !nullVSWord(wl)) {
          String wp = Utilities.pluralizeMe(wl);
          if (!name.contains(wp))
            name.add(wp);
        }
      }
      for (String w : stripPunctuation(splitByCamelCase(vs.getDescription()), true).split(" ")) {
        String wl = w.toLowerCase();
        if (!Utilities.noString(w) && !grammarWord(wl) && !nullVSWord(wl)) {
          String wp = Utilities.pluralizeMe(wl);
          if (!description.contains(wp))
            description.add(wp);
        }
      }
    }
  }

  private WorkerContext context;
  private List<String> fixups;
  private Set<ValueSet> handled = new HashSet<ValueSet>();
  private List<VSDuplicateList> duplicateList = new ArrayList<ValueSetValidator.VSDuplicateList>();

  public ValueSetValidator(WorkerContext context, List<String> fixups) {
    this.context = context;
    this.fixups = fixups;
  }

  public boolean nullVSWord(String wp) {
    return 
        wp.equals("vs") ||
        wp.equals("valueset") ||
        wp.equals("value") ||
        wp.equals("set") ||
        wp.equals("includes") ||
        wp.equals("code") ||
        wp.equals("system") ||
        wp.equals("contents") ||
        wp.equals("definition") ||
        wp.equals("hl7") ||
        wp.equals("v2") ||
        wp.equals("v3") ||
        wp.equals("table") ||
        wp.equals("codes");
  }
 
  
  public void validate(List<ValidationMessage> errors, String nameForErrors, ValueSet vs, boolean internal, boolean exemptFromCopyrightRule) {
    int o_warnings = 0;
    for (ValidationMessage em : errors) {
      if (em.getLevel() == IssueSeverity.WARNING)
        o_warnings++;
    }
    if (!handled.contains(vs)) {
      handled.add(vs);
      duplicateList.add(new VSDuplicateList(vs));
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

  public void checkDuplicates(List<ValidationMessage> errors) {
    for (int i = 0; i < duplicateList.size()-1; i++) {
      for (int j = i+1; j < duplicateList.size(); j++) {
        VSDuplicateList vd1 = duplicateList.get(i);
        VSDuplicateList vd2 = duplicateList.get(j);
        if (rule(errors, IssueType.BUSINESSRULE, "ValueSetComparison", !vd1.id.equals(vd2.id), "Duplicate Value Set ids : "+vd1.id+"("+vd1.vs.getName()+") & "+vd2.id+"("+vd2.vs.getName()+") (id)") &&
            rule(errors, IssueType.BUSINESSRULE, "ValueSetComparison", !vd1.url.equals(vd2.url), "Duplicate Value Set URLs: "+vd1.id+"("+vd1.vs.getName()+") & "+vd2.id+"("+vd2.vs.getName()+") (url)")) {
          if (isInternal(vd1.url) || isInternal(vd2.url)) {
            warning(errors, IssueType.BUSINESSRULE, "ValueSetComparison", areDisjoint(vd1.name, vd2.name), "Duplicate Valueset Names: "+vd1.vs.getUserString("path")+" ("+vd1.vs.getName()+") & "+vd2.vs.getUserString("path")+" ("+vd2.vs.getName()+") (name: "+vd1.name.toString()+" / "+vd2.name.toString()+"))", 
                "Duplicate Valueset Names: <a href=\""+vd1.vs.getUserString("path")+"\">"+vd1.id+"</a> ("+vd1.vs.getName()+") & <a href=\""+vd2.vs.getUserString("path")+"\">"+vd2.id+"</a> ("+vd2.vs.getName()+") (name: "+vd1.name.toString()+" / "+vd2.name.toString()+"))");
            warning(errors, IssueType.BUSINESSRULE, "ValueSetComparison", areDisjoint(vd1.description, vd2.description), "Duplicate Valueset Definitions: "+vd1.vs.getUserString("path")+" ("+vd1.vs.getName()+") & "+vd2.vs.getUserString("path")+" ("+vd2.vs.getName()+") (description: "+vd1.description.toString()+" / "+vd2.description.toString()+")",
                "Duplicate Valueset descriptions: <a href=\""+vd1.vs.getUserString("path")+"\">"+vd1.id+"</a> ("+vd1.vs.getName()+") & <a href=\""+vd2.vs.getUserString("path")+"\">"+vd2.id+"</a> ("+vd2.vs.getName()+") (description: "+vd1.description.toString()+" / "+vd2.description.toString()+"))");
          }
        }
      }
    }
  }

  private boolean isInternal(String url) {
    return url.startsWith("http://hl7.org/fhir") && !url.startsWith("http://hl7.org/fhir/v2") && !url.startsWith("http://hl7.org/fhir/v3");
  }

  private boolean areDisjoint(Set<String> set1, Set<String> set2) {
    if (set1.isEmpty() || set2.isEmpty())
      return true;
    
    Set<String> set = new HashSet<String>();
    for (String s : set1) 
      if (!set2.contains(s))
        set.add(s);
    for (String s : set2) 
      if (!set1.contains(s))
        set.add(s);
    return !set.isEmpty();
  }

}
