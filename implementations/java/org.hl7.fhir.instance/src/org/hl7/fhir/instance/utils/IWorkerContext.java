package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.ParserType;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.validation.IResourceValidator;

/**
 * Standard interface for work context across reference implementations
 * 
 * @author Grahame
 */
public interface IWorkerContext {
  
  public class FindResourceResponse<T extends Resource> {
  	Bundle bundle;
  	DomainResource container;
  	T resource;
		protected FindResourceResponse(Bundle bundle, DomainResource container, T resource) {
	    super();
	    this.bundle = bundle;
	    this.container = container;
	    this.resource = resource;
    }
		public Bundle getBundle() {
			return bundle;
		}
		public T getResource() {
			return resource;
		}
		public DomainResource getContainer() {
			return container;
		}
  	
  }

  /**
   * This is the general method for resolving references to resources found within a resource
   * Bundles provide a resolution context, so are explicitly part of the API. 
   * 
   * When you find a reference to another resource in a resource, you pass in the 
   * reference itself, the resource it was found in, and (if available), the bundle
   * that the source resource was located in. Note: if the reference is inside a 
   * contained resource, provide the Container, not the contained resource
   *
   * THe implementation of this method will look through the contained resources in 
   * resource (if it's an internal reference - starts with #), or the bundle (following
   * the bundle documentation), if bundle != null. Otherwise, the implementation will
   * use it's cache(s) and or other client(s) to resolve the resource.
   * 
   * Either the resource will be returned, or an exception will be thrown with details
   * about why the resource could not be resolved. If the resolved resource is inside 
   * a bundle (usually, but not always) the same bundle, then the bundle will be returned 
   * as well as the resource, so that it can be passed back to this method for subsequent
   * calls 
   * @param <T>
   *   
   * @param bundle
   * @param resource
   * @param Reference
   * @return
   */
	public <T extends Resource> FindResourceResponse<T> findResource(Class<T> class_, Bundle bundle, DomainResource resource, Reference Reference) throws Exception;

	/**
	 * a simplified call to the same interface that can be used if it's known in advance 
	 * that the resource reference resolution won't depend on a bundle context, nor 
	 * will there be contained resources
	 * 
	 * One place where it is safe to use this is accessing the conformance resources
	 * published as part of the spec (no contained resources, and resolution doesn't
	 * depend on bundle context - all absolute URLs)
	 * 
	 * @param resource
	 * @param Reference
	 * @return
	 * @throws Exception
	 */
	public <T extends Resource> T findResource(Class<T> class_, Reference Reference) throws Exception;

	/**
	 * a simplified call to the same interface that can be used if it's known in advance 
	 * that the resource reference resolution won't depend on a bundle context, nor 
	 * will there be contained resources
	 * 
   * One place where it is safe to use this is accessing the conformance resources
   * published as part of the spec (no contained resources, and resolution doesn't
   * depend on bundle context - all absolute URLs)
   * 
	 * @param resource
	 * @param Reference
	 * @return
	 * @throws Exception
	 */
	public <T extends Resource> T findResource(Class<T> class_, String uri) throws Exception;

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
  
	/**
	 * 
	 * @return a handle to the terminology services associated with this worker context
	 * 
	 * @throws Exception
	 */
	public TerminologyServices getTerminologyServices() throws Exception;
	
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
