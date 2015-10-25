package org.hl7.fhir.tools.publisher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.utilities.ScriptedPageProcessor;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public abstract class BuildToolScriptedPageProcessor extends ScriptedPageProcessor {

  protected List<String> tabs = new ArrayList<String>();
  protected PageProcessor page;
  protected String pagePath;
  protected String name;
  protected String type;
  protected ImplementationGuideDefn ig;
  
  public BuildToolScriptedPageProcessor(String title, int level, PageProcessor page, ImplementationGuideDefn ig, String name, String type, String pagePath) {
    super(title, level);
    this.ig = ig;
    this.name = name;
    this.type = type;
    this.page = page;
    this.pagePath = pagePath;
  }

  @Override
  protected String processCommand(String command, String[] com) throws Exception {

    if (com[0].equals("file"))
      return TextFile.fileToString(page.getFolders().srcDir + com[1]+".html");
    else if (com[0].equals("gendate"))
      return Config.DATE_FORMAT().format(page.getGenDate().getTime());
    else if (com[0].equals("name"))
      return name;
    else if (com[0].equals("version"))
      return page.getIni().getStringProperty("FHIR", "version");
    else if (com[0].equals("breadcrumb"))
      return page.getBreadCrumbManager().make(name);
    else if (com[0].equals("navlist"))
      return page.getBreadCrumbManager().navlist(name, genlevel());
    else if (com[0].equals("breadcrumblist"))
      return ((ig == null || ig.isCore()) ? page.getBreadCrumbManager().makelist(name, type, genlevel(), title) : ig.makeList(name, type, genlevel(), title));      
    else if (com[0].equals("year"))
      return new SimpleDateFormat("yyyy").format(page.getGenDate());      
    else if (com[0].equals("revision"))
      return page.getSvnRevision();      
    else if (com[0].equals("pub-type"))
      return page.getPublicationType();      
    else if (com[0].equals("pagepath"))
      return pagePath;  
    else if (com[0].equals("rellink"))
      return Utilities.URLEncode(pagePath);  
    else if (com[0].equals("search-footer")) 
      return searchFooter(level);
    else if (com[0].equals("search-header")) 
      return searchHeader(level);
    else if (com[0].equals("introduction")) 
      return page.loadXmlNotes(name, "introduction", true, getDescription(), getResource(), tabs, ig);
    else if (com[0].equals("notes")) 
      return page.loadXmlNotes(name, "notes", false, getDescription(), getResource(), tabs, ig);
    else if (com[0].equals("plural"))
      return Utilities.pluralizeMe(name);
    else if (com[0].equals("baseURL"))
      return Utilities.URLEncode(page.getBaseURL());  
    else 
      return super.processCommand(command, com);
  }

  protected abstract ResourceDefn getResource();
  protected abstract String getDescription();

  private String searchFooter(int level) {
    return "<a style=\"color: #81BEF7\" href=\"http://hl7.org/fhir/search.cfm\">Search</a>";
  }

  private String searchHeader(int level) {
    return "<div id=\"hl7-nav\"><a id=\"hl7-logo\" no-external=\"true\" href=\"http://hl7.org/fhir/search.cfm\"><img alt=\"Search FHIR\" src=\"./assets/images/search.png\"/></a></div>";
  }

  protected String makeHeaderTab(String tabName, String path, Boolean selected)
  {
    StringBuilder b = new StringBuilder();
    
    if(!selected)
    {
      b.append("<li>");
      b.append(String.format("<a href=\"%s\">%s</a>", path, tabName));
    }
    else
    {
      b.append("<li class=\"active\">");
      b.append(String.format("<a href=\"#\">%s</a>", tabName));
    }

    b.append("</li>");
    
    return b.toString();    
  }
  
  protected String genConstraints(StructureDefinition res) throws Exception {
    StringBuilder b = new StringBuilder();
    for (ElementDefinition e : res.getSnapshot().getElement()) {      
      for (ElementDefinitionConstraintComponent inv : e.getConstraint()) {
        if (!e.getPath().contains("."))
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
        else
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: On "+e.getPath()+": "+Utilities.escapeXml(inv.getHuman())+" (xpath on "+presentPath(e.getPath())+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      }
    }    
    if (b.length() > 0)
      return "<p>Constraints</p><ul>"+b+"</ul>";
    else
      return "";
  }
  
  private String presentPath(String path) {
    String[] parts = path.split("\\.");
    StringBuilder s = new StringBuilder();
    for (String p : parts) {
      if (s.length() > 0)
        s.append("/");
      s.append("f:"+p);
    }
    return s.toString();
      
  }


}
