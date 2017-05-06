package org.hl7.fhir.igtools.publisher;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hl7.fhir.convertors.R2ToR4Loader;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.context.SimpleWorkerContext;

public class SpecificationPackage {

  private SimpleWorkerContext context;

  public static SpecificationPackage fromPath(String path) throws FileNotFoundException, IOException, FHIRException {
    SpecificationPackage self = new SpecificationPackage();
    self.context = SimpleWorkerContext.fromPack(path);
    return self;
  }

  public static SpecificationPackage fromClassPath(String path) throws IOException, FHIRException {
    SpecificationPackage self = new SpecificationPackage();
    self.context = SimpleWorkerContext.fromClassPath(path);
    return self;
  }

  public static SpecificationPackage fromPath(String path, R2ToR4Loader loader) throws FileNotFoundException, IOException, FHIRException {
     SpecificationPackage self = new SpecificationPackage();
    self.context = SimpleWorkerContext.fromPack(path, loader);
    return self;
  }

  public SimpleWorkerContext makeContext() {
    return new SimpleWorkerContext(context);
  }

}
