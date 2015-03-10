package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.ParserType;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.validation.IResourceValidator;

/**
 * Standard interface for work context across reference implementations
 * 
 * Provides access to common services that code using FHIR resources needs
 * 
 * @author Grahame
 */
public interface IWorkerContext {

  // -- Parsers (read and write instances) ----------------------------------------

  /**
   * Get a parser to read/write instances. Use the defined type (will be extended 
   * as further types are added, though the only anticipated types is RDF)
   * 
   * XML/JSON - the standard renderers
   * XHTML - render the narrative only (generate it if necessary)
   * 
   * @param type
   * @return
   */
  public IParser getParser(ParserType type);

  /**
   * Get a parser to read/write instances. Determine the type 
   * from the stated type. Supported value for type:
   * - the recommended MIME types
   * - variants of application/xml and application/json
   * - _format values xml, json
   * 
   * @param type
   * @return
   */	
  public IParser getParser(String type);

  /**
   * Get a JSON parser
   * 
   * @return
   */
  public IParser newJsonParser();

  /**
   * Get an XML parser
   * 
   * @return
   */
  public IParser newXmlParser();

  // -- access to fixed content ---------------------------------------------------

  /**
   * Fetch a fixed resource that's pre-known in advance, and loaded as part of the
   * context. The most common use of this is to access the the standard conformance
   * resources that are part of the standard - profiles, extension definitions, and 
   * value sets (etc).
   * 
   * The context loader may choose to make additional resources available (i.e. 
   * implementation specific conformance statements, profiles, extension definitions)
   * 
   * Schemas and other similar non resource content can be accessed as Binary resources
   * using their filename in validation.zip as the id (http:/hl7/.org/fhir/Binary/[name]
   * 
   * @param resource
   * @param Reference
   * @return
   * @throws Exception
   */
  public <T extends Resource> T fetchResource(Class<T> class_, String uri) throws Exception;

  // -- Ancilliary services ------------------------------------------------------

  /**
   * Return a client configured to access the nominated server by it's base URL 
   * 
   * Todo: how does security work?
   * 
   * @param base
   * @return
   * @throws Exception
   */
  public IFHIRClient getClient(String base) throws Exception;

  /**
   * 
   * @return a handle to the terminology services associated with this worker context
   * 
   * @throws Exception
   */
  public ITerminologyServices getTerminologyServices() throws Exception;

  /**
	 * 
	 * @return a handle to the terminology services associated with this worker context
	 * 
	 * @throws Exception
	 */
	public IWorkerContext setTerminologyServices(ITerminologyServices value);
	
	/**
   * Get a generator that can generate narrative for the instance
   * 
   * @return a prepared generator
   */
  public INarrativeGenerator getNarrativeGenerator();

  /**
   * Get a validator that can check whether a resource is valid 
   * 
   * @return a prepared generator
   */
  public IResourceValidator newValidator();

}
