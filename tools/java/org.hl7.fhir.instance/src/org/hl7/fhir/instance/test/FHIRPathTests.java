package org.hl7.fhir.instance.test;

import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.utils.FHIRPathEvaluator;
import org.hl7.fhir.instance.utils.FHIRPathEvaluator.MappingContext;

public class FHIRPathTests {

  public static void main(String[] args) throws Exception {
    new FHIRPathTests().testPaths();
  }
  public void testPaths() throws Exception {
//    check("aggregation.empty() or (code = \"Reference\")");
//    check("binding.empty() or type.code.empty() or type.any((code = 'code') or (code = 'Coding') or (code='CodeableConcept') or (code = 'Quantity') or (code = 'Extension') or (code = 'string') or (code = 'uri'))");
//    check("(low.empty() or ((low.code = \"%\") and (low.system = %ucum))) and (high.empty() or ((high.code = \"%\") and (high.system = %ucum)))");
//    check("kind != 'root' or uniqueId in ('uuid' | 'ruid')");
//    check("reference.startsWith(\"#\").not() or $resource.contained.where(id = $context.reference.substring(1))");
//    check("(name.item(1).family | name.item(2).family).count() < 4");

//      check("where(item = %ucum)");
   
    checkMappings();
  }

  private void checkMappings() throws Exception {
//    InternalPathEvaluator me = new InternalPathEvaluator(null);
//    MappingContext ctxt = me.initMapping();
    //me.parseMap(ctxt, "Composition.section.title; if (value) Composition.section.title; rf := Composition.section.title; var c := Coding[code := '123']");
//    me.parseMap(ctxt, "c := Coding[code := '123']");
  }

  private void check(String path) throws Exception {
    new FHIRPathEvaluator(null).check(null, null, null, path, false);   
  }
  
}
