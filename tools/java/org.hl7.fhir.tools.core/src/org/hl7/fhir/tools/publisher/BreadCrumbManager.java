package org.hl7.fhir.tools.publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BreadCrumbManager {

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
    public String getResource() {
      return resource;
    }
    public void setResource(String resource) {
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
        if (p.getResource() != null)
          map.put(p.getResource().toLowerCase(), path);
          
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
    return pages;
  }
  
  private Page parsePage(Element node) throws Exception {
    Page page = new Page();
    page.setTitle(node.getAttribute("title"));
    if (node.hasAttribute("type")) {
      if (node.getAttribute("type").equals("resource")) {
        page.setType(PageType.resource);
        page.setResource(node.getAttribute("resource"));
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
        b.append("<a class=\"breadcrumb\" href=\""+focus.getFilename()+"\">"+focus.getTitle()+"</a>");
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
    b.append("              <li><a href=\""+prefix+"index.html\">Home</a></li>\r\n");
    for (Node n : home.getChildren()) {
      b.append("              <li><a href=\""+prefix+((Page) n).getFilename()+"\">"+((Page) n).getTitle()+"</a></li>\r\n");
    }
    return b.toString();
  }

  public String makelist(String name, String type, String prefix) {
    StringBuilder b = new StringBuilder();
    if (name.equals("index")) {
      b.append("        <li><b>Home</b></li>\r\n");      
    } else {
      b.append("        <li><a href=\""+prefix+"index.html\">Home</a></li>\r\n");
      name = name + ".html";
      if (map.containsKey(name)) {
        String[] path = map.get(name).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length - 1; i++) {
          focus = getChild(focus, path[i]);
          b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+focus.getTitle()+"</a></li>");
        }
        focus = getChild(focus, path[path.length - 1]);
        b.append("        <li><b>"+focus.getTitle()+"</b></li>");
      } else if (map.containsKey(type)) {
        String[] path = map.get(type).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length - 1; i++) {
          focus = getChild(focus, path[i]);
          b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+focus.getTitle()+"</a></li>");
        }
        b.append("        <li><b>"+Utilities.fileTitle(name)+"</b></li>");
      } else if (type.startsWith("res") && map.containsKey(Utilities.fileTitle(name))) {
        String[] path = map.get(Utilities.fileTitle(name)).split("\\.");
        Page focus = home;
        for (int i = 0; i < path.length - 1; i++) {
          focus = getChild(focus, path[i]);
          b.append("        <li><a href=\""+prefix+focus.getFilename()+"\">"+focus.getTitle()+"</a></li>");
        }
        focus = getChild(focus, path[path.length - 1]);
        if (type.equals("resource")) {
          b.append("        <li><b>"+focus.getResource()+"</b></li>");
        } else {
          b.append("        <li><a href=\""+focus.getResource().toLowerCase()+".html\">"+focus.getResource()+"</a></li>");
          b.append("        <li><b>"+type.substring(4)+"</b></li>");          
        }
      } else {
        b.append("        <li>??? "+name+" / "+type+"</li>\r\n");
      }
    }
    return b.toString();
  }

  public String makeToc() {
    StringBuilder b = new StringBuilder();
    writePage(b, home, 0, null);
    return b.toString();
  }

  private void writePage(StringBuilder b, Page p, int level, String path) {
    if (p.getType() == PageType.resource) {
      addLink(b, p.getResource().toLowerCase()+".html", p.getResource(), path, level);
      addLink(b, p.getResource().toLowerCase()+"-examples.html", p.getResource()+" Examples", path+".1", level+1);
      addLink(b, p.getResource().toLowerCase()+"-definitions.html", p.getResource()+" Definitions", path+".2", level+1);
      addLink(b, p.getResource().toLowerCase()+"-mappings.html", p.getResource()+" Mappings", path+".3", level+1);
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
    if (map.containsKey(name)) {
      Page p = pages.get(name);
      if (p.getChildren().size() > 0)
        return map.get(name)+".0";
      else
        return map.get(name);
    }
    if (name.equals("index.html"))
      return "0";
    return "?.?";
  }

  public String getIndexPrefixForResource(String name) {
    return map.get(name.toLowerCase());
  }

  public void makeToc(XhtmlNode p) {
    writePage(p, home, 0, null);    
  }
  
  private void writePage(XhtmlNode node, Page p, int level, String path) {
    if (p.getType() == PageType.resource) {
      addLink(node, p.getResource().toLowerCase()+".html", p.getResource(), path, level);
      addLink(node, p.getResource().toLowerCase()+"-examples.html", p.getResource()+" Examples", path+".1", level+1);
      addLink(node, p.getResource().toLowerCase()+"-definitions.html", p.getResource()+" Definitions", path+".2", level+1);
      addLink(node, p.getResource().toLowerCase()+"-mappings.html", p.getResource()+" Mappings", path+".3", level+1);
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

  
}

