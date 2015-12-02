package org.hl7.fhir.tools.publisher;

import java.util.Map;

import org.hl7.fhir.definitions.generators.specification.SvgGenerator;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.dstu21.model.StructureDefinition;
import org.hl7.fhir.dstu21.utils.ProfileUtilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class LogicalModelProcessor extends BuildToolScriptedPageProcessor {

  StructureDefinition definition;
  String tx;
  String dict;
  private Map<String, String> examples;
  
  public LogicalModelProcessor(String title, PageProcessor page, ImplementationGuideDefn ig, String name, String type, String pagePath, StructureDefinition definition, String tx, String dict, Map<String, String> examples) {
    super(title, ig.getLevel(), page, ig, name, type, pagePath);
    this.definition = definition;
    this.tx = tx;
    this.dict = dict;
    this.examples = examples;
    }

  @Override
  protected String processCommand(String command, String[] com) throws Exception {
    if (com[0].equals("lmheader"))
      return lmHeader(name, definition.getId(), com.length > 1 ? com[1] : null, true);
    else if (com[0].equals("svg"))
      return new SvgGenerator(page, genlevel()).generate(definition, com[1], "");        
    else if (com[0].equals("draft-note"))
      return page.getDraftNote(definition);
    else if (com[0].equals("definition"))
      return definition.getDescription();
    else if (com[0].equals("tx"))
      return tx;
    else if (com[0].equals("inv"))
      return genConstraints(definition);
    else if (com[0].equals("resource-table"))
      return genLogicalModelTable(definition, genlevel());
    else if (com[0].equals("dictionary"))
      return dict;
    else if (com[0].equals("lmexamples"))
      return genExampleList();      
    else if (com[0].equals("resref"))
      return "{todo}";      
    else if (com[0].equals("maponthispage"))
      return "{todo}";      
    else if (com[0].equals("mappings"))
      return "{todo}";      
    else 
      return super.processCommand(command, com);
  }


  private String genExampleList() {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"list\">\r\n");
    for (String n : examples.keySet()) {
      b.append("<tr><td>"+examples.get(n)+"</td><td><a href=\""+n+".xml.html\">XML</a></td><td><a href=\""+n+".json.html\">JSON</a></td></tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String lmHeader(String n, String title, String mode, boolean hasXMlJson) throws Exception {
    StringBuilder b = new StringBuilder();

    b.append("<ul class=\"nav nav-tabs\">");
    
    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    if (hasXMlJson) {
      b.append(makeHeaderTab("XML", n+".xml.html", "xml".equals(mode)));
      b.append(makeHeaderTab("JSON", n+".json.html", "json".equals(mode)));
    }
    b.append("</ul>\r\n");

    return b.toString();   
  }

  private String genLogicalModelTable(StructureDefinition sd, String prefix) throws Exception {
    ProfileUtilities pu = new ProfileUtilities(page.getWorkerContext(), null, page);
    XhtmlNode x = pu.generateTable(sd.getId()+"-definitions.html", sd, false, page.getFolders().dstDir, false, sd.getId(), true, prefix);
    return new XhtmlComposer().compose(x);
  }

  @Override
  protected ResourceDefn getResource() {
    return null;
  }

  @Override
  protected String getDescription() {
    return definition.getDescription();
  }

}
