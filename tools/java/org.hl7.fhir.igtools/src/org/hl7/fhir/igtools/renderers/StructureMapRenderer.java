package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.terminologies.ValueSetUtilities;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities.StructureMapAnalysis;
import org.hl7.fhir.igtools.publisher.FetchedResource;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class StructureMapRenderer extends BaseRenderer {


  private StructureMapUtilities utils;
  private StructureMap map;
  private StructureMapAnalysis analysis;
  private String destDir;

  public StructureMapRenderer(IWorkerContext context, String prefix, StructureMap map, String destDir, IGKnowledgeProvider igp, List<SpecMapManager> maps) {
    super(context, prefix, igp, maps);
    this.map = map;
    this.destDir = destDir;
    utils = new StructureMapUtilities(context, null, null, igp);
    analysis = (StructureMapAnalysis) map.getUserData("analysis");
  }

  public String summary(FetchedResource r, boolean xml, boolean json, boolean ttl) throws Exception {
//    return "[--Summary goes here--]";
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tbody><tr><td>Defining URL:</td><td>"+Utilities.escapeXml(map.getUrl())+"</td></tr>\r\n");
    b.append(" <tr><td>Name:</td><td>"+Utilities.escapeXml(map.getName())+"</td></tr>\r\n");
    if (map.hasDescription())
      b.append(" <tr><td>Definition:</td><td>"+processMarkdown("description", map.getDescription())+"</td></tr>\r\n");
    if (map.hasPublisher())
      b.append(" <tr><td>Publisher:</td><td>"+Utilities.escapeXml(map.getPublisher())+"</td></tr>\r\n");
    if (map.hasCopyright())
      b.append(" <tr><td>Copyright:</td><td>"+Utilities.escapeXml(map.getCopyright())+"</td></tr>\r\n");
    if (xml || json || ttl) {
      b.append(" <tr><td>Source Resource</td><td>");
      boolean first = true;
      String filename = igp.getProperty(r, "format");
      if (filename == null)
        filename = "ValueSet-"+r.getId()+".{{[fmt]}}.html";
      if (xml) {
        first = false;
        b.append("<a href=\""+igp.doReplacements(filename,  r,  null, "xml")+"\">XML</a>");
      }
      if (json) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\""+igp.doReplacements(filename,  r,  null, "json")+"\">JSON</a>");
      }
      if (ttl) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\""+igp.doReplacements(filename,  r,  null, "ttl")+"\">Turtle</a>");
      }
      b.append("</td></tr>\r\n");
    }
    b.append("</tbody></table>\r\n");

    return b.toString();    
  }

  public String profiles() {
    return "[--Profiles go here--]";
  }

  public String script() throws FHIRException {
    return utils.render(map);
  }

  public String content() throws IOException {
    return new XhtmlComposer().compose(analysis.getSummary());
  }


  
}
