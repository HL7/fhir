package org.hl7.fhir.igtools.publisher;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.context.SimpleWorkerContext;
import org.hl7.fhir.r5.context.SimpleWorkerContext.IContextResourceLoader;
import org.hl7.fhir.utilities.cache.NpmPackage;

public class SpecificationPackage {

  private SimpleWorkerContext context;

//  public static SpecificationPackage fromPath(String path) throws FileNotFoundException, IOException, FHIRException {
//    SpecificationPackage self = new SpecificationPackage();
//    self.context = SimpleWorkerContext.fromPath(path);
//    return self;
//  }

//  public static SpecificationPackage fromClassPath(String path) throws IOException, FHIRException {
//    SpecificationPackage self = new SpecificationPackage();
//    self.context = SimpleWorkerContext.fromClassPath(path);
//    return self;
//  }
//
//  public static SpecificationPackage fromPath(String path, IContextResourceLoader loader) throws FileNotFoundException, IOException, FHIRException {
//    SpecificationPackage self = new SpecificationPackage();
//    self.context = SimpleWorkerContext.fromPath(path, loader);
//    return self;
//  }

  public SimpleWorkerContext makeContext() throws FileNotFoundException, IOException, FHIRException {
    return new SimpleWorkerContext(context);
  }

  public void loadOtherContent(NpmPackage pi) throws FileNotFoundException, Exception {
    context.loadBinariesFromFolder(pi);

  }

  public static SpecificationPackage fromPackage(NpmPackage pi, IContextResourceLoader loader) throws FileNotFoundException, IOException, FHIRException {
    SpecificationPackage self = new SpecificationPackage();
    self.context = SimpleWorkerContext.fromPackage(pi, loader);
    return self;
  }

  public static SpecificationPackage fromPackage(NpmPackage pi) throws FileNotFoundException, IOException, FHIRException {
    SpecificationPackage self = new SpecificationPackage();
    self.context = SimpleWorkerContext.fromPackage(pi, null);
    return self;
  }

}
