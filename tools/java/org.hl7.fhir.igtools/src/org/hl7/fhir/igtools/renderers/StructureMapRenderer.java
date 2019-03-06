package org.hl7.fhir.igtools.renderers;

import java.io.IOException;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.publisher.FetchedResource;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.igtools.publisher.SpecMapManager;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureMap;
import org.hl7.fhir.r5.utils.StructureMapUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.r5.utils.StructureMapUtilities.StructureMapAnalysis;
import org.hl7.fhir.utilities.MarkDownProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class StructureMapRenderer extends BaseRenderer {


  private StructureMapUtilities utils;
  private StructureMap map;
  private StructureMapAnalysis analysis;
  private String destDir;

  public StructureMapRenderer(IWorkerContext context, String prefix, StructureMap map, String destDir, IGKnowledgeProvider igp, List<SpecMapManager> maps, MarkDownProcessor markdownEngine, NpmPackage packge) {
    super(context, prefix, igp, maps, markdownEngine, packge);
    this.map = map;
    this.destDir = destDir;
    utils = new StructureMapUtilities(context, null, igp);
    analysis = (StructureMapAnalysis) map.getUserData("analysis");
  }

  public String summary(FetchedResource r, boolean xml, boolean json, boolean ttl) throws Exception {
//    return "[--Summary goes here--]";
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tbody><tr><td>"+translate("sm.summary", "Defining URL")+":</td><td>"+Utilities.escapeXml(map.getUrl())+"</td></tr>\r\n");
    if (map.hasVersion())
      b.append(" <tr><td>"+translate("cs.summary", "Version")+":</td><td>"+Utilities.escapeXml(map.getVersion())+"</td></tr>\r\n");
    b.append(" <tr><td>"+translate("sm.summary", "Name")+":</td><td>"+Utilities.escapeXml(gt(map.getNameElement()))+"</td></tr>\r\n");
    if (map.hasDescription())
      b.append(" <tr><td>"+translate("sm.summary", "Definition")+":</td><td>"+processMarkdown("description", map.getDescriptionElement())+"</td></tr>\r\n");
    if (map.hasPublisher())
      b.append(" <tr><td>"+translate("sm.summary", "Publisher")+":</td><td>"+Utilities.escapeXml(gt(map.getPublisherElement()))+"</td></tr>\r\n");
    if (map.hasCopyright())
      b.append(" <tr><td>"+translate("sm.summary", "Copyright")+":</td><td>"+Utilities.escapeXml(gt(map.getCopyrightElement()))+"</td></tr>\r\n");
    if (ToolingExtensions.hasExtension(map, ToolingExtensions.EXT_FMM_LEVEL))
      b.append(" <tr><td><a class=\"fmm\" href=\"versions.html#maturity\" title=\"Maturity Level\">"+translate("cs.summary", "Maturity")+"</a>:</td><td>"+ToolingExtensions.readStringExtension(map, ToolingExtensions.EXT_FMM_LEVEL)+"</td></tr>\r\n");
    if (xml || json || ttl) {
      b.append(" <tr><td>"+translate("sm.summary", "Source Resource")+":</td><td>");
      boolean first = true;
      String filename = igp.getProperty(r, "format");
      if (filename == null)
        filename = "ValueSet-"+r.getId()+".{{[fmt]}}.html";
      if (xml) {
        first = false;
        b.append("<a href=\""+igp.doReplacements(filename,  r,  null, "xml")+"\">"+translate("sm.summary", "XML")+"</a>");
      }
      if (json) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\""+igp.doReplacements(filename,  r,  null, "json")+"\">"+translate("sm.summary", "JSON")+"</a>");
      }
      if (ttl) {
        if (first) first = false; else b.append(" / ");
        b.append("<a href=\""+igp.doReplacements(filename,  r,  null, "ttl")+"\">"+translate("sm.summary", "Turtle")+"</a>");
      }
      b.append("</td></tr>\r\n");
    }
    b.append("</tbody></table>\r\n");

    return b.toString();    
  }

  public String profiles() {
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    for (StructureDefinition sd : analysis.getProfiles()) {
      b.append("  <li><a href=\""+sd.getUserString("path")+"\">"+Utilities.escapeXml(gt(sd.getNameElement()))+"</a></li>\r\n");
    }
    b.append("</ul>\r\n");
    return b.toString();
  }

  public String script() throws FHIRException {
    return StructureMapUtilities.render(map);
  }

  public String content() throws IOException {
    if (analysis == null) {
      try {
        analysis = utils.analyse(null, map);
      } catch (FHIRException e) {
        return "Error in Map: "+e.getMessage();  
      }
      map.setUserData("analysis", analysis);
    }      
    return new XhtmlComposer(XhtmlComposer.HTML).compose(analysis.getSummary());
  }


  
}
