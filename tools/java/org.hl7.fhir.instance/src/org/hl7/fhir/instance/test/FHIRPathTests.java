package org.hl7.fhir.instance.test;

import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.utils.BuildToolPathEvaluator;
import org.hl7.fhir.instance.utils.FHIRPathEvaluator;

public class FHIRPathTests {

  public void testPaths() throws Exception {
    check("aggregation.empty() or (code = \"Reference\")");
    check("binding.empty() or type.code.empty() or type.any((code = 'code') or (code = 'Coding') or (code='CodeableConcept') or (code = 'Quantity') or (code = 'Extension') or (code = 'string') or (code = 'uri'))");
    check("(low.empty() or ((low.code = \"%\") and (low.system = %ucum))) and (high.empty() or ((high.code = \"%\") and (high.system = %ucum)))");
    check("kind != 'root' or uniqueId in ('uuid' | 'ruid')");
    check("reference.startsWith(\"#\").not() or $resource.contained.where(id = $context.reference.substring(1))");
    check("(name.item(1).family | name.item(2).family).count() < 4");
    
  }

  private void check(String path) throws Exception {
    new BuildToolPathEvaluator(null).check(null, null, path, false);   
  }
  
}
