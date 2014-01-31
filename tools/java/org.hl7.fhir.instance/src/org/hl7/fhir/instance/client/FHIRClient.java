package org.hl7.fhir.instance.client;

/*
Copyright (c) 2011-2013, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;

/*
Copyright (c) 2011-2013, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/

/**
 * FHIR RESTful Client Interface.
 * 
 * @author Claude Nanjo
 * @author Grahame Grieve
 *
 */
public interface FHIRClient {

	/**
	 * Call method to initialize FHIR client. This method must be invoked
	 * with a valid base server URL prior to using the client.
	 * 
	 * Invalid base server URLs will result in a URISyntaxException being thrown.
	 * 
	 * @param baseServiceUrl Base service URL for FHIR Service.
	 * @throws URISyntaxException
	 */
	public void initialize(String baseServiceUrl)  throws URISyntaxException;
	
	/**
	 * Override the default resource format of 'application/fhir+xml'. This format is
	 * used to set Accept and Content-Type headers for client requests.
	 * 
	 * @param resourceFormat
	 */
	public void setPreferredResourceFormat(ResourceFormat resourceFormat);
	
	/**
	 * Returns the resource format in effect.
	 * 
	 * @return
	 */
	public String getPreferredResourceFormat();
	
	/**
	 * Override the default feed format of 'application/atom+xml'. This format is
	 * used to set Accept and Content-Type headers for client requests.
	 * 
	 * @param resourceFormat
	 */
	public void setPreferredFeedFormat(FeedFormat feedFormat);
	
	/**
	 * Returns the feed format in effect.
	 * 
	 * @return
	 */
	public String getPreferredFeedFormat();
	
	/**
	 * Method returns a conformance statement for the system queried.
	 * @return
	 */
	public Conformance getConformanceStatement();
	
	/**
	 * Method returns a conformance statement for the system queried.
	 * 
	 * @param useOptionsVerb If 'true', use OPTION rather than GET.
	 * 
	 * @return
	 */
	public Conformance getConformanceStatement(boolean useOptionsVerb);
	
	/**
	 * Read the current state of a resource.
	 * 
	 * @param resource
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomEntry<T> read(Class<T> resource, String id);

	/**
	 * Read the state of a specific version of the resource
	 * 
	 * @param resource
	 * @param id
	 * @param versionid
	 * @return
	 */
	public <T extends Resource> AtomEntry<T> vread(Class<T> resource, String id, String versionid);
	
	/**
	 * Update an existing resource by its id or create it if it is a new resource, not present on the server
	 * 
	 * @param resourceClass
	 * @param resource
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id);
	
	/**
	 * Update an existing resource by its id or create it if it is a new resource, not present on the server
	 * 
	 * @param resourceClass
	 * @param resource
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id, List<AtomCategory> tags);
	
	/**
	 * Delete the resource with the given ID.
	 * 
	 * @param resourceClass
	 * @param id
	 * @return
	 */
	public <T extends Resource> boolean delete(Class<T> resourceClass, String id); 

	/**
	 * Create a new resource with a server assigned id. Return the newly created
	 * resource with the id the server assigned.
	 * 
	 * @param resourceClass
	 * @param resource
	 * @return
	 */
	public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource);
	
	/**
	 * Create a new resource with a server assigned id. Return the newly created
	 * resource with the id the server assigned. Associates tags with newly created resource.
	 * 
	 * @param resourceClass
	 * @param resource
	 * @return
	 */
	public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource, List<AtomCategory> tags);
	
	/**
	 * Retrieve the update history for a resource with given id since last update time. 
	 * Last update may be null TODO - ensure this is the case.
	 * 
	 * @param lastUpdate
	 * @param resourceClass
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id);
	
	/**
	 * Retrieve the entire update history for a resource with the given id.
	 * Last update may be null TODO - ensure this is the case.
	 * 
	 * @param lastUpdate
	 * @param resourceClass
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomFeed history(Class<T> resource, String id);
	
	/**
	 * Retrieve the update history for a resource type since the specified calendar date.
	 * Last update may be null TODO - ensure this is the case.
	 * 
	 * @param lastUpdate
	 * @param resourceClass
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass);
	
	/**
	 * Retrieve the update history for all resource types since the specified calendar date.
	 * Last update may be null TODO - ensure this is the case.
	 * 
	 * @param lastUpdate
	 * @param resourceClass
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomFeed history(Calendar lastUpdate);
	
	/**
	 * Validate resource payload.
	 * 
	 * @param resourceClass
	 * @param resource
	 * @param id
	 * @return
	 */
	public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id);
	
	/**
	 * Return all results matching search query parameters for the given resource class.
	 * 
	 * @param resourceClass
	 * @param params
	 * @return
	 */
	public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> params);
	
  /**
   * Return all results matching search query parameters for the given resource class.
   * This includes a resource as one of the parameters, and performs a post
   * 
   * @param resourceClass
   * @param params
   * @return
   */
  public <T extends Resource> AtomFeed searchPost(Class<T> resourceClass, T resource, Map<String, String> params);
	
	/**
	 * Update or create a set of resources
	 * 
	 * @param batch
	 * @return
	 */
	public AtomFeed transaction(AtomFeed batch);
	
	/**
	 * Get a list of all tags on server 
	 * 
	 * GET [base]/_tags
	 */
	public List<AtomCategory> getAllTags();
	
	/**
	 * Get a list of all tags used for the nominated resource type 
	 * 
	 * GET [base]/[type]/_tags
	 */
	public <T extends Resource> List<AtomCategory> getAllTagsForResourceType(Class<T> resourceClass);
	
	/**
	 * Get a list of all tags affixed to the nominated resource. This duplicates the HTTP header entries 
	 * 
	 * GET [base]/[type]/[id]/_tags
	 */
	public <T extends Resource> List<AtomCategory> getTagsForResource(Class<T> resource, String id);
	
	/**
	 * Get a list of all tags affixed to the nominated version of the resource. This duplicates the HTTP header entries
	 * 
	 * GET [base]/[type]/[id]/_history/[vid]/_tags
	 */
	public <T extends Resource> List<AtomCategory> getTagsForResourceVersion(Class<T> resource, String id, String versionId);
	
	/**
	 * Remove all tags in the provided list from the list of tags for the nominated resource
	 * 
	 * DELETE [base]/[type]/[id]/_tags
	 */
	//public <T extends Resource> boolean deleteTagsForResource(Class<T> resourceClass, String id);
	
	/**
	 * Remove tags in the provided list from the list of tags for the nominated version of the resource
	 * 
	 * DELETE [base]/[type]/[id]/_history/[vid]/_tags
	 */
	//public <T extends Resource> boolean deleteTagsForResourceVersion(Class<T> resourceClass, String id, List<AtomCategory> tags, String version);
	
	/**
	 * Affix tags in the list to the nominated resource
	 * 
	 * POST [base]/[type]/[id]/_tags
	 * @return
	 */
	public <T extends Resource> AtomEntry<OperationOutcome> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id);
	
	/**
	 * Affix tags in the list to the nominated version of the resource
	 * 
	 * POST [base]/[type]/[id]/_history/[vid]/_tags
	 * 
	 * @return
	 */
	public <T extends Resource> AtomEntry<OperationOutcome> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version);
}
