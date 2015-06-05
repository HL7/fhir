package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;

public class ImplementationGuide {

  private String code;
  private String name;
  private String page;
  private boolean review;
  private String source;
  private List<String> pageList = new ArrayList<String>();
  
  public ImplementationGuide(String code, String name, String page, String source, boolean review) {
    super();
    this.code = code;
    this.name = name;
    this.source = source;
    this.page = page;
    this.review = review;
  }
  
  public String getCode() {
    return code;
  }
  public String getName() {
    return name;
  }
  public String getPage() {
    return page;
  }
  public boolean isReview() {
    return review;
  }
  public void setReview(boolean review) {
    this.review = review;
  }

  public String getSource() {
    return source;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public List<String> getPageList() {
    return pageList;
  }
  
}
