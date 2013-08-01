package org.hl7.fhir.tools.publisher;

import java.util.ArrayList;
import java.util.List;

public class BreadCrumbManager {

  public class Node {
    
  }
  
  public enum PageType {
    page, resource;
  }
  public class Page extends Node {
    private String title;
    private PageType type;
    private String filename;
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
    
  }
  
  public enum PagesType {
    codeSystem, ValueSet, v2Table, v3Vocab;
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
  
}

