package org.hl7.fhir.tools.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.ExtensionContextType;
import org.hl7.fhir.r5.utils.Translations;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BreadCrumbManager {

  
  private Translations translations;
  private Definitions definitions;
  private IWorkerContext context;

  public BreadCrumbManager(Translations translations) {
    super();
    this.translations = translations;
  }

  public class Node {
    
  }
  
  public enum PageType {
    page, resource;
  }
  public class Page extends Node {
    private String title;
    private String id;
    private PageType type;
    private String filename;
    private String source;
    private String resource;
    private String icon;
    
    private List<Node> children = new ArrayList<BreadCrumbManager.Node>();
    
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    public PageType getType() {
      return type;
    }
    public void setType(PageType type) {
      this.type = type;
    }
    public String getFilename() {
      return filename;
    }
    public void setFilename(String filename) {
      this.filename = filename;
    }
    public String getReference() {
      return resource;
    }
    public void setReference(String resource) {
      this.resource = resource;
    }
    public List<Node> getChildren() {
      return children;
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getSource() {
      return source;
    }
    public void setSource(String source) {
      this.source = source;
    }
    public String getIcon() {
      return icon;
    }
    public void setIcon(String icon) {
      this.icon = icon;
    }
    
  }
  
  public enum PagesType {
    codeSystem, valueSet, v2Vocab, v3Vocab;
  }
  public class Pages extends Node {
    private PagesType type;
    private String template;
    public PagesType getType() {
      return type;
    }
    public void setType(PagesType type) {
      this.type = type;
    }
    public String getTemplate() {
      return template;
    }
    public void setTemplate(String template) {
      this.template = template;
    }
  }
 
  private Page home;
  private Map<String, String> map = new HashMap<String, String>();
  private Map<String, Page> pages = new HashMap<String, BreadCrumbManager.Page>();
  private Map<String, Pages> pagesMap = new HashMap<String, BreadCrumbManager.Pages>();
  
  public void parse(String filename) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(filename)));
    if (xdoc.getDocumentElement().getNodeName().equals("fhir")) {
      home = parsePage(XMLUtil.getFirstChild(xdoc.getDocumentElement()));
    } else 
      throw new Exception("File not recognised");
    // now we assign section numbers to everything, and index the source files
    home.setId("0");
    numberChildren(home, null);
  }

  private void numberChildren(Page page, String root) {
    int i = 0;
    for (Node node : page.getChildren()) {
      i++;
      String path = root == null? Integer.toString(i) : root +"."+Integer.toString(i);
      if (node instanceof Page) {
        Page p = (Page) node;
        pages.put(p.getFilename(), p);
        map.put(p.getFilename(), path);
        if (p.getSource() != null)
          map.put(p.getSource(), path);
        if (p.getReference() != null)
          map.put(p.getReference().toLowerCase(), path);
          
        p.setId(Integer.toString(i));
        numberChildren(p, path);
      } else if (node instanceof Pages) {
        Pages p = (Pages) node;
        map.put(p.getType().toString(), path);
      }
    }    
  }

  private Pages parsePages(Element node) throws Exception {
    Pages pages = new Pages();
    pages.setTemplate(node.getAttribute("filename"));
    String s = node.getAttribute("type");
    if ("codesystem".equals(s))
      pages.setType(PagesType.codeSystem);
    else if ("v2-table".equals(s))
      pages.setType(PagesType.v2Vocab);
    else if ("v3-vocab".equals(s))
      pages.setType(PagesType.v3Vocab);
    else if ("valueset".equals(s))
      pages.setType(PagesType.valueSet);
    pagesMap.put(pages.getTemplate(), pages);
    return pages;
  }
  
  private Page parsePage(Element node) throws Exception {
    Page page = new Page();
    page.setTitle(node.getAttribute("title"));
    page.setIcon(node.getAttribute("icon"));
    if (node.hasAttribute("type")) {
      if (node.getAttribute("type").equals("resource")) {
        page.setType(PageType.resource);
        page.setReference(node.getAttribute("resource"));
      } else
        throw new Exception("Unknown page node type");
    } else { 
      page.setType(PageType.page);
      page.setFilename(node.getAttribute("filename"));
    }
    if (node.hasAttribute("source"))
      page.setSource(node.getAttribute("source"));
    
    Element child = XMLUtil.getFirstChild(node);
    while (child != null) {
      if (child.getNodeName().equals("page"))
        page.getChildren().add(parsePage(child));
      else if (child.getNodeName().equals("pages"))
          page.getChildren().add(parsePages(child));
        else
        throw new Exception("Unknown element "+child.getNodeName());
      child = XMLUtil.getNextSibling(child);
    }
    return page;
  }

  public String make(String name) {
    name = name + ".html";
    if (map.containsKey(name)) {
      String[] path = map.get(name).split("\\.");
      StringBuilder b = new StringBuilder();
      b.append("<a class=\"breadcrumb\" href=\"index.html\">FHIR</a>");
      Page focus = home;
      for (int i = 0; i < path.length; i++) {
        b.append(" / ");
        focus = getChild(focus, path[i]);
        b.append("<a class=\"breadcrumb\" href=\""+focus.getFilename()+"\">"+Utilities.escapeXml(focus.getTitle())+"</a>");
      }
      return b.toString();
    } else
      return "?? "+name;
  }

  private Page getChild(Page focus, String id) {
    int i = Integer.parseInt(id) - 1;
    return (Page) focus.getChildren().get(i);
  }

  public String navlist(String name, String prefix) {
    StringBuilder b = new StringBuilder();
//    b.append("              <li><a href=\""+prefix+"index.html\">"+translations.getMessage("HOME", "Home")+"</a></li>\r\n");
    for (Node n : home.getChildren()) {
      b.append("              <li><a href=\""+prefix+((Page) n).getFilename()+"\">"+imgLink(prefix, (Page) n)+Utilities.escapeXml(((Page) n).getTitle())+"</a></li>\r\n");
    }
    return b.toString();
  }

  public String makelist(String name, String type, String prefix, String title) throws Exception {
    StringBuilder b = new StringBuilder();
    if (name.equals("index")) {
      b.append("        <li><b>Home</b></li>\r\n");      
    } else {
      //b.append("        <li><a href=\""+prefix+"index.html\">"+translations.getMessage("HOME", "Home")+"</a></li>\r\n");
      if (!name.endsWith(".html"))
        name = name + ".html";
      String nt = Utilities.changeFileExt(name, "");
      if (map.containsKey(nt) && Utilities.noString(type)) {
        String[] path = map.get(nt).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length - 1; i++) {
          focus = getChild(focus, path[i]);
          if (focus.getFilename() != null)
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>\r\n");
        }
        focus = getChild(focus, path[path.length - 1]);
        b.append("        <li><b>"+imgLink(prefix, focus)+Utilities.escapeXml(focus.resource)+"</b></li>");
      } else if (map.containsKey(name)) {
        String[] path = map.get(name).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length - 1; i++) {
          focus = getChild(focus, path[i]);
          if (focus.getFilename() != null)
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>\r\n");
        }
        focus = getChild(focus, path[path.length - 1]);
        b.append("        <li><b>"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</b></li>");
      } else if (map.containsKey(type) && !type.equals("resource")) {
        String[] path = map.get(type).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length - 1; i++) {
          focus = getChild(focus, path[i]);
          b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
        }
        b.append("        <li><b>"+imgLink(prefix, focus)+Utilities.escapeXml(title)+"</b></li>");
      } else if (type.equals("example") && name.contains("-") && map.containsKey(name.substring(0, name.indexOf("-")))) {
        String[] path = map.get(name.substring(0, name.indexOf("-"))).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length; i++) {
          focus = getChild(focus, path[i]);
          if (focus.type == PageType.resource)
            b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
          else
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
        }
        b.append("        <li><b>Example</b></li>");
      } else {
        String s = map.get(type.substring(type.indexOf(":")+1).toLowerCase());
        if (type.startsWith("resource-instance") && type.contains(":")) {
          if (s == null)
            throw new Exception("Unable to find map for "+type);
          String[] path = s.split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length; i++) {
            focus = getChild(focus, path[i]);
            if (focus.type == PageType.resource)
              b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            else
              b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(Utilities.escapeXml(focus.getTitle()))+"</a></li>");
          }
          if (type.startsWith("resource-instance"))
            b.append("        <li><b>Example Instance</b></li>");
          else
            b.append("        <li><b>Profile Instance</b></li>");
        } else if (type.startsWith("resource-questionnaire") && type.contains(":")) {
          if (s == null)
            throw new Exception("Unable to find map for "+type);
          String[] path = s.split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length; i++) {
            focus = getChild(focus, path[i]);
            if (focus.type == PageType.resource)
              b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            else
              b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><b>Generated Questionnaire</b></li>");
        } else if (type.equals("valueset-instance") && name.contains(".")) {
          String[] path = map.get("terminologies-valuesets.html").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length; i++) {
            focus = getChild(focus, path[i]);
            if (focus.type == PageType.resource)
              b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            else
              b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><a href=\""+prefix+name.substring(0, name.indexOf("."))+".html\">"+Utilities.escapeXml(name.substring(0, name.indexOf(".")))+"</a></li>");
          b.append("        <li><b>Instance</b></li>");
        } else if (type.equals("conceptmap-instance") && name.contains(".")) {
          String[] path = map.get("terminologies-conceptmaps.html").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length; i++) {
            focus = getChild(focus, path[i]);
            if (focus.type == PageType.resource)
              b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            else
              b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><b>Example</b></li>");
        } else if (type.startsWith("res") && map.containsKey(Utilities.fileTitle(name))) {
          String[] path = map.get(Utilities.fileTitle(name)).split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length - 1; i++) {
            focus = getChild(focus, path[i]);
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          focus = getChild(focus, path[path.length - 1]);
          if (type.equals("resource")) {
            b.append("        <li><b>"+Utilities.escapeXml(focus.getReference())+"</b></li>");
          } else {
            b.append("        <li><a href=\""+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            b.append("        <li><b>"+Utilities.escapeXml(type.substring(4))+"</b></li>");          
          }
        } else if (type.startsWith("profile:")) {
          String p = s;
          if (p == null) {
            // the bit after profile is resource.pack.profile
            String[] path = type.substring(type.indexOf(":")+1).split("\\/");
            if (map.containsKey(path[0].toLowerCase())) {
              String[] path2 = map.get(path[0].toLowerCase()).split("\\.");
              Page focus = home;
              for (int i = 0; i < path2.length - 1; i++) {
                focus = getChild(focus, path2[i]);
                b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
              }
              focus = getChild(focus, path2[path2.length - 1]);
              b.append("        <li><a href=\""+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            } else {
              if (!Utilities.noString(path[0]))
                b.append("        <li><a href=\""+prefix+path[0].toLowerCase()+".html\">"+Utilities.escapeXml(path[0])+"</a></li>");
              if (Utilities.noString(path[0]) || definitions.hasResource(path[0]))
                b.append("        <li><a href=\""+prefix+(Utilities.noString(path[0]) ? "profilelist" : path[0].toLowerCase()+"-profiles")+".html\">Profiles</a></li>");
            }
            
            Profile pack = definitions.hasResource(path[0]) ? definitions.getResourceByName(path[0]).getConformancePackage(path[1]) : null;
            if (pack == null || !("profile".equals(pack.metadata("navigation")) && pack.getProfiles().size() == 1))
              b.append("        <li><a href=\""+prefix+path[1].toLowerCase()+".html\">Profile</a></li>");
//          b.append("        <li><a href=\""+prefix+path[0].toLowerCase()+".html\">"+path[0]+"</a></li>");
          } else {
            String[] path = p.split("\\.");
            Page focus = home;
            for (int i = 0; i < path.length; i++) {
              focus = getChild(focus, path[i]);
              if (focus.type == PageType.resource)
                b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
              else
                b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
            }
          }
          b.append("        <li><b>"+Utilities.escapeXml(title)+"</b></li>");
        } else if (type.startsWith("profile-instance:type")) {
          String[] path = map.get("datatypes.html").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length; i++) {
            focus = getChild(focus, path[i]);
            if (focus.type == PageType.resource)
              b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            else
              b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><b>Profile</b></li>");
        } else if (type.startsWith("profile-instance:resource")) { // TODO: profile-questionnaire:
          String t = type.substring(type.lastIndexOf(":")+1);
          if (t==null)
            throw new Exception("Unable to read type "+type);
          String obj = map.get(t.toLowerCase());
          if (obj == null)
            throw new Exception("Unable to find type "+t);
          if (type.startsWith("profile-instance:resource")) {
            String[] path = obj.split("\\.");
            Page focus = home;
            for (int i = 0; i < path.length; i++) {
              focus = getChild(focus, path[i]);
              if (focus.type == PageType.resource)
                b.append("          <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
              else
                b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
            }
            b.append("        <li><b>Profile Instance</b></li>");
          } else if (type.startsWith("profile-instance:res:")) {
            String[] path = obj.split("\\.");
            Page focus = home;
            for (int i = 0; i < path.length; i++) {
              focus = getChild(focus, path[i]);
              if (focus.type == PageType.resource)
                b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
              else
                b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
            }
            b.append("        <li><a href=\""+prefix+Utilities.fileTitle(name)+".html\">"+Utilities.escapeXml(title)+"</a></li>");
            b.append("        <li><b>Profile Instance</b></li>");
          }
        } else if (type.startsWith("profile-instance") || type.startsWith("profile-questionnaire") ) {
          String[] path = map.get("profilelist.html").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length; i++) {
            focus = getChild(focus, path[i]);
            if (focus.type == PageType.resource)
              b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
            else
              b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><b>Profile</b></li>");
        } else if (type.startsWith("v2:")) {
          String[] path = map.get("v2Vocab").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length - 1; i++) {
            focus = getChild(focus, path[i]);
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><a href=\"index.html\">"+Utilities.escapeXml(title)+"</a></li>");
          b.append("        <li><b>Instance</b></li>");
        } else if (type.startsWith("v3:")) {        
          String[] path = map.get("v3Vocab").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length - 1; i++) {
            focus = getChild(focus, path[i]);
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><a href=\"index.html\">"+Utilities.escapeXml(Utilities.fileTitle(name.substring(3)))+"</a></li>");
          b.append("        <li><b>Instance</b></li>");
        } else if (type.startsWith("sid:")) {        
          String[] path = map.get("terminologies.html").split("\\.");
          Page focus = home;
          for (int i = 0; i < path.length - 1; i++) {
            focus = getChild(focus, path[i]);
            b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
          }
          b.append("        <li><a href=\""+prefix+"terminologies.html\">Terminologies</a></li>");
          b.append("        <li><a href=\""+prefix+"terminologies-systems.html\">Systems</a></li>");
          b.append("        <li><b>SID: "+Utilities.escapeXml(type.substring(4))+"</b></li>");
        } else if ((name.startsWith("extension-") || name.startsWith("cqif\\extension-")) && name.endsWith(".html")) {
          String url = title;
          StructureDefinition ext = context.fetchResource(StructureDefinition.class, url);
          if (!ext.hasContext() || ext.getContextFirstRep().getType() != ExtensionContextType.ELEMENT) { 
            b.append("        <li>??? "+Utilities.escapeXml(name)+" / "+Utilities.escapeXml(type)+"</li>\r\n");
            System.out.println("no breadcrumb: name = "+name+", type = "+type+", prefix = "+prefix+", title = '"+title+"'");
          } else {
            String[] path;
            String ttl;
            String fn;
            String[] p = ext.getContextFirstRep().getExpression().split("\\.");
            if (definitions.hasType(p[0])) {
              path = map.get("datatypes.html").split("\\.");
              ttl = "Data Types";
              fn = "datatypes";
            } else {
              String rn = p[0];
              ttl = rn;
              if (ttl.equals("*"))
                ttl = "Resource";
              fn = ttl.toLowerCase();
              String m = map.get(fn);
              if (m == null)
                path = new String[] { "??" };
              else
                path = m.split("\\.");
            }
            Page focus = home;
            for (int i = 0; i < path.length; i++) {
              focus = getChild(focus, path[i]);
              if (focus.getFilename() != null)
                b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>\r\n");
            }
            b.append("        <li><a href=\""+fn+".html\">"+Utilities.escapeXml(ttl)+"</a></li>");
            b.append("        <li><b>Extension</b></li>");
          }          
        } else if (type.equals("logical-model")){
          String m = map.get(head(name).toLowerCase()+".html");
          if (m != null) {
            String[] path = m.split("\\.");
            Page focus = home;
            for (int i = 0; i < path.length; i++) {
              focus = getChild(focus, path[i]);
              if (focus.type == PageType.resource)
                b.append("        <li><a href=\""+prefix+focus.getReference().toLowerCase()+".html\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getReference())+"</a></li>");
              else
                b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+imgLink(prefix, focus)+Utilities.escapeXml(focus.getTitle())+"</a></li>");
            }
          } else
            System.out.println("no breadcrumb: name = "+name+", type = "+type+", prefix = "+prefix+", title = '"+title+"'");
          b.append("        <li><b>Logical Model</b></li>");
        } else if (type.startsWith("search-parameter:") || type.equals("searchparam-instance")){
          b.append("                  <li><a href=\"foundation-module.html\"><img src=\"foundation.png\"/> Foundation</a></li>\r\n");
          b.append("                  <li><a href=\"http.html\">RESTful API</a></li>\r\n");
          b.append("                  <li><a href=\"search.html\">Search</a></li>\r\n");
          b.append("                  <li><b>Search Parameter</b></li>\r\n");
        } else if (name.startsWith("codesystem-")) {
          b.append("                  <li><a href=\"terminology-module.html\"><img src=\"terminology.png\"/> Terminology</a></li>\r\n");
          b.append("                  <li><a href=\"terminologies-systems.html\">Code Systems</a></li>\r\n");
          b.append("                  <li><b>Code System</b></li>\r\n");
        } else if (name.equals("qa.html")){
          b.append("                  <li><b>QA Page</b></li>\r\n");
        } else {
          System.out.println("no breadcrumb: name = "+name+", type = "+type+", prefix = "+prefix+", title = '"+title+"'");
        }
      }
    }
    b.append("        <!-- "+Utilities.escapeXml(name)+" / "+Utilities.escapeXml(type)+" / "+Utilities.escapeXml(title)+"-->\r\n");
    return b.toString();
  }

  private String head(String name) {
    if (name.contains("."))
      return name.substring(0, name.indexOf("."));
    else
      return name;
  }

  private String imgLink(String prefix, Page focus) {
    if (Utilities.noString(focus.icon))
      return "";
    return "<img src=\""+prefix+focus.icon+"\"/> ";
  }

  public String makeToc() {
    StringBuilder b = new StringBuilder();
    writePage(b, home, 0, null);
    return b.toString();
  }

  private void writePage(StringBuilder b, Page p, int level, String path) {
    if (p.getType() == PageType.resource) {
        addLink(b, p.getReference().toLowerCase()+".html", p.getReference(), path, level);
//        if (p.hasExamples())
          addLink(b, p.getReference().toLowerCase()+"-examples.html", p.getReference()+" Examples", path+".1", level+1);
        addLink(b, p.getReference().toLowerCase()+"-definitions.html", p.getReference()+" Definitions", path+".2", level+1);
//        if (p.hasMappings())
          addLink(b, p.getReference().toLowerCase()+"-mappings.html", p.getReference()+" Mappings", path+".3", level+1);
//        if (p.hasProfiles())
          addLink(b, p.getReference().toLowerCase()+"-profiles.html", p.getReference()+" Profiles", path+".4", level+1);
//        if (p.hasOperations())
//          addLink(b, p.getReference().toLowerCase()+"-operations.html", p.getReference()+" Operations", path+".5", level+1);
    } else {
      addLink(b, p.getFilename(), p.getTitle(), path, level);
      for (Node n : p.getChildren()) {
        if (n instanceof Page) {
          writePage(b, (Page) n, level+1, path == null ? ((Page) n).getId() : path+"."+((Page) n).getId());
        }
      }
    }
  }

  private void addLink(StringBuilder b, String name, String title, String path, int level) {
    for (int i = 0; i < level; i++)
      b.append("&nbsp;&nbsp;");
    if (path == null)
      b.append("<a href=\""+name+"\">"+Utilities.escapeXml(title)+"</a><br/>\r\n");
    else 
      b.append("<a href=\""+name+"\">"+path+"</a> "+Utilities.escapeXml(title)+"<br/>\r\n");
  }

  public String getIndexPrefixForFile(String name) {
    if (pagesMap.containsKey(name)) {
      name = pagesMap.get(name).getType().toString();
      return map.get(name)+".X";
    }
    if (map.containsKey(name)) {
      Page p = pages.get(name);
      if (p.getChildren().size() > 0)
        return map.get(name)+".0";
      else
        return map.get(name);
    }
    if (name.equals("index.html"))
      return "0";
    if (name.startsWith("sid:"))
      return getIndexPrefixForFile(name.substring(4))+".X";
    return "?.?";
  }

  public String getIndexPrefixForReference(String name) {
    if (map.containsKey(name.toLowerCase()))
      return map.get(name.toLowerCase());
    else
      return "??.??";
  }

  public void makeToc(XhtmlNode p) {
    writePage(p, home, 0, null);    
  }
  
  private void writePage(XhtmlNode node, Page p, int level, String path) {
    if (p.getType() == PageType.resource) {
      addLink(node, p.getReference().toLowerCase()+".html", p.getReference(), path, level);
      addLink(node, p.getReference().toLowerCase()+"-examples.html", p.getReference()+" Examples", path+".1", level+1);
      addLink(node, p.getReference().toLowerCase()+"-definitions.html", p.getReference()+" Definitions", path+".2", level+1);
      addLink(node, p.getReference().toLowerCase()+"-mappings.html", p.getReference()+" Mappings", path+".3", level+1);
    } else {
      addLink(node, p.getFilename(), p.getTitle(), path, level);
      for (Node n : p.getChildren()) {
        if (n instanceof Page) {
          writePage(node, (Page) n, level+1, path == null ? ((Page) n).getId() : path+"."+((Page) n).getId());
        }
      }
    }
  }

  private void addLink(XhtmlNode p, String name, String title, String path, int level) {
    for (int i = 0; i < level; i++)
      p.addText(XMLUtil.SPACE_CHAR+XMLUtil.SPACE_CHAR);
    if (path == null) {
      XhtmlNode a = p.addTag("a");
      a.setAttribute("href", name);
      a.addText(title);
    }
    else {
      XhtmlNode a = p.addTag("a");
      a.setAttribute("href", name);
      a.addText(path);
      p.addText(" "+title);
    }
    p.addTag("br");
  }

  public List<String> getSpineOrder() throws Exception {
    List<String> res = new ArrayList<String>();
    getSpineOrder1(res, home);
    getSpineOrder2(res, home);
    return res;
  }

  private void getSpineOrder1(List<String> res, Page page) throws Exception {
    if (page.getType() == PageType.resource) {
      ResourceDefn rd = definitions.getResourceByName(page.resource);
      String resource = page.resource.toLowerCase();
      res.add(resource+".html");
      if (!rd.isAbstract()) {
        res.add(resource+"-examples.html");
        res.add(resource+"-definitions.html");
        if (!rd.getName().equals("Parameters")) {
          res.add(resource+"-mappings.html");
          res.add(resource+"-explanations.html");
          res.add(resource+"-profiles.html");
        }
      }
    } else if (!Utilities.noString(page.filename))
      res.add(page.filename);
    for (Node p : page.getChildren()) {
      if (p instanceof Page) 
        getSpineOrder1(res, (Page) p);
      else {
        // ignore for now
      }
    }
    
  }

  private void getSpineOrder2(List<String> res, Page page) {
    for (Node p : page.getChildren()) {
      if (p instanceof Page) 
        getSpineOrder2(res, (Page) p);
      else {
        // ignore for now
      }
    }
    
  }

  public Page getPage() {
    return home;
  }

  public Definitions getDefinitions() {
    return definitions;
  }

  public void setDefinitions(Definitions definitions) {
    this.definitions = definitions;
  }

  public boolean knowsResource(String n) {
    return map.containsKey(n.toLowerCase());
  }

  public IWorkerContext getContext() {
    return context;
  }

  public void setContext(IWorkerContext context) {
    this.context = context;
  }
  
  
}

