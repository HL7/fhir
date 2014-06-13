package org.hl7.fhir.definitions.model;

public class MappingSpace {

  private String columnName; // in the spreadsheets
  private String title; // in the specification
  private String preamble; // html to go in spec
  private String id; // internal page reference
  private int sortOrder; 
  
  public MappingSpace(String columnName, String title, String id, int sortOrder) {
    super();
    this.columnName = columnName;
    this.title = title;
    this.id = id;
    this.sortOrder = sortOrder;
    this.preamble = "";
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPreamble() {
    return preamble;
  }

  public void setPreamble(String preamble) {
    this.preamble = preamble;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
  
  
}
