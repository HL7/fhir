package org.hl7.fhir.instance.formats;

import java.io.InputStream;

import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
import org.hl7.fhir.instance.model.Resource;


/**
 * General interface - either an XML or JSON parser. 
 * Defined to allow a factory to create a parser of the right type
 */
public interface Parser {

  /**
   * parse content that is either a resource or a bundle  
   */
  public ResourceOrFeed parseGeneral(InputStream input) throws Exception;
  
  /**
   * parse content that is known to be a resource  
   */
  public Resource parse(InputStream input) throws Exception;

}
