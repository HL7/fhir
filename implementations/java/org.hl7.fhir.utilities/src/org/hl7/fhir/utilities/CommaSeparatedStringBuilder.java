package org.hl7.fhir.utilities;

public class CommaSeparatedStringBuilder {

  boolean first = true;
  StringBuilder b = new StringBuilder();

  public void append(String value) {
    if (!first)
      b.append(", ");
    b.append(value);
    first = false;    
    
  }
  
  public String toString() {
    return b.toString();
  }
}
