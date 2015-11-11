package org.hl7.fhir.instance.test;

import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.test.FHIRPathTests.TestFHIRPathEvaluator;
import org.hl7.fhir.instance.utils.FHIRPathEvaluator;

public class FHIRPathTests {

  public class TestFHIRPathEvaluator extends FHIRPathEvaluator {

    @Override
    protected void getChildrenByName(Base item, String name, List<Base> result) {
      throw new Error("not done yet");
    }

  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  public void testPaths() throws Exception {
    check("aggregation.empty() or (code = \"Reference\")");
    check("binding.empty() or type.code.empty() or type.any((code = 'code') or (code = 'Coding') or (code='CodeableConcept') or (code = 'Quantity') or (code = 'Extension') or (code = 'string') or (code = 'uri'))");
    check("(low.empty() or ((low.code = \"%\") and (low.system = %ucum))) and (high.empty() or ((high.code = \"%\") and (high.system = %ucum)))");
    check("kind != 'root' or uniqueId in ('uuid' | 'ruid')");
  }

  private void check(String path) throws Exception {
    new TestFHIRPathEvaluator().check(null, path);   
  }
  
}
