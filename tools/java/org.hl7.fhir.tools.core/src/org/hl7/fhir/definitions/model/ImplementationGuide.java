package org.hl7.fhir.definitions.model;

public class ImplementationGuide {

  private String code;
  private String name;
  private String page;
  private boolean review;
  private boolean ballot;
  
  public ImplementationGuide(String code, String name, String page, boolean review, boolean ballot) {
    super();
    this.code = code;
    this.name = name;
    this.page = page;
    this.review = review;
    this.ballot = ballot;
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

  public boolean isBallot() {
    return ballot;
  }
  
}
