package org.hl7.fhir.instance.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.utils.ValueSetExpander.ValueSetExpansionOutcome;

public class ValueSetCheckerSimple implements ValueSetChecker {

  private ValueSet valueset;
  private TerminologyServices locator;
  private ValueSetExpanderFactory factory;
  private Map<String, ValueSet> valuesets;
  private Map<String, ValueSet> codesystems;

  public ValueSetCheckerSimple(ValueSet source, TerminologyServices locator, ValueSetExpanderFactory factory, Map<String, ValueSet> codesystems, Map<String, ValueSet> valuesets) {
    this.valueset = source;
    this.locator = locator;
    this.factory = factory;
    this.codesystems = codesystems;
    this.valuesets = valuesets;
  }

  @Override
  public boolean codeInValueSet(String system, String code) {
    if (valueset.getDefine() != null && system.equals(valueset.getDefine().getSystemSimple()) && codeInDefine(valueset.getDefine().getConcept(), code, valueset.getDefine().getCaseSensitiveSimple()))
     return true;

    if (valueset.getCompose() != null) {
      boolean ok = false;
      for (Uri uri : valueset.getCompose().getImport()) {
        ok = ok || inImport(uri.getValue(), system, code);
      }
      for (ConceptSetComponent vsi : valueset.getCompose().getInclude()) {
        ok = ok || inComponent(vsi, system, code);
      }
      for (ConceptSetComponent vsi : valueset.getCompose().getExclude()) {
        ok = ok && !inComponent(vsi, system, code);
      }
    }
    
    return false;
  }

  private boolean inImport(String uri, String system, String code) {
    ValueSet vs = valuesets.get(uri);
    if (vs == null) 
      return false ; // we can't tell
    return codeInExpansion(factory.getExpander().expand(vs), system, code);
  }

  private boolean codeInExpansion(ValueSetExpansionOutcome vso, String system, String code) {
    if (vso.getService() != null) {
      return vso.getService().codeInValueSet(system, code);
    } else {
      for (ValueSetExpansionContainsComponent c : vso.getValueset().getExpansion().getContains()) {
        if (code.equals(c.getCodeSimple()) && (system == null || system.equals(c.getSystemSimple())))
          return true;
        if (codeinExpansion(c, system, code)) 
          return true;
      }
    }
    return false;
  }

  private boolean codeinExpansion(ValueSetExpansionContainsComponent cnt, String system, String code) {
    for (ValueSetExpansionContainsComponent c : cnt.getContains()) {
      if (code.equals(c.getCodeSimple()) && system.equals(c.getSystemSimple().toString()))
        return true;
      if (codeinExpansion(c, system, code)) 
        return true;
    }
    return false;
  }


  private boolean inComponent(ConceptSetComponent vsi, String system, String code) {
    if (!vsi.getSystemSimple().equals(system))
      return false; 
    // whether we know the system or not, we'll accept the stated codes at face value
    for (Code cc : vsi.getCode())
      if (cc.getValue().equals(code)) {
        return false;
      }
      
    if (codesystems.containsKey(system)) {
      ValueSet def = codesystems.get(system);
      if (!def.getDefine().getCaseSensitiveSimple()) {
        // well, ok, it's not case sensitive - we'll check that too now
        for (Code cc : vsi.getCode())
          if (cc.getValue().equalsIgnoreCase(code)) {
            return false;
          }
      }
      if (vsi.getCode().isEmpty() && vsi.getFilter().isEmpty()) {
        return codeInDefine(def.getDefine().getConcept(), code, def.getDefine().getCaseSensitiveSimple());
      }
      for (ConceptSetFilterComponent f: vsi.getFilter())
        throw new Error("not done yet");

      return false;
    } else if (locator.supportsSystem(system)) {
      return locator.checkVS(vsi, system, code);
    } else
      // we don't know this system, and can't resolve it
      return false;
  }

  private boolean codeInDefine(List<ValueSetDefineConceptComponent> concepts, String code, boolean caseSensitive) {
    for (ValueSetDefineConceptComponent c : concepts) {
      if (caseSensitive && code.equals(c.getCodeSimple()))
        return true;
      if (!caseSensitive && code.equalsIgnoreCase(c.getCodeSimple()))
        return true;
      if (codeInDefine(c.getConcept(), code, caseSensitive))
        return true;
    }
    return false;
  }

}
