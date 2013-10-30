package org.hl7.fhir.instance.model;

/**
 * A class that represents the special FHIR resource "Binary"
 */
public class Binary extends Resource {

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Binary;
  }

  /**
   * The content of the binary
   */
  private byte[] content;
  
  /**
   * The mimetype of the binary content
   */
  private String contentType;
  
  /**
   * @return The content of the binary
   */
  public byte[] getContent() {
    return content;
  }
  /**
   * @param content The content of the binary
   */
  public void setContent(byte[] content) {
    this.content = content;
  }
  /**
   * @return The mimetype of the binary content
   */
  public String getContentType() {
    return contentType;
  }
  /**
   * @param contentType The mimetype of the binary content
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  
  
}
