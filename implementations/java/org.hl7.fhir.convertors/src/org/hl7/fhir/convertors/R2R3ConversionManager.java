package org.hl7.fhir.convertors;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class manages conversion from R2 to R3 and vice versa
 * 
 * To use this class, do the following:
 * 
 *  - provide a stream or path (file or URL) that points to R2 definitions (from http://hl7.org/fhir/DSTU2/downloads.html)
 *  - provide a stream or a path (file or URL) that points to the R3 definitions  (from http://hl7.org/fhir/DSTU2/downloads.html)
 *  - provide a stream or a path (file or URL) that points to R2/R3 map files (from ...? )
 * 
 *  - call convert()
 *  
 * @author Grahame Grieve
 *
 */
public class R2R3ConversionManager {

  // set up ------------------------------------------------------------------
  public void setR2Definitions(InputStream stream) {
    
  }
  
  public void setR2Definitions(String source) {
    
  }
  
  public void setR3Definitions(InputStream stream) {
    
  }
  
  public void setR3Definitions(String source) {
    
  }
  
  public void setMappingLibrary(InputStream stream) {
    
  }
  
  public void setMappingLibrary(String source) {
    
  }
  
  // execution 
  public byte[] convert(byte[] source) {
      throw new Error("not implemented yet");
  }

  public OutputStream convert(InputStream source) {
    throw new Error("not implemented yet");
  }

  public org.hl7.fhir.dstu2.model.Resource convert(org.hl7.fhir.dstu3.model.Resource source) {
    throw new Error("not implemented yet");
  }

  public org.hl7.fhir.dstu3.model.Resource convert(org.hl7.fhir.dstu2.model.Resource source) {
    throw new Error("not implemented yet");
  }
  
}
