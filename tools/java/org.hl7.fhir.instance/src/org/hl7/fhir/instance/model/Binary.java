package org.hl7.fhir.instance.model;

public class Binary extends Resource {

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Binary;
  }

  private byte[] content;
  private String contentType;
  
  public byte[] getContent() {
    return content;
  }
  public void setContent(byte[] content) {
    this.content = content;
  }
  public String getContentType() {
    return contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  
  
}
