package org.hl7.fhir.utg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BaseExporter {

  protected List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<String>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }
}
