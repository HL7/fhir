package org.hl7.fhir.igtools.renderers;

import java.io.IOException;

import org.hl7.fhir.r4.context.IWorkerContext;
import org.hl7.fhir.r4.model.CapabilityStatement;
import org.hl7.fhir.utilities.TextFile;

public class SwaggerGenerator {

  private IWorkerContext context;
  private StringBuilder yaml;
  private String version;
  
  public SwaggerGenerator(IWorkerContext context, String version) {
    this.context = context;
    this.version = version;
    yaml = new StringBuilder();
    yaml.append("swagger: 2.0\r\n\r\n");
  }

  public void generate(CapabilityStatement cpbs) {
    generateInfo(cpbs);
    yaml.append("# host: [user to provide]\r\n");
    yaml.append("# basePath: [user to provide]\r\n");
    yaml.append("schemes:\r\n");
    yaml.append("  - https\r\n");
    yaml.append("  # - http this would not normally be allowed (see doco)\r\n");
    yaml.append("consumes:\r\n");
    genMimeTypes(cpbs);
    yaml.append("produces:\r\n");
    genMimeTypes(cpbs);
  }

  private void genMimeTypes(CapabilityStatement cpbs) {
    String jt = version.equals("1.4.0") ? "application/json+fhir" : "application/fhir+json"; 
    String xt = version.equals("1.4.0") ? "application/json+xml" : "application/fhir+xml";
    if (!cpbs.hasFormat()) {
      yaml.append("  - "+jt+"\r\n");
      yaml.append("  - "+xt+"\r\n");
    } else {
      if (cpbs.hasFormat("json")) 
        yaml.append("  - "+jt+"\r\n");
      if (cpbs.hasFormat("xml")) 
        yaml.append("  - "+xt+"\r\n");
    }
  }

  private void generateInfo(CapabilityStatement cpbs) {
    yaml.append("info:");
    yaml.append("  title: "+cpbs.getName()+"\r\n");
    yaml.append("  description: "+cpbs.getDescription()+"\r\n");
    yaml.append("  version: "+cpbs.getVersion()+"\r\n");
    yaml.append("  # contact:\r\n");
    yaml.append("    # end user to fill out\r\n");
    yaml.append("  license:\r\n");
    yaml.append("    name: "+ cpbs.getCopyright()+"\r\n\r\n");
  }

  public void save(String path) throws IOException {
    TextFile.stringToFile(yaml.toString(), path);    
  }

}
