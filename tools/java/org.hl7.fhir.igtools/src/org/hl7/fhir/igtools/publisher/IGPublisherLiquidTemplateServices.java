package org.hl7.fhir.igtools.publisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.utils.NarrativeGenerator.ILiquidTemplateProvider;
import org.hl7.fhir.r5.utils.NarrativeGenerator.ResourceContext;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class IGPublisherLiquidTemplateServices implements ILiquidTemplateProvider {

  Map<String, String> templates = new HashMap<>();
  
  public void clear() {
    templates.clear();
  }
  
  public void load(String path) throws FileNotFoundException, IOException {
    for (File f : new File(path).listFiles()) {
      if (f.getName().endsWith(".liquid")) {
        String fn = f.getName();
        fn = fn.substring(0, fn.indexOf("."));
        templates.put(fn.toLowerCase(), TextFile.fileToString(f));
      }
    }
  }

  @Override
  public String findTemplate(ResourceContext rcontext, DomainResource r) {
    String s = r.fhirType();
    return templates.get(s.toLowerCase());
  }

}
